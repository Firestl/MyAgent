package supwisdom;

import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import supwisdom.ac;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class xb {
    public static int d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AccessibilityNodeInfo f9729a;
    public int b = -1;
    public int c = -1;

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class a {
        public static final a d = new a(1, null);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f9730e = new a(2, null);
        public static final a f;
        public static final a g;
        public static final a h;
        public static final a i;
        public static final a j;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f9731a;
        public final Class<? extends ac.a> b;
        public final ac c;

        static {
            new a(4, null);
            new a(8, null);
            f = new a(16, null);
            new a(32, null);
            new a(64, null);
            new a(128, null);
            new a(256, null, ac.b.class);
            new a(512, null, ac.b.class);
            new a(1024, null, ac.c.class);
            new a(2048, null, ac.c.class);
            g = new a(4096, null);
            h = new a(8192, null);
            new a(16384, null);
            new a(32768, null);
            new a(65536, null);
            new a(131072, null, ac.g.class);
            new a(262144, null);
            new a(524288, null);
            new a(1048576, null);
            new a(PKIFailureInfo.badSenderNonce, null, ac.h.class);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, null, null, ac.e.class);
            i = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null, null, null);
            j = new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            new a(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            new a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null, null, null);
            new a(Build.VERSION.SDK_INT >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, ac.f.class);
            new a(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, ac.d.class);
            new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            new a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
            new a(Build.VERSION.SDK_INT >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null, null, null);
            new a(Build.VERSION.SDK_INT >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null, null, null);
        }

        public a(int i2, CharSequence charSequence) {
            this(null, i2, charSequence, null, null);
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.f9731a).getId();
            }
            return 0;
        }

        public CharSequence b() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.f9731a).getLabel();
            }
            return null;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            Object obj2 = this.f9731a;
            return obj2 == null ? aVar.f9731a == null : obj2.equals(aVar.f9731a);
        }

        public int hashCode() {
            Object obj = this.f9731a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public a(Object obj) {
            this(obj, 0, null, null, null);
        }

        public a(int i2, CharSequence charSequence, Class<? extends ac.a> cls) {
            this(null, i2, charSequence, null, cls);
        }

        public boolean a(View view, Bundle bundle) {
            if (this.c == null) {
                return false;
            }
            ac.a aVar = null;
            Class<? extends ac.a> cls = this.b;
            if (cls != null) {
                try {
                    ac.a aVarNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        aVarNewInstance.a(bundle);
                        aVar = aVarNewInstance;
                    } catch (Exception e2) {
                        e = e2;
                        aVar = aVarNewInstance;
                        Class<? extends ac.a> cls2 = this.b;
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? com.igexin.push.core.b.m : cls2.getName()), e);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            return this.c.a(view, aVar);
        }

        public a(Object obj, int i2, CharSequence charSequence, ac acVar, Class<? extends ac.a> cls) {
            this.c = acVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.f9731a = new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence);
            } else {
                this.f9731a = obj;
            }
            this.b = cls;
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f9732a;

        public b(Object obj) {
            this.f9732a = obj;
        }

        public static b a(int i, int i2, boolean z, int i3) {
            int i4 = Build.VERSION.SDK_INT;
            return i4 >= 21 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3)) : i4 >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z)) : new b(null);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f9733a;

        public c(Object obj) {
            this.f9733a = obj;
        }

        public static c a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            int i5 = Build.VERSION.SDK_INT;
            return i5 >= 21 ? new c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2)) : i5 >= 19 ? new c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z)) : new c(null);
        }
    }

    public xb(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f9729a = accessibilityNodeInfo;
    }

    public static xb A() {
        return a(AccessibilityNodeInfo.obtain());
    }

    public static xb a(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new xb(accessibilityNodeInfo);
    }

    public static String c(int i) {
        if (i == 1) {
            return "ACTION_FOCUS";
        }
        if (i == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case 262144:
                return "ACTION_EXPAND";
            case 524288:
                return "ACTION_COLLAPSE";
            case PKIFailureInfo.badSenderNonce /* 2097152 */:
                return "ACTION_SET_TEXT";
            case R.id.accessibilityActionMoveWindow:
                return "ACTION_MOVE_WINDOW";
            case R.id.accessibilityActionImeEnter:
                return "ACTION_IME_ENTER";
            default:
                switch (i) {
                    case R.id.accessibilityActionShowOnScreen:
                        return "ACTION_SHOW_ON_SCREEN";
                    case R.id.accessibilityActionScrollToPosition:
                        return "ACTION_SCROLL_TO_POSITION";
                    case R.id.accessibilityActionScrollUp:
                        return "ACTION_SCROLL_UP";
                    case R.id.accessibilityActionScrollLeft:
                        return "ACTION_SCROLL_LEFT";
                    case R.id.accessibilityActionScrollDown:
                        return "ACTION_SCROLL_DOWN";
                    case R.id.accessibilityActionScrollRight:
                        return "ACTION_SCROLL_RIGHT";
                    case R.id.accessibilityActionContextClick:
                        return "ACTION_CONTEXT_CLICK";
                    case R.id.accessibilityActionSetProgress:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (i) {
                            case R.id.accessibilityActionShowTooltip:
                                return "ACTION_SHOW_TOOLTIP";
                            case R.id.accessibilityActionHideTooltip:
                                return "ACTION_HIDE_TOOLTIP";
                            case R.id.accessibilityActionPageUp:
                                return "ACTION_PAGE_UP";
                            case R.id.accessibilityActionPageDown:
                                return "ACTION_PAGE_DOWN";
                            case R.id.accessibilityActionPageLeft:
                                return "ACTION_PAGE_LEFT";
                            case R.id.accessibilityActionPageRight:
                                return "ACTION_PAGE_RIGHT";
                            case R.id.accessibilityActionPressAndHold:
                                return "ACTION_PRESS_AND_HOLD";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    public static xb g(View view) {
        return a(AccessibilityNodeInfo.obtain(view));
    }

    public boolean b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f9729a.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f9731a);
        }
        return false;
    }

    public void c(View view, int i) {
        this.c = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9729a.setSource(view, i);
        }
    }

    public int d() {
        return this.f9729a.getChildCount();
    }

    public void e(View view) {
        this.b = -1;
        this.f9729a.setParent(view);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof xb)) {
            return false;
        }
        xb xbVar = (xb) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f9729a;
        if (accessibilityNodeInfo == null) {
            if (xbVar.f9729a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(xbVar.f9729a)) {
            return false;
        }
        return this.c == xbVar.c && this.b == xbVar.b;
    }

    public void f(View view) {
        this.c = -1;
        this.f9729a.setSource(view);
    }

    public int h() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f9729a.getMovementGranularities();
        }
        return 0;
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f9729a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public void i(boolean z) {
        this.f9729a.setFocusable(z);
    }

    public void j(boolean z) {
        this.f9729a.setFocused(z);
    }

    public String k() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f9729a.getViewIdResourceName();
        }
        return null;
    }

    public void l(boolean z) {
        this.f9729a.setLongClickable(z);
    }

    public boolean m() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f9729a.isAccessibilityFocused();
        }
        return false;
    }

    public boolean n() {
        return this.f9729a.isCheckable();
    }

    public boolean o() {
        return this.f9729a.isChecked();
    }

    public boolean p() {
        return this.f9729a.isClickable();
    }

    public void q(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9729a.setVisibleToUser(z);
        }
    }

    public boolean r() {
        return this.f9729a.isFocusable();
    }

    public boolean s() {
        return this.f9729a.isFocused();
    }

    public boolean t() {
        return this.f9729a.isLongClickable();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        a(rect);
        sb.append("; boundsInParent: " + rect);
        b(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(i());
        sb.append("; className: ");
        sb.append(e());
        sb.append("; text: ");
        sb.append(j());
        sb.append("; contentDescription: ");
        sb.append(f());
        sb.append("; viewId: ");
        sb.append(k());
        sb.append("; checkable: ");
        sb.append(n());
        sb.append("; checked: ");
        sb.append(o());
        sb.append("; focusable: ");
        sb.append(r());
        sb.append("; focused: ");
        sb.append(s());
        sb.append("; selected: ");
        sb.append(w());
        sb.append("; clickable: ");
        sb.append(p());
        sb.append("; longClickable: ");
        sb.append(t());
        sb.append("; enabled: ");
        sb.append(q());
        sb.append("; password: ");
        sb.append(u());
        sb.append("; scrollable: " + v());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<a> listB = b();
            for (int i = 0; i < listB.size(); i++) {
                a aVar = listB.get(i);
                String strC = c(aVar.a());
                if (strC.equals("ACTION_UNKNOWN") && aVar.b() != null) {
                    strC = aVar.b().toString();
                }
                sb.append(strC);
                if (i != listB.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            int iC = c();
            while (iC != 0) {
                int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(iC);
                iC &= ~iNumberOfTrailingZeros;
                sb.append(c(iNumberOfTrailingZeros));
                if (iC != 0) {
                    sb.append(", ");
                }
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public boolean u() {
        return this.f9729a.isPassword();
    }

    public boolean v() {
        return this.f9729a.isScrollable();
    }

    public boolean w() {
        return this.f9729a.isSelected();
    }

    public boolean x() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f9729a.isVisibleToUser();
        }
        return false;
    }

    public void y() {
        this.f9729a.recycle();
    }

    public AccessibilityNodeInfo z() {
        return this.f9729a;
    }

    public static xb a(xb xbVar) {
        return a(AccessibilityNodeInfo.obtain(xbVar.f9729a));
    }

    public void d(Rect rect) {
        this.f9729a.setBoundsInScreen(rect);
    }

    public Bundle g() {
        return Build.VERSION.SDK_INT >= 19 ? this.f9729a.getExtras() : new Bundle();
    }

    public CharSequence i() {
        return this.f9729a.getPackageName();
    }

    public CharSequence j() {
        if (!l()) {
            return this.f9729a.getText();
        }
        List<Integer> listA = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listA2 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listA3 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listA4 = a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f9729a.getText(), 0, this.f9729a.getText().length()));
        for (int i = 0; i < listA.size(); i++) {
            spannableString.setSpan(new ub(listA4.get(i).intValue(), this, g().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listA.get(i).intValue(), listA2.get(i).intValue(), listA3.get(i).intValue());
        }
        return spannableString;
    }

    public final boolean l() {
        return !a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public void n(boolean z) {
        this.f9729a.setScrollable(z);
    }

    public void o(boolean z) {
        this.f9729a.setSelected(z);
    }

    public void p(boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f9729a.setShowingHintText(z);
        } else {
            a(4, z);
        }
    }

    public void a(View view) {
        this.f9729a.addChild(view);
    }

    public void b(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9729a.setMovementGranularities(i);
        }
    }

    public void d(boolean z) {
        this.f9729a.setChecked(z);
    }

    public void e(boolean z) {
        this.f9729a.setClickable(z);
    }

    public CharSequence f() {
        return this.f9729a.getContentDescription();
    }

    public void h(boolean z) {
        this.f9729a.setEnabled(z);
    }

    public void i(CharSequence charSequence) {
        this.f9729a.setText(charSequence);
    }

    public void k(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f9729a.setHeading(z);
        } else {
            a(2, z);
        }
    }

    public void m(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f9729a.setScreenReaderFocusable(z);
        } else {
            a(1, z);
        }
    }

    public boolean q() {
        return this.f9729a.isEnabled();
    }

    public void a(View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9729a.addChild(view, i);
        }
    }

    public int c() {
        return this.f9729a.getActions();
    }

    public final void d(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayC = c(view);
        if (sparseArrayC != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < sparseArrayC.size(); i++) {
                if (sparseArrayC.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                sparseArrayC.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    public void e(CharSequence charSequence) {
        this.f9729a.setPackageName(charSequence);
    }

    public void f(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.setContentInvalid(z);
        }
    }

    public void h(CharSequence charSequence) {
        if (k9.a()) {
            this.f9729a.setStateDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
        }
    }

    public void b(View view, int i) {
        this.b = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9729a.setParent(view, i);
        }
    }

    @Deprecated
    public void c(Rect rect) {
        this.f9729a.setBoundsInParent(rect);
    }

    public CharSequence e() {
        return this.f9729a.getClassName();
    }

    public void g(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.setDismissable(z);
        }
    }

    public void a(int i) {
        this.f9729a.addAction(i);
    }

    public void c(boolean z) {
        this.f9729a.setCheckable(z);
    }

    public void f(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f9729a.setPaneTitle(charSequence);
        } else if (i >= 19) {
            this.f9729a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public final List<Integer> a(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f9729a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f9729a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public final SparseArray<WeakReference<ClickableSpan>> c(View view) {
        return (SparseArray) view.getTag(androidx.core.R.id.tag_accessibility_clickable_spans);
    }

    public void g(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
        }
    }

    public void b(Rect rect) {
        this.f9729a.getBoundsInScreen(rect);
    }

    public void c(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f9729a.setError(charSequence);
        }
    }

    public final SparseArray<WeakReference<ClickableSpan>> b(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayC = c(view);
        if (sparseArrayC != null) {
            return sparseArrayC;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(androidx.core.R.id.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    public void d(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.f9729a.setHintText(charSequence);
        } else if (i >= 19) {
            this.f9729a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    public void b(CharSequence charSequence) {
        this.f9729a.setContentDescription(charSequence);
    }

    public void a(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f9729a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f9731a);
        }
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((c) obj).f9733a);
        }
    }

    public boolean a(int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f9729a.performAction(i, bundle);
        }
        return false;
    }

    public static ClickableSpan[] j(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    @Deprecated
    public void a(Rect rect) {
        this.f9729a.getBoundsInParent(rect);
    }

    public List<a> b() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f9729a.getActionList() : null;
        if (actionList != null) {
            ArrayList arrayList = new ArrayList();
            int size = actionList.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new a(actionList.get(i)));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public void a(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9729a.setAccessibilityFocused(z);
        }
    }

    public void a(CharSequence charSequence) {
        this.f9729a.setClassName(charSequence);
    }

    public void a(CharSequence charSequence, View view) {
        int i = Build.VERSION.SDK_INT;
        if (i < 19 || i >= 26) {
            return;
        }
        a();
        d(view);
        ClickableSpan[] clickableSpanArrJ = j(charSequence);
        if (clickableSpanArrJ == null || clickableSpanArrJ.length <= 0) {
            return;
        }
        g().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", androidx.core.R.id.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayB = b(view);
        for (int i2 = 0; clickableSpanArrJ != null && i2 < clickableSpanArrJ.length; i2++) {
            int iA = a(clickableSpanArrJ[i2], sparseArrayB);
            sparseArrayB.put(iA, new WeakReference<>(clickableSpanArrJ[i2]));
            a(clickableSpanArrJ[i2], (Spanned) charSequence, iA);
        }
    }

    public void b(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.setCanOpenPopup(z);
        }
    }

    public final int a(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                if (clickableSpan.equals(sparseArray.valueAt(i).get())) {
                    return sparseArray.keyAt(i);
                }
            }
        }
        int i2 = d;
        d = i2 + 1;
        return i2;
    }

    public final void a() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f9729a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f9729a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f9729a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    public final void a(ClickableSpan clickableSpan, Spanned spanned, int i) {
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9729a.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f9732a);
        }
    }

    public final void a(int i, boolean z) {
        Bundle bundleG = g();
        if (bundleG != null) {
            int i2 = bundleG.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i);
            if (!z) {
                i = 0;
            }
            bundleG.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }
}
