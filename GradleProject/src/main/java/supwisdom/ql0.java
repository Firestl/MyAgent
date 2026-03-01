package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: compiled from: BottomSheetDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class ql0 extends u0 {
    public BottomSheetBehavior<FrameLayout> c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8934e;
    public boolean f;
    public BottomSheetBehavior.c g;

    /* JADX INFO: compiled from: BottomSheetDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ql0 ql0Var = ql0.this;
            if (ql0Var.d && ql0Var.isShowing() && ql0.this.b()) {
                ql0.this.cancel();
            }
        }
    }

    /* JADX INFO: compiled from: BottomSheetDialog.java */
    public class b extends oa {
        public b() {
        }

        @Override // supwisdom.oa
        public void onInitializeAccessibilityNodeInfo(View view, xb xbVar) {
            super.onInitializeAccessibilityNodeInfo(view, xbVar);
            if (!ql0.this.d) {
                xbVar.g(false);
            } else {
                xbVar.a(1048576);
                xbVar.g(true);
            }
        }

        @Override // supwisdom.oa
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i == 1048576) {
                ql0 ql0Var = ql0.this;
                if (ql0Var.d) {
                    ql0Var.cancel();
                    return true;
                }
            }
            return super.performAccessibilityAction(view, i, bundle);
        }
    }

    /* JADX INFO: compiled from: BottomSheetDialog.java */
    public class c implements View.OnTouchListener {
        public c() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* JADX INFO: compiled from: BottomSheetDialog.java */
    public class d extends BottomSheetBehavior.c {
        public d() {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.c
        public void a(View view, float f) {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.c
        public void a(View view, int i) {
            if (i == 5) {
                ql0.this.cancel();
            }
        }
    }

    public ql0(Context context, int i) {
        super(context, a(context, i));
        this.d = true;
        this.f8934e = true;
        this.g = new d();
        a(1);
    }

    public final View a(int i, View view, ViewGroup.LayoutParams layoutParams) {
        FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) frameLayout.findViewById(R.id.coordinator);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, (ViewGroup) coordinatorLayout, false);
        }
        FrameLayout frameLayout2 = (FrameLayout) coordinatorLayout.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior<FrameLayout> bottomSheetBehaviorB = BottomSheetBehavior.b(frameLayout2);
        this.c = bottomSheetBehaviorB;
        bottomSheetBehaviorB.a(this.g);
        this.c.b(this.d);
        if (layoutParams == null) {
            frameLayout2.addView(view);
        } else {
            frameLayout2.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new a());
        lb.a(frameLayout2, new b());
        frameLayout2.setOnTouchListener(new c());
        return frameLayout;
    }

    public boolean b() {
        if (!this.f) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{android.R.attr.windowCloseOnTouchOutside});
            this.f8934e = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
            this.f = true;
        }
        return this.f8934e;
    }

    @Override // supwisdom.u0, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(67108864);
                window.addFlags(Integer.MIN_VALUE);
            }
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.c;
        if (bottomSheetBehavior == null || bottomSheetBehavior.d() != 5) {
            return;
        }
        this.c.c(4);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.d != z) {
            this.d = z;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.c;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.b(z);
            }
        }
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.d) {
            this.d = true;
        }
        this.f8934e = z;
        this.f = true;
    }

    @Override // supwisdom.u0, android.app.Dialog
    public void setContentView(int i) {
        super.setContentView(a(i, null, null));
    }

    @Override // supwisdom.u0, android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(a(0, view, null));
    }

    @Override // supwisdom.u0, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(a(0, view, layoutParams));
    }

    public static int a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, typedValue, true)) {
            return typedValue.resourceId;
        }
        return R.style.Theme_Design_Light_BottomSheetDialog;
    }
}
