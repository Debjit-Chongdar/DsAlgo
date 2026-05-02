package stream.collectors.lambda;

import stream.collectors.util.GroupBYCollectors;
import stream.collectors.util.Item;

import java.util.*;
import java.util.stream.Collector;

public class SimpleGroupBy {

    static Map<Integer, List<Item>> groupByItemId(List<Item> items){
        //return items.stream().collect(Collectors.groupingBy(Item::getId));
        return items.stream().collect(new GroupBYCollectors());
    }

    static Map<Integer, List<Item>> groupByItemId_1(List<Item> items){
        return items.stream().collect(Collector.of(
 /*Supplier */  HashMap::new,
 /*accumulator*/(map, item) -> map.computeIfAbsent(item.getType_id(), k-> new ArrayList<>()).add(item),
 /*combiner*/   (map1, map2) -> {
                    map2.forEach((k, v) -> map1.merge(k, v, (list1, list2) -> {
                        list1.addAll(list2);
                        return list1;
                    }));
                    return map1;
                }
        ));
    }

    static void main() {
        Item i1 = new Item(1, 10, "Apple");
        Item i2 = new Item(1, 20, "Banana");
        Item i3 = new Item(1, 15, "Orange");

        Item i4 = new Item(2, 5, "Milk");
        Item i5 = new Item(2, 25, "Bread");

        Item i6 = new Item(3, 30, "Rice");
        Item i7 = new Item(3, 10, "Wheat");
        Item i8 = new Item(3, 40, "Sugar");

        Item i9  = new Item(4, 50, "Salt");   // single entry
        Item i10 = new Item(5, 60, "Oil");    // single entry

        List<Item> items = Arrays.asList(
                i1, i2, i3,
                i4, i5,
                i6, i7, i8,
                i9, i10
        );
        Map<Integer, List<Item>> map = groupByItemId_1(items);
        map.entrySet().forEach(e -> {
            System.out.print(e.getKey() + " -> ");
            e.getValue().forEach(v -> System.out.print(v.getName() + "  :  "));
            System.out.println();
        });
    }
}
