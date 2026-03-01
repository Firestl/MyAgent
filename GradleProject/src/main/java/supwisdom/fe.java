package supwisdom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentCallbacks2;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: ReportFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class fe extends Fragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f7594a;

    /* JADX INFO: compiled from: ReportFragment.java */
    public interface a {
        void a();

        void onResume();

        void onStart();
    }

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new fe(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public final void b(a aVar) {
        if (aVar != null) {
            aVar.onResume();
        }
    }

    public final void c(a aVar) {
        if (aVar != null) {
            aVar.onStart();
        }
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(this.f7594a);
        a(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a(Lifecycle.Event.ON_DESTROY);
        this.f7594a = null;
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        a(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        b(this.f7594a);
        a(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        c(this.f7594a);
        a(Lifecycle.Event.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        a(Lifecycle.Event.ON_STOP);
    }

    public final void a(a aVar) {
        if (aVar != null) {
            aVar.a();
        }
    }

    public final void a(Lifecycle.Event event) {
        ComponentCallbacks2 activity = getActivity();
        if (activity instanceof zd) {
            ((zd) activity).getLifecycle().a(event);
        } else if (activity instanceof xd) {
            Lifecycle lifecycle = ((xd) activity).getLifecycle();
            if (lifecycle instanceof yd) {
                ((yd) lifecycle).a(event);
            }
        }
    }
}
