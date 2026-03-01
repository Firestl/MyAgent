package io.dcloud.feature.unimp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.sdk.DCSDKInitConfig;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleCloseButtontCallBack;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleMenuButtontCallBack;
import io.dcloud.feature.sdk.Interface.IMenuButtonClickCallBack;
import io.dcloud.feature.sdk.Interface.IOnUniMPEventCallBack;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack;
import io.dcloud.feature.unimp.h.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a implements io.dcloud.feature.unimp.f.a {
    public static io.dcloud.feature.unimp.f.a p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<io.dcloud.feature.unimp.f.c> f6679a;
    public ArrayList<io.dcloud.feature.unimp.f.c> b;
    public ArrayList<a.EnumC0183a> c;
    public Context d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public DCSDKInitConfig f6680e;
    public String f;
    public String g;
    public HashMap<String, IMenuButtonClickCallBack> h = new HashMap<>();
    public HashMap<String, IUniMPOnCloseCallBack> i = new HashMap<>();
    public HashMap<String, IOnUniMPEventCallBack> j = new HashMap<>();
    public IMenuButtonClickCallBack k;
    public IUniMPOnCloseCallBack l;
    public IOnUniMPEventCallBack m;
    public IDCUniMPOnCapsuleCloseButtontCallBack n;
    public IDCUniMPOnCapsuleMenuButtontCallBack o;

    /* JADX INFO: renamed from: io.dcloud.feature.unimp.a$a, reason: collision with other inner class name */
    public class C0181a implements Comparator<io.dcloud.feature.unimp.f.c> {
        public C0181a(a aVar) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(io.dcloud.feature.unimp.f.c cVar, io.dcloud.feature.unimp.f.c cVar2) {
            if (cVar2 == null || cVar == null) {
                return 0;
            }
            return cVar.c().compareTo(cVar2.c());
        }
    }

    private void b(Context context) {
        if (this.c.isEmpty()) {
            return;
        }
        this.b.add(new b(context, this, this.c.remove(0)));
    }

    public static io.dcloud.feature.unimp.f.a e() {
        if (p == null) {
            p = new a();
        }
        return p;
    }

    private void f() {
        for (int i = 0; i < 3; i++) {
            this.c.add(io.dcloud.feature.unimp.h.a.a(i));
        }
    }

    private io.dcloud.feature.unimp.f.c g(String str) {
        for (io.dcloud.feature.unimp.f.c cVar : this.f6679a) {
            if (!TextUtils.isEmpty(cVar.getAppid()) && str.equals(cVar.getAppid())) {
                return cVar;
            }
        }
        return null;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(Context context, DCSDKInitConfig dCSDKInitConfig) {
        this.f6679a = new ArrayList<>();
        this.b = new ArrayList<>();
        this.c = new ArrayList<>();
        this.d = context;
        this.f6680e = dCSDKInitConfig;
        new Handler(Looper.getMainLooper());
        f();
        b(context);
        if (Build.VERSION.SDK_INT >= 26) {
            LanguageUtil.updateContextLanguageAfterO(context, true);
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public JSONObject c(String str) {
        JSONObject jSONObjectOptJSONObject;
        String str2 = this.d.getFilesDir().getAbsolutePath() + "/apps/" + str + "/" + BaseInfo.APP_WWW_FS_DIR + BaseInfo.sConfigXML;
        if (BaseInfo.SyncDebug || DHFile.hasFile()) {
            str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + this.d.getPackageName() + "/apps/" + str + "/" + BaseInfo.APP_WWW_FS_DIR + BaseInfo.sConfigXML;
        }
        JSONObject configData = PdrUtil.getConfigData(this.d.getApplicationContext(), str, str2, false);
        if (configData == null || !configData.has("version") || (jSONObjectOptJSONObject = configData.optJSONObject("version")) == null) {
            return null;
        }
        return jSONObjectOptJSONObject;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public String d() {
        return this.f;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void onUniMPEventReceive(String str, String str2, Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        if (this.j.containsKey(str)) {
            this.j.get(str).onUniMPEventReceive(str, str2, obj, dCUniMPJSCallback);
        }
        IOnUniMPEventCallBack iOnUniMPEventCallBack = this.m;
        if (iOnUniMPEventCallBack != null) {
            iOnUniMPEventCallBack.onUniMPEventReceive(str, str2, obj, dCUniMPJSCallback);
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public boolean d(String str) {
        return g(str) != null;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public String f(String str) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG != null) {
            return cVarG.getCurrentPageUrl();
        }
        return null;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public boolean e(String str) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG == null) {
            return false;
        }
        cVarG.d();
        return true;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void b(String str, String str2) {
        if (this.h.containsKey(str)) {
            this.h.get(str).onClick(str, str2);
        }
        IMenuButtonClickCallBack iMenuButtonClickCallBack = this.k;
        if (iMenuButtonClickCallBack != null) {
            iMenuButtonClickCallBack.onClick(str, str2);
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void c(String str, String str2) {
        this.f = str;
        this.g = str2;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public DCSDKInitConfig b() {
        return this.f6680e;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public String c() {
        return this.g;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public boolean b(String str) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG == null) {
            return false;
        }
        this.f6679a.remove(cVarG);
        this.b.add(cVarG);
        Collections.sort(this.b, new C0181a(this));
        return cVarG.a();
    }

    @Override // io.dcloud.feature.unimp.f.a
    public IUniMP a(String str, String str2, String str3, JSONObject jSONObject) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG == null) {
            cVarG = a(this.d);
            if (this.f6679a.contains(cVarG)) {
                this.f6679a.remove(cVarG);
            }
            this.f6679a.add(cVarG);
        }
        cVarG.a("close", this.n != null);
        cVarG.a(AbsoluteConst.EVENTS_MENU, this.o != null);
        cVarG.a(str, str2, str3, jSONObject);
        return cVarG.m762clone();
    }

    @Override // io.dcloud.feature.unimp.f.a
    public IUniMP a(JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            return null;
        }
        try {
            String strOptString = jSONObject.optString("appid");
            String strOptString2 = jSONObject.optString("redirectPath");
            String strOptString3 = jSONObject.optString("arguments");
            String strOptString4 = jSONObject.optString("appInfo");
            JSONObject jSONObject3 = new JSONObject();
            if (jSONObject.has("name")) {
                jSONObject3.put("name", jSONObject.optString("name"));
            }
            String strOptString5 = jSONObject.has("splash_View") ? jSONObject.optString("splash_View") : "io.dcloud.feature.internal.splash.UniMPDefSplash";
            if (jSONObject.has("path")) {
                String strOptString6 = jSONObject.optString("path");
                if (!PdrUtil.isEmpty(strOptString6)) {
                    jSONObject3.put("path", PdrUtil.standardizedURL(BaseInfo.sCacheFsAppsPath + BaseInfo.sDefaultBootApp + "/www/", strOptString6));
                }
            }
            if (jSONObject.has(com.heytap.mcssdk.d.q)) {
                jSONObject3.put(com.heytap.mcssdk.d.q, jSONObject.getString(com.heytap.mcssdk.d.q));
            }
            if (jSONObject.has("icon")) {
                String string = jSONObject.getString("icon");
                if (!PdrUtil.isNetPath(string)) {
                    string = PdrUtil.standardizedURL(BaseInfo.sCacheFsAppsPath + BaseInfo.sDefaultBootApp + "/www/", string);
                }
                jSONObject3.put("icon", string);
            }
            if (!TextUtils.isEmpty(strOptString3)) {
                jSONObject2 = new JSONObject(strOptString3);
            } else {
                jSONObject2 = new JSONObject();
            }
            jSONObject2.put("unimp_info", jSONObject3);
            if (!TextUtils.isEmpty(strOptString4)) {
                jSONObject2.put("host_unimp_info", strOptString4);
            }
            a(strOptString, strOptString5, strOptString2, jSONObject2);
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private io.dcloud.feature.unimp.f.c a(Context context) {
        io.dcloud.feature.unimp.f.c next;
        if (this.c.isEmpty()) {
            if (this.b.isEmpty() && !this.f6679a.isEmpty()) {
                if (this.f6679a.size() > 1) {
                    Iterator<io.dcloud.feature.unimp.f.c> it = this.f6679a.iterator();
                    while (it.hasNext()) {
                        next = it.next();
                        if (next.getState() != 1) {
                            break;
                        }
                    }
                    next = null;
                } else {
                    next = null;
                }
                return next == null ? this.f6679a.remove(0) : next;
            }
            io.dcloud.feature.unimp.f.c cVarRemove = this.b.remove(0);
            if (this.b.size() != 0) {
                return cVarRemove;
            }
            b(context);
            return cVarRemove;
        }
        if (this.b.isEmpty()) {
            b(context);
        }
        io.dcloud.feature.unimp.f.c cVarRemove2 = this.b.remove(0);
        if (this.b.size() != 0) {
            return cVarRemove2;
        }
        b(context);
        return cVarRemove2;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public synchronized void a() {
        Iterator<io.dcloud.feature.unimp.f.c> it = this.f6679a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(IMenuButtonClickCallBack iMenuButtonClickCallBack) {
        this.k = iMenuButtonClickCallBack;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(IUniMPOnCloseCallBack iUniMPOnCloseCallBack) {
        this.l = iUniMPOnCloseCallBack;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(IOnUniMPEventCallBack iOnUniMPEventCallBack) {
        this.m = iOnUniMPEventCallBack;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(IDCUniMPOnCapsuleCloseButtontCallBack iDCUniMPOnCapsuleCloseButtontCallBack) {
        this.n = iDCUniMPOnCapsuleCloseButtontCallBack;
        Iterator<io.dcloud.feature.unimp.f.c> it = this.f6679a.iterator();
        while (it.hasNext()) {
            it.next().a("close", this.n != null);
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(IDCUniMPOnCapsuleMenuButtontCallBack iDCUniMPOnCapsuleMenuButtontCallBack) {
        this.o = iDCUniMPOnCapsuleMenuButtontCallBack;
        Iterator<io.dcloud.feature.unimp.f.c> it = this.f6679a.iterator();
        while (it.hasNext()) {
            it.next().a(AbsoluteConst.EVENTS_MENU, this.o != null);
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(io.dcloud.feature.unimp.f.c cVar, String str) {
        if (this.i.containsKey(str)) {
            this.i.get(str).onClose(str);
        }
        IUniMPOnCloseCallBack iUniMPOnCloseCallBack = this.l;
        if (iUniMPOnCloseCallBack != null) {
            iUniMPOnCloseCallBack.onClose(str);
        }
        this.i.remove(str);
        this.j.remove(str);
        this.h.remove(str);
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(String str, String str2) {
        IDCUniMPOnCapsuleCloseButtontCallBack iDCUniMPOnCapsuleCloseButtontCallBack;
        str.hashCode();
        if (str.equals(AbsoluteConst.EVENTS_MENU)) {
            IDCUniMPOnCapsuleMenuButtontCallBack iDCUniMPOnCapsuleMenuButtontCallBack = this.o;
            if (iDCUniMPOnCapsuleMenuButtontCallBack != null) {
                iDCUniMPOnCapsuleMenuButtontCallBack.menuButtonClicked(str2);
                return;
            }
            return;
        }
        if (str.equals("close") && (iDCUniMPOnCapsuleCloseButtontCallBack = this.n) != null) {
            iDCUniMPOnCapsuleCloseButtontCallBack.closeButtonClicked(str2);
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public void a(String str, String str2, String str3, Object obj, boolean z) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG != null) {
            cVarG.a(str, str2, str3, obj, z);
        }
    }

    @Override // io.dcloud.feature.unimp.f.a
    public boolean a(String str) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG == null) {
            return false;
        }
        cVarG.b();
        return true;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public boolean a(String str, String str2, Object obj) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG != null) {
            return cVarG.sendUniMPEvent(str2, obj);
        }
        return false;
    }

    @Override // io.dcloud.feature.unimp.f.a
    public boolean a(String str, Intent intent, int i, int i2) {
        io.dcloud.feature.unimp.f.c cVarG = g(str);
        if (cVarG != null) {
            return cVarG.a(str, intent, i, i2);
        }
        return false;
    }
}
