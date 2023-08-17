package com.vren.common.module.identity.user.domain.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vren.common.module.identity.role.domain.entity.ModuleListEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoEntity {
    private String cHName;

    private String sub;

    private String userName;

    private String missionRole;

    private List<String> missionRoleList;

    private String workerNo;

    @JsonAlias(value = "roleID")
    private String roleId;

    @JsonIgnore
    private String factoryId;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private List<String> moduleCodeList;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private List<ModuleListEntity> moduleList;

    private String userId;

    public void setKeyId(String keyId) {
        this.userId = keyId;
    }

    public void setID(String id) {
        this.userId = id;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public void setValue(String value) {
        this.userName = value;
    }


}
