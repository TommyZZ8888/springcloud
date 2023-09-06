package com.zz.common.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    private static String profile;

    private static String imageWebsite;

    private static String gatewayUrl;


    public void setGatewayUrl(String gatewayUrl) {
        SystemConfig.gatewayUrl = gatewayUrl;
    }

    public static String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setProfile(String profile) {
        SystemConfig.profile = profile;
    }

    public void setImageWebsite(String imageWebsite) {
        SystemConfig.imageWebsite = imageWebsite;
    }

    public static String getImageWebsite() {
        return imageWebsite;
    }


    public static String getImportPath() {
        return profile;
    }

    public static String getProfile() {
        return profile;
    }

    public static String getDownloadPath() {
        return profile;
    }
}

