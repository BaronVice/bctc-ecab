package bctc.cabinet.controllers;

import bctc.cabinet.dto.StudentDto;
import bctc.cabinet.dto.TeacherDto;
import bctc.cabinet.models.Student;
import bctc.cabinet.models.Teacher;
import bctc.cabinet.services.StudentService;
import bctc.cabinet.services.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/bctc/teacher")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherController {
    private final TeacherService teacherService;
    private final StudentService studentService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("teachers", teacherService.findAll().stream().map(TeacherDto::new).toList());
        return "bctc/teacher/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("teacher", new TeacherDto(teacherService.findOneEager(id)));
        return "bctc/teacher/show";
    }

    @GetMapping("/new")
    public String sendCreationPage(@ModelAttribute("teacher") TeacherDto teacher,
                                   Model model){
        model.addAttribute("studentsMap", collectStudentsToHandle(Collections.emptyList()));
        return "bctc/teacher/new";
    }

    @GetMapping("/{id}/edit")
    public String sendUpdatePage(@PathVariable("id") int id,
                                 Model model){
        TeacherDto teacher = new TeacherDto(teacherService.findOneEager(id));
        Map<StudentDto, Boolean> studentsMap = collectStudentsToHandle(teacher.getStudents());

        model.addAttribute("teacher", teacher);
        model.addAttribute("studentsMap", studentsMap);

        return "bctc/teacher/edit";
    }

    // TODO: collect by criteria maybe (teachers count < 2)
    private Map<StudentDto, Boolean> collectStudentsToHandle(List<Student> teacherStudents){
        Map<StudentDto, Boolean> students = new TreeMap<>();
        for (StudentDto student : studentService.findAll().stream().map(StudentDto::new).toList())
            students.put(student, false);
        for (StudentDto student : teacherStudents.stream().map(StudentDto::new).toList())
            students.replace(student, true);

        return students;
    }

    @PostMapping()
    public String save(@ModelAttribute("teacher") @Valid TeacherDto teacher,
                       BindingResult bindingResult,
                       Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentsMap", collectStudentsToHandle(Collections.emptyList()));
            return "bctc/teacher/new";
        }

        teacherService.save(new Teacher(teacher));
        return "redirect:/bctc/teacher";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("teacher") @Valid TeacherDto teacher,
                         BindingResult bindingResult,
                         Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentsMap", collectStudentsToHandle(teacher.getStudents()));
            return "bctc/teacher/edit";
        }

        teacherService.update(id, new Teacher(teacher));
        return String.format("redirect:/bctc/teacher/%d", id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        teacherService.delete(id);
        return "redirect:/bctc/teacher";
    }
}
