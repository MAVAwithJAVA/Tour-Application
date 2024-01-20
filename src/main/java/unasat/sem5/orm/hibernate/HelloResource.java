package unasat.sem5.orm.hibernate;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String hello(){
        System.out.println("MavaCore369");
        return "Hello, World";
    }
}
