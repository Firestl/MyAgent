package supwisdom;

import com.bumptech.glide.load.engine.GlideException;
import com.sangfor.dex.util.ExceptionWithContext;
import java.util.ArrayList;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class lz0 extends l61 {
    public final int c;

    public lz0(int i, int i2) {
        super(i);
        this.c = i2;
    }

    public static lz0 a(ArrayList<kz0> arrayList, int i) {
        int size = arrayList.size();
        lz0 lz0Var = new lz0(size, i);
        for (int i2 = 0; i2 < size; i2++) {
            lz0Var.a(i2, arrayList.get(i2));
        }
        lz0Var.e();
        return lz0Var;
    }

    public kz0 d(int i) {
        return (kz0) a(i);
    }

    public int h() {
        int size = size();
        if (size == 0) {
            return 0;
        }
        return d(size - 1).g();
    }

    public int i() {
        int iH;
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            kz0 kz0Var = (kz0) a(i2);
            if (kz0Var instanceof iz0) {
                u41 u41VarN = ((iz0) kz0Var).n();
                if (u41VarN instanceof x41) {
                    iH = ((x41) u41VarN).a(kz0Var.h().a() == 113);
                } else {
                    if (u41VarN instanceof b51) {
                        ((b51) u41VarN).e();
                        throw null;
                    }
                    iH = 0;
                }
            } else if (!(kz0Var instanceof tz0)) {
                continue;
            } else {
                if (kz0Var.h().a() != 250) {
                    throw new RuntimeException("Expecting invoke-polymorphic");
                }
                iH = ((t51) ((tz0) kz0Var).c(1)).d().c().h() + 1;
            }
            if (iH > i) {
                i = iH;
            }
        }
        return i;
    }

    public int j() {
        return this.c;
    }

    public void a(int i, kz0 kz0Var) {
        a(i, (Object) kz0Var);
    }

    public void a(h61 h61Var) {
        int iA = h61Var.a();
        int size = size();
        if (h61Var.e()) {
            boolean zC = h61Var.c();
            for (int i = 0; i < size; i++) {
                kz0 kz0Var = (kz0) a(i);
                int iB = kz0Var.b() * 2;
                String strA = (iB != 0 || zC) ? kz0Var.a(GlideException.IndentedAppendable.INDENT, h61Var.b(), true) : null;
                if (strA != null) {
                    h61Var.a(iB, strA);
                } else if (iB != 0) {
                    h61Var.a(iB, "");
                }
            }
        }
        for (int i2 = 0; i2 < size; i2++) {
            kz0 kz0Var2 = (kz0) a(i2);
            try {
                kz0Var2.a(h61Var);
            } catch (RuntimeException e2) {
                throw ExceptionWithContext.withContext(e2, "...while writing " + kz0Var2);
            }
        }
        int iA2 = (h61Var.a() - iA) / 2;
        if (iA2 == h()) {
            return;
        }
        throw new RuntimeException("write length mismatch; expected " + h() + " but actually wrote " + iA2);
    }
}
