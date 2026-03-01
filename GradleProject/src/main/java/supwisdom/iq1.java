package supwisdom;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: Maps.java */
/* JADX INFO: loaded from: classes3.dex */
public final class iq1 {
    public static <K, V> V a(Map<K, V> map, K k, V v) {
        jq1.a(map);
        if (map instanceof ConcurrentMap) {
            return (V) ((ConcurrentMap) map).putIfAbsent(k, v);
        }
        V v2 = map.get(k);
        return v2 == null ? map.put(k, v) : v2;
    }
}
