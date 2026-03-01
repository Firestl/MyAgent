package supwisdom;

import android.graphics.Typeface;
import android.os.Handler;
import supwisdom.w9;
import supwisdom.x9;

/* JADX INFO: compiled from: CallbackWithHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class s9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x9.c f9138a;
    public final Handler b;

    /* JADX INFO: compiled from: CallbackWithHandler.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ x9.c f9139a;
        public final /* synthetic */ Typeface b;

        public a(s9 s9Var, x9.c cVar, Typeface typeface) {
            this.f9139a = cVar;
            this.b = typeface;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9139a.a(this.b);
        }
    }

    /* JADX INFO: compiled from: CallbackWithHandler.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ x9.c f9140a;
        public final /* synthetic */ int b;

        public b(s9 s9Var, x9.c cVar, int i) {
            this.f9140a = cVar;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9140a.a(this.b);
        }
    }

    public s9(x9.c cVar, Handler handler) {
        this.f9138a = cVar;
        this.b = handler;
    }

    public final void a(Typeface typeface) {
        this.b.post(new a(this, this.f9138a, typeface));
    }

    public final void a(int i) {
        this.b.post(new b(this, this.f9138a, i));
    }

    public void a(w9.e eVar) {
        if (eVar.a()) {
            a(eVar.f9603a);
        } else {
            a(eVar.b);
        }
    }
}
