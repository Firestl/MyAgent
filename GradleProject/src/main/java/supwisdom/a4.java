package supwisdom;

import java.util.HashMap;
import java.util.Map;
import supwisdom.b4;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a4<K, V> extends b4<K, V> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashMap<K, b4.c<K, V>> f6852e = new HashMap<>();

    @Override // supwisdom.b4
    public b4.c<K, V> a(K k) {
        return this.f6852e.get(k);
    }

    @Override // supwisdom.b4
    public V b(K k, V v) {
        b4.c<K, V> cVarA = a(k);
        if (cVarA != null) {
            return cVarA.b;
        }
        this.f6852e.put(k, a(k, v));
        return null;
    }

    public boolean contains(K k) {
        return this.f6852e.containsKey(k);
    }

    @Override // supwisdom.b4
    public V remove(K k) {
        V v = (V) super.remove(k);
        this.f6852e.remove(k);
        return v;
    }

    public Map.Entry<K, V> b(K k) {
        if (contains(k)) {
            return this.f6852e.get(k).d;
        }
        return null;
    }
}
