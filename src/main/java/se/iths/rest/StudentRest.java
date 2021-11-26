package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        Student foundStudent = studentService.getStudentById(id);
        return Response.ok(foundStudent).build();
    }

    @Path("/search")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastName) {
        List<Student> foundStudents = studentService.getStudentsByLastName(lastName);
        return Response.ok(foundStudents).build();
    }

    @PUT
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateStudentPhonenumber(@PathParam("id") Long id, Student student) {
        Student updatedStudent = studentService.updateStudentPhonenumber(id, student);
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @Path("{id}/getsubjects")
    @GET
    public Response getStudentSubjects(@PathParam("id") Long id) {
        Set<Subject> subjects = studentService.getStudentSubjects(id);
        return Response.ok(subjects).build();
    }
}
