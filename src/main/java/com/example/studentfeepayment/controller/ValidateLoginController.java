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

@Path("login")
public class ValidateLoginController {

    @POST
    @Path("/validate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateLogin(Students student) throws URISyntaxException {
        Students response = GetInstances.getInstanceOfStudentOperationService().validateStudentLogin(student);
        if (response == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(student).build();
    }
}
