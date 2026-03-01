package supwisdom;

import android.os.Build;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public class du {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f7383a;

    public static class a implements e {
        @Override // supwisdom.du.e
        public int a(MotionEvent motionEvent) {
            return 1;
        }

        @Override // supwisdom.du.e
        public int a(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return 0;
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        @Override // supwisdom.du.e
        public float b(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getX();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        @Override // supwisdom.du.e
        public float c(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getY();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        @Override // supwisdom.du.e
        public int d(MotionEvent motionEvent, int i) {
            return i == 0 ? 0 : -1;
        }
    }

    public static class b extends a {
        @Override // supwisdom.du.a, supwisdom.du.e
        public int a(MotionEvent motionEvent) {
            return eu.a(motionEvent);
        }

        @Override // supwisdom.du.a, supwisdom.du.e
        public float b(MotionEvent motionEvent, int i) {
            return eu.c(motionEvent, i);
        }

        @Override // supwisdom.du.a, supwisdom.du.e
        public float c(MotionEvent motionEvent, int i) {
            return eu.d(motionEvent, i);
        }

        @Override // supwisdom.du.a, supwisdom.du.e
        public int d(MotionEvent motionEvent, int i) {
            return eu.a(motionEvent, i);
        }

        @Override // supwisdom.du.a, supwisdom.du.e
        public int a(MotionEvent motionEvent, int i) {
            return eu.b(motionEvent, i);
        }
    }

    public static class c extends b {
    }

    public static class d extends c {
    }

    public interface e {
        int a(MotionEvent motionEvent);

        int a(MotionEvent motionEvent, int i);

        float b(MotionEvent motionEvent, int i);

        float c(MotionEvent motionEvent, int i);

        int d(MotionEvent motionEvent, int i);
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 12) {
            f7383a = new d();
            return;
        }
        if (i >= 9) {
            f7383a = new c();
        } else if (i >= 5) {
            f7383a = new b();
        } else {
            f7383a = new a();
        }
    }

    public static int a(MotionEvent motionEvent, int i) {
        return f7383a.d(motionEvent, i);
    }

    public static int b(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public static int c(MotionEvent motionEvent) {
        return f7383a.a(motionEvent);
    }

    public static float d(MotionEvent motionEvent, int i) {
        return f7383a.c(motionEvent, i);
    }

    public static int a(MotionEvent motionEvent) {
        return (motionEvent.getAction() & 65280) >> 8;
    }

    public static int b(MotionEvent motionEvent, int i) {
        return f7383a.a(motionEvent, i);
    }

    public static float c(MotionEvent motionEvent, int i) {
        return f7383a.b(motionEvent, i);
    }
}
