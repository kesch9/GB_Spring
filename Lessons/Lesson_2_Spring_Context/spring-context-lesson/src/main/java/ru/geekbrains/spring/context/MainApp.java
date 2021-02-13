package ru.geekbrains.spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        DemoBean demoBean1 = context.getBean("demoBean", DemoBean.class);
//        System.out.println(demoBean1.getTitle());
//        demoBean1.setTitle("qwerty");
//
//        DemoBean demoBean2 = context.getBean("demoBean", DemoBean.class);
//        System.out.println(demoBean2.getTitle());
//
//        System.out.println(demoBean1 == demoBean2);

//        StudentRepository studentRepository = context.getBean("studentRepository", StudentRepository.class);
//        // studentRepository.getStudents().add(new Student(3L, "K", 1000));
//        studentRepository.add(new Student(3L, "Q", 490));
//        System.out.println(studentRepository.getStudents());

        StudentService studentService = context.getBean("studentService", StudentService.class);

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        if (cmd.equals("/avg_score")) {
            System.out.println(studentService.calculateAverageScore());
        }


        context.close();
    }
}
