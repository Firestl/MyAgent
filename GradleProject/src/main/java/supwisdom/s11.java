package supwisdom;

import com.sangfor.dex.util.ExceptionWithContext;
import com.sangfor.dx.dex.code.LocalList;
import com.sangfor.dx.dex.file.ItemType;
import java.io.PrintWriter;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class s11 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final jz0 f9109e;
    public byte[] f;
    public final boolean g;
    public final r51 h;

    public s11(jz0 jz0Var, boolean z, r51 r51Var) {
        super(1, -1);
        if (jz0Var == null) {
            throw new NullPointerException("code == null");
        }
        this.f9109e = jz0Var;
        this.g = z;
        this.h = r51Var;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
    }

    public void a(t11 t11Var, h61 h61Var, String str) {
        a(t11Var, str, null, h61Var, false);
    }

    public final byte[] b(t11 t11Var, String str, PrintWriter printWriter, h61 h61Var, boolean z) {
        wz0 wz0VarG = this.f9109e.g();
        LocalList localListF = this.f9109e.f();
        lz0 lz0VarE = this.f9109e.e();
        r11 r11Var = new r11(wz0VarG, localListF, t11Var, lz0VarE.h(), lz0VarE.j(), this.g, this.h);
        return (printWriter == null && h61Var == null) ? r11Var.b() : r11Var.a(str, printWriter, h61Var, z);
    }

    @Override // supwisdom.p21
    public String g() {
        throw new RuntimeException("unsupported");
    }

    public final byte[] a(t11 t11Var, String str, PrintWriter printWriter, h61 h61Var, boolean z) {
        return b(t11Var, str, printWriter, h61Var, z);
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_DEBUG_INFO_ITEM;
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        try {
            byte[] bArrA = a(t21Var.b(), null, null, null, false);
            this.f = bArrA;
            a(bArrA.length);
        } catch (RuntimeException e2) {
            throw ExceptionWithContext.withContext(e2, "...while placing debug info for " + this.h.toHuman());
        }
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        if (h61Var.e()) {
            h61Var.a(f() + " debug info");
            a(t11Var, null, null, h61Var, true);
        }
        h61Var.write(this.f);
    }
}
