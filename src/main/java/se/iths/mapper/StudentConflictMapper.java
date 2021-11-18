package se.iths.mapper;

import se.iths.exception.StudentConflictException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentConflictMapper implements ExceptionMapper<StudentConflictException> {
    @Override
    public Response toResponse(StudentConflictException e) {
        JsonObject body = Json.createObjectBuilder().add("Message", e.getMessage()).build();
        return Response.status(Response.Status.CONFLICT).entity(body).build();
    }
}
