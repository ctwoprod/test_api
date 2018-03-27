package org.rf.test.config;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.consul.ConsulConfigurationSourceBuilder;
import org.cfg4j.source.reload.ReloadStrategy;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;

import java.util.concurrent.TimeUnit;

public class ConsulAppConfig implements AppConfig {
    private ConfigurationProvider provider;

    public ConsulAppConfig() {
        String consulHost = StringUtils.defaultIfEmpty(System.getenv("CONSUL_HOST"), "localhost");
        String consulPort = StringUtils.defaultIfEmpty(System.getenv("CONSUL_PORT"), "8500");
        ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(5, TimeUnit.SECONDS);
        ConfigurationSource consulSource = new ConsulConfigurationSourceBuilder()
                .withHost(consulHost)
                .withPort(Integer.parseInt(consulPort))
                .build();
        this.provider = new ConfigurationProviderBuilder()
                .withConfigurationSource(consulSource)
                .withReloadStrategy(reloadStrategy)
                .build();
    }

    public <T> T getConfig(String key, Class<T> clazz) {
        return provider.getProperty(key, clazz);
    }
}
