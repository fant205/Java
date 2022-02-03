package ru.gb.phonebook;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    private List<Account> list;

    public boolean add(String surname, String phone) {
        if(list == null){
            list = new ArrayList<>();
        }
        return list.add(new Account(surname, phone));
    }

    public List<String> get(String surname) {
        List<String> result = new ArrayList<>();
        for (Account a : list) {
            if(a.getSurname().equals(surname)){
                result.add(a.getPhone());
            }
        }
        return result;
    }



}