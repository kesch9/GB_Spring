package ru.geekbrains.spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);
        while(true) {
            System.out.println( "Commands:\n" +
                    "- create id title cost\n" +
                    "- read id\n" +
                    "- readAll\n" +
                    "- update id title cost\n" +
                    "- delete id\n" +
                    "- averagePrice\n" +
                    "- q");
            Scanner sc = new Scanner(System.in);
            String cmd = sc.nextLine();
            String[] splitCommands = cmd.split(" ");
            cmd = splitCommands[0];
            if (cmd.equals("create")) {
                productService.create(
                        Integer.parseInt(splitCommands[1]),
                        splitCommands[2],
                        Integer.parseInt(splitCommands[3]));
            }
            if (cmd.equals("read")) {
                System.out.println(productService.read(Integer.parseInt(splitCommands[1])));
            }
            if (cmd.equals("readAll")) {
                productService.readAll().forEach(System.out::println);
            }
            if (cmd.equals("update")) {
                productService.update(
                        Integer.parseInt(splitCommands[1]),
                        splitCommands[2],
                        Integer.parseInt(splitCommands[3]));
            }

            if (cmd.equals("delete")) {
                productService.delete(
                        Integer.parseInt(splitCommands[1]));
            }
            if (cmd.equals("averagePrice")) {
                System.out.println(productService.averagePrice());
            }
            if (cmd.equals("q")) break;
        }
        context.close();
    }
}
