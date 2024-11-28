package ua.edu.chnu.comments_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CommentsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsApiApplication.class, args);
    }

}
