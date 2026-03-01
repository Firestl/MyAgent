package supwisdom;

import android.content.Context;
import android.os.Build;
import com.nostra13.dcloudimageloader.core.assist.QueueProcessingType;
import com.nostra13.dcloudimageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes2.dex */
public class ww0 {

    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final AtomicInteger f9676e = new AtomicInteger(1);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ThreadGroup f9677a;
        public final AtomicInteger b = new AtomicInteger(1);
        public final String c;
        public final int d;

        public a(int i) {
            this.d = i;
            SecurityManager securityManager = System.getSecurityManager();
            this.f9677a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.c = "uil-pool-" + f9676e.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f9677a, runnable, this.c + this.b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.d);
            return thread;
        }
    }

    public static rx0 a() {
        return new sx0();
    }

    public static ow0 b() {
        return new pw0();
    }

    public static jw0 a(Context context, ow0 ow0Var, int i, int i2) {
        return i > 0 ? new mw0(cy0.c(context), ow0Var, i) : i2 > 0 ? new lw0(cy0.c(context), ow0Var, i2) : new nw0(cy0.a(context), ow0Var);
    }

    public static ThreadFactory b(int i) {
        return new a(i);
    }

    public static Executor a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, (BlockingQueue<Runnable>) (queueProcessingType == QueueProcessingType.LIFO ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue()), b(i2));
    }

    public static px0 a(boolean z) {
        return new ox0(z);
    }

    public static ImageDownloader a(Context context) {
        return new tx0(context);
    }

    public static sw0 a(int i) {
        if (i == 0) {
            i = (int) (Runtime.getRuntime().maxMemory() / 8);
        }
        if (Build.VERSION.SDK_INT >= 9) {
            return new vw0(i);
        }
        return new uw0(i);
    }

    public static jw0 a(File file) {
        File file2 = new File(file, "uil-images");
        if (file2.exists() || file2.mkdir()) {
            file = file2;
        }
        return new mw0(file, PKIFailureInfo.badSenderNonce);
    }
}
