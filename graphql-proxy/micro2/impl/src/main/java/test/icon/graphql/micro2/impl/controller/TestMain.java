package test.icon.graphql.micro2.impl.controller;

import org.reflections.Reflections;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Set;

public class TestMain {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("test.icon.graphql.micro2.api");
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(RequestMapping.class);
        for (Class<?> controller : controllers) {
            for (Method method : controller.getMethods()) {
                System.out.println(method.getName());
            }
        }
    }
}
