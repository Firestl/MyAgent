package com.zx.a.I8b7;

import android.content.Context;
import com.zx.a.I8b7.c3;
import com.zx.module.base.Callback;
import com.zx.module.base.Listener;
import com.zx.module.base.ZXModule;
import com.zx.module.context.ContextHolder;
import com.zx.module.exception.ZXModuleInvokeException;
import com.zx.module.exception.ZXModuleOnCreateException;
import com.zx.module.exception.ZXModuleOnDestroyException;
import com.zx.module.exception.ZXModuleStartException;
import com.zx.sdk.common.utils.ZXTask;
import java.lang.reflect.Method;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class r1 implements ZXModule {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v2 f6273a;
    public final p1 b = new p1();

    @Override // com.zx.module.base.ZXModule
    public String getModuleIdentifier() {
        return "core-n";
    }

    @Override // com.zx.module.base.ZXModule
    public String getModuleVersion() {
        return "3.3.2.25477";
    }

    @Override // com.zx.module.base.ZXModule
    public String invoke(String str, String str2) throws ZXModuleInvokeException {
        p1 p1Var = this.b;
        p1Var.getClass();
        try {
            String strSubstring = j.a(str, "SHA256").substring(0, 16);
            if (!((HashSet) p1.b).contains(strSubstring)) {
                return p1Var.a(str + " not in invokableMethods", 3);
            }
            Method declaredMethod = p1.class.getDeclaredMethod(cn.com.chinatelecom.account.api.e.f.f1517a + strSubstring, String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(p1Var, str2);
        } catch (Exception e2) {
            y1.a(e2);
            throw new ZXModuleInvokeException("Cannot invoke " + str + ", nested exception is " + e2.getMessage(), e2);
        }
    }

    @Override // com.zx.module.base.ZXModule
    public String invokeAsync(String str, String str2, Callback callback) throws ZXModuleInvokeException {
        p1 p1Var = this.b;
        p1Var.getClass();
        try {
            String strSubstring = j.a(str, "SHA256").substring(0, 16);
            if (!((HashSet) p1.b).contains(strSubstring)) {
                String strA = p1Var.a(str + " not in invokableMethods", 3);
                callback.callback(strA);
                return strA;
            }
            y1.a("开始执行invokeAsync: method:" + str + "; " + str2 + ":cb");
            StringBuilder sb = new StringBuilder();
            sb.append(cn.com.chinatelecom.account.api.e.f.f1517a);
            sb.append(strSubstring);
            Method declaredMethod = p1.class.getDeclaredMethod(sb.toString(), String.class, Callback.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(p1Var, str2, callback);
        } catch (Exception e2) {
            StringBuilder sbA = m2.a("开始执行invokeAsync:");
            sbA.append(e2.getMessage());
            y1.b(sbA.toString());
            throw new ZXModuleInvokeException("Cannot invokeAsync " + str + ", nested exception is " + e2.getMessage(), e2);
        }
    }

    @Override // com.zx.module.base.ZXModule
    public void onCreate(ContextHolder contextHolder) throws ZXModuleOnCreateException {
        a3 a3Var = new a3();
        this.f6273a = a3Var;
        Context context = (Context) contextHolder.getContext();
        try {
            if (!a3Var.b.getAndSet(true)) {
                c3.e.f6204a.f6203a.execute(new z2(a3Var, context));
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXCore init failed: "));
            a3Var.b.set(false);
        }
        this.b.f6263a = this.f6273a;
    }

    @Override // com.zx.module.base.ZXModule
    public void onDestroy() throws ZXModuleOnDestroyException {
    }

    @Override // com.zx.module.base.ZXModule
    public void setMessageListener(Listener listener) {
        a3 a3Var = (a3) this.f6273a;
        a3Var.getClass();
        a3Var.c = new w2(a3Var, listener);
    }

    @Override // com.zx.module.base.ZXModule
    public void start() throws ZXModuleStartException {
        a3 a3Var = (a3) this.f6273a;
        if (a3Var.f6198a.compareAndSet(false, true)) {
            try {
                c3.e.f6204a.f6203a.execute(new ZXTask(new x2(a3Var), new y2(a3Var)));
            } catch (Throwable th) {
                a3Var.c.onMessage("MESSAGE_ON_ZXID_RECEIVED", m1.a(10007, th.getMessage()));
                StringBuilder sb = new StringBuilder();
                sb.append("ZXCore start failed: ");
                n2.a(th, sb);
            }
        }
    }
}
