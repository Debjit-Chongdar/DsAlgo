package stream.collectors.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordToSentense {
    static String combineWords(List<String> words){
        return words.stream().collect(Collectors.joining(" "));
    }

    static String combineWords_1(List<String> words){
        return words.stream().collect(Collector.of(
                StringBuilder::new,
                (sb, s) -> sb.append(s+" "),
                (sb1, sb2) -> sb1.append(sb2),
                sb -> sb.toString()
        ));
    }

    static void main() {
        System.out.println(combineWords(Arrays.asList("Hi", "I", "am","Debjit", "Chongdar")));
        System.out.println(combineWords_1(Arrays.asList("Hi", "I", "am","Debjit", "Chongdar")));
    }
}
