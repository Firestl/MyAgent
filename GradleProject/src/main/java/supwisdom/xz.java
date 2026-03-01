package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class xz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f9828a;

    public xz(boolean z) {
        this.f9828a = z;
    }

    public void a(yv[] yvVarArr) {
        if (!this.f9828a || yvVarArr == null || yvVarArr.length < 3) {
            return;
        }
        yv yvVar = yvVarArr[0];
        yvVarArr[0] = yvVarArr[2];
        yvVarArr[2] = yvVar;
    }
}
