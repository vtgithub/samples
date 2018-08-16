
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by vahid on 4/17/17.
 */
public class DeploymentHelper {


    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();
        Process p;

        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();

    }

    public static void main(String[] args) {
        System.out.println(executeCommand("ls /home/vahid"));
    }

}
