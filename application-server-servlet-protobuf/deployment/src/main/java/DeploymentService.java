
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by vahid on 5/21/17.
 */
@Path("/deploy")
public class DeploymentService {
    @Path("/wars")
    @GET
    @Consumes("*/*")
    @Produces("application/json")
    public Response runDeployGetCommand(){
        new Thread() {
            @Override
            public void run() {
                String result = DeploymentHelper.executeCommand("/./home/vahid/deploy.sh");
            }
        }.start();
        return Response.status(Response.Status.OK).build();

    }

    @Path("/wars")
    @POST
    @Consumes("*/*")
    @Produces("application/json")
    public Response runDeployPostCommand(){
        new Thread() {
            @Override
            public void run() {
                DeploymentHelper.executeCommand("/./home/vahid/deploy.sh");
            }
        }.start();
        return Response.status(Response.Status.OK).build();
    }
}
