package org.rf.test.config;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;

import java.nio.file.Paths;
import java.util.Arrays;

public class FileAppConfig implements AppConfig {

    private ConfigurationProvider configurationProvider;

    public FileAppConfig() {
        ConfigFilesProvider configFilesProvider = () -> Arrays.asList(Paths.get("application.yml"));
        ConfigurationSource source = new ClasspathConfigurationSource(configFilesProvider);
        this.configurationProvider = new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .build();
    }

    @Override
    public <T> T getConfig(String key, Class<T> clazz) {
        return this.configurationProvider.getProperty(key, clazz);
    }
}
