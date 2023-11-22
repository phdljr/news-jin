package kr.ac.brother.newsjin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NewsJinApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsJinApplication.class, args);
    }

}
