package supwisdom;

import android.text.TextUtils;

/* JADX INFO: compiled from: ExpressionPair.java */
/* JADX INFO: loaded from: classes.dex */
public class jj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8069a;
    public final String b;

    public jj(String str, String str2) {
        this.f8069a = str;
        this.b = str2;
    }

    public static jj a(String str, String str2) {
        return new jj(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || jj.class != obj.getClass()) {
            return false;
        }
        jj jjVar = (jj) obj;
        String str = this.f8069a;
        if (str == null ? jjVar.f8069a != null : !str.equals(jjVar.f8069a)) {
            return false;
        }
        String str2 = this.b;
        String str3 = jjVar.b;
        return str2 != null ? str2.equals(str3) : str3 == null;
    }

    public int hashCode() {
        String str = this.f8069a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public static boolean a(jj jjVar) {
        return (jjVar == null || TextUtils.isEmpty(jjVar.b) || "{}".equals(jjVar.b)) ? false : true;
    }
}
