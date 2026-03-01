package supwisdom;

/* JADX INFO: compiled from: Subscriber.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class xw1<T> implements sw1<T>, yw1 {
    public static final long NOT_SET = Long.MIN_VALUE;
    public tw1 producer;
    public long requested;
    public final xw1<?> subscriber;
    public final fy1 subscriptions;

    public xw1() {
        this(null, false);
    }

    private void addToRequested(long j) {
        long j2 = this.requested;
        if (j2 == Long.MIN_VALUE) {
            this.requested = j;
            return;
        }
        long j3 = j2 + j;
        if (j3 < 0) {
            this.requested = Long.MAX_VALUE;
        } else {
            this.requested = j3;
        }
    }

    public final void add(yw1 yw1Var) {
        this.subscriptions.a(yw1Var);
    }

    @Override // supwisdom.yw1
    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }

    public void onStart() {
    }

    public final void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j);
        }
        synchronized (this) {
            if (this.producer == null) {
                addToRequested(j);
            } else {
                this.producer.request(j);
            }
        }
    }

    public void setProducer(tw1 tw1Var) {
        long j;
        boolean z;
        synchronized (this) {
            j = this.requested;
            this.producer = tw1Var;
            z = this.subscriber != null && j == Long.MIN_VALUE;
        }
        if (z) {
            this.subscriber.setProducer(this.producer);
        } else if (j == Long.MIN_VALUE) {
            this.producer.request(Long.MAX_VALUE);
        } else {
            this.producer.request(j);
        }
    }

    @Override // supwisdom.yw1
    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }

    public xw1(xw1<?> xw1Var) {
        this(xw1Var, true);
    }

    public xw1(xw1<?> xw1Var, boolean z) {
        this.requested = Long.MIN_VALUE;
        this.subscriber = xw1Var;
        this.subscriptions = (!z || xw1Var == null) ? new fy1() : xw1Var.subscriptions;
    }
}
