package supwisdom;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import supwisdom.yi;

/* JADX INFO: compiled from: BindingXPropertyInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public class ui {
    public static ui c = new ui();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f9412a = new Handler(Looper.getMainLooper());
    public final LinkedList<b> b = new LinkedList<>();

    /* JADX INFO: compiled from: BindingXPropertyInterceptor.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f9413a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Object c;
        public final /* synthetic */ yi.c d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Map f9414e;
        public final /* synthetic */ Object[] f;

        public a(View view, String str, Object obj, yi.c cVar, Map map, Object[] objArr) {
            this.f9413a = view;
            this.b = str;
            this.c = obj;
            this.d = cVar;
            this.f9414e = map;
            this.f = objArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = ui.this.b.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a(this.f9413a, this.b, this.c, this.d, this.f9414e, this.f);
            }
        }
    }

    /* JADX INFO: compiled from: BindingXPropertyInterceptor.java */
    public interface b {
        boolean a(View view, String str, Object obj, yi.c cVar, Map<String, Object> map, Object... objArr);
    }

    public static ui b() {
        return c;
    }

    public void a(View view, String str, Object obj, yi.c cVar, Map<String, Object> map, Object... objArr) {
        if (this.b.isEmpty()) {
            return;
        }
        this.f9412a.post(new zi(new a(view, str, obj, cVar, map, objArr)));
    }

    public void a() {
        this.f9412a.removeCallbacksAndMessages(null);
    }
}
