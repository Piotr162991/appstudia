package com.agency.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class RestConfig {
    private final AppConfig appConfig;

    @ConditionalOnProperty("com.agency.cors-configuration-enabled")
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(appConfig.isCorsAllowCredentials());
        config.setAllowedOriginPatterns(appConfig.getCorsAllowedOrigins());
        config.setAllowedHeaders(appConfig.getCorsAllowedHeaders());
        config.setAllowedMethods(appConfig.getCorsAllowedMethods());
        config.setExposedHeaders(appConfig.getCorsExposedHeaders());
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
