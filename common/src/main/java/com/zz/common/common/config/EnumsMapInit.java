package com.zz.common.common.config;

import com.zz.common.common.annotation.EnumsClassName;
import com.zz.common.common.core.enums.BaseEnumsInterface;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Locale;

@Component
public class EnumsMapInit {

    private final String BASE_PACKAGE = "com.vren.*.module.*.domain.enums";
    private final String RESOURCE_PATTERN = "/**/*.class";

    private final HashMap<String, Class<? extends BaseEnumsInterface>> classHashMap = new HashMap<>();

    public HashMap<String, Class<? extends BaseEnumsInterface>> getClassHashMap() {
        return classHashMap;
    }

    @PostConstruct
    public void init() throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        String s = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
        Resource[] resources = resourcePatternResolver.getResources(s);
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        for (Resource resource : resources) {
            MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
            String className = metadataReader.getClassMetadata().getClassName();
            Class<?> aClass = Class.forName(className);
            if (BaseEnumsInterface.class.isAssignableFrom(aClass)) {
                Class<? extends BaseEnumsInterface> clazz = (Class<? extends BaseEnumsInterface>) aClass;
                EnumsClassName annotation = aClass.getAnnotation(EnumsClassName.class);
                String key = aClass.getSimpleName().toLowerCase(Locale.ROOT);
                if (annotation != null) {
                    key = annotation.name();
                }
                classHashMap.put(key, clazz);
            }
        }
    }

}
