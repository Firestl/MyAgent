package supwisdom;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class uf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final vf f9407a;
    public final SavedStateRegistry b = new SavedStateRegistry();

    public uf(vf vfVar) {
        this.f9407a = vfVar;
    }

    public SavedStateRegistry a() {
        return this.b;
    }

    public void b(Bundle bundle) {
        this.b.a(bundle);
    }

    public void a(Bundle bundle) {
        Lifecycle lifecycle = this.f9407a.getLifecycle();
        if (lifecycle.a() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.a(new Recreator(this.f9407a));
        this.b.a(lifecycle, bundle);
    }

    public static uf a(vf vfVar) {
        return new uf(vfVar);
    }
}
