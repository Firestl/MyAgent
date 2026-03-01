package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import supwisdom.sd;
import supwisdom.vd;
import supwisdom.xd;

/* JADX INFO: loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements vd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final sd f1308a;
    public final vd b;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1309a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1309a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1309a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1309a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1309a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1309a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1309a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1309a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(sd sdVar, vd vdVar) {
        this.f1308a = sdVar;
        this.b = vdVar;
    }

    @Override // supwisdom.vd
    public void a(xd xdVar, Lifecycle.Event event) {
        switch (a.f1309a[event.ordinal()]) {
            case 1:
                this.f1308a.b(xdVar);
                break;
            case 2:
                this.f1308a.f(xdVar);
                break;
            case 3:
                this.f1308a.a(xdVar);
                break;
            case 4:
                this.f1308a.c(xdVar);
                break;
            case 5:
                this.f1308a.d(xdVar);
                break;
            case 6:
                this.f1308a.e(xdVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        vd vdVar = this.b;
        if (vdVar != null) {
            vdVar.a(xdVar, event);
        }
    }
}
