package com.command;

import java.util.HashMap;

@Service
public class NewsDatabase { //이게 젤 마지막에 만든거임
public HashMap<Integer, String> db =
        new HashMap<Integer, String>();

public NewsDatabase(){
    db.put(1, "오늘의 주식시장은 조하요!!!!!!!");
    db.put(2, "날씨가조아용!!!");
    db.put(3, "빨리 끝냈으면 합니다!!!!");
}

public String get(int id){
    return db.get(id);
}
}
