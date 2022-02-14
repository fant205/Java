package ru.gb;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class EchoClient {
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);) {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);

            Thread thread1 = new Thread(() -> {
                while (true) {
                    try {
                        while (true) {
                            String msg = sc.nextLine();
                            if (msg.equals("/end")) {
                                break;
                            }
                            out.writeUTF(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });
            thread1.start();

            Thread thread2 = new Thread(() -> {
                while (true) {
                    try {
                        String messageFromServer = in.readUTF();
                        if (messageFromServer.equals("/end")) {
                            break;
                        }
                        System.out.println("Сервер прислал: " + messageFromServer);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });
            thread2.start();

            thread1.join();
            thread2.join();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}