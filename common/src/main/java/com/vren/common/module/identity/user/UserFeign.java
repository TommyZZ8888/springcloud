package com.vren.common.module.identity.user;

import com.vren.common.common.core.domain.ResponseResult;
import com.vren.common.module.identity.user.domain.dto.AllUserInfoDTO;
import com.vren.common.module.identity.user.domain.dto.GetUserInfoDTO;
import com.vren.common.module.identity.user.domain.dto.SaveUserInfoDTO;
import com.vren.common.module.identity.user.domain.dto.UserInfoDto;
import com.vren.common.module.identity.user.domain.entity.UserInfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户服务接口
 */
@FeignClient(value = "UserApi")
public interface UserFeign {

    @RequestMapping(value = "/api/user/user/GetUserInfoDetail", method = RequestMethod.POST)
    ResponseResult<UserInfoEntity> getUserInfoDetail(GetUserInfoDTO data);

    @RequestMapping(value = "/api/user/user/GetUserBindSelect", method = RequestMethod.POST)
    ResponseResult<List<UserInfoEntity>> getUserBindSelect();


    /**
     * 调用批量保存用户的方法
     *
     * @param saveUserInfoDTO 参数实体
     * @return
     */
    @RequestMapping(value = "/api/user/user/AddUserInfoList", method = RequestMethod.POST)
    String addUserInfoList(@RequestBody SaveUserInfoDTO saveUserInfoDTO);


    @RequestMapping(value = "/api/user/user/GetUserListForPage", method = RequestMethod.POST)
    String getUserListForPage(@RequestBody AllUserInfoDTO dto);


    @RequestMapping(value = "/api/user/user/SaveUserInfo", method = RequestMethod.POST)
    String addUserInfo(@RequestBody UserInfoDto dto);


}
