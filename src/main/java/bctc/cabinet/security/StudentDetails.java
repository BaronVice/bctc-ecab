package bctc.cabinet.security;

import bctc.cabinet.models.Student;

public class StudentDetails extends MemberDetails<Student> {
    public StudentDetails(Student member) {
        super(member);
    }
}
