package com.vren.test.module.test.domain.entity;

import com.deepoove.poi.data.PictureRenderData;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author GR
 * time 2023-05-25-17-01
 **/
@Data
@Builder
public class PersonCopy {

    private String name;

    private Integer age;

    private String gender;

    private Date date;

    private PictureRenderData image;

    private List<Person> lists;
}
