package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public class t implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f9223a;
    public final /* synthetic */ String b;
    public final /* synthetic */ r c;

    public t(r rVar, String str, String str2) {
        this.c = rVar;
        this.f9223a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.c.a(this.f9223a, this.b);
    }
}
