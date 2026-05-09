package stream.collectors.lambda;

import stream.collectors.util.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Top3Element {
    static List<Item> getTop3Item(List<Item> items){
        return items.stream().sorted(Comparator.comparingInt(Item::getCount).reversed())
                .limit(3).collect(Collectors.toList());
    }

    static List<Item> getTop3Item_1(List<Item> items){
        return items.stream().collect(Collector.of(
                ArrayList::new,
                (List<Item> list, Item item) -> {
                    list.add(item);
                    if(list.size()>3){
                        list.sort(Comparator.comparingInt(Item::getCount).reversed());
                        list.remove(3);
                    }
                },
                (list1, list2) -> {
                    list1.addAll(list2);
                    Collections.sort(list1, Comparator.comparingInt(Item::getCount).reversed());
                    return list1.stream().limit(3).collect(Collectors.toList());
                }
        ));
    }

    static void main() {
        System.out.println(getTop3Item(Item.getSampleData()));
        System.out.println(getTop3Item_1(Item.getSampleData()));
    }
}
