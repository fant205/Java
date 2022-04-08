package com.geekbrains.server.history;

import java.util.List;

public interface HistoryService {
    public void start();
    public void addMessage(String msg);
    public String getAllMessages();
    public void end();
}
