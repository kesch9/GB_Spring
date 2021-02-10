package ru.geekbrains.first.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    // GET:
    // 1. Request params живут прямо в url (url имеет конечную длину)
    // 2. Кэшируются и сохраняются в истории браузера
    // 3. В GET не принято что-то зашивать в тело запроса
    // 4. REST (получение ресурса)

    // POST:
    // 1. Request params зашиваются в тело запроса
    // 2. Не сохраются в истории браузера
    // 3. Как правило предназначен для передачи данных
    // 4. REST (создание ресурса)

    // GET http://localhost:port/context-path/hello
    // GET http://localhost:8189/spring/hello
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, User!!!";
    }

    // GET http://localhost:8189/spring/sum?a=10&b=20
    @GetMapping("/sum")
    public Integer calculateSum(@RequestParam(defaultValue = "0") Integer a, @RequestParam(defaultValue = "0") Integer b) {
        return a + b;
    }

    // GET http://localhost:8189/spring/greetings?[name=John&surname=Johnson]
    @GetMapping("/greetings")
    public String greetings(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        if (name == null && surname == null) {
            return "Hello, Stranger!!!";
        }
        return "Hello, " + (name != null ? name : "Unknown") + " " + (surname != null ? surname : "Unknown");
    }
}
