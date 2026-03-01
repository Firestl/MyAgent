package supwisdom;

import supwisdom.rw1;
import supwisdom.vw1;

/* JADX INFO: compiled from: SingleToObservable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sx1<T> implements rw1.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final vw1.a<T> f9219a;

    public sx1(vw1.a<T> aVar) {
        this.f9219a = aVar;
    }

    @Override // supwisdom.bx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void call(xw1<? super T> xw1Var) {
        rx1 rx1Var = new rx1(xw1Var);
        xw1Var.add(rx1Var);
        this.f9219a.call(rx1Var);
    }
}
