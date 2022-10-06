package vl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WsApp {

    public static void main(String[] args) {
        SpringApplication.run(WsApp.class, args);
//        new SpringApplicationBuilder(WsApp.class)
//                .web(WebApplicationType.NONE)
//                .run(args);

    }

}
