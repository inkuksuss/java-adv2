package annotation.basic;

public class ElementDataMain {

    public static void main(String[] args) {
        Class<ElementData1> annoClas = ElementData1.class;
        AnnoElement annotation = annoClas.getAnnotation(AnnoElement.class);

        String value = annotation.value();
        System.out.println("value = " + value);

        int count = annotation.count();
        System.out.println("count = " + count);

        String[] tags = annotation.tags();
        System.out.println("tags = " + tags);
    }
}
