package com.docsconsole.tutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RestTemplateClientApp {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateClientApp.class, args);
    }

}


