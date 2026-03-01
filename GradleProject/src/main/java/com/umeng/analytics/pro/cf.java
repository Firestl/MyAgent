package com.umeng.analytics.pro;

import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.cc;
import com.umeng.analytics.pro.cf;
import com.xiaomi.mipush.sdk.Constants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: TUnion.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class cf<T extends cf<?, ?>, F extends cc> implements bv<T, F> {
    public static final Map<Class<? extends dc>, dd> c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f5225a;
    public F b;

    /* JADX INFO: compiled from: TUnion.java */
    public static class a extends de<cf> {
        public a() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, cf cfVar) throws cb {
            cfVar.b = null;
            cfVar.f5225a = null;
            cuVar.j();
            cp cpVarL = cuVar.l();
            Object objA = cfVar.a(cuVar, cpVarL);
            cfVar.f5225a = objA;
            if (objA != null) {
                cfVar.b = (F) cfVar.a(cpVarL.c);
            }
            cuVar.m();
            cuVar.l();
            cuVar.k();
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, cf cfVar) throws cb {
            if (cfVar.a() == null || cfVar.b() == null) {
                throw new cv("Cannot write a TUnion with no set value!");
            }
            cuVar.a(cfVar.d());
            cuVar.a(cfVar.c(cfVar.b));
            cfVar.a(cuVar);
            cuVar.c();
            cuVar.d();
            cuVar.b();
        }
    }

    /* JADX INFO: compiled from: TUnion.java */
    public static class b implements dd {
        public b() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: TUnion.java */
    public static class c extends df<cf> {
        public c() {
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(cu cuVar, cf cfVar) throws cb {
            cfVar.b = null;
            cfVar.f5225a = null;
            short sV = cuVar.v();
            Object objA = cfVar.a(cuVar, sV);
            cfVar.f5225a = objA;
            if (objA != null) {
                cfVar.b = (F) cfVar.a(sV);
            }
        }

        @Override // com.umeng.analytics.pro.dc
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(cu cuVar, cf cfVar) throws cb {
            if (cfVar.a() == null || cfVar.b() == null) {
                throw new cv("Cannot write a TUnion with no set value!");
            }
            cuVar.a(cfVar.b.a());
            cfVar.b(cuVar);
        }
    }

    /* JADX INFO: compiled from: TUnion.java */
    public static class d implements dd {
        public d() {
        }

        @Override // com.umeng.analytics.pro.dd
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    static {
        HashMap map = new HashMap();
        c = map;
        map.put(de.class, new b());
        c.put(df.class, new d());
    }

    public cf() {
        this.b = null;
        this.f5225a = null;
    }

    public static Object a(Object obj) {
        return obj instanceof bv ? ((bv) obj).deepCopy() : obj instanceof ByteBuffer ? bw.d((ByteBuffer) obj) : obj instanceof List ? a((List) obj) : obj instanceof Set ? a((Set) obj) : obj instanceof Map ? a((Map<Object, Object>) obj) : obj;
    }

    public abstract F a(short s);

    public abstract Object a(cu cuVar, cp cpVar) throws cb;

    public abstract Object a(cu cuVar, short s) throws cb;

    public abstract void a(cu cuVar) throws cb;

    public Object b() {
        return this.f5225a;
    }

    public abstract void b(F f, Object obj) throws ClassCastException;

    public abstract void b(cu cuVar) throws cb;

    public abstract cp c(F f);

    public boolean c() {
        return this.b != null;
    }

    @Override // com.umeng.analytics.pro.bv
    public final void clear() {
        this.b = null;
        this.f5225a = null;
    }

    public abstract cz d();

    @Override // com.umeng.analytics.pro.bv
    public void read(cu cuVar) throws cb {
        c.get(cuVar.D()).b().b(cuVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.L);
        sb.append(cf.class.getSimpleName());
        sb.append(Operators.SPACE_STR);
        if (a() != null) {
            Object objB = b();
            sb.append(c(a()).f5241a);
            sb.append(Constants.COLON_SEPARATOR);
            if (objB instanceof ByteBuffer) {
                bw.a((ByteBuffer) objB, sb);
            } else {
                sb.append(objB.toString());
            }
        }
        sb.append(Operators.G);
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.bv
    public void write(cu cuVar) throws cb {
        c.get(cuVar.D()).b().a(cuVar, this);
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean b(int i) {
        return b(a((short) i));
    }

    public cf(F f, Object obj) {
        a(f, obj);
    }

    public cf(cf<T, F> cfVar) {
        if (cfVar.getClass().equals(cf.class)) {
            this.b = cfVar.b;
            this.f5225a = a(cfVar.f5225a);
            return;
        }
        throw new ClassCastException();
    }

    public static Map a(Map<Object, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            map2.put(a(entry.getKey()), a(entry.getValue()));
        }
        return map2;
    }

    public static Set a(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(a(it.next()));
        }
        return hashSet;
    }

    public static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object a(F f) {
        if (f == this.b) {
            return b();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
    }

    public Object a(int i) {
        return a(a((short) i));
    }

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.f5225a = obj;
    }

    public void a(int i, Object obj) {
        a(a((short) i), obj);
    }
}
