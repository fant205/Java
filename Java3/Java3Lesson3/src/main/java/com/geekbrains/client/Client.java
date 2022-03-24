package com.geekbrains.client;

import com.geekbrains.CommonConstants;
import com.geekbrains.server.ServerCommandConstants;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Scanner scanner;
    private FileWriter fileWriter;
    private String nickName;

    public Client() {
        scanner = new Scanner(System.in);
        try {
            openConnection();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void openConnection() throws IOException {
        initializeNetwork();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String messageFromServer = inputStream.readUTF();
                        System.out.println(messageFromServer);
                        if (messageFromServer.startsWith("/authok")) {
                            nickName = messageFromServer.replaceAll("/authok", "").trim();
                            initializeHistoryFile(nickName);
                        }
                        fileWriter.write(messageFromServer + "\n");
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String text = scanner.nextLine();
                        if (text.equals(ServerCommandConstants.SHUTDOWN)) {
                            sendMessage(text);
                            closeHistoryFile();
                            closeConnection();
                        } else {
                            sendMessage(text);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initializeNetwork() throws IOException {
        socket = new Socket(CommonConstants.SERVER_ADDRESS, CommonConstants.SERVER_PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    private void initializeHistoryFile(String nickName) throws IOException {
        File history = new File(String.format("Java3/Java3Lesson3/history_%s.txt", nickName));
        if (!history.exists()) {
            history.createNewFile();
        }
        fileWriter = new FileWriter(history);
    }


    public void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        System.exit(1);
    }

    private void closeHistoryFile() {
        try {
            fileWriter.close();
            System.out.println("Файл закрыт!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
