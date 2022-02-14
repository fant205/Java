package ru.gb;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Сервер запущен");
            //Ожидаем клиента
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);

            Thread thread1 = new Thread(() -> {
                while (true) {
                    try {
                        while (true) {
                            String clientMessage = in.readUTF();
                            if (clientMessage.equals("/end")) {
                                break;
                            }
                            System.out.println("Клиент прислал: " + clientMessage);
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
                        String msg = sc.nextLine();
                        if (msg.equals("/end")) {
                            break;
                        }
                        out.writeUTF(msg);
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
