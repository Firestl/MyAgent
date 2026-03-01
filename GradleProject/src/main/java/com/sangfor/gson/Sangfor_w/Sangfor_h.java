package com.sangfor.gson.Sangfor_w;

import com.huawei.hms.framework.common.ContainerUtils;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class Sangfor_h<K, V> extends AbstractMap<K, V> implements Serializable {
    public Comparator<? super K> Sangfor_c;
    public e<K, V> Sangfor_d;
    public int Sangfor_e;
    public int Sangfor_f;
    public final e<K, V> Sangfor_g;
    public Sangfor_h<K, V>.b Sangfor_h;
    public Sangfor_h<K, V>.c Sangfor_i;
    public static final /* synthetic */ boolean Sangfor_b = !Sangfor_h.class.desiredAssertionStatus();
    public static final Comparator<Comparable> Sangfor_a = new a();

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Comparator<Comparable> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b extends AbstractSet<Map.Entry<K, V>> {

        /* JADX INFO: compiled from: Proguard */
        public class a extends Sangfor_h<K, V>.d<Map.Entry<K, V>> {
            public a(b bVar) {
                super();
            }

            @Override // java.util.Iterator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        public b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Sangfor_h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && Sangfor_h.this.Sangfor_a((Map.Entry<?, ?>) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e<K, V> eVarSangfor_a;
            if (!(obj instanceof Map.Entry) || (eVarSangfor_a = Sangfor_h.this.Sangfor_a((Map.Entry<?, ?>) obj)) == null) {
                return false;
            }
            Sangfor_h.this.Sangfor_b(eVarSangfor_a, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Sangfor_h.this.Sangfor_e;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public final class c extends AbstractSet<K> {

        /* JADX INFO: compiled from: Proguard */
        public class a extends Sangfor_h<K, V>.d<K> {
            public a(c cVar) {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().f;
            }
        }

        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Sangfor_h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return Sangfor_h.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return Sangfor_h.this.Sangfor_b(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Sangfor_h.this.Sangfor_e;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public abstract class d<T> implements Iterator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e<K, V> f3884a;
        public e<K, V> b = null;
        public int c;

        public d() {
            this.f3884a = Sangfor_h.this.Sangfor_g.d;
            this.c = Sangfor_h.this.Sangfor_f;
        }

        public final e<K, V> a() {
            e<K, V> eVar = this.f3884a;
            Sangfor_h sangfor_h = Sangfor_h.this;
            if (eVar == sangfor_h.Sangfor_g) {
                throw new NoSuchElementException();
            }
            if (sangfor_h.Sangfor_f != this.c) {
                throw new ConcurrentModificationException();
            }
            this.f3884a = eVar.d;
            this.b = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f3884a != Sangfor_h.this.Sangfor_g;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.b;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            Sangfor_h.this.Sangfor_b(eVar, true);
            this.b = null;
            this.c = Sangfor_h.this.Sangfor_f;
        }
    }

    public Sangfor_h() {
        this(Sangfor_a);
    }

    public e<K, V> Sangfor_a(K k, boolean z) {
        int iCompareTo;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.Sangfor_c;
        e<K, V> eVar2 = this.Sangfor_d;
        if (eVar2 != null) {
            Comparable comparable = comparator == Sangfor_a ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f) : comparator.compare(k, eVar2.f);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.b : eVar2.c;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        e<K, V> eVar4 = this.Sangfor_g;
        if (eVar2 != null) {
            eVar = new e<>(eVar2, k, eVar4, eVar4.f3886e);
            if (iCompareTo < 0) {
                eVar2.b = eVar;
            } else {
                eVar2.c = eVar;
            }
            Sangfor_a((e) eVar2, true);
        } else {
            if (comparator == Sangfor_a && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.f3886e);
            this.Sangfor_d = eVar;
        }
        this.Sangfor_e++;
        this.Sangfor_f++;
        return eVar;
    }

    public void Sangfor_b(e<K, V> eVar, boolean z) {
        int i;
        if (z) {
            e<K, V> eVar2 = eVar.f3886e;
            eVar2.d = eVar.d;
            eVar.d.f3886e = eVar2;
        }
        e<K, V> eVar3 = eVar.b;
        e<K, V> eVar4 = eVar.c;
        e<K, V> eVar5 = eVar.f3885a;
        int i2 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                Sangfor_a((e) eVar, (e) eVar3);
                eVar.b = null;
            } else if (eVar4 != null) {
                Sangfor_a((e) eVar, (e) eVar4);
                eVar.c = null;
            } else {
                Sangfor_a((e) eVar, (e) null);
            }
            Sangfor_a((e) eVar5, false);
            this.Sangfor_e--;
            this.Sangfor_f++;
            return;
        }
        e<K, V> eVarB = eVar3.h > eVar4.h ? eVar3.b() : eVar4.a();
        Sangfor_b(eVarB, false);
        e<K, V> eVar6 = eVar.b;
        if (eVar6 != null) {
            i = eVar6.h;
            eVarB.b = eVar6;
            eVar6.f3885a = eVarB;
            eVar.b = null;
        } else {
            i = 0;
        }
        e<K, V> eVar7 = eVar.c;
        if (eVar7 != null) {
            i2 = eVar7.h;
            eVarB.c = eVar7;
            eVar7.f3885a = eVarB;
            eVar.c = null;
        }
        eVarB.h = Math.max(i, i2) + 1;
        Sangfor_a((e) eVar, (e) eVarB);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.Sangfor_d = null;
        this.Sangfor_e = 0;
        this.Sangfor_f++;
        e<K, V> eVar = this.Sangfor_g;
        eVar.f3886e = eVar;
        eVar.d = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return Sangfor_a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Sangfor_h<K, V>.b bVar = this.Sangfor_h;
        if (bVar != null) {
            return bVar;
        }
        Sangfor_h<K, V>.b bVar2 = new b();
        this.Sangfor_h = bVar2;
        return bVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        e<K, V> eVarSangfor_a = Sangfor_a(obj);
        if (eVarSangfor_a != null) {
            return eVarSangfor_a.g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Sangfor_h<K, V>.c cVar = this.Sangfor_i;
        if (cVar != null) {
            return cVar;
        }
        Sangfor_h<K, V>.c cVar2 = new c();
        this.Sangfor_i = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        e<K, V> eVarSangfor_a = Sangfor_a((Object) k, true);
        V v2 = eVarSangfor_a.g;
        eVarSangfor_a.g = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarSangfor_b = Sangfor_b(obj);
        if (eVarSangfor_b != null) {
            return eVarSangfor_b.g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.Sangfor_e;
    }

    public Sangfor_h(Comparator<? super K> comparator) {
        this.Sangfor_e = 0;
        this.Sangfor_f = 0;
        this.Sangfor_g = new e<>();
        this.Sangfor_c = comparator == null ? Sangfor_a : comparator;
    }

    /* JADX INFO: compiled from: Proguard */
    public static final class e<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e<K, V> f3885a;
        public e<K, V> b;
        public e<K, V> c;
        public e<K, V> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e<K, V> f3886e;
        public final K f;
        public V g;
        public int h;

        public e() {
            this.f = null;
            this.f3886e = this;
            this.d = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.b; eVar2 != null; eVar2 = eVar2.b) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.c; eVar2 != null; eVar2 = eVar2.c) {
                eVar = eVar2;
            }
            return eVar;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k = this.f;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.g;
            if (v == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!v.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.g;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.g;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public String toString() {
            return this.f + ContainerUtils.KEY_VALUE_DELIMITER + this.g;
        }

        public e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f3885a = eVar;
            this.f = k;
            this.h = 1;
            this.d = eVar2;
            this.f3886e = eVar3;
            eVar3.d = this;
            eVar2.f3886e = this;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e<K, V> Sangfor_a(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return Sangfor_a(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public e<K, V> Sangfor_a(Map.Entry<?, ?> entry) {
        e<K, V> eVarSangfor_a = Sangfor_a(entry.getKey());
        if (eVarSangfor_a != null && Sangfor_a(eVarSangfor_a.g, entry.getValue())) {
            return eVarSangfor_a;
        }
        return null;
    }

    private boolean Sangfor_a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void Sangfor_a(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f3885a;
        eVar.f3885a = null;
        if (eVar2 != null) {
            eVar2.f3885a = eVar3;
        }
        if (eVar3 != null) {
            if (eVar3.b == eVar) {
                eVar3.b = eVar2;
                return;
            } else {
                if (!Sangfor_b && eVar3.c != eVar) {
                    throw new AssertionError();
                }
                eVar3.c = eVar2;
                return;
            }
        }
        this.Sangfor_d = eVar2;
    }

    public e<K, V> Sangfor_b(Object obj) {
        e<K, V> eVarSangfor_a = Sangfor_a(obj);
        if (eVarSangfor_a != null) {
            Sangfor_b(eVarSangfor_a, true);
        }
        return eVarSangfor_a;
    }

    private void Sangfor_b(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.b;
        e<K, V> eVar3 = eVar.c;
        e<K, V> eVar4 = eVar2.b;
        e<K, V> eVar5 = eVar2.c;
        eVar.b = eVar5;
        if (eVar5 != null) {
            eVar5.f3885a = eVar;
        }
        Sangfor_a((e) eVar, (e) eVar2);
        eVar2.c = eVar;
        eVar.f3885a = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.h : 0, eVar5 != null ? eVar5.h : 0) + 1;
        eVar.h = iMax;
        eVar2.h = Math.max(iMax, eVar4 != null ? eVar4.h : 0) + 1;
    }

    private void Sangfor_a(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.b;
            e<K, V> eVar3 = eVar.c;
            int i = eVar2 != null ? eVar2.h : 0;
            int i2 = eVar3 != null ? eVar3.h : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                e<K, V> eVar4 = eVar3.b;
                e<K, V> eVar5 = eVar3.c;
                int i4 = (eVar4 != null ? eVar4.h : 0) - (eVar5 != null ? eVar5.h : 0);
                if (i4 != -1 && (i4 != 0 || z)) {
                    if (!Sangfor_b && i4 != 1) {
                        throw new AssertionError();
                    }
                    Sangfor_b((e) eVar3);
                    Sangfor_a((e) eVar);
                } else {
                    Sangfor_a((e) eVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                e<K, V> eVar6 = eVar2.b;
                e<K, V> eVar7 = eVar2.c;
                int i5 = (eVar6 != null ? eVar6.h : 0) - (eVar7 != null ? eVar7.h : 0);
                if (i5 != 1 && (i5 != 0 || z)) {
                    if (!Sangfor_b && i5 != -1) {
                        throw new AssertionError();
                    }
                    Sangfor_a((e) eVar2);
                    Sangfor_b((e) eVar);
                } else {
                    Sangfor_b((e) eVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                eVar.h = i + 1;
                if (z) {
                    return;
                }
            } else {
                if (!Sangfor_b && i3 != -1 && i3 != 1) {
                    throw new AssertionError();
                }
                eVar.h = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f3885a;
        }
    }

    private void Sangfor_a(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.b;
        e<K, V> eVar3 = eVar.c;
        e<K, V> eVar4 = eVar3.b;
        e<K, V> eVar5 = eVar3.c;
        eVar.c = eVar4;
        if (eVar4 != null) {
            eVar4.f3885a = eVar;
        }
        Sangfor_a((e) eVar, (e) eVar3);
        eVar3.b = eVar;
        eVar.f3885a = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.h : 0, eVar4 != null ? eVar4.h : 0) + 1;
        eVar.h = iMax;
        eVar3.h = Math.max(iMax, eVar5 != null ? eVar5.h : 0) + 1;
    }
}
