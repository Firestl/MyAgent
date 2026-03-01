package supwisdom;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class k71 extends TextView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l71 f8133a;
    public GradientDrawable b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f8134e;
    public int f;

    /* JADX INFO: compiled from: Proguard */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            l71 l71Var = k71.this.f8133a;
            if (l71Var != null) {
                l71Var.a();
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements View.OnTouchListener {
        public b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!k71.this.isClickable() && !k71.this.isLongClickable()) {
                return false;
            }
            k71 k71Var = k71.this;
            k71Var.f = ViewConfiguration.get(k71Var.getContext()).getScaledTouchSlop();
            int action = motionEvent.getAction();
            if (action == 0) {
                k71.this.a();
                k71.this.d = false;
                k71.this.c = true;
                k71.this.setPressed(true);
                if (k71.this.isLongClickable()) {
                    if (k71.this.f8134e == null) {
                        k71 k71Var2 = k71.this;
                        k71Var2.f8134e = new c(k71Var2, null);
                    }
                    k71.this.f8134e.a();
                }
                k71 k71Var3 = k71.this;
                k71Var3.postDelayed(k71Var3.f8134e, 3000L);
            } else if (action == 1) {
                k71.this.a(true);
                if (k71.this.isPressed() || k71.this.c) {
                    if (!k71.this.d) {
                        k71.this.b();
                        k71.this.performClick();
                    }
                    k71.this.d = false;
                    k71.this.setPressed(false);
                }
                k71.this.c = false;
            } else if (action == 2) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (!k71.this.a(x, y, r2.f)) {
                    if (k71.this.c) {
                        k71.this.b();
                        k71.this.d = false;
                        k71.this.c = false;
                    }
                    k71.this.setPressed(false);
                }
            } else if (action == 3) {
                if (k71.this.c) {
                    k71.this.b();
                    k71.this.d = false;
                    k71.this.c = false;
                }
                k71.this.setPressed(false);
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8137a;

        public c() {
        }

        public void a() {
            this.f8137a = k71.this.getWindowAttachCount();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (k71.this.isPressed() && k71.this.getParent() != null && this.f8137a == k71.this.getWindowAttachCount() && k71.this.performLongClick()) {
                k71.this.d = true;
            }
        }

        public /* synthetic */ c(k71 k71Var, a aVar) {
            this();
        }
    }

    public k71(Context context) {
        super(context);
        this.c = false;
        this.d = false;
        this.f8134e = null;
        this.f = 20;
        setOnTouchListener(new b());
    }

    public abstract void a();

    public abstract void a(boolean z);

    public final void b() {
        c cVar = this.f8134e;
        if (cVar != null) {
            removeCallbacks(cVar);
        }
    }

    public void a(l71 l71Var) {
        this.f8133a = l71Var;
        setOnClickListener(new a());
    }

    public final boolean a(float f, float f2, float f3) {
        float f4 = -f3;
        return f >= f4 && f2 >= f4 && f < ((float) (getRight() - getLeft())) + f3 && f2 < ((float) (getBottom() - getTop())) + f3;
    }
}
