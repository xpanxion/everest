package com.xpanxion.everest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class
})
public class Application {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Creates a restTemplate for the weather service and places it in the spring context.
     * @return the rest template for the weather service.
     */
    @Bean
    public RestTemplate weatherTemplate(){
        return new RestTemplate();
    }
}
