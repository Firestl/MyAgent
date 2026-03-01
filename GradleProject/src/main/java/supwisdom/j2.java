package supwisdom;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: compiled from: AbsActionBarView.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j2 extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f8008a;
    public final Context b;
    public ActionMenuView c;
    public ActionMenuPresenter d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8009e;
    public pb f;
    public boolean g;
    public boolean h;

    public j2(Context context) {
        this(context, null);
    }

    public static int a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    public int getAnimatedVisibility() {
        return this.f != null ? this.f8008a.b : getVisibility();
    }

    public int getContentHeight() {
        return this.f8009e;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.a(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            pb pbVar = this.f;
            if (pbVar != null) {
                pbVar.a();
            }
            super.setVisibility(i);
        }
    }

    /* JADX INFO: compiled from: AbsActionBarView.java */
    public class a implements qb {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8010a = false;
        public int b;

        public a() {
        }

        public a a(pb pbVar, int i) {
            j2.this.f = pbVar;
            this.b = i;
            return this;
        }

        @Override // supwisdom.qb
        public void b(View view) {
            if (this.f8010a) {
                return;
            }
            j2 j2Var = j2.this;
            j2Var.f = null;
            j2.super.setVisibility(this.b);
        }

        @Override // supwisdom.qb
        public void c(View view) {
            j2.super.setVisibility(0);
            this.f8010a = false;
        }

        @Override // supwisdom.qb
        public void a(View view) {
            this.f8010a = true;
        }
    }

    public j2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

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
    public pb a(int i, long j) {
        pb pbVar = this.f;
        if (pbVar != null) {
            pbVar.a();
        }
        if (i != 0) {
            pb pbVarA = lb.a(this);
            pbVarA.a(0.0f);
            pbVarA.a(j);
            a aVar = this.f8008a;
            aVar.a(pbVarA, i);
            pbVarA.a(aVar);
            return pbVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        pb pbVarA2 = lb.a(this);
        pbVarA2.a(1.0f);
        pbVarA2.a(j);
        a aVar2 = this.f8008a;
        aVar2.a(pbVarA2, i);
        pbVarA2.a(aVar2);
        return pbVarA2;
    }

    public j2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8008a = new a();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.b = new ContextThemeWrapper(context, typedValue.resourceId);
        } else {
            this.b = context;
        }
    }

    public int a(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    public int a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }
}
