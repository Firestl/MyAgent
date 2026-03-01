package supwisdom;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class j4<K, V> extends p4<K, V> implements Map<K, V> {
    public o4<K, V> mCollections;

    /* JADX INFO: compiled from: ArrayMap.java */
    public class a extends o4<K, V> {
        public a() {
        }

        @Override // supwisdom.o4
        public Object a(int i, int i2) {
            return j4.this.mArray[(i << 1) + i2];
        }

        @Override // supwisdom.o4
        public int b(Object obj) {
            return j4.this.indexOfValue(obj);
        }

        @Override // supwisdom.o4
        public int c() {
            return j4.this.mSize;
        }

        @Override // supwisdom.o4
        public int a(Object obj) {
            return j4.this.indexOfKey(obj);
        }

        @Override // supwisdom.o4
        public Map<K, V> b() {
            return j4.this;
        }

        @Override // supwisdom.o4
        public void a(K k, V v) {
            j4.this.put(k, v);
        }

        @Override // supwisdom.o4
        public V a(int i, V v) {
            return j4.this.setValueAt(i, v);
        }

        @Override // supwisdom.o4
        public void a(int i) {
            j4.this.removeAt(i);
        }

        @Override // supwisdom.o4
        public void a() {
            j4.this.clear();
        }
    }

    public j4() {
    }

    private o4<K, V> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new a();
        }
        return this.mCollections;
    }

    public boolean containsAll(Collection<?> collection) {
        return o4.a((Map) this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return getCollection().d();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return getCollection().e();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.mSize + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(Collection<?> collection) {
        return o4.b(this, collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return o4.c(this, collection);
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return getCollection().f();
    }

    public j4(int i) {
        super(i);
    }

    public j4(p4 p4Var) {
        super(p4Var);
    }
}
