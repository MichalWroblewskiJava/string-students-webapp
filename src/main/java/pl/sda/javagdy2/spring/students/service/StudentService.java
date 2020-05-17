package pl.sda.javagdy2.spring.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.javagdy2.spring.students.model.Student;
import pl.sda.javagdy2.spring.students.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // insert into...
    public void add(Student student){
        studentRepository.save(student);
    }

    public Optional<Student> findStudent(Long id) {
        return studentRepository.findById(id);
    }
    // select * from...
    public List<Student> getBazaDanych() {
        return studentRepository.findAll();
    }
    public void deleteById(Long studentIdentifier) {
        studentRepository.deleteById(studentIdentifier);
    }
}

