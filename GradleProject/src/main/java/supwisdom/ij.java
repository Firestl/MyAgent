package supwisdom;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: ExpressionHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class ij {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7958a;
    public String b;
    public jj c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f7959e;
    public Map<String, Object> f;

    public ij(String str, String str2, jj jjVar, String str3, String str4, Map<String, Object> map) {
        this.f7958a = str;
        this.b = str2;
        this.c = jjVar;
        this.d = str3;
        this.f7959e = str4;
        if (map == null) {
            this.f = Collections.emptyMap();
        } else {
            this.f = Collections.unmodifiableMap(map);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ij.class != obj.getClass()) {
            return false;
        }
        ij ijVar = (ij) obj;
        String str = this.f7958a;
        if (str == null ? ijVar.f7958a != null : !str.equals(ijVar.f7958a)) {
            return false;
        }
        jj jjVar = this.c;
        if (jjVar == null ? ijVar.c != null : !jjVar.equals(ijVar.c)) {
            return false;
        }
        String str2 = this.d;
        if (str2 == null ? ijVar.d != null : !str2.equals(ijVar.d)) {
            return false;
        }
        String str3 = this.f7959e;
        if (str3 == null ? ijVar.f7959e != null : !str3.equals(ijVar.f7959e)) {
            return false;
        }
        Map<String, Object> map = this.f;
        Map<String, Object> map2 = ijVar.f;
        return map != null ? map.equals(map2) : map2 == null;
    }

    public int hashCode() {
        String str = this.f7958a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        jj jjVar = this.c;
        int iHashCode2 = (iHashCode + (jjVar != null ? jjVar.hashCode() : 0)) * 31;
        String str2 = this.d;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f7959e;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Map<String, Object> map = this.f;
        return iHashCode4 + (map != null ? map.hashCode() : 0);
    }
}
