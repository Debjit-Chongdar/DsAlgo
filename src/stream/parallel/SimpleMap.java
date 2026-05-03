package stream.parallel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleMap {

    static void whenToUse(List<String> large_list){
        // don't use for small dataset  < 100,000
        large_list.parallelStream().map(k -> k).collect(Collectors.toList());

        List<String> auditLog = new ArrayList<>(); // shared mutable
        large_list.parallelStream().forEach(t -> auditLog.add(t.substring(5))); // it will fail
        // shared mutable state - use sequential or concurrent Linked Queue

        // if order is critical don't use parallel here
        large_list.parallelStream().sorted(Comparator.comparing(k -> k.substring(5))).collect(Collectors.toList()); // it will fail

        large_list.parallelStream().collect(Collectors.groupingBy(k -> k.substring(5))); //it will fail
        large_list.parallelStream()
                .collect(Collectors.groupingBy(
                        k -> k.substring(5),
                        LinkedHashMap::new,                     // -> This will preserve order
                        Collectors.toList()
                ));
    }

    static void test_1(List<String> list){
        list.parallelStream().forEach(s-> operation(s)); // does not preserve insertion order
        list.parallelStream().forEachOrdered(s-> operation(s)); // -> maintain order
        List<String> responseList = list.parallelStream().map(k->k).collect(Collectors.toList());
        //->maintain order if source is an ordered collection like array list,
        // parallel collector uses pre order index slots to each thread
        // Hash set is a UnOrdered source
    }

    static void operation(String s){
        System.out.println(s.concat("_"));
    }
}
