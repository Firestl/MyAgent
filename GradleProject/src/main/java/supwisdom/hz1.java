package supwisdom;

/* JADX INFO: compiled from: RxJavaErrorHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class hz1 {
    public final String a(Object obj) {
        try {
            return b(obj);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return obj.getClass().getName() + ".errorRendering";
        } catch (Throwable th) {
            zw1.b(th);
            return obj.getClass().getName() + ".errorRendering";
        }
    }

    @Deprecated
    public void a(Throwable th) {
    }

    public String b(Object obj) throws InterruptedException {
        return null;
    }
}
