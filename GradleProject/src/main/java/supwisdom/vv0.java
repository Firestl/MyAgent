package supwisdom;

/* JADX INFO: compiled from: ColumnEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public class vv0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9552a;
    public String b;
    public String[] c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9553e;
    public boolean f;

    public vv0(String... strArr) {
        this.c = strArr;
    }

    public vv0(String str, String str2) {
        this(str, str2, false, false, false);
    }

    public vv0(String str, String str2, boolean z, boolean z2) {
        this(str, str2, z, z2, false);
    }

    public vv0(String str, String str2, boolean z, boolean z2, boolean z3) {
        this.f9552a = str;
        this.b = str2;
        this.d = z;
        this.f9553e = z2;
        this.f = z3;
    }
}
