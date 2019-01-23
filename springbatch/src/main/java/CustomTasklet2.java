import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet2 implements Tasklet {

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step2 tasklet started ...");
//        throw new RuntimeException();
        return null;
    }
}
