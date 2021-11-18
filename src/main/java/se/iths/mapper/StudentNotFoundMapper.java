package se.iths.mapper;

import se.iths.exception.StudentNotFoundException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentNotFoundMapper implements ExceptionMapper<StudentNotFoundException> {

    @Override
    public Response toResponse(StudentNotFoundException studentNotFoundException) {
        JsonObject body = Json.createObjectBuilder().add("Message", studentNotFoundException.getMessage()).build();
        return Response.status(Response.Status.NOT_FOUND).entity(body).build();
    }
}
