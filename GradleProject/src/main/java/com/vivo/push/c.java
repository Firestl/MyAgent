package com.vivo.push;

import android.os.Handler;
import android.os.Message;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: IPCManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b f5583a;

    public c(b bVar) {
        this.f5583a = bVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message == null) {
            com.vivo.push.util.o.a("AidlManager", "handleMessage error : msg is null");
            return false;
        }
        int i = message.what;
        if (i == 1) {
            com.vivo.push.util.o.a("AidlManager", "In connect, bind core service time out");
            if (this.f5583a.f.get() == 2) {
                this.f5583a.a(1);
            }
        } else if (i != 2) {
            com.vivo.push.util.o.b("AidlManager", "unknow msg what [" + message.what + Operators.ARRAY_END_STR);
        } else {
            if (this.f5583a.f.get() == 4) {
                this.f5583a.f();
            }
            this.f5583a.a(1);
        }
        return true;
    }
}
