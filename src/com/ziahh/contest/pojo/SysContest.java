package com.ziahh.contest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SysContest {
    private Integer cid;
    private String contestName;
    private String contestDesc;
    private String contestHost;
    private String contestType;
    private Integer contestTotal;
    private Integer contestEnroll;
    private String contestBeginTime;
    private String contestEndTime;
    private Integer contestStatus;
}
