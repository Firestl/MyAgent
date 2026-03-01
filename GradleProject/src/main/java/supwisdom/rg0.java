package supwisdom;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import supwisdom.nf0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class rg0 implements Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ qg0 f9059a;

    public rg0(qg0 qg0Var) {
        this.f9059a = qg0Var;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.f9059a.d) {
                nf0.a aVar = (nf0.a) message.obj;
                sg0 sg0Var = (sg0) this.f9059a.d.get(aVar);
                if (sg0Var != null && sg0Var.c()) {
                    if (sg0Var.a()) {
                        sg0Var.b("GmsClientSupervisor");
                    }
                    this.f9059a.d.remove(aVar);
                }
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this.f9059a.d) {
            nf0.a aVar2 = (nf0.a) message.obj;
            sg0 sg0Var2 = (sg0) this.f9059a.d.get(aVar2);
            if (sg0Var2 != null && sg0Var2.b() == 3) {
                String strValueOf = String.valueOf(aVar2);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 47);
                sb.append("Timeout waiting for ServiceConnection callback ");
                sb.append(strValueOf);
                Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                ComponentName componentNameE = sg0Var2.e();
                if (componentNameE == null) {
                    componentNameE = aVar2.b();
                }
                if (componentNameE == null) {
                    String strA = aVar2.a();
                    pf0.a(strA);
                    componentNameE = new ComponentName(strA, "unknown");
                }
                sg0Var2.onServiceDisconnected(componentNameE);
            }
        }
        return true;
    }
}
