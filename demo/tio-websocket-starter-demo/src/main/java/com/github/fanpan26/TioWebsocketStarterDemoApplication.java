package com.github.fanpan26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTioWebSocketServer
public class TioWebsocketStarterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TioWebsocketStarterDemoApplication.class, args);
	}

}
