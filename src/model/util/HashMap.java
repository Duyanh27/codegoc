package model.util;

public class HashMap<K extends Comparable<K>, V> {

    // Entry class representing a key-value pair
    public static class Entry<K, V> {
        final public K key;
        public V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // List to store entries
    private final ArrayList<Entry<K,V>> entries;

    public HashMap() {
        this.entries = new ArrayList<>();
    }

    public void put(K key, V value) {
        // Update value if key exists
        for (Entry<K, V> entry : entries) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        // Otherwise, add new entry
        entries.add(new Entry<>(key, value));
    }

    public V get(K key) {
        for (Entry<K, V> entry : entries) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Key not found
    }

    public int size() {
        return entries.size();
    }

    public ArrayList<Entry<K, V>> entryList() {
        return entries;
    }
}
