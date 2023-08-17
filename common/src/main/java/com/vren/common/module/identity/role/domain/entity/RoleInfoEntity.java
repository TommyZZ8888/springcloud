package com.vren.common.module.identity.role.domain.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoleInfoEntity {

    private String keyId;

    private String roleName;

    private String moduleName;
}
