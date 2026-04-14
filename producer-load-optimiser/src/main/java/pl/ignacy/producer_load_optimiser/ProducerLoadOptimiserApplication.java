package pl.ignacy.producer_load_optimiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerLoadOptimiserApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProducerLoadOptimiserApplication.class, args);
	}
}
