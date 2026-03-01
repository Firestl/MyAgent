package supwisdom;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.entity.ECardResData;

/* JADX INFO: loaded from: classes.dex */
public class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f8855a;
    public final /* synthetic */ p b;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            q.this.b.b.d();
        }
    }

    public q(p pVar, String str) {
        this.b = pVar;
        this.f8855a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            ECardResData<String> eCardResDataA = this.b.f8747a.a(this.f8855a, p.g);
            if (eCardResDataA == null) {
                p.a(this.b, "Check0003", "校验失败");
                return;
            }
            if (!eCardResDataA.isSuccess()) {
                d0.b("queryIdserialByToken: " + eCardResDataA);
                if (!TextUtils.isEmpty(eCardResDataA.getResultData())) {
                    d0.b("queryIdserialByToken decode resultData：" + hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key)));
                }
                p.a(this.b, "Check0003", eCardResDataA.getMessage());
                return;
            }
            String strA = gw0.a(JSON.parseObject(hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key))).getString("idserial"), "1", b0.a(R.string.des_key));
            Log.d("a.a.a.b.j", "parseToken----idserial: decodeCBC=" + strA);
            if (TextUtils.isEmpty(strA)) {
                p.a(this.b, "Check0003", "数据解析异常");
                return;
            }
            a.a.a.c.c cVarM = a.a.a.c.c.m();
            cVarM.c(strA);
            cVarM.b();
            if (this.b.b != null) {
                this.b.c.post(new a());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            p.a(this.b, "Check0003", e2.getMessage());
        }
    }
}
