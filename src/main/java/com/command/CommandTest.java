package com.command;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CommandTest {

    public static void main(String[] args) throws  Exception {

        String[] urls = {"com.command.Databse", "com.command.ComponentMain", "com.command.ComponentSecond",
                "com.command.CommandTest", "com.command.ComponentThird",
                "com.command.NewsDatabase", //  얘가 젤 늦게 생성되면 주입이 안됨!!
                "com.command.MyService", "com.command.NewsService" // ** 한번 여기에도 넣어보세요 ㅇㅅㅇ
        };
        new ToySpring(urls) .run();
    }
}