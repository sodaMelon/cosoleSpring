package com.command;


@Component
public class ComponentNew {


    @Command("/info")
    @Parameter
    public String info(Model model) {
        return  "^^";
    }
}
