package ru.vsu.lab.inject;

import ru.vsu.lab.exception.InjectorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    public <T> T inject(T o) throws InjectorException {
        Class objectClass = o.getClass();
        Field[] objectFields = objectClass.getDeclaredFields();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/Configuration.properties"));
        } catch (IOException e) {
            throw new InjectorException(e.getMessage());
        }
        for (Field field : objectFields) {
            if (field.isAnnotationPresent(LabInject.class)) {
                try {
                    field.setAccessible(true);
                    String targetClassName = properties.get(field.getType().getName()).toString();
                    Class targetClass = Class.forName(targetClassName);
                    field.set(o, targetClass.newInstance());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    throw new InjectorException(e.getMessage());
                }
            }
        }
        return o;
    }
}


