package dawsonlee1790.example.biyesheji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BiyeshejiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiyeshejiApplication.class, args);
	}

}
