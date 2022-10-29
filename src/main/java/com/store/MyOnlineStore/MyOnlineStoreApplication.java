package com.store.MyOnlineStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.SpringProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
public class MyOnlineStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOnlineStoreApplication.class, args);
	}
}
