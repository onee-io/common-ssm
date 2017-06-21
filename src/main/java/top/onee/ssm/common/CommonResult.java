package top.onee.ssm.common;

import top.onee.ssm.expand.enums.HttpStateEnum;

/**
 * 接口通用返回格式
 * Created by VOREVER on 23/04/2017.
 */
public class CommonResult<T> {

    private int state;     /*状态*/

    private String info;        /*错误信息*/

    private T data;             /*返回数据*/

    public CommonResult(HttpStateEnum httpState) {
        this.state = httpState.getState();
        this.info = httpState.getInfo();
    }

    public CommonResult(HttpStateEnum httpState, T data) {
        this.state = httpState.getState();
        this.info = httpState.getInfo();
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
