package se.iths.mapper;

import se.iths.exception.EmptyListException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmptyListMapper implements ExceptionMapper<EmptyListException>{

    @Override
    public Response toResponse(EmptyListException e) {
        JsonObject body = Json.createObjectBuilder().add("Message", e.getMessage()).build();
        return Response.ok().entity(body).build();
    }
}
