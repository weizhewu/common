package com.waltz.bean.result;

/**
 * @author MrWei
 * @version 1.0.0
 * @date 2023/4/25 22:20
 * @description 结果统一返回码
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS("200", "成功"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR("40001", "系统繁忙，请稍后重试");

    private final String code;

    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static String getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }
}
