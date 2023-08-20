package bctc.cabinet.services;

import bctc.cabinet.models.Teacher;
import bctc.cabinet.repositories.TeachersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TeacherService extends MemberService<TeachersRepository, Teacher> {
    public TeacherService(TeachersRepository repository) {
        super(repository);
    }

    @Transactional(readOnly = true)
    public Teacher findOneEager(int id){
        Optional<Teacher> teacher = repository.findById(id);
        if (teacher.isEmpty())
            return null;

        // That's should be a way of initialization like Hibernate.initialize()
        teacher.get().getStudents().size();
        return teacher.get();
    }
}
