package io.dcloud.feature.unimp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.huawei.hms.actions.SearchIntents;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.sdk.DCSDKInitConfig;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack;
import io.dcloud.feature.unimp.h.a;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b implements io.dcloud.feature.unimp.f.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6681a;
    public a.EnumC0183a b;
    public DCSDKInitConfig d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public l f6682e;
    public String f;
    public io.dcloud.feature.unimp.f.a g;
    public Message h;
    public IUniMPOnCloseCallBack o;
    public IUniMP q;
    public boolean i = false;
    public boolean j = false;
    public int k = 0;
    public ServiceConnection l = new c();
    public Handler m = new d();
    public io.dcloud.feature.unimp.f.b n = new e();
    public boolean p = false;
    public UniMPBinder c = new UniMPBinder();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6683a;

        public a(Bundle bundle) {
            this.f6683a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            if (bVar.g == null || bVar.k == 3) {
                return;
            }
            b.this.g.a(this.f6683a.getString("type"), this.f6683a.getString("appid"));
        }
    }

    /* JADX INFO: renamed from: io.dcloud.feature.unimp.b$b, reason: collision with other inner class name */
    public class RunnableC0182b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6684a;

        public RunnableC0182b(Bundle bundle) {
            this.f6684a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b.this.k = this.f6684a.getInt("state");
            } catch (Exception unused) {
            }
        }
    }

    public class c implements ServiceConnection {
        public c() {
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            b.this.c.b();
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                b.this.c.a(iBinder, b.this.d.getDefaultMenuButton(), b.this.g.d(), b.this.g.c(), b.this.n);
                if (b.this.f6682e != null) {
                    Message message = new Message();
                    message.what = 1000;
                    b.this.m.sendMessage(message);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            b.this.c.b();
        }
    }

    public class d extends Handler {
        public d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1000) {
                if (i == 2000) {
                    b.this.f();
                    Intent intent = (Intent) message.obj;
                    if (b.this.k == 3) {
                        intent.putExtra("isPreUniJS", true);
                    }
                    b.this.f6681a.startActivity(intent);
                    b.this.k = 1;
                }
            } else if (b.this.f6682e != null) {
                b bVar = b.this;
                bVar.a(bVar.f6682e.f6694a, b.this.f6682e.b, b.this.f6682e.c, b.this.f6682e.d);
            }
            super.handleMessage(message);
        }
    }

    public class e implements io.dcloud.feature.unimp.f.b {
        public e() {
        }

        @Override // io.dcloud.feature.unimp.f.b
        public void callBack(String str, Bundle bundle) {
            b.this.a(str, bundle);
        }
    }

    public class f implements IUniMPOnCloseCallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6688a;
        public final /* synthetic */ Message b;

        public f(String str, Message message) {
            this.f6688a = str;
            this.b = message;
        }

        @Override // io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack
        public void onClose(String str) {
            if (this.f6688a.equals(str)) {
                b.this.m.sendMessage(this.b);
            }
        }
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6689a;

        public g(Bundle bundle) {
            this.f6689a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.g.b(this.f6689a.getString("appid"), this.f6689a.getString("id"));
        }
    }

    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6690a;

        public h(Bundle bundle) {
            this.f6690a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            String string = this.f6690a.getString("appid");
            IUniMPOnCloseCallBack iUniMPOnCloseCallBack = b.this.o;
            if (iUniMPOnCloseCallBack != null) {
                iUniMPOnCloseCallBack.onClose(string);
            }
            b bVar = b.this;
            if (bVar.h != null) {
                bVar.m.sendMessage(b.this.h);
                b.this.h = null;
            }
            b.this.p = false;
            b.this.k = 3;
            b bVar2 = b.this;
            bVar2.g.a(bVar2, string);
        }
    }

    public class i implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6691a;

        public i(b bVar, Bundle bundle) {
            this.f6691a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6691a.getString("appid");
        }
    }

    public class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6692a;

        public j(Bundle bundle) {
            this.f6692a = bundle;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Runnable
        public void run() {
            DCUniMPJSCallback dCUniMPJSCallback;
            String string = this.f6692a.getString("event");
            String string2 = this.f6692a.getString("data");
            String string3 = this.f6692a.getString("dataType");
            if (this.f6692a.containsKey("instanceId")) {
                dCUniMPJSCallback = new DCUniMPJSCallback(b.this.f, this.f6692a.getString("instanceId"), this.f6692a.getString(SDK.UNIMP_EVENT_CALLBACKID));
            } else {
                dCUniMPJSCallback = null;
            }
            if (b.this.g != null) {
                Object obj = string2;
                if (string3.equals("JSON")) {
                    obj = JSON.parse(string2);
                }
                b bVar = b.this;
                bVar.g.onUniMPEventReceive(bVar.f, string, obj, dCUniMPJSCallback);
            }
        }
    }

    public class k implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6693a;

        public k(Bundle bundle) {
            this.f6693a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.g != null) {
                try {
                    b.this.g.a(new JSONObject(this.f6693a.getString("op")));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6694a;
        public String b;
        public String c;
        public JSONObject d;

        public l(b bVar) {
        }
    }

    public b(Context context, io.dcloud.feature.unimp.f.a aVar, a.EnumC0183a enumC0183a) {
        this.f6681a = context;
        this.g = aVar;
        this.d = aVar.b();
        this.b = enumC0183a;
        e();
    }

    @Override // io.dcloud.feature.unimp.f.c
    public String getAppid() {
        return this.f;
    }

    @Override // io.dcloud.feature.unimp.f.c
    public String getCurrentPageUrl() {
        if (this.c.a() == null) {
            return null;
        }
        try {
            return this.c.a().getCurrentPageUrl();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // io.dcloud.feature.unimp.f.c
    public int getState() {
        return this.k;
    }

    @Override // io.dcloud.feature.unimp.f.c
    public boolean sendUniMPEvent(String str, Object obj) {
        if (this.c.a() == null || TextUtils.isEmpty(this.f)) {
            Logger.e("DCUniMPSDK", "sendUniMPEvent fail  Communication lost");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("event", str);
        bundle.putString("appid", this.f);
        bundle.putString("dataType", "String");
        if (obj instanceof String) {
            bundle.putString("data", String.valueOf(obj));
        } else if (obj instanceof JSON) {
            bundle.putString("data", ((JSON) obj).toJSONString());
            bundle.putString("dataType", "JSON");
        } else if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            bundle.putString("dataType", "JSON");
            bundle.putString("data", obj.toString());
        } else {
            bundle.putString("data", obj.toString());
        }
        try {
            this.c.a().execute("sendUniMPEvent", bundle);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // io.dcloud.feature.unimp.f.c
    public boolean b() {
        if (!this.d.isEnableBackground()) {
            return false;
        }
        int i2 = this.k;
        if ((i2 != 1 && i2 != 2) || this.c.a() == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClassName(this.f6681a, this.d.isEnableBackground() ? this.b.b() : this.b.f());
        intent.putExtra("appid", this.f);
        intent.putExtra(IntentConst.START_FROM_TO_CLASS, this.d.isEnableBackground() ? this.b.a() : this.b.e());
        intent.setFlags(268435456);
        Message message = new Message();
        message.obj = intent;
        message.what = 2000;
        this.m.sendMessage(message);
        return true;
    }

    @Override // io.dcloud.feature.unimp.f.c
    public a.EnumC0183a c() {
        return this.b;
    }

    @Override // io.dcloud.feature.unimp.f.c
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public IUniMP m762clone() {
        IUniMP iUniMP = this.q;
        if (iUniMP != null) {
            try {
                if (iUniMP.getAppid().equals(this.f)) {
                    return this.q;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        io.dcloud.feature.unimp.g gVar = new io.dcloud.feature.unimp.g(this.f, this.g);
        this.q = gVar;
        return gVar;
    }

    @Override // io.dcloud.feature.unimp.f.c
    public boolean d() {
        if (this.k != 1 || !this.d.isEnableBackground() || this.c.a() == null) {
            return false;
        }
        this.k = 2;
        Bundle bundle = new Bundle();
        bundle.putString("appid", this.f);
        try {
            this.c.a().execute("hideApp", bundle);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void e() {
        if (this.c.a() == null) {
            Intent intent = new Intent();
            intent.setClassName(this.f6681a, this.b.d());
            this.f6681a.bindService(intent, this.l, 1);
        }
    }

    public void f() {
        if (this.c.a() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(AbsoluteConst.EVENTS_MENU, this.i);
            bundle.putBoolean("close", this.j);
            try {
                this.c.a().execute("setCapsubeClick", bundle);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // io.dcloud.feature.unimp.f.c
    public void a(String str, String str2, String str3, JSONObject jSONObject) {
        String strSubstring;
        if (this.c.a() == null) {
            e();
            if (this.f6682e == null) {
                this.f6682e = new l(this);
            }
            l lVar = this.f6682e;
            lVar.f6694a = str;
            lVar.b = str2;
            lVar.c = str3;
            lVar.d = jSONObject;
            return;
        }
        try {
            this.f = str;
            Intent intent = new Intent();
            if (!TextUtils.isEmpty(str3)) {
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                int iIndexOf = str3.indexOf(Operators.CONDITION_IF_STRING);
                if (iIndexOf > 1) {
                    String strSubstring2 = str3.substring(0, iIndexOf);
                    strSubstring = str3.substring(iIndexOf + 1);
                    str3 = strSubstring2;
                } else {
                    strSubstring = "";
                }
                if (str3.startsWith("/")) {
                    str3 = str3.substring(1);
                }
                jSONObject3.put(SearchIntents.EXTRA_QUERY, strSubstring);
                jSONObject3.put("path", str3);
                jSONObject2.put("arguments", jSONObject3);
                intent.putExtra(IntentConst.UNIMP_DIRECT_DATA, jSONObject2.toString());
            }
            if (jSONObject != null) {
                if (jSONObject.has("host_unimp_info")) {
                    intent.putExtra("host_unimp_info", (String) jSONObject.remove("host_unimp_info"));
                }
                JSONObject jSONObject4 = jSONObject.has("unimp_info") ? (JSONObject) jSONObject.remove("unimp_info") : null;
                intent.putExtra(IntentConst.UNIMP_RUN_ARGUMENTS, jSONObject.toString());
                if (jSONObject4 != null) {
                    intent.putExtra(IntentConst.UNIMP_APP_INFO, jSONObject4.toString());
                }
            }
            intent.setClassName(this.f6681a, this.d.isEnableBackground() ? this.d.isUniMPFromRecents() ? this.b.b() : this.b.c() : this.b.f());
            intent.putExtra("appid", str);
            intent.putExtra("isCapsule", this.d.isCapsule());
            intent.putExtra(IntentConst.START_FROM_TO_CLASS, this.d.isEnableBackground() ? this.b.a() : this.b.e());
            intent.setFlags(268435456);
            if (!PdrUtil.isEmpty(str2)) {
                intent.putExtra(io.dcloud.feature.sdk.b.h, str2);
            }
            Message message = new Message();
            message.obj = intent;
            message.what = 2000;
            if (this.p) {
                this.h = message;
                return;
            }
            String runningAppid = this.c.a().getRunningAppid();
            if (!TextUtils.isEmpty(runningAppid) && !str.endsWith(runningAppid)) {
                a(runningAppid, new f(runningAppid, message));
            } else {
                this.m.sendMessage(message);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, IUniMPOnCloseCallBack iUniMPOnCloseCallBack) {
        a(str);
        this.o = iUniMPOnCloseCallBack;
    }

    public void a(String str) {
        if (this.c.a() != null) {
            try {
                this.p = true;
                this.c.a().stopApp(str);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // io.dcloud.feature.unimp.f.c
    public boolean a() {
        if (this.c.a() == null) {
            return false;
        }
        try {
            this.p = true;
            this.c.a().closeCurrentApp();
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public synchronized void a(String str, Bundle bundle) {
        if (this.m != null) {
            str.hashCode();
            switch (str) {
                case "unimp_on_state":
                    this.m.post(new RunnableC0182b(bundle));
                    break;
                case "unimp_capsule_button_click":
                    this.m.post(new a(bundle));
                    break;
                case "unimp_js_to_native":
                    this.m.post(new j(bundle));
                    break;
                case "uni_oncloseapp":
                    this.m.postDelayed(new h(bundle), 300L);
                    break;
                case "TITLE_BAR_MENU_CLICK":
                    this.m.post(new g(bundle));
                    break;
                case "uni_onOpenApp":
                    this.m.post(new i(this, bundle));
                    break;
                case "open_unimp":
                    this.m.post(new k(bundle));
                    break;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    @Override // io.dcloud.feature.unimp.f.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.Object r6, boolean r7) {
        /*
            r2 = this;
            boolean r3 = r6 instanceof java.lang.String
            r0 = 1
            r1 = 0
            if (r3 == 0) goto Lc
            java.lang.String r3 = java.lang.String.valueOf(r6)
        La:
            r0 = 0
            goto L27
        Lc:
            boolean r3 = r6 instanceof com.alibaba.fastjson.JSON
            if (r3 == 0) goto L17
            com.alibaba.fastjson.JSON r6 = (com.alibaba.fastjson.JSON) r6
            java.lang.String r3 = r6.toJSONString()
            goto L27
        L17:
            boolean r3 = r6 instanceof org.json.JSONObject
            if (r3 != 0) goto L23
            boolean r3 = r6 instanceof org.json.JSONArray
            if (r3 == 0) goto L20
            goto L23
        L20:
            java.lang.String r3 = ""
            goto La
        L23:
            java.lang.String r3 = r6.toString()
        L27:
            io.dcloud.feature.unimp.UniMPBinder r6 = r2.c
            io.dcloud.feature.sdk.IDCUniMPServer r6 = r6.a()
            if (r6 == 0) goto L5d
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            java.lang.String r1 = "instanceId"
            r6.putString(r1, r4)
            java.lang.String r4 = "callbackId"
            r6.putString(r4, r5)
            java.lang.String r4 = "isKeepAlive"
            r6.putBoolean(r4, r7)
            java.lang.String r4 = "data"
            r6.putString(r4, r3)
            java.lang.String r3 = "isJson"
            r6.putBoolean(r3, r0)
            io.dcloud.feature.unimp.UniMPBinder r3 = r2.c     // Catch: android.os.RemoteException -> L59
            io.dcloud.feature.sdk.IDCUniMPServer r3 = r3.a()     // Catch: android.os.RemoteException -> L59
            java.lang.String r4 = "uniMPEventToJS"
            r3.execute(r4, r6)     // Catch: android.os.RemoteException -> L59
            goto L5d
        L59:
            r3 = move-exception
            r3.printStackTrace()
        L5d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.unimp.b.a(java.lang.String, java.lang.String, java.lang.String, java.lang.Object, boolean):void");
    }

    @Override // io.dcloud.feature.unimp.f.c
    public void a(String str, boolean z) {
        str.hashCode();
        if (str.equals(AbsoluteConst.EVENTS_MENU)) {
            this.i = z;
        } else if (!str.equals("close")) {
            return;
        } else {
            this.j = z;
        }
        f();
    }

    @Override // io.dcloud.feature.unimp.f.c
    public boolean a(String str, Intent intent, int i2, int i3) {
        if (this.c.a() != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, intent);
            bundle.putInt("enterAnim", i2);
            bundle.putInt("exitAnim", i3);
            try {
                return Boolean.valueOf(this.c.a().execute("startActivityForUniMPTask", bundle)).booleanValue();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
