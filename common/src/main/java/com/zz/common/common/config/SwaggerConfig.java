package com.zz.common.common.config;

import com.zz.common.module.system.login.LoginServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * @author Vren
 */
//@Configuration
//@EnableOpenApi
public class SwaggerConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .securitySchemes(List.of(httpAuthenticationScheme()))
                .securityContexts(List.of(securityContext()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zz." + applicationName + ".module"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationName + " 接口文档")
                .version("doc")
                .build();
    }

    private HttpAuthenticationScheme httpAuthenticationScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name(LoginServer.AUTH_HEADER_KEY).build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(List.of(SecurityReference.builder().scopes(new AuthorizationScope[0]).reference(LoginServer.AUTH_HEADER_KEY).build()))
                .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                .build();
    }
}
