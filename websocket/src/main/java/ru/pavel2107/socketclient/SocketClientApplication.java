package ru.pavel2107.socketclient;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SocketClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketClientApplication.class, args);
    }

}
