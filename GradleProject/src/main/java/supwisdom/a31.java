package supwisdom;

import com.bumptech.glide.load.engine.GlideException;
import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class a31 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d61 f6851e;

    public a31(d61 d61Var) {
        super(4, (d61Var.size() * 2) + 4);
        this.f6851e = d61Var;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        z21 z21VarP = t11Var.p();
        int size = this.f6851e.size();
        for (int i = 0; i < size; i++) {
            z21VarP.b(this.f6851e.getType(i));
        }
    }

    @Override // supwisdom.p21
    public int b(p21 p21Var) {
        return a61.a(this.f6851e, ((a31) p21Var).f6851e);
    }

    @Override // supwisdom.p21
    public String g() {
        throw new RuntimeException("unsupported");
    }

    public d61 h() {
        return this.f6851e;
    }

    public int hashCode() {
        return a61.a(this.f6851e);
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_TYPE_LIST;
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        z21 z21VarP = t11Var.p();
        int size = this.f6851e.size();
        if (h61Var.e()) {
            h61Var.a(0, f() + " type_list");
            h61Var.a(4, "  size: " + m61.g(size));
            for (int i = 0; i < size; i++) {
                b61 type = this.f6851e.getType(i);
                h61Var.a(2, GlideException.IndentedAppendable.INDENT + m61.d(z21VarP.a(type)) + " // " + type.toHuman());
            }
        }
        h61Var.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            h61Var.writeShort(z21VarP.a(this.f6851e.getType(i2)));
        }
    }
}
