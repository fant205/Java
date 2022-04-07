package com.geekbrains.server;

import com.geekbrains.CommonConstants;
import com.geekbrains.server.authorization.AuthService;
import com.geekbrains.server.authorization.DataBaseAuthServiceImpl;
import com.geekbrains.server.history.HistoryService;
import com.geekbrains.server.history.HistoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.geekbrains.server.ServerCommandConstants.*;

public class Server {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private final AuthService authService;

    private final HistoryService historyService;

    private List<ClientHandler> connectedUsers;

    private ExecutorService executorService;

    public Server() {
        authService = new DataBaseAuthServiceImpl();
        historyService = new HistoryServiceImpl();
        executorService = Executors.newCachedThreadPool();

        try (ServerSocket server = new ServerSocket(CommonConstants.SERVER_PORT)) {
            authService.start();
            historyService.start();
            connectedUsers = new ArrayList<>();
            while (true) {
                LOGGER.debug("Сервер ожидает подключения");
                Socket socket = server.accept();
                LOGGER.debug("Клиент подключился");
                new ClientHandler(this, socket, executorService);
            }
        } catch (IOException exception) {
            LOGGER.error("Ошибка в работе сервера");
            exception.printStackTrace();
        } finally {
            executorService.shutdown();

            if (authService != null) {
                authService.end();
            }
            if (historyService != null) {
                historyService.end();
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
        if (message.startsWith(PERSONAL_MESSAGE)) {

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

    public String changeNickname(String oldNickName, String messageInChat) {

        try {
            authService.start();
            String newNickname = messageInChat.replaceAll(CHANGE_NICKNAME, "").trim();
            authService.changeNickname(oldNickName, newNickname);

            for (ClientHandler handler : connectedUsers) {
                handler.sendMessage(String.format("Пользователь %s сменил nickname на %s", oldNickName, newNickname));
            }

            return newNickname;
        } finally {
            if (authService != null) {
                authService.end();
            }
        }
    }

    public HistoryService getHistoryService() {
        return historyService;
    }
}