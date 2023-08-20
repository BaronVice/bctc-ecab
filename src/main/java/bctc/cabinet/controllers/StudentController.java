package bctc.cabinet.controllers;


import bctc.cabinet.dto.StudentDto;
import bctc.cabinet.models.Student;
import bctc.cabinet.services.StudentService;
import bctc.cabinet.util.ServiceError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bctc/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("students", studentService.findAll().stream().map(StudentDto::new).toList());
        return "bctc/student/index";
    }

    @GetMapping("/{id}")
    public String show(Model model,
                       @PathVariable("id") int id){
        model.addAttribute("student", new StudentDto(studentService.findOneEager(id)));
        return "bctc/student/show";
    }

    @GetMapping("/new")
    public String sendCreationPage(@ModelAttribute("student") StudentDto student){
        return "bctc/student/new";
    }

    @GetMapping("/{id}/edit")
    public String sendUpdatePage(Model model,
                                 @PathVariable("id") int id){
        model.addAttribute(
                "student",
                new StudentDto(studentService.findOne(id, ServiceError.USER_NOT_FOUND)));
        return "bctc/student/edit";
    }

    @PostMapping()
    public String save(@ModelAttribute("student") @Valid StudentDto student,
                       BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "bctc/student/new";

        studentService.save(new Student(student));
        return "redirect:/bctc/student";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("student") @Valid StudentDto student,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "bctc/student/edit";

        studentService.update(id, new Student(student));
        return String.format("redirect:/bctc/student/%s", id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        studentService.delete(id);
        return "redirect:/bctc/student";
    }
}
