package bctc.cabinet.services;

import bctc.cabinet.models.Student;
import bctc.cabinet.repositories.StudentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentService extends MemberService<StudentsRepository, Student> {
    public StudentService(StudentsRepository repository) {
        super(repository);
    }

    public Student findOneEager(int id){
        Optional<Student> student = repository.findById(id);
        if (student.isEmpty())
            return null;

        student.get().getTeachers().size();
        return student.get();
    }
}
