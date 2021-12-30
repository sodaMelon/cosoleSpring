package com.command;

import java.util.HashMap;

@Component
public class ComponentSecond {

    private HashMap<String, Integer> db = new HashMap<String, Integer>();

    public ComponentSecond() {
        db.put("milk", 100);
        db.put("water", 200);
        db.put("rice", 500);
        db.put("noodle", 700);
    }



    @Command("/drink")
    public String drink() {
        return  "음료\n" +
                "1. milk\n" +
                "2. water\n";
    }


    @Command("/view")
    @View
    public  String view(Model model) {
        model.put("name", "홍길동");
        model.put("age", "78");
        return "view";
    }

    @Command("/order")
    @Parameter
    @View
    public String order(Model model) {

        String code = model.get("code");
        int count = Integer.parseInt(model.get("count"));

        int total = db.get(code) * count;

        model.put("price", Integer.toString(total));
        return "order";
    }
}
