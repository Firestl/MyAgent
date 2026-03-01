package com.unicom.xiaowo.account.shield.c;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.unicom.xiaowo.account.shield.ResultListener;
import com.unicom.xiaowo.account.shield.c.a;
import com.unicom.xiaowo.account.shield.e.f;
import com.unicom.xiaowo.account.shield.e.g;
import com.unicom.xiaowo.account.shield.e.h;
import com.unicom.xiaowo.account.shield.e.i;
import com.unicom.xiaowo.account.shield.e.j;
import com.unicom.xiaowo.account.shield.e.k;
import com.unicom.xiaowo.account.shield.e.l;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c f5540a;

    private int a(String str, String str2) {
        try {
            byte[] bytes = str.getBytes();
            byte[] bytes2 = str2.getBytes();
            for (int i = 0; i < 5; i++) {
                int i2 = 5 + i;
                if (bytes[i2] != bytes2[i2]) {
                    return 0;
                }
            }
            return 1;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static c a() {
        if (f5540a == null) {
            synchronized (c.class) {
                if (f5540a == null) {
                    f5540a = new c();
                }
            }
        }
        return f5540a;
    }

    private boolean a(Context context) {
        int i;
        InputStream inputStreamB = null;
        try {
            try {
                inputStreamB = h.b(context);
                byte[] bArrA = h.a(inputStreamB);
                String strA = h.a(bArrA);
                byte[] bArrA2 = k.a();
                if (TextUtils.isEmpty(strA)) {
                    i = 0;
                } else {
                    if (a("3.0.0A0000B0508", strA) != 1) {
                        f.b("Different SDK");
                    } else if ("3.0.0A0000B0508".compareTo(strA) <= 0) {
                        i = 1;
                    }
                    i = 0;
                }
                f.b("read " + i);
                if (i == 0) {
                    h.b(context, bArrA2);
                } else {
                    h.a(context, bArrA);
                }
                h.a(context, h.a(context));
                h.c(context);
                if (i == 0) {
                    h.d(context);
                }
                if (inputStreamB != null) {
                    try {
                        inputStreamB.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            } catch (Throwable th) {
                if (inputStreamB != null) {
                    try {
                        inputStreamB.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            if (inputStreamB != null) {
                try {
                    inputStreamB.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        }
    }

    public static String b() {
        return "5.2.3AR002B0427";
    }

    private void b(Context context, String str, String str2) {
        try {
            Class clsLoadClass = h.a().loadClass(new String(l.b("Y29tLnVuaWNvbS54aWFvd28ubG9naW5jb3JlLlVuaUF1dGhIZWxwZXI=")));
            clsLoadClass.getMethod("init", Context.class, String.class, String.class).invoke(clsLoadClass.getMethod("getInstance", new Class[0]).invoke(clsLoadClass, new Object[0]), context, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            h.d(context);
        }
    }

    public void a(Context context, int i, int i2, final ResultListener resultListener) {
        long jB = j.b(context);
        JSONObject jSONObjectB = g.b(context);
        g.a(context);
        if (i2 == 1 && jSONObjectB != null && g.e()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultCode", 0);
                jSONObject.put("resultMsg", "调用成功");
                String strOptString = jSONObjectB.optString("accessCode");
                String strOptString2 = jSONObjectB.optString("mobile");
                long jOptLong = jSONObjectB.optLong("expires");
                String strOptString3 = jSONObjectB.optString("operatorType");
                long jCurrentTimeMillis = jOptLong - ((System.currentTimeMillis() - jB) / 1000);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("mobile", strOptString2);
                jSONObject2.put("expires", jCurrentTimeMillis);
                jSONObject2.put("accessCode", strOptString);
                jSONObject2.put(RemoteMessageConst.MSGID, i.a("" + System.currentTimeMillis()));
                jSONObject.put("resultData", jSONObject2);
                jSONObject.put("operatorType", strOptString3);
                resultListener.onResult(jSONObject.toString());
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        new a().a(context, i, i2, new a.InterfaceC0136a() { // from class: com.unicom.xiaowo.account.shield.c.c.1
            @Override // com.unicom.xiaowo.account.shield.c.a.InterfaceC0136a
            public void a(String str) {
                try {
                    JSONObject jSONObject3 = new JSONObject(str);
                    int iOptInt = jSONObject3.optInt("resultCode");
                    jSONObject3.optString("resultMsg");
                    if (iOptInt != 0) {
                        com.unicom.xiaowo.account.shield.e.c.a().b();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                resultListener.onResult(str);
            }
        });
    }

    public void a(Context context, String str, String str2) {
        try {
            if (a(context)) {
                b(context, str, str2);
            } else {
                h.d(context);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(boolean z) {
        f.a(z);
    }
}
