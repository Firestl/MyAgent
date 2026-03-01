package supwisdom;

import android.util.Log;

/* JADX INFO: compiled from: CeaUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class x50 {
    public static void a(long j, o80 o80Var, f50[] f50VarArr) {
        while (o80Var.b() > 1) {
            int iA = a(o80Var);
            int iA2 = a(o80Var);
            if (iA2 == -1 || iA2 > o80Var.b()) {
                Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                o80Var.c(o80Var.c());
            } else if (a(iA, iA2, o80Var)) {
                o80Var.d(8);
                int iG = o80Var.g() & 31;
                o80Var.d(1);
                int i = iG * 3;
                int iD = o80Var.d();
                for (f50 f50Var : f50VarArr) {
                    o80Var.c(iD);
                    f50Var.a(o80Var, i);
                    f50Var.a(j, 1, i, 0, null);
                }
                o80Var.d(iA2 - (i + 10));
            } else {
                o80Var.d(iA2);
            }
        }
    }

    public static int a(o80 o80Var) {
        int i = 0;
        while (o80Var.b() != 0) {
            int iG = o80Var.g();
            i += iG;
            if (iG != 255) {
                return i;
            }
        }
        return -1;
    }

    public static boolean a(int i, int i2, o80 o80Var) {
        if (i != 4 || i2 < 8) {
            return false;
        }
        int iD = o80Var.d();
        int iG = o80Var.g();
        int iH = o80Var.h();
        int iN = o80Var.n();
        int iG2 = o80Var.g();
        o80Var.c(iD);
        return iG == 181 && iH == 49 && iN == 1195456820 && iG2 == 3;
    }
}
