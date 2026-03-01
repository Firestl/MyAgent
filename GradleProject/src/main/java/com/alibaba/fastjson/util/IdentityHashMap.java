package com.alibaba.fastjson.util;

/* JADX INFO: loaded from: classes.dex */
public class IdentityHashMap<K, V> {
    public static final int DEFAULT_TABLE_SIZE = 1024;
    public final Entry<K, V>[] buckets;
    public final int indexMask;

    public static final class Entry<K, V> {
        public final int hashCode;
        public final K key;
        public final Entry<K, V> next;
        public V value;

        public Entry(K k, V v, int i, Entry<K, V> entry) {
            this.key = k;
            this.value = v;
            this.next = entry;
            this.hashCode = i;
        }
    }

    public IdentityHashMap() {
        this(1024);
    }

    public final V get(K k) {
        for (Entry<K, V> entry = this.buckets[System.identityHashCode(k) & this.indexMask]; entry != null; entry = entry.next) {
            if (k == entry.key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean put(K k, V v) {
        int iIdentityHashCode = System.identityHashCode(k);
        int i = this.indexMask & iIdentityHashCode;
        for (Entry<K, V> entry = this.buckets[i]; entry != null; entry = entry.next) {
            if (k == entry.key) {
                entry.value = v;
                return true;
            }
        }
        this.buckets[i] = new Entry<>(k, v, iIdentityHashCode, this.buckets[i]);
        return false;
    }

    public int size() {
        int i = 0;
        int i2 = 0;
        while (true) {
            Entry<K, V>[] entryArr = this.buckets;
            if (i >= entryArr.length) {
                return i2;
            }
            for (Entry<K, V> entry = entryArr[i]; entry != null; entry = entry.next) {
                i2++;
            }
            i++;
        }
    }

    public IdentityHashMap(int i) {
        this.indexMask = i - 1;
        this.buckets = new Entry[i];
    }
}
