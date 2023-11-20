package dev.brickverse.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SocketApplication {

	public static void main(String[] args) {
		System.out.println("args = " + Arrays.toString(args));
		SocketCommand command = new SocketCommand();
		command.startServer();
		SpringApplication.run(SocketApplication.class, args);
	}

}
