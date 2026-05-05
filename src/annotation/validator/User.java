package annotation.validator;

public class User {

    @NotEmpty(message = "이름이 빔")
    private final String name;

    @Range(min = 1, max = 100, message = "나이는 1~100")
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
