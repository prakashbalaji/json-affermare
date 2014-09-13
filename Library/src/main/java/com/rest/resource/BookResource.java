package com.rest.resource;

import com.rest.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rest.dao.MockDAO.REFACTORING;
import static com.rest.dao.MockDAO.TDD;
import static java.util.Arrays.asList;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {


    public BookResource() {
    }

    @GET
    @Path("/all.json")
    public List<Book> allBooks() {
        return asList(TDD, REFACTORING);
    }

    @GET
    @Path("/{bookId}.json")
    public Book bookById(@PathParam("bookId") Integer bookId) {
        return TDD;
    }

    @GET
    @Path("/empty.json")
    public Map<String, String> emptyBook() {
        return new HashMap<>();
    }
}