package com.vren.common.module.identity.role;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.vren.common.common.core.domain.ResponseResult;
import com.vren.common.module.identity.role.domain.dto.GetRoleListDTO;
import com.vren.common.module.identity.role.domain.dto.GetRoleModuleListDTO;
import com.vren.common.module.identity.role.domain.entity.ModuleListEntity;
import com.vren.common.module.identity.role.domain.entity.RoleInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:RoleService
 * @Author: vren
 * @Date: 2022/7/19 16:34
 */
@Service
public class RoleService {

    @Autowired
    private RoleFeign roleFeign;

    public List<ModuleListEntity> getRoleLinkModuleList(String roleId) {
        GetRoleModuleListDTO dto = new GetRoleModuleListDTO();
        dto.setKeyId(roleId);
        ResponseResult<List<ModuleListEntity>> responseResult = roleFeign.getRoleLinkModuleList(dto);
        return responseResult.getData();
    }


    public List<RoleInfoEntity> getRoleListForPage(String roleName) {
        GetRoleListDTO dto = new GetRoleListDTO();
        dto.setRoleName(roleName);
        ResponseResult<List<RoleInfoEntity>> responseResult = roleFeign.getRoleListForPage(dto);
        return responseResult.getData();
    }


    public List<RoleInfoEntity> getRoleListForPage() {
        GetRoleListDTO dto = new GetRoleListDTO();
        ResponseResult<List<RoleInfoEntity>> responseResult = roleFeign.getRoleListForPage(dto);
        return responseResult.getData();
    }
}
