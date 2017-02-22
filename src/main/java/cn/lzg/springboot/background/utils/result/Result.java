package cn.lzg.springboot.background.utils.result;

import java.io.Serializable;

/**
 * controller层统一返回类型
 *
 * @author lzg
 * @Date 2017/2/13.
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1779220683956492485L;
    private int code;
    private String message;

    private T data;

    public Result() {
    }

    public Result(ApiCode apiCode) {
        this.code = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public Result(ApiCode apiCode, T data) {
        this.code = apiCode.getCode();
        this.message = apiCode.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
