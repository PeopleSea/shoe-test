package shoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@EnableTransactionManagement
@ComponentScan(basePackages = {"shoes.controller", "shoes.services"})
@EntityScan(basePackages = {"shoes.model"})
@EnableJpaRepositories(basePackages = {"shoes.persistence"})
public class ShoesTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoesTestApplication.class, args);
	}
}
