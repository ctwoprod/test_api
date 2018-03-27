package org.rf.test.persistence;

import org.rf.test.config.AppConfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceFactory {

	private static HikariDataSource DATASOURCE;

	public static HikariDataSource getDataSource(AppConfig appConfig) {
		if (DATASOURCE == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", appConfig.getConfigAsString("DB_HOST"),
					appConfig.getConfigAsInteger("DB_PORT"), appConfig.getConfigAsString("DB_NAME")));
			config.setUsername(appConfig.getConfigAsString("DB_USERNAME"));
			config.setPassword(appConfig.getConfigAsString("DB_PASSWORD"));
			config.addDataSourceProperty("connectionTimeout", "2000");
			config.addDataSourceProperty("maximumPoolSize", "100");
			DATASOURCE = new HikariDataSource(config);
		}
		return DATASOURCE;
	}
}
