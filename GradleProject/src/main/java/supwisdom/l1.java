package supwisdom;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;
import supwisdom.i1;
import supwisdom.w1;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class l1 extends i1 implements w1.a {
    public Context c;
    public ActionBarContextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i1.a f8228e;
    public WeakReference<View> f;
    public boolean g;
    public w1 h;

    public l1(Context context, ActionBarContextView actionBarContextView, i1.a aVar, boolean z) {
        this.c = context;
        this.d = actionBarContextView;
        this.f8228e = aVar;
        w1 w1Var = new w1(actionBarContextView.getContext());
        w1Var.c(1);
        this.h = w1Var;
        w1Var.a(this);
    }

    @Override // supwisdom.i1
    public void a(CharSequence charSequence) {
        this.d.setSubtitle(charSequence);
    }

    @Override // supwisdom.i1
    public void b(CharSequence charSequence) {
        this.d.setTitle(charSequence);
    }

    @Override // supwisdom.i1
    public Menu c() {
        return this.h;
    }

    @Override // supwisdom.i1
    public MenuInflater d() {
        return new n1(this.d.getContext());
    }

    @Override // supwisdom.i1
    public CharSequence e() {
        return this.d.getSubtitle();
    }

    @Override // supwisdom.i1
    public CharSequence g() {
        return this.d.getTitle();
    }

    @Override // supwisdom.i1
    public void i() {
        this.f8228e.b(this, this.h);
    }

    @Override // supwisdom.i1
    public boolean j() {
        return this.d.c();
    }

    @Override // supwisdom.i1
    public void a(int i) {
        a((CharSequence) this.c.getString(i));
    }

    @Override // supwisdom.i1
    public void b(int i) {
        b(this.c.getString(i));
    }

    @Override // supwisdom.i1
    public void a(boolean z) {
        super.a(z);
        this.d.setTitleOptional(z);
    }

    @Override // supwisdom.i1
    public View b() {
        WeakReference<View> weakReference = this.f;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // supwisdom.i1
    public void a(View view) {
        this.d.setCustomView(view);
        this.f = view != null ? new WeakReference<>(view) : null;
    }

    @Override // supwisdom.i1
    public void a() {
        if (this.g) {
            return;
        }
        this.g = true;
        this.d.sendAccessibilityEvent(32);
        this.f8228e.a(this);
    }

    @Override // supwisdom.w1.a
    public boolean a(w1 w1Var, MenuItem menuItem) {
        return this.f8228e.a(this, menuItem);
    }

    @Override // supwisdom.w1.a
    public void a(w1 w1Var) {
        i();
        this.d.e();
    }
}
