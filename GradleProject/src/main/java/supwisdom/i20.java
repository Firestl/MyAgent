package supwisdom;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: compiled from: ScriptTagPayloadReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class i20 extends com.google.android.exoplayer2.d.a.d {
    public long b;

    public i20(f50 f50Var) {
        super(f50Var);
        this.b = -9223372036854775807L;
    }

    public static int b(o80 o80Var) {
        return o80Var.g();
    }

    public static Boolean c(o80 o80Var) {
        return Boolean.valueOf(o80Var.g() == 1);
    }

    public static Double d(o80 o80Var) {
        return Double.valueOf(Double.longBitsToDouble(o80Var.p()));
    }

    public static String e(o80 o80Var) {
        int iH = o80Var.h();
        int iD = o80Var.d();
        o80Var.d(iH);
        return new String(o80Var.f8644a, iD, iH);
    }

    public static ArrayList<Object> f(o80 o80Var) {
        int iT = o80Var.t();
        ArrayList<Object> arrayList = new ArrayList<>(iT);
        for (int i = 0; i < iT; i++) {
            arrayList.add(a(o80Var, b(o80Var)));
        }
        return arrayList;
    }

    public static HashMap<String, Object> g(o80 o80Var) {
        HashMap<String, Object> map = new HashMap<>();
        while (true) {
            String strE = e(o80Var);
            int iB = b(o80Var);
            if (iB == 9) {
                return map;
            }
            map.put(strE, a(o80Var, iB));
        }
    }

    public static HashMap<String, Object> h(o80 o80Var) {
        int iT = o80Var.t();
        HashMap<String, Object> map = new HashMap<>(iT);
        for (int i = 0; i < iT; i++) {
            map.put(e(o80Var), a(o80Var, b(o80Var)));
        }
        return map;
    }

    public static Date i(o80 o80Var) {
        Date date = new Date((long) d(o80Var).doubleValue());
        o80Var.d(2);
        return date;
    }

    public long a() {
        return this.b;
    }

    @Override // com.google.android.exoplayer2.d.a.d
    public boolean a(o80 o80Var) {
        return true;
    }

    @Override // com.google.android.exoplayer2.d.a.d
    public void a(o80 o80Var, long j) throws com.google.android.exoplayer2.n {
        if (b(o80Var) != 2) {
            throw new com.google.android.exoplayer2.n();
        }
        if ("onMetaData".equals(e(o80Var)) && b(o80Var) == 8) {
            HashMap<String, Object> mapH = h(o80Var);
            if (mapH.containsKey("duration")) {
                double dDoubleValue = ((Double) mapH.get("duration")).doubleValue();
                if (dDoubleValue > 0.0d) {
                    this.b = (long) (dDoubleValue * 1000000.0d);
                }
            }
        }
    }

    public static Object a(o80 o80Var, int i) {
        if (i == 0) {
            return d(o80Var);
        }
        if (i == 1) {
            return c(o80Var);
        }
        if (i == 2) {
            return e(o80Var);
        }
        if (i == 3) {
            return g(o80Var);
        }
        if (i == 8) {
            return h(o80Var);
        }
        if (i == 10) {
            return f(o80Var);
        }
        if (i != 11) {
            return null;
        }
        return i(o80Var);
    }
}
