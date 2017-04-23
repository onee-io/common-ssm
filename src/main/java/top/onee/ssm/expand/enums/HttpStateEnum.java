package top.onee.ssm.expand.enums;

/**
 * 统一状态码
 * Created by VOREVER on 23/04/2017.
 */
public enum HttpStateEnum {

    SUCCESS(200, "请求成功"),
    BAD_PARAMS(400, "错误的请求参数"),
    AUTH_ERROR(401, "请求未授权"),
    NO_DATA(404, "没有数据"),
    FAIL(500, "访问接口失败");

    private int state;
    private String info;

    HttpStateEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

}
