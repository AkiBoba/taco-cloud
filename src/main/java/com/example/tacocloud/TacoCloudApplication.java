package com.example.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import static java.lang.System.*;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
        out.println("Go to http://localhost:8080/");
    }

}
