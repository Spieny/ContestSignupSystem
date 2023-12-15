package com.ziahh.contest.common;


/**
 * @author Ziahh
 */

public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    USERNAME_USED(505,"userNameUsed"),
    CONTEST_ENROLLED(601,"contestEnrolled"),
    CONTEST_ENROLLEND(602,"contestEnrollEnd"),
    CONTEST_ENROLLNOTSTART(603,"contestEnrollNotStart"),
    CONTEST_NOTFOUND(604,"contestNotFound"),
    CONTEST_NOTENROLLED(605,"contestNotEnrolled");

    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}