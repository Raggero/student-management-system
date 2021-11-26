package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @GET
    public Response getAllSubjects() {
        List<Subject> foundSubjects = subjectService.getAllSubjects();
        return Response.ok(foundSubjects).build();
    }

    @Path("{id}")
    @GET
    public Response getSubjectById(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.getSubjectById(id);
        return Response.ok(foundSubject).build();
    }

    @Path("search")
    @GET
    public Response getSubjectByName(@QueryParam("name") String name) {
        Subject foundSubject = subjectService.getSubjectByName(name);
        return Response.ok(foundSubject).build();
    }

    @POST
    public Response createSubject(Subject subject) {
        subjectService.createSubject(subject);
        return Response.status(Response.Status.CREATED).entity(subject).build();
    }

    @Path("{subjectid}/student/{studentid}")
    @PATCH
    public Response addStudentToSubject(@PathParam("subjectid") Long subjectjId, @PathParam("studentid") Long studentId) {
        Subject updatedSubject = subjectService.addStudentToSubject(subjectjId, studentId);
        return Response.ok(updatedSubject).build();
    }

    @Path("{subjectid}/teacher/{teacherid}")
    @PATCH
    public Response addTeacherToSubject(@PathParam("subjectid") Long subjectId, @PathParam("teacherid") Long teacherId) {
        Subject updatedSubject = subjectService.addTeacherToSubject(subjectId, teacherId);
        return Response.ok(updatedSubject).build();
    }
}
