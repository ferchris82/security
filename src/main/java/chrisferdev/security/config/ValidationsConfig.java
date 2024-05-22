package chrisferdev.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chrisferdev.security.services.impl.models.dtos.validation.UserValidation;

@Configuration
public class ValidationsConfig {

    @Bean
    public UserValidation uservalidation(){
        return new UserValidation();
    }
}
