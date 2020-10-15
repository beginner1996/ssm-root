package com.sm.cn.common.http.entity;

public enum MyStatus {
    OK(20000,"操作成功"),
    ERROR(50000,"操作失败"),
    EMAIL_NOT_REGISTER(40000,"邮箱没有注册"),
    ERROR_CODE(50000,"验证码错误"),
    NO_REGISTER(50002,"邮箱未激活")
    ;
    private int status;
    private String msg;

    MyStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
