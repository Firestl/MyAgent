package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
import io.dcloud.common.util.JSUtil;
import java.util.Arrays;
import java.util.HashMap;
import supwisdom.e7;
import supwisdom.f6;
import supwisdom.i6;
import supwisdom.j6;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f1243a;
    public int b;
    public Context c;
    public i6 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1244e;
    public String f;
    public String g;
    public View[] h;
    public HashMap<Integer, String> i;

    public ConstraintHelper(Context context) {
        super(context);
        this.f1243a = new int[32];
        this.f1244e = false;
        this.h = null;
        this.i = new HashMap<>();
        this.c = context;
        a((AttributeSet) null);
    }

    public void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f = string;
                    setIds(string);
                } else if (index == R.styleable.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.g = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void a(ConstraintWidget constraintWidget, boolean z) {
    }

    public void b() {
        if (this.d == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).m0 = (ConstraintWidget) this.d;
        }
    }

    public final int c(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int iA = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object objA = constraintLayout.a(0, str);
            if (objA instanceof Integer) {
                iA = ((Integer) objA).intValue();
            }
        }
        if (iA == 0 && constraintLayout != null) {
            iA = a(constraintLayout, str);
        }
        if (iA == 0) {
            try {
                iA = R.id.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iA == 0 ? this.c.getResources().getIdentifier(str, "id", this.c.getPackageName()) : iA;
    }

    public void c(ConstraintLayout constraintLayout) {
    }

    public void d(ConstraintLayout constraintLayout) {
    }

    public void e(ConstraintLayout constraintLayout) {
    }

    public void f(ConstraintLayout constraintLayout) {
        String str;
        int iA;
        if (isInEditMode()) {
            setIds(this.f);
        }
        i6 i6Var = this.d;
        if (i6Var == null) {
            return;
        }
        i6Var.a();
        for (int i = 0; i < this.b; i++) {
            int i2 = this.f1243a[i];
            View viewB = constraintLayout.b(i2);
            if (viewB == null && (iA = a(constraintLayout, (str = this.i.get(Integer.valueOf(i2))))) != 0) {
                this.f1243a[i] = iA;
                this.i.put(Integer.valueOf(iA), str);
                viewB = constraintLayout.b(iA);
            }
            if (viewB != null) {
                this.d.a(constraintLayout.a(viewB));
            }
        }
        this.d.a(constraintLayout.c);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1243a, this.b);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.g;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f1244e) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void setIds(String str) {
        this.f = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.b = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                a(str.substring(i));
                return;
            } else {
                a(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferenceTags(String str) {
        this.g = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.b = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                b(str.substring(i));
                return;
            } else {
                b(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f = null;
        this.b = 0;
        for (int i : iArr) {
            a(i);
        }
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.f == null) {
            a(i);
        }
    }

    public final void b(String str) {
        if (str == null || str.length() == 0 || this.c == null) {
            return;
        }
        String strTrim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof ConstraintLayout.LayoutParams) && strTrim.equals(((ConstraintLayout.LayoutParams) layoutParams).U)) {
                if (childAt.getId() == -1) {
                    Log.w("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    a(childAt.getId());
                }
            }
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1243a = new int[32];
        this.f1244e = false;
        this.h = null;
        this.i = new HashMap<>();
        this.c = context;
        a(attributeSet);
    }

    public final void a(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.b + 1;
        int[] iArr = this.f1243a;
        if (i2 > iArr.length) {
            this.f1243a = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1243a;
        int i3 = this.b;
        iArr2[i3] = i;
        this.b = i3 + 1;
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1243a = new int[32];
        this.f1244e = false;
        this.h = null;
        this.i = new HashMap<>();
        this.c = context;
        a(attributeSet);
    }

    public final void a(String str) {
        if (str == null || str.length() == 0 || this.c == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iC = c(strTrim);
        if (iC != 0) {
            this.i.put(Integer.valueOf(iC), strTrim);
            a(iC);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + JSUtil.QUOTE);
    }

    public View[] b(ConstraintLayout constraintLayout) {
        View[] viewArr = this.h;
        if (viewArr == null || viewArr.length != this.b) {
            this.h = new View[this.b];
        }
        for (int i = 0; i < this.b; i++) {
            this.h[i] = constraintLayout.b(this.f1243a[i]);
        }
        return this.h;
    }

    public final int a(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.c.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            if (childAt.getId() != -1) {
                String resourceEntryName = null;
                try {
                    resourceEntryName = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                }
                if (str.equals(resourceEntryName)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    public void a(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.b; i++) {
            View viewB = constraintLayout.b(this.f1243a[i]);
            if (viewB != null) {
                viewB.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewB.setTranslationZ(viewB.getTranslationZ() + elevation);
                }
            }
        }
    }

    public void a() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        a((ConstraintLayout) parent);
    }

    public void a(f6 f6Var, i6 i6Var, SparseArray<ConstraintWidget> sparseArray) {
        i6Var.a();
        for (int i = 0; i < this.b; i++) {
            i6Var.a(sparseArray.get(this.f1243a[i]));
        }
    }

    public void a(e7.a aVar, j6 j6Var, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        e7.b bVar = aVar.d;
        int[] iArr = bVar.e0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = bVar.f0;
            if (str != null && str.length() > 0) {
                e7.b bVar2 = aVar.d;
                bVar2.e0 = a(this, bVar2.f0);
            }
        }
        j6Var.a();
        if (aVar.d.e0 == null) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr2 = aVar.d.e0;
            if (i >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i]);
            if (constraintWidget != null) {
                j6Var.a(constraintWidget);
            }
            i++;
        }
    }

    public final int[] a(View view, String str) {
        String[] strArrSplit = str.split(",");
        view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        for (String str2 : strArrSplit) {
            int iC = c(str2.trim());
            if (iC != 0) {
                iArr[i] = iC;
                i++;
            }
        }
        return i != strArrSplit.length ? Arrays.copyOf(iArr, i) : iArr;
    }
}
