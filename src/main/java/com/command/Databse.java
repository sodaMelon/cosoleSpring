package com.command;

@Service
public class Databse {
    private String name;

    public  Databse() {
        name = "안녕하세요";
    }

    public String getName() {
        return name;
    }
}
