package ir.ord.application.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by vahid on 6/28/17.
 */
@Path("/rest/log")
public class LoggService {

    // app, button, com, management

    @GET
//    @Produces("text/html")
//    @Consumes("*/*")
    @Path("/{page}")
    public InputStream getLogPage(@PathParam("page") String pageName) throws FileNotFoundException {
        System.out.println("_______________HTML______________");
        File pageFile ;
        if (pageName.equals("notif"))
            pageFile = new File("/var/log/notification-center/"+pageName+".html");
        else
            pageFile = new File("/var/log/application-server/" + pageName + ".html");
        return new FileInputStream(pageFile);
//        return Response.status(200).entity("DDDDDDDDDDDDDDDDDDDDDDD").build();
    }
}
