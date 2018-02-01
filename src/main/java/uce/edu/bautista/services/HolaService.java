package uce.edu.bautista.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


/**
 * Created by Alexis on 30/01/2018.
 */
public class HolaService {
    private final Logger log = LoggerFactory.getLogger(HolaService.class);

//    @GET
//    @Path("/hola/{nombre}")
//    @Produces({"application/xml","application/json"})
//    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
//    public String getBucket(@PathParam("nombre") String nombre) {
//        log.debug("name : " + nombre);
//        return  "Hola "+nombre;
//    }

    @GET
    @Path("/hola/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();

    }
}
