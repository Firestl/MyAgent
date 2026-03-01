package supwisdom;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: RtmpSessionInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class x00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9693a = Integer.MAX_VALUE;
    public int b = 128;
    public int c = 128;
    public Map<Integer, u00> d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<Integer, String> f9694e = new ConcurrentHashMap();

    public u00 a(int i) {
        u00 u00Var = this.d.get(Integer.valueOf(i));
        if (u00Var != null) {
            return u00Var;
        }
        u00 u00Var2 = new u00();
        this.d.put(Integer.valueOf(i), u00Var2);
        return u00Var2;
    }

    public int b() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }

    public String d(int i) {
        return this.f9694e.remove(Integer.valueOf(i));
    }

    public void b(int i) {
        this.f9693a = i;
    }

    public int c() {
        return this.c;
    }

    public String a(int i, String str) {
        return this.f9694e.put(Integer.valueOf(i), str);
    }

    public int a() {
        return this.f9693a;
    }
}
