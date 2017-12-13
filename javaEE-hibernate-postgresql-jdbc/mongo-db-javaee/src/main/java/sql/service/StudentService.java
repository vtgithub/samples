package sql.service;

import sql.dal.dao.StudentDAO;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * Created by vahid on 3/24/17.
 */
@Path("/sql/student")
public class StudentService {

    @Inject
    private StudentDAO studentDAO;

    @GET
    @Path("/get-all")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAllStudents(){
        try{
            return Response.status(Response.Status.OK).entity(studentDAO.getAll()).build();
        }catch (Exception e){
            return Response.status(3001).build();
        }
    }

    @GET
    @Path("/test")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getTest(){
        return Response.status(Response.Status.OK).entity("success").build();
    }
}
