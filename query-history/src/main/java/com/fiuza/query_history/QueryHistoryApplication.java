package com.fiuza.query_history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.example.shared.entities"})
@SpringBootApplication
public class QueryHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryHistoryApplication.class, args);
	}

}
