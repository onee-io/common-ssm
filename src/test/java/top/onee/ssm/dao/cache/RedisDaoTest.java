package top.onee.ssm.dao.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.onee.ssm.common.CommonConsts;
import top.onee.ssm.dao.mapper.UserInfoMapper;
import top.onee.ssm.modal.DO.UserInfo;

import java.util.List;


/**
 * Redis DAO 单元测试
 * Created by VOREVER on 23/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-redis.xml"
})
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testRedis() throws Exception {
        /*获取测试数据*/
        List<UserInfo> userInfos = userInfoMapper.listOfAllUserInfo();
        /*存入Redis*/
        String result = redisDao.put(CommonConsts.REDIS_KEY_TEST, userInfos);
        Assert.assertEquals("OK", result);
        /*从Redis中取出*/
        List<UserInfo> redisUserInfos = redisDao.getList(CommonConsts.REDIS_KEY_TEST, UserInfo.class);
        System.out.println(redisUserInfos);
    }

}