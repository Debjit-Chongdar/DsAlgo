package stream.collectors.lambda;

import stream.collectors.util.Item;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GroupBy2ndTopEach {
    // Using existing method
    public static Map<Integer, String> get2ndTopInEachItemType(List<Item> items) {
        return items.stream().collect(Collectors.groupingBy(
                Item::getType_id,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        (List<Item> list) -> list.stream()
                                .sorted(Comparator.comparingInt((Item item) -> item.getCount()).reversed())
                                .map(item -> item.getName())
                                .skip(1)
                               // .limit(1).collect(Collectors.toList()) ## limit always return list
                                .findFirst()    //-> it always return optional type
                                .orElse(null)
                )
        ));
    }

    // creating & using custom Collector
    public static Map<Integer, String> get2ndTopInEachItemType_1(List<Item> items) {
        return items.stream().collect(Collector.of(
/*Supplier*/    HashMap::new,
/*Accumulator*/ (Map<Integer, List<Item>> map, Item item) -> {
                    map.computeIfAbsent(item.getType_id(),k-> new ArrayList<>()).add(item);

                    map.get(item.getType_id()).sort(Comparator.comparingInt(Item::getCount).reversed());
                    if(map.get(item.getType_id()).size() > 2)
                        map.get(item.getType_id()).remove(map.get(item.getType_id()).size()-1);
                },
/*Combiner*/    (map1, map2) -> {
                    map2.forEach((k, v) -> {
                        map1.merge(k,v,(l1, l2)-> {
                            l1.addAll(l2);
                            l1.sort(Comparator.comparingInt(Item::getCount).reversed());
                            return (l1.size()>2) ? l1.subList(0, 1) : l1;
                        });
                    });
                    return map1;
                },
/*Finisher*/    map -> {
                    Map<Integer, String> result = new HashMap<>();
                    map.forEach((k,v) -> {
                        result.put(k, v.size()>1 ? v.get(1).getName() : null);
                    });
                    return result;
                }
                /*map -> map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e-> {
                    return (e.getValue().size()>1)?e.getValue().get(1).getName():null; // it will not work, as we pass null, if we pass "NA" or "" it will work
                }))*/
        ));
    }

    static void main() {
        System.out.println(get2ndTopInEachItemType_1(Item.getSampleData()));
    }
}
