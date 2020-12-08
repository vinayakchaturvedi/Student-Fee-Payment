package com.example.studentfeepayment.controller;

import com.example.studentfeepayment.bean.Students;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("login")
public class ValidateLoginController {

    @POST
    @Path("/validate")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateLogin(Students students) throws URISyntaxException {
        System.out.println(students.getUserName());
        System.out.println(students.getPassword());
        return Response.ok().build();
    }
}
