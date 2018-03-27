package org.rf.test.injection;

import javax.sql.DataSource;

import org.rf.test.config.AppConfig;
import org.rf.test.config.AppConfigFactory;
import org.rf.test.helper.IdGenerator;
import org.rf.test.persistence.HikariDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {
	private static final Logger logger = LoggerFactory.getLogger(AppModule.class);

	@Provides
	@Singleton
	AppConfig provideAppConfig() {
		return AppConfigFactory.getAppConfig();
	}

	@Provides
	@Singleton
	DataSource provideDataSource(AppConfig appConfig) {
		return HikariDataSourceFactory.getDataSource(appConfig);
	}

	@Provides
	@Singleton
	ObjectMapper provideObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper;
	}

	@Provides
	@Singleton
	IdGenerator provideIdGenerator() {
		return new IdGenerator();
	}

	@Override
	protected void configure() {
	}
}
