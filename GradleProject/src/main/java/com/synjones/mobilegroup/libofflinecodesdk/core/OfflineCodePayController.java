package com.synjones.mobilegroup.libofflinecodesdk.core;

import android.app.Application;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.synjones.mobilegroup.libofflinecodesdk.beans.CodeBarPayInfoBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeCompoundResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeInitResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeOfflineResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeOnlineResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodePayListResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketStateResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetOfflineCodeResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JsonOperate;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineAndOfflineListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineCodeListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetPayListListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OffInitListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnUnReceivePaySocketMessageListener;
import com.taobao.weex.common.Constants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import supwisdom.ai;
import supwisdom.ai.b;
import supwisdom.ai.c;
import supwisdom.ai.d;
import supwisdom.ai.f;
import supwisdom.ai.g;
import supwisdom.aq1;
import supwisdom.bq1;
import supwisdom.bt1;
import supwisdom.cp1;
import supwisdom.dq1;
import supwisdom.dr1;
import supwisdom.fr1;
import supwisdom.gr1;
import supwisdom.gt1;
import supwisdom.hr1;
import supwisdom.m0;
import supwisdom.mr1;
import supwisdom.np1;
import supwisdom.sp1;
import supwisdom.tp1;
import supwisdom.un1;
import supwisdom.up1;
import supwisdom.vp1;
import supwisdom.wp1;
import supwisdom.xp1;
import supwisdom.yp1;
import supwisdom.zs1;

/* JADX INFO: loaded from: classes2.dex */
public class OfflineCodePayController {
    public m0 mOff;

    public static class InstanceHolder {
        public static OfflineCodePayController INSTANCE = new OfflineCodePayController();
    }

    public static OfflineCodePayController getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public ExternalCodeOfflineResult getOfflineCode(int i) {
        ai aiVar = (ai) this.mOff;
        synchronized (aiVar) {
            ExternalCodeResultEnum externalCodeResultEnumA = aiVar.a("getOfflineCode");
            if (!externalCodeResultEnumA.code.equals(ExternalCodeResultEnum.ERROR_NO.code)) {
                return new ExternalCodeOfflineResult(externalCodeResultEnumA.code, externalCodeResultEnumA.msg);
            }
            if (tp1.a()) {
                aiVar.b();
            }
            GetOfflineCodeResult getOfflineCodeResultA = aiVar.new b(i + "").a(false);
            if (!getOfflineCodeResultA.success) {
                return new ExternalCodeOfflineResult(getOfflineCodeResultA.code, getOfflineCodeResultA.msg);
            }
            if (tp1.a()) {
                aq1.d();
                aq1.d();
                aiVar.a(aq1.d(aq1.b(aq1.d().c(), getOfflineCodeResultA.qrCode)), false);
            } else {
                aq1 aq1VarD = aq1.d();
                aq1.d();
                aq1.d();
                aq1VarD.b(aq1.d(aq1.b(aq1.d().c(), getOfflineCodeResultA.qrCode)));
            }
            String str = getOfflineCodeResultA.code;
            String str2 = getOfflineCodeResultA.msg;
            StringBuilder sb = new StringBuilder();
            sb.append(bq1.f().a(i + ""));
            sb.append("");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(bq1.f().b(i + ""));
            sb2.append("");
            ExternalCodeOfflineResult externalCodeOfflineResult = new ExternalCodeOfflineResult(str, str2, new ExternalCodeOfflineResult.DataBean(string, sb2.toString(), getOfflineCodeResultA.qrCode));
            bq1.f().c(i + "");
            return externalCodeOfflineResult;
        }
    }

