package supwisdom;

import com.google.crypto.tink.shaded.protobuf.MapFieldLite;
import java.util.Map;
import supwisdom.oq0;

/* JADX INFO: compiled from: MapFieldSchemaLite.java */
/* JADX INFO: loaded from: classes.dex */
public class qq0 implements pq0 {
    @Override // supwisdom.pq0
    public Object a(Object obj) {
        ((MapFieldLite) obj).makeImmutable();
        return obj;
    }

    @Override // supwisdom.pq0
    public oq0.a<?, ?> b(Object obj) {
        return ((oq0) obj).a();
    }

    @Override // supwisdom.pq0
    public Map<?, ?> c(Object obj) {
        return (MapFieldLite) obj;
    }

    @Override // supwisdom.pq0
    public Object d(Object obj) {
        return MapFieldLite.emptyMapField().mutableCopy();
    }

    @Override // supwisdom.pq0
    public boolean e(Object obj) {
        return !((MapFieldLite) obj).isMutable();
    }

    @Override // supwisdom.pq0
    public Map<?, ?> f(Object obj) {
        return (MapFieldLite) obj;
    }

    public static <K, V> MapFieldLite<K, V> b(Object obj, Object obj2) {
        MapFieldLite<K, V> mapFieldLiteMutableCopy = (MapFieldLite) obj;
        MapFieldLite<K, V> mapFieldLite = (MapFieldLite) obj2;
        if (!mapFieldLite.isEmpty()) {
            if (!mapFieldLiteMutableCopy.isMutable()) {
                mapFieldLiteMutableCopy = mapFieldLiteMutableCopy.mutableCopy();
            }
            mapFieldLiteMutableCopy.mergeFrom(mapFieldLite);
        }
        return mapFieldLiteMutableCopy;
    }

    @Override // supwisdom.pq0
    public Object a(Object obj, Object obj2) {
        return b(obj, obj2);
    }

    @Override // supwisdom.pq0
    public int a(int i, Object obj, Object obj2) {
        return b(i, obj, obj2);
    }

    public static <K, V> int b(int i, Object obj, Object obj2) {
        MapFieldLite mapFieldLite = (MapFieldLite) obj;
        oq0 oq0Var = (oq0) obj2;
        int iA = 0;
        if (mapFieldLite.isEmpty()) {
            return 0;
        }
        for (Map.Entry<K, V> entry : mapFieldLite.entrySet()) {
            iA += oq0Var.a(i, entry.getKey(), entry.getValue());
        }
        return iA;
    }
}
