package com.ken.common.enums;

public enum ResultEnum {
    SUCCESS(200, "操作成功"), ERROR(500, "系统报错"), NOT_LOGIN(102, "用户没有登录"), NO_AUTHORITY(403,
            "用户没有权限"), NOT_EXITS(104, "用户不存在");

    private Integer code;
    private String info;

    ResultEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
