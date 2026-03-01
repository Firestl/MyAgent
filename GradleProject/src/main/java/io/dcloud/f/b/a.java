package io.dcloud.f.b;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.dcloud.common.adapter.util.CanvasHelper;
import io.dcloud.common.util.AESUtil;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.internal.sdk.SDK;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static a d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Context f6491a;
    public boolean b = false;

    @SuppressLint({"HandlerLeak"})
    public Handler c = new HandlerC0163a();

    /* JADX INFO: renamed from: io.dcloud.f.b.a$a, reason: collision with other inner class name */
    public class HandlerC0163a extends Handler {
        public HandlerC0163a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                a.this.a();
            } catch (Exception unused) {
            }
            sendEmptyMessageDelayed(1, Double.valueOf((Math.random() + 1.0d) * 1000.0d * 60.0d).longValue());
        }
    }

    public class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                a.this.a();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            sendEmptyMessageDelayed(1, Double.valueOf((Math.random() + 1.0d) * 1000.0d * 60.0d).longValue());
        }
    }

    public a() {
        if (d != null) {
            throw new IllegalStateException();
        }
    }

    private String b() {
        try {
            byte[] bArrDecode2bytes = Base64.decode2bytes(io.dcloud.f.c.b.a(1));
            if (bArrDecode2bytes == null) {
                a(4);
                return "";
            }
            String strDecrypt = AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), bArrDecode2bytes);
            if (strDecrypt == null) {
                a(4);
                return "";
            }
            try {
                Class<?> cls = Class.forName(strDecrypt);
                return (String) ((Map) cls.getMethod("NM_getCustomInfo", new Class[0]).invoke(cls.newInstance(), new Object[0])).get("app_id");
            } catch (Exception unused) {
                Class<?> cls2 = null;
                try {
                    cls2 = Class.forName(AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), Base64.decode2bytes(io.dcloud.f.c.b.a(9))));
                } catch (Exception unused2) {
                }
                Class.forName(AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), Base64.decode2bytes(io.dcloud.f.c.b.a(8))));
                return cls2 == null ? String.valueOf(Math.random()) : "";
            }
        } catch (Exception unused3) {
            return "";
        }
    }

    private String c() {
        byte[] bArrDecode2bytes = Base64.decode2bytes(io.dcloud.f.c.b.a(2));
        if (bArrDecode2bytes == null) {
            a(4);
            return "";
        }
        String strDecrypt = AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), bArrDecode2bytes);
        if (strDecrypt == null) {
            a(4);
            return "";
        }
        try {
            Class<?> cls = Class.forName(strDecrypt);
            Object objInvoke = cls.getMethod("getAppStatus", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (objInvoke != null) {
                return (String) objInvoke.getClass().getMethod("getAPPID", new Class[0]).invoke(objInvoke, new Object[0]);
            }
        } catch (Exception unused) {
        }
        return "";
    }

    private String d() {
        byte[] bArrDecode2bytes = Base64.decode2bytes(io.dcloud.f.c.b.a(10));
        if (bArrDecode2bytes == null) {
            a(4);
            return "";
        }
        String strDecrypt = AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), bArrDecode2bytes);
        if (strDecrypt == null) {
            a(4);
            return "";
        }
        try {
            return (String) Class.forName(strDecrypt).getMethod("getAppId", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return "";
        }
    }

    public static a f() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    public List<String> e() {
        ArrayList arrayList = new ArrayList();
        try {
            ActivityInfo[] activityInfoArr = this.f6491a.getPackageManager().getPackageInfo(this.f6491a.getPackageName(), 1).activities;
            if (activityInfoArr != null) {
                for (ActivityInfo activityInfo : activityInfoArr) {
                    arrayList.add(activityInfo.name);
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public void g() {
        Handler handler = this.c;
        if (handler != null) {
            this.b = false;
            handler.removeMessages(1);
            this.c = null;
        }
    }

    @SuppressLint({"HandlerLeak"})
    public void a(Context context) {
        Handler handler;
        if (!PdrUtil.checkIntl() || LanguageUtil.getDeviceDefLocalLanguage().equalsIgnoreCase(io.dcloud.g.a.b("r`%kf"))) {
            this.f6491a = context;
            try {
                if (SDK.isUniMPSDK()) {
                    return;
                }
                if (Math.abs(Math.random() * 5.0d) == 5.0d && (handler = this.c) != null) {
                    handler.removeMessages(1);
                    this.c = null;
                }
                if (this.c == null) {
                    this.c = new b();
                }
                if (this.c.hasMessages(1)) {
                    return;
                }
                this.c.sendEmptyMessage(1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        String strB;
        String strA;
        String strC;
        String strA2;
        String strD;
        String strA3;
        boolean z;
        boolean z2;
        boolean z3;
        Method method;
        List<String> listE = e();
        List<String> listA = io.dcloud.f.c.b.a();
        if (listE.size() == 0 || listE.contains(io.dcloud.g.a.b(listA.get(1))) || listE.contains(io.dcloud.g.a.b(listA.get(4)))) {
            strB = b();
            strA = a("UNIAD_CSJ_APPID", "csj");
        } else {
            strB = null;
            strA = null;
        }
        if (listE.size() == 0 || listE.contains(io.dcloud.g.a.b(listA.get(0)))) {
            strC = c();
            strA2 = a("UNIAD_GDT_APPID", "gdt");
        } else {
            strC = null;
            strA2 = null;
        }
        if (listE.size() == 0 || listE.contains(io.dcloud.g.a.b(listA.get(2)))) {
            strD = d();
            strA3 = a("UNIAD_KS_APPID", "ks");
        } else {
            strD = null;
            strA3 = null;
        }
        if (TextUtils.isEmpty(strB) || strB.equals(strA) || a(strB, a("csj"))) {
            z = true;
        } else {
            a(6);
            z = false;
        }
        if (TextUtils.isEmpty(strC) || strC.equals(strA2) || a(strC, a("gdt"))) {
            z2 = true;
        } else {
            a(6);
            z2 = false;
        }
        if (TextUtils.isEmpty(strD) || strD.equals(strA3) || a(strD, a("ks"))) {
            z3 = true;
        } else {
            a(6);
            z3 = false;
        }
        if ((z && z2 && z3) || this.b) {
            return;
        }
        try {
            Method[] declaredMethods = Class.forName(AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), Base64.decode2bytes(io.dcloud.f.c.b.a(7)))).getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    method = null;
                    break;
                }
                method = declaredMethods[i];
                if (method.getName().equals("pr")) {
                    break;
                } else {
                    i++;
                }
            }
            HashMap map = new HashMap();
            map.put("t", 1);
            JSONObject jSONObject = new JSONObject();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("r", "1");
                if (!z) {
                    jSONObject.put("csj", jSONObject2);
                }
                if (!z2) {
                    jSONObject.put("gdt", jSONObject2);
                }
                if (!z3) {
                    jSONObject.put("ks", jSONObject2);
                }
            } catch (Exception unused) {
            }
            map.put("rad", jSONObject.toString());
            method.setAccessible(true);
            method.invoke(null, this.f6491a, map);
            this.b = true;
        } catch (Exception unused2) {
        }
    }

    private boolean a(String str, JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                if (str.equals(jSONArray.optString(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private String a(String str, String str2) {
        byte[] bArrDecode2bytes = Base64.decode2bytes(io.dcloud.f.c.b.a(3));
        if (bArrDecode2bytes == null) {
            a(4);
            return "";
        }
        String strDecrypt = AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), bArrDecode2bytes);
        if (strDecrypt == null) {
            a(4);
            return "";
        }
        try {
            return (String) Class.forName(strDecrypt).getMethod("da", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return "";
        }
    }

    private JSONArray a(String str) {
        byte[] bArrDecode2bytes = Base64.decode2bytes(io.dcloud.f.c.b.a(3));
        if (bArrDecode2bytes == null) {
            a(4);
            return null;
        }
        String strDecrypt = AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), bArrDecode2bytes);
        if (strDecrypt == null) {
            a(4);
            return null;
        }
        try {
            return (JSONArray) Class.forName(strDecrypt).getMethod("dah", String.class).invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    private void a(int i) {
        if (this.f6491a == null) {
            return;
        }
        io.dcloud.feature.ui.nativeui.b bVar = new io.dcloud.feature.ui.nativeui.b((Activity) this.f6491a, "");
        TextView textView = new TextView(this.f6491a);
        textView.setAutoLinkMask(1);
        textView.setClickable(true);
        textView.setText(io.dcloud.common.ui.c.a(this.f6491a).a(AESUtil.decrypt(io.dcloud.g.a.c(), io.dcloud.g.a.a(), Base64.decode2bytes(io.dcloud.f.c.b.a(i)))));
        LinearLayout linearLayout = new LinearLayout(this.f6491a);
        linearLayout.addView(textView);
        bVar.a(linearLayout, textView);
        bVar.setDuration(1);
        bVar.setGravity(80, bVar.getXOffset(), bVar.getYOffset());
        int iDip2px = CanvasHelper.dip2px(this.f6491a, 10.0f);
        int iDip2px2 = CanvasHelper.dip2px(this.f6491a, 8.0f);
        linearLayout.setPadding(iDip2px, iDip2px2, iDip2px, iDip2px2);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(iDip2px2);
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-1308622848);
        linearLayout.setBackground(gradientDrawable);
        textView.setGravity(17);
        textView.setTextColor(Color.parseColor("#ffffffff"));
        bVar.show();
    }
}
