package supwisdom;

import android.util.Log;
import com.hihonor.push.sdk.HonorMessageService;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class qt0 implements fu0<ot0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ HonorMessageService f8951a;

    public qt0(HonorMessageService honorMessageService) {
        this.f8951a = honorMessageService;
    }

    @Override // supwisdom.fu0
    public void a(pt0<ot0> pt0Var) {
        if (!pt0Var.e()) {
            boolean z = pt0Var.b() instanceof JSONException;
            return;
        }
        ot0 ot0VarC = pt0Var.c();
        if (ot0VarC == null) {
            Log.i("HonorMessageService", "parse remote data failed.");
            return;
        }
        Log.i("HonorMessageService", "onMessageReceived. msgId is " + ot0VarC.a());
        this.f8951a.a(ot0VarC);
    }
}
