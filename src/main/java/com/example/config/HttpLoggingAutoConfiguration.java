package com.example.config;

import com.example.aspect.HttpLoggingAspect;
import com.example.properties.HttpLoggingProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HttpLoggingProperties.class)
@ConditionalOnProperty(
        prefix = "logging.from.starter",
        name = "enabled",
        havingValue = "true"
)
public class HttpLoggingAutoConfiguration {

    @Bean
    public HttpLoggingAspect httpLoggingAspect(HttpLoggingProperties httpLoggingProperties) {
        return new HttpLoggingAspect(httpLoggingProperties);
    }
}
