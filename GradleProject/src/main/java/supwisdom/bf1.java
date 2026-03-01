package supwisdom;

/* JADX INFO: compiled from: NamedRunnable.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class bf1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7063a;

    public bf1(String str, Object... objArr) {
        this.f7063a = String.format(str, objArr);
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f7063a);
        try {
            a();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
