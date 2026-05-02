package stream.collectors.lambda;

import stream.collectors.util.Item;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GroupByTop3 {
    static Map<Integer, List<Item>> groupByItemId_top3(List<Item> items){
        return items.stream().collect(Collectors.groupingBy(Item::getType_id))
                .entrySet().stream().sorted((e1,e2) -> e2.getValue().size() - e1.getValue().size())
                .limit(3)
                .collect(Collectors.toMap(e-> e.getKey(), e -> e.getValue()));
        //return items.stream().collect(new Top3GroupByCollector());
    }

    static Map<Integer, List<Item>> groupByItemId_top3_1(List<Item> items){
        return items.stream().collect(Collector.of(
                HashMap::new,
                (map, item) -> map.computeIfAbsent(item.getType_id(), k-> new ArrayList<>()).add(item),
                (map1, map2) -> {
                    map2.forEach(
                            (k, v) -> map1.merge(k, v, (l1, l2) -> {
                                l1.addAll(l2);
                                return l1;
                            })
                    );
                    return map1;
                },
                (HashMap<Integer, List<Item>> map) -> {
                    return map.entrySet().stream()
                        .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
                        .limit(3)
                        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
                }
        ));
    }

    static void main() {
        Map<Integer, List<Item>> map = groupByItemId_top3_1(Item.getSampleData());
        map.entrySet().forEach(e -> {
            System.out.print(e.getKey() + " -> ");
            e.getValue().forEach(v -> System.out.print(v.getName() + "  :  "));
            System.out.println();
        });
    }
}
