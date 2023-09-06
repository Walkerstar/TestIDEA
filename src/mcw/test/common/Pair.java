package mcw.test.common;

/**
 * @author MCW 2023/9/6
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair() {
    }

    public Pair(K key) {
        this.key = key;
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}