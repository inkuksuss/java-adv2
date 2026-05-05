package reflection;

import java.lang.reflect.Constructor;

public class ConstructV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        System.out.println("====constructors()====");
        for (Constructor<?> constructor : aClass.getConstructors()) {
            System.out.println("constructor = " + constructor);
        }

        System.out.println("====declaredConstructors()====");
        for (Constructor<?> declaredConstructor : aClass.getDeclaredConstructors()) {
            System.out.println("declaredConstructor = " + declaredConstructor);;
        }


    }
}
