package supwisdom;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f7882a;
    public final String b;
    public final List<byte[]> c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f7883e;
    public final int f;
    public final int g;
    public String h;

    public hw(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public List<byte[]> a() {
        return this.c;
    }

    public void a(Integer num) {
    }

    public String b() {
        return this.d;
    }

    public void b(Integer num) {
    }

    public Object c() {
        return this.f7883e;
    }

    public byte[] d() {
        return this.f7882a;
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public String g() {
        return this.b;
    }

    public boolean h() {
        return this.f >= 0 && this.g >= 0;
    }

    public hw(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        this.f7882a = bArr;
        this.b = str;
        this.c = list;
        this.d = str2;
        this.f = i2;
        this.g = i;
    }

    public void a(Object obj) {
        this.f7883e = obj;
    }
}
