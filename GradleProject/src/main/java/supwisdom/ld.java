package supwisdom;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class ld implements xd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public yd f8277a = null;

    public void a() {
        if (this.f8277a == null) {
            this.f8277a = new yd(this);
        }
    }

    public boolean b() {
        return this.f8277a != null;
    }

    @Override // supwisdom.xd
    public Lifecycle getLifecycle() {
        a();
        return this.f8277a;
    }

    public void a(Lifecycle.Event event) {
        this.f8277a.a(event);
    }
}
