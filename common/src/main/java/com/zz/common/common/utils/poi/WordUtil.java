package com.zz.common.common.utils.poi;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author 耿让
 * 文档：http://deepoove.com/poi-tl/
 */
public class WordUtil {


    /**
     *  map模板数据，不带list
     * @param templatePath  模板文件路径
     * @param fileName   导出文件名称
     * @param data      数据
     * @param response
     */
    public static void createWord(String templatePath, String fileName, Map data, HttpServletResponse response) throws IOException {
        XWPFTemplate compile = compile(templatePath, fileName, response, null);
        compile.render(data);
        compile.write(response.getOutputStream());
    }

    /**
     *  对象导出
     * @param templatePath
     * @param fileName
     * @param data
     * @param response
     * @throws IOException
     */
    public static void createWord(String templatePath, String fileName, Object data, HttpServletResponse response) throws IOException {
        XWPFTemplate compile = compile(templatePath, fileName,response, null);
        compile.render(data);
        compile.write(response.getOutputStream());
    }
    public static void createWord(String templatePath, String fileName, Object data, HttpServletResponse response,Configure config) throws IOException {
        XWPFTemplate compile = compile(templatePath, fileName,response, config);
        compile.render(data);
        compile.write(response.getOutputStream());
    }

    public static void createWord(String templatePath, String fileName, Map data, HttpServletResponse response,Configure config) throws IOException {
        XWPFTemplate compile = compile(templatePath, fileName, response, config);
        compile.render(data);
        compile.write(response.getOutputStream());
    }

    public static XWPFTemplate compile(String templatePath, String fileName, HttpServletResponse response, Configure config) throws IOException {
        Assert.notNull(templatePath, "word模板文件路径不能为空");
        Assert.notNull(fileName, "生成的文件名不能为空");
        //添加响应头
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //暴露新添加的响应头
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        InputStream inputStream = new ClassPathResource(templatePath).getInputStream();
        XWPFTemplate compile;
        if (config!=null){
            compile = XWPFTemplate.compile(inputStream,config);
        }else {
            compile = XWPFTemplate.compile(inputStream);
        }
        return compile;
//        compile.render(data);
//        compile.write(response.getOutputStream());
    }

}