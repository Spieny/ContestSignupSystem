package com.ziahh.contest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class SysUpdate {
    private Integer uid;
    private String userName;
    private String userPwd;
    private String updatePwd;
    private String userClass;
}
