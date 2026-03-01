package com.umeng.ccg;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.HashMap;

/* JADX INFO: compiled from: Dispatch.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f5345a = 101;
    public static final int b = 102;
    public static final int c = 103;
    public static final int d = 104;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f5346e = 105;
    public static final int f = 106;
    public static final int g = 107;
    public static final int h = 0;
    public static final int i = 1;
    public static final int j = 2;
    public static final int k = 201;
    public static final int l = 202;
    public static final int m = 203;
    public static final int n = 301;
    public static final int o = 302;
    public static final int p = 303;
    public static HandlerThread q = null;
    public static Handler r = null;
    public static HashMap<Integer, a> s = null;
    public static final int t = 256;

    /* JADX INFO: compiled from: Dispatch.java */
    public interface a {
        void a(Object obj, int i);
    }

    public static void b(Message message) {
        int i2 = message.arg1;
        Object obj = message.obj;
        Integer numValueOf = Integer.valueOf(i2 / 100);
        HashMap<Integer, a> map = s;
        if (map == null) {
            return;
        }
        a aVar = map.containsKey(numValueOf) ? s.get(numValueOf) : null;
        if (aVar != null) {
            aVar.a(obj, i2);
        }
    }

    public static void a(Context context, int i2, int i3, a aVar, Object obj, long j2) {
        if (context == null || aVar == null) {
            return;
        }
        if (s == null) {
            s = new HashMap<>();
        }
        Integer numValueOf = Integer.valueOf(i3 / 100);
        if (!s.containsKey(numValueOf)) {
            s.put(numValueOf, aVar);
        }
        if (q == null || r == null) {
            a();
        }
        try {
            if (r != null) {
                Message messageObtainMessage = r.obtainMessage();
                messageObtainMessage.what = i2;
                messageObtainMessage.arg1 = i3;
                messageObtainMessage.obj = obj;
                r.sendMessageDelayed(messageObtainMessage, j2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, int i2, a aVar, Object obj) {
        a(context, 256, i2, aVar, obj, 0L);
    }

    public static void a(Context context, int i2, a aVar, Object obj, long j2) {
        a(context, 256, i2, aVar, obj, j2);
    }

    public static synchronized void a() {
        try {
            if (q == null) {
                HandlerThread handlerThread = new HandlerThread("ccg_dispatch");
                q = handlerThread;
                handlerThread.start();
                if (r == null) {
                    r = new Handler(q.getLooper()) { // from class: com.umeng.ccg.c.1
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            if (message.what != 256) {
                                return;
                            }
                            c.b(message);
                        }
                    };
                }
            }
        } catch (Throwable unused) {
        }
    }
}
