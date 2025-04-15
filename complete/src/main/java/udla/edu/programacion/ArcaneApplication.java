package udla.edu.programacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("udla.edu.programacion")
@EnableJpaRepositories("udla.edu.programacion")
@ComponentScan("udla.edu.programacion")
public class ArcaneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArcaneApplication.class, args);
    }
}
