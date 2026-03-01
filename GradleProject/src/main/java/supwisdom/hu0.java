package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public class hu0<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final pt0<TResult> f7869a = new pt0<>();

    public void a(TResult tresult) {
        pt0<TResult> pt0Var = this.f7869a;
        synchronized (pt0Var.f8828a) {
            if (!pt0Var.b) {
                pt0Var.b = true;
                pt0Var.c = tresult;
                pt0Var.f8828a.notifyAll();
                pt0Var.a();
            }
        }
    }

    public void a(Exception exc) {
        pt0<TResult> pt0Var = this.f7869a;
        synchronized (pt0Var.f8828a) {
            if (!pt0Var.b) {
                pt0Var.b = true;
                pt0Var.d = exc;
                pt0Var.f8828a.notifyAll();
                pt0Var.a();
            }
        }
    }
}
