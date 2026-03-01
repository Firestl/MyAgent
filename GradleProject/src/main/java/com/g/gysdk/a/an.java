package com.g.gysdk.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.g.gysdk.a.al;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class an {
    public static final String[] f = new String[0];
    public static final an[] g = new an[0];
    public static final Handler h;
    public static final Object i;
    public static final AtomicInteger j;
    public static final ArrayList<an> k;
    public static final List<String> l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1971a;
    public final String b;
    public final al.b c;
    public final al.b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String[] f1972e;
    public final AtomicBoolean m = new AtomicBoolean(false);
    public final b n;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f1976a;
        public final String b;
        public al.b c;
        public al.b d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String[] f1977e;
        public b f;

        public a(long j, String str) {
            this.f1976a = j;
            if (TextUtils.isEmpty(str)) {
                str = "GyWorker-" + an.j.getAndIncrement();
            }
            this.b = str;
            this.f1977e = an.f;
            al.b bVar = al.b.Work;
            this.d = bVar;
            this.c = bVar;
        }

        private al.b a(int i) {
            return i == 2 ? al.b.UI : i == 0 ? al.b.Queue : al.b.Work;
        }

        public a a(int i, int i2) {
            this.d = a(i);
            this.c = a(i2);
            return this;
        }

        public a a(String... strArr) {
            if (strArr == null || strArr.length == 0) {
                this.f1977e = an.f;
            } else {
                ArrayList arrayList = new ArrayList();
                for (String str : strArr) {
                    if (!TextUtils.isEmpty(str)) {
                        arrayList.add(str);
                    }
                }
                this.f1977e = (String[]) arrayList.toArray(an.f);
            }
            return this;
        }

        public void a(b bVar) {
            if (bVar == null) {
                throw new IllegalStateException("callback==null");
            }
            this.f = bVar;
            new an(this).a();
        }
    }

    public interface b {
        void a(an anVar);

        void a(Object obj, Throwable th);
    }

    public static class c implements b {
        @Override // com.g.gysdk.a.an.b
        public void a(an anVar) {
            anVar.a((Object) null);
        }

        @Override // com.g.gysdk.a.an.b
        public void a(Object obj, Throwable th) {
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread("GyWorker-SafeHandlerThread");
        handlerThread.start();
        h = new Handler(handlerThread.getLooper());
        i = new Object();
        j = new AtomicInteger(1);
        k = new ArrayList<>();
        l = new ArrayList();
    }

    public an(a aVar) {
        this.f1971a = aVar.f1976a;
        this.b = aVar.b;
        this.d = aVar.c;
        this.c = aVar.d;
        this.f1972e = aVar.f1977e;
        this.n = aVar.f;
    }

    public static a a(String str, long j2) {
        return new a(j2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Object obj, final Throwable th) {
        if (this.m.compareAndSet(false, true)) {
            al.a(this.d, new Runnable() { // from class: com.g.gysdk.a.an.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        an.this.n.a(obj, th);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            }, false);
        }
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        synchronized (i) {
            List<String> list = l;
            if (!list.contains(str)) {
                list.add(str);
            }
            for (an anVar : (an[]) k.toArray(g)) {
                if (!a(anVar.f1972e)) {
                    k.remove(anVar);
                    arrayList.add(anVar);
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((an) it.next()).f();
        }
    }

    public static boolean a(String... strArr) {
        for (String str : strArr) {
            if (!l.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static a b(String str) {
        return new a(-1L, str);
    }

    private void f() {
        if (this.m.get()) {
            return;
        }
        al.a(this.c, new Runnable() { // from class: com.g.gysdk.a.an.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    an.this.n.a(an.this);
                } catch (Throwable th) {
                    an.this.a((Object) null, th);
                }
            }
        }, true);
    }

    private void g() {
        if (this.f1971a > 0) {
            h.postDelayed(new Runnable() { // from class: com.g.gysdk.a.an.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (an.i) {
                        int iIndexOf = an.k.indexOf(an.this);
                        if (iIndexOf >= 0) {
                            an.k.remove(iIndexOf);
                        }
                    }
                    an.this.a((Object) null, new TimeoutException("任务[" + an.this.b + "]超时"));
                }
            }, this.f1971a);
        }
    }

    public void a() {
        g();
        synchronized (i) {
            if (a(this.f1972e)) {
                k.add(this);
            } else {
                f();
            }
        }
    }

    public void a(Object obj) {
        a(obj, (Throwable) null);
    }
}
