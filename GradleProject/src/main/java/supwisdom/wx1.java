package supwisdom;

import java.util.concurrent.ThreadFactory;
import supwisdom.uw1;

/* JADX INFO: compiled from: NewThreadScheduler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wx1 extends uw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadFactory f9680a;

    public wx1(ThreadFactory threadFactory) {
        this.f9680a = threadFactory;
    }

    @Override // supwisdom.uw1
    public uw1.a a() {
        return new xx1(this.f9680a);
    }
}
