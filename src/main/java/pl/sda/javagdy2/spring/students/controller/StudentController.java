package pl.sda.javagdy2.spring.students.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.javagdy2.spring.students.model.Student;
import pl.sda.javagdy2.spring.students.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(path = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/add")
    public String studentAdd(Model model) {
        // wysyłam nowy obiekt studenta który ma być "wypełniony"
        model.addAttribute("newStudent", new Student());
        // fakt że jest to kontroler, oraz że zwraca String powoduje, że szukany jest plik o nazwie "formularz.html"
        // w katalogu "templates" w "resources"
        return "student-form";
    }

    @PostMapping("/add")
    public String studentAdd(Student student) {
        // w parametrze znajduje się obiekt student który wysłany jest
        // nam z formularza (użyliśmy th:object)
        // todo: dodać do bazy
        studentService.add(student);
        // todo: przekieruj na listę studentów
        // przekierowanie to słowo "redirect:LINK_DO_PRZEKIEROWANIA"
        return "redirect:/student/list";
    }

    @GetMapping("/list")
    public String studentList(Model model)
//            ,@RequestParam(name = "imie") String imie
    {
        // Model model - jest klasą która służy do przesyłania obiektów do widoku
        // czyli służy dodawaniu atrybutów (wartości) które chcemy pokazać w html
        model.addAttribute("listOfStudents", studentService.getBazaDanych());
        return "student-list";
    }

    // spodziewamy się wartości / parametru od użytkownika na jeden z dwóch sposobów
    // path variable: http://localhost:8080/student/edit/5
    @GetMapping("/edit/{stud_id}")
    public String editStudent(Model model, @PathVariable(name = "stud_id") Long studentId) {
        return getStudentToEdit(model, studentId);
    }

    // request param: http://localhost:8080/student/edit?stud_id=5
    @GetMapping("/edit")
    public String editStudentParam(Model model, @RequestParam(name = "stud_id") Long studentId) {
        return getStudentToEdit(model, studentId);
    }

    private String getStudentToEdit(Model model, @RequestParam(name = "stud_id") Long studentId) {
        Optional<Student> optionalStudent = studentService.findStudent(/*identyfikator*/studentId);
        if (optionalStudent.isPresent()) { // udało się go znaleźć
//            wyświetl stronę edytowania studenta
            Student student = optionalStudent.get(); // wyciągam studenta z pudełka
            model.addAttribute("newStudent", student);
            // ma się załadować widok student-form.
            return "student-form";
        } else {
            return "redirect:/student/list"; // przekieruj na listę studentów
        }
    }

    @GetMapping("/delete/{studentIdentifier}")
    public String deleteStudent(@PathVariable Long studentIdentifier) {
        studentService.deleteById(studentIdentifier);

        return "redirect:/student/list";
    }

    // http://localhost:8080/student/details?studentIdentifier=5
    @GetMapping("/details")
    public String studentDetailsPage(Model model, @RequestParam Long studentIdentifier) {
        Optional<Student> optionalStudent = studentService.findStudent(studentIdentifier);
        if (optionalStudent.isPresent()) { // udało się go znaleźć
//            wyświetl stronę edytowania studenta
            Student student = optionalStudent.get(); // wyciągam studenta z pudełka
            model.addAttribute("studentDetails", student);
            return "student-details";
        }
        return "redirect:/student/list";
    }
}