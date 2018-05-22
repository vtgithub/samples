package annotation.proc;


import annotation.def.CustomClassAnnotation;
import annotation.def.CustomFieldAnnotation;
import annotation.def.CustomMethodAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class CustomAnnotationProcessor {
    public String processFields(Object object) throws ProcessException {
        try {
            Class<?> objectClass = requireNonNull(object).getClass();
            Map<String, String> jsonElements = new HashMap<String, String>();
            for (Field field: objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(CustomFieldAnnotation.class)) {
                    jsonElements.put(getProcessedKey(field), (String) field.get(object));
                }
            }
            System.out.println(toJsonString(jsonElements));
            return toJsonString(jsonElements);
        }
        catch (IllegalAccessException e) {
            throw new ProcessException(e.getMessage());
        }
    }

    private String toJsonString(Map<String, String> jsonMap) {
        String elementsString = jsonMap.entrySet()
                .stream()
                .map(entry -> "\""  + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(joining(","));
        return "{" + elementsString + "}";
    }
    private static String getProcessedKey(Field field) {
        String annotationValue = field.getAnnotation(CustomFieldAnnotation.class).value();
        if (annotationValue.isEmpty()) {
            return field.getName();
        }
        else {
            return annotationValue;
        }
    }

    //----------------  class
    public String processClass(Object object){
        StringBuilder sb = new StringBuilder();
        Class<?> objClass = requireNonNull(object).getClass();
        if(objClass.isAnnotationPresent(CustomClassAnnotation.class)){
            CustomClassAnnotation annotation = objClass.getAnnotation(CustomClassAnnotation.class);
            sb.append("createdBy:");
            sb.append(annotation.createdBy());
            sb.append(", priority:");
            sb.append(annotation.priority());
            sb.append(", tags:");
            sb.append(annotation.tags());
        }
        return sb.toString();
    }

    //----------------- method
    public void getEnabledMethods(Object object) throws ProcessException {
//        StringBuilder sb = new StringBuilder();
        try{
            Class<?> objClass = requireNonNull(object).getClass();
            for (Method method : objClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(CustomMethodAnnotation.class)){
                    CustomMethodAnnotation annotation = method.getAnnotation(CustomMethodAnnotation.class);
                    if (annotation.enabled())
                        method.invoke(objClass.newInstance());
                }
            }
        }catch (Exception e){
            throw new ProcessException(e.getMessage());
        }

    }

}