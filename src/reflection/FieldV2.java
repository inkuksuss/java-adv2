package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

public class FieldV2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User("id1", "userA", 20);
        System.out.println("user = " + user);

        Class<? extends User> aClass = user.getClass();
        Field name = aClass.getDeclaredField("name");

        name.setAccessible(true);
        name.set(user, "userB");
        System.out.println("change user = " + user);
    }
}
