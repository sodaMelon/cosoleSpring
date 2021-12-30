package com.command;


import lombok.ToString;

import java.util.HashMap;

@ToString

public class Model {
    private HashMap<String, String> map  = new HashMap<String, String>();
    
    public void put(String name, String value) {
        map.put(name, value);
    }
    public String get(String name) {
        return map.get(name);
    }
}


