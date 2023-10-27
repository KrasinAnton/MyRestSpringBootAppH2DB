package ru.example.MyRestSpringBootAppH2DB.dao;

import ru.example.MyRestSpringBootAppH2DB.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {
    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            Query query = entityManager.createQuery("from Student");
            List<Student> allStudents = query.getResultList();
            log.info("getAllStudents: Received " + allStudents.size() + " by students");
            return allStudents;
        } catch (Exception e) {
            log.error("Error when getting the list of students: " + e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public Student saveStudent(Student student) {
        try {
            Student savedStudent = entityManager.merge(student);
            log.info("Saved student with ID " + savedStudent.getId());
            return savedStudent;
        } catch (Exception e) {
            log.error("Error when saving a student: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Student getStudent(int id) {
        try {
            Student student = entityManager.find(Student.class, id);
            if (student != null) {
                log.info("Received a student with ID: " + id);
            } else {
                log.info("Student with ID " + id + " not found");
            }
            return student;
        } catch (Exception e) {
            log.error("Error when receiving a student: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteStudent(int id) {
        try {
            Query query = entityManager.createQuery("delete from Student where id = :studentId");
            query.setParameter("studentId", id);
            int deletedCount = query.executeUpdate();
            if (deletedCount > 0) {
                log.info("Deleted student with ID " + id);
            } else {
                log.info("Student with ID " + id + " not found and not deleted");
            }
        } catch (Exception e) {
            log.error("Error when deleting a student: " + e.getMessage(), e);
            throw e;
        }
    }
}
