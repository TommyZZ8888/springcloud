package com.vren.common.module.identity.user.domain.dto;

import lombok.Data;

/**
 * 用户信息
 * @author 耿让
 */
@Data
public class UserInfoDto {

    private String token_UserId;

    private String token_UserName;

    private String token_ClientId;

    private String keyId;

    private String userName;

    private String chName;

    private Integer gender;

    private String workerNo;

    private String cardNo;

    private String idCardNo;

    private String depID;

    private String teamID;

    private String photoImg;

    private String signImg;

    private String roleID;

    private boolean isActive;

    private String comment;

    private Integer isDelete;

}
