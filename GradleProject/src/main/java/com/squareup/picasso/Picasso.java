package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.ImageView;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import supwisdom.ah1;
import supwisdom.ch1;
import supwisdom.eh1;
import supwisdom.fh1;
import supwisdom.ih1;
import supwisdom.jh1;
import supwisdom.kh1;
import supwisdom.lh1;
import supwisdom.mh1;
import supwisdom.nh1;
import supwisdom.rg1;
import supwisdom.rh1;
import supwisdom.sg1;
import supwisdom.tg1;
import supwisdom.ug1;
import supwisdom.wg1;
import supwisdom.xg1;
import supwisdom.yg1;
import supwisdom.zg1;

/* JADX INFO: loaded from: classes2.dex */
public class Picasso {
    public static final Handler p = new a(Looper.getMainLooper());
    public static volatile Picasso q = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f3962a;
    public final e b;
    public final c c;
    public final List<lh1> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f3963e;
    public final zg1 f;
    public final ug1 g;
    public final nh1 h;
    public final Map<Object, rg1> i;
    public final Map<ImageView, yg1> j;
    public final ReferenceQueue<Object> k;
    public final Bitmap.Config l;
    public boolean m;
    public volatile boolean n;
    public boolean o;

    public enum LoadedFrom {
        MEMORY(-16711936),
        DISK(-16776961),
        NETWORK(-65536);

        public final int debugColor;

        LoadedFrom(int i) {
            this.debugColor = i;
        }
    }

    public enum Priority {
        LOW,
        NORMAL,
        HIGH
    }

