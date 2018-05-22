package annotation;

import annotation.def.CustomClassAnnotation;
import annotation.def.CustomFieldAnnotation;
import annotation.def.CustomMethodAnnotation;

@CustomClassAnnotation(
        createdBy = "vahid123",
        priority = CustomClassAnnotation.Priority.HIGH,
        tags = {"annotated", "test", "first_usage", "bahal"}
)
public class AnnotatedTest {
    @CustomFieldAnnotation("field_one")
    private String filed1;
    @CustomFieldAnnotation("field_two")
    private Integer field2;

    @CustomMethodAnnotation(enabled = false)
    public void procFieldsNo(){
        System.out.printf("procFieldsNo has been called");

    }
    @CustomMethodAnnotation
    public void procFieldsYes(){
        System.out.printf("procFieldsYes has been called");
    }


}
