package pl.sda.javagdy2.spring.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.javagdy2.spring.students.model.Grade;
import pl.sda.javagdy2.spring.students.model.Student;
import pl.sda.javagdy2.spring.students.repository.GradeRepository;
import pl.sda.javagdy2.spring.students.repository.StudentRepository;

import java.util.Optional;
@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    public void saveGrade(Grade grade, Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            grade.setStudent(student);
            gradeRepository.save(grade);
        }
    }
    public Optional<Grade> findById(Long gradeId){
        return gradeRepository.findById(gradeId);

    }
}
