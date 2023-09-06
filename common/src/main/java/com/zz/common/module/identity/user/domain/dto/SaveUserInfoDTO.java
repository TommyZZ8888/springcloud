package com.zz.common.module.identity.user.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 耿让
 */
@Data
public class SaveUserInfoDTO {

    private String token_UserId;

    private String token_UserName;

    private String token_ClientId;

    private List<UserInfoDto> userRequests;

}
