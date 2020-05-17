package pl.sda.javagdy2.spring.students.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//IDEnTITY
    private Long id;
    private String imie;
    private String nazwisko;
    private boolean pelnoletni;
    private double wzrost;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<Grade> gradeList;


}
