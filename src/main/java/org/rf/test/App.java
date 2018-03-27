package org.rf.test;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import org.rf.test.config.AppConfig;
import org.rf.test.controller.BankController;
import org.rf.test.controller.GlobalFilter;
import org.rf.test.controller.PingController;
import org.rf.test.controller.TestController;
import org.rf.test.helper.DatabaseMigration;
import org.rf.test.injection.AppModule;
import org.rf.test.service.BankService;
import org.rf.test.service.TestService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AppModule());
		ObjectMapper objectMapper = injector.getInstance(ObjectMapper.class);
		AppConfig appConfig = injector.getInstance(AppConfig.class);
		// Sentry.init(appConfig.getConfigAsString("SENTRY_DSN"));
		port(appConfig.getConfigAsInteger("PORT"));
		staticFileLocation("/public");
		DatabaseMigration.main(new String[0]);
		new GlobalFilter(objectMapper);
		new PingController();
		new BankController(injector.getInstance(BankService.class), objectMapper);
		new TestController(injector.getInstance(TestService.class), objectMapper);
	}
}
