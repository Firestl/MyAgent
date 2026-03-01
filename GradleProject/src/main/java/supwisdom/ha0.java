package supwisdom;

/* JADX INFO: compiled from: SchemeValuePair.java */
/* JADX INFO: loaded from: classes.dex */
public class ha0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7819a;
    public final String b;

    public ha0(String str, String str2) {
        this.f7819a = str;
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ha0.class != obj.getClass()) {
            return false;
        }
        ha0 ha0Var = (ha0) obj;
        return x80.a(this.f7819a, ha0Var.f7819a) && x80.a(this.b, ha0Var.b);
    }

    public int hashCode() {
        String str = this.f7819a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }
}