    public void getOnlineAndOfflineCode(int i, GetOnlineAndOfflineListener getOnlineAndOfflineListener) {
        ai aiVar = (ai) this.mOff;
        synchronized (aiVar) {
            ExternalCodeResultEnum externalCodeResultEnumA = aiVar.a("getPayCode");
            if (!externalCodeResultEnumA.code.equals(ExternalCodeResultEnum.ERROR_NO.code)) {
                if (getOnlineAndOfflineListener != null) {
                    JsonOperate.toJson(new ExternalCodeCompoundResult(externalCodeResultEnumA.code, externalCodeResultEnumA.msg));
                    getOnlineAndOfflineListener.onGetOnlineAndOffline(new ExternalCodeCompoundResult(externalCodeResultEnumA.code, externalCodeResultEnumA.msg));
                }
                return;
            }
            if (tp1.a()) {
                if (aiVar.f6898a != null) {
                    if (getOnlineAndOfflineListener != null) {
                        ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETPAYLIST_FAIL;
                        new ExternalCodeCompoundResult(externalCodeResultEnum.code, externalCodeResultEnum.msg).toString();
                        getOnlineAndOfflineListener.onGetOnlineAndOffline(new ExternalCodeCompoundResult(externalCodeResultEnum.code, externalCodeResultEnum.msg));
                    }
                    return;
                }
                aiVar.b();
                ai.c cVar = aiVar.new c(i + "", getOnlineAndOfflineListener);
                aiVar.f6898a = cVar;
                cVar.a();
                return;
            }
            GetOfflineCodeResult getOfflineCodeResultA = aiVar.new b(i + "").a(false);
            if (getOnlineAndOfflineListener != null) {
                if (getOfflineCodeResultA.success) {
                    String str = externalCodeResultEnumA.code;
                    String str2 = externalCodeResultEnumA.msg;
                    StringBuilder sb = new StringBuilder();
                    sb.append(bq1.f().a(i + ""));
                    sb.append("");
                    String string = sb.toString();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(bq1.f().b(i + ""));
                    sb2.append("");
                    JsonOperate.toJson(new ExternalCodeCompoundResult(str, str2, new ExternalCodeCompoundResult.DataBean(string, sb2.toString(), "0", "", getOfflineCodeResultA.qrCode)));
                    String str3 = externalCodeResultEnumA.code;
                    String str4 = externalCodeResultEnumA.msg;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(bq1.f().a(i + ""));
                    sb3.append("");
                    String string2 = sb3.toString();
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(bq1.f().b(i + ""));
                    sb4.append("");
                    getOnlineAndOfflineListener.onGetOnlineAndOffline(new ExternalCodeCompoundResult(str3, str4, new ExternalCodeCompoundResult.DataBean(string2, sb4.toString(), "0", "", getOfflineCodeResultA.qrCode)));
                    bq1.f().c(i + "");
                } else {
                    JsonOperate.toJson(new ExternalCodeCompoundResult(getOfflineCodeResultA.code, getOfflineCodeResultA.msg));
                    getOnlineAndOfflineListener.onGetOnlineAndOffline(new ExternalCodeCompoundResult(getOfflineCodeResultA.code, getOfflineCodeResultA.msg));
                }
            }
        }
    }

    public void getOnlineCode(int i, GetOnlineCodeListener getOnlineCodeListener) {
        ai aiVar = (ai) this.mOff;
        synchronized (aiVar) {
            ExternalCodeResultEnum externalCodeResultEnumA = aiVar.a("getOnlineCode");
            if (!externalCodeResultEnumA.code.equals(ExternalCodeResultEnum.ERROR_NO.code)) {
                if (getOnlineCodeListener != null) {
                    getOnlineCodeListener.onGetOnlineCodeResult(new ExternalCodeOnlineResult(externalCodeResultEnumA.code, externalCodeResultEnumA.msg));
                }
                return;
            }
            if (aiVar.f6898a != null) {
                if (getOnlineCodeListener != null) {
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_GETPAYLIST_FAIL;
                    getOnlineCodeListener.onGetOnlineCodeResult(new ExternalCodeOnlineResult(externalCodeResultEnum.code, externalCodeResultEnum.msg));
                }
            } else if (tp1.a()) {
                ai.d dVar = aiVar.new d(i, getOnlineCodeListener);
                aiVar.f6898a = dVar;
                dVar.a();
            } else {
                if (getOnlineCodeListener != null) {
                    ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_GETONLINE_NONET;
                    getOnlineCodeListener.onGetOnlineCodeResult(new ExternalCodeOnlineResult(externalCodeResultEnum2.code, externalCodeResultEnum2.msg));
                }
            }
        }
    }

