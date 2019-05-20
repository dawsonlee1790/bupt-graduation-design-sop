package bupt.dawsonlee1790.sop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SopApplication.class, args);
	}

}
