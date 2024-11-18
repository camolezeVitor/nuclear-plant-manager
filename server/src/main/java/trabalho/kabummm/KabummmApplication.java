package trabalho.kabummm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KabummmApplication {

    public static void main(String[] args) {
        SpringApplication.run(KabummmApplication.class, args);
    }

}