    public void getPaymentList(GetPayListListener getPayListListener) {
        ai aiVar = (ai) this.mOff;
        synchronized (aiVar) {
            ExternalCodeResultEnum externalCodeResultEnumA = aiVar.a("getPayList");
            String str = externalCodeResultEnumA.code;
            ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_NO;
            if (!str.equals(externalCodeResultEnum.code)) {
                if (getPayListListener != null) {
                    getPayListListener.onGetPayListResult(new ExternalCodePayListResult(externalCodeResultEnumA.code, externalCodeResultEnumA.msg));
                }
                return;
            }
            if (bq1.f().f9798a.getBoolean("key_payinfo_list", false)) {
                if (getPayListListener != null) {
                    if (bq1.f().b() == null || bq1.f().b().size() <= 0) {
                        getPayListListener.onGetPayListResult(new ExternalCodePayListResult(externalCodeResultEnum.code, externalCodeResultEnum.msg + ",无支付列表数据", null));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        for (CodeBarPayInfoBean.DataBean dataBean : bq1.f().b()) {
                            arrayList.add(new ExternalCodePayListResult.DataBean(dataBean.account, dataBean.expdate, dataBean.lostflag + "", dataBean.name, dataBean.payacc, dataBean.id + ""));
                        }
                        ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_NO;
                        getPayListListener.onGetPayListResult(new ExternalCodePayListResult(externalCodeResultEnum2.code, externalCodeResultEnum2.msg, arrayList));
                    }
                }
                if (aiVar.b != null) {
                    return;
                }
                if (tp1.a()) {
                    ai.f fVar = aiVar.new f(null);
                    aiVar.b = fVar;
                    fVar.a();
                }
            } else if (aiVar.f6898a != null) {
                if (getPayListListener != null) {
                    ExternalCodeResultEnum externalCodeResultEnum3 = ExternalCodeResultEnum.ERROR_GETPAYLIST_FAIL;
                    getPayListListener.onGetPayListResult(new ExternalCodePayListResult(externalCodeResultEnum3.code, externalCodeResultEnum3.msg));
                }
            } else if (tp1.a()) {
                ai.f fVar2 = aiVar.new f(getPayListListener);
                aiVar.f6898a = fVar2;
                fVar2.a();
            } else if (getPayListListener != null) {
                ExternalCodeResultEnum externalCodeResultEnum4 = ExternalCodeResultEnum.ERROR_PAYLIST_FAIL_NONET;
                getPayListListener.onGetPayListResult(new ExternalCodePayListResult(externalCodeResultEnum4.code, externalCodeResultEnum4.msg));
            }
        }
    }

