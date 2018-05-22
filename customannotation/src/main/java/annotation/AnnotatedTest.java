package annotation;

import annotation.def.CustomFieldAnnotation;

public class AnnotatedTest {
    @CustomFieldAnnotation("field_one")
    private String filed1;
    @CustomFieldAnnotation("field_two")
    private Integer field2;


}
