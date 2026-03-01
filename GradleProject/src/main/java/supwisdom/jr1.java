package supwisdom;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketReceiveResult;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class jr1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f8082a;
    public final /* synthetic */ gr1 b;

    public class a extends TypeToken<Map<String, Object>> {
        public a(jr1 jr1Var) {
        }
    }

    public jr1(gr1 gr1Var, String str) {
        this.b = gr1Var;
        this.f8082a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.d != null) {
            try {
                this.b.d.onSocketReceiveDataReslut(new ExternalSocketReceiveResult(ExternalCodeResultEnum.ERROR_NO.code, "收到新socket消息", (Map) new Gson().fromJson(this.f8082a, new a(this).getType())));
            } catch (JsonSyntaxException unused) {
                OnReceivePaySocketMessageListener onReceivePaySocketMessageListener = this.b.d;
                ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_SOCKET_RECEIVE_DATA;
                onReceivePaySocketMessageListener.onSocketReceiveDataReslut(new ExternalSocketReceiveResult(externalCodeResultEnum.code, externalCodeResultEnum.msg, null));
            }
        }
    }
}
