package supwisdom;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import supwisdom.hn0;

/* JADX INFO: compiled from: BaseTransientBottomBar.java */
/* JADX INFO: loaded from: classes.dex */
public class dn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public hn0.b f7366a;

    public dn0(SwipeDismissBehavior<?> swipeDismissBehavior) {
        swipeDismissBehavior.b(0.1f);
        swipeDismissBehavior.a(0.6f);
        swipeDismissBehavior.a(0);
    }

    public boolean a(View view) {
        return view instanceof gn0;
    }

    public void a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            if (coordinatorLayout.a(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                hn0.a().b(this.f7366a);
            }
        } else if (actionMasked == 1 || actionMasked == 3) {
            hn0.a().c(this.f7366a);
        }
    }
}
