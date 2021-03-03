package ru.geekbrains.spring.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.university.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentByName(String name);

    @Query("select s from Student s where s.id = :id")
    List<Student> customStudentsQuery(int id);
}
