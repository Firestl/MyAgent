package com.igexin.push.core;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.igexin.assist.sdk.AssistPushManager;
import com.igexin.assist.util.AssistUtils;
import com.igexin.push.config.a.AnonymousClass3;
import com.igexin.push.config.a.AnonymousClass4;
import com.igexin.push.core.d;
import com.igexin.push.core.e.f.AnonymousClass20;
import com.igexin.push.core.e.f.AnonymousClass21;
import com.igexin.push.d.c.o;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.main.FeedbackImpl;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.db;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.adapter.ui.webview.WebLoadEvent;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3497a = "PushController";
    public static m b;

    public static m a() {
        if (b == null) {
            b = new m();
        }
        return b;
    }

    public static void a(int i) {
        com.igexin.push.config.d.f3289e = i;
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.config.a.a().new AnonymousClass3(), false, true);
        if (e.u) {
            System.currentTimeMillis();
            com.igexin.c.a.c.a.a("setHeartbeatInterval heartbeatReq", new Object[0]);
            if (System.currentTimeMillis() - e.Y > 5000) {
                e.Y = System.currentTimeMillis();
                com.igexin.push.core.a.b.d();
                com.igexin.push.core.a.b.f();
            }
        }
    }

    public static void a(int i, int i2) {
        com.igexin.push.config.d.b = i;
        com.igexin.push.config.d.c = i2;
        com.igexin.push.config.a.a().b();
        com.igexin.push.f.f.a().d();
        com.igexin.c.a.c.a.d.a().a("[PushController] setSilentTime success");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v15 */
    public static void a(Bundle bundle) {
        int i;
        int i2;
        String string = bundle.getString("action");
        com.igexin.c.a.c.a.a("PushController|action pushmanager action = ".concat(String.valueOf(string)), new Object[0]);
        if (TextUtils.isEmpty(string)) {
        }
        byte b2 = -1;
        switch (string.hashCode()) {
            case -1710807787:
                if (string.equals("queryPushOnLine")) {
                    b2 = SharedPreferencesNewImpl.FINISH_MARK;
                }
                break;
            case -1673882831:
                if (string.equals("setVivoBadgeNum")) {
                    b2 = 11;
                }
                break;
            case -1411528570:
                if (string.equals("setNotificationIcon")) {
                    b2 = 16;
                }
                break;
            case -1166665294:
                if (string.equals(PushConsts.QUERY_TAG)) {
                    b2 = 17;
                }
                break;
            case -1092138459:
                if (string.equals("setOppoBadgeNum")) {
                    b2 = 12;
                }
                break;
            case -957964269:
                if (string.equals("bindAlias")) {
                    b2 = 7;
                }
                break;
            case -908867308:
                if (string.equals("setHwBadgeNum")) {
                    b2 = 10;
                }
                break;
            case -905799720:
                if (string.equals("setTag")) {
                    b2 = 0;
                }
                break;
            case -889524838:
                if (string.equals("unbindAlias")) {
                    b2 = 8;
                }
                break;
            case -850755092:
                if (string.equals("turnOffPush")) {
                    b2 = 6;
                }
                break;
            case -479268212:
                if (string.equals("registerPushActivity")) {
                    b2 = db.l;
                }
                break;
            case -344351336:
                if (string.equals("sendApplinkFeedback")) {
                    b2 = 9;
                }
                break;
            case -159289499:
                if (string.equals("setBadgeNum")) {
                    b2 = 13;
                }
                break;
            case -101965284:
                if (string.equals("setLinkMerge")) {
                    b2 = 21;
                }
                break;
            case -12797509:
                if (string.equals("setGuardOptions")) {
                    b2 = 20;
                }
                break;
            case 329771905:
                if (string.equals("setDeviceToken")) {
                    b2 = 19;
                }
                break;
            case 495464132:
                if (string.equals("setSilentTime")) {
                    b2 = 1;
                }
                break;
            case 539767084:
                if (string.equals("setSocketTimeout")) {
                    b2 = 4;
                }
                break;
            case 556182983:
                if (string.equals("registerUserService")) {
                    b2 = 15;
                }
                break;
            case 691453791:
                if (string.equals("sendMessage")) {
                    b2 = 2;
                }
                break;
            case 999002527:
                if (string.equals("setHeartbeatInterval")) {
                    b2 = 3;
                }
                break;
            case 1841202202:
                if (string.equals("sendFeedbackMessage")) {
                    b2 = 5;
                }
                break;
        }
        switch (b2) {
            case 0:
                if (com.igexin.push.config.d.k) {
                    String string2 = bundle.getString("tags");
                    String string3 = bundle.getString("sn");
                    if (TextUtils.isEmpty(e.A)) {
                        com.igexin.c.a.c.a.d.a().a("setTag : " + string2 + ", failed, has not get clientid");
                        l.a().a(string3, "20008");
                    } else {
                        try {
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("action", "set_tag");
                                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                                jSONObject.put("cid", e.A);
                                jSONObject.put("appid", e.f3403a);
                                jSONObject.put("tags", URLEncoder.encode(string2, "utf-8"));
                                jSONObject.put("sn", string3);
                            } catch (Exception e2) {
                                com.igexin.c.a.c.a.a(e2);
                            }
                            e.f3404e = string2.replaceAll(",", Operators.SPACE_STR);
                            String string4 = jSONObject.toString();
                            com.igexin.push.core.e.e.a().b(new com.igexin.push.core.b.n(jCurrentTimeMillis, string4, (byte) 2, e.u ? jCurrentTimeMillis : 0L));
                            o oVar = new o();
                            oVar.c = 128;
                            oVar.f3521e = b.O;
                            oVar.f = string4;
                            d.a.f3384a.h.a("C-" + e.A, oVar, false);
                            com.igexin.c.a.c.a.a("settag", new Object[0]);
                        } catch (Exception e3) {
                            com.igexin.c.a.c.a.a(e3);
                            return;
                        }
                    }
                }
                break;
            case 1:
                if (com.igexin.push.config.d.l) {
                    int i3 = bundle.getInt("beginHour", 0);
                    int i4 = bundle.getInt("duration", 0);
                    e.l.getPackageName();
                    a(i3, i4);
                    AssistPushManager.getInstance().setSilentTime(e.l, i3, i4);
                }
                break;
            case 2:
                boolean z = com.igexin.push.config.d.j;
                com.igexin.c.a.c.a.a("PushController onPushManagerMessage recevie action : sendMessage", new Object[0]);
                if (com.igexin.push.config.d.j) {
                    String string5 = bundle.getString("taskid");
                    byte[] byteArray = bundle.getByteArray("extraData");
                    com.igexin.c.a.c.a.a("PushController receive broadcast msg data , task id : " + string5 + " ######@##@@@#", new Object[0]);
                    if (e.A != null) {
                        JSONObject jSONObject2 = new JSONObject();
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        try {
                            jSONObject2.put("action", "sendmessage");
                            jSONObject2.put("id", String.valueOf(jCurrentTimeMillis2));
                            jSONObject2.put("cid", e.A);
                            jSONObject2.put("appid", e.f3403a);
                            jSONObject2.put("taskid", string5);
                            jSONObject2.put("extraData", Base64.encodeToString(byteArray, 0));
                            String string6 = jSONObject2.toString();
                            com.igexin.push.core.e.e.a().b(new com.igexin.push.core.b.n(jCurrentTimeMillis2, string6, (byte) 6, jCurrentTimeMillis2));
                            com.igexin.push.d.c.b bVar = new com.igexin.push.d.c.b();
                            bVar.c = 128;
                            bVar.b = (int) jCurrentTimeMillis2;
                            bVar.f3521e = e.A;
                            bVar.f = string6;
                            bVar.g = byteArray;
                            bVar.h = e.A;
                            d.a.f3384a.h.a("C-" + e.A, bVar, false);
                            if (string5 != null && string5.startsWith("4T5@S_")) {
                                com.igexin.c.a.c.a.a("PushController sending lbs report message : ".concat(String.valueOf(string6)), new Object[0]);
                                break;
                            }
                        } catch (Throwable th) {
                            com.igexin.c.a.c.a.a(th);
                            return;
                        }
                    }
                }
                break;
            case 3:
                if (com.igexin.push.config.d.m) {
                    int i5 = bundle.getInt("interval", 0);
                    e.l.getPackageName();
                    com.igexin.push.config.d.f3289e = i5;
                    com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.config.a.a().new AnonymousClass3(), false, true);
                    if (e.u) {
                        System.currentTimeMillis();
                        com.igexin.c.a.c.a.a("setHeartbeatInterval heartbeatReq", new Object[0]);
                        if (System.currentTimeMillis() - e.Y > 5000) {
                            e.Y = System.currentTimeMillis();
                            com.igexin.push.core.a.b.d();
                            com.igexin.push.core.a.b.f();
                        }
                    }
                }
                break;
            case 4:
                if (com.igexin.push.config.d.n) {
                    int i6 = bundle.getInt("submitTimeoutEvent", 0);
                    e.l.getPackageName();
                    com.igexin.push.config.d.f = i6;
                    com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.config.a.a().new AnonymousClass4(), false, true);
                }
                break;
            case 5:
                boolean z2 = com.igexin.push.config.d.o;
                int i7 = e.am;
                if (com.igexin.push.config.d.o && e.am <= 200) {
                    String string7 = bundle.getString("taskid");
                    String string8 = bundle.getString("messageid");
                    String string9 = bundle.getString("actionid");
                    String str = string7 + Constants.COLON_SEPARATOR + string8 + Constants.COLON_SEPARATOR + string9;
                    if (e.al.get(str) == null) {
                        long jCurrentTimeMillis3 = System.currentTimeMillis();
                        PushTaskBean pushTaskBean = new PushTaskBean();
                        pushTaskBean.setTaskId(string7);
                        pushTaskBean.setMessageId(string8);
                        pushTaskBean.setAppid(e.f3403a);
                        FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, string9);
                        e.am++;
                        e.al.put(str, Long.valueOf(jCurrentTimeMillis3));
                    }
                    break;
                }
                break;
            case 6:
                d dVar = d.a.f3384a;
                if (e.l != null) {
                    com.igexin.push.core.d.d.a().a("p", Boolean.FALSE);
                    e.s = false;
                    e.v = false;
                    dVar.h.b();
                }
                AssistPushManager.getInstance().turnOffPush(e.l);
                break;
            case 7:
                String string10 = bundle.getString("alias");
                String string11 = bundle.getString("sn");
                com.igexin.c.a.c.a.a("PushController|onPushManagerMessage bindAlias...", new Object[0]);
                if (!TextUtils.isEmpty(e.A)) {
                    long jCurrentTimeMillis4 = System.currentTimeMillis();
                    if (jCurrentTimeMillis4 - e.aa <= 1000) {
                        com.igexin.c.a.c.a.a("PushController|bindAlias frequently called", new Object[0]);
                    } else {
                        String str2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(jCurrentTimeMillis4));
                        String str3 = e.Z;
                        if (str2.equals(e.Z)) {
                            i = 0;
                        } else {
                            com.igexin.push.core.e.f.a().d(str2);
                            i = 0;
                            com.igexin.push.core.e.f.a().a(0);
                        }
                        int i8 = e.ab;
                        com.igexin.c.a.c.a.a("-> CoreRuntimeInfo.opAliasTimes:" + e.ab, new Object[i]);
                        if (e.ab >= 100) {
                            com.igexin.c.a.c.a.a("PushController|bindAlias times exceed", new Object[i]);
                            com.igexin.c.a.c.a.d.a().a("bindAlias : " + string10 + ", failed, , the number of calls per day cannot exceed 100");
                            l.a().b(string11, "30003");
                        } else {
                            com.igexin.c.a.c.a.a("start bindAlias ###", new Object[i]);
                            e.aa = jCurrentTimeMillis4;
                            com.igexin.push.core.e.f.a().a(e.ab + 1);
                            a(string10, string11, i, true);
                        }
                    }
                } else {
                    com.igexin.c.a.c.a.d.a().a("bindAlias : " + string10 + ", failed, has not get clientid");
                    l.a().b(string11, "30005");
                }
                break;
            case 8:
                String string12 = bundle.getString("alias");
                String string13 = bundle.getString("sn");
                boolean z3 = bundle.getBoolean("isSeft");
                com.igexin.c.a.c.a.a("PushController|onPushManagerMessage unbindAlias...", new Object[0]);
                if (TextUtils.isEmpty(e.A)) {
                    com.igexin.c.a.c.a.d.a().a("unbindAlias : " + string12 + ", failed, has not get clientid");
                    l.a().c(string13, "30005");
                } else if (!z3 || !TextUtils.isEmpty(e.A)) {
                    long jCurrentTimeMillis5 = System.currentTimeMillis();
                    if (jCurrentTimeMillis5 - e.aa <= 1000) {
                        com.igexin.c.a.c.a.a("PushController|unbindAlias frequently called", new Object[0]);
                    } else {
                        String str4 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(jCurrentTimeMillis5));
                        if (str4.equals(e.Z)) {
                            i2 = 0;
                        } else {
                            com.igexin.push.core.e.f.a().d(str4);
                            i2 = 0;
                            com.igexin.push.core.e.f.a().a(0);
                        }
                        if (e.ab >= 100) {
                            com.igexin.c.a.c.a.a("PushController|unbindAlias times exceed", new Object[i2]);
                            com.igexin.c.a.c.a.d.a().a("unbindAlias : " + string12 + ", failed, , the number of calls per day cannot exceed 100");
                            l.a().c(string13, "30003");
                        } else {
                            com.igexin.c.a.c.a.a("start unbindAlias ###", new Object[i2]);
                            e.aa = jCurrentTimeMillis5;
                            com.igexin.push.core.e.f.a().a(e.ab + 1);
                            a(string12, string13, true, z3);
                        }
                    }
                }
                break;
            case 9:
                String string14 = bundle.getString("url");
                boolean z4 = com.igexin.push.config.d.E;
                if (!TextUtils.isEmpty(string14)) {
                    try {
                        Uri uri = Uri.parse(string14);
                        String host = uri.getHost();
                        String queryParameter = uri.getQueryParameter("p");
                        if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(queryParameter)) {
                            if (!com.igexin.push.config.d.E) {
                                com.igexin.c.a.c.a.a("PushController|isApplinkFeedback is false, not feedback", new Object[0]);
                            } else if (!com.igexin.push.g.c.c(host)) {
                                com.igexin.c.a.c.a.a("PushController|checkIsWhiteApplinkDomain is false, not feedback", new Object[0]);
                            } else {
                                com.igexin.c.a.c.a.a("PushController|isApplinkFeedback is true and checkIsWhiteApplinkDomain is true, to feedback", new Object[0]);
                                PushTaskBean pushTaskBean2 = new PushTaskBean();
                                pushTaskBean2.setTaskId("getuiapplinkup");
                                pushTaskBean2.setMessageId(queryParameter);
                                pushTaskBean2.setAppid(e.f3403a);
                                FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean2, PushConsts.SEND_MESSAGE_ERROR);
                            }
                        }
                        com.igexin.c.a.c.a.a("PushController|url " + string14 + " is invalid", new Object[0]);
                    } catch (Exception e4) {
                        com.igexin.c.a.c.a.a(e4);
                        com.igexin.c.a.c.a.a("PushController|" + e4.toString(), new Object[0]);
                        return;
                    }
                }
                break;
            case 10:
                com.igexin.push.g.d.a(bundle.getInt("badgeNum"), true);
                break;
            case 11:
                com.igexin.push.g.d.b(bundle.getInt("badgeNum"), true);
                break;
            case 12:
                com.igexin.push.g.d.c(bundle.getInt("badgeNum"), true);
                break;
            case 13:
                if (com.igexin.push.g.n.d().equalsIgnoreCase(AssistUtils.BRAND_HW) || com.igexin.push.g.n.d().equalsIgnoreCase(AssistUtils.BRAND_HON)) {
                    com.igexin.push.g.d.a(bundle.getInt("badgeNum"), true);
                } else if (com.igexin.push.g.n.d().equalsIgnoreCase(AssistUtils.BRAND_OPPO)) {
                    com.igexin.push.g.d.c(bundle.getInt("badgeNum"), true);
                } else if (com.igexin.push.g.n.d().equalsIgnoreCase(AssistUtils.BRAND_VIVO)) {
                    com.igexin.push.g.d.b(bundle.getInt("badgeNum"), true);
                }
                break;
            case 14:
            case 15:
                e.a();
                break;
            case 16:
                e.aK = bundle.getString("smallIcon", "");
                e.aL = bundle.getString("largeIcon", "");
                com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.core.e.f.a().new AnonymousClass20(e.aK, e.aL), false, true);
                com.igexin.c.a.c.a.d.a().a("[PushController] setNotificationIcon success");
                break;
            case 17:
                String string15 = bundle.getString("sn");
                if (!TextUtils.isEmpty(e.A)) {
                    if (System.currentTimeMillis() - e.c < com.igexin.push.config.d.f3288a * 1000 && e.d != null) {
                        com.igexin.c.a.c.a.a("PushController|query tag already cache, tag = " + e.d, new Object[0]);
                        l.a().a(string15, "0", e.d);
                    } else {
                        try {
                            long jCurrentTimeMillis6 = System.currentTimeMillis();
                            JSONObject jSONObject3 = new JSONObject();
                            try {
                                jSONObject3.put("action", "query_tag");
                                jSONObject3.put("id", String.valueOf(jCurrentTimeMillis6));
                                jSONObject3.put("cid", e.A);
                                jSONObject3.put("appid", e.f3403a);
                                jSONObject3.put("sn", string15);
                            } catch (Exception e5) {
                                com.igexin.c.a.c.a.a(e5);
                            }
                            String string16 = jSONObject3.toString();
                            com.igexin.push.core.e.e.a().b(new com.igexin.push.core.b.n(jCurrentTimeMillis6, string16, (byte) 11, jCurrentTimeMillis6));
                            o oVar2 = new o();
                            oVar2.c = 128;
                            oVar2.f3521e = b.O;
                            oVar2.f = string16;
                            d.a.f3384a.h.a("C-" + e.A, oVar2, false);
                            com.igexin.push.core.e.f fVarA = com.igexin.push.core.e.f.a();
                            if (e.c != jCurrentTimeMillis6) {
                                e.c = jCurrentTimeMillis6;
                                com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) fVarA.new AnonymousClass21(), false, true);
                            }
                            com.igexin.c.a.c.a.a("PushController｜queryTag", new Object[0]);
                        } catch (Exception e6) {
                            com.igexin.c.a.c.a.a(e6);
                            return;
                        }
                    }
                }
                break;
            case 18:
                l.a().b();
                break;
            case 19:
                try {
                    String string17 = bundle.getString("token", "");
                    if (!TextUtils.isEmpty(string17) && e.b().booleanValue() && !string17.equals(e.I)) {
                        com.igexin.push.core.e.f.a().b(string17);
                        if (e.u) {
                            com.igexin.c.a.c.a.b(f3497a, "online, send addphoneinfo");
                            com.igexin.push.core.a.b.d().i();
                        }
                    }
                    com.igexin.c.a.c.a.d.a().a("[PushController] setDeviceToken success ".concat(String.valueOf(string17)));
                } catch (Throwable th2) {
                    com.igexin.c.a.c.a.a(th2);
                    return;
                }
                break;
            case 20:
                com.igexin.push.config.e.a(bundle.getBoolean("guardMe", true), bundle.getBoolean("guardOthers", true));
                e.a();
                com.igexin.c.a.c.a.d.a().a("[PushController] setGuardOptions success");
                break;
            case 21:
                boolean z5 = bundle.getBoolean(WebLoadEvent.ENABLE, true);
                com.igexin.push.config.e.a(z5, z5);
                e.a();
                com.igexin.c.a.c.a.d.a().a("[PushController] setLinkMerge success");
                break;
        }
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Uri uri = Uri.parse(str);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("p");
            if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(queryParameter)) {
                if (!com.igexin.push.config.d.E) {
                    com.igexin.c.a.c.a.a("PushController|isApplinkFeedback is false, not feedback", new Object[0]);
                    return;
                }
                if (!com.igexin.push.g.c.c(host)) {
                    com.igexin.c.a.c.a.a("PushController|checkIsWhiteApplinkDomain is false, not feedback", new Object[0]);
                    return;
                }
                com.igexin.c.a.c.a.a("PushController|isApplinkFeedback is true and checkIsWhiteApplinkDomain is true, to feedback", new Object[0]);
                PushTaskBean pushTaskBean = new PushTaskBean();
                pushTaskBean.setTaskId("getuiapplinkup");
                pushTaskBean.setMessageId(queryParameter);
                pushTaskBean.setAppid(e.f3403a);
                FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, PushConsts.SEND_MESSAGE_ERROR);
                return;
            }
            com.igexin.c.a.c.a.a("PushController|url " + str + " is invalid", new Object[0]);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            com.igexin.c.a.c.a.a("PushController|" + e2.toString(), new Object[0]);
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(e.A)) {
            com.igexin.c.a.c.a.d.a().a("setTag : " + str + ", failed, has not get clientid");
            l.a().a(str2, "20008");
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", "set_tag");
                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                jSONObject.put("cid", e.A);
                jSONObject.put("appid", e.f3403a);
                jSONObject.put("tags", URLEncoder.encode(str, "utf-8"));
                jSONObject.put("sn", str2);
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            e.f3404e = str.replaceAll(",", Operators.SPACE_STR);
            String string = jSONObject.toString();
            com.igexin.push.core.e.e.a().b(new com.igexin.push.core.b.n(jCurrentTimeMillis, string, (byte) 2, e.u ? jCurrentTimeMillis : 0L));
            o oVar = new o();
            oVar.c = 128;
            oVar.f3521e = b.O;
            oVar.f = string;
            d.a.f3384a.h.a("C-" + e.A, oVar, false);
            com.igexin.c.a.c.a.a("settag", new Object[0]);
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
        }
    }

    public static void a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(e.A)) {
            com.igexin.c.a.c.a.d.a().a("unbindAlias : " + str + ", failed, has not get clientid");
            l.a().c(str2, "30005");
            return;
        }
        if (z && TextUtils.isEmpty(e.A)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - e.aa <= 1000) {
            com.igexin.c.a.c.a.a("PushController|unbindAlias frequently called", new Object[0]);
            return;
        }
        String str3 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(jCurrentTimeMillis));
        if (!str3.equals(e.Z)) {
            com.igexin.push.core.e.f.a().d(str3);
            com.igexin.push.core.e.f.a().a(0);
        }
        if (e.ab < 100) {
            com.igexin.c.a.c.a.a("start unbindAlias ###", new Object[0]);
            e.aa = jCurrentTimeMillis;
            com.igexin.push.core.e.f.a().a(e.ab + 1);
            a(str, str2, true, z);
            return;
        }
        com.igexin.c.a.c.a.a("PushController|unbindAlias times exceed", new Object[0]);
        com.igexin.c.a.c.a.d.a().a("unbindAlias : " + str + ", failed, , the number of calls per day cannot exceed 100");
        l.a().c(str2, "30003");
    }

    public static void a(String str, String str2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(e.A)) {
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            String str3 = z ? "unbind_alias" : "bind_alias";
            byte b2 = z ? (byte) 8 : (byte) 7;
            try {
                jSONObject.put("action", str3);
                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                jSONObject.put("cid", e.A);
                jSONObject.put("appid", e.f3403a);
                jSONObject.put("alias", str);
                jSONObject.put("sn", str2);
                if (z) {
                    jSONObject.put("is_self", z2);
                }
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            String string = jSONObject.toString();
            com.igexin.push.core.e.e.a().b(new com.igexin.push.core.b.n(jCurrentTimeMillis, string, b2, e.u ? jCurrentTimeMillis : 0L));
            o oVar = new o();
            oVar.c = 128;
            oVar.f3521e = b.O;
            oVar.f = string;
            d.a.f3384a.h.a("C-" + e.A, oVar, false);
            com.igexin.c.a.c.a.a(str3 + " = " + string, new Object[0]);
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
        }
    }

    public static void a(String str, byte[] bArr) {
        if (e.A != null) {
            JSONObject jSONObject = new JSONObject();
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                jSONObject.put("action", "sendmessage");
                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                jSONObject.put("cid", e.A);
                jSONObject.put("appid", e.f3403a);
                jSONObject.put("taskid", str);
                jSONObject.put("extraData", Base64.encodeToString(bArr, 0));
                String string = jSONObject.toString();
                com.igexin.push.core.e.e.a().b(new com.igexin.push.core.b.n(jCurrentTimeMillis, string, (byte) 6, jCurrentTimeMillis));
                com.igexin.push.d.c.b bVar = new com.igexin.push.d.c.b();
                bVar.c = 128;
                bVar.b = (int) jCurrentTimeMillis;
                bVar.f3521e = e.A;
                bVar.f = string;
                bVar.g = bArr;
                bVar.h = e.A;
                d.a.f3384a.h.a("C-" + e.A, bVar, false);
                if (str == null || !str.startsWith("4T5@S_")) {
                    return;
                }
                com.igexin.c.a.c.a.a("PushController sending lbs report message : ".concat(String.valueOf(string)), new Object[0]);
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }
    }

    public static void b(int i) {
        com.igexin.push.config.d.f = i;
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.config.a.a().new AnonymousClass4(), false, true);
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(e.A)) {
            return;
        }
        if (System.currentTimeMillis() - e.c < com.igexin.push.config.d.f3288a * 1000 && e.d != null) {
            com.igexin.c.a.c.a.a("PushController|query tag already cache, tag = " + e.d, new Object[0]);
            l.a().a(str, "0", e.d);
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", "query_tag");
                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                jSONObject.put("cid", e.A);
                jSONObject.put("appid", e.f3403a);
                jSONObject.put("sn", str);
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            String string = jSONObject.toString();
            com.igexin.push.core.e.e.a().b(new com.igexin.push.core.b.n(jCurrentTimeMillis, string, (byte) 11, jCurrentTimeMillis));
            o oVar = new o();
            oVar.c = 128;
            oVar.f3521e = b.O;
            oVar.f = string;
            d.a.f3384a.h.a("C-" + e.A, oVar, false);
            com.igexin.push.core.e.f fVarA = com.igexin.push.core.e.f.a();
            if (e.c != jCurrentTimeMillis) {
                e.c = jCurrentTimeMillis;
                com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) fVarA.new AnonymousClass21(), false, true);
            }
            com.igexin.c.a.c.a.a("PushController｜queryTag", new Object[0]);
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
        }
    }

    public static void b(String str, String str2) {
        if (TextUtils.isEmpty(e.A)) {
            com.igexin.c.a.c.a.d.a().a("bindAlias : " + str + ", failed, has not get clientid");
            l.a().b(str2, "30005");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - e.aa <= 1000) {
            com.igexin.c.a.c.a.a("PushController|bindAlias frequently called", new Object[0]);
            return;
        }
        String str3 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(jCurrentTimeMillis));
        String str4 = e.Z;
        if (!str3.equals(e.Z)) {
            com.igexin.push.core.e.f.a().d(str3);
            com.igexin.push.core.e.f.a().a(0);
        }
        int i = e.ab;
        com.igexin.c.a.c.a.a("-> CoreRuntimeInfo.opAliasTimes:" + e.ab, new Object[0]);
        if (e.ab < 100) {
            com.igexin.c.a.c.a.a("start bindAlias ###", new Object[0]);
            e.aa = jCurrentTimeMillis;
            com.igexin.push.core.e.f.a().a(e.ab + 1);
            a(str, str2, false, true);
            return;
        }
        com.igexin.c.a.c.a.a("PushController|bindAlias times exceed", new Object[0]);
        com.igexin.c.a.c.a.d.a().a("bindAlias : " + str + ", failed, , the number of calls per day cannot exceed 100");
        l.a().b(str2, "30003");
    }
}
