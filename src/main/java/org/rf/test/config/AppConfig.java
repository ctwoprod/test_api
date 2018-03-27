package org.rf.test.config;

public interface AppConfig {
    <T> T getConfig(String key, Class<T> clazz);

    default String getConfigAsString(String key){
        return getConfig(key, String.class);
    }

    default Integer getConfigAsInteger(String key){
        return getConfig(key, Integer.class);
    }

    default Double getConfigAsDouble(String key){
        return getConfig(key, Double.class);
    }

    default Long getConfigAsLong(String key){
        return getConfig(key, Long.class);
    }
}
