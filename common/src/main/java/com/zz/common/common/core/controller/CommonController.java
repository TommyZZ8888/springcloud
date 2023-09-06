package com.zz.common.common.core.controller;

import com.zz.common.common.annotation.NoNeedLogin;
import com.zz.common.common.config.EnumsMapInit;
import com.zz.common.common.core.domain.ResponseResult;
import com.zz.common.common.core.enums.BaseEnumsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/enums")
public class CommonController extends BaseController {
    @Autowired
    private EnumsMapInit enumsMapInit;

    @RequestMapping(value = "/{enumsClassName}", method = RequestMethod.GET)
    @NoNeedLogin
    public ResponseResult<List<HashMap<String, String>>> toList(@PathVariable String enumsClassName) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            Class<? extends BaseEnumsInterface> aClass = enumsMapInit.getClassHashMap().get(enumsClassName.toLowerCase(Locale.ROOT));
            if (aClass == null) {
                throw new ClassNotFoundException();
            }
            BaseEnumsInterface[] enumConstants = aClass.getEnumConstants();
            for (BaseEnumsInterface enumConstant : enumConstants) {
                HashMap<String, String> map = new HashMap<>();
                enumConstant.extraParameter(map);
                map.put("name", enumConstant.getName());
                map.put("value", enumConstant.getValue());
                result.add(map);
            }
        } catch (ClassNotFoundException e) {
            return ResponseResult.error("未找到查询对象");
        }
        return ResponseResult.success("查询成功", result);
    }

}
