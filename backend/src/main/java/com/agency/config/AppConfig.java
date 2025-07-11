package com.agency.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(
        prefix = "com.agency"
)
@Getter
@Setter
public class AppConfig {

    private List<String> corsAllowedOrigins;
    private List<String> corsAllowedHeaders;
    private List<String> corsAllowedMethods;
    private List<String> corsExposedHeaders;
    private boolean corsAllowCredentials;
    private boolean corsConfigurationEnabled;
    private String runningEnv;
}
