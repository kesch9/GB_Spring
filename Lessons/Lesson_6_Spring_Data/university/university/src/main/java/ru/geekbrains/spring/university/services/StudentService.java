package ru.geekbrains.spring.university.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.university.model.Student;
import ru.geekbrains.spring.university.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<Student> getAllByScore(Integer first, Integer second) {

        return studentRepository.findStudentsByScoreBetween(first, second);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student getByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
