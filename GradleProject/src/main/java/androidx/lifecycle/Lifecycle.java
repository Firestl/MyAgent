package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;
import supwisdom.wd;

/* JADX INFO: loaded from: classes.dex */
public abstract class Lifecycle {

    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY
    }

    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public boolean isAtLeast(State state) {
            return compareTo(state) >= 0;
        }
    }

    public Lifecycle() {
        new AtomicReference();
    }

    public abstract State a();

    public abstract void a(wd wdVar);

    public abstract void b(wd wdVar);
}
