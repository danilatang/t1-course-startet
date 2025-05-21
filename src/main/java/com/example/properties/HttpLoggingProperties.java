package com.example.properties;

import com.example.constants.LogLevel;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logging.from.starter")
public class HttpLoggingProperties {
    private boolean enabled;
    private LogLevel logLevel = LogLevel.INFO;

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LogLevel getLevel() {
        return logLevel;
    }

    public void setLevel(LogLevel level) {
        this.logLevel = level;
    }
}