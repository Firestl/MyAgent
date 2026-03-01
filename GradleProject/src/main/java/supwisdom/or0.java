package supwisdom;

import com.huawei.hms.framework.common.ContainerUtils;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import supwisdom.cq0;

/* JADX INFO: compiled from: SmallSortedMap.java */
/* JADX INFO: loaded from: classes.dex */
public class or0<K extends Comparable<K>, V> extends AbstractMap<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8701a;
    public List<or0<K, V>.e> b;
    public Map<K, V> c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile or0<K, V>.g f8702e;
    public Map<K, V> f;
    public volatile or0<K, V>.c g;

    /* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class a<FieldDescriptorType> extends or0<FieldDescriptorType, Object> {
        public a(int i) {
            super(i, null);
        }

        @Override // supwisdom.or0
        public void i() {
            if (!h()) {
                for (int i = 0; i < d(); i++) {
                    Map.Entry<FieldDescriptorType, Object> entryA = a(i);
                    if (((cq0.b) entryA.getKey()).D()) {
                        entryA.setValue(Collections.unmodifiableList((List) entryA.getValue()));
                    }
                }
                for (Map.Entry<FieldDescriptorType, Object> entry : f()) {
                    if (((cq0.b) entry.getKey()).D()) {
                        entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                    }
                }
            }
            super.i();
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class b implements Iterator<Map.Entry<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8703a;
        public Iterator<Map.Entry<K, V>> b;

        public b() {
            this.f8703a = or0.this.b.size();
        }

        public final Iterator<Map.Entry<K, V>> a() {
            if (this.b == null) {
                this.b = or0.this.f.entrySet().iterator();
            }
            return this.b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            int i = this.f8703a;
            return (i > 0 && i <= or0.this.b.size()) || a().hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (a().hasNext()) {
                return a().next();
            }
            List list = or0.this.b;
            int i = this.f8703a - 1;
            this.f8703a = i;
            return (Map.Entry) list.get(i);
        }

        public /* synthetic */ b(or0 or0Var, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class c extends or0<K, V>.g {
        public c() {
            super(or0.this, null);
        }

        @Override // supwisdom.or0.g, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new b(or0.this, null);
        }

        public /* synthetic */ c(or0 or0Var, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Iterator<Object> f8704a = new a();
        public static final Iterable<Object> b = new b();

        /* JADX INFO: compiled from: SmallSortedMap.java */
        public class a implements Iterator<Object> {
            @Override // java.util.Iterator
            public boolean hasNext() {
                return false;
            }

            @Override // java.util.Iterator
            public Object next() {
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        /* JADX INFO: compiled from: SmallSortedMap.java */
        public class b implements Iterable<Object> {
            @Override // java.lang.Iterable
            public Iterator<Object> iterator() {
                return d.f8704a;
            }
        }

        public static <T> Iterable<T> b() {
            return (Iterable<T>) b;
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class e implements Map.Entry<K, V>, Comparable<or0<K, V>.e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final K f8705a;
        public V b;

        public e(or0 or0Var, Map.Entry<K, V> entry) {
            this(entry.getKey(), entry.getValue());
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(or0<K, V>.e eVar) {
            return getKey().compareTo(eVar.getKey());
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return a(this.f8705a, entry.getKey()) && a(this.b, entry.getValue());
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f8705a;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.b;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            or0.this.a();
            V v2 = this.b;
            this.b = v;
            return v2;
        }

        public String toString() {
            return this.f8705a + ContainerUtils.KEY_VALUE_DELIMITER + this.b;
        }

        public e(K k, V v) {
            this.f8705a = k;
            this.b = v;
        }

        public final boolean a(Object obj, Object obj2) {
            return obj == null ? obj2 == null : obj.equals(obj2);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f8705a;
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class f implements Iterator<Map.Entry<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8706a;
        public boolean b;
        public Iterator<Map.Entry<K, V>> c;

        public f() {
            this.f8706a = -1;
        }

        public final Iterator<Map.Entry<K, V>> a() {
            if (this.c == null) {
                this.c = or0.this.c.entrySet().iterator();
            }
            return this.c;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f8706a + 1 >= or0.this.b.size()) {
                return !or0.this.c.isEmpty() && a().hasNext();
            }
            return true;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.b) {
                throw new IllegalStateException("remove() was called before next()");
            }
            this.b = false;
            or0.this.a();
            if (this.f8706a >= or0.this.b.size()) {
                a().remove();
                return;
            }
            or0 or0Var = or0.this;
            int i = this.f8706a;
            this.f8706a = i - 1;
            or0Var.b(i);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            this.b = true;
            int i = this.f8706a + 1;
            this.f8706a = i;
            return i < or0.this.b.size() ? (Map.Entry) or0.this.b.get(this.f8706a) : a().next();
        }

        public /* synthetic */ f(or0 or0Var, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class g extends AbstractSet<Map.Entry<K, V>> {
        public g() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean add(Map.Entry<K, V> entry) {
            if (contains(entry)) {
                return false;
            }
            or0.this.put(entry.getKey(), entry.getValue());
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            or0.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = or0.this.get(entry.getKey());
            Object value = entry.getValue();
            return obj2 == value || (obj2 != null && obj2.equals(value));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new f(or0.this, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (!contains(entry)) {
                return false;
            }
            or0.this.remove(entry.getKey());
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return or0.this.size();
        }

        public /* synthetic */ g(or0 or0Var, a aVar) {
            this();
        }
    }

    public /* synthetic */ or0(int i, a aVar) {
        this(i);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        a();
        if (!this.b.isEmpty()) {
            this.b.clear();
        }
        if (this.c.isEmpty()) {
            return;
        }
        this.c.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return a(comparable) >= 0 || this.c.containsKey(comparable);
    }

    public int e() {
        return this.c.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.f8702e == null) {
            this.f8702e = new g(this, null);
        }
        return this.f8702e;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof or0)) {
            return super.equals(obj);
        }
        or0 or0Var = (or0) obj;
        int size = size();
        if (size != or0Var.size()) {
            return false;
        }
        int iD = d();
        if (iD != or0Var.d()) {
            return entrySet().equals(or0Var.entrySet());
        }
        for (int i = 0; i < iD; i++) {
            if (!a(i).equals(or0Var.a(i))) {
                return false;
            }
        }
        if (iD != size) {
            return this.c.equals(or0Var.c);
        }
        return true;
    }

    public Iterable<Map.Entry<K, V>> f() {
        return this.c.isEmpty() ? d.b() : this.c.entrySet();
    }

    public final SortedMap<K, V> g() {
        a();
        if (this.c.isEmpty() && !(this.c instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.c = treeMap;
            this.f = treeMap.descendingMap();
        }
        return (SortedMap) this.c;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        return iA >= 0 ? this.b.get(iA).getValue() : this.c.get(comparable);
    }

    public boolean h() {
        return this.d;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iD = d();
        int iHashCode = 0;
        for (int i = 0; i < iD; i++) {
            iHashCode += this.b.get(i).hashCode();
        }
        return e() > 0 ? iHashCode + this.c.hashCode() : iHashCode;
    }

    public void i() {
        if (this.d) {
            return;
        }
        this.c = this.c.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.c);
        this.f = this.f.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f);
        this.d = true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        a();
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        if (iA >= 0) {
            return b(iA);
        }
        if (this.c.isEmpty()) {
            return null;
        }
        return this.c.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.b.size() + this.c.size();
    }

    public or0(int i) {
        this.f8701a = i;
        this.b = Collections.emptyList();
        this.c = Collections.emptyMap();
        this.f = Collections.emptyMap();
    }

    public static <FieldDescriptorType extends cq0.b<FieldDescriptorType>> or0<FieldDescriptorType, Object> c(int i) {
        return new a(i);
    }

    public final V b(int i) {
        a();
        V value = this.b.remove(i).getValue();
        if (!this.c.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = g().entrySet().iterator();
            this.b.add(new e(this, it.next()));
            it.remove();
        }
        return value;
    }

    public int d() {
        return this.b.size();
    }

    public Map.Entry<K, V> a(int i) {
        return this.b.get(i);
    }

    public final void c() {
        a();
        if (!this.b.isEmpty() || (this.b instanceof ArrayList)) {
            return;
        }
        this.b = new ArrayList(this.f8701a);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public V put(K k, V v) {
        a();
        int iA = a(k);
        if (iA >= 0) {
            return this.b.get(iA).setValue(v);
        }
        c();
        int i = -(iA + 1);
        if (i >= this.f8701a) {
            return g().put(k, v);
        }
        int size = this.b.size();
        int i2 = this.f8701a;
        if (size == i2) {
            or0<K, V>.e eVarRemove = this.b.remove(i2 - 1);
            g().put(eVarRemove.getKey(), eVarRemove.getValue());
        }
        this.b.add(i, new e(k, v));
        return null;
    }

    public Set<Map.Entry<K, V>> b() {
        if (this.g == null) {
            this.g = new c(this, null);
        }
        return this.g;
    }

    public final int a(K k) {
        int size = this.b.size() - 1;
        if (size >= 0) {
            int iCompareTo = k.compareTo(this.b.get(size).getKey());
            if (iCompareTo > 0) {
                return -(size + 2);
            }
            if (iCompareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int iCompareTo2 = k.compareTo(this.b.get(i2).getKey());
            if (iCompareTo2 < 0) {
                size = i2 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i2;
                }
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public final void a() {
        if (this.d) {
            throw new UnsupportedOperationException();
        }
    }
}
