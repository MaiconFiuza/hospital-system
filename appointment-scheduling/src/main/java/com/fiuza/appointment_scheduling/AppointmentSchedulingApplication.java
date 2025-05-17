package com.fiuza.appointment_scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.example.shared.entities"})
@SpringBootApplication
public class AppointmentSchedulingApplication {

	public static void main(String[] args) {


		SpringApplication.run(AppointmentSchedulingApplication.class, args);
	}

}
