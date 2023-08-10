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
public class StudentDto implements Comparable<StudentDto> {
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^([a-zA-Z]{1,30}) ([a-zA-Z]{1,30})$", message = "Weird name .-.")
    private String name;

    @NotNull(message = "Age cannot be empty")
    @Min(value = 10, message = "Age should be between 10 and 20")
    @Max(value = 20, message = "Age should be between 10 and 20")
    private Integer age;

    private List<Teacher> teachers;

    public StudentDto(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.teachers = student.getTeachers();
    }

    @Override
    public int compareTo(StudentDto o) {
        return this.name.compareTo(o.getName());
    }
}