    public String getVersionCode() {
        ((ai) this.mOff).getClass();
        Application application = up1.f9429a;
        if (application == null) {
            return "";
        }
        try {
            return application.getPackageManager().getPackageInfo(up1.f9429a.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public void init(Application application, String str, String str2, String str3, String str4, OffInitListener offInitListener) {
        un1 un1Var;
        Class<dr1> superclass = dr1.class;
        if (sp1.c == null) {
            sp1.c = new sp1(application);
        }
        sp1 sp1Var = sp1.c;
        sp1.a aVar = sp1Var.f9201a;
        if (aVar != null) {
            aVar.c = false;
            sp1Var.f9201a = null;
        }
        if (sp1Var.f9201a == null) {
            sp1Var.f9201a = new sp1.a(sp1Var, String.valueOf(sp1Var.b), sp1.d);
        }
        sp1Var.f9201a.start();
        up1.f9429a = application;
        if (dr1.b == null) {
            synchronized (superclass) {
                if (dr1.b == null) {
                    dr1.b = new dr1();
                }
            }
        }
        dr1 dr1Var = dr1.b;
        dr1Var.getClass();
        synchronized (superclass) {
            try {
                if (!dr1Var.f7373a) {
                    dr1Var.f7373a = true;
                    System.out.println("ScheduleUtil regist：this class is: " + superclass.getSimpleName());
                    if (wp1.b == null) {
                        synchronized (wp1.class) {
                            if (wp1.b == null) {
                                wp1.b = new wp1();
                            }
                        }
                    }
                    wp1 wp1Var = wp1.b;
                    wp1Var.getClass();
                    if (wp1Var.f9651a.get(superclass) == null) {
                        while (superclass != null) {
                            ArrayList arrayList = new ArrayList();
                            Method[] methods = superclass.getMethods();
                            if (methods.length != 0) {
                                for (Method method : methods) {
                                    if (method.isAnnotationPresent(un1.class)) {
                                        Class<?>[] parameterTypes = method.getParameterTypes();
                                        if (parameterTypes.length == 1 && (un1Var = (un1) method.getAnnotation(un1.class)) != null) {
                                            arrayList.add(new vp1(un1Var.threadMode(), parameterTypes[0], method));
                                        }
                                    }
                                }
                                wp1Var.f9651a.put(superclass, arrayList);
                            }
                            superclass = superclass.getSuperclass();
                            String name = superclass.getName();
                            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android")) {
                                superclass = null;
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
        xp1.b = application;
        bq1.f().a("key_server_url", str2);
        HashMap<String, np1> map = cp1.b;
        cp1.c = bq1.f().e();
        yp1.e().a("saved_appid", str);
        bq1.f().a("pos_sn", application.getFilesDir().getAbsolutePath());
        ai aiVar = (ai) this.mOff;
        synchronized (aiVar) {
            aiVar.f = str4;
            if (str3.equals(dq1.c().f9798a.getString("saved_token_local", "")) && yp1.e().b()) {
                aiVar.d.clear();
                aiVar.a();
                if (offInitListener != null) {
                    ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_NO;
                    offInitListener.onInitResult(new ExternalCodeInitResult(externalCodeResultEnum.code, externalCodeResultEnum.msg));
                }
                if (tp1.a()) {
                    dq1.c().a("saved_token", str3);
                    if (aiVar.c != null) {
                        return;
                    }
                    ai.g gVar = aiVar.new g(str, "mobile_service_platform_sdk_secret", str3, null);
                    aiVar.c = gVar;
                    gVar.a();
                }
                return;
            }
            if (tp1.a()) {
                dq1.c().a("saved_token", str3);
                if (aiVar.f6898a == null) {
                    ai.g gVar2 = aiVar.new g(str, "mobile_service_platform_sdk_secret", str3, offInitListener);
                    aiVar.f6898a = gVar2;
                    gVar2.a();
                } else if (offInitListener != null) {
                    ExternalCodeResultEnum externalCodeResultEnum2 = ExternalCodeResultEnum.ERROR_GETPAYLIST_FAIL;
                    offInitListener.onInitResult(new ExternalCodeInitResult(externalCodeResultEnum2.code, externalCodeResultEnum2.msg));
                }
            } else {
                yp1.e().a("saved_initstate", false);
                if (offInitListener != null) {
                    ExternalCodeResultEnum externalCodeResultEnum3 = ExternalCodeResultEnum.ERROR_SDKINIT_FAIL_NONET;
                    offInitListener.onInitResult(new ExternalCodeInitResult(externalCodeResultEnum3.code, externalCodeResultEnum3.msg));
                }
            }
        }
    }

    public void registerPaymentListener(String str, OnReceivePaySocketMessageListener onReceivePaySocketMessageListener) {
        zs1 zs1Var;
        ai aiVar = (ai) this.mOff;
        ExternalCodeResultEnum externalCodeResultEnumA = aiVar.a("getOnlineCode");
        if (!externalCodeResultEnumA.code.equals(ExternalCodeResultEnum.ERROR_NO.code)) {
            aiVar.f6899e = false;
            onReceivePaySocketMessageListener.onSocketStateResult(new ExternalSocketStateResult(externalCodeResultEnumA.code, externalCodeResultEnumA.msg));
            return;
        }
        aiVar.f6899e = true;
        fr1 fr1VarA = fr1.a();
        if (fr1VarA.c != onReceivePaySocketMessageListener) {
            fr1VarA.c = onReceivePaySocketMessageListener;
            if (!fr1VarA.f7637a.isEmpty()) {
                Iterator<Map.Entry<String, gr1>> it = fr1VarA.f7637a.entrySet().iterator();
                while (it.hasNext()) {
                    gr1 value = it.next().getValue();
                    if (value != null) {
                        value.d = onReceivePaySocketMessageListener;
                    }
                }
            }
        }
        if (aiVar.f6899e) {
            fr1 fr1VarA2 = fr1.a();
            String str2 = aiVar.f;
            fr1VarA2.getClass();
            mr1 mr1Var = new mr1(str, str2);
            if (!tp1.a() || TextUtils.isEmpty(str)) {
                return;
            }
            if (fr1VarA2.f7637a.size() == 2) {
                gr1 gr1VarRemove = fr1VarA2.f7637a.remove(fr1VarA2.b.removeFirst());
                if (gr1VarRemove != null) {
                    gt1 gt1Var = gr1VarRemove.f7766a;
                    if (gt1Var != null) {
                        gt1Var.close(1000, "close by hand");
                    }
                    gr1VarRemove.d = null;
                }
                fr1VarA2.a("removeFirst and reset size is:" + fr1VarA2.b.size());
            }
            mr1Var.d = fr1VarA2.c;
            fr1VarA2.b.addLast(str);
            fr1VarA2.f7637a.put(str, mr1Var);
            bt1 bt1Var = mr1Var.c;
            if (bt1Var != null && (zs1Var = mr1Var.b) != null) {
                mr1Var.f7766a = zs1Var.a(bt1Var, new hr1(mr1Var));
            }
            fr1VarA2.a("createNewSocket");
        }
    }

    public void unRegisterPaymentListener(OnUnReceivePaySocketMessageListener onUnReceivePaySocketMessageListener) {
        ai aiVar = (ai) this.mOff;
        ExternalCodeResultEnum externalCodeResultEnumA = aiVar.a("getOnlineCode");
        if (!externalCodeResultEnumA.code.equals(ExternalCodeResultEnum.ERROR_NO.code)) {
            onUnReceivePaySocketMessageListener.onSocketStateResult(new ExternalSocketStateResult(externalCodeResultEnumA.code, externalCodeResultEnumA.msg));
            return;
        }
        aiVar.f6899e = false;
        fr1 fr1VarA = fr1.a();
        for (gr1 gr1Var : fr1VarA.f7637a.values()) {
            gr1Var.d = null;
            gt1 gt1Var = gr1Var.f7766a;
            if (gt1Var != null) {
                gt1Var.close(1000, "close by hand");
            }
        }
        fr1VarA.b.clear();
        fr1VarA.f7637a.clear();
        fr1VarA.a(Constants.Event.SLOT_LIFECYCLE.DESTORY);
        ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_NO;
        onUnReceivePaySocketMessageListener.onSocketStateResult(new ExternalSocketStateResult(externalCodeResultEnum.code, externalCodeResultEnum.msg));
    }

    public OfflineCodePayController() {
        this.mOff = new ai();
        System.out.println("u can not construct me!03211102");
    }
}