    public static class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 3) {
                rg1 rg1Var = (rg1) message.obj;
                if (rg1Var.f().n) {
                    rh1.a("Main", "canceled", rg1Var.b.d(), "target got garbage collected");
                }
                rg1Var.f9060a.a(rg1Var.j());
                return;
            }
            int i2 = 0;
            if (i == 8) {
                List list = (List) message.obj;
                int size = list.size();
                while (i2 < size) {
                    tg1 tg1Var = (tg1) list.get(i2);
                    tg1Var.b.a(tg1Var);
                    i2++;
                }
                return;
            }
            if (i != 13) {
                throw new AssertionError("Unknown handler message received: " + message.what);
            }
            List list2 = (List) message.obj;
            int size2 = list2.size();
            while (i2 < size2) {
                rg1 rg1Var2 = (rg1) list2.get(i2);
                rg1Var2.f9060a.b(rg1Var2);
                i2++;
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f3964a;
        public Downloader b;
        public ExecutorService c;
        public ug1 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public d f3965e;
        public e f;
        public List<lh1> g;
        public Bitmap.Config h;
        public boolean i;
        public boolean j;

        public b(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.f3964a = context.getApplicationContext();
        }

        public Picasso a() {
            Context context = this.f3964a;
            if (this.b == null) {
                this.b = rh1.c(context);
            }
            if (this.d == null) {
                this.d = new ch1(context);
            }
            if (this.c == null) {
                this.c = new ih1();
            }
            if (this.f == null) {
                this.f = e.f3968a;
            }
            nh1 nh1Var = new nh1(this.d);
            return new Picasso(context, new zg1(context, this.c, Picasso.p, this.b, this.d, nh1Var), this.d, this.f3965e, this.f, this.g, nh1Var, this.h, this.i, this.j);
        }
    }

    public static class c extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ReferenceQueue<Object> f3966a;
        public final Handler b;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Exception f3967a;

            public a(c cVar, Exception exc) {
                this.f3967a = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                throw new RuntimeException(this.f3967a);
            }
        }

        public c(ReferenceQueue<Object> referenceQueue, Handler handler) {
            this.f3966a = referenceQueue;
            this.b = handler;
            setDaemon(true);
            setName("Picasso-refQueue");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            while (true) {
                try {
                    rg1.a aVar = (rg1.a) this.f3966a.remove(1000L);
                    Message messageObtainMessage = this.b.obtainMessage();
                    if (aVar != null) {
                        messageObtainMessage.what = 3;
                        messageObtainMessage.obj = aVar.f9062a;
                        this.b.sendMessage(messageObtainMessage);
                    } else {
                        messageObtainMessage.recycle();
                    }
                } catch (InterruptedException unused) {
                    return;
                } catch (Exception e2) {
                    this.b.post(new a(this, e2));
                    return;
                }
            }
        }
    }

    public interface d {
        void a(Picasso picasso, Uri uri, Exception exc);
    }

    public interface e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f3968a = new a();

        public static class a implements e {
            @Override // com.squareup.picasso.Picasso.e
            public jh1 a(jh1 jh1Var) {
                return jh1Var;
            }
        }

        jh1 a(jh1 jh1Var);
    }

    public Picasso(Context context, zg1 zg1Var, ug1 ug1Var, d dVar, e eVar, List<lh1> list, nh1 nh1Var, Bitmap.Config config, boolean z, boolean z2) {
        this.f3963e = context;
        this.f = zg1Var;
        this.g = ug1Var;
        this.f3962a = dVar;
        this.b = eVar;
        this.l = config;
        ArrayList arrayList = new ArrayList((list != null ? list.size() : 0) + 7);
        arrayList.add(new mh1(context));
        if (list != null) {
            arrayList.addAll(list);
        }
        arrayList.add(new wg1(context));
        arrayList.add(new eh1(context));
        arrayList.add(new xg1(context));
        arrayList.add(new sg1(context));
        arrayList.add(new ah1(context));
        arrayList.add(new fh1(zg1Var.d, nh1Var));
        this.d = Collections.unmodifiableList(arrayList);
        this.h = nh1Var;
        this.i = new WeakHashMap();
        this.j = new WeakHashMap();
        this.m = z;
        this.n = z2;
        this.k = new ReferenceQueue<>();
        c cVar = new c(this.k, p);
        this.c = cVar;
        cVar.start();
    }

    public Bitmap b(String str) {
        Bitmap bitmapA = this.g.a(str);
        if (bitmapA != null) {
            this.h.b();
        } else {
            this.h.c();
        }
        return bitmapA;
    }

    public void c(rg1 rg1Var) {
        this.f.b(rg1Var);
    }

    public void a(ImageView imageView) {
        a((Object) imageView);
    }

    public kh1 a(Uri uri) {
        return new kh1(this, uri, 0);
    }

    public kh1 a(String str) {
        if (str == null) {
            return new kh1(this, null, 0);
        }
        if (str.trim().length() != 0) {
            return a(Uri.parse(str));
        }
        throw new IllegalArgumentException("Path must not be empty.");
    }

    public void b(rg1 rg1Var) {
        Bitmap bitmapB = MemoryPolicy.shouldReadFromMemoryCache(rg1Var.f9061e) ? b(rg1Var.c()) : null;
        if (bitmapB != null) {
            a(bitmapB, LoadedFrom.MEMORY, rg1Var);
            if (this.n) {
                rh1.a("Main", "completed", rg1Var.b.d(), "from " + LoadedFrom.MEMORY);
                return;
            }
            return;
        }
        a(rg1Var);
        if (this.n) {
            rh1.a("Main", "resumed", rg1Var.b.d());
        }
    }

    public List<lh1> a() {
        return this.d;
    }

    public jh1 a(jh1 jh1Var) {
        this.b.a(jh1Var);
        if (jh1Var != null) {
            return jh1Var;
        }
        throw new IllegalStateException("Request transformer " + this.b.getClass().getCanonicalName() + " returned null for " + jh1Var);
    }

    public void a(ImageView imageView, yg1 yg1Var) {
        this.j.put(imageView, yg1Var);
    }

    public void a(rg1 rg1Var) {
        Object objJ = rg1Var.j();
        if (objJ != null && this.i.get(objJ) != rg1Var) {
            a(objJ);
            this.i.put(objJ, rg1Var);
        }
        c(rg1Var);
    }

    public void a(tg1 tg1Var) {
        rg1 rg1VarC = tg1Var.c();
        List<rg1> listD = tg1Var.d();
        boolean z = true;
        boolean z2 = (listD == null || listD.isEmpty()) ? false : true;
        if (rg1VarC == null && !z2) {
            z = false;
        }
        if (z) {
            Uri uri = tg1Var.e().d;
            Exception excF = tg1Var.f();
            Bitmap bitmapL = tg1Var.l();
            LoadedFrom loadedFromH = tg1Var.h();
            if (rg1VarC != null) {
                a(bitmapL, loadedFromH, rg1VarC);
            }
            if (z2) {
                int size = listD.size();
                for (int i = 0; i < size; i++) {
                    a(bitmapL, loadedFromH, listD.get(i));
                }
            }
            d dVar = this.f3962a;
            if (dVar == null || excF == null) {
                return;
            }
            dVar.a(this, uri, excF);
        }
    }

    public final void a(Bitmap bitmap, LoadedFrom loadedFrom, rg1 rg1Var) {
        if (rg1Var.k()) {
            return;
        }
        if (!rg1Var.l()) {
            this.i.remove(rg1Var.j());
        }
        if (bitmap == null) {
            rg1Var.b();
            if (this.n) {
                rh1.a("Main", "errored", rg1Var.b.d());
                return;
            }
            return;
        }
        if (loadedFrom != null) {
            rg1Var.a(bitmap, loadedFrom);
            if (this.n) {
                rh1.a("Main", "completed", rg1Var.b.d(), "from " + loadedFrom);
                return;
            }
            return;
        }
        throw new AssertionError("LoadedFrom cannot be null.");
    }

    public final void a(Object obj) {
        rh1.a();
        rg1 rg1VarRemove = this.i.remove(obj);
        if (rg1VarRemove != null) {
            rg1VarRemove.a();
            this.f.a(rg1VarRemove);
        }
        if (obj instanceof ImageView) {
            yg1 yg1VarRemove = this.j.remove((ImageView) obj);
            if (yg1VarRemove != null) {
                yg1VarRemove.a();
            }
        }
    }

    public static Picasso a(Context context) {
        if (q == null) {
            synchronized (Picasso.class) {
                if (q == null) {
                    q = new b(context).a();
                }
            }
        }
        return q;
    }
}
