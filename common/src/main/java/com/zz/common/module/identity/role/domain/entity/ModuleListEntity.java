package com.zz.common.module.identity.role.domain.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:ModuleListEntity
 * @Author: vren
 * @Date: 2022/7/19 16:33
 */
@NoArgsConstructor
@Data
public class ModuleListEntity {

    @JsonAlias(value = "token_UserId")
    private String tokenUserId;

    @JsonAlias(value = "token_UserName")
    private String tokenUserName;

    @JsonAlias(value = "token_ClientId")
    private String tokenClientId;

    private String keyId;

    private String moduleCode;

    private String moduleName;

    private String moduleUrl;

    private Integer mType;

    private Integer moduleSubType;

    private String desc;

    private String parentID;

    private Integer seq;

    private Integer isShow;

    private String icon;

    private String color;
}
