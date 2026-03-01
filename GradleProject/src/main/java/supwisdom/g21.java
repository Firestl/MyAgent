package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class g21 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ItemType f7682e;
    public final t21 f;
    public final f21 g;
    public final int h;

    public g21(ItemType itemType, t21 t21Var, f21 f21Var, f21 f21Var2, int i) {
        super(4, 12);
        if (itemType == null) {
            throw new NullPointerException("type == null");
        }
        if (t21Var == null) {
            throw new NullPointerException("section == null");
        }
        if (f21Var == null) {
            throw new NullPointerException("firstItem == null");
        }
        if (f21Var2 == null) {
            throw new NullPointerException("lastItem == null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("itemCount <= 0");
        }
        this.f7682e = itemType;
        this.f = t21Var;
        this.g = f21Var;
        this.h = i;
    }

    public static void a(t21[] t21VarArr, o21 o21Var) {
        if (t21VarArr == null) {
            throw new NullPointerException("sections == null");
        }
        if (o21Var.d().size() != 0) {
            throw new IllegalArgumentException("mapSection.items().size() != 0");
        }
        ArrayList arrayList = new ArrayList(50);
        for (t21 t21Var : t21VarArr) {
            ItemType itemType = null;
            f21 f21Var = null;
            f21 f21Var2 = null;
            int i = 0;
            for (f21 f21Var3 : t21Var.d()) {
                ItemType itemTypeA = f21Var3.a();
                if (itemTypeA != itemType) {
                    if (i != 0) {
                        arrayList.add(new g21(itemType, t21Var, f21Var, f21Var2, i));
                    }
                    f21Var = f21Var3;
                    itemType = itemTypeA;
                    i = 0;
                }
                i++;
                f21Var2 = f21Var3;
            }
            if (i != 0) {
                arrayList.add(new g21(itemType, t21Var, f21Var, f21Var2, i));
            } else if (t21Var == o21Var) {
                arrayList.add(new g21(o21Var));
            }
        }
        o21Var.a((p21) new c31(ItemType.TYPE_MAP_LIST, arrayList));
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        int mapValue = this.f7682e.getMapValue();
        f21 f21Var = this.g;
        int iC = f21Var == null ? this.f.c() : this.f.a(f21Var);
        if (h61Var.e()) {
            h61Var.a(0, f() + ' ' + this.f7682e.getTypeName() + " map");
            h61Var.a(2, "  type:   " + m61.d(mapValue) + " // " + this.f7682e.toString());
            h61Var.a(2, "  unused: 0");
            StringBuilder sb = new StringBuilder();
            sb.append("  size:   ");
            sb.append(m61.g(this.h));
            h61Var.a(4, sb.toString());
            h61Var.a(4, "  offset: " + m61.g(iC));
        }
        h61Var.writeShort(mapValue);
        h61Var.writeShort(0);
        h61Var.writeInt(this.h);
        h61Var.writeInt(iC);
    }

    @Override // supwisdom.p21
    public final String g() {
        return toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(g21.class.getName());
        sb.append(Operators.BLOCK_START);
        sb.append(this.f.toString());
        sb.append(' ');
        sb.append(this.f7682e.toHuman());
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public g21(t21 t21Var) {
        super(4, 12);
        if (t21Var != null) {
            this.f7682e = ItemType.TYPE_MAP_LIST;
            this.f = t21Var;
            this.g = null;
            this.h = 1;
            return;
        }
        throw new NullPointerException("section == null");
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_MAP_ITEM;
    }
}
