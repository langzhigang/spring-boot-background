package cn.lzg.springboot.background.config.exception;

/**
 * 自定义业务异常类
 * @author lzg
 * @Date 2017/2/13.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 2654796954495294836L;
    private int code;
    private String message;

    public BusinessException() {
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
