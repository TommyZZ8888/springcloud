package com.zz.common.module.identity.role.domain.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:GetRoleModuleListDTO
 * @Author: vren
 * @Date: 2022/7/19 16:32
 */
@NoArgsConstructor
@Data
public class GetRoleModuleListDTO {

    @JSONField(name = "token_UserId")
    private String tokenUserId;
    @JSONField(name = "token_UserName")
    private String tokenUserName;
    @JSONField(name = "token_ClientId")
    private String tokenClientId;
    private String keyId;
}
