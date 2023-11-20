package dev.brickverse.socket;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@ShellComponent
public class SocketCommand {

    @ShellMethod("Start server on port 2511")
    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(2511);
            System.out.println("Server started. Listening on port 2511...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                String message = getMessage(socket);
                System.out.println("Received message from client: " + message);

                socket.close();
                System.out.println("Client disconnected: " + socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMessage(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        StringBuilder messageBuilder = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            messageBuilder.append((char) character);
            if (messageBuilder.toString().endsWith("\n") || !reader.ready()) {
                break;
            }
        }

        return messageBuilder.toString().trim();
    }
}
