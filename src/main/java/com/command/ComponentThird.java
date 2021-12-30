package com.command;

@Component
public class ComponentThird {

    @Autowired
    public Databse databse;

    @Command("/new_")
    public String new_() {
        return  "new~~~~~~~ : " + databse.getName();
    }
}
