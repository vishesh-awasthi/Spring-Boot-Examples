package com.vishesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootJibApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJibApplication.class, args);
    }

    @GetMapping()
    public ResponseEntity<String> greetings() {
        return new ResponseEntity<>("Spring boot with jib", HttpStatus.OK);
    }

}
