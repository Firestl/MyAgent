package supwisdom;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.oa;
import supwisdom.tb;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public class lb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f8268a;
    public static boolean b;
    public static Field c;
    public static boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static WeakHashMap<View, String> f8269e;
    public static WeakHashMap<View, pb> f;
    public static Field g;
    public static boolean h;
    public static ThreadLocal<Rect> i;

    /* JADX INFO: compiled from: ViewCompat.java */
    public class a {
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class b extends g<Boolean> {
        public b(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.lb.g
        public Boolean a(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class c extends g<CharSequence> {
        public c(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.lb.g
        public CharSequence a(View view) {
            return view.getAccessibilityPaneTitle();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class d extends g<CharSequence> {
        public d(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.lb.g
        public CharSequence a(View view) {
            return view.getStateDescription();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class e extends g<Boolean> {
        public e(int i, Class cls, int i2) {
            super(i, cls, i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.lb.g
        public Boolean a(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static abstract class g<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8271a;
        public final Class<T> b;
        public final int c;

        public g(int i, Class<T> cls, int i2) {
            this(i, cls, 0, i2);
        }

        public abstract T a(View view);

        public final boolean a() {
            return Build.VERSION.SDK_INT >= 19;
        }

        public T b(View view) {
            if (b()) {
                return a(view);
            }
            if (!a()) {
                return null;
            }
            T t = (T) view.getTag(this.f8271a);
            if (this.b.isInstance(t)) {
                return t;
            }
            return null;
        }

        public g(int i, Class<T> cls, int i2, int i3) {
            this.f8271a = i;
            this.b = cls;
            this.c = i3;
        }

        public final boolean b() {
            return Build.VERSION.SDK_INT >= this.c;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class h {

        /* JADX INFO: compiled from: ViewCompat.java */
        public class a implements View.OnApplyWindowInsetsListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public tb f8272a = null;
            public final /* synthetic */ View b;
            public final /* synthetic */ gb c;

            public a(View view, gb gbVar) {
                this.b = view;
                this.c = gbVar;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                tb tbVarA = tb.a(windowInsets, view);
                if (Build.VERSION.SDK_INT < 30) {
                    h.a(windowInsets, this.b);
                    if (tbVarA.equals(this.f8272a)) {
                        return this.c.a(view, tbVarA).l();
                    }
                }
                this.f8272a = tbVarA;
                tb tbVarA2 = this.c.a(view, tbVarA);
                if (Build.VERSION.SDK_INT >= 30) {
                    return tbVarA2.l();
                }
                lb.R(view);
                return tbVarA2.l();
            }
        }

        public static tb a(View view) {
            return tb.a.a(view);
        }

        public static tb a(View view, tb tbVar, Rect rect) {
            WindowInsets windowInsetsL = tbVar.l();
            if (windowInsetsL != null) {
                return tb.a(view.computeSystemWindowInsets(windowInsetsL, rect), view);
            }
            rect.setEmpty();
            return tbVar;
        }

        public static void a(View view, gb gbVar) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(R.id.tag_on_apply_window_listener, gbVar);
            }
            if (gbVar == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new a(view, gbVar));
            }
        }

        public static void a(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class i {
        public static tb a(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            tb tbVarA = tb.a(rootWindowInsets);
            tbVarA.a(tbVarA);
            tbVarA.a(view.getRootView());
            return tbVarA;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class j {
        public static void a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public interface k {
        boolean a(View view, KeyEvent keyEvent);
    }

    static {
        new AtomicInteger(1);
        f = null;
        h = false;
        new a();
        new f();
    }

    @Deprecated
    public static float A(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float B(View view) {
        return view.getTranslationY();
    }

    public static float C(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTranslationZ();
        }
        return 0.0f;
    }

    public static int D(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static float E(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    public static boolean F(View view) {
        return b(view) != null;
    }

    public static boolean G(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static boolean H(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasOverlappingRendering();
        }
        return true;
    }

    public static boolean I(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasTransientState();
        }
        return false;
    }

    public static boolean J(View view) {
        Boolean boolB = a().b(view);
        if (boolB == null) {
            return false;
        }
        return boolB.booleanValue();
    }

    public static boolean K(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isAttachedToWindow() : view.getWindowToken() != null;
    }

    public static boolean L(View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return view.isInLayout();
        }
        return false;
    }

    public static boolean M(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean N(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        if (view instanceof ab) {
            return ((ab) view).isNestedScrollingEnabled();
        }
        return false;
    }

    public static boolean O(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.isPaddingRelative();
        }
        return false;
    }

    public static boolean P(View view) {
        Boolean boolB = d().b(view);
        if (boolB == null) {
            return false;
        }
        return boolB.booleanValue();
    }

    public static void Q(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void R(View view) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            view.requestApplyInsets();
        } else if (i2 >= 16) {
            view.requestFitSystemWindows();
        }
    }

    public static void S(View view) {
        if (l(view) == 0) {
            h(view, 1);
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (l((View) parent) == 4) {
                h(view, 2);
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void T(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof ab) {
            ((ab) view).stopNestedScroll();
        }
    }

    public static void U(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static void a(View view, @SuppressLint({"ContextFirst"}) Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 29) {
            j.a(view, context, iArr, attributeSet, typedArray, i2, i3);
        }
    }

    public static Rect b() {
        if (i == null) {
            i = new ThreadLocal<>();
        }
        Rect rect = i.get();
        if (rect == null) {
            rect = new Rect();
            i.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static View.AccessibilityDelegate c(View view) {
        if (h) {
            return null;
        }
        if (g == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                g = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                h = true;
                return null;
            }
        }
        try {
            Object obj = g.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            h = true;
            return null;
        }
    }

    public static int d(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.getAccessibilityLiveRegion();
        }
        return 0;
    }

    @Deprecated
    public static void e(View view, float f2) {
        view.setScaleY(f2);
    }

    @Deprecated
    public static void f(View view, float f2) {
        view.setTranslationX(f2);
    }

    public static void g(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setAccessibilityLiveRegion(i2);
        }
    }

    public static void h(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            view.setImportantForAccessibility(i2);
        } else if (i3 >= 16) {
            if (i2 == 4) {
                i2 = 2;
            }
            view.setImportantForAccessibility(i2);
        }
    }

    public static void i(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i2);
        }
    }

    public static void j(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayoutDirection(i2);
        }
    }

    public static boolean k(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    public static int l(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    @SuppressLint({"InlinedApi"})
    public static int m(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static int n(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static int o(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!d) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinHeight");
                c = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            d = true;
        }
        Field field = c;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static int p(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumWidth();
        }
        if (!b) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinWidth");
                f8268a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            b = true;
        }
        Field field = f8268a;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static int q(View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingEnd() : view.getPaddingRight();
    }

    public static int r(View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingStart() : view.getPaddingLeft();
    }

    public static ViewParent s(View view) {
        return Build.VERSION.SDK_INT >= 16 ? view.getParentForAccessibility() : view.getParent();
    }

    public static tb t(View view) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            return i.a(view);
        }
        if (i2 >= 21) {
            return h.a(view);
        }
        return null;
    }

    @Deprecated
    public static float u(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float v(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float w(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float x(View view) {
        return view.getScaleY();
    }

    public static final CharSequence y(View view) {
        return e().b(view);
    }

    public static String z(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = f8269e;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static void e(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetLeftAndRight(i2);
            return;
        }
        if (i3 < 21) {
            b(view, i2);
            return;
        }
        Rect rectB = b();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectB.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectB.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        b(view, i2);
        if (z && rectB.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectB);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList f(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof jb) {
            return ((jb) view).getSupportBackgroundTintList();
        }
        return null;
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class f implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f8270a = new WeakHashMap<>();

        public final void a(View view, boolean z) {
            boolean z2 = view.getVisibility() == 0;
            if (z != z2) {
                lb.d(view, z2 ? 16 : 32);
                this.f8270a.put(view, Boolean.valueOf(z2));
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT < 28) {
                for (Map.Entry<View, Boolean> entry : this.f8270a.entrySet()) {
                    a(entry.getKey(), entry.getValue().booleanValue());
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            a(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }

        public final void a(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class l {
        public static final ArrayList<WeakReference<View>> d = new ArrayList<>();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f8273a = null;
        public SparseArray<WeakReference<View>> b = null;
        public WeakReference<KeyEvent> c = null;

        public final SparseArray<WeakReference<View>> a() {
            if (this.b == null) {
                this.b = new SparseArray<>();
            }
            return this.b;
        }

        public final View b(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f8273a;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewB = b(viewGroup.getChildAt(childCount), keyEvent);
                        if (viewB != null) {
                            return viewB;
                        }
                    }
                }
                if (c(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        public final boolean c(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((k) arrayList.get(size)).a(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        public static l a(View view) {
            l lVar = (l) view.getTag(R.id.tag_unhandled_key_event_manager);
            if (lVar != null) {
                return lVar;
            }
            l lVar2 = new l();
            view.setTag(R.id.tag_unhandled_key_event_manager, lVar2);
            return lVar2;
        }

        public boolean a(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                b();
            }
            View viewB = b(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewB != null && !KeyEvent.isModifierKey(keyCode)) {
                    a().put(keyCode, new WeakReference<>(viewB));
                }
            }
            return viewB != null;
        }

        public final void b() {
            WeakHashMap<View, Boolean> weakHashMap = this.f8273a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (d.isEmpty()) {
                return;
            }
            synchronized (d) {
                if (this.f8273a == null) {
                    this.f8273a = new WeakHashMap<>();
                }
                for (int size = d.size() - 1; size >= 0; size--) {
                    View view = d.get(size).get();
                    if (view == null) {
                        d.remove(size);
                    } else {
                        this.f8273a.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f8273a.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        public boolean a(KeyEvent keyEvent) {
            int iIndexOfKey;
            WeakReference<KeyEvent> weakReference = this.c;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.c = new WeakReference<>(keyEvent);
            WeakReference<View> weakReferenceValueAt = null;
            SparseArray<WeakReference<View>> sparseArrayA = a();
            if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArrayA.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                weakReferenceValueAt = sparseArrayA.valueAt(iIndexOfKey);
                sparseArrayA.removeAt(iIndexOfKey);
            }
            if (weakReferenceValueAt == null) {
                weakReferenceValueAt = sparseArrayA.get(keyEvent.getKeyCode());
            }
            if (weakReferenceValueAt == null) {
                return false;
            }
            View view = weakReferenceValueAt.get();
            if (view != null && lb.K(view)) {
                c(view, keyEvent);
            }
            return true;
        }
    }

    @Deprecated
    public static boolean a(View view, int i2) {
        return view.canScrollVertically(i2);
    }

    @Deprecated
    public static void d(View view, float f2) {
        view.setScaleX(f2);
    }

    @Deprecated
    public static void g(View view, float f2) {
        view.setTranslationY(f2);
    }

    public static Display i(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getDisplay();
        }
        if (K(view)) {
            return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public static float j(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void k(View view, int i2) {
        if (view instanceof ya) {
            ((ya) view).stopNestedScroll(i2);
        } else if (i2 == 0) {
            T(view);
        }
    }

    public static void a(View view, xb xbVar) {
        view.onInitializeAccessibilityNodeInfo(xbVar.z());
    }

    public static g<Boolean> d() {
        return new b(R.id.tag_screen_reader_focusable, Boolean.class, 28);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode g(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof jb) {
            return ((jb) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static void h(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTranslationZ(f2);
        }
    }

    public static void a(View view, oa oaVar) {
        if (oaVar == null && (b(view) instanceof oa.a)) {
            oaVar = new oa();
        }
        view.setAccessibilityDelegate(oaVar == null ? null : oaVar.getBridge());
    }

    public static void d(View view, int i2) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = e(view) != null && view.getVisibility() == 0;
            if (d(view) != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i2);
                if (z) {
                    accessibilityEventObtain.getText().add(e(view));
                    S(view);
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i2 == 32) {
                AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.setEventType(32);
                accessibilityEventObtain2.setContentChangeTypes(i2);
                accessibilityEventObtain2.setSource(view);
                view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.getText().add(e(view));
                accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i2);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                }
            }
        }
    }

    public static void f(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetTopAndBottom(i2);
            return;
        }
        if (i3 >= 21) {
            Rect rectB = b();
            boolean z = false;
            Object parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                rectB.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !rectB.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            c(view, i2);
            if (z && rectB.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(rectB);
                return;
            }
            return;
        }
        c(view, i2);
    }

    public static Rect h(View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return view.getClipBounds();
        }
        return null;
    }

    public static View.AccessibilityDelegate b(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return view.getAccessibilityDelegate();
        }
        return c(view);
    }

    public static void a(View view, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation(i2, i3, i4, i5);
        } else {
            view.postInvalidate(i2, i3, i4, i5);
        }
    }

    public static void b(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setHasTransientState(z);
        }
    }

    public static void a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    @Deprecated
    public static void c(View view, float f2) {
        view.setRotationY(f2);
    }

    public static void b(View view, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(i2, i3, i4, i5);
        } else {
            view.setPadding(i2, i3, i4, i5);
        }
    }

    public static void c(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (view.getVisibility() == 0) {
            U(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                U((View) parent);
            }
        }
    }

    public static void a(View view, Runnable runnable, long j2) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j2);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j2);
        }
    }

    @Deprecated
    public static void b(View view, float f2) {
        view.setRotationX(f2);
    }

    public static tb b(View view, tb tbVar) {
        WindowInsets windowInsetsL;
        if (Build.VERSION.SDK_INT >= 21 && (windowInsetsL = tbVar.l()) != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsL);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsL)) {
                return tb.a(windowInsetsOnApplyWindowInsets, view);
            }
        }
        return tbVar;
    }

    public static boolean a(View view, int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.performAccessibilityAction(i2, bundle);
        }
        return false;
    }

    public static g<CharSequence> c() {
        return new c(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    public static CharSequence e(View view) {
        return c().b(view);
    }

    @Deprecated
    public static void a(View view, int i2, Paint paint) {
        view.setLayerType(i2, paint);
    }

    public static g<CharSequence> e() {
        return new d(R.id.tag_state_description, CharSequence.class, 64, 30);
    }

    public static void a(View view, Paint paint) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayerPaint(paint);
        } else {
            view.setLayerType(view.getLayerType(), paint);
            view.invalidate();
        }
    }

    public static void b(View view, int i2) {
        view.offsetLeftAndRight(i2);
        if (view.getVisibility() == 0) {
            U(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                U((View) parent);
            }
        }
    }

    public static pb a(View view) {
        if (f == null) {
            f = new WeakHashMap<>();
        }
        pb pbVar = f.get(view);
        if (pbVar != null) {
            return pbVar;
        }
        pb pbVar2 = new pb(view);
        f.put(view, pbVar2);
        return pbVar2;
    }

    public static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return l.a(view).a(keyEvent);
    }

    public static void a(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f2);
        }
    }

    public static void a(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (f8269e == null) {
            f8269e = new WeakHashMap<>();
        }
        f8269e.put(view, str);
    }

    @Deprecated
    public static void a(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    public static void a(View view, gb gbVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            h.a(view, gbVar);
        }
    }

    public static tb a(View view, tb tbVar) {
        WindowInsets windowInsetsL;
        if (Build.VERSION.SDK_INT >= 21 && (windowInsetsL = tbVar.l()) != null) {
            WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsetsL);
            if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsetsL)) {
                return tb.a(windowInsetsDispatchApplyWindowInsets, view);
            }
        }
        return tbVar;
    }

    public static tb a(View view, tb tbVar, Rect rect) {
        return Build.VERSION.SDK_INT >= 21 ? h.a(view, tbVar, rect) : tbVar;
    }

    public static void a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background == null || !z) {
                    return;
                }
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
                return;
            }
            return;
        }
        if (view instanceof jb) {
            ((jb) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(mode);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background == null || !z) {
                    return;
                }
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
                return;
            }
            return;
        }
        if (view instanceof jb) {
            ((jb) view).setSupportBackgroundTintMode(mode);
        }
    }

    public static void a(View view, Rect rect) {
        if (Build.VERSION.SDK_INT >= 18) {
            view.setClipBounds(rect);
        }
    }

    public static void a(View view, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i2, i3);
        }
    }

    public static void a(View view, ib ibVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon((PointerIcon) (ibVar != null ? ibVar.a() : null));
        }
    }

    public static boolean a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return l.a(view).a(view, keyEvent);
    }

    public static g<Boolean> a() {
        return new e(R.id.tag_accessibility_heading, Boolean.class, 28);
    }
}
