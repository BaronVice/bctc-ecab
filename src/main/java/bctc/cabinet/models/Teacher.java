package bctc.cabinet.models;

import bctc.cabinet.dto.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends Member {
    @ManyToMany
    @JoinTable(
        name = "student_teacher",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public Teacher(TeacherDto teacherDto){
        this.id = teacherDto.getId();
        this.name = teacherDto.getName();
        this.age = teacherDto.getAge();
        this.students = teacherDto.getStudents();
    }
}
