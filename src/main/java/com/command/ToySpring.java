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

class ToySpring {

    public  static HashMap<String, Object> services = new HashMap<String, Object>();

    public void process(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clz = Class.forName(url);
        Object obj = clz.newInstance();

        Field fields[] = clz.getDeclaredFields();
        for (Field field : fields) {
            Autowired auto = field.getAnnotation(Autowired.class);
            if ( auto != null)  {
                field.set(obj, services.get(field.getType().getName())  );//
            }
        }

        Method methods[] = clz.getDeclaredMethods();
        for (Method method : methods) {
            Command antCommand = method.getAnnotation(Command.class);
            View antView = method.getAnnotation(View.class);
            Parameter antParameter = method.getAnnotation(Parameter.class);
            if (antCommand != null) {
                String methodsName = method.getName();
                map.put(antCommand.value(), new Object[] { method, obj,
                        antView != null ? true : false,
                        antParameter != null ? true : false,
                });
                System.out.println("command : " + antCommand.value());
            }
        }
    }

    public ToySpring (String[] urls) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (String url : urls ) {
            Class clz = Class.forName(url);
            Component antCom = (Component) clz.getAnnotation(Component.class);
            Service antSvr = (Service) clz.getAnnotation(Service.class);
            if ( antCom != null ) process(url);
            if ( antSvr != null ) {
                System.out.println("서비스 생성 ==>: " + clz.getName());
                services.put(clz.getName(), clz.newInstance());
            }
        }
    }

    private HashMap<String, Object[]> map = new HashMap<String, Object[]>();
    private String getVar(String str) {
        String word = "";
        boolean flag = false;
        for(int i=0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ( c == '{') {
                flag = true;
            } else if ( c == '}' ) break;
            else if ( flag  ) {
                word += c;
            }
        }
        if (word != "" ) return  word;
        return null;
    }

    public void run() throws InvocationTargetException, IllegalAccessException, IOException {

        while ( true ) {
            Scanner sc= new Scanner(System.in);
            String query = sc.nextLine();
            String[] tokens = query.split(" ");
            String cmd = query;
            String param = null;

            if (tokens.length > 1 ) {
                cmd = tokens[0];
                param = tokens[1];
            }

            if ( map.get(cmd) != null ) {
                Method met = (Method)map.get(cmd)[0];

                Object obj = map.get(cmd)[1];
                boolean bView = (boolean) map.get(cmd)[2];
                boolean bParam = (boolean) map.get(cmd)[3];

                Model model = new Model();

                if ( bParam) {
                    String[] paris = param.split("&");
                    for (String pair : paris ) {
                        String[] nv = pair.split("=");
                        model.put(nv[0], nv[1]);
                    }
                }

                if ( bView )  {
                    String result = (String)met.invoke(obj, model) + ".txt";

                    StringBuilder html = new StringBuilder();
                    List<String> lines = Files.readAllLines(Paths.get(result), Charset.forName("utf-8"));
                    for(String  line : lines) {
                        String var = getVar(line);
                        //System.out.println(line + "/" + var);
                        if  ( var != null ) {
                            line = line.replace("{" + var +"}", model.get(var));
                        }
                        html.append(line).append("\n");
                    }
                    System.out.println(model);
                    System.out.println(html.toString());
                } else {
                    String result = bParam ? (String)met.invoke(obj, model) : (String)met.invoke(obj);
                    System.out.println(result);
                }
            } else {
                System.out.println("메뉴가 없습니다.");
            };
        }
    }
}
