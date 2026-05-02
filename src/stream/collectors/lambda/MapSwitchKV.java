package stream.collectors.lambda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MapSwitchKV {

    static Map<String, Set<String>> swithKeyValue(Map<String, String> map){
        return map.entrySet().stream().collect(Collectors.groupingBy(e -> e.getValue(),
                Collectors.mapping(e -> e.getKey(), Collectors.toSet())));
    }

    //create and use Custom Collectors
    static Map<String, Set<String>> swithKeyValue_1(Map<String, String> map){
        return map.entrySet().stream().collect(Collector.of(
/*Supplier*/    HashMap::new,
/*Accumulator*/ (Map<String, Set<String>> map1, Map.Entry<String, String> entry) -> map1.computeIfAbsent(entry.getValue(), k-> new HashSet<>()).add(entry.getKey()),
/*Combiner*/    (map1, map2) -> {
                    map2.forEach((k, v) -> {
                        map1.merge(k, v, (v1, v2) -> {
                            v1.addAll(v2);
                            return v1;
                        });
                    });
                    return map1;
                }
        ));
    }

    static void main() {
        Map<String, String> map = Map.of(
                "Debjit", "Chongdar",
                "Asit", "Chongdar",
                "Lakshmi", "Chongdar",
                "Mita", "Bhui"
            );
        System.out.println(swithKeyValue_1(map));
    }//
}
