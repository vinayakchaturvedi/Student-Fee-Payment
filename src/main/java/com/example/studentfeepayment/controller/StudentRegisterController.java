package com.example.studentfeepayment.controller;

import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.GetInstances;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("register")
public class StudentRegisterController {

    @POST
    @Path("/student")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateLogin(Students student) throws URISyntaxException {
        System.out.println(student.getFirstName() + " In Controller");
        System.out.println(GetInstances.getInstanceOfRegisterStudent().registerStudent(student));

        return Response.ok().build();
    }

}
