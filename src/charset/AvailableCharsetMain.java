package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetMain {

    public static void main(String[] args) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String charsetName : charsets.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }

        System.out.println("=========");
        Charset ms949 = Charset.forName("MS949");
        System.out.println("ms949 = " + ms949);

        Set<String> aliases = ms949.aliases();
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }

        Charset utf8 = Charset.forName("UTF-8");
        System.out.println("utf8 = " + utf8);

        Charset utf8Con = StandardCharsets.UTF_8;
        System.out.println("utf8Con = " + utf8Con);

        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("defaultCharset = " + defaultCharset);
    }
}
