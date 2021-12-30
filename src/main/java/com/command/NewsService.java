package com.command;

@Component
public class NewsService { //test 3 cases

    @Autowired
    public  NewsDatabase db;

    @Command(".news")
    @View
    public String news(Model Model) { //@View 쓸때 Model 갖고있어야하는 느낌으로 코딩해놓음
        return "news";
    }

    @Command(".read")  // ex) .read id=2
    @Parameter
    public String read(Model model){ //@Parameter가 있으면  param값을 model에 넣어주게되어있음(더뜯어봐야할듯)
        int id = Integer.parseInt(model.get("id"));
        String[] news = {"11111", "222222", "333333"};
        return "news id" + id + ":" + news[id];
    }

    @Command(".print") // .print id=2 -> "333333"
    @Parameter
    @View
    public  String print(Model model){
        int id = Integer.parseInt(model.get("id"));
        String[] news = {"11111", "222222", "333333"};
        model.put("title", news[id]);
        return "print";
    }

    @Command(".info") //info id=1
    @Parameter
    public String info(Model model){
        int id = Integer.parseInt(model.get("id"));
        return "title:" +db.get(id); //db가 제대로 주입이 안됨 ㅇㅅㅇ;;
        //순서문제로.....  서비스가 먼저 생성되게하고 , 그다음에 컴포넌트
    }
}
