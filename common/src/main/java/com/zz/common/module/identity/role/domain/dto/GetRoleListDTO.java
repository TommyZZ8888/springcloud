package com.zz.common.module.identity.role.domain.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class GetRoleListDTO {

    @JSONField(name = "token_UserId")
    private String tokenUserId;
    @JSONField(name = "token_UserName")
    private String tokenUserName;
    @JSONField(name = "token_ClientId")
    private String tokenClientId;

    private Integer pageIndex = 1;

    private Integer pageSize = 9999;

    private String roleName;
}
