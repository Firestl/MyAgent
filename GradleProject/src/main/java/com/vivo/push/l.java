package com.vivo.push;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: PushClientTask.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f5616a;
    public int b;
    public o c;

    public l(o oVar) {
        this.b = -1;
        this.c = oVar;
        int iB = oVar.b();
        this.b = iB;
        if (iB < 0) {
            throw new IllegalArgumentException("PushTask need a > 0 task id.");
        }
        this.f5616a = e.a().h();
    }

    public final int a() {
        return this.b;
    }

    public abstract void a(o oVar);

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.f5616a;
        if (context != null && !(this.c instanceof com.vivo.push.b.n)) {
            com.vivo.push.util.o.a(context, "[执行指令]" + this.c);
        }
        a(this.c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Operators.BLOCK_START_STR);
        o oVar = this.c;
        sb.append(oVar == null ? "[null]" : oVar.toString());
        sb.append(Operators.BLOCK_END_STR);
        return sb.toString();
    }
}
