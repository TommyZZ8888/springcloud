package com.zz.gateway.module.system.log.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * user_operate_log
 *
 * @author
 */
@ApiModel(value = "用户操作日志")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOperateLogEntity implements Serializable {

    private String logId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 操作模块
     */
    @ApiModelProperty(value = "操作模块")
    private String module;

    private String jsonResult;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String content;

    /**
     * 请求路径
     */
    @ApiModelProperty(value = "请求路径")
    private String url;

    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    private String method;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String param;

    /**
     * 请求结果 1成功 0失败
     */
    @ApiModelProperty(value = "请求结果 1成功 0失败")
    private Boolean result;

    /**
     * 失败原因
     */
    @ApiModelProperty(value = "失败原因")
    private String failReason;

    private Date updateTime;

    private Date createTime;

    private Long cost;

    private static final long serialVersionUID = 1L;
}