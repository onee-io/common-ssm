package top.onee.ssm.expand.exception;

/**
 * 参数验证不通过
 * Created by VOREVER on 23/04/2017.
 */
public class BadParamException extends Throwable {

    private static final long serialVersionUID = 1L;

    public BadParamException() {
    }

    public BadParamException(String message) {
        super(message);
    }

    public BadParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
