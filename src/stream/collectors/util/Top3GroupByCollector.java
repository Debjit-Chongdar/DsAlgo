package stream.collectors.util;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Top3GroupByCollector implements Collector<Item, Map<Integer, List<Item>>, Map<Integer, List<Item>>> {

    @Override
    public Supplier<Map<Integer, List<Item>>> supplier() {
        return HashMap::new;    // create new map
    }

    @Override
    public BiConsumer<Map<Integer, List<Item>>, Item> accumulator() {
        return (map, item) -> {
            map.computeIfAbsent(item.type_id, k -> new ArrayList<>()).add(item);    // add item to map
        };
    }

    @Override
    public BinaryOperator<Map<Integer, List<Item>>> combiner() {
        return (map1, map2) -> {    // merge map2 into map1
            map2.forEach((k, v) -> {
                map1.merge(k, v, (list1, list2) -> {    // merge list2 into list1
                    list1.addAll(list2);
                    return list1;
                });
            });
            return map1;
        };
    }

    @Override
    public Function<Map<Integer, List<Item>>, Map<Integer, List<Item>>> finisher() {
        return map -> map.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
                .limit(3)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
