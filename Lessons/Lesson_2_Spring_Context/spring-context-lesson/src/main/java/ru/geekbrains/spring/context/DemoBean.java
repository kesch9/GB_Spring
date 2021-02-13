package ru.geekbrains.spring.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component // DemoBean -> demoBean, JDBCConnector -> JDBCConnector
@Scope
public class DemoBean {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @PostConstruct
    public void init() {
        title = "abcd1";
    }
}
