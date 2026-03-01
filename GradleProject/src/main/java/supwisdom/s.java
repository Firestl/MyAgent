package supwisdom;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.entity.ECardResData;

/* JADX INFO: loaded from: classes.dex */
public class s implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f9103a;
    public final /* synthetic */ r b;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            s.this.b.c.d();
        }
    }

    public s(r rVar, String str) {
        this.b = rVar;
        this.f9103a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            ECardResData<String> eCardResDataA = this.b.g.a(this.f9103a, r.j);
            if (eCardResDataA == null) {
                r.a(this.b, "Check0003", "校验失败");
                return;
            }
            if (!eCardResDataA.isSuccess()) {
                d0.b("queryIdserialByToken: " + eCardResDataA);
                if (!TextUtils.isEmpty(eCardResDataA.getResultData())) {
                    d0.b("queryIdserialByToken decode resultData：" + hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key)));
                }
                r.a(this.b, "Check0003", eCardResDataA.getMessage());
                return;
            }
            String strA = gw0.a(b0.a(R.string.sdk_des_key), JSON.parseObject(hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key))).getString("idserial"));
            Log.d("a.a.a.b.l", "parseToken----idserial: decodeCBC=" + strA);
            if (TextUtils.isEmpty(strA)) {
                r.a(this.b, "Check0003", "数据解析异常");
                return;
            }
            a.a.a.c.c cVarM = a.a.a.c.c.m();
            cVarM.c(strA);
            cVarM.b();
            if (this.b.c != null) {
                this.b.d.post(new a());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            r.a(this.b, "Check0003", e2.getMessage());
        }
    }
}
