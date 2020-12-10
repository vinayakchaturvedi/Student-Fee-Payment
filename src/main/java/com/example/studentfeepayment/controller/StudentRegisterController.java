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
    public Response registerStudent(Students student) throws URISyntaxException {
        System.out.println("Response from service and DAO for registering a new student: " +
                GetInstances.getInstanceOfStudentOperationService().registerStudent(student).toString());

        return Response.ok().build();
    }

}
