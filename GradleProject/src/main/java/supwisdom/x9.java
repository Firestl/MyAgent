package supwisdom;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class x9 {

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9722a;
        public final b[] b;

        @Deprecated
        public a(int i, b[] bVarArr) {
            this.f9722a = i;
            this.b = bVarArr;
        }

        public b[] a() {
            return this.b;
        }

        public int b() {
            return this.f9722a;
        }

        public static a a(int i, b[] bVarArr) {
            return new a(i, bVarArr);
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Uri f9723a;
        public final int b;
        public final int c;
        public final boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f9724e;

        @Deprecated
        public b(Uri uri, int i, int i2, boolean z, int i3) {
            na.a(uri);
            this.f9723a = uri;
            this.b = i;
            this.c = i2;
            this.d = z;
            this.f9724e = i3;
        }

        public static b a(Uri uri, int i, int i2, boolean z, int i3) {
            return new b(uri, i, i2, z, i3);
        }

        public int b() {
            return this.b;
        }

        public Uri c() {
            return this.f9723a;
        }

        public int d() {
            return this.c;
        }

        public boolean e() {
            return this.d;
        }

        public int a() {
            return this.f9724e;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class c {
        public void a(int i) {
            throw null;
        }

        public void a(Typeface typeface) {
            throw null;
        }
    }

    public static Typeface a(Context context, v9 v9Var, int i, boolean z, int i2, Handler handler, c cVar) {
        s9 s9Var = new s9(cVar, handler);
        return z ? w9.a(context, v9Var, s9Var, i, i2) : w9.a(context, v9Var, i, (Executor) null, s9Var);
    }
}
