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
        // get palindrome string
        List<String> palindrome = list.stream().map(Item::getName).filter(str -> {
            StringBuilder sb = new StringBuilder(str);
            String rev = sb.reverse().toString();
            return str.equals(rev);
        }).collect(Collectors.toList());
    }

//Simple Question to practice
// second highest salary from empl list -> Orderder().skip(1).findFirst().getSal().orElse(0.0);
//sum of all even numbers
//Partitioned numbers into odd and even -> .collect(Collectors.partitionBy(n-> n%2 == 0))
//group employees by dept
//highest salary per dept => Collectors.maxBy(Comparator.comparingDouble(Emp::sal))
//avarage sal per dept
// get names of top 3 highest paid employees
// count employees per dept -> .collect(Collectors.groupingBy(EMP::dept, Collectors.counting()));
// get distinct words from a list of sentences -> .flatMap(sent -> Arrays.stream(sent.split("")))
// filter a map where value > 50
// sort a map by value descending -> .sorted(Comparator.comparingInt(e -> e.getValue()).reversed())

// merge two maps, summing values for duplicate keys -> Stream.of(map1, map2).flatMap(m->m.entrySet().stream())

// find duplicate elements in a list => .distinct()
// -> return list.stream().collect(Collectors.groupingBy(k->k, Collectors.counting()))
//      .entrySet().stream().filter(e-> e.getValue()>1).map(e -> e.getKey()).collect(Collectors.toList());
// Simple Approach => .filter(e -> Collections.frequency(list, e) > 1).collect(Collectors.toList());

// Remove nulls and get distinct sorted list -> return list.stream().filter(Object::nonNull).sorted()

//sum salaries only of employees aged > 40 in IT dept
// -> return emps.stream().filter(emp -> emp.dept.equals("IT") && emp.age > 40).mapToDouble(EMP::getSal).sum();
}