package com.techmaster.read_csv.config;

import com.techmaster.read_csv.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public PersonService personService() {
        return new PersonService("person.csv");
    }
}
