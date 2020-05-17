package pl.sda.javagdy2.spring.students.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity

@AllArgsConstructor
@NoArgsConstructor


public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Przedmiot przedmiot;

    private double ocena;// nie moze przyjac null

    @CreationTimestamp
    private LocalDateTime dataDodania;

    @ManyToOne()
    private Student student;

    public Grade(Przedmiot przedmiot, double ocena) {
        this.przedmiot = przedmiot;
        this.ocena = ocena;
    }
}