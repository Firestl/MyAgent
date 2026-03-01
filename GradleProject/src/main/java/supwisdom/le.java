package supwisdom;

import android.os.Bundle;
import android.util.Log;
import com.bumptech.glide.load.engine.GlideException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.bouncycastle.asn1.util.ASN1Dump;
import supwisdom.he;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class le extends ke {
    public static boolean c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final xd f8279a;
    public final c b;

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class b<D> implements de<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class c extends ge {
        public static final he.a c = new a();
        public q4<a> b = new q4<>();

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        public static class a implements he.a {
            @Override // supwisdom.he.a
            public <T extends ge> T a(Class<T> cls) {
                return new c();
            }
        }

        public static c a(ie ieVar) {
            return (c) new he(ieVar, c).a(c.class);
        }

        @Override // supwisdom.ge
        public void b() {
            super.b();
            if (this.b.c() <= 0) {
                this.b.a();
            } else {
                this.b.e(0).a(true);
                throw null;
            }
        }

        public void c() {
            int iC = this.b.c();
            for (int i = 0; i < iC; i++) {
                this.b.e(i).c();
            }
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.b.c() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + ASN1Dump.TAB;
                if (this.b.c() <= 0) {
                    return;
                }
                a aVarE = this.b.e(0);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.b.c(0));
                printWriter.print(": ");
                printWriter.println(aVarE.toString());
                aVarE.a(str2, fileDescriptor, printWriter, strArr);
                throw null;
            }
        }
    }

    public le(xd xdVar, ie ieVar) {
        this.f8279a = xdVar;
        this.b = c.a(ieVar);
    }

    @Override // supwisdom.ke
    public void a() {
        this.b.c();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        ga.a(this.f8279a, sb);
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object<D>] */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends ce<D> {
        public final int j;
        public final Bundle k;
        public final me<D> l;
        public xd m;
        public b<D> n;
        public me<D> o;

        @Override // androidx.lifecycle.LiveData
        public void a() {
            if (le.c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.l.c();
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void b() {
            if (le.c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.l.d();
            throw null;
        }

        public void c() {
            xd xdVar = this.m;
            b<D> bVar = this.n;
            if (xdVar == null || bVar == null) {
                return;
            }
            super.a((de) bVar);
            a(xdVar, bVar);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.j);
            sb.append(" : ");
            ga.a(this.l, sb);
            sb.append("}}");
            return sb.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void a(de<? super D> deVar) {
            super.a((de) deVar);
            this.m = null;
            this.n = null;
        }

        public me<D> a(boolean z) {
            if (le.c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.l.a();
            throw null;
        }

        @Override // supwisdom.ce, androidx.lifecycle.LiveData
        public void a(D d) {
            super.a(d);
            me<D> meVar = this.o;
            if (meVar == null) {
                return;
            }
            meVar.b();
            throw null;
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.j);
            printWriter.print(" mArgs=");
            printWriter.println(this.k);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.l);
            this.l.a(str + GlideException.IndentedAppendable.INDENT, fileDescriptor, printWriter, strArr);
            throw null;
        }
    }

    @Override // supwisdom.ke
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.b.a(str, fileDescriptor, printWriter, strArr);
    }
}
