package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse;
import e.b;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class gp1<T extends CommonBaseResponse> implements hs1 {
    public abstract Class<T> a();

    public abstract void a(T t);

    public void a(Throwable th) {
        if (th instanceof b.a) {
            b(th);
        } else {
            b(new b.a(th, 1000));
        }
    }

    public abstract void b(Throwable th);

    @Override // supwisdom.hs1
    public void onFailure(gs1 gs1Var, IOException iOException) {
        a(iOException);
    }

    @Override // supwisdom.hs1
    public void onResponse(gs1 gs1Var, dt1 dt1Var) {
        try {
            Object[] objArr = new Object[4];
            objArr[0] = gs1Var.request().g();
            objArr[1] = Integer.valueOf(dt1Var.c());
            objArr[2] = dt1Var.g();
            objArr[3] = Boolean.valueOf(dt1Var.a() == null);
            String.format("json response: url[1/2][ %s ], code and message=[%d, %s], body is null[ %b ]", objArr);
            if (dt1Var.c() == 200 && dt1Var.a() != null) {
                String strString = dt1Var.a().string();
                String.format("json response: url[2/2][ %s ], json[---%s---]", gs1Var.request().g(), strString);
                CommonBaseResponse commonBaseResponse = (CommonBaseResponse) hp1.a(strString, a());
                if (commonBaseResponse == null) {
                    a(new e.d(-1, "解析数据类错误"));
                    return;
                }
                int i = commonBaseResponse.code;
                if (i != 0 && i != 200) {
                    a(new e.d(commonBaseResponse.code, commonBaseResponse.msg));
                    return;
                }
                a(commonBaseResponse);
                return;
            }
            a(e.b.a(new e.d(dt1Var)));
        } catch (Exception e2) {
            e2.printStackTrace();
            a(new e.d(-2, "解析错误:" + e2.getMessage()));
        }
    }
}
