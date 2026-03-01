package supwisdom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import supwisdom.c2;
import supwisdom.w1;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public interface x2 {
    pb a(int i, long j);

    void a(int i);

    void a(Menu menu, c2.a aVar);

    void a(c2.a aVar, w1.a aVar2);

    void a(i3 i3Var);

    void a(boolean z);

    boolean a();

    void b();

    void b(int i);

    void b(boolean z);

    boolean c();

    void collapseActionView();

    boolean d();

    boolean e();

    boolean f();

    void g();

    Context getContext();

    CharSequence getTitle();

    boolean h();

    Menu i();

    int j();

    ViewGroup k();

    int l();

    void m();

    void n();

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setVisibility(int i);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
