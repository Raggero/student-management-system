package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exception.EmptyListException;
import se.iths.exception.StudentConflictException;
import se.iths.exception.StudentNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        verifyCompleteStudent(student);
        if (findStudent(student).isEmpty()) {
            entityManager.persist(student);
        } else {
            throw new StudentConflictException("Student already exists");
        }
    }

    private void verifyCompleteStudent(Student student) {
        if (checkIncompleteString(student.getFirstName()) || checkIncompleteString(student.getLastName()) || checkIncompleteString(student.getEmail())) {
            throw new StudentConflictException("Incomplete student information");
        }
    }

    private boolean checkIncompleteString(String name) {
        return name == null || name.isEmpty();
    }

    private List<Student> findStudent(Student student) {
        return entityManager.createQuery("SELECT s from Student s where s.firstName = :firstname and s.lastName = :lastname and s.email = :email", Student.class)
                .setParameter("firstname", student.getFirstName())
                .setParameter("lastname", student.getLastName())
                .setParameter("email", student.getEmail())
                .getResultList();
    }

    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
        if (students.isEmpty()) {
            throw new EmptyListException("No students in database yet");
        }
        return students;
    }

    public Student getStudentById(Long id) {
        Student foundStudent = findStudent(id);
        if (foundStudent == null) {
            throw new StudentNotFoundException("Student with ID " + id + " was not found");
        }
        return foundStudent;
    }

    public List<Student> getStudentsByLastName(String lastName) {
        List<Student> students = entityManager.createQuery("SELECT s from Student s WHERE s.lastName = :name ", Student.class)
                .setParameter("name", lastName)
                .getResultList();
        if (students.isEmpty()) {
            throw new EmptyListException("No students with lastname " + lastName + " was found in the database");
        }
        return students;
    }

    public Student updateStudentPhonenumber(Long id, Student student) {
        Student foundStudent = findStudent(id);
        if (foundStudent == null) {
            throw new StudentNotFoundException("Student could not be found and therefore not update phonenumber.");
        }
        foundStudent.setPhoneNumber(student.getPhoneNumber());
        return foundStudent;

    }

    public void updateStudent(Student student) {
        verifyCompleteStudent(student);
        if (findStudent(student.getId()) == null) {
            throw new StudentNotFoundException("Student could not be found and therefore not updated.");
        }
        entityManager.merge(student);
    }


    public void deleteStudent(Long id) {
        Student foundStudent = findStudent(id);
        if (foundStudent == null) {
            throw new StudentNotFoundException("Student with id " + id + " was not found and therefore not deleted");
        }
        entityManager.remove(foundStudent);
    }

    private Student findStudent(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Set<Subject> getStudentSubjects(Long id) {
        Student foundStudent = findStudent(id);
        return foundStudent.getSubjects();
    }
}
