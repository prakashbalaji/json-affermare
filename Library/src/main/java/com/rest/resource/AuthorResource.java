package com.rest.resource;
import com.rest.model.Author;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.rest.dao.MockDAO.BECK;
import static com.rest.dao.MockDAO.FOWLER;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;

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
    @Path("/empty.json")
    public List<Author> emptySet() {
        return EMPTY_LIST;
    }

    @GET
    @Path("/{authorId}.json")
    public Author authorById(@PathParam("authorId") Integer authorId) {
        return FOWLER;
    }
}