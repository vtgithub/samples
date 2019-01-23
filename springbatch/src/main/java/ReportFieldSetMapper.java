import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.net.BindException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReportFieldSetMapper implements FieldSetMapper<Report> {
  
    static Report reportObj;
    private SimpleDateFormat dateFormatObj = new SimpleDateFormat("dd/MM/yyyy");
  
    public Report mapFieldSet(FieldSet fieldSetObj){
        reportObj = new Report();
        reportObj.setId(fieldSetObj.readInt(0));
        reportObj.setSales(fieldSetObj.readBigDecimal(1));
        reportObj.setQty(fieldSetObj.readInt(2));
        reportObj.setStaffName(fieldSetObj.readString(3));
  
        String csvDate = fieldSetObj.readString(4);
        try { 
            reportObj.setDate(dateFormatObj.parse(csvDate));
        } catch (ParseException parseExceptionObj) {
            parseExceptionObj.printStackTrace();
        } 
        return reportObj;
    } 
} 