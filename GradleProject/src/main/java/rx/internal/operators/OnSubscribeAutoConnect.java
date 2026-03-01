package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.bx1;
import supwisdom.dz1;
import supwisdom.fz1;
import supwisdom.rw1;
import supwisdom.xw1;
import supwisdom.yw1;

/* JADX INFO: loaded from: classes3.dex */
public final class OnSubscribeAutoConnect<T> extends AtomicInteger implements rw1.a<T> {
    public final bx1<? super yw1> connection;
    public final int numberOfSubscribers;
    public final dz1<? extends T> source;

    public OnSubscribeAutoConnect(dz1<? extends T> dz1Var, int i, bx1<? super yw1> bx1Var) {
        if (i <= 0) {
            throw new IllegalArgumentException("numberOfSubscribers > 0 required");
        }
        this.source = dz1Var;
        this.numberOfSubscribers = i;
        this.connection = bx1Var;
    }

    @Override // supwisdom.bx1
    public void call(xw1<? super T> xw1Var) {
        this.source.b(fz1.a(xw1Var));
        if (incrementAndGet() == this.numberOfSubscribers) {
            this.source.a(this.connection);
        }
    }
}
