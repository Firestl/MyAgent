package supwisdom;

import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class mg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f8386a;
    public Runnable b;

    public void a() {
        Runnable runnable;
        if (a(this.f8386a) != this || (runnable = this.b) == null) {
            return;
        }
        runnable.run();
    }

    public static void a(View view, mg mgVar) {
        view.setTag(R.id.transition_current_scene, mgVar);
    }

    public static mg a(View view) {
        return (mg) view.getTag(R.id.transition_current_scene);
    }
}
