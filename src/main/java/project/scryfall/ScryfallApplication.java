package project.scryfall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
public class ScryfallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScryfallApplication.class, args);
	}

        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }
        
        
}
