package supwisdom;

import android.os.Handler;
import android.os.Message;
import supwisdom.aa1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class v91 implements Handler.Callback, q91 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9493a;
    public boolean b;
    public boolean c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9494e;
    public final int f;

    /* JADX INFO: compiled from: Proguard */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final v91 f9495a = new v91();
    }

    public static final v91 f() {
        return b.f9495a;
    }

    public final void a(Message message) {
        if (this.c) {
            t91.c("HCallBackStub", "call handleLaunchActivityForMasterSlaveModeManager");
            sb1.a().a(message.obj);
        }
        if (this.b) {
            t91.c("HCallBackStub", "shareBusiness call putExtraDataIntoIntent");
            nb1.b(message.obj);
        }
    }

    public final void b(Message message) {
        if (!this.c) {
            t91.d("HCallBackStub", "no need Handle NewIntent For masterslave business, reason: not support MasterSlave");
        } else {
            t91.c("HCallBackStub", "call handleNewIntentForMasterSlaveModeManager");
            sb1.a().b(message.obj);
        }
    }

    public final Handler.Callback c() {
        return ca1.f7170a.a(d());
    }

    public final void c(Message message) {
    }

    public final Handler d() {
        return aa1.b.a(aa1.f6877a.a(new Object[0]));
    }

    public void e() {
        this.b = true;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        t91.c("HCallBackStub", "handleMessage called, msg:  " + message.what + ", mIsSupportMasterSlave: " + this.c + "isCalling: " + this.f9493a + ", mIsSupportShareBusiness: " + this.b);
        if (!this.f9493a) {
            this.f9493a = true;
            try {
                try {
                    int i = message.what;
                    if (i == this.d) {
                        t91.c("HCallBackStub", "LAUNCH_ACTIVITY msg in");
                        a(message);
                    } else if (i == this.f) {
                        c(message);
                    } else if (i == this.f9494e) {
                        t91.c("HCallBackStub", "handleNewIntent msg in");
                        b(message);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                this.f9493a = false;
            }
        }
        return false;
    }

    public v91() {
        this.f9493a = false;
        this.b = false;
        this.c = false;
        ra1 ra1Var = aa1.b.f6879a;
        if (ra1Var != null) {
            this.d = ra1Var.a();
        } else {
            t91.d("HCallBackStub", "no handle LAUNCH_ACTIVITY event, because ActivityThread.H.LAUNCH_ACTIVITY is null");
            this.d = -1;
        }
        ra1 ra1Var2 = aa1.b.c;
        if (ra1Var2 != null) {
            this.f9494e = ra1Var2.a();
        } else {
            t91.d("HCallBackStub", "no handle LAUNCH_ACTIVITY event, because ActivityThread.H.LAUNCH_ACTIVITY is null");
            this.f9494e = -1;
        }
        ra1 ra1Var3 = aa1.b.b;
        if (ra1Var3 != null) {
            this.f = ra1Var3.a();
        } else {
            t91.d("HCallBackStub", "no handle RECEIVER event, because ActivityThread.H.RECEIVER is null");
            this.f = -1;
        }
    }

    @Override // supwisdom.q91
    public boolean b() {
        return c() == this;
    }

    @Override // supwisdom.q91
    public void a() {
        c();
        ca1.f7170a.a(d(), this);
    }
}
