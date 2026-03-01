package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class cy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final wx f7270a;
    public final wx b;
    public final xx c;

    public cy(wx wxVar, wx wxVar2, xx xxVar, boolean z) {
        this.f7270a = wxVar;
        this.b = wxVar2;
        this.c = xxVar;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public wx b() {
        return this.f7270a;
    }

    public wx c() {
        return this.b;
    }

    public boolean d() {
        return this.b == null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof cy)) {
            return false;
        }
        cy cyVar = (cy) obj;
        return a(this.f7270a, cyVar.f7270a) && a(this.b, cyVar.b) && a(this.c, cyVar.c);
    }

    public int hashCode() {
        return (a(this.f7270a) ^ a(this.b)) ^ a(this.c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(this.f7270a);
        sb.append(" , ");
        sb.append(this.b);
        sb.append(" : ");
        xx xxVar = this.c;
        sb.append(xxVar == null ? com.igexin.push.core.b.m : Integer.valueOf(xxVar.c()));
        sb.append(" ]");
        return sb.toString();
    }

    public xx a() {
        return this.c;
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
