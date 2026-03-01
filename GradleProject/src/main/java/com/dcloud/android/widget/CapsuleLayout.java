package com.dcloud.android.widget;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CapsuleLayout extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1792a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f1793e;
    public List<View> f;
    public int g;
    public int h;
    public boolean i;

    public enum ButtonType {
        LIFT,
        MIDDLE,
        RIGHT
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1794a;

        static {
            int[] iArr = new int[ButtonType.values().length];
            f1794a = iArr;
            try {
                iArr[ButtonType.LIFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1794a[ButtonType.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1794a[ButtonType.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class b extends GradientDrawable {
        public b() {
        }

        public /* synthetic */ b(CapsuleLayout capsuleLayout, a aVar) {
            this();
        }
    }

    public CapsuleLayout(Context context, float f) {
        super(context);
        this.h = 1;
        this.i = false;
        this.f1792a = f;
        this.f = new ArrayList();
        a(Color.parseColor("#ffffffff"), Color.parseColor("#ffe5e5e5"), 1);
        this.g = Color.parseColor("#CBCCCD");
    }

    public void a(View view, LinearLayout.LayoutParams layoutParams, ButtonType buttonType, View.OnClickListener onClickListener) {
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        layoutParams2.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.addView(view, layoutParams);
        addView(linearLayout, layoutParams2);
        linearLayout.setOnClickListener(onClickListener);
        a(linearLayout, buttonType);
    }

    public final void b() {
        Iterator<View> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().setBackgroundColor(this.b);
        }
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        super.removeAllViews();
        this.f.clear();
    }

    public void setAngle(float f) {
        this.f1792a = f;
        a();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable instanceof b) {
            super.setBackground(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.d = i;
        a();
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
    }

    public void setRoundColor(int i) {
        this.b = i;
        a();
    }

    public void a(float f) {
        View view = new View(getContext());
        view.setBackgroundColor(this.b);
        addView(view, new LinearLayout.LayoutParams(this.c, (int) (f * 18.0f)));
        this.f.add(view);
    }

    public int a(int i) {
        if (this.i) {
            return 1;
        }
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        if (i2 > 235 && i3 > 235 && i4 > 235) {
            if (this.h == 2) {
                this.h = 1;
                a(Color.parseColor("#ffffffff"), Color.parseColor("#ffe5e5e5"), 1);
                b();
            }
        } else if (this.h == 1) {
            this.h = 2;
            a(Color.parseColor("#1a000000"), Color.parseColor("#4de5e5e5"), 1);
            b();
        }
        return this.h;
    }

    public final void a(View view, ButtonType buttonType) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        a aVar = null;
        b bVar = new b(this, aVar);
        b bVar2 = new b(this, aVar);
        float[] fArr = new float[0];
        int i = a.f1794a[buttonType.ordinal()];
        if (i == 1) {
            float f = this.f1792a;
            fArr = new float[]{f, f, 0.0f, 0.0f, 0.0f, 0.0f, f, f};
            bVar2.setStroke(this.c, 0);
        } else if (i == 2) {
            float f2 = this.f1792a;
            fArr = new float[]{0.0f, 0.0f, f2, f2, f2, f2, 0.0f, 0.0f};
            bVar2.setStroke(this.c, 0);
        } else if (i == 3) {
            fArr = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        }
        bVar2.setCornerRadii(fArr);
        bVar.setCornerRadii(fArr);
        bVar.setColor(0);
        bVar2.setColor(this.g);
        stateListDrawable.addState(new int[]{R.attr.state_pressed, R.attr.state_enabled}, bVar2);
        stateListDrawable.addState(new int[]{R.attr.state_enabled, R.attr.state_focused}, bVar2);
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, bVar);
        stateListDrawable.addState(new int[]{R.attr.state_focused}, bVar2);
        stateListDrawable.addState(new int[]{R.attr.state_window_focused}, bVar2);
        stateListDrawable.addState(new int[0], bVar);
        if (Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(stateListDrawable);
        } else {
            view.setBackground(stateListDrawable);
        }
    }

    public void a(View view, ButtonType buttonType, int i) {
        if (view == null || view.getParent() == null) {
            return;
        }
        this.g = i;
        a((View) view.getParent(), buttonType);
    }

    public void a(int i, int i2, int i3) {
        this.d = i;
        this.b = i2;
        this.c = i3;
        a();
    }

    public final void a() {
        if (this.f1793e == null) {
            b bVar = new b(this, null);
            this.f1793e = bVar;
            if (Build.VERSION.SDK_INT < 16) {
                setBackgroundDrawable(bVar);
            } else {
                setBackground(bVar);
            }
        }
        this.f1793e.setCornerRadius(this.f1792a);
        this.f1793e.setStroke(this.c, this.b);
        this.f1793e.setColor(this.d);
        this.f1793e.invalidateSelf();
    }
}
