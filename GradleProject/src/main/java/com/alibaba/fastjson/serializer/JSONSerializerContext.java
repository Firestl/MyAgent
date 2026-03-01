package com.alibaba.fastjson.serializer;

/* JADX INFO: loaded from: classes.dex */
public final class JSONSerializerContext {
    public static final int DEFAULT_TABLE_SIZE = 128;
    public final Entry[] buckets;
    public final int indexMask;

    public static final class Entry {
        public final int hashCode;
        public Entry next;
        public final Object object;

        public Entry(Object obj, int i, Entry entry) {
            this.object = obj;
            this.next = entry;
            this.hashCode = i;
        }
    }

    public JSONSerializerContext() {
        this(128);
    }

    public final boolean put(Object obj) {
        int iIdentityHashCode = System.identityHashCode(obj);
        int i = this.indexMask & iIdentityHashCode;
        for (Entry entry = this.buckets[i]; entry != null; entry = entry.next) {
            if (obj == entry.object) {
                return true;
            }
        }
        this.buckets[i] = new Entry(obj, iIdentityHashCode, this.buckets[i]);
        return false;
    }

    public JSONSerializerContext(int i) {
        this.indexMask = i - 1;
        this.buckets = new Entry[i];
    }
}
