package supwisdom;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: BoltsExecutors.java */
/* JADX INFO: loaded from: classes.dex */
public final class ki {
    public static final ki c = new ki();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f8177a;
    public final Executor b;

    /* JADX INFO: compiled from: BoltsExecutors.java */
    public static class b implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ThreadLocal<Integer> f8178a;

        public b() {
            this.f8178a = new ThreadLocal<>();
        }

        public final int a() {
            Integer num = this.f8178a.get();
            if (num == null) {
                num = 0;
            }
            int iIntValue = num.intValue() - 1;
            if (iIntValue == 0) {
                this.f8178a.remove();
            } else {
                this.f8178a.set(Integer.valueOf(iIntValue));
            }
            return iIntValue;
        }

        public final int b() {
            Integer num = this.f8178a.get();
            if (num == null) {
                num = 0;
            }
            int iIntValue = num.intValue() + 1;
            this.f8178a.set(Integer.valueOf(iIntValue));
            return iIntValue;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            try {
                if (b() <= 15) {
                    runnable.run();
                } else {
                    ki.a().execute(runnable);
                }
            } finally {
                a();
            }
        }
    }

    public ki() {
        this.f8177a = !c() ? Executors.newCachedThreadPool() : ji.a();
        Executors.newSingleThreadScheduledExecutor();
        this.b = new b();
    }

    public static ExecutorService a() {
        return c.f8177a;
    }

    public static Executor b() {
        return c.b;
    }

    public static boolean c() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains("android");
    }
}
