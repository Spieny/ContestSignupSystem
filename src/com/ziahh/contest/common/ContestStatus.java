package com.ziahh.contest.common;

public enum ContestStatus {
    NOT_START(0),
    STARTED(1),
    END(2);

    private Integer code;

    ContestStatus(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
