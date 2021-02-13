package ru.geekbrains.spring.context;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class StudentDbRepository implements StudentRepository {
    @Override
    public List<Student> getStudents() {
        return new ArrayList<>();
    }
}
