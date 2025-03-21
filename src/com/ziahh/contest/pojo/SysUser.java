package com.ziahh.contest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SysUser implements Serializable {

    private Integer uid;
    private String userName;
    private String userPwd;
    private String userClass;
}
