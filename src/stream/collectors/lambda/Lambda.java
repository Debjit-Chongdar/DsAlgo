package stream.collectors.lambda;

import stream.collectors.util.Item;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda {
    static void listToMap(List<Item> list){
        //Simple list to Map
        Map<Integer, Item> map = list.stream()
                .collect(Collectors.toMap(
                        item -> item.getType_id(),
                        item -> item,
                        (existing, replacement) -> existing, //can have own merge func (Optional)
                        LinkedHashMap::new  // custom map supplier (Optional)
                ));
        //using grouping by
        Map<Integer, List<Item>> groupBy = list.stream().collect(Collectors.groupingBy(Item::getType_id));
        //using partition by :: don't use partition by if you want more than 2 group
        // key is always true or false
        Map<Boolean, List<Item>> partitionBy = list.stream().collect(Collectors.partitioningBy(Item ->Item.getCount() > 100));
    }
}