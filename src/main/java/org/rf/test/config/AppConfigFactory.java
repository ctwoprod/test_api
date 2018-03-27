package org.rf.test.config;

import org.apache.commons.lang3.StringUtils;

public class AppConfigFactory {
    public static AppConfig getAppConfig() {
        if(StringUtils.equalsIgnoreCase("true", System.getenv("CI"))){
            return new EnvironmentVariableConfig();
        } else if (StringUtils.equalsAnyIgnoreCase(System.getenv("APP_ENVIRONMENT"), "staging", "production")) {
            return new ConsulAppConfig();
        } else {
            return new FileAppConfig();
        }
    }
}
