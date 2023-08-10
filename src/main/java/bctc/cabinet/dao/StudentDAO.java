package bctc.cabinet.dao;

import bctc.cabinet.models.Student;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentDAO {
    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Student> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Student", Student.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Student show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }

    @Transactional
    public void save(Student student){
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    @Transactional
    public void update(int id, Student updatedStudent){
        Session session = sessionFactory.getCurrentSession();
        Student studentToUpdate = session.get(Student.class, id);

        studentToUpdate.setName(updatedStudent.getName());
        studentToUpdate.setAge(updatedStudent.getAge());
    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.createQuery(String.format("delete Student where id=%d", id)).executeUpdate();
    }
}
