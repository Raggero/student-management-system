package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }

    public Subject getSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public Subject getSubjectByName(String name) {
        String query = "SELECT s from Subject s WHERE s.name = :name";
        TypedQuery<Subject> typedQuery = entityManager.createQuery(query, Subject.class)
                .setParameter("name", name.toLowerCase());
        return typedQuery.getSingleResult();
    }

    public Subject addStudentToSubject(Long subjectId, Long studentId) {
        Subject foundSubject = entityManager.find(Subject.class, subjectId);
        Student foundStudent = entityManager.find(Student.class, studentId);
        foundSubject.addStudent(foundStudent);
        entityManager.merge(foundSubject);
        return foundSubject;
    }

    public Subject addTeacherToSubject(Long id, Long teacher) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        Teacher foundTeacher = entityManager.find(Teacher.class, teacher);
        foundSubject.addTeacher(foundTeacher);
        entityManager.merge(foundSubject);
        return foundSubject;
    }
}
