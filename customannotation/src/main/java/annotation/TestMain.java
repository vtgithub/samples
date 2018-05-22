package annotation;

import annotation.proc.CustomAnnotationProcessor;
import annotation.proc.ProcessException;

public class TestMain {
    public static void main(String[] args) throws ProcessException {
        AnnotatedTest annotatedTest = new AnnotatedTest();
        CustomAnnotationProcessor customAnnotationProcessor = new CustomAnnotationProcessor();
        customAnnotationProcessor.process(annotatedTest);
    }
}
