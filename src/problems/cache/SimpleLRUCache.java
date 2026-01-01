package problems.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleLRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public SimpleLRUCache(int capacity) {
        // true = access-order (LRU)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        SimpleLRUCache<Integer, String> cache = new SimpleLRUCache(4);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        cache.put(1, "a");
        cache.put(4, "d");
        cache.put(5, "e");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}