package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: LazyStringArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public class jq0 extends kp0<String> implements kq0, RandomAccess {
    public static final jq0 c;
    public final List<Object> b;

    static {
        jq0 jq0Var = new jq0();
        c = jq0Var;
        jq0Var.c();
    }

    public jq0() {
        this(10);
    }

    @Override // supwisdom.kp0, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String set(int i, String str) {
        a();
        return a(this.b.set(i, str));
    }

    @Override // supwisdom.kp0, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        a();
        this.b.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // supwisdom.kq0
    public List<?> d() {
        return Collections.unmodifiableList(this.b);
    }

    @Override // supwisdom.kq0
    public kq0 e() {
        return f() ? new ur0(this) : this;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.b.size();
    }

    public jq0(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    @Override // supwisdom.gq0.i
    /* JADX INFO: renamed from: a */
    public jq0 a2(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.b);
        return new jq0((ArrayList<Object>) arrayList);
    }

    @Override // supwisdom.kp0, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends String> collection) {
        a();
        if (collection instanceof kq0) {
            collection = ((kq0) collection).d();
        }
        boolean zAddAll = this.b.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int i) {
        Object obj = this.b.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.b.set(i, stringUtf8);
            }
            return stringUtf8;
        }
        byte[] bArr = (byte[]) obj;
        String strC = gq0.c(bArr);
        if (gq0.b(bArr)) {
            this.b.set(i, strC);
        }
        return strC;
    }

    @Override // java.util.AbstractList, java.util.List
    public String remove(int i) {
        a();
        Object objRemove = this.b.remove(i);
        ((AbstractList) this).modCount++;
        return a(objRemove);
    }

    public jq0(ArrayList<Object> arrayList) {
        this.b = arrayList;
    }

    @Override // supwisdom.kq0
    public Object b(int i) {
        return this.b.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(int i, String str) {
        a();
        this.b.add(i, str);
        ((AbstractList) this).modCount++;
    }

    @Override // supwisdom.kq0
    public void a(ByteString byteString) {
        a();
        this.b.add(byteString);
        ((AbstractList) this).modCount++;
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            return ((ByteString) obj).toStringUtf8();
        }
        return gq0.c((byte[]) obj);
    }
}
