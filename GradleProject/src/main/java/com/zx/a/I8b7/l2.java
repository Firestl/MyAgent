package com.zx.a.I8b7;

import android.text.TextUtils;
import com.zx.sdk.api.ZXIDChangedListener;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class l2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f6239a;
    public final /* synthetic */ ZXIDChangedListener b;

    public l2(e2 e2Var, String str, ZXIDChangedListener zXIDChangedListener) {
        this.f6239a = str;
        this.b = zXIDChangedListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            o2 o2VarA = e2.a();
            String str = this.f6239a;
            ZXIDChangedListener zXIDChangedListener = this.b;
            x1 x1Var = o2VarA.c;
            x1Var.getClass();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            LinkedList<ZXIDChangedListener> linkedList = x1Var.f6303a.get(str);
            if (linkedList == null) {
                linkedList = new LinkedList<>();
            }
            linkedList.add(zXIDChangedListener);
            x1Var.f6303a.put(str, linkedList);
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.allowPermissionDialog failed: "));
        }
    }
}
