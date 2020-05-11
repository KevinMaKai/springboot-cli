package com.kevinmakai.springproject.cli.reflection;

import com.kevinmakai.springproject.cli.EightQueen;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 请填写类的描述
 *
 * @author makai5
 * @date 2020-05-09 14:09
 */
public class ReflectionTest {

    private static Method[] getMethod(Object object){
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method:methods){
            System.out.println(method.getName() + " " + method.getDefaultValue());
        }
        return methods;
    }

    private static Field[] getField(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field :fields){
            field.setAccessible(true);
            System.out.println(field.getName() + " " + field.get(object));
        }
        return fields;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        EightQueen eightQueen = EightQueen.class.newInstance();
        getMethod(eightQueen);
        getField(eightQueen);
    }
}
