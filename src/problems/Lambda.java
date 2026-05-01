package problems;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Lambda {
    static void listToMap(List<Order> list){
        //Simple list to Map
        Map<Integer, Order> map = list.stream()
                .collect(Collectors.toMap(
                        item -> item.getCountryCode(),
                        item -> item,
                        (existing, replacement) -> existing, //can have own merge func (Optional)
                        LinkedHashMap::new  // custom map supplier (Optional)
                ));
        //using grouping by
        Map<Integer, List<Order>> groupBy = list.stream().collect(Collectors.groupingBy(Order::getCountryCode));
        //using partition by :: don't use partition by if you want more than 2 group
        // key is always true or false
        Map<Boolean, List<Order>> partitionBy = list.stream().collect(Collectors.partitioningBy(order ->order.getCountryCode() > 100));
    }

    // swap key value of a map
    static Map<Integer, Set<String>> swapKeyValue(Map<String, Integer> sourceMap){
        return sourceMap.entrySet().stream().collect(
                Collectors.groupingBy(entry -> entry.getValue(),
                        Collectors.mapping(entry ->  entry.getKey(), Collectors.toSet())
                ));
    }

    // find top 3 most ordered items for each country
    static Map<Integer, List<Order>> countryWiseTop3Orders(List<List<Order>> countryOrders){
        return countryOrders.stream()
                .flatMap(orderList -> orderList.stream())
                .collect(Collectors.groupingBy(
                        order -> order.getCountryCode(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt((Order o) -> o.getOrder_count()).reversed())
                                        .limit(3)
                                        .collect(Collectors.toList())
                        )
                ));
    }
    //get me each country next top 3 most selling product except top 2
    List<List<Order>> top3SkipTop2(List<List<Order>> list){
        return list.stream().map(orders -> orders.stream()
                .sorted(Comparator.comparingInt(Order::getOrder_count).reversed())
                .skip(2)
                .limit(3)
                .collect(Collectors.toList())
        ).collect(Collectors.toList());
    }

    public static void main() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 1);
        System.out.println(swapKeyValue(map));
        Order o1 = new Order(1, "KA", 200);
        Order o2 = new Order(1, "KA", 150);
        Order o3 = new Order(1, "KA", 300);
        Order o4 = new Order(1, "KA", 250);
        Order o5 = new Order(1, "KA", 100);

        Order o6 = new Order(2, "TN", 400);
        Order o7 = new Order(2, "TN", 50);
        Order o8 = new Order(2, "TN", 350);
        Order o9 = new Order(2, "TN", 200);

        Order o10 = new Order(3, "AP", 120);
        Order o11 = new Order(3, "AP", 220);
        Order o12 = new Order(3, "AP", 180);

        List<Order> list1 = Arrays.asList(o1, o2, o3, o4, o5);   // KA
        List<Order> list2 = Arrays.asList(o6, o7, o8, o9);       // TN
        List<Order> list3 = Arrays.asList(o10, o11, o12);        // AP

        List<List<Order>> countryOrders = Arrays.asList(list1, list2, list3);
        Map<Integer, List<Order>> map2 = countryWiseTop3Orders(countryOrders);
        map2.entrySet().forEach(data -> System.out.println(data.getKey()+"  "+data.getValue().stream().collect(Collectors.summingInt(Order::getOrder_count))));
    }
}

class MyCollector implements Collector<Order, List<Order>, List<Order>>{

    @Override
    public Supplier<List<Order>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Order>, Order> accumulator() {
        return (list, order) -> {
            list.add(order);
            list.sort(Comparator.comparingInt(Order::getOrder_count).reversed());
            if (list.size() > 3) list.remove(list.size() - 1);
        };
    }

    @Override
    public BinaryOperator<List<Order>> combiner() {
        return (l1, l2) -> {
            l1.addAll(l2);
            l1.sort(Comparator.comparingInt(Order::getOrder_count));
            return l1.size()>3 ? l1.subList(0,2) : l1;
        };
    }

    @Override
    public Function<List<Order>, List<Order>> finisher() {
        return list -> list; // -> Identical
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}

class Order{
    int countryCode;
    String state;
    int order_count;
    public Order(int countryCode, String state, int order_count){
        this.countryCode = countryCode;
        this.state = state;
        this.order_count = order_count;
    }
    public int getOrder_count(){
        return this.order_count;
    }

    public int getCountryCode() {
        return countryCode;
    }
}