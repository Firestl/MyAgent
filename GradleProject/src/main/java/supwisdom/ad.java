package supwisdom;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cd<?> f6887a;

    public ad(cd<?> cdVar) {
        this.f6887a = cdVar;
    }

    public static ad a(cd<?> cdVar) {
        na.a(cdVar, "callbacks == null");
        return new ad(cdVar);
    }

    public void b() {
        this.f6887a.f7178e.k();
    }

    public void c() {
        this.f6887a.f7178e.l();
    }

    public void d() {
        this.f6887a.f7178e.n();
    }

    public void e() {
        this.f6887a.f7178e.o();
    }

    public void f() {
        this.f6887a.f7178e.q();
    }

    public void g() {
        this.f6887a.f7178e.r();
    }

    public void h() {
        this.f6887a.f7178e.s();
    }

    public boolean i() {
        return this.f6887a.f7178e.v();
    }

    public dd j() {
        return this.f6887a.f7178e;
    }

    public void k() {
        this.f6887a.f7178e.D();
    }

    public Parcelable l() {
        return this.f6887a.f7178e.F();
    }

    public Fragment a(String str) {
        return this.f6887a.f7178e.b(str);
    }

    public void b(boolean z) {
        this.f6887a.f7178e.b(z);
    }

    public void a(Fragment fragment) {
        cd<?> cdVar = this.f6887a;
        cdVar.f7178e.a(cdVar, cdVar, fragment);
    }

    public boolean b(Menu menu) {
        return this.f6887a.f7178e.b(menu);
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f6887a.f7178e.onCreateView(view, str, context, attributeSet);
    }

    public boolean b(MenuItem menuItem) {
        return this.f6887a.f7178e.b(menuItem);
    }

    public void a(Parcelable parcelable) {
        cd<?> cdVar = this.f6887a;
        if (cdVar instanceof je) {
            cdVar.f7178e.a(parcelable);
            return;
        }
        throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
    }

    public void a() {
        this.f6887a.f7178e.j();
    }

    public void a(boolean z) {
        this.f6887a.f7178e.a(z);
    }

    public void a(Configuration configuration) {
        this.f6887a.f7178e.a(configuration);
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        return this.f6887a.f7178e.a(menu, menuInflater);
    }

    public boolean a(MenuItem menuItem) {
        return this.f6887a.f7178e.a(menuItem);
    }

    public void a(Menu menu) {
        this.f6887a.f7178e.a(menu);
    }
}
