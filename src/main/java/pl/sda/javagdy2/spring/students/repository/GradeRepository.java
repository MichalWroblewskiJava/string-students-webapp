package pl.sda.javagdy2.spring.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javagdy2.spring.students.model.Grade;
import pl.sda.javagdy2.spring.students.model.Student;

import java.util.List;
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByPrzedmiot(String szukaPrzedmiot);
}
