package com.geekbrains.server.authorization;

public interface AuthService {
    void start();

    String getNickNameByLoginAndPassword(String login, String password);

    public void changeNickname(String oldNickName, String newNickname);

    void end();
}
