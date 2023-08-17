package com.vren.test;

import com.vren.common.common.utils.ApplicationContextUtils;
import com.vren.common.module.identity.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

@SpringBootTest
public class Tests {


    @Autowired
    private UserService userService;

    @Test
    void context() throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        String s = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath("com.vren.*.module.*.domain.enums") + "/**/*.class";
        Resource[] resources = resourcePatternResolver.getResources(s);
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        for (Resource resource : resources) {
            MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
            String className = metadataReader.getClassMetadata().getClassName();
            System.out.println(className);
        }
    }

    @Test
    void enumTest(){
        System.out.println(ApplicationContextUtils.getBean("objectMapper"));
    }
}
