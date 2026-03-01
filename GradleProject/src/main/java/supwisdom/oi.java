package supwisdom;

/* JADX INFO: compiled from: TaskCompletionSource.java */
/* JADX INFO: loaded from: classes.dex */
public class oi<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ni<TResult> f8675a = new ni<>();

    public ni<TResult> a() {
        return this.f8675a;
    }

    public boolean b(TResult tresult) {
        return this.f8675a.a(tresult);
    }

    public boolean c() {
        return this.f8675a.g();
    }

    public void a(TResult tresult) {
        if (!b(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public boolean b(Exception exc) {
        return this.f8675a.a(exc);
    }

    public void b() {
        if (!c()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void a(Exception exc) {
        if (!b(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
