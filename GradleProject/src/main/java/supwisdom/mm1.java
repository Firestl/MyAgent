package supwisdom;

import android.R;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/* JADX INFO: compiled from: AndroidBug5497Workaround.java */
/* JADX INFO: loaded from: classes2.dex */
public class mm1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f8406a;
    public int b;
    public FrameLayout.LayoutParams c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8407e = true;
    public Activity f;
    public int g;

    /* JADX INFO: compiled from: AndroidBug5497Workaround.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (mm1.this.f8407e) {
                mm1 mm1Var = mm1.this;
                mm1Var.d = mm1Var.f8406a.getHeight();
                mm1.this.f8407e = false;
            }
            mm1.this.b();
        }
    }

    public mm1(Activity activity) {
        this.g = activity.getResources().getDimensionPixelSize(activity.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        this.f = activity;
        View childAt = ((FrameLayout) activity.findViewById(R.id.content)).getChildAt(0);
        this.f8406a = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new a());
        this.c = (FrameLayout.LayoutParams) this.f8406a.getLayoutParams();
    }

    public final void b() {
        int iA = a();
        if (iA != this.b) {
            int height = this.f8406a.getRootView().getHeight();
            int i = height - iA;
            if (i <= height / 4) {
                this.c.height = this.d;
            } else if (Build.VERSION.SDK_INT >= 19) {
                this.c.height = (height - i) + this.g;
            } else {
                this.c.height = height - i;
            }
            this.f8406a.requestLayout();
            this.b = iA;
        }
    }

    public static void a(Activity activity) {
        new mm1(activity);
    }

    public final int a() {
        Rect rect = new Rect();
        this.f8406a.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }
}
