package supwisdom;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* JADX INFO: compiled from: GestureDetectorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ra {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f9025a;

    /* JADX INFO: compiled from: GestureDetectorCompat.java */
    public interface a {
        boolean onTouchEvent(MotionEvent motionEvent);
    }

    /* JADX INFO: compiled from: GestureDetectorCompat.java */
    public static class b implements a {
        public static final int v = ViewConfiguration.getTapTimeout();
        public static final int w = ViewConfiguration.getDoubleTapTimeout();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9026a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Handler f9027e;
        public final GestureDetector.OnGestureListener f;
        public GestureDetector.OnDoubleTapListener g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public MotionEvent m;
        public MotionEvent n;
        public boolean o;
        public float p;
        public float q;
        public float r;
        public float s;
        public boolean t;
        public VelocityTracker u;

        public b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.f9027e = new a(handler);
            } else {
                this.f9027e = new a();
            }
            this.f = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                a((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            a(context);
        }

        public final void a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            }
            if (this.f == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            this.t = true;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
            int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
            this.c = viewConfiguration.getScaledMinimumFlingVelocity();
            this.d = viewConfiguration.getScaledMaximumFlingVelocity();
            this.f9026a = scaledTouchSlop * scaledTouchSlop;
            this.b = scaledDoubleTapSlop * scaledDoubleTapSlop;
        }

        public final void b() {
            this.f9027e.removeMessages(1);
            this.f9027e.removeMessages(2);
            this.f9027e.removeMessages(3);
            this.o = false;
            this.k = false;
            this.l = false;
            this.i = false;
            if (this.j) {
                this.j = false;
            }
        }

        public void c() {
            this.f9027e.removeMessages(3);
            this.i = false;
            this.j = true;
            this.f.onLongPress(this.m);
        }

        @Override // supwisdom.ra.a
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean zOnDoubleTap;
            MotionEvent motionEvent2;
            boolean zOnFling;
            GestureDetector.OnDoubleTapListener onDoubleTapListener;
            int action = motionEvent.getAction();
            if (this.u == null) {
                this.u = VelocityTracker.obtain();
            }
            this.u.addMovement(motionEvent);
            int i = action & 255;
            boolean z = i == 6;
            int actionIndex = z ? motionEvent.getActionIndex() : -1;
            int pointerCount = motionEvent.getPointerCount();
            float x = 0.0f;
            float y = 0.0f;
            for (int i2 = 0; i2 < pointerCount; i2++) {
                if (actionIndex != i2) {
                    x += motionEvent.getX(i2);
                    y += motionEvent.getY(i2);
                }
            }
            float f = z ? pointerCount - 1 : pointerCount;
            float f2 = x / f;
            float f3 = y / f;
            if (i == 0) {
                if (this.g == null) {
                    zOnDoubleTap = false;
                } else {
                    boolean zHasMessages = this.f9027e.hasMessages(3);
                    if (zHasMessages) {
                        this.f9027e.removeMessages(3);
                    }
                    MotionEvent motionEvent3 = this.m;
                    if (motionEvent3 == null || (motionEvent2 = this.n) == null || !zHasMessages || !a(motionEvent3, motionEvent2, motionEvent)) {
                        this.f9027e.sendEmptyMessageDelayed(3, w);
                        zOnDoubleTap = false;
                    } else {
                        this.o = true;
                        zOnDoubleTap = this.g.onDoubleTap(this.m) | false | this.g.onDoubleTapEvent(motionEvent);
                    }
                }
                this.p = f2;
                this.r = f2;
                this.q = f3;
                this.s = f3;
                MotionEvent motionEvent4 = this.m;
                if (motionEvent4 != null) {
                    motionEvent4.recycle();
                }
                this.m = MotionEvent.obtain(motionEvent);
                this.k = true;
                this.l = true;
                this.h = true;
                this.j = false;
                this.i = false;
                if (this.t) {
                    this.f9027e.removeMessages(2);
                    this.f9027e.sendEmptyMessageAtTime(2, this.m.getDownTime() + ((long) v) + ((long) ViewConfiguration.getLongPressTimeout()));
                }
                this.f9027e.sendEmptyMessageAtTime(1, this.m.getDownTime() + ((long) v));
                return zOnDoubleTap | this.f.onDown(motionEvent);
            }
            if (i == 1) {
                this.h = false;
                MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                if (this.o) {
                    zOnFling = this.g.onDoubleTapEvent(motionEvent) | false;
                } else {
                    if (this.j) {
                        this.f9027e.removeMessages(3);
                        this.j = false;
                    } else if (this.k) {
                        boolean zOnSingleTapUp = this.f.onSingleTapUp(motionEvent);
                        if (this.i && (onDoubleTapListener = this.g) != null) {
                            onDoubleTapListener.onSingleTapConfirmed(motionEvent);
                        }
                        zOnFling = zOnSingleTapUp;
                    } else {
                        VelocityTracker velocityTracker = this.u;
                        int pointerId = motionEvent.getPointerId(0);
                        velocityTracker.computeCurrentVelocity(1000, this.d);
                        float yVelocity = velocityTracker.getYVelocity(pointerId);
                        float xVelocity = velocityTracker.getXVelocity(pointerId);
                        if (Math.abs(yVelocity) > this.c || Math.abs(xVelocity) > this.c) {
                            zOnFling = this.f.onFling(this.m, motionEvent, xVelocity, yVelocity);
                        }
                    }
                    zOnFling = false;
                }
                MotionEvent motionEvent5 = this.n;
                if (motionEvent5 != null) {
                    motionEvent5.recycle();
                }
                this.n = motionEventObtain;
                VelocityTracker velocityTracker2 = this.u;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.u = null;
                }
                this.o = false;
                this.i = false;
                this.f9027e.removeMessages(1);
                this.f9027e.removeMessages(2);
            } else {
                if (i != 2) {
                    if (i == 3) {
                        a();
                        return false;
                    }
                    if (i == 5) {
                        this.p = f2;
                        this.r = f2;
                        this.q = f3;
                        this.s = f3;
                        b();
                        return false;
                    }
                    if (i != 6) {
                        return false;
                    }
                    this.p = f2;
                    this.r = f2;
                    this.q = f3;
                    this.s = f3;
                    this.u.computeCurrentVelocity(1000, this.d);
                    int actionIndex2 = motionEvent.getActionIndex();
                    int pointerId2 = motionEvent.getPointerId(actionIndex2);
                    float xVelocity2 = this.u.getXVelocity(pointerId2);
                    float yVelocity2 = this.u.getYVelocity(pointerId2);
                    for (int i3 = 0; i3 < pointerCount; i3++) {
                        if (i3 != actionIndex2) {
                            int pointerId3 = motionEvent.getPointerId(i3);
                            if ((this.u.getXVelocity(pointerId3) * xVelocity2) + (this.u.getYVelocity(pointerId3) * yVelocity2) < 0.0f) {
                                this.u.clear();
                                return false;
                            }
                        }
                    }
                    return false;
                }
                if (this.j) {
                    return false;
                }
                float f4 = this.p - f2;
                float f5 = this.q - f3;
                if (this.o) {
                    return false | this.g.onDoubleTapEvent(motionEvent);
                }
                if (!this.k) {
                    if (Math.abs(f4) < 1.0f && Math.abs(f5) < 1.0f) {
                        return false;
                    }
                    boolean zOnScroll = this.f.onScroll(this.m, motionEvent, f4, f5);
                    this.p = f2;
                    this.q = f3;
                    return zOnScroll;
                }
                int i4 = (int) (f2 - this.r);
                int i5 = (int) (f3 - this.s);
                int i6 = (i4 * i4) + (i5 * i5);
                if (i6 > this.f9026a) {
                    zOnFling = this.f.onScroll(this.m, motionEvent, f4, f5);
                    this.p = f2;
                    this.q = f3;
                    this.k = false;
                    this.f9027e.removeMessages(3);
                    this.f9027e.removeMessages(1);
                    this.f9027e.removeMessages(2);
                } else {
                    zOnFling = false;
                }
                if (i6 > this.f9026a) {
                    this.l = false;
                }
            }
            return zOnFling;
        }

        /* JADX INFO: compiled from: GestureDetectorCompat.java */
        public class a extends Handler {
            public a() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    b bVar = b.this;
                    bVar.f.onShowPress(bVar.m);
                    return;
                }
                if (i == 2) {
                    b.this.c();
                    return;
                }
                if (i != 3) {
                    throw new RuntimeException("Unknown message " + message);
                }
                b bVar2 = b.this;
                GestureDetector.OnDoubleTapListener onDoubleTapListener = bVar2.g;
                if (onDoubleTapListener != null) {
                    if (bVar2.h) {
                        bVar2.i = true;
                    } else {
                        onDoubleTapListener.onSingleTapConfirmed(bVar2.m);
                    }
                }
            }

            public a(Handler handler) {
                super(handler.getLooper());
            }
        }

        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.g = onDoubleTapListener;
        }

        public final void a() {
            this.f9027e.removeMessages(1);
            this.f9027e.removeMessages(2);
            this.f9027e.removeMessages(3);
            this.u.recycle();
            this.u = null;
            this.o = false;
            this.h = false;
            this.k = false;
            this.l = false;
            this.i = false;
            if (this.j) {
                this.j = false;
            }
        }

        public final boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (!this.l || motionEvent3.getEventTime() - motionEvent2.getEventTime() > w) {
                return false;
            }
            int x = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
            int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
            return (x * x) + (y * y) < this.b;
        }
    }

    /* JADX INFO: compiled from: GestureDetectorCompat.java */
    public static class c implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final GestureDetector f9029a;

        public c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.f9029a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // supwisdom.ra.a
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.f9029a.onTouchEvent(motionEvent);
        }
    }

    public ra(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public boolean a(MotionEvent motionEvent) {
        return this.f9025a.onTouchEvent(motionEvent);
    }

    public ra(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        if (Build.VERSION.SDK_INT > 17) {
            this.f9025a = new c(context, onGestureListener, handler);
        } else {
            this.f9025a = new b(context, onGestureListener, handler);
        }
    }
}
