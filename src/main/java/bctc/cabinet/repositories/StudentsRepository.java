package bctc.cabinet.repositories;

import bctc.cabinet.models.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends MemberRepository<Student> {
}
