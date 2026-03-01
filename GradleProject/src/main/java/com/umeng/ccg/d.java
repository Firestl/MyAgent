package com.umeng.ccg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.igexin.sdk.PushConsts;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import com.umeng.analytics.pro.ab;
import com.umeng.analytics.pro.ac;
import com.umeng.analytics.pro.ad;
import com.umeng.analytics.pro.ae;
import com.umeng.analytics.pro.af;
import com.umeng.analytics.pro.ag;
import com.umeng.analytics.pro.ah;
import com.umeng.analytics.pro.aj;
import com.umeng.analytics.pro.al;
import com.umeng.analytics.pro.an;
import com.umeng.analytics.pro.ap;
import com.umeng.analytics.pro.aq;
import com.umeng.analytics.pro.ar;
import com.umeng.analytics.pro.at;
import com.umeng.analytics.pro.au;
import com.umeng.analytics.pro.av;
import com.umeng.analytics.pro.aw;
import com.umeng.ccg.c;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import io.dcloud.common.DHInterface.IApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: Monitor.java */
/* JADX INFO: loaded from: classes2.dex */
public class d implements c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5347a = "iucc";
    public static final String b = aw.b().b(aw.C);
    public static JSONObject c = null;
    public static final String[] d = {com.umeng.ccg.a.f, com.umeng.ccg.a.g, com.umeng.ccg.a.h};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ArrayList<ac> f5348e = null;
    public static ArrayList<ac> f = null;
    public static ArrayList<ac> g = null;
    public static c j = new c();
    public volatile String h = "";
    public Map<String, a> i = new HashMap();

    /* JADX INFO: compiled from: Monitor.java */
    public class a {
        public JSONArray b;
        public String c;

        public a(JSONArray jSONArray, String str) {
            this.b = jSONArray;
            this.c = str;
        }

        public JSONArray a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }
    }

    /* JADX INFO: compiled from: Monitor.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f5351a = new d();
    }

    /* JADX INFO: compiled from: Monitor.java */
    public static class c extends BroadcastReceiver {
        public long a(ArrayList<ac> arrayList) {
            if (arrayList != null && arrayList.size() > 0) {
                for (int i = 0; i < arrayList.size(); i++) {
                    ac acVar = arrayList.get(i);
                    if (acVar instanceof ae) {
                        return ((ae) acVar).c();
                    }
                }
            }
            return 0L;
        }

        public boolean b(ArrayList<ac> arrayList) {
            if (arrayList == null || arrayList.size() <= 0) {
                return false;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).b()) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (action.equals("android.intent.action.SCREEN_ON")) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv intent : ACTION_SCREEN_ON");
                    if (b(d.f5348e)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "report screen_on event.");
                        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 301, d.a(), null, a(d.f5348e) * 1000);
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "don't report screen_on event.");
                    }
                }
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv intent : ACTION_SCREEN_OFF");
                    if (b(d.f)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "report screen_off event.");
                        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), com.umeng.ccg.c.o, d.a(), null, a(d.f) * 1000);
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "don't report screen_off event.");
                    }
                }
                if (action.equals(PushConsts.ACTION_BROADCAST_USER_PRESENT)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv intent : ACTION_USER_PRESENT");
                    if (!b(d.g)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "don't report screen_unlock event.");
                        return;
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "report screen_unlock event.");
                    com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), com.umeng.ccg.c.p, d.a(), null, a(d.g) * 1000);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(Context context, String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(str);
        context.registerReceiver(j, intentFilter);
    }

    private String e(Context context) {
        try {
            SharedPreferences sharedPreferencesA = at.a(context);
            return sharedPreferencesA != null ? sharedPreferencesA.getString(at.f5178e, "") : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private void f() {
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                sharedPreferencesA.edit().putString(at.f, new JSONObject(ar.a()).toString()).commit();
            }
        } catch (Throwable unused) {
        }
    }

    private boolean g() {
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                return !TextUtils.isEmpty(sharedPreferencesA.getString(at.g, ""));
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private long b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("ts")) {
            try {
                return jSONObject.optLong("ts");
            } catch (Throwable unused) {
            }
        }
        return 0L;
    }

    private void c(Context context) {
        ImprintHandler.getImprintService(context).registImprintCallback(f5347a, new UMImprintChangeCallback() { // from class: com.umeng.ccg.d.1
            @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
            public void onImprintValueChanged(String str, String str2) {
                com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 107, d.a(), str2);
            }
        });
    }

    private Long d(Context context) {
        try {
            SharedPreferences sharedPreferencesA = at.a(context);
            if (sharedPreferencesA != null) {
                return Long.valueOf(sharedPreferencesA.getLong(at.d, 0L));
            }
            return 0L;
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject != null && (jSONObject instanceof JSONObject) && jSONObject.has(com.umeng.ccg.a.f5341a)) {
            try {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(com.umeng.ccg.a.f5341a);
                ab abVarA = jSONObjectOptJSONObject.has(com.umeng.ccg.a.b) ? a(com.umeng.ccg.a.b, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.b)) : null;
                ab abVarA2 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.c) ? a(com.umeng.ccg.a.c, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.c)) : null;
                ab abVarA3 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.d) ? a(com.umeng.ccg.a.d, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.d)) : null;
                ab abVarA4 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.f5342e) ? a(com.umeng.ccg.a.f5342e, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.f5342e)) : null;
                ab abVarA5 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.f) ? a(com.umeng.ccg.a.f, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.f)) : null;
                ab abVarA6 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.g) ? a(com.umeng.ccg.a.g, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.g)) : null;
                ab abVarA7 = jSONObjectOptJSONObject.has(com.umeng.ccg.a.h) ? a(com.umeng.ccg.a.h, jSONObjectOptJSONObject.optJSONObject(com.umeng.ccg.a.h)) : null;
                ArrayList arrayList = new ArrayList();
                if (abVarA != null) {
                    arrayList.add(abVarA);
                }
                if (abVarA2 != null) {
                    arrayList.add(abVarA2);
                }
                if (abVarA3 != null) {
                    arrayList.add(abVarA3);
                }
                if (abVarA4 != null) {
                    arrayList.add(abVarA4);
                }
                if (abVarA5 != null) {
                    arrayList.add(abVarA5);
                }
                if (abVarA6 != null) {
                    arrayList.add(abVarA6);
                }
                if (abVarA7 != null) {
                    arrayList.add(abVarA7);
                }
                com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 202, a(), arrayList);
            } catch (Throwable unused) {
            }
        }
    }

    private boolean e() {
        SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
        if (sharedPreferencesA != null) {
            String string = sharedPreferencesA.getString(at.f, "");
            if (TextUtils.isEmpty(string)) {
                f();
                return false;
            }
            try {
                if (!ar.a().keySet().equals(ar.a(new JSONObject(string)).keySet())) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static d a() {
        return b.f5351a;
    }

    public synchronized JSONObject b(Context context) {
        FileInputStream fileInputStreamOpenFileInput;
        JSONObject jSONObject = null;
        try {
            if (!new File(context.getFilesDir(), b).exists()) {
                return null;
            }
            try {
                fileInputStreamOpenFileInput = context.openFileInput(b);
                try {
                    JSONObject jSONObject2 = new JSONObject(new String(av.a(HelperUtils.readStreamToByteArray(fileInputStreamOpenFileInput), UMConfigure.sAppkey.getBytes())));
                    try {
                        ar.a(fileInputStreamOpenFileInput);
                    } catch (Throwable unused) {
                    }
                    jSONObject = jSONObject2;
                } catch (Throwable unused2) {
                    ar.a(fileInputStreamOpenFileInput);
                }
            } catch (Throwable unused3) {
                fileInputStreamOpenFileInput = null;
            }
        } catch (Throwable unused4) {
        }
        return jSONObject;
    }

    public void a(Context context) {
        com.umeng.ccg.c.a(context, 105, a(), null);
    }

    private boolean a(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("code")) {
            return false;
        }
        try {
            if (200 == Integer.valueOf(jSONObject.optInt("code")).intValue() && jSONObject.has(com.umeng.ccg.a.f5341a)) {
                return jSONObject.has("ts");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private synchronized void a(Context context, JSONObject jSONObject, String str) {
        long jB;
        byte[] bArrA;
        try {
            jB = b(jSONObject);
            bArrA = av.a(jSONObject.toString().getBytes(), UMConfigure.sAppkey.getBytes());
        } catch (Throwable unused) {
        }
        if (bArrA != null && bArrA.length > 1) {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getFilesDir(), b));
            try {
                fileOutputStream.write(bArrA);
                fileOutputStream.flush();
                ar.a(fileOutputStream);
                a(context, str, jB);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "saveConfigFile success.");
            } catch (Throwable th) {
                ar.a(fileOutputStream);
                throw th;
            }
        }
    }

    private void b(String str) {
        String str2 = at.b + str;
        SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
        if (sharedPreferencesA != null) {
            sharedPreferencesA.edit().putLong(str2, System.currentTimeMillis()).commit();
        }
    }

    private void a(String str, ac acVar) {
        if (com.umeng.ccg.a.f.equalsIgnoreCase(str)) {
            if (f5348e == null) {
                f5348e = new ArrayList<>();
            }
            f5348e.add(acVar);
        }
        if (com.umeng.ccg.a.g.equalsIgnoreCase(str)) {
            if (f == null) {
                f = new ArrayList<>();
            }
            f.add(acVar);
        }
        if (com.umeng.ccg.a.h.equalsIgnoreCase(str)) {
            if (g == null) {
                g = new ArrayList<>();
            }
            g.add(acVar);
        }
    }

    private ab a(String str, JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray;
        String str2;
        String str3;
        ab abVar;
        JSONArray jSONArrayOptJSONArray2;
        if (jSONObject != null && (jSONObject instanceof JSONObject)) {
            try {
                if (jSONObject.has(com.umeng.ccg.a.i) && (jSONArrayOptJSONArray = jSONObject.optJSONArray(com.umeng.ccg.a.i)) != null && jSONArrayOptJSONArray.length() > 0) {
                    JSONObject jSONObject2 = (JSONObject) jSONArrayOptJSONArray.get(0);
                    boolean zHas = jSONObject2.has(com.umeng.ccg.a.j);
                    boolean zHas2 = jSONObject2.has(com.umeng.ccg.a.m);
                    boolean zHas3 = jSONObject2.has(com.umeng.ccg.a.n);
                    if (!zHas || !zHas2 || !zHas3) {
                        return null;
                    }
                    try {
                        int iOptInt = jSONObject2.optInt(com.umeng.ccg.a.j);
                        long jOptLong = jSONObject2.optLong(com.umeng.ccg.a.m);
                        long jOptLong2 = jSONObject2.optLong(com.umeng.ccg.a.n);
                        String strOptString = jSONObject2.optString(com.umeng.ccg.a.o);
                        ArrayList arrayList = new ArrayList();
                        if (jSONObject2.has(com.umeng.ccg.a.k)) {
                            JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray(com.umeng.ccg.a.k);
                            str2 = "action";
                            HashSet hashSet = new HashSet();
                            if (jSONArrayOptJSONArray3 != null) {
                                str3 = com.umeng.ccg.a.r;
                                int i = 0;
                                for (int length = jSONArrayOptJSONArray3.length(); i < length; length = length) {
                                    hashSet.add(Integer.valueOf(jSONArrayOptJSONArray3.getInt(i)));
                                    i++;
                                }
                            } else {
                                str3 = com.umeng.ccg.a.r;
                            }
                            if (hashSet.size() > 0) {
                                al alVar = new al(hashSet);
                                if (Arrays.asList(d).contains(str)) {
                                    a(str, alVar);
                                } else {
                                    arrayList.add(alVar);
                                }
                            }
                        } else {
                            str2 = "action";
                            str3 = com.umeng.ccg.a.r;
                        }
                        if (jSONObject2.has(com.umeng.ccg.a.l)) {
                            String strOptString2 = jSONObject2.optString(com.umeng.ccg.a.l);
                            if (!TextUtils.isEmpty(strOptString2)) {
                                aj ajVar = new aj(strOptString2);
                                HashSet hashSet2 = new HashSet();
                                for (int i2 = 1; i2 <= 24; i2++) {
                                    if (ajVar.a(i2)) {
                                        hashSet2.add(Integer.valueOf(i2));
                                    }
                                }
                                if (hashSet2.size() > 0) {
                                    af afVar = new af(hashSet2);
                                    if (Arrays.asList(d).contains(str)) {
                                        a(str, afVar);
                                    } else {
                                        arrayList.add(afVar);
                                    }
                                }
                            }
                        }
                        arrayList.add(new ah(iOptInt));
                        ag agVar = new ag(str, jOptLong);
                        if (Arrays.asList(d).contains(str)) {
                            a(str, agVar);
                        } else {
                            arrayList.add(agVar);
                        }
                        ae aeVar = new ae(jOptLong2);
                        if (Arrays.asList(d).contains(str)) {
                            a(str, aeVar);
                            arrayList.add(aeVar);
                        } else {
                            arrayList.add(aeVar);
                        }
                        if (com.umeng.ccg.a.f5342e.equals(str)) {
                            abVar = new ad(str, arrayList);
                        } else {
                            abVar = new ab(str, arrayList);
                        }
                        try {
                            abVar.a(strOptString);
                            String str4 = "";
                            String str5 = str3;
                            if (jSONObject.has(str5) && (jSONArrayOptJSONArray2 = jSONObject.optJSONArray(str5)) != null && (jSONArrayOptJSONArray2 instanceof JSONArray)) {
                                if (this.i != null && !this.i.containsKey(str)) {
                                    this.i.put(str, new a(new JSONArray(jSONArrayOptJSONArray2.toString()), strOptString));
                                }
                                int length2 = jSONArrayOptJSONArray2.length();
                                for (int i3 = 0; i3 < jSONArrayOptJSONArray2.length(); i3++) {
                                    str4 = str4 + jSONArrayOptJSONArray2.getString(i3);
                                    if (i3 < length2 - 1) {
                                        str4 = str4 + ",";
                                    }
                                }
                            }
                            abVar.b(str4);
                            if (com.umeng.ccg.a.f5342e.equals(str) && (abVar instanceof ad)) {
                                String str6 = str2;
                                if (jSONObject2.has(str6)) {
                                    ((ad) abVar).d(jSONObject2.optString(str6));
                                }
                                if (jSONObject2.has(com.umeng.ccg.a.s)) {
                                    ((ad) abVar).c(jSONObject2.optString(com.umeng.ccg.a.s));
                                }
                            }
                        } catch (Throwable unused) {
                        }
                        return abVar;
                    } catch (Throwable unused2) {
                        return null;
                    }
                }
            } catch (Throwable unused3) {
            }
        }
        return null;
    }

    private void a(Context context, String str, long j2) {
        SharedPreferences sharedPreferencesA;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String[] strArrSplit = str.split(CellDataManager.VIRTUAL_COMPONENT_SEPRATOR);
            if (strArrSplit.length != 4 || (sharedPreferencesA = at.a(context)) == null) {
                return;
            }
            long j3 = Long.parseLong(strArrSplit[0]);
            String str2 = strArrSplit[1];
            SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
            editorEdit.putLong(at.c, j2);
            editorEdit.putLong(at.d, j3);
            editorEdit.putString(at.f5178e, str2).commit();
            UMRTLog.i(UMRTLog.RTLOG_TAG, "updateTsS1S2 : ts = " + j2 + "; s1 = " + j3 + "; s2 = " + str2);
        } catch (Throwable unused) {
        }
    }

    private void a(String str) {
        try {
            String[] strArrSplit = str.split(CellDataManager.VIRTUAL_COMPONENT_SEPRATOR);
            if (strArrSplit.length != 4) {
                return;
            }
            long j2 = Long.parseLong(strArrSplit[0]);
            String str2 = strArrSplit[1];
            if (!TextUtils.isEmpty(this.h)) {
                String[] strArrSplit2 = this.h.split(CellDataManager.VIRTUAL_COMPONENT_SEPRATOR);
                if (strArrSplit2.length == 2) {
                    long j3 = Long.parseLong(strArrSplit2[0]);
                    String str3 = strArrSplit2[1];
                    if (j3 == j2 && str3.equalsIgnoreCase(str2)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "重复的iucc S1 and S2, 忽略本次更新，不发起fetch。");
                        return;
                    }
                }
            }
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                if (sharedPreferencesA.getLong(at.c, 0L) != j2) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "local config ts != iuccS1, send FETCH_NEW_CONFIG msg.");
                    this.h = String.valueOf(j2) + CellDataManager.VIRTUAL_COMPONENT_SEPRATOR + str2;
                    com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 101, a(), str);
                    return;
                }
                d(UMGlobalContext.getAppContext());
                if (e(UMGlobalContext.getAppContext()).equalsIgnoreCase(str2)) {
                    return;
                }
                UMRTLog.i(UMRTLog.RTLOG_TAG, "local S2 != iuccS2, send FETCH_NEW_CONFIG msg.");
                this.h = String.valueOf(j2) + CellDataManager.VIRTUAL_COMPONENT_SEPRATOR + str2;
                com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 101, a(), str);
            }
        } catch (Throwable unused) {
        }
    }

    private void a(boolean z) {
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                if (z) {
                    editorEdit.putString(at.g, "1").commit();
                } else {
                    editorEdit.putString(at.g, "").commit();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v15, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r3v19, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v59 */
    @Override // com.umeng.ccg.c.a
    public void a(Object obj, int i) {
        Integer numValueOf;
        ArrayList arrayList;
        int size;
        a aVar;
        a aVar2;
        a aVar3;
        JSONObject jSONObjectB = null;
        boolean z = true;
        int i2 = 0;
        try {
            switch (i) {
                case 101:
                    if (obj != null && (obj instanceof String)) {
                        String str = (String) obj;
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "[workEvent]: recv FETCH_NEW_CONFIG msg. source iucc is: " + str);
                        JSONObject jSONObjectA = an.a(UMGlobalContext.getAppContext(), str);
                        if (jSONObjectA != null) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "[imprint] send request. body: " + jSONObjectA.toString());
                            au.a(new aq(aq.f5174a, jSONObjectA, str), 0L, TimeUnit.SECONDS);
                        }
                        if (g()) {
                            c(UMGlobalContext.getAppContext());
                            String strImprintProperty = UMEnvelopeBuild.imprintProperty(UMGlobalContext.getAppContext(), f5347a, "");
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "manual check iucc value: " + strImprintProperty);
                            a(strImprintProperty);
                        }
                        break;
                    }
                    break;
                case 102:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "[workEvent]: recv FETCH_RESPONSE msg.");
                    this.h = "";
                    if (obj != null && (obj instanceof JSONObject)) {
                        JSONObject jSONObject = (JSONObject) obj;
                        if (a(jSONObject.optJSONObject(com.igexin.push.core.b.Y))) {
                            com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 103, a(), jSONObject);
                        } else {
                            com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 104, a(), null);
                        }
                    } else {
                        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 104, a(), null);
                    }
                    break;
                case 103:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "[workEvent]: recv FETCH_SUCCESS msg.");
                    Context appContext = UMGlobalContext.getAppContext();
                    if (obj != null && (obj instanceof JSONObject)) {
                        JSONObject jSONObject2 = (JSONObject) obj;
                        JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject(com.igexin.push.core.b.Y);
                        String strOptString = jSONObject2.optString("sourceIucc");
                        if (jSONObjectOptJSONObject != null) {
                            if (g()) {
                                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> 成功拉取云配参数后，检测到should fetch标志，清除此标志。更新SDK类型集缓存值");
                                f();
                                a(false);
                            }
                            a(appContext, jSONObjectOptJSONObject, strOptString);
                            CcgAgent.notifyConfigChanged(jSONObjectOptJSONObject);
                        }
                        break;
                    }
                    break;
                case 104:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "[workEvent]: recv FETCH_FAILED msg.");
                    break;
                case 105:
                    String[] collectItemList = CcgAgent.getCollectItemList();
                    int length = collectItemList.length;
                    ?? jSONObject3 = 0;
                    while (jSONObject3 < length) {
                        String str2 = collectItemList[jSONObject3];
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "[forbid_sdk] 采集项: " + str2 + "; 值: " + CcgAgent.getForbidSdkArray(str2).toString());
                        jSONObject3++;
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "[workEvent]: recv LOAD_CONFIG msg.");
                    Integer num = 0;
                    try {
                        try {
                            jSONObjectB = b(UMGlobalContext.getAppContext());
                            if (jSONObjectB != null && (jSONObjectB instanceof JSONObject) && a(jSONObjectB)) {
                                numValueOf = Integer.valueOf(num.intValue() | 1);
                            } else {
                                numValueOf = Integer.valueOf(num.intValue() | 0);
                            }
                            jSONObject3 = new JSONObject();
                            jSONObject3.put("result", numValueOf);
                        } catch (Throwable unused) {
                            jSONObject3 = new JSONObject();
                            jSONObject3.put("result", num);
                            if (0 != 0) {
                                jSONObject3 = jSONObject3;
                                if (jSONObjectB instanceof JSONObject) {
                                }
                            }
                        }
                        if (jSONObjectB != null) {
                            jSONObject3 = jSONObject3;
                            if (jSONObjectB instanceof JSONObject) {
                                jSONObject3.put(com.igexin.push.core.b.Y, jSONObjectB);
                            }
                        }
                        break;
                    } catch (Throwable unused2) {
                    }
                    com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 106, a(), jSONObject3);
                    break;
                case 106:
                    if (obj != null && (obj instanceof JSONObject)) {
                        JSONObject jSONObject4 = (JSONObject) obj;
                        if (jSONObject4 != null && jSONObject4.has("result")) {
                            if ((jSONObject4.optInt("result") & 1) != 0 && jSONObject4.has(com.igexin.push.core.b.Y)) {
                                JSONObject jSONObjectOptJSONObject2 = jSONObject4.optJSONObject(com.igexin.push.core.b.Y);
                                c = jSONObjectOptJSONObject2;
                                if (jSONObjectOptJSONObject2 != null) {
                                    CcgAgent.notifyConfigReady(jSONObjectOptJSONObject2);
                                    i2 = 1;
                                }
                            }
                            if (i2 == 0) {
                                CcgAgent.notifyConfigReady(null);
                            }
                        }
                        if (e()) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> 检测到集成的SDK类型集合发生变化，发起云配参数拉取请求(设置本地should fetch标志).");
                            String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(UMGlobalContext.getAppContext(), f5347a, "");
                            a(true);
                            com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 101, a(), strImprintProperty2);
                        } else {
                            c(UMGlobalContext.getAppContext());
                            String strImprintProperty3 = UMEnvelopeBuild.imprintProperty(UMGlobalContext.getAppContext(), f5347a, "");
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "manual check iucc value: " + strImprintProperty3);
                            a(strImprintProperty3);
                        }
                        break;
                    }
                    break;
                case 107:
                    if (obj != null) {
                        try {
                            if (obj instanceof String) {
                                String str3 = (String) obj;
                                UMRTLog.i(UMRTLog.RTLOG_TAG, "[IMPRINT_IUCC_CHANGED] iucc : " + str3);
                                a(str3);
                            }
                        } catch (Throwable th) {
                            UMRTLog.e(UMRTLog.RTLOG_TAG, "[imprint] process error " + th.getMessage());
                            return;
                        }
                    }
                    break;
                default:
                    switch (i) {
                        case 201:
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "recv PARSE_CONFIG msg.");
                            if (obj != null && (obj instanceof JSONObject)) {
                                c((JSONObject) obj);
                                break;
                            }
                            break;
                        case 202:
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "recv COLLECTION_JUDGMENT msg.");
                            if (obj != null && (obj instanceof ArrayList) && (size = (arrayList = (ArrayList) obj).size()) > 0) {
                                while (i2 < size) {
                                    ab abVar = (ab) arrayList.get(i2);
                                    JSONObject jSONObjectA2 = abVar.a(abVar.a(), null);
                                    if (jSONObjectA2 != null) {
                                        long jOptLong = !Arrays.asList(d).contains(abVar.a()) ? jSONObjectA2.optLong(IApp.ConfigProperty.CONFIG_DELAY) * 1000 : 0L;
                                        jSONObjectA2.remove(IApp.ConfigProperty.CONFIG_DELAY);
                                        UMRTLog.i(UMRTLog.RTLOG_TAG, "send START_COLLECT msg, delayTs = " + jOptLong);
                                        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 203, a(), jSONObjectA2, jOptLong);
                                    }
                                    i2++;
                                }
                                break;
                            }
                            break;
                        case 203:
                            if (obj != null && (obj instanceof JSONObject)) {
                                JSONObject jSONObject5 = (JSONObject) obj;
                                String strOptString2 = jSONObject5.optString("actionName");
                                UMRTLog.i(UMRTLog.RTLOG_TAG, "recv START_COLLECT msg. name is : " + strOptString2);
                                if (com.umeng.ccg.b.a(strOptString2)) {
                                    String string = jSONObject5.toString();
                                    if (Arrays.asList(d).contains(strOptString2)) {
                                        if (com.umeng.ccg.a.f.equalsIgnoreCase(strOptString2)) {
                                            UMRTLog.i(UMRTLog.RTLOG_TAG, "register Intent.ACTION_SCREEN_ON");
                                            a(UMGlobalContext.getAppContext(), "android.intent.action.SCREEN_ON");
                                        }
                                        if (com.umeng.ccg.a.g.equalsIgnoreCase(strOptString2)) {
                                            UMRTLog.i(UMRTLog.RTLOG_TAG, "register Intent.ACTION_SCREEN_OFF");
                                            a(UMGlobalContext.getAppContext(), "android.intent.action.SCREEN_OFF");
                                        }
                                        if (com.umeng.ccg.a.h.equalsIgnoreCase(strOptString2)) {
                                            UMRTLog.i(UMRTLog.RTLOG_TAG, "register Intent.ACTION_USER_PRESENT");
                                            a(UMGlobalContext.getAppContext(), PushConsts.ACTION_BROADCAST_USER_PRESENT);
                                        }
                                    } else {
                                        b(strOptString2);
                                        if (CcgAgent.hasRegistedActionInfo()) {
                                            if (CcgAgent.getActionInfo("anti") == null) {
                                                z = false;
                                            }
                                            String strOptString3 = jSONObject5.optString(com.umeng.ccg.a.p);
                                            if (TextUtils.isEmpty(strOptString3)) {
                                                UMRTLog.i(UMRTLog.RTLOG_TAG, "忽略 本次采集项[" + strOptString2 + "]采集请求.");
                                            } else {
                                                ActionInfo actionInfo = CcgAgent.getActionInfo(strOptString3);
                                                if (actionInfo != null) {
                                                    UMRTLog.i(UMRTLog.RTLOG_TAG, "调用[" + strOptString3 + "] onCommand接口方法, 参数: " + jSONObject5.toString());
                                                    actionInfo.onCommand(UMGlobalContext.getAppContext(), strOptString2, jSONObject5);
                                                }
                                            }
                                            if (!z) {
                                                ar.a(UMGlobalContext.getAppContext(), string);
                                            }
                                        } else {
                                            ar.a(UMGlobalContext.getAppContext(), string);
                                        }
                                    }
                                } else {
                                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Local switch of [" + strOptString2 + "] is off, ignore this command.");
                                }
                                break;
                            }
                            break;
                        default:
                            switch (i) {
                                case 301:
                                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv REPORT_SCREEN_ON msg.");
                                    b(com.umeng.ccg.a.f);
                                    if (this.i.containsKey(com.umeng.ccg.a.f) && (aVar = this.i.get(com.umeng.ccg.a.f)) != null) {
                                        JSONObject jSONObjectA3 = an.a(UMGlobalContext.getAppContext(), 1, aVar.a(), aVar.b());
                                        UMRTLog.i(UMRTLog.RTLOG_TAG, "screen_on event param: " + jSONObjectA3.toString());
                                        au.a(new ap(ap.f5173a, jSONObjectA3), 0L, TimeUnit.SECONDS);
                                        break;
                                    }
                                    break;
                                case com.umeng.ccg.c.o /* 302 */:
                                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv REPORT_SCREEN_OFF msg.");
                                    b(com.umeng.ccg.a.g);
                                    if (this.i.containsKey(com.umeng.ccg.a.g) && (aVar2 = this.i.get(com.umeng.ccg.a.g)) != null) {
                                        JSONObject jSONObjectA4 = an.a(UMGlobalContext.getAppContext(), 3, aVar2.a(), aVar2.b());
                                        UMRTLog.i(UMRTLog.RTLOG_TAG, "screen_off event param: " + jSONObjectA4.toString());
                                        au.a(new ap(ap.f5173a, jSONObjectA4), 0L, TimeUnit.SECONDS);
                                        break;
                                    }
                                    break;
                                case com.umeng.ccg.c.p /* 303 */:
                                    UMRTLog.i(UMRTLog.RTLOG_TAG, "recv REPORT_SCREEN_UNLOCK msg.");
                                    b(com.umeng.ccg.a.h);
                                    if (this.i.containsKey(com.umeng.ccg.a.h) && (aVar3 = this.i.get(com.umeng.ccg.a.h)) != null) {
                                        JSONObject jSONObjectA5 = an.a(UMGlobalContext.getAppContext(), 2, aVar3.a(), aVar3.b());
                                        UMRTLog.i(UMRTLog.RTLOG_TAG, "screen_unlock event param: " + jSONObjectA5.toString());
                                        au.a(new ap(ap.f5173a, jSONObjectA5), 0L, TimeUnit.SECONDS);
                                        break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } catch (Throwable unused3) {
        }
    }
}
