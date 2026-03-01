package supwisdom;

import com.bumptech.glide.load.engine.GlideException;
import io.dcloud.common.util.PdrUtil;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import supwisdom.fz0;
import supwisdom.gz0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class m11 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final jz0 f8345a;
    public gz0 b = null;
    public byte[] c = null;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TreeMap<fz0, Integer> f8346e = null;

    public m11(jz0 jz0Var) {
        this.f8345a = jz0Var;
    }

    public static void a(fz0 fz0Var, int i, int i2, String str, PrintWriter printWriter, h61 h61Var) {
        String strA = fz0Var.a(str, m61.d(i) + ": ");
        if (printWriter != null) {
            printWriter.println(strA);
        }
        h61Var.a(i2, strA);
    }

    public int b() {
        a();
        return this.b.size();
    }

    public int c() {
        return (b() * 8) + this.c.length;
    }

    public final void a(String str, PrintWriter printWriter, h61 h61Var) {
        a();
        int i = 0;
        boolean z = h61Var != null;
        int i2 = z ? 6 : 0;
        int i3 = z ? 2 : 0;
        int size = this.b.size();
        String str2 = str + GlideException.IndentedAppendable.INDENT;
        if (z) {
            h61Var.a(0, str + "tries:");
        } else {
            printWriter.println(str + "tries:");
        }
        for (int i4 = 0; i4 < size; i4++) {
            gz0.a aVarD = this.b.d(i4);
            fz0 fz0VarB = aVarD.b();
            String str3 = str2 + "try " + m61.e(aVarD.c()) + PdrUtil.FILE_PATH_ENTRY_BACK + m61.e(aVarD.a());
            String strA = fz0VarB.a(str2, "");
            if (z) {
                h61Var.a(i2, str3);
                h61Var.a(i3, strA);
            } else {
                printWriter.println(str3);
                printWriter.println(strA);
            }
        }
        if (z) {
            h61Var.a(0, str + "handlers:");
            h61Var.a(this.d, str2 + "size: " + m61.d(this.f8346e.size()));
            fz0 fz0Var = null;
            for (Map.Entry<fz0, Integer> entry : this.f8346e.entrySet()) {
                fz0 key = entry.getKey();
                int iIntValue = entry.getValue().intValue();
                if (fz0Var != null) {
                    a(fz0Var, i, iIntValue - i, str2, printWriter, h61Var);
                }
                fz0Var = key;
                i = iIntValue;
            }
            a(fz0Var, i, this.c.length - i, str2, printWriter, h61Var);
        }
    }

    public void a(t11 t11Var) {
        a();
        z21 z21VarP = t11Var.p();
        int size = this.b.size();
        this.f8346e = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            this.f8346e.put(this.b.d(i).b(), null);
        }
        if (this.f8346e.size() <= 65535) {
            k61 k61Var = new k61();
            this.d = k61Var.c(this.f8346e.size());
            for (Map.Entry<fz0, Integer> entry : this.f8346e.entrySet()) {
                fz0 key = entry.getKey();
                int size2 = key.size();
                boolean zH = key.h();
                entry.setValue(Integer.valueOf(k61Var.a()));
                if (zH) {
                    k61Var.f(-(size2 - 1));
                    size2--;
                } else {
                    k61Var.f(size2);
                }
                for (int i2 = 0; i2 < size2; i2++) {
                    fz0.a aVarD = key.d(i2);
                    k61Var.c(z21VarP.a(aVarD.a()));
                    k61Var.c(aVarD.b());
                }
                if (zH) {
                    k61Var.c(key.d(size2).b());
                }
            }
            this.c = k61Var.h();
            return;
        }
        throw new UnsupportedOperationException("too many catch handlers");
    }

    public final void a() {
        if (this.b == null) {
            this.b = this.f8345a.c();
        }
    }

    public void a(t11 t11Var, h61 h61Var) {
        a();
        if (h61Var.e()) {
            a(GlideException.IndentedAppendable.INDENT, null, h61Var);
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            gz0.a aVarD = this.b.d(i);
            int iC = aVarD.c();
            int iA = aVarD.a();
            int i2 = iA - iC;
            if (i2 < 65536) {
                h61Var.writeInt(iC);
                h61Var.writeShort(i2);
                h61Var.writeShort(this.f8346e.get(aVarD.b()).intValue());
            } else {
                throw new UnsupportedOperationException("bogus exception range: " + m61.g(iC) + PdrUtil.FILE_PATH_ENTRY_BACK + m61.g(iA));
            }
        }
        h61Var.write(this.c);
    }
}
