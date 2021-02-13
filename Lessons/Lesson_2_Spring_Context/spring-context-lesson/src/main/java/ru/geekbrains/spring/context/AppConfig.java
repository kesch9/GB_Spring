package ru.geekbrains.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("ru.geekbrains.spring.context")
public class AppConfig {
//    @Autowired
//    private StudentService studentService;

//    @Bean
//    public Box box1() {
//        Box box = new Box();
//        box.setStudentService(studentService);
//        box.setSize(5);
//        box.setColor("White");
//        return box;
//    }
//
//    @Bean
//    public Box box2() {
//        Box box = new Box();
//        box.setStudentService(studentService);
//        box.setSize(5);
//        box.setColor("Red");
//        return box;
//    }

//    @Bean
//    public Connection connection() {
//        try {
//            Class.forName("org.sqlite.jdbc");
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:123.fb");
//            return connection;
//        } catch (Exception throwables) {
//            throw new RuntimeException("The End!!!");
//        }
//    }
}
