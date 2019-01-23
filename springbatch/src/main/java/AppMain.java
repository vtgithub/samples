import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
  
    static JobLauncher jobLauncherObj;
    static ApplicationContext contextObj;
    private static String[] springConfig  = {"spring/batch/jobs/spring-beans.xml" };
  
    public static void main(String[] args) {        
        // Loading The Bean Definition From The Spring Configuration File 
        contextObj = new ClassPathXmlApplicationContext(springConfig);
        Job firstJob = (Job) contextObj.getBean("firstJob");
        Job secondJob = (Job) contextObj.getBean("secondJob");
        jobLauncherObj = (JobLauncher) contextObj.getBean("jobLauncher");       
        try {
            System.out.println("job 1 started ...");
            JobExecution firstJobStatus = jobLauncherObj.run(firstJob, new JobParameters());
            System.out.println("job 1 finished with status : " + firstJobStatus.getStatus());
            System.out.println("job 2 started ...");
            JobExecution secondJobStatus = jobLauncherObj.run(secondJob, new JobParameters());
            System.out.println("job 2 finished with status : " + secondJobStatus.getStatus());
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } 
        System.out.println("Done");
    } 
} 