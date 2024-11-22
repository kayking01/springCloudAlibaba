package com.tulingxueyuan.order.domain;

/*** @author wkx * @date 2024年11月21日 17:07 */
public class Result<T> {
    private String msg;
    private Integer code;
    private T data;

    public Result(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Result(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result error( Integer code, String msg){
        return new Result(msg, code);
    }
}
