package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import supwisdom.b3;
import supwisdom.f2;
import supwisdom.l2;
import supwisdom.lb;
import supwisdom.p3;
import supwisdom.pa;
import supwisdom.xb;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f1134a;
    public final g b;
    public final View c;
    public final Drawable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final FrameLayout f1135e;
    public final ImageView f;
    public final FrameLayout g;
    public final ImageView h;
    public final int i;
    public pa j;
    public final DataSetObserver k;
    public final ViewTreeObserver.OnGlobalLayoutListener l;
    public ListPopupWindow m;
    public PopupWindow.OnDismissListener n;
    public boolean o;
    public int p;
    public boolean q;
    public int r;

    public static class InnerLayout extends LinearLayout {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1136a = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            p3 p3VarA = p3.a(context, attributeSet, f1136a);
            setBackgroundDrawable(p3VarA.b(0));
            p3VarA.b();
        }
    }

    public class a extends DataSetObserver {
        public a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.f1134a.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f1134a.notifyDataSetInvalidated();
        }
    }

    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ActivityChooserView.this.b()) {
                if (!ActivityChooserView.this.isShown()) {
                    ActivityChooserView.this.getListPopupWindow().dismiss();
                    return;
                }
                ActivityChooserView.this.getListPopupWindow().show();
                pa paVar = ActivityChooserView.this.j;
                if (paVar != null) {
                    paVar.a(true);
                }
            }
        }
    }

    public class c extends View.AccessibilityDelegate {
        public c() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            xb.a(accessibilityNodeInfo).b(true);
        }
    }

    public class d extends b3 {
        public d(View view) {
            super(view);
        }

        @Override // supwisdom.b3
        public f2 b() {
            return ActivityChooserView.this.getListPopupWindow();
        }

        @Override // supwisdom.b3
        public boolean c() {
            ActivityChooserView.this.c();
            return true;
        }

        @Override // supwisdom.b3
        public boolean d() {
            ActivityChooserView.this.a();
            return true;
        }
    }

    public class e extends DataSetObserver {
        public e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.d();
        }
    }

    public class g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public g() {
        }

        public final void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.n;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.g) {
                if (view != activityChooserView.f1135e) {
                    throw new IllegalArgumentException();
                }
                activityChooserView.o = false;
                activityChooserView.a(activityChooserView.p);
                return;
            }
            activityChooserView.a();
            Intent intentA = ActivityChooserView.this.f1134a.b().a(ActivityChooserView.this.f1134a.b().a(ActivityChooserView.this.f1134a.c()));
            if (intentA != null) {
                intentA.addFlags(524288);
                ActivityChooserView.this.getContext().startActivity(intentA);
            }
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            pa paVar = ActivityChooserView.this.j;
            if (paVar != null) {
                paVar.a(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int itemViewType = ((f) adapterView.getAdapter()).getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.a(Integer.MAX_VALUE);
                return;
            }
            ActivityChooserView.this.a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (activityChooserView.o) {
                if (i > 0) {
                    activityChooserView.f1134a.b().c(i);
                    return;
                }
                return;
            }
            if (!activityChooserView.f1134a.e()) {
                i++;
            }
            Intent intentA = ActivityChooserView.this.f1134a.b().a(i);
            if (intentA != null) {
                intentA.addFlags(524288);
                ActivityChooserView.this.getContext().startActivity(intentA);
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.g) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f1134a.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.o = true;
                activityChooserView2.a(activityChooserView2.p);
            }
            return true;
        }
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [boolean, int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void a(int i) {
        if (this.f1134a.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.l);
        ?? r0 = this.g.getVisibility() == 0 ? 1 : 0;
        int iA = this.f1134a.a();
        if (i == Integer.MAX_VALUE || iA <= i + r0) {
            this.f1134a.a(false);
            this.f1134a.a(i);
        } else {
            this.f1134a.a(true);
            this.f1134a.a(i - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.isShowing()) {
            return;
        }
        if (this.o || r0 == 0) {
            this.f1134a.a(true, r0);
        } else {
            this.f1134a.a(false, false);
        }
        listPopupWindow.e(Math.min(this.f1134a.f(), this.i));
        listPopupWindow.show();
        pa paVar = this.j;
        if (paVar != null) {
            paVar.a(true);
        }
        listPopupWindow.d().setContentDescription(getContext().getString(androidx.appcompat.R.string.abc_activitychooserview_choose_application));
        listPopupWindow.d().setSelector(new ColorDrawable(0));
    }

    public boolean b() {
        return getListPopupWindow().isShowing();
    }

    public boolean c() {
        if (b() || !this.q) {
            return false;
        }
        this.o = false;
        a(this.p);
        return true;
    }

    public void d() {
        if (this.f1134a.getCount() > 0) {
            this.f1135e.setEnabled(true);
        } else {
            this.f1135e.setEnabled(false);
        }
        int iA = this.f1134a.a();
        int iD = this.f1134a.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.g.setVisibility(0);
            ResolveInfo resolveInfoC = this.f1134a.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.h.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.r != 0) {
                this.g.setContentDescription(getContext().getString(this.r, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.g.setVisibility(8);
        }
        if (this.g.getVisibility() == 0) {
            this.c.setBackgroundDrawable(this.d);
        } else {
            this.c.setBackgroundDrawable(null);
        }
    }

    public l2 getDataModel() {
        return this.f1134a.b();
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.m == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.m = listPopupWindow;
            listPopupWindow.a(this.f1134a);
            this.m.a(this);
            this.m.a(true);
            this.m.a((AdapterView.OnItemClickListener) this.b);
            this.m.a((PopupWindow.OnDismissListener) this.b);
        }
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        l2 l2VarB = this.f1134a.b();
        if (l2VarB != null) {
            l2VarB.registerObserver(this.k);
        }
        this.q = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        l2 l2VarB = this.f1134a.b();
        if (l2VarB != null) {
            l2VarB.unregisterObserver(this.k);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.l);
        }
        if (b()) {
            a();
        }
        this.q = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.c.layout(0, 0, i3 - i, i4 - i2);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        View view = this.c;
        if (this.g.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 1073741824);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(l2 l2Var) {
        this.f1134a.a(l2Var);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.r = i;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.f.setContentDescription(getContext().getString(i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i) {
        this.p = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    public void setProvider(pa paVar) {
        this.j = paVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new a();
        this.l = new b();
        this.p = 4;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.appcompat.R.styleable.ActivityChooserView, i, 0);
        lb.a(this, context, androidx.appcompat.R.styleable.ActivityChooserView, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        this.p = typedArrayObtainStyledAttributes.getInt(androidx.appcompat.R.styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(androidx.appcompat.R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(androidx.appcompat.R.layout.abc_activity_chooser_view, (ViewGroup) this, true);
        this.b = new g();
        View viewFindViewById = findViewById(androidx.appcompat.R.id.activity_chooser_view_content);
        this.c = viewFindViewById;
        this.d = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(androidx.appcompat.R.id.default_activity_button);
        this.g = frameLayout;
        frameLayout.setOnClickListener(this.b);
        this.g.setOnLongClickListener(this.b);
        this.h = (ImageView) this.g.findViewById(androidx.appcompat.R.id.image);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(androidx.appcompat.R.id.expand_activities_button);
        frameLayout2.setOnClickListener(this.b);
        frameLayout2.setAccessibilityDelegate(new c());
        frameLayout2.setOnTouchListener(new d(frameLayout2));
        this.f1135e = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(androidx.appcompat.R.id.image);
        this.f = imageView;
        imageView.setImageDrawable(drawable);
        f fVar = new f();
        this.f1134a = fVar;
        fVar.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.i = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(androidx.appcompat.R.dimen.abc_config_prefDialogWidth));
    }

    public class f extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public l2 f1141a;
        public int b = 4;
        public boolean c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1142e;

        public f() {
        }

        public void a(l2 l2Var) {
            l2 l2VarB = ActivityChooserView.this.f1134a.b();
            if (l2VarB != null && ActivityChooserView.this.isShown()) {
                l2VarB.unregisterObserver(ActivityChooserView.this.k);
            }
            this.f1141a = l2Var;
            if (l2Var != null && ActivityChooserView.this.isShown()) {
                l2Var.registerObserver(ActivityChooserView.this.k);
            }
            notifyDataSetChanged();
        }

        public l2 b() {
            return this.f1141a;
        }

        public ResolveInfo c() {
            return this.f1141a.c();
        }

        public int d() {
            return this.f1141a.d();
        }

        public boolean e() {
            return this.c;
        }

        public int f() {
            int i = this.b;
            this.b = Integer.MAX_VALUE;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            View view = null;
            int iMax = 0;
            for (int i2 = 0; i2 < count; i2++) {
                view = getView(i2, view, null);
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredWidth());
            }
            this.b = i;
            return iMax;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int iB = this.f1141a.b();
            if (!this.c && this.f1141a.c() != null) {
                iB--;
            }
            int iMin = Math.min(iB, this.b);
            return this.f1142e ? iMin + 1 : iMin;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (!this.c && this.f1141a.c() != null) {
                i++;
            }
            return this.f1141a.b(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return (this.f1142e && i == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(androidx.appcompat.R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(androidx.appcompat.R.id.title)).setText(ActivityChooserView.this.getContext().getString(androidx.appcompat.R.string.abc_activity_chooser_view_see_all));
                return viewInflate;
            }
            if (view == null || view.getId() != androidx.appcompat.R.id.list_item) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(androidx.appcompat.R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(androidx.appcompat.R.id.icon);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(androidx.appcompat.R.id.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.c && i == 0 && this.d) {
                view.setActivated(true);
            } else {
                view.setActivated(false);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }

        public void a(int i) {
            if (this.b != i) {
                this.b = i;
                notifyDataSetChanged();
            }
        }

        public void a(boolean z) {
            if (this.f1142e != z) {
                this.f1142e = z;
                notifyDataSetChanged();
            }
        }

        public int a() {
            return this.f1141a.b();
        }

        public void a(boolean z, boolean z2) {
            if (this.c == z && this.d == z2) {
                return;
            }
            this.c = z;
            this.d = z2;
            notifyDataSetChanged();
        }
    }

    public boolean a() {
        if (!b()) {
            return true;
        }
        getListPopupWindow().dismiss();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(this.l);
        return true;
    }
}
