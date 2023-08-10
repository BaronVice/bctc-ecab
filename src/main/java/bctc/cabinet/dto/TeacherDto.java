package bctc.cabinet.dto;

import bctc.cabinet.models.Student;
import bctc.cabinet.models.Teacher;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^([a-zA-Z]{1,30}) ([a-zA-Z]{1,30})$", message = "Weird name .-.")
    private String name;

    @NotNull(message = "Age cannot be empty")
    @Min(value = 18, message = "Age should be between 18 and 70")
    @Max(value = 70, message = "Age should be between 18 and 70")
    private Integer age;

    private List<Student> students;

    public TeacherDto(Teacher teacher){
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.age = teacher.getAge();
        this.students = teacher.getStudents();
    }
}
