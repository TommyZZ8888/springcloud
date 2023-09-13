package com.zz.gateway.module;

import com.zz.gateway.module.config.SwaggerProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.List;

@SpringBootTest
class GatewayApplicationTests {

    @Autowired
    private SwaggerProvider provider;

@Test
void context(){
    List<SwaggerResource> swaggerResources = provider.get();
    System.out.println(swaggerResources);
}
}
