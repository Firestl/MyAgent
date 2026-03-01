package pl.droidsonroids.gif;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class GifRenderingExecutor extends ScheduledThreadPoolExecutor {

    public static final class InstanceHolder {
        public static final GifRenderingExecutor INSTANCE = new GifRenderingExecutor();
    }

    public static GifRenderingExecutor getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public GifRenderingExecutor() {
        super(1, new ThreadPoolExecutor.DiscardPolicy());
    }
}
