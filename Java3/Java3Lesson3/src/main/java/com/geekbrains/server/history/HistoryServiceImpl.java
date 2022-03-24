package com.geekbrains.server.history;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    private FileWriter fileWriter;
    private LinkedList<String> messages;

    @Override
    public void start() {

        File file = new File("Java3/Java3Lesson3/history.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            messages = new LinkedList<>();
            String s;
            while ((s = reader.readLine()) != null) {
                messages.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMessage(String msg) {
        messages.addLast(msg);
        if(messages.size() > 100){
            messages.removeFirst();
        }
    }

    @Override
    public String getAllMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("История: \n");
        for (String s : messages) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }

    @Override
    public void end() {
        try (BufferedWriter writer = new BufferedWriter(new
                FileWriter("Java3/Java3Lesson3/history.txt"))) {
            for (String msg : messages) {
                writer.write(msg + "\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

//some test
//    public static void main(String[] args) {
//        HistoryServiceImpl historyService = new HistoryServiceImpl();
//        historyService.start();
//        historyService.addMessage("Привет 4");
//        historyService.addMessage("Привет 5");
//        historyService.addMessage("Привет 6");
//        System.out.println(historyService.getAllMessages());
//        historyService.end();
//    }
}
