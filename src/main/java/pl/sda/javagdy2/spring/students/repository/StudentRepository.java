package pl.sda.javagdy2.spring.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.javagdy2.spring.students.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByNazwisko(String szukaNazwiska);
}
