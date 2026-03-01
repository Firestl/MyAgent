package supwisdom;

import android.content.Context;
import supwisdom.s70;

/* JADX INFO: compiled from: DefaultDataSourceFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class y70 implements s70.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9851a;
    public final d80<? super s70> b;
    public final s70.a c;

    public y70(Context context, d80<? super s70> d80Var, s70.a aVar) {
        this.f9851a = context.getApplicationContext();
        this.b = d80Var;
        this.c = aVar;
    }

    @Override // supwisdom.s70.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public x70 a() {
        return new x70(this.f9851a, this.b, this.c.a());
    }
}
