package top.onee.ssm.expand.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.onee.ssm.common.CommonResult;
import top.onee.ssm.expand.enums.HttpStateEnum;
import top.onee.ssm.expand.exception.BadParamException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.logstash.logback.marker.Markers.appendEntries;

/**
 * Controller日志切面
 * Created by VOREVER on 23/04/2017.
 */
@Aspect
@Component
@Order(1)
public class ControllerLogAop {

    private static Logger logger = LoggerFactory.getLogger("aop");

    /**
     * 对 @RestController 和 @Controller 进行切面
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)||@within(org.springframework.stereotype.Controller)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object aroundLog(ProceedingJoinPoint pj) {
        Map<String, Object> logs = new HashMap();

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        logs.put("@funName", pj.getSignature().getName());
        logs.put("@requestArgs",  JSON.toJSONString(pj.getArgs(), SerializerFeature.WriteMapNullValue));
        logs.put("_method",method);
        logs.put("@uri",uri);
        logs.put("ip",getRemoteHost(request));
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = pj.proceed();
            logs.put("@executeTime",System.currentTimeMillis()-start);
            logger.info(appendEntries(logs), JSON.toJSONString(result));
        } catch (Throwable t) {
            if (t instanceof BadParamException) {
                result = new CommonResult(HttpStateEnum.BAD_PARAMS);
            } else {
                result = new CommonResult(HttpStateEnum.FAIL);
                logs.put("ErrMsgCuz",JSON.parseObject(JSON.toJSONString(t.getStackTrace()),new TypeReference<List<Map<String,Object>>>(){}));
                logs.put("_errMessage",String.valueOf(t.getClass().getName()+"-"+t.getMessage()));
            }
            logger.error(appendEntries(logs),JSON.toJSONString(result));
        }
        return result;
    }

    /**
     * 获取访问客户ip
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}

