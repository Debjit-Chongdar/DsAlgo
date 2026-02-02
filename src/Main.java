import java.util.*;
import java.util.stream.Collectors;

class Order{
    int countryCode;
    String state;
    int order_count;
    public int getOrder_count(){
        return this.order_count;
    }

    public int getCountryCode() {
        return countryCode;
    }
}
public class Main {
    public static void main(String[] args) {
        List<List<Order>> countryOrders = new ArrayList<>();
        Map<Integer, List<Order>> result = countryOrders.stream().flatMap(list-> list.stream())
                .collect(Collectors.groupingBy(Order::getCountryCode,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(Order::getOrder_count)
                                                .reversed()).limit(3).collect(Collectors.toList())
                        )
                ));

        List<List<Order>> result_1 = countryOrders.stream().map(
                list -> list.stream().sorted(
                        Comparator.comparingInt(Order::getOrder_count).reversed()
                ).skip(2).limit(3).collect(Collectors.toList())
        ).collect(Collectors.toList());
    }
}
// Skip => it will skip first number of elements mentioned
// limit => how many we want in response