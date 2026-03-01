package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import com.sangfor.dx.rop.annotation.AnnotationVisibility;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class f11 extends p21 {
    public static final b h = new b(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w31 f7558e;
    public y21 f;
    public byte[] g;

    /* JADX INFO: compiled from: Proguard */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7559a;

        static {
            int[] iArr = new int[AnnotationVisibility.values().length];
            f7559a = iArr;
            try {
                iArr[AnnotationVisibility.BUILD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7559a[AnnotationVisibility.RUNTIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7559a[AnnotationVisibility.SYSTEM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b implements Comparator<f11> {
        public b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f11 f11Var, f11 f11Var2) {
            int iD = f11Var.f.d();
            int iD2 = f11Var2.f.d();
            if (iD < iD2) {
                return -1;
            }
            return iD > iD2 ? 1 : 0;
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    @Override // supwisdom.p21
    public int b(p21 p21Var) {
        return this.f7558e.compareTo(((f11) p21Var).f7558e);
    }

    @Override // supwisdom.p21
    public String g() {
        return this.f7558e.toHuman();
    }

    public int hashCode() {
        return this.f7558e.hashCode();
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        this.f = t11Var.p().b(this.f7558e.getType());
        d31.a(t11Var, this.f7558e);
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        k61 k61Var = new k61();
        new d31(t21Var.b(), k61Var).a(this.f7558e, false);
        byte[] bArrH = k61Var.h();
        this.g = bArrH;
        a(bArrH.length + 1);
    }

    public void a(h61 h61Var, String str) {
        h61Var.a(0, str + "visibility: " + this.f7558e.i().toHuman());
        h61Var.a(0, str + "type: " + this.f7558e.getType().toHuman());
        for (y31 y31Var : this.f7558e.h()) {
            h61Var.a(0, str + y31Var.a().toHuman() + ": " + d31.b(y31Var.b()));
        }
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        boolean zE = h61Var.e();
        AnnotationVisibility annotationVisibilityI = this.f7558e.i();
        if (zE) {
            h61Var.a(0, f() + " annotation");
            h61Var.a(1, "  visibility: VISBILITY_" + annotationVisibilityI);
        }
        int i = a.f7559a[annotationVisibilityI.ordinal()];
        if (i == 1) {
            h61Var.writeByte(0);
        } else if (i == 2) {
            h61Var.writeByte(1);
        } else if (i == 3) {
            h61Var.writeByte(2);
        } else {
            throw new RuntimeException("shouldn't happen");
        }
        if (zE) {
            new d31(t11Var, h61Var).a(this.f7558e, true);
        } else {
            h61Var.write(this.g);
        }
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_ANNOTATION_ITEM;
    }

    public static void a(f11[] f11VarArr) {
        Arrays.sort(f11VarArr, h);
    }
}
