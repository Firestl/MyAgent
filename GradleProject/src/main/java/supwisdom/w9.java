package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import supwisdom.x9;

/* JADX INFO: compiled from: FontRequestWorker.java */
/* JADX INFO: loaded from: classes.dex */
public class w9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n4<String, Typeface> f9598a = new n4<>(16);
    public static final ExecutorService b = y9.a("fonts-androidx", 10, 10000);
    public static final Object c = new Object();
    public static final p4<String, ArrayList<fa<e>>> d = new p4<>();

    /* JADX INFO: compiled from: FontRequestWorker.java */
    public class a implements Callable<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f9599a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ v9 c;
        public final /* synthetic */ int d;

        public a(String str, Context context, v9 v9Var, int i) {
            this.f9599a = str;
            this.b = context;
            this.c = v9Var;
            this.d = i;
        }

        @Override // java.util.concurrent.Callable
        public e call() {
            return w9.a(this.f9599a, this.b, this.c, this.d);
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    public class b implements fa<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s9 f9600a;

        public b(s9 s9Var) {
            this.f9600a = s9Var;
        }

        @Override // supwisdom.fa
        public void a(e eVar) {
            this.f9600a.a(eVar);
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    public class c implements Callable<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f9601a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ v9 c;
        public final /* synthetic */ int d;

        public c(String str, Context context, v9 v9Var, int i) {
            this.f9601a = str;
            this.b = context;
            this.c = v9Var;
            this.d = i;
        }

        @Override // java.util.concurrent.Callable
        public e call() {
            return w9.a(this.f9601a, this.b, this.c, this.d);
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    public class d implements fa<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f9602a;

        public d(String str) {
            this.f9602a = str;
        }

        @Override // supwisdom.fa
        public void a(e eVar) {
            synchronized (w9.c) {
                ArrayList<fa<e>> arrayList = w9.d.get(this.f9602a);
                if (arrayList == null) {
                    return;
                }
                w9.d.remove(this.f9602a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(eVar);
                }
            }
        }
    }

    public static Typeface a(Context context, v9 v9Var, s9 s9Var, int i, int i2) {
        String strA = a(v9Var, i);
        Typeface typefaceB = f9598a.b(strA);
        if (typefaceB != null) {
            s9Var.a(new e(typefaceB));
            return typefaceB;
        }
        if (i2 == -1) {
            e eVarA = a(strA, context, v9Var, i);
            s9Var.a(eVarA);
            return eVarA.f9603a;
        }
        try {
            e eVar = (e) y9.a(b, new a(strA, context, v9Var, i), i2);
            s9Var.a(eVar);
            return eVar.f9603a;
        } catch (InterruptedException unused) {
            s9Var.a(new e(-3));
            return null;
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Typeface f9603a;
        public final int b;

        public e(int i) {
            this.f9603a = null;
            this.b = i;
        }

        @SuppressLint({"WrongConstant"})
        public boolean a() {
            return this.b == 0;
        }

        @SuppressLint({"WrongConstant"})
        public e(Typeface typeface) {
            this.f9603a = typeface;
            this.b = 0;
        }
    }

    public static Typeface a(Context context, v9 v9Var, int i, Executor executor, s9 s9Var) {
        String strA = a(v9Var, i);
        Typeface typefaceB = f9598a.b(strA);
        if (typefaceB != null) {
            s9Var.a(new e(typefaceB));
            return typefaceB;
        }
        b bVar = new b(s9Var);
        synchronized (c) {
            ArrayList<fa<e>> arrayList = d.get(strA);
            if (arrayList != null) {
                arrayList.add(bVar);
                return null;
            }
            ArrayList<fa<e>> arrayList2 = new ArrayList<>();
            arrayList2.add(bVar);
            d.put(strA, arrayList2);
            c cVar = new c(strA, context, v9Var, i);
            if (executor == null) {
                executor = b;
            }
            y9.a(executor, cVar, new d(strA));
            return null;
        }
    }

    public static String a(v9 v9Var, int i) {
        return v9Var.c() + "-" + i;
    }

    public static e a(String str, Context context, v9 v9Var, int i) {
        Typeface typefaceB = f9598a.b(str);
        if (typefaceB != null) {
            return new e(typefaceB);
        }
        try {
            x9.a aVarA = u9.a(context, v9Var, (CancellationSignal) null);
            int iA = a(aVarA);
            if (iA != 0) {
                return new e(iA);
            }
            Typeface typefaceA = q8.a(context, null, aVarA.a(), i);
            if (typefaceA != null) {
                f9598a.a(str, typefaceA);
                return new e(typefaceA);
            }
            return new e(-3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new e(-1);
        }
    }

    @SuppressLint({"WrongConstant"})
    public static int a(x9.a aVar) {
        int i = 1;
        if (aVar.b() != 0) {
            return aVar.b() != 1 ? -3 : -2;
        }
        x9.b[] bVarArrA = aVar.a();
        if (bVarArrA != null && bVarArrA.length != 0) {
            i = 0;
            for (x9.b bVar : bVarArrA) {
                int iA = bVar.a();
                if (iA != 0) {
                    if (iA < 0) {
                        return -3;
                    }
                    return iA;
                }
            }
        }
        return i;
    }
}
