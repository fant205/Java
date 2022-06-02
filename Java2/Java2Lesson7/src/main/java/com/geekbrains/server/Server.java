package com.geekbrains.server;

import com.geekbrains.CommonConstants;
import com.geekbrains.server.authorization.AuthService;
import com.geekbrains.server.authorization.InMemoryAuthServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final AuthService authService;

    private List<ClientHandler> connectedUsers;

    public Server() {
        authService = new InMemoryAuthServiceImpl();
        try (ServerSocket server = new ServerSocket(CommonConstants.SERVER_PORT)) {
            authService.start();
            connectedUsers = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException exception) {
            System.out.println("Ошибка в работе сервера");
            exception.printStackTrace();
        } finally {
            if (authService != null) {
                authService.end();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickNameBusy(String nickName) {
        for (ClientHandler handler : connectedUsers) {
            if (handler.getNickName().equals(nickName)) {
                return true;
            }
        }

        return false;
    }

    public synchronized void broadcastMessage(String fromNickName, String message) {

        //Логика отправка сообщения конкретному пользователю
        if (message.startsWith(ServerCommandConstants.PERSONAL_MESSAGE)) {

            message = message.replaceAll("/w", "").trim();
            String targetNickName = message.substring(0, message.indexOf(" ")).trim();
            String msg = message.substring(message.indexOf(" ")).trim();

            for (ClientHandler handler : connectedUsers) {
                if (handler.getNickName().equals(targetNickName)) {
                    handler.sendMessage(fromNickName + ": " + msg);
                    break;
                }
            }
            return;
        }

        for (ClientHandler handler : connectedUsers) {
            handler.sendMessage(fromNickName + ": " + message);
        }
    }

    public synchronized void addConnectedUser(ClientHandler handler) {
        connectedUsers.add(handler);
    }

    public synchronized void disconnectUser(ClientHandler handler) {
        connectedUsers.remove(handler);
    }

}
