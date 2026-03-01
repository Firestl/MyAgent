package com.igexin.push.d.a;

import com.igexin.c.a.b.d;
import com.igexin.c.a.d.a.e;
import com.igexin.push.d.c.f;
import com.igexin.push.d.c.h;
import com.igexin.push.d.c.k;
import com.igexin.push.d.c.m;
import com.igexin.push.d.c.n;
import com.igexin.push.d.c.p;
import com.igexin.push.d.c.q;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3508a = "CommandFilter";

    public a(String str, d dVar) {
        super(str, (byte) 0);
        a(dVar);
    }

    public static boolean a(com.igexin.push.d.c.a aVar, com.igexin.push.d.c.c cVar) {
        String string;
        if (aVar.b != 26) {
            return false;
        }
        n nVar = (n) cVar;
        if (nVar.d() && nVar.f != null) {
            try {
                JSONObject jSONObject = new JSONObject((String) nVar.f);
                if (jSONObject.has("action") && (string = jSONObject.getString("action")) != null) {
                    if (string.equals(com.igexin.push.core.b.G)) {
                        return true;
                    }
                }
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
                com.igexin.c.a.c.a.a("CommandFilter|" + e2.toString(), new Object[0]);
            }
        }
        return false;
    }

    public static e c(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        com.igexin.push.d.c.a aVar = (com.igexin.push.d.c.a) obj;
        byte b = aVar.b;
        com.igexin.push.d.c.c hVar = b != 0 ? b != 5 ? b != 9 ? b != 20 ? b != 26 ? b != 37 ? b != 97 ? null : new h() : new m() : new n() : new q() : new p() : new k() : new f();
        int i = aVar.f;
        if ((i != 1 && i != 7) || hVar == null) {
            return null;
        }
        hVar.a(aVar.f3519e);
        if (aVar.f != 7) {
            if (a(aVar, hVar)) {
                return hVar;
            }
            return null;
        }
        if (aVar.g != 32 || a(aVar, hVar)) {
            return hVar;
        }
        com.igexin.c.a.c.a.a(f3508a, "version = 7 and enc type = 0x20, redirect = false");
        return null;
    }

    @Override // com.igexin.c.a.b.d
    public final Object a(Object obj) throws Exception {
        if (obj instanceof com.igexin.push.d.c.c) {
            com.igexin.push.d.c.c cVar = (com.igexin.push.d.c.c) obj;
            com.igexin.push.d.c.a aVar = new com.igexin.push.d.c.a();
            aVar.b = (byte) cVar.m;
            aVar.a(cVar.b());
            aVar.c = cVar.n;
            aVar.d = cVar.o;
            return aVar;
        }
        if (!(obj instanceof com.igexin.push.d.c.c[])) {
            return null;
        }
        com.igexin.push.d.c.c[] cVarArr = (com.igexin.push.d.c.c[]) obj;
        com.igexin.push.d.c.a[] aVarArr = new com.igexin.push.d.c.a[cVarArr.length];
        for (int i = 0; i < cVarArr.length; i++) {
            aVarArr[i] = new com.igexin.push.d.c.a();
            aVarArr[i].b = (byte) cVarArr[i].m;
            aVarArr[i].a(cVarArr[i].b());
        }
        return aVarArr;
    }

    @Override // com.igexin.c.a.b.d
    public final /* synthetic */ Object b(Object obj) throws Exception {
        return c(obj);
    }
}
