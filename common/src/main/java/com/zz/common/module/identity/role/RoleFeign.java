package com.zz.common.module.identity.role;

import com.zz.common.common.core.domain.ResponseResult;
import com.zz.common.module.identity.role.domain.dto.GetRoleListDTO;
import com.zz.common.module.identity.role.domain.dto.GetRoleModuleListDTO;
import com.zz.common.module.identity.role.domain.entity.ModuleListEntity;
import com.zz.common.module.identity.role.domain.entity.RoleInfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName:RoleFeign
 * @Author: vren
 * @Date: 2022/7/19 16:30
 */
@FeignClient(value = "UserApi")
public interface RoleFeign {

    @RequestMapping(value = "/api/user/Role/GetRoleLinkModuleList", method = RequestMethod.POST)
    ResponseResult<List<ModuleListEntity>> getRoleLinkModuleList(GetRoleModuleListDTO dto);

    @RequestMapping(value = "/api/user/Role/GetRoleListForPage", method = RequestMethod.POST)
    ResponseResult<List<RoleInfoEntity>> getRoleListForPage(GetRoleListDTO dto);

}
