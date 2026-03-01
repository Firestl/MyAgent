package supwisdom;

import android.view.View;
import com.umeng.commonsdk.framework.UMModuleRegister;
import org.bouncycastle.pqc.crypto.newhope.Params;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class sf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f9162a;
    public a b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public interface b {
        int a();

        int a(View view);

        View a(int i);

        int b();

        int b(View view);
    }

    public sf(b bVar) {
        this.f9162a = bVar;
    }

    public View a(int i, int i2, int i3, int i4) {
        int iA = this.f9162a.a();
        int iB = this.f9162a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f9162a.a(i);
            this.b.a(iA, iB, this.f9162a.a(viewA), this.f9162a.b(viewA));
            if (i3 != 0) {
                this.b.b();
                this.b.a(i3);
                if (this.b.a()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.b.b();
                this.b.a(i4);
                if (this.b.a()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9163a = 0;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9164e;

        public int a(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        public void a(int i, int i2, int i3, int i4) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.f9164e = i4;
        }

        public void b() {
            this.f9163a = 0;
        }

        public void a(int i) {
            this.f9163a = i | this.f9163a;
        }

        public boolean a() {
            int i = this.f9163a;
            if ((i & 7) != 0 && (i & (a(this.d, this.b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f9163a;
            if ((i2 & 112) != 0 && (i2 & (a(this.d, this.c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f9163a;
            if ((i3 & Params.POLY_BYTES) != 0 && (i3 & (a(this.f9164e, this.b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f9163a;
            return (i4 & UMModuleRegister.SHARE_EVENT_VALUE_HIGH) == 0 || (i4 & (a(this.f9164e, this.c) << 12)) != 0;
        }
    }

    public boolean a(View view, int i) {
        this.b.a(this.f9162a.a(), this.f9162a.b(), this.f9162a.a(view), this.f9162a.b(view));
        if (i == 0) {
            return false;
        }
        this.b.b();
        this.b.a(i);
        return this.b.a();
    }
}
