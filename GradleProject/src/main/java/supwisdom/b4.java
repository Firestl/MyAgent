package supwisdom;

import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b4<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c<K, V> f7015a;
    public c<K, V> b;
    public WeakHashMap<f<K, V>, Boolean> c = new WeakHashMap<>();
    public int d = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // supwisdom.b4.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.d;
        }

        @Override // supwisdom.b4.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.c;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class b<K, V> extends e<K, V> {
        public b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // supwisdom.b4.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.c;
        }

        @Override // supwisdom.b4.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.d;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final K f7016a;
        public final V b;
        public c<K, V> c;
        public c<K, V> d;

        public c(K k, V v) {
            this.f7016a = k;
            this.b = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f7016a.equals(cVar.f7016a) && this.b.equals(cVar.b);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f7016a;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f7016a.hashCode() ^ this.b.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f7016a + ContainerUtils.KEY_VALUE_DELIMITER + this.b;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c<K, V> f7017a;
        public boolean b = true;

        public d() {
        }

        @Override // supwisdom.b4.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f7017a;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.d;
                this.f7017a = cVar3;
                this.b = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.b) {
                return b4.this.f7015a != null;
            }
            c<K, V> cVar = this.f7017a;
            return (cVar == null || cVar.c == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (this.b) {
                this.b = false;
                this.f7017a = b4.this.f7015a;
            } else {
                c<K, V> cVar = this.f7017a;
                this.f7017a = cVar != null ? cVar.c : null;
            }
            return this.f7017a;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c<K, V> f7018a;
        public c<K, V> b;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f7018a = cVar2;
            this.b = cVar;
        }

        @Override // supwisdom.b4.f
        public void a(c<K, V> cVar) {
            if (this.f7018a == cVar && cVar == this.b) {
                this.b = null;
                this.f7018a = null;
            }
            c<K, V> cVar2 = this.f7018a;
            if (cVar2 == cVar) {
                this.f7018a = b(cVar2);
            }
            if (this.b == cVar) {
                this.b = a();
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b != null;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.b;
            this.b = a();
            return cVar;
        }

        public final c<K, V> a() {
            c<K, V> cVar = this.b;
            c<K, V> cVar2 = this.f7018a;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public c<K, V> a(K k) {
        c<K, V> cVar = this.f7015a;
        while (cVar != null && !cVar.f7016a.equals(k)) {
            cVar = cVar.c;
        }
        return cVar;
    }

    public V b(K k, V v) {
        c<K, V> cVarA = a(k);
        if (cVarA != null) {
            return cVarA.b;
        }
        a(k, v);
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b4)) {
            return false;
        }
        b4 b4Var = (b4) obj;
        if (size() != b4Var.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = b4Var.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    public b4<K, V>.d g() {
        b4<K, V>.d dVar = new d();
        this.c.put(dVar, false);
        return dVar;
    }

    public Map.Entry<K, V> h() {
        return this.b;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            iHashCode += it.next().hashCode();
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f7015a, this.b);
        this.c.put(aVar, false);
        return aVar;
    }

    public V remove(K k) {
        c<K, V> cVarA = a(k);
        if (cVarA == null) {
            return null;
        }
        this.d--;
        if (!this.c.isEmpty()) {
            Iterator<f<K, V>> it = this.c.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarA);
            }
        }
        c<K, V> cVar = cVarA.d;
        if (cVar != null) {
            cVar.c = cVarA.c;
        } else {
            this.f7015a = cVarA.c;
        }
        c<K, V> cVar2 = cVarA.c;
        if (cVar2 != null) {
            cVar2.d = cVarA.d;
        } else {
            this.b = cVarA.d;
        }
        cVarA.c = null;
        cVarA.d = null;
        return cVarA.b;
    }

    public int size() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START_STR);
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public c<K, V> a(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.d++;
        c<K, V> cVar2 = this.b;
        if (cVar2 == null) {
            this.f7015a = cVar;
            this.b = cVar;
            return cVar;
        }
        cVar2.c = cVar;
        cVar.d = cVar2;
        this.b = cVar;
        return cVar;
    }

    public Map.Entry<K, V> b() {
        return this.f7015a;
    }

    public Iterator<Map.Entry<K, V>> a() {
        b bVar = new b(this.b, this.f7015a);
        this.c.put(bVar, false);
        return bVar;
    }
}
