package org.rf.test.config;

import org.apache.commons.beanutils.ConvertUtils;

public class EnvironmentVariableConfig implements AppConfig {
    @Override
    public <T> T getConfig(String key, Class<T> clazz) {
        return (T) ConvertUtils.convert(System.getenv(key), clazz);
    }
}
