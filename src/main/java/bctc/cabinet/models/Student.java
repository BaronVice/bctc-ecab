package bctc.cabinet.models;

import bctc.cabinet.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Member {
    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    public Student(StudentDto studentDto){
        this.id = studentDto.getId();
        this.name = studentDto.getName();
        this.age = studentDto.getAge();
        this.teachers = studentDto.getTeachers();
    }
}
