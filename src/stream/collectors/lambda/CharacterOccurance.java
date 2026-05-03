package stream.collectors.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CharacterOccurance {
    static Map<Character, Long> charOccurance(String str){
        //chars() method
        //mapToObject method
        //No stream method
        // counting() or summingLong(k->1)
        return str.chars()
                .mapToObj(ch -> (char)ch)
                .collect(Collectors.groupingBy(
                        ch->ch,
                        Collectors.summingLong(k->1)
                        )
                );
    }

    static Map<Character, Long> charOccurance_1(String str){
        return Arrays.asList(str.split("")).stream()
                .collect(
                        Collectors.groupingBy(ch->ch.charAt(0), // String to char conversion
                        Collectors.counting()
                        )
                );
    }

    static Map<Character, Integer> charOccurance_2(String str){
        return Arrays.asList(str.split("")).stream().collect(
                Collector.of(
    /*Supplier*/        HashMap::new,
    /*Accumulator*/     (Map<Character, Integer> map, String s) -> {
                            map.putIfAbsent(s.charAt(0), 0);    // it's mandatory
                            map.put(s.charAt(0), map.get(s.charAt(0)) + 1);
                        },
    /*Combiner*/        (map1, map2) ->{
                            map2.forEach((k,v) ->
                                    map1.merge(k, v, (v1, v2) -> v1+v2 )
                            );
                            return map1;
                        }
                )
        );
    }

    static void main() {
        System.out.println(charOccurance("Eastern India"));
        System.out.println(charOccurance_1("Eastern India"));
        System.out.println(charOccurance_2("Eastern India"));
    }
}
