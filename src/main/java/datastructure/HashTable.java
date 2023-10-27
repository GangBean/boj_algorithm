package datastructure;

import java.util.LinkedList;
import java.util.function.Function;

public class HashTable {
    private static final int MAX_SIZE = 1_000;
    private static final Function<String, Integer> HASH = (key) -> (key.hashCode() % MAX_SIZE);
    private LinkedList<String>[] table = new LinkedList<>[MAX_SIZE];
    
    public void put(String key, String value) {
        LinkedList<String> buckets = table[HASH.apply(key)];
        buckets.add(value);
    }
    
    public String get(String key) {
        return table[HASH.apply(key)].stream()
            .filter(key::equals)
            .findAny()
            .orElseThrow();
    }
}
