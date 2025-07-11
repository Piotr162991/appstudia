package com.agency;

import com.agency.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class,
        })
@EnableConfigurationProperties({AppConfig.class})
@RequiredArgsConstructor
public class Application {

    private final AppConfig appConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
