package pl.droidsonroids.gif;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class InvalidationHandler extends Handler {
    public static final int MSG_TYPE_INVALIDATION = -1;
    public final WeakReference<GifDrawable> mDrawableRef;

    public InvalidationHandler(GifDrawable gifDrawable) {
        super(Looper.getMainLooper());
        this.mDrawableRef = new WeakReference<>(gifDrawable);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        GifDrawable gifDrawable = this.mDrawableRef.get();
        if (gifDrawable == null) {
            return;
        }
        if (message.what == -1) {
            gifDrawable.invalidateSelf();
            return;
        }
        Iterator<AnimationListener> it = gifDrawable.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onAnimationCompleted(message.what);
        }
    }
}
