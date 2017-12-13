
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: 2/26/13
 * Time: 9:42 AM
 */
public class ServerMain {
//    private static final Logger logger = LoggerFactory.getLogger(ServerMain.class);

    private static final int DEFAULT_PORT = 9595;
    private static final String DEFAULT_CONTEXTROOT = "/";

    public static void main(String[] args) throws Exception {
        ServerMain.startServer(Integer.parseInt(((args[0]==null || args[0].equals(""))?"0":args[0])));
    }

    public static void startServer(int port) throws Exception {

        final int portNum;
        if (port <= 0)
            portNum = DEFAULT_PORT;
        else
            portNum = port;

        Server server = new Server(portNum);
        ServletContextHandler contextHandler = new ServletContextHandler();

        contextHandler.setContextPath(DEFAULT_CONTEXTROOT);
        server.setHandler(contextHandler);
        contextHandler.addServlet(new ServletHolder(new FileServlet()), "/");

        server.start();
    }


}
