package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {

        Student studentAnna = new Student("Anna", "Gran", "annagran@school.com");
        Student studentGunnar = new Student("Gunnar", "Blom", "gunnarblom@school.com");
        Student studentSofia = new Student("Sofia", "Andersson", "sofiaandresson@school.com");
        Student studentNiklas = new Student("Niklas", "Larsson", "niklaslarsson@school.com");
        Student studentBodil = new Student("Bodil", "Malm", "bodilmalm@school.com");
        Student studentKarl = new Student("Karl", "Ås", "karlas@school.com");

        Teacher teacherSvensson = new Teacher("Mrs", "Svensson", "svensson@school.com");
        Teacher teacherLind = new Teacher("Mr", "Lind", "lind@school.com");
        Teacher teacherJansson = new Teacher("Ms", "Jansson", "jansson@school.com");

        Subject english = new Subject("english");
        Subject math = new Subject("math");
        Subject biology = new Subject("biology");
        Subject swedish = new Subject("swedish");

        studentAnna.addSubject(english);
        studentAnna.addSubject(swedish);
        studentAnna.addSubject(math);

        studentGunnar.addSubject(biology);

        studentSofia.addSubject(english);
        studentSofia.addSubject(biology);

        studentNiklas.addSubject(math);

        studentBodil.addSubject(english);
        studentBodil.addSubject(math);
        studentBodil.addSubject(biology);

        studentKarl.addSubject(swedish);
        studentKarl.addSubject(math);

        teacherSvensson.addSubject(english);
        teacherSvensson.addSubject(swedish);

        teacherLind.addSubject(math);

        teacherJansson.addSubject(biology);

        entityManager.persist(studentAnna);
        entityManager.persist(studentGunnar);
        entityManager.persist(studentSofia);
        entityManager.persist(studentNiklas);
        entityManager.persist(studentBodil);
        entityManager.persist(studentKarl);
        entityManager.persist(teacherSvensson);
        entityManager.persist(teacherLind);
        entityManager.persist(teacherJansson);
    }
}
