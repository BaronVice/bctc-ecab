package bctc.cabinet.repositories;

import bctc.cabinet.models.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends MemberRepository<Teacher> {
}
