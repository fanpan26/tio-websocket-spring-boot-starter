package com.github.fanpan26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fanyuepan
 */
@SpringBootApplication
@EnableTioWebSocketServer
public class TioWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(TioWebSocketApplication.class, args);
    }
}
