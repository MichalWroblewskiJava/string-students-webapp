package pl.sda.javagdy2.spring.students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.javagdy2.spring.students.model.Grade;
import pl.sda.javagdy2.spring.students.model.Przedmiot;
import pl.sda.javagdy2.spring.students.service.GradeService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @GetMapping("/add")
    public String gradeAddForm(Model model,
                               @RequestParam("student_id") Long studentId) {
        model.addAttribute("newGrade", new Grade());
        model.addAttribute("studentId", studentId);
        model.addAttribute("przedmioty", Przedmiot.values());
        return "grade-form";
    }
    @PostMapping("/add")
    public String gradeSave(Grade grade, Long studentGraded) {
        gradeService.saveGrade(grade, studentGraded);
        // przekieruj na stronę szczegółów tego studenta
        return "redirect:/student/details?studentIdentifier=" + studentGraded;
    }

    @GetMapping("/edit")
    public String editGrade(Model model, @RequestParam(name = "gradeId") Long gradeId) {
        Optional<Grade> gradeOptional = gradeService.findById(gradeId);
        if (gradeOptional.isPresent()) {
            Grade grade = gradeOptional.get();
            model.addAttribute("newGrade", grade);
            model.addAttribute("studentId", grade.getStudent().getId());
            model.addAttribute("przedmioty", Przedmiot.values());

            return "grade-form";
        }
        // jeśli nie udało się znaleźć oceny, to przekieruj na listę studentów.
        return "redirect:/student/list";
    }
}