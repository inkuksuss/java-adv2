package reflection;

import reflection.data.BasicData;

public class BasicV1 {

    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;
        System.out.println("basicDataClass = " + basicDataClass);
    }
}
