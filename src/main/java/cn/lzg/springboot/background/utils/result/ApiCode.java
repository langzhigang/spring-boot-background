package cn.lzg.springboot.background.utils.result;

/**
 * 自定义消息返回类型的枚举类
 * @author lzg
 * @Date 2017/2/13.
 */
public enum ApiCode {

    success(200,"成功"),
    fail(500,"失败");

    private int code;
    private String message;

    ApiCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
