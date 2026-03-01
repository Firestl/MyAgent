package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import supwisdom.rd;
import supwisdom.vd;
import supwisdom.xd;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements vd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1316a;
    public final rd.a b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1316a = obj;
        this.b = rd.c.b(obj.getClass());
    }

    @Override // supwisdom.vd
    public void a(xd xdVar, Lifecycle.Event event) {
        this.b.a(xdVar, event, this.f1316a);
    }
}
