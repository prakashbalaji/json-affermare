package com.rest.resource;

import com.rest.model.ApplicationError;
import com.rest.model.Author;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.rest.dao.MockDAO.*;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.Response.Status.PRECONDITION_FAILED;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    public AuthorResource() {
    }

    @GET
    @Path("/all.json")
    public List<Author> allAuthors() {
        return asList(FOWLER, BECK);
    }

    @GET
    @Path("/names.json")
    public List<String> allAuthorNames() {
        return asList(FOWLER.getName(), BECK.getName());
    }

    @GET
    @Path("/ids.json")
    public List<Integer> allAuthorIds() {
        return asList(FOWLER.getId(), BECK.getId());
    }

    @GET
    @Path("/empty.json")
    public List<Author> emptySet() {
        return EMPTY_LIST;
    }

    @GET
    @Path("/{authorId}.json")
    public Author authorById(@PathParam("authorId") Integer authorId) {
        return FOWLER;
    }

    @GET
    @Path("/error.json")
    public Response errorWithDetails() {
        return Response.status(PRECONDITION_FAILED).type(APPLICATION_JSON_TYPE)
                .entity(new GenericEntity<List<ApplicationError>>(asList(MISSING_NAME, MISSING_PHONE)) {
                }).build();
    }


}