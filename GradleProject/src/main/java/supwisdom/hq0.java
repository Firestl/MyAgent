package supwisdom;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LazyField.java */
/* JADX INFO: loaded from: classes.dex */
public class hq0 extends iq0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final uq0 f7864e;

    /* JADX INFO: compiled from: LazyField.java */
    public static class b<K> implements Map.Entry<K, Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Map.Entry<K, hq0> f7865a;

        public hq0 a() {
            return this.f7865a.getValue();
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f7865a.getKey();
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            hq0 value = this.f7865a.getValue();
            if (value == null) {
                return null;
            }
            return value.c();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (obj instanceof uq0) {
                return this.f7865a.getValue().c((uq0) obj);
            }
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }

        public b(Map.Entry<K, hq0> entry) {
            this.f7865a = entry;
        }
    }

    /* JADX INFO: compiled from: LazyField.java */
    public static class c<K> implements Iterator<Map.Entry<K, Object>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Iterator<Map.Entry<K, Object>> f7866a;

        public c(Iterator<Map.Entry<K, Object>> it) {
            this.f7866a = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f7866a.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.f7866a.remove();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, Object> next() {
            Map.Entry<K, Object> next = this.f7866a.next();
            return next.getValue() instanceof hq0 ? new b(next) : next;
        }
    }

    public uq0 c() {
        return b(this.f7864e);
    }

    @Override // supwisdom.iq0
    public boolean equals(Object obj) {
        return c().equals(obj);
    }

    @Override // supwisdom.iq0
    public int hashCode() {
        return c().hashCode();
    }

    public String toString() {
        return c().toString();
    }
}
