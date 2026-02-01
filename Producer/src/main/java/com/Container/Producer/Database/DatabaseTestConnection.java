package com.Container.Producer.Database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DatabaseTestConnection {

    @Bean
    CommandLineRunner testDatabaseConnection(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("✅ DATABASE CONNECTION SUCCESSFUL");
                System.out.println("Connected to: " +
                        connection.getMetaData().getURL());
            } catch (Exception e) {
                System.out.println("❌ DATABASE CONNECTION FAILED");
                e.printStackTrace();
            }
        };
    }
}
