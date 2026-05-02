package stream.collectors.util;

import java.util.Arrays;
import java.util.List;

public class Item {
    int type_id;
    int count;
    String name;

    public Item(int type_id, int count, String name) {
        this.type_id = type_id;
        this.count = count;
        this.name = name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "type_id=" + type_id +
                ", count=" + count +
                ", name='" + name + '\'' +
                '}';
    }

    public static List<Item> getSampleData(){
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
        return items;
    }
}
