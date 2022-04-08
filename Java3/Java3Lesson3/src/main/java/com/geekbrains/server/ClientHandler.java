package com.geekbrains.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {

    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);
    private final Server server;
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private FileWriter fileWriter;

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public ClientHandler(Server server, Socket socket, ExecutorService executorService) {
        try {
            this.server = server;
            this.socket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            initializeHistoryFile();

            executorService.execute(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                    closeHistoryFile();
                    closeConnection();
                }
            });


//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        authentication();
//                        readMessages();
//                    } catch (IOException exception) {
//                        exception.printStackTrace();
//                    } finally {
//                        closeHistoryFile();
//                        closeConnection();
//                    }
//                }
//            }).start();
        } catch (IOException exception) {
            throw new RuntimeException("Проблемы при создании обработчика");
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(ServerCommandConstants.AUTHORIZATION)) {
                String[] authInfo = message.split(" ");
                String nickName = server.getAuthService().getNickNameByLoginAndPassword(authInfo[1], authInfo[2]);
                if (nickName != null) {
                    if (!server.isNickNameBusy(nickName)) {
                        sendMessage("/authok " + nickName);
                        this.nickName = nickName;
                        server.broadcastMessage(nickName, " зашел в чат");
                        sendMessage(server.getHistoryService().getAllMessages());
                        server.addConnectedUser(this);
                        return;
                    } else {
                        sendMessage("Учетная запись уже используется");
                    }
                } else {
                    sendMessage("Неверные логин или пароль");
                }
            }
        }
    }

    private void initializeHistoryFile() throws IOException {
        File history = new File("Java3/Java3Lesson3/chatHistory.txt");
        if (!history.exists()) {
            history.createNewFile();
        }
        fileWriter = new FileWriter(history);
    }

    private void closeHistoryFile() {
        try {
            fileWriter.close();
            System.out.println("Файл закрыт!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String messageInChat = inputStream.readUTF();
            LOGGER.debug("от " + nickName + ": " + messageInChat);
            if (messageInChat.equals(ServerCommandConstants.SHUTDOWN)) {
                System.out.println("Команда отключения от " + nickName);
                server.getHistoryService().end();
                return;
            } else if (messageInChat.startsWith(ServerCommandConstants.CHANGE_NICKNAME)) {
                nickName = server.changeNickname(nickName, messageInChat);
            }

            server.broadcastMessage(nickName, messageInChat);
            server.getHistoryService().addMessage(nickName + ": " + messageInChat);

        }
    }

    public void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void closeConnection() {
        server.disconnectUser(this);
        server.broadcastMessage(nickName, " вышел из чата");
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
