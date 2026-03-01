package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import java.lang.reflect.Method;
import supwisdom.f2;
import supwisdom.lb;
import supwisdom.mc;
import supwisdom.z2;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements f2 {
    public static Method F;
    public static Method G;
    public static Method H;
    public final Handler A;
    public final Rect B;
    public Rect C;
    public boolean D;
    public PopupWindow E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1175a;
    public ListAdapter b;
    public z2 c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1176e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public boolean m;
    public boolean n;
    public int o;
    public View p;
    public int q;
    public DataSetObserver r;
    public View s;
    public Drawable t;
    public AdapterView.OnItemClickListener u;
    public AdapterView.OnItemSelectedListener v;
    public final g w;
    public final f x;
    public final e y;
    public final c z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewH = ListPopupWindow.this.h();
            if (viewH == null || viewH.getWindowToken() == null) {
                return;
            }
            ListPopupWindow.this.show();
        }
    }

    public class b implements AdapterView.OnItemSelectedListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            z2 z2Var;
            if (i == -1 || (z2Var = ListPopupWindow.this.c) == null) {
                return;
            }
            z2Var.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.g();
        }
    }

    public class d extends DataSetObserver {
        public d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    public class e implements AbsListView.OnScrollListener {
        public e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || ListPopupWindow.this.j() || ListPopupWindow.this.E.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.A.removeCallbacks(listPopupWindow.w);
            ListPopupWindow.this.w.run();
        }
    }

    public class f implements View.OnTouchListener {
        public f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.E) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.E.getWidth() && y >= 0 && y < ListPopupWindow.this.E.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.A.postDelayed(listPopupWindow.w, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
            listPopupWindow2.A.removeCallbacks(listPopupWindow2.w);
            return false;
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            z2 z2Var = ListPopupWindow.this.c;
            if (z2Var == null || !lb.K(z2Var) || ListPopupWindow.this.c.getCount() <= ListPopupWindow.this.c.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.c.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.o) {
                listPopupWindow.E.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                F = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                H = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                G = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public void a(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.r;
        if (dataSetObserver == null) {
            this.r = new d();
        } else {
            ListAdapter listAdapter2 = this.b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.r);
        }
        z2 z2Var = this.c;
        if (z2Var != null) {
            z2Var.setAdapter(this.b);
        }
    }

    public void b(int i) {
        this.g = i;
        this.i = true;
    }

    public Drawable c() {
        return this.E.getBackground();
    }

    public void d(int i) {
        this.E.setAnimationStyle(i);
    }

    @Override // supwisdom.f2
    public void dismiss() {
        this.E.dismiss();
        l();
        this.E.setContentView(null);
        this.c = null;
        this.A.removeCallbacks(this.w);
    }

    public int e() {
        if (this.i) {
            return this.g;
        }
        return 0;
    }

    public void f(int i) {
        this.l = i;
    }

    public void g(int i) {
        this.E.setInputMethodMode(i);
    }

    public void h(int i) {
        this.q = i;
    }

    public int i() {
        return this.f1176e;
    }

    @Override // supwisdom.f2
    public boolean isShowing() {
        return this.E.isShowing();
    }

    public void j(int i) {
        this.f1176e = i;
    }

    public boolean k() {
        return this.D;
    }

    public final void l() {
        View view = this.p;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.p);
            }
        }
    }

    @Override // supwisdom.f2
    public void show() {
        int iF = f();
        boolean zJ = j();
        mc.a(this.E, this.h);
        if (this.E.isShowing()) {
            if (lb.K(h())) {
                int width = this.f1176e;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = h().getWidth();
                }
                int i = this.d;
                if (i == -1) {
                    if (!zJ) {
                        iF = -1;
                    }
                    if (zJ) {
                        this.E.setWidth(this.f1176e == -1 ? -1 : 0);
                        this.E.setHeight(0);
                    } else {
                        this.E.setWidth(this.f1176e == -1 ? -1 : 0);
                        this.E.setHeight(-1);
                    }
                } else if (i != -2) {
                    iF = i;
                }
                this.E.setOutsideTouchable((this.n || this.m) ? false : true);
                this.E.update(h(), this.f, this.g, width < 0 ? -1 : width, iF < 0 ? -1 : iF);
                return;
            }
            return;
        }
        int width2 = this.f1176e;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = h().getWidth();
        }
        int i2 = this.d;
        if (i2 == -1) {
            iF = -1;
        } else if (i2 != -2) {
            iF = i2;
        }
        this.E.setWidth(width2);
        this.E.setHeight(iF);
        c(true);
        this.E.setOutsideTouchable((this.n || this.m) ? false : true);
        this.E.setTouchInterceptor(this.x);
        if (this.k) {
            mc.a(this.E, this.j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = H;
            if (method != null) {
                try {
                    method.invoke(this.E, this.C);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.E.setEpicenterBounds(this.C);
        }
        mc.a(this.E, h(), this.f, this.g, this.l);
        this.c.setSelection(-1);
        if (!this.D || this.c.isInTouchMode()) {
            g();
        }
        if (this.D) {
            return;
        }
        this.A.post(this.z);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public final void c(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.E.setIsClippedToScreen(z);
            return;
        }
        Method method = F;
        if (method != null) {
            try {
                method.invoke(this.E, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // supwisdom.f2
    public ListView d() {
        return this.c;
    }

    public final int f() {
        int measuredHeight;
        int i;
        int iMakeMeasureSpec;
        View view;
        int i2;
        if (this.c == null) {
            Context context = this.f1175a;
            new a();
            z2 z2VarA = a(context, !this.D);
            this.c = z2VarA;
            Drawable drawable = this.t;
            if (drawable != null) {
                z2VarA.setSelector(drawable);
            }
            this.c.setAdapter(this.b);
            this.c.setOnItemClickListener(this.u);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new b());
            this.c.setOnScrollListener(this.y);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.v;
            if (onItemSelectedListener != null) {
                this.c.setOnItemSelectedListener(onItemSelectedListener);
            }
            z2 z2Var = this.c;
            View view2 = this.p;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i3 = this.q;
                if (i3 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(z2Var, layoutParams);
                } else if (i3 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.q);
                } else {
                    linearLayout.addView(z2Var, layoutParams);
                    linearLayout.addView(view2);
                }
                int i4 = this.f1176e;
                if (i4 >= 0) {
                    i2 = Integer.MIN_VALUE;
                } else {
                    i4 = 0;
                    i2 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i4, i2), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = z2Var;
            }
            this.E.setContentView(view);
        } else {
            View view3 = this.p;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.E.getBackground();
        if (background != null) {
            background.getPadding(this.B);
            Rect rect = this.B;
            int i5 = rect.top;
            i = rect.bottom + i5;
            if (!this.i) {
                this.g = -i5;
            }
        } else {
            this.B.setEmpty();
            i = 0;
        }
        int iA = a(h(), this.g, this.E.getInputMethodMode() == 2);
        if (this.m || this.d == -1) {
            return iA + i;
        }
        int i6 = this.f1176e;
        if (i6 == -2) {
            int i7 = this.f1175a.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.B;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i6 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
        } else {
            int i8 = this.f1175a.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.B;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect3.left + rect3.right), 1073741824);
        }
        int iA2 = this.c.a(iMakeMeasureSpec, 0, -1, iA - measuredHeight, -1);
        if (iA2 > 0) {
            measuredHeight += i + this.c.getPaddingTop() + this.c.getPaddingBottom();
        }
        return iA2 + measuredHeight;
    }

    public void g() {
        z2 z2Var = this.c;
        if (z2Var != null) {
            z2Var.setListSelectionHidden(true);
            z2Var.requestLayout();
        }
    }

    public View h() {
        return this.s;
    }

    public void i(int i) {
        z2 z2Var = this.c;
        if (!isShowing() || z2Var == null) {
            return;
        }
        z2Var.setListSelectionHidden(false);
        z2Var.setSelection(i);
        if (z2Var.getChoiceMode() != 0) {
            z2Var.setItemChecked(i, true);
        }
    }

    public boolean j() {
        return this.E.getInputMethodMode() == 2;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public void b(boolean z) {
        this.k = true;
        this.j = z;
    }

    public void e(int i) {
        Drawable background = this.E.getBackground();
        if (background != null) {
            background.getPadding(this.B);
            Rect rect = this.B;
            this.f1176e = rect.left + rect.right + i;
            return;
        }
        j(i);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.d = -2;
        this.f1176e = -2;
        this.h = 1002;
        this.l = 0;
        this.m = false;
        this.n = false;
        this.o = Integer.MAX_VALUE;
        this.q = 0;
        this.w = new g();
        this.x = new f();
        this.y = new e();
        this.z = new c();
        this.B = new Rect();
        this.f1175a = context;
        this.A = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i, i2);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.i = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.E = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }

    public void a(boolean z) {
        this.D = z;
        this.E.setFocusable(z);
    }

    public void a(Drawable drawable) {
        this.E.setBackgroundDrawable(drawable);
    }

    public void a(View view) {
        this.s = view;
    }

    public int a() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(Rect rect) {
        this.C = rect != null ? new Rect(rect) : null;
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.u = onItemClickListener;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.E.setOnDismissListener(onDismissListener);
    }

    public z2 a(Context context, boolean z) {
        return new z2(context, z);
    }

    public final int a(View view, int i, boolean z) {
        if (Build.VERSION.SDK_INT <= 23) {
            Method method = G;
            if (method != null) {
                try {
                    return ((Integer) method.invoke(this.E, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                }
            }
            return this.E.getMaxAvailableHeight(view, i);
        }
        return this.E.getMaxAvailableHeight(view, i, z);
    }
}
