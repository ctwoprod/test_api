package org.rf.test.helper;

import org.flywaydb.core.Flyway;
import org.rf.test.config.AppConfig;
import org.rf.test.config.AppConfigFactory;
import org.rf.test.persistence.HikariDataSourceFactory;

import javax.sql.DataSource;

public class DatabaseMigration {

    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        AppConfig appConfig = AppConfigFactory.getAppConfig();
        DataSource dataSource = HikariDataSourceFactory.getDataSource(appConfig);
        flyway.setDataSource(dataSource);
        String command = "migrate";
        if(args.length > 0){
            command = args[0];
        }
        switch (command) {
            case "migrate" :
                flyway.repair();
                flyway.migrate();
                break;
            case "reset" :
                flyway.clean();
                flyway.migrate();
                break;
            case "repair" :
                flyway.repair();
                break;
            default:
                throw new IllegalArgumentException("Available arguments are [migrate, clean]");
        }
    }
}
