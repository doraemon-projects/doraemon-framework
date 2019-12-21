package org.doraemon.framework.exception;

/**
 * @Package : com.jfteam.exception
 * @Description :
 * @Author : yuanDong.lin
 * @Date : 2019-08-27 14:32
 */
public enum ExceptionCode {
    PARAM_REQUIRED("5000", "参数不能为空"),
    PARAM_ERROR("5001", "参数异常"),
    RECORDS_NOT_EXIST("6000", "记录不存在"),
    RECORDS_EXIST("60001", "记录已经存在"),
    THIRD_REQUEST_EXCEPTION ("7000","第三方请求异常"),
    SYSTEM_ERROR("8000","系统异常");
    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
