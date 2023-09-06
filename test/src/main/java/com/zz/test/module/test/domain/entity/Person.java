package com.zz.test.module.test.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @author GR
 * time 2023-05-25-16-12
 **/
@Data
@Builder
public class Person {

    private String name;

    private String phoneNumber;

    private String qq;

    @Email(message = "邮箱格式错误")
    private String email;

}
