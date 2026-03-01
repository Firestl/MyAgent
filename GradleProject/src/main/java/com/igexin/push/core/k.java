package com.igexin.push.core;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Pair;
import com.igexin.push.core.d;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3490a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final String d = "LoginInteractor";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static k f3491e;

    public static k a() {
        if (f3491e == null) {
            f3491e = new k();
        }
        return f3491e;
    }

    public static void a(List<com.igexin.push.d.c.j> list) {
        if (ServiceManager.getInstance().initType == null) {
            return;
        }
        int iIntValue = ((Integer) ServiceManager.getInstance().initType.first).intValue();
        String strValueOf = String.valueOf(iIntValue);
        if (iIntValue == 1) {
            strValueOf = strValueOf + "#" + ((String) ServiceManager.getInstance().initType.second);
        }
        com.igexin.push.d.c.j jVar = new com.igexin.push.d.c.j();
        jVar.f3532a = (byte) 5;
        jVar.b = strValueOf;
        list.add(jVar);
    }

    public static int b() {
        if (!e.s || !com.igexin.push.g.c.a()) {
            com.igexin.c.a.c.a.a(d, "keyNegotiate stop ++++++++++");
            com.igexin.c.a.c.a.a("LoginInteractor|keyNegotiate stop ++++++++++", new Object[0]);
            return -1;
        }
        com.igexin.push.d.c.g gVar = new com.igexin.push.d.c.g();
        gVar.b = e.f3403a;
        int iA = d.a.f3384a.h.a("K-", gVar, true);
        com.igexin.c.a.c.a.a("LoginInteractor|keyNegotiate result=".concat(String.valueOf(iA)), new Object[0]);
        return iA < 0 ? 0 : 1;
    }

    public static void c() {
        com.igexin.c.a.c.a.d.a().a("[LoginInteractor] Start login appid = " + e.f3403a);
        if (e.t) {
            e.t = false;
            e.T = System.currentTimeMillis() + (((long) Math.abs(new Random().nextInt() % 24)) * 3600000);
        }
        com.igexin.push.c.c.a().d().d();
        if (e.z != 0) {
            com.igexin.push.d.c.i iVarD = d();
            com.igexin.c.a.c.a.a("loginReqBefore|" + iVarD.b, new Object[0]);
            com.igexin.push.e.a aVar = d.a.f3384a.h;
            StringBuilder sb = new StringBuilder("S-");
            sb.append(e.z);
            if (aVar.a(sb.toString(), iVarD, true) >= 0) {
                String str = e.A;
                com.igexin.c.a.c.a.a("LoginInteractor|loginReq|" + e.A, new Object[0]);
                return;
            }
            return;
        }
        com.igexin.c.a.c.a.a("registerReq #####", new Object[0]);
        com.igexin.push.d.c.d dVar = new com.igexin.push.d.c.d(e.D, e.E, e.L, e.f3403a);
        String str2 = e.D;
        String str3 = e.E;
        String str4 = e.L;
        com.igexin.push.e.a aVar2 = d.a.f3384a.h;
        StringBuilder sb2 = new StringBuilder("R-");
        sb2.append(e.L);
        com.igexin.c.a.c.a.a("registerReq|" + (aVar2.a(sb2.toString(), dVar, true) >= 0) + "|" + e.L, new Object[0]);
    }

    public static com.igexin.push.d.c.i d() {
        Pair<String, String> pairB;
        NetworkInfo activeNetworkInfo;
        com.igexin.push.d.c.i iVar = new com.igexin.push.d.c.i();
        iVar.b = e.z;
        iVar.c = (byte) 0;
        iVar.d = 65280;
        iVar.f3531e = e.f3403a;
        int type = -1;
        try {
            ArrayList<com.igexin.push.d.c.j> arrayList = new ArrayList();
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) e.l.getSystemService("connectivity");
                if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                    type = activeNetworkInfo.getType();
                    com.igexin.push.d.c.j jVar = new com.igexin.push.d.c.j();
                    jVar.f3532a = (byte) 2;
                    jVar.b = String.valueOf(type);
                    arrayList.add(jVar);
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
            if (type == 1 && (pairB = com.igexin.push.g.n.b()) != null) {
                String str = (String) pairB.first;
                String str2 = (String) pairB.second;
                if (str != null) {
                    com.igexin.push.d.c.j jVar2 = new com.igexin.push.d.c.j();
                    jVar2.f3532a = (byte) 1;
                    jVar2.b = str;
                    arrayList.add(jVar2);
                }
                if (str2 != null) {
                    com.igexin.push.d.c.j jVar3 = new com.igexin.push.d.c.j();
                    jVar3.f3532a = (byte) 4;
                    jVar3.b = str2;
                    arrayList.add(jVar3);
                }
            }
            String strP = com.igexin.push.g.n.p();
            if (!TextUtils.isEmpty(strP)) {
                String[] strArrSplit = strP.split("#");
                if (strArrSplit.length >= 3 && !TextUtils.isEmpty(strArrSplit[2])) {
                    com.igexin.push.d.c.j jVar4 = new com.igexin.push.d.c.j();
                    jVar4.f3532a = (byte) 6;
                    jVar4.b = strArrSplit[2];
                    arrayList.add(jVar4);
                }
            }
            if (com.igexin.push.config.d.am) {
                String strT = com.igexin.push.g.n.t();
                if (!TextUtils.isEmpty(strT)) {
                    com.igexin.push.d.c.j jVar5 = new com.igexin.push.d.c.j();
                    jVar5.f3532a = (byte) 7;
                    jVar5.b = strT;
                    arrayList.add(jVar5);
                }
            }
            if (ServiceManager.getInstance().initType != null) {
                int iIntValue = ((Integer) ServiceManager.getInstance().initType.first).intValue();
                String strValueOf = String.valueOf(iIntValue);
                if (iIntValue == 1) {
                    strValueOf = strValueOf + "#" + ((String) ServiceManager.getInstance().initType.second);
                }
                com.igexin.push.d.c.j jVar6 = new com.igexin.push.d.c.j();
                jVar6.f3532a = (byte) 5;
                jVar6.b = strValueOf;
                arrayList.add(jVar6);
            }
            try {
                StringBuilder sb = new StringBuilder();
                for (com.igexin.push.d.c.j jVar7 : arrayList) {
                    sb.append((int) jVar7.f3532a);
                    sb.append(Constants.COLON_SEPARATOR);
                    sb.append((String) jVar7.b);
                    sb.append("|");
                }
                com.igexin.c.a.c.a.a("LoginInteractor| ".concat(String.valueOf(sb)), new Object[0]);
            } catch (Throwable th2) {
                com.igexin.c.a.c.a.a(th2);
            }
            if (!arrayList.isEmpty()) {
                iVar.f = arrayList;
            }
        } catch (Throwable th3) {
            com.igexin.c.a.c.a.a(th3);
        }
        return iVar;
    }
}
