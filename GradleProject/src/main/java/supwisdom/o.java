package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f8593a;
    public final /* synthetic */ String b;
    public final /* synthetic */ p c;

    public o(p pVar, String str, String str2) {
        this.c = pVar;
        this.f8593a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.b.a(this.f8593a, this.b);
    }
}
