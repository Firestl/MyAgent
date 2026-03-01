package com.vivo.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: Worker.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f5623a;
    public Handler b;
    public final Object c = new Object();

    /* JADX INFO: compiled from: Worker.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            q.this.b(message);
        }
    }

    public q() {
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName(), 1);
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public final void a(Context context) {
        this.f5623a = context;
    }

    public abstract void b(Message message);

    public final void a(Message message) {
        synchronized (this.c) {
            if (this.b == null) {
                String str = "Dead worker dropping a message: " + message.what;
                com.vivo.push.util.o.e(getClass().getSimpleName(), str + " (Thread " + Thread.currentThread().getId() + ")");
            } else {
                this.b.sendMessage(message);
            }
        }
    }
}
