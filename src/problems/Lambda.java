package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lambda {
    static Map<Integer, Set<String>> swapKeyValue(Map<String, Integer> sourceMap){
        return sourceMap.entrySet().stream().collect(
                Collectors.groupingBy(entry -> entry.getValue(),
                        Collectors.mapping(entry ->  entry.getKey(), Collectors.toSet())
                ));
    }
    static void main() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 1);
        System.out.println(swapKeyValue(map));
    }
}
