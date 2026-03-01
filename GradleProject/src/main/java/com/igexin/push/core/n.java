package com.igexin.push.core;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.push.core.d;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.main.FeedbackImpl;
import com.umeng.analytics.AnalyticsConfig;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class n {
    public static final String b = "PushMessageExecutor";
    public static Set<String> d;
    public static volatile n f;
    public final Map<String, PushMessageInterface> c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, String> f3498a = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<String, String> f3499e = new ConcurrentHashMap();

    public n() {
        d = new HashSet();
        this.c = new HashMap();
        d.add(b.s);
        d.add("notification");
        d.add(b.o);
        d.add(b.p);
        d.add(b.q);
        d.add(b.r);
        d.add(b.m);
        d.add(b.u);
        d.add(b.v);
        d.add(b.w);
        d.add(b.x);
        d.add(b.y);
        d.add(b.t);
        d.add(b.z);
    }

    public static n a() {
        if (f == null) {
            synchronized (n.class) {
                if (f == null) {
                    f = new n();
                }
            }
        }
        return f;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.igexin.push.extension.mod.PushMessageInterface a(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.n.a(java.lang.String):com.igexin.push.extension.mod.PushMessageInterface");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.igexin.push.extension.mod.PushMessageInterface b(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.n.b(java.lang.String):com.igexin.push.extension.mod.PushMessageInterface");
    }

    public static void b() {
        try {
            if (!TextUtils.isEmpty(com.igexin.push.config.d.C) && !"none".equals(com.igexin.push.config.d.C)) {
                List<String> listAsList = Arrays.asList(com.igexin.push.config.d.C.split(","));
                if (listAsList.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<Map.Entry<String, PushTaskBean>> it = e.ah.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, PushTaskBean> next = it.next();
                    String key = next.getKey();
                    PushTaskBean value = next.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        for (String str : listAsList) {
                            if (!TextUtils.isEmpty(str) && key.startsWith(str)) {
                                arrayList.add(value.getTaskId());
                                it.remove();
                            }
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    strArr[i] = (String) arrayList.get(i);
                }
                d.a.f3384a.i.a("message", new String[]{"taskid"}, strArr);
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    public static void b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        Message messageObtain = Message.obtain();
        messageObtain.what = b.V;
        messageObtain.obj = bundle;
        d.a.f3384a.a(messageObtain);
    }

    public static void b(JSONObject jSONObject, PushTaskBean pushTaskBean) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("condition");
            HashMap map = new HashMap();
            if (jSONObject2.has("wifi")) {
                map.put("wifi", jSONObject2.getString("wifi"));
            }
            if (jSONObject2.has("screenOn")) {
                map.put("screenOn", jSONObject2.getString("screenOn"));
            }
            if (jSONObject2.has("ssid")) {
                map.put("ssid", jSONObject2.getString("ssid"));
                if (jSONObject2.has("bssid")) {
                    map.put("bssid", jSONObject2.getString("bssid"));
                }
            }
            if (jSONObject2.has("duration")) {
                String string = jSONObject2.getString("duration");
                if (string.contains("-")) {
                    int iIndexOf = string.indexOf("-");
                    String strSubstring = string.substring(0, iIndexOf);
                    String strSubstring2 = string.substring(iIndexOf + 1, string.length());
                    map.put(AnalyticsConfig.RTD_START_TIME, strSubstring);
                    map.put("endTime", strSubstring2);
                }
            }
            if (jSONObject2.has("netConnected")) {
                map.put("netConnected", jSONObject2.getString("netConnected"));
            }
            if (jSONObject2.has("expireTime")) {
                String string2 = jSONObject2.getString("expireTime");
                if (!TextUtils.isEmpty(string2) && TextUtils.isDigitsOnly(string2)) {
                    map.put("expireTime", string2);
                }
            }
            pushTaskBean.setConditionMap(map);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    private void c(String str, String str2) {
        if (str2 == null || str == null) {
            return;
        }
        try {
            com.igexin.push.core.a.b.d();
            String strA = com.igexin.push.core.a.b.a(str, str2);
            PushTaskBean pushTaskBean = e.ah.get(strA);
            if (pushTaskBean == null) {
                return;
            }
            if (pushTaskBean.getStatus() == b.ah) {
                com.igexin.c.a.c.a.b(b, " has execute ".concat(String.valueOf(strA)));
                return;
            }
            pushTaskBean.setStatus(b.ah);
            com.igexin.c.a.c.a.b(b, " do processActionExecute ".concat(String.valueOf(strA)));
            if (a(str, str2) != PushMessageInterface.ActionPrepareState.success) {
                pushTaskBean.setStatus(b.ag);
                return;
            }
            com.igexin.push.core.e.c.a();
            com.igexin.push.core.e.c.a(b.ah, str);
            pushTaskBean.setStatus(b.ah);
            if (a(str, str2, "1")) {
                return;
            }
            com.igexin.push.core.e.c.a();
            com.igexin.push.core.e.c.a(b.ag, str);
            pushTaskBean.setStatus(b.ag);
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    private boolean e() {
        if (e.ah.isEmpty() && e.w.getAndSet(false)) {
            Cursor cursorA = null;
            try {
                cursorA = d.a.f3384a.i.a("message", new String[]{"status"}, new String[]{"0"}, null, null);
                if (cursorA != null) {
                    while (cursorA.moveToNext()) {
                        byte[] blob = cursorA.getBlob(cursorA.getColumnIndex("msgextra"));
                        try {
                            JSONObject jSONObject = new JSONObject(new String(com.igexin.c.b.a.c(cursorA.getBlob(cursorA.getColumnIndex(com.umeng.commonsdk.internal.utils.f.f5404a)))));
                            String string = jSONObject.getString("id");
                            String string2 = jSONObject.getString("appid");
                            String string3 = jSONObject.getString("messageid");
                            String string4 = jSONObject.getString("taskid");
                            String string5 = jSONObject.getString("appkey");
                            JSONArray jSONArray = jSONObject.getJSONArray("action_chains");
                            com.igexin.push.core.a.b.d();
                            String strA = com.igexin.push.core.a.b.a(string4, string3);
                            PushTaskBean pushTaskBean = new PushTaskBean();
                            pushTaskBean.setAppid(string2);
                            pushTaskBean.setMessageId(string3);
                            pushTaskBean.setTaskId(string4);
                            pushTaskBean.setId(string);
                            pushTaskBean.setAppKey(string5);
                            pushTaskBean.setCurrentActionid(1);
                            pushTaskBean.setStatus(cursorA.getInt(cursorA.getColumnIndex("status")));
                            if (blob != null) {
                                pushTaskBean.setMsgExtra(blob);
                            }
                            if (jSONObject.has("condition")) {
                                b(jSONObject, pushTaskBean);
                            }
                            if (jSONArray.length() > 0) {
                                if (a(jSONObject, pushTaskBean)) {
                                    e.ah.put(strA, pushTaskBean);
                                } else {
                                    com.igexin.c.a.c.a.a(b, "load task from db parseActionChains error, " + jSONObject.toString());
                                    com.igexin.c.a.c.a.a("PushMessageExecutor|load task from db parseActionChains error, " + jSONObject.toString(), new Object[0]);
                                }
                            }
                        } catch (JSONException e2) {
                            com.igexin.c.a.c.a.a(e2);
                        }
                    }
                }
            } catch (Throwable th) {
                try {
                    com.igexin.c.a.c.a.a(th);
                } finally {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            }
            if (cursorA != null) {
            }
        }
        return e.ah.isEmpty();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00fe A[DONT_GENERATE, PHI: r1
  0x00fe: PHI (r1v2 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v3 android.database.Cursor) binds: [B:30:0x00fc, B:26:0x00f5] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean f() {
        /*
            Method dump skipped, instruction units count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.n.f():boolean");
    }

    private void g() {
        try {
            List<ScanResult> listJ = com.igexin.push.g.n.j();
            this.f3499e.clear();
            if (listJ == null || listJ.isEmpty()) {
                return;
            }
            for (int i = 0; i < listJ.size(); i++) {
                this.f3499e.put(listJ.get(i).BSSID, listJ.get(i).SSID);
                String str = listJ.get(i).BSSID;
                String str2 = listJ.get(i).SSID;
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    public final PushMessageInterface.ActionPrepareState a(String str, String str2) {
        PushMessageInterface.ActionPrepareState actionPrepareState = PushMessageInterface.ActionPrepareState.success;
        com.igexin.push.core.a.b.d();
        PushTaskBean pushTaskBean = e.ah.get(com.igexin.push.core.a.b.a(str, str2));
        if (pushTaskBean == null) {
            return PushMessageInterface.ActionPrepareState.stop;
        }
        int i = 0;
        boolean z = false;
        for (BaseActionBean baseActionBean : pushTaskBean.getActionChains()) {
            PushMessageInterface.ActionPrepareState actionPrepareStatePrepareExecuteAction = PushMessageInterface.ActionPrepareState.stop;
            if (baseActionBean == null) {
                return actionPrepareStatePrepareExecuteAction;
            }
            if (!z && b.r.equals(baseActionBean.getType())) {
                z = true;
            }
            PushMessageInterface pushMessageInterfaceA = a(baseActionBean.getType());
            if (pushMessageInterfaceA != null) {
                actionPrepareStatePrepareExecuteAction = pushMessageInterfaceA.prepareExecuteAction(pushTaskBean, baseActionBean);
            } else {
                baseActionBean.getType();
            }
            if (actionPrepareState == PushMessageInterface.ActionPrepareState.success) {
                actionPrepareState = actionPrepareStatePrepareExecuteAction;
            }
            if (actionPrepareStatePrepareExecuteAction == PushMessageInterface.ActionPrepareState.wait) {
                i++;
            }
        }
        return (i == 0 || z || e.a(str, Integer.valueOf(i))) ? actionPrepareState : PushMessageInterface.ActionPrepareState.success;
    }

    public final void a(Intent intent) {
        String stringExtra = intent.getStringExtra("taskid");
        String stringExtra2 = intent.getStringExtra("messageid");
        String stringExtra3 = intent.getStringExtra("actionid");
        String stringExtra4 = intent.getStringExtra("accesstoken");
        String stringExtra5 = intent.getStringExtra("url");
        String stringExtra6 = intent.getStringExtra(RemoteMessageConst.Notification.INTENT_URI);
        String stringExtra7 = intent.getStringExtra(AssistPushConsts.MSG_TYPE_PAYLOAD);
        String stringExtra8 = intent.hasExtra("title") ? intent.getStringExtra("title") : "";
        String stringExtra9 = intent.hasExtra("content") ? intent.getStringExtra("content") : "";
        int intExtra = intent.getIntExtra("notifID", 0);
        NotificationManager notificationManager = (NotificationManager) e.l.getSystemService("notification");
        if (intExtra != 0) {
            notificationManager.cancel(intExtra);
        } else if (e.ai.containsKey(stringExtra)) {
            intExtra = e.ai.get(stringExtra).intValue();
            notificationManager.cancel(intExtra);
        }
        e.ai.remove(stringExtra);
        if (stringExtra4.equals(e.an)) {
            l.a().b(stringExtra, stringExtra2, stringExtra8, stringExtra9, stringExtra5, stringExtra6, stringExtra7);
            b(stringExtra, stringExtra2, stringExtra3);
        }
    }

    public final boolean a(String str, String str2, String str3) {
        if (Thread.currentThread().getId() == d.a.f3384a.a()) {
            return b(str, str2, str3);
        }
        Bundle bundle = new Bundle();
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        bundle.putString("actionid", str3);
        Message messageObtain = Message.obtain();
        messageObtain.what = b.S;
        messageObtain.obj = bundle;
        return d.a.f3384a.a(messageObtain);
    }

    public final boolean a(Map<String, String> map, String str, PushTaskBean pushTaskBean) {
        String str2;
        if (!com.igexin.push.g.c.b(str)) {
            if (map != null && map.size() != 0) {
                if ((!map.containsKey("expireTime") || Long.parseLong(map.get("expireTime")) >= System.currentTimeMillis()) && (!map.containsKey("endTime") || Long.parseLong(map.get("endTime")) >= System.currentTimeMillis())) {
                    if (map.containsKey("wifi")) {
                        int i = Integer.parseInt(map.get("wifi"));
                        com.igexin.push.g.c.c();
                        if (i != e.x) {
                            return false;
                        }
                    }
                    if (map.containsKey("screenOn")) {
                        int i2 = Integer.parseInt(map.get("screenOn"));
                        com.igexin.push.g.c.d();
                        if (i2 != e.y) {
                            return false;
                        }
                    }
                    if (map.containsKey("ssid")) {
                        str2 = map.get("ssid");
                        try {
                            List<ScanResult> listJ = com.igexin.push.g.n.j();
                            this.f3499e.clear();
                            if (listJ != null && !listJ.isEmpty()) {
                                for (int i3 = 0; i3 < listJ.size(); i3++) {
                                    this.f3499e.put(listJ.get(i3).BSSID, listJ.get(i3).SSID);
                                    String str3 = listJ.get(i3).BSSID;
                                    String str4 = listJ.get(i3).SSID;
                                }
                            }
                        } catch (Throwable th) {
                            com.igexin.c.a.c.a.a(th);
                        }
                        if (!this.f3499e.containsValue(str2)) {
                            return false;
                        }
                    } else {
                        str2 = "";
                    }
                    if (map.containsKey("bssid")) {
                        String str5 = map.get("bssid");
                        if (!this.f3499e.containsKey(str5)) {
                            return false;
                        }
                        String str6 = this.f3499e.get(str5);
                        if (str6 != null && !str6.equals(str2)) {
                            return false;
                        }
                    }
                    if (map.containsKey(AnalyticsConfig.RTD_START_TIME) && Long.parseLong(map.get(AnalyticsConfig.RTD_START_TIME)) > System.currentTimeMillis()) {
                        return false;
                    }
                    if (map.containsKey("netConnected")) {
                        try {
                            if (Integer.parseInt(map.get("netConnected")) != com.igexin.push.g.c.e()) {
                                return false;
                            }
                        } catch (Exception e2) {
                            com.igexin.c.a.c.a.a(e2);
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        com.igexin.push.core.e.c.a();
        com.igexin.push.core.e.c.a(b.ai, str);
        pushTaskBean.setStatus(b.ah);
        return false;
    }

    public final boolean a(JSONObject jSONObject, PushTaskBean pushTaskBean) {
        com.igexin.c.a.c.a.a("PushMessageExecutor------parse pushmessage actionchain json start-------", new Object[0]);
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("action_chains");
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = ((JSONObject) jSONArray.get(i)).getString("type");
                if (!this.f3498a.containsKey(string) && !d.contains(string)) {
                    com.igexin.c.a.c.a.a("PushMessageExecutor|" + string + " not support~", new Object[0]);
                    return false;
                }
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                String string2 = jSONObject2.getString("type");
                com.igexin.c.a.c.a.a("PushMessageExecutor|start parse type = ".concat(String.valueOf(string2)), new Object[0]);
                PushMessageInterface pushMessageInterfaceA = a(string2);
                if (pushMessageInterfaceA != null) {
                    arrayList.add(pushMessageInterfaceA.parseAction(jSONObject2));
                }
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        pushTaskBean.setActionChains(arrayList);
        com.igexin.c.a.c.a.b(b, "------parse pushmessage actionchain json end-------");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x019d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019a A[PHI: r8
  0x019a: PHI (r8v4 com.igexin.push.extension.mod.BaseActionBean) = 
  (r8v3 com.igexin.push.extension.mod.BaseActionBean)
  (r8v3 com.igexin.push.extension.mod.BaseActionBean)
  (r8v7 com.igexin.push.extension.mod.BaseActionBean)
 binds: [B:43:0x0188, B:45:0x018e, B:47:0x0196] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01b6 A[Catch: all -> 0x0236, LOOP:0: B:40:0x016f->B:52:0x01b6, LOOP_END, TryCatch #1 {all -> 0x0236, blocks: (B:29:0x00ef, B:31:0x00f8, B:33:0x0110, B:35:0x014d, B:36:0x0155, B:38:0x015b, B:39:0x015e, B:40:0x016f, B:42:0x0175, B:44:0x018a, B:46:0x0190, B:51:0x019d, B:52:0x01b6, B:53:0x01b9, B:72:0x0232, B:54:0x01bd, B:56:0x01c2, B:57:0x01c8, B:60:0x01d2, B:62:0x01e0, B:63:0x01e3, B:64:0x01e7, B:66:0x01fc, B:67:0x021f, B:69:0x022d), top: B:103:0x00ef, outer: #2, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(org.json.JSONObject r19, byte[] r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 688
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.n.a(org.json.JSONObject, byte[], boolean):boolean");
    }

    public final boolean b(String str, String str2, final String str3) {
        PushMessageInterface pushMessageInterfaceA;
        com.igexin.push.core.a.b.d();
        String strA = com.igexin.push.core.a.b.a(str, str2);
        com.igexin.c.a.c.a.b(b, "executePushMessageAction taskid:" + str + ", actionid:" + str3);
        final PushTaskBean pushTaskBean = e.ah.get(strA);
        if (pushTaskBean == null) {
            Cursor cursorA = null;
            try {
                cursorA = d.a.f3384a.i.a("message", new String[]{"taskid", "messageid"}, new String[]{str, str2}, null, null);
            } catch (Throwable th) {
                try {
                    com.igexin.c.a.c.a.a(th);
                    if (cursorA != null) {
                    }
                } finally {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            }
            if (cursorA != null && cursorA.getCount() > 0) {
                while (cursorA.moveToNext()) {
                    a().a(new JSONObject(new String(com.igexin.c.b.a.c(cursorA.getBlob(cursorA.getColumnIndexOrThrow(com.umeng.commonsdk.internal.utils.f.f5404a))))), cursorA.getBlob(cursorA.getColumnIndexOrThrow("msgextra")), false);
                    PushTaskBean pushTaskBean2 = e.ah.get(str + Constants.COLON_SEPARATOR + str2);
                    if (pushTaskBean2 == null) {
                        return false;
                    }
                    pushTaskBean = pushTaskBean2;
                }
                if (cursorA != null) {
                    cursorA.close();
                }
            }
            if (cursorA != null) {
                cursorA.close();
            }
            return false;
        }
        int executeTimes = pushTaskBean.getExecuteTimes();
        if (executeTimes >= 50) {
            try {
                e.ah.remove(strA);
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
                com.igexin.c.a.c.a.a("PushMessageExecutor|" + e2.toString(), new Object[0]);
            }
            return true;
        }
        pushTaskBean.setExecuteTimes(executeTimes + 1);
        FeedbackImpl.getInstance().asyncFeedback(new Runnable() { // from class: com.igexin.push.core.n.2
            @Override // java.lang.Runnable
            public final void run() {
                FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, str3);
            }
        });
        try {
            BaseActionBean baseAction = pushTaskBean.getBaseAction(str3);
            if (baseAction != null && (pushMessageInterfaceA = a(baseAction.getType())) != null) {
                return pushMessageInterfaceA.executeAction(pushTaskBean, baseAction);
            }
        } catch (Throwable th2) {
            com.igexin.c.a.c.a.a(th2);
        }
        return false;
    }

    public final void c() {
        try {
            if (com.igexin.push.g.c.a(System.currentTimeMillis())) {
                com.igexin.c.a.c.a.b(b, "message in silent time , ignored...");
                return;
            }
            if (e()) {
                return;
            }
            for (Map.Entry<String, PushTaskBean> entry : e.ah.entrySet()) {
                try {
                    entry.getKey();
                    PushTaskBean value = entry.getValue();
                    if (value != null && value.getStatus() == b.ag) {
                        String taskId = value.getTaskId();
                        Map<String, String> conditionMap = value.getConditionMap();
                        if (conditionMap == null) {
                            return;
                        }
                        if (a(conditionMap, taskId, value)) {
                            b(taskId, value.getMessageId());
                        }
                    }
                } catch (Exception e2) {
                    com.igexin.c.a.c.a.a(e2);
                    com.igexin.c.a.c.a.a("PushMessageExecutor|" + e2.toString(), new Object[0]);
                }
            }
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
            com.igexin.c.a.c.a.a("PushMessageExecutor|" + e3.toString(), new Object[0]);
        }
    }

    public final void d() {
        com.igexin.c.a.c.a.a("PushMessageExecutor|--------checkConditionStatus the pushMessageMap from db because log gkt...", new Object[0]);
        try {
            if (com.igexin.push.g.c.a(System.currentTimeMillis())) {
                com.igexin.c.a.c.a.b(b, "message in silent time , ignored...");
                return;
            }
            if (f()) {
                return;
            }
            for (Map.Entry<String, PushTaskBean> entry : e.ah.entrySet()) {
                try {
                    entry.getKey();
                    PushTaskBean value = entry.getValue();
                    if (value != null && value.getStatus() == b.ag) {
                        String taskId = value.getTaskId();
                        Map<String, String> conditionMap = value.getConditionMap();
                        if (conditionMap == null) {
                            return;
                        }
                        if (a(conditionMap, taskId, value)) {
                            b(taskId, value.getMessageId());
                        }
                    }
                } catch (Exception e2) {
                    com.igexin.c.a.c.a.a(e2);
                    com.igexin.c.a.c.a.a("PushMessageExecutor|" + e2.toString(), new Object[0]);
                }
            }
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
            com.igexin.c.a.c.a.a("PushMessageExecutor|" + e3.toString(), new Object[0]);
        }
    }
}
