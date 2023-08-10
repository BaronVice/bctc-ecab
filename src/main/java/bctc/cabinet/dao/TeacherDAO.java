package bctc.cabinet.dao;

import bctc.cabinet.models.Teacher;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherDAO {
    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Teacher> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Teacher", Teacher.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Teacher show(int id, boolean initialize){
        Session session = sessionFactory.getCurrentSession();
        Teacher teacher = session.get(Teacher.class, id);
        if (initialize)
            Hibernate.initialize(teacher.getStudents());

        return teacher;
    }

    @Transactional
    public void save(Teacher teacher){
        Session session = sessionFactory.getCurrentSession();
        session.save(teacher);
    }

    @Transactional
    public void update(int id, Teacher updatedTeacher){
        Session session = sessionFactory.getCurrentSession();
        Teacher teacherToUpdate = session.get(Teacher.class, id);

        teacherToUpdate.setName(updatedTeacher.getName());
        teacherToUpdate.setAge(updatedTeacher.getAge());
    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.createQuery(String.format("delete Teacher where id=%d", id)).executeUpdate();
    }
}
