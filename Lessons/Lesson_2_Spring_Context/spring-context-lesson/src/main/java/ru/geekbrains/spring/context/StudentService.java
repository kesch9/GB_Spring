package ru.geekbrains.spring.context;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {
    private StudentRepository studentRepository;

    @Value("12234-42342-234234")
    private String identifier;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public int calculateAverageScore() {
        List<Student> students = studentRepository.getStudents();
        if (students.size() == 0) {
            return 0;
        }

        int avg = 0;
        for (Student s : students) {
            avg += s.getScore();
        }
        avg /= students.size();
        return avg;
    }
}
