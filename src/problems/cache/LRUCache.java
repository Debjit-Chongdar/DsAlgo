package problems.cache;


import java.util.HashMap;
import java.util.Map;

class LRU_Node {
    int key;
    String data;
    LRU_Node preNode;
    LRU_Node nextNode;

    public LRU_Node(int key, String data) {
        this.key = key;
        this.data = data;
    }
}

public class LRUCache {
    private int cacheSize;
    private LRU_Node head;
    private LRU_Node tail;
    private Map<Integer, LRU_Node> valueNodeMap = new HashMap<>();

    public LRUCache(int size) {
        this.cacheSize = size;
    }

    public void addIntoCache(int key, String value) {
        if (valueNodeMap.containsKey(key)) {
            removeFromCache(key);
            if (valueNodeMap.get(key) == tail) {
                return;
            }
        }
        // if not present then add and check new size if it exceds then remove head
        LRU_Node node = new LRU_Node(key, value);
        if (tail == null) {
            head = node;
            tail = node;
            valueNodeMap.put(key, node);
            return;
        }
        node.preNode = tail;
        tail.nextNode = node;
        tail = node;
        valueNodeMap.put(key, node);
        // if size exceds then remove head
        if (valueNodeMap.size() > cacheSize) {
            valueNodeMap.remove(head.key, head);
            head = head.nextNode;
            head.preNode = null;
        }
    }

    private void removeFromCache(int key) {
        LRU_Node node = valueNodeMap.get(key);
        if (node == tail) {
            return;
        } else if (node == head) {
            valueNodeMap.remove(key, node);
            head = head.nextNode;
            head.preNode = null;
            return;
        }
        valueNodeMap.remove(key, node);
        LRU_Node pre = node.preNode;
        LRU_Node post = node.nextNode;
        pre.nextNode = post;
        post.preNode = pre;
    }

    public String get(int key) {
        if (valueNodeMap.containsKey(key)) {
            return valueNodeMap.get(key).data;
        }
        return "removed from cache";
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        cache.addIntoCache(1, "a");
        cache.addIntoCache(2, "b");
        cache.addIntoCache(3, "c");
        cache.addIntoCache(1, "a");
        cache.addIntoCache(4, "d");
        cache.addIntoCache(5, "e");
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
    }
}
