package supwisdom;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.igexin.sdk.PushConsts;
import com.synjones.mobilegroup.libofflinecodesdk.beans.BarcodeParBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.CodeBarPayInfoBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ConfigSDKBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeInitResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetBarCodeBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetOfflineCodeResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetRequestLogBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetSdkListBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JniResponseBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JniResponseCreateCodeBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JsonOperate;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineAndOfflineListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineCodeListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetPayListListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OffInitListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.bt1;
import synjones.commerce.utils.JniQrCodeSign;

/* JADX INFO: loaded from: classes.dex */
public class ai implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public h f6898a;
    public h b;
    public h c;
    public String f;
    public List<String> d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6899e = false;
    public int g = 1;
    public final Runnable h = new a();

    public class b extends h {
        public String b;

        public b(String str) {
            super(ai.this, null);
            this.b = str + "";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public GetOfflineCodeResult a(boolean z) {
            ExternalCodeResultEnum externalCodeResultEnum;
            JniResponseBean.MessageBean messageBean;
            GetOfflineCodeResult getOfflineCodeResult;
            JniResponseCreateCodeBean.MessageBean messageBean2;
            JniResponseCreateCodeBean.MessageBean messageBean3;
            bq1.f().c();
            if (bq1.f().c().size() == 0) {
                ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_GETOFFLINE_GET_DATA_NOPARAM;
                return new GetOfflineCodeResult(false, externalCodeResultEnum2.code, externalCodeResultEnum2.msg, "", "");
            }
            CodeBarPayInfoBean.DataBean dataBean = bq1.f().c().get(this.b + "");
            if (dataBean == null) {
                ExternalCodeResultEnum externalCodeResultEnum3 = ExternalCodeResultEnum.ERROR_GETOFFLINE_TYPEID;
                return new GetOfflineCodeResult(false, externalCodeResultEnum3.code, externalCodeResultEnum3.msg, "", "");
            }
            bq1 bq1VarF = bq1.f();
            HashMap map = null;
            if (!TextUtils.isEmpty(bq1VarF.f9798a.getString("mBarcodeParMap", ""))) {
                String string = bq1VarF.f9798a.getString("mBarcodeParMap", "");
                map = new HashMap();
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        map.put(next, (BarcodeParBean) hp1.a(jSONObject.getJSONObject(next).toString(), BarcodeParBean.class));
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            if (map == null) {
                map = new HashMap();
            }
            map.toString();
            BarcodeParBean barcodeParBean = (BarcodeParBean) map.get(this.b);
            ExternalCodeResultEnum externalCodeResultEnum4 = ExternalCodeResultEnum.ERROR_GETOFFLINE_GET_DATA_SO_SUCCESS;
            if (TextUtils.isEmpty(dataBean.voucher)) {
                externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETOFFLINE_GET_DATA_SO_VOUCHER;
            } else {
                String string2 = ((BarcodeParBean.DataBean) barcodeParBean.data).toJSONObject().toString();
                String str = dataBean.voucher;
                String strD = bq1.f().d();
                String.format(" barCodeParToJni数据准备完成：qrcode_json=%s, voucher_no=%s, use=%d, filepath=%s", string2, str, Integer.valueOf(ai.this.g), strD);
                int i = JniQrCodeSign.f10049a;
                JniResponseBean jniResponseBean = (JniResponseBean) hp1.b(JniQrCodeSign.a.f10050a.QqrInitParam(string2, str, ai.this.g, strD), JniResponseBean.class);
                externalCodeResultEnum = (jniResponseBean == null || (messageBean = jniResponseBean.message) == null || messageBean.retcode != 0) ? ExternalCodeResultEnum.ERROR_GETOFFLINE_GET_DATA_SO_FAIL : externalCodeResultEnum4;
            }
            if (!externalCodeResultEnum4.code.equals(externalCodeResultEnum.code)) {
                return new GetOfflineCodeResult(false, externalCodeResultEnum.code, externalCodeResultEnum.msg, "", "");
            }
            String str2 = this.b;
            String str3 = ((BarcodeParBean.DataBean) barcodeParBean.data).obj.PID;
            int i2 = Integer.parseInt(dataBean.account);
            int iB = ai.this.g == 1 ? bq1.f().b(str2) : 1;
            String.format("<paymentCode>createNewOfflineCode:params: %s, %s, %s", Integer.valueOf(i2), Integer.valueOf(iB), str3);
            int i3 = JniQrCodeSign.f10049a;
            JniResponseCreateCodeBean jniResponseCreateCodeBean = (JniResponseCreateCodeBean) hp1.b(JniQrCodeSign.a.f10050a.OqrGetQrcode(i2, iB, str3, bq1.f().d()), JniResponseCreateCodeBean.class);
            if (jniResponseCreateCodeBean == null || (messageBean3 = jniResponseCreateCodeBean.message) == null || messageBean3.retcode != 0) {
                ExternalCodeResultEnum externalCodeResultEnum5 = ExternalCodeResultEnum.ERROR_GETOFFLINE_GET_DATA_JNI_FAIL;
                String str4 = externalCodeResultEnum5.code;
                StringBuilder sb = new StringBuilder();
                sb.append(externalCodeResultEnum5.msg);
                sb.append(",");
                sb.append((jniResponseCreateCodeBean == null || (messageBean2 = jniResponseCreateCodeBean.message) == null) ? "未知错误" : messageBean2.retmsg);
                getOfflineCodeResult = new GetOfflineCodeResult(false, str4, sb.toString(), "", "");
            } else {
                ExternalCodeResultEnum externalCodeResultEnum6 = ExternalCodeResultEnum.ERROR_NO;
                getOfflineCodeResult = new GetOfflineCodeResult(true, externalCodeResultEnum6.code, externalCodeResultEnum6.msg, "", messageBean3.qrcode_data);
            }
            aq1.d().a("saved_so_offcode", getOfflineCodeResult.qrCode);
            String str5 = ((ConfigSDKBean.DataBean) yp1.e().c().data).apiVersion;
            if (!getOfflineCodeResult.success || z) {
                return getOfflineCodeResult;
            }
            getOfflineCodeResult.qrCode = ai.a(ai.this, "00000000000000000000", getOfflineCodeResult.qrCode);
            return getOfflineCodeResult;
        }
    }

    public abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6924a;

        public h(ai aiVar) {
        }

        public /* synthetic */ h(ai aiVar, a aVar) {
            this(aiVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String a(ai aiVar, String str, String str2) {
        aiVar.getClass();
        if ("1".equals((yp1.e().c() == null || yp1.e().c().data == 0) ? "0" : ((ConfigSDKBean.DataBean) yp1.e().c().data).apiVersion)) {
            return str + "SP" + pp1.b.a(qp1.a(str2));
        }
        return str + "SO" + str2;
    }

    public final synchronized void b() {
        er1.a(this.h, 0L);
    }

    public class e extends h {
        public String b;
        public boolean c;
        public Runnable d;

        public e(String str, boolean z) {
            super(ai.this, null);
            this.d = new a();
            this.b = str;
            this.c = z;
        }

        public void a() {
            er1.b.schedule(new cr1(this.d), 0L, TimeUnit.MILLISECONDS);
        }

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                String strB = dq1.c().b();
                ri riVar = new ri();
                String str = e.this.b;
                C0207a c0207a = new C0207a();
                xs1 xs1VarB = xs1.b("application/json;charset=UTF-8");
                zs1 zs1VarB = fp1.c().b();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("code", PushConsts.SEND_MESSAGE_ERROR);
                    jSONObject.put("data", str);
                    jSONObject.put("token", strB);
                } catch (Exception unused) {
                }
                ct1 ct1VarCreate = ct1.create(xs1VarB, jSONObject.toString());
                bt1.a aVar = new bt1.a();
                aVar.b(bq1.f().e() + "/berserker-log/oplog");
                aVar.c(ct1VarCreate);
                zs1VarB.a(aVar.a()).a(new qi(riVar, c0207a));
            }

            /* JADX INFO: renamed from: supwisdom.ai$e$a$a, reason: collision with other inner class name */
            public class C0207a extends gp1<GetRequestLogBean> {
                public C0207a() {
                }

                @Override // supwisdom.gp1
                public void a(CommonBaseResponse commonBaseResponse) {
                    if (e.this.c) {
                        aq1.d().a(e.this.b);
                        ai.this.a();
                    }
                }

                @Override // supwisdom.gp1
                public void b(Throwable th) {
                    e eVar = e.this;
                    if (!eVar.c) {
                        aq1.d().b(e.this.b);
                        return;
                    }
                    ai.this.d.add(eVar.b);
                    aq1.d().a(e.this.b);
                    ai.this.a();
                }

                @Override // supwisdom.gp1
                public Class<GetRequestLogBean> a() {
                    return GetRequestLogBean.class;
                }
            }
        }
    }

    public void a(String str, boolean z) {
        new e(str, z).a();
    }

    public class g extends h {
        public OffInitListener b;
        public final String c;
        public final String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public GetSdkListBean f6917e;
        public final Runnable f;
        public final Runnable g;
        public final Runnable h;
        public final Runnable i;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g gVar = g.this;
                synchronized (gVar) {
                    if (gVar.f6924a) {
                        return;
                    }
                    er1.a(gVar.g, 0L);
                }
            }
        }

        public class d implements Runnable {
            public d() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                JniResponseBean.MessageBean messageBean;
                g gVar = g.this;
                GetSdkListBean getSdkListBean = gVar.f6917e;
                if (getSdkListBean == null || getSdkListBean.code != 200 || getSdkListBean.data == 0) {
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_SDKINIT_FAIL_GERSDK_DATA_NULL;
                    g.a(gVar, externalCodeResultEnum.code, externalCodeResultEnum.msg);
                    return;
                }
                String strD = bq1.f().d();
                if (TextUtils.isEmpty(strD)) {
                    g gVar2 = g.this;
                    ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_SDKINIT_FAIL_SOFILE_ERROR;
                    g.a(gVar2, externalCodeResultEnum2.code, externalCodeResultEnum2.msg);
                    return;
                }
                int i = JniQrCodeSign.f10049a;
                JniQrCodeSign jniQrCodeSign = JniQrCodeSign.a.f10050a;
                GetSdkListBean.DataBean dataBean = (GetSdkListBean.DataBean) g.this.f6917e.data;
                JniResponseBean jniResponseBean = (JniResponseBean) hp1.b(jniQrCodeSign.OqrSetPackageName(dataBean.schoolcode, dataBean.sdknamelist, strD), JniResponseBean.class);
                if (jniResponseBean == null || (messageBean = jniResponseBean.message) == null || messageBean.retcode != 0) {
                    String errorString = jniResponseBean == null ? "无数据" : jniResponseBean.toErrorString();
                    g gVar3 = g.this;
                    ExternalCodeResultEnum externalCodeResultEnum3 = ExternalCodeResultEnum.ERROR_SDKINIT_FAIL_SETPACKAGENAME_JNI_ERROR;
                    g.a(gVar3, externalCodeResultEnum3.code, externalCodeResultEnum3.msg + "," + errorString);
                    return;
                }
                g gVar4 = g.this;
                synchronized (gVar4) {
                    if (gVar4.f6924a) {
                        return;
                    }
                    gVar4.f6924a = true;
                    yp1.e().a("saved_initstate", true);
                    synchronized (ai.this) {
                        ai aiVar = ai.this;
                        if (aiVar.f6898a != null) {
                            aiVar.f6898a = null;
                        }
                        if (aiVar.c != null) {
                            aiVar.c = null;
                        }
                    }
                    new Handler(Looper.getMainLooper()).post(new hi(gVar4));
                }
            }
        }

        public g(String str, String str2, String str3, OffInitListener offInitListener) {
            super(ai.this, null);
            this.f = new a();
            this.g = new b();
            this.h = new c();
            this.i = new d();
            this.c = str;
            this.d = str3;
            this.b = offInitListener;
        }

        public void a() {
            synchronized (this) {
                this.f6924a = false;
            }
            synchronized (this) {
                if (this.f6924a) {
                    return;
                }
                er1.a(this.f, 0L);
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ((dp1) fp1.a(dp1.class)).b().a(new a());
            }

            public class a extends gp1<GetSdkListBean> {
                public a() {
                }

                @Override // supwisdom.gp1
                public void a(CommonBaseResponse commonBaseResponse) {
                    g gVar = g.this;
                    gVar.f6917e = (GetSdkListBean) commonBaseResponse;
                    synchronized (gVar) {
                        if (gVar.f6924a) {
                            return;
                        }
                        er1.a(gVar.i, 0L);
                    }
                }

                @Override // supwisdom.gp1
                public void b(Throwable th) {
                    th.getMessage();
                    g gVar = g.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_SDKINIT_FAIL_GERSDK_DATA_ERROR;
                    g.a(gVar, externalCodeResultEnum.code, externalCodeResultEnum.msg + "," + th.getMessage());
                }

                @Override // supwisdom.gp1
                public Class<GetSdkListBean> a() {
                    return GetSdkListBean.class;
                }
            }
        }

        public static void a(g gVar, String str, String str2) {
            synchronized (gVar) {
                if (gVar.f6924a) {
                    return;
                }
                gVar.f6924a = true;
                yp1.e().a("saved_initstate", false);
                dq1.c().a("saved_token_local", gVar.d);
                synchronized (ai.this) {
                    ai aiVar = ai.this;
                    if (aiVar.f6898a != null) {
                        aiVar.f6898a = null;
                    }
                    if (aiVar.c != null) {
                        aiVar.c = null;
                    }
                }
                new Handler(Looper.getMainLooper()).post(new ii(gVar, str, str2));
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                String str = g.this.c;
                String string = dq1.c().f9798a.getString("saved_token_local", "");
                String str2 = g.this.d;
                ((dp1) fp1.a(dp1.class)).a(str, "mobile_service_platform_sdk_secret", string, str2).a(new a());
            }

            public class a extends gp1<ConfigSDKBean> {
                public a() {
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // supwisdom.gp1
                public void a(CommonBaseResponse commonBaseResponse) {
                    ConfigSDKBean configSDKBean = (ConfigSDKBean) commonBaseResponse;
                    T t = configSDKBean.data;
                    if (t == 0) {
                        g gVar = g.this;
                        ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_SDKINIT_FAIL_DATA_NULL;
                        g.a(gVar, externalCodeResultEnum.code, externalCodeResultEnum.msg);
                        return;
                    }
                    if (((ConfigSDKBean.DataBean) t).checkSno && yp1.e().b()) {
                        ai.this.d.clear();
                        ai.this.a();
                        if (g.this.b != null) {
                            ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_NO;
                            JsonOperate.toJson(new ExternalCodeInitResult(externalCodeResultEnum2.code, externalCodeResultEnum2.msg));
                            g.this.b.onInitResult(new ExternalCodeInitResult(externalCodeResultEnum2.code, externalCodeResultEnum2.msg));
                        }
                        ai.this.c = null;
                        return;
                    }
                    yp1 yp1VarE = yp1.e();
                    yp1VarE.getClass();
                    yp1VarE.a("saved_mConfigSDKBean", new Gson().toJson(configSDKBean));
                    aq1 aq1VarD = aq1.d();
                    aq1VarD.a(aq1VarD.c, (String) null);
                    bq1 bq1VarF = bq1.f();
                    bq1VarF.getClass();
                    bq1VarF.a("key_payinfo_list", false);
                    bq1VarF.a((List<CodeBarPayInfoBean.DataBean>) null);
                    dq1.c().a("saved_token_local", g.this.d);
                    g gVar2 = g.this;
                    synchronized (gVar2) {
                        if (!gVar2.f6924a) {
                            er1.a(gVar2.h, 0L);
                        }
                    }
                }

                @Override // supwisdom.gp1
                public void b(Throwable th) {
                    th.getMessage();
                    g gVar = g.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_SDKINIT_FAIL_DATA_ERROR;
                    g.a(gVar, externalCodeResultEnum.code, externalCodeResultEnum.msg + "," + th.getMessage());
                }

                @Override // supwisdom.gp1
                public Class<ConfigSDKBean> a() {
                    return ConfigSDKBean.class;
                }
            }
        }
    }

    public void a() {
        List<String> listB = aq1.d().b();
        if (listB != null && listB.size() > 0) {
            listB.size();
            a(listB.get(0), true);
        } else if (this.d.size() > 0) {
            Iterator<String> it = this.d.iterator();
            while (it.hasNext()) {
                aq1.d().b(it.next());
            }
        }
    }

    public class c extends h {
        public final GetOnlineAndOfflineListener b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6902e;
        public Runnable f;
        public Runnable g;

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                GetOfflineCodeResult getOfflineCodeResultA = ai.this.new b(cVar.c).a(true);
                if (!getOfflineCodeResultA.success) {
                    c.a(c.this, getOfflineCodeResultA.code, getOfflineCodeResultA.msg);
                    return;
                }
                c cVar2 = c.this;
                getOfflineCodeResultA.qrCode = ai.a(ai.this, cVar2.d, getOfflineCodeResultA.qrCode);
                c cVar3 = c.this;
                getOfflineCodeResultA.barCode = cVar3.d;
                String str = cVar3.c;
                int i = cVar3.f6902e;
                synchronized (cVar3) {
                    if (!cVar3.f6924a) {
                        cVar3.f6924a = true;
                        synchronized (ai.this) {
                            ai.this.f6898a = null;
                        }
                        new Handler(Looper.getMainLooper()).post(new bi(cVar3, str, i, getOfflineCodeResultA));
                        if (tp1.a()) {
                            ai aiVar = ai.this;
                            aq1.d();
                            aq1.d();
                            aiVar.a(aq1.d(aq1.a(aq1.d().c(), getOfflineCodeResultA.qrCode, cVar3.d)), false);
                        } else {
                            aq1 aq1VarD = aq1.d();
                            aq1.d();
                            aq1.d();
                            aq1VarD.b(aq1.d(aq1.a(aq1.d().c(), getOfflineCodeResultA.qrCode, cVar3.d)));
                        }
                    }
                }
            }
        }

        public c(String str, GetOnlineAndOfflineListener getOnlineAndOfflineListener) {
            super(ai.this, null);
            this.f = new a();
            this.g = new b();
            this.b = getOnlineAndOfflineListener;
            this.c = str + "";
        }

        public void a() {
            synchronized (this) {
                this.f6924a = false;
            }
            synchronized (this) {
                if (this.f6924a) {
                    return;
                }
                er1.a(this.f, 0L);
            }
        }

        public static void a(c cVar, String str, String str2) {
            synchronized (cVar) {
                if (cVar.f6924a) {
                    return;
                }
                cVar.f6924a = true;
                synchronized (ai.this) {
                    ai.this.f6898a = null;
                }
                new Handler(Looper.getMainLooper()).post(new ci(cVar, str, str2));
            }
        }

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                bq1.f().c();
                if (bq1.f().c().size() == 0) {
                    c cVar = c.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETCOMPOUND_LINE_GET_DATA_NOPARAM;
                    c.a(cVar, externalCodeResultEnum.code, externalCodeResultEnum.msg);
                    return;
                }
                CodeBarPayInfoBean.DataBean dataBean = bq1.f().c().get(c.this.c);
                if (dataBean == null) {
                    c cVar2 = c.this;
                    ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_GETCOMPOUND_LINE_TYPEID;
                    c.a(cVar2, externalCodeResultEnum2.code, externalCodeResultEnum2.msg);
                } else {
                    String str = dataBean.payacc;
                    String str2 = dataBean.paytype;
                    String str3 = dataBean.account;
                    ((dp1) fp1.a(dp1.class)).b(str, str2, str3).a(new C0205a());
                }
            }

            /* JADX INFO: renamed from: supwisdom.ai$c$a$a, reason: collision with other inner class name */
            public class C0205a extends gp1<GetBarCodeBean> {
                public C0205a() {
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // supwisdom.gp1
                public void a(CommonBaseResponse commonBaseResponse) {
                    List<String> list;
                    GetBarCodeBean getBarCodeBean = (GetBarCodeBean) commonBaseResponse;
                    T t = getBarCodeBean.data;
                    if (t == 0 || (list = ((GetBarCodeBean.DataBean) t).barcode) == null || list.size() == 0) {
                        c cVar = c.this;
                        ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETCOMPOUND_LINE_DATA_NULL;
                        c.a(cVar, externalCodeResultEnum.code, externalCodeResultEnum.msg);
                    } else {
                        c.this.d = ((GetBarCodeBean.DataBean) getBarCodeBean.data).barcode.get(0);
                        c cVar2 = c.this;
                        cVar2.f6902e = ((GetBarCodeBean.DataBean) getBarCodeBean.data).expires;
                        synchronized (cVar2) {
                            if (!cVar2.f6924a) {
                                er1.a(cVar2.g, 0L);
                            }
                        }
                    }
                }

                @Override // supwisdom.gp1
                public void b(Throwable th) {
                    th.getMessage();
                    c cVar = c.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETCOMPOUND_LINE_DATA_ERROR;
                    c.a(cVar, externalCodeResultEnum.code, externalCodeResultEnum.msg + "," + th.getMessage());
                }

                @Override // supwisdom.gp1
                public Class<GetBarCodeBean> a() {
                    return GetBarCodeBean.class;
                }
            }
        }
    }

    public class d extends h {
        public GetOnlineCodeListener b;
        public int c;
        public Runnable d;

        public d(int i, GetOnlineCodeListener getOnlineCodeListener) {
            super(ai.this, null);
            this.d = new a();
            this.b = getOnlineCodeListener;
            this.c = i;
        }

        public void a() {
            synchronized (this) {
                this.f6924a = false;
            }
            synchronized (this) {
                if (this.f6924a) {
                    return;
                }
                er1.a(this.d, 0L);
            }
        }

        public static void a(d dVar, String str, String str2) {
            synchronized (dVar) {
                if (dVar.f6924a) {
                    return;
                }
                dVar.f6924a = true;
                synchronized (ai.this) {
                    ai.this.f6898a = null;
                }
                new Handler(Looper.getMainLooper()).post(new ei(dVar, str, str2));
            }
        }

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                bq1.f().c();
                if (bq1.f().c().size() == 0) {
                    d dVar = d.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETONLINE_NOPARM_FAIL;
                    d.a(dVar, externalCodeResultEnum.code, externalCodeResultEnum.msg);
                    return;
                }
                CodeBarPayInfoBean.DataBean dataBean = bq1.f().c().get(d.this.c + "");
                if (dataBean == null) {
                    d dVar2 = d.this;
                    ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_GETONLINE_TYPEID;
                    d.a(dVar2, externalCodeResultEnum2.code, externalCodeResultEnum2.msg);
                } else {
                    ((dp1) fp1.a(dp1.class)).b(dataBean.payacc, dataBean.paytype, dataBean.account).a(new C0206a());
                }
            }

            /* JADX INFO: renamed from: supwisdom.ai$d$a$a, reason: collision with other inner class name */
            public class C0206a extends gp1<GetBarCodeBean> {
                public C0206a() {
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // supwisdom.gp1
                public void a(CommonBaseResponse commonBaseResponse) {
                    List<String> list;
                    GetBarCodeBean getBarCodeBean = (GetBarCodeBean) commonBaseResponse;
                    T t = getBarCodeBean.data;
                    if (t == 0 || (list = ((GetBarCodeBean.DataBean) t).barcode) == null || list.size() == 0) {
                        d dVar = d.this;
                        ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETONLINE_GET_DATA_NULL;
                        d.a(dVar, externalCodeResultEnum.code, externalCodeResultEnum.msg);
                        return;
                    }
                    d dVar2 = d.this;
                    String str = ((GetBarCodeBean.DataBean) getBarCodeBean.data).barcode.get(0);
                    int i = ((GetBarCodeBean.DataBean) getBarCodeBean.data).expires;
                    synchronized (dVar2) {
                        if (!dVar2.f6924a) {
                            dVar2.f6924a = true;
                            synchronized (ai.this) {
                                ai.this.f6898a = null;
                            }
                            new Handler(Looper.getMainLooper()).post(new di(dVar2, str, i));
                            if (tp1.a()) {
                                ai aiVar = ai.this;
                                aq1.d();
                                aq1.d();
                                aiVar.a(aq1.d(aq1.c(str)), false);
                            } else {
                                aq1 aq1VarD = aq1.d();
                                aq1.d();
                                aq1.d();
                                aq1VarD.b(aq1.d(aq1.c(str)));
                            }
                        }
                    }
                }

                @Override // supwisdom.gp1
                public void b(Throwable th) {
                    th.getMessage();
                    d dVar = d.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETONLINE_GET_DATA_ERROR;
                    d.a(dVar, externalCodeResultEnum.code, externalCodeResultEnum.msg + "," + th.getMessage());
                }

                @Override // supwisdom.gp1
                public Class<GetBarCodeBean> a() {
                    return GetBarCodeBean.class;
                }
            }
        }
    }

    public class f extends h {
        public GetPayListListener b;
        public final Runnable c;
        public final Runnable d;

        public f(GetPayListListener getPayListListener) {
            super(ai.this, null);
            this.c = new a();
            this.d = new b();
            this.b = getPayListListener;
        }

        public static void b(f fVar) {
            synchronized (fVar) {
                if (fVar.f6924a) {
                    return;
                }
                fVar.f6924a = true;
                synchronized (ai.this) {
                    ai aiVar = ai.this;
                    if (aiVar.f6898a != null) {
                        aiVar.f6898a = null;
                    } else if (aiVar.b != null) {
                        aiVar.b = null;
                    }
                }
                new Handler(Looper.getMainLooper()).post(new fi(fVar));
            }
        }

        public void a() {
            synchronized (this) {
                this.f6924a = false;
            }
            synchronized (this) {
                if (this.f6924a) {
                    return;
                }
                er1.a(this.c, 100L);
            }
        }

        public static void a(f fVar) {
            synchronized (fVar) {
                if (fVar.f6924a) {
                    return;
                }
                er1.a(fVar.d, 0L);
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f6915a = 0;
            public Map<String, BarcodeParBean> b = new HashMap();

            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                bq1.f().c();
                if (bq1.f().c().size() == 0 || bq1.f().b() == null || bq1.f().b().size() == 0) {
                    f fVar = f.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_PAYLIST_FAIL_GET_NULL;
                    f.a(fVar, externalCodeResultEnum.code, externalCodeResultEnum.msg);
                    return;
                }
                synchronized (f.this.d) {
                    if (this.f6915a >= bq1.f().c().size()) {
                        bq1.f().a(this.b);
                        f.b(f.this);
                        return;
                    }
                    CodeBarPayInfoBean.DataBean dataBean = bq1.f().b().get(this.f6915a);
                    String str = dataBean.payacc;
                    String str2 = dataBean.paytype;
                    String str3 = dataBean.voucher;
                    ((dp1) fp1.a(dp1.class)).a(str, str2, str3).a(new a(dataBean));
                }
            }

            public class a extends gp1<BarcodeParBean> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ CodeBarPayInfoBean.DataBean f6916a;

                public a(CodeBarPayInfoBean.DataBean dataBean) {
                    this.f6916a = dataBean;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // supwisdom.gp1
                public void a(CommonBaseResponse commonBaseResponse) {
                    BarcodeParBean barcodeParBean = (BarcodeParBean) commonBaseResponse;
                    synchronized (f.this.d) {
                        bq1.f().a(this.f6916a.id + "", Integer.parseInt(((BarcodeParBean.DataBean) barcodeParBean.data).obj.OFFLINENUMBER));
                        b.this.b.put(this.f6916a.id + "", barcodeParBean);
                        System.out.println("bar code par size=" + b.this.b.size() + "  jjjj=" + b.this.b.size());
                        b bVar = b.this;
                        bVar.f6915a = bVar.f6915a + 1;
                        f.a(f.this);
                    }
                }

                @Override // supwisdom.gp1
                public void b(Throwable th) {
                    th.getMessage();
                    f fVar = f.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_PAYLIST_FAIL_GET_PARAM_ERROR;
                    f.a(fVar, externalCodeResultEnum.code, externalCodeResultEnum.msg + "," + th.getMessage());
                }

                @Override // supwisdom.gp1
                public Class<BarcodeParBean> a() {
                    return BarcodeParBean.class;
                }
            }
        }

        public static void a(f fVar, String str, String str2) {
            synchronized (fVar) {
                if (fVar.f6924a) {
                    return;
                }
                fVar.f6924a = true;
                synchronized (ai.this) {
                    ai aiVar = ai.this;
                    if (aiVar.f6898a != null) {
                        aiVar.f6898a = null;
                    } else if (aiVar.b != null) {
                        aiVar.b = null;
                    }
                }
                new Handler(Looper.getMainLooper()).post(new gi(fVar, str, str2));
            }
        }

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ((dp1) fp1.a(dp1.class)).a().a(new C0208a());
            }

            /* JADX INFO: renamed from: supwisdom.ai$f$a$a, reason: collision with other inner class name */
            public class C0208a extends gp1<CodeBarPayInfoBean> {
                public C0208a() {
                }

                @Override // supwisdom.gp1
                public void a(CommonBaseResponse commonBaseResponse) {
                    CodeBarPayInfoBean codeBarPayInfoBean = (CodeBarPayInfoBean) commonBaseResponse;
                    if (codeBarPayInfoBean.code != 200) {
                        f fVar = f.this;
                        ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_PAYLIST_FAIL_GET_ERROR;
                        f.a(fVar, externalCodeResultEnum.code, externalCodeResultEnum.msg + ",code != 200");
                        return;
                    }
                    synchronized (bq1.class) {
                        ArrayList arrayList = new ArrayList();
                        HashMap map = new HashMap();
                        arrayList.addAll((Collection) codeBarPayInfoBean.data);
                        for (CodeBarPayInfoBean.DataBean dataBean : (List) codeBarPayInfoBean.data) {
                            map.put(dataBean.id + "", dataBean);
                        }
                        bq1.f().a(arrayList);
                        bq1 bq1VarF = bq1.f();
                        bq1VarF.getClass();
                        String strB = "";
                        try {
                            strB = rp1.b(map);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        bq1VarF.a("mCodeBarPayInfoMap", strB);
                        bq1 bq1VarF2 = bq1.f();
                        bq1VarF2.getClass();
                        bq1VarF2.a("key_payinfo_list", true);
                        bq1 bq1VarF3 = bq1.f();
                        synchronized (bq1VarF3) {
                            bq1VarF3.a("maxOfflineCountMap", (String) null);
                            bq1VarF3.a("currentOfflineCountMap", (String) null);
                        }
                        f.a(f.this);
                    }
                }

                @Override // supwisdom.gp1
                public void b(Throwable th) {
                    th.getMessage();
                    f fVar = f.this;
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_PAYLIST_FAIL_GET_ERROR;
                    f.a(fVar, externalCodeResultEnum.code, externalCodeResultEnum.msg + "," + th.getMessage());
                }

                @Override // supwisdom.gp1
                public Class<CodeBarPayInfoBean> a() {
                    return CodeBarPayInfoBean.class;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ExternalCodeResultEnum a(String str) {
        if (up1.a() != null && yp1.e().b()) {
            if (yp1.e().c() != null && yp1.e().c().data != 0 && ((ConfigSDKBean.DataBean) yp1.e().c().data).apiInfo != null && ((ConfigSDKBean.DataBean) yp1.e().c().data).apiInfo.size() != 0) {
                for (ConfigSDKBean.ApiInfo apiInfo : ((ConfigSDKBean.DataBean) yp1.e().c().data).apiInfo) {
                    if (str.equals(apiInfo.name)) {
                        return apiInfo.empower ? ExternalCodeResultEnum.ERROR_NO : ExternalCodeResultEnum.ERROR_NOPERMISSIONOF_API;
                    }
                }
                return ExternalCodeResultEnum.ERROR_NO;
            }
            return ExternalCodeResultEnum.ERROR_SDKINIT_FAIL;
        }
        return ExternalCodeResultEnum.ERROR_SDKINIT_FAIL;
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6900a = 0;
        public Map<String, BarcodeParBean> b = new HashMap();

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            bq1.f().c();
            if (bq1.f().c().size() == 0 || bq1.f().b() == null || bq1.f().b().size() == 0) {
                return;
            }
            if (this.f6900a >= bq1.f().c().size()) {
                bq1.f().c().size();
                bq1.f().a(this.b);
                this.b.clear();
                this.f6900a = 0;
                return;
            }
            CodeBarPayInfoBean.DataBean dataBean = bq1.f().b().get(this.f6900a);
            String str = dataBean.payacc;
            String str2 = dataBean.paytype;
            String str3 = dataBean.voucher;
            ((dp1) fp1.a(dp1.class)).a(str, str2, str3).a(new C0204a(dataBean));
        }

        /* JADX INFO: renamed from: supwisdom.ai$a$a, reason: collision with other inner class name */
        public class C0204a extends gp1<BarcodeParBean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ CodeBarPayInfoBean.DataBean f6901a;

            public C0204a(CodeBarPayInfoBean.DataBean dataBean) {
                this.f6901a = dataBean;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // supwisdom.gp1
            public void a(CommonBaseResponse commonBaseResponse) {
                BarcodeParBean barcodeParBean = (BarcodeParBean) commonBaseResponse;
                bq1.f().a(this.f6901a.id + "", Integer.parseInt(((BarcodeParBean.DataBean) barcodeParBean.data).obj.OFFLINENUMBER));
                bq1 bq1VarF = bq1.f();
                String str = this.f6901a.id + "";
                Map map = (Map) JsonOperate.fromJson(bq1VarF.f9798a.getString("currentOfflineCountMap", ""), new cq1(bq1VarF).getType());
                if (map == null) {
                    map = new HashMap();
                }
                map.put(str, 1);
                bq1VarF.a("currentOfflineCountMap", JsonOperate.toJson(map));
                a.this.b.put(this.f6901a.id + "", barcodeParBean);
                a aVar = a.this;
                aVar.f6900a = aVar.f6900a + 1;
                ai.this.b();
            }

            @Override // supwisdom.gp1
            public void b(Throwable th) {
                th.getMessage();
            }

            @Override // supwisdom.gp1
            public Class<BarcodeParBean> a() {
                return BarcodeParBean.class;
            }
        }
    }
}
