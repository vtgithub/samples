import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Report, Report> {
    public Report process(Report itemObj) {
        System.out.println("Processing Item?= " + itemObj);
        return itemObj;
    } 
} 