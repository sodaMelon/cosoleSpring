package com.command;


@Component
public class ComponentMain {

    @Command("/")
    public String home() {
        return  " 음신 주문  ====== application\n" +
                "    \n" +
                "  메뉴  /menu  \n";
    }

    @Command("/menu")
    public String menu() {
        return  " 1. 음식 /food \n" +
                " 2. 음료 /drink \n" +
                " 3. 홈  /  \n";
    }

    @Command("/food")
    public String food() {
        return  "음식\n" +
                "1.밥  /rice\n" +
                "2.라면 /noodle\n";
    }
}
