package ru.honorzor.manifestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ManifestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManifestServiceApplication.class, args);
    }

}
