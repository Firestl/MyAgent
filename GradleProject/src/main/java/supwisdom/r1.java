package supwisdom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import supwisdom.c2;
import supwisdom.d2;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class r1 implements c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8995a;
    public Context b;
    public w1 c;
    public LayoutInflater d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c2.a f8996e;
    public int f;
    public int g;
    public d2 h;
    public int i;

    public r1(Context context, int i, int i2) {
        this.f8995a = context;
        this.d = LayoutInflater.from(context);
        this.f = i;
        this.g = i2;
    }

    @Override // supwisdom.c2
    public void a(Context context, w1 w1Var) {
        this.b = context;
        LayoutInflater.from(context);
        this.c = w1Var;
    }

    public abstract void a(y1 y1Var, d2.a aVar);

    public abstract boolean a(int i, y1 y1Var);

    @Override // supwisdom.c2
    public boolean a(w1 w1Var, y1 y1Var) {
        return false;
    }

    public d2 b(ViewGroup viewGroup) {
        if (this.h == null) {
            d2 d2Var = (d2) this.d.inflate(this.f, viewGroup, false);
            this.h = d2Var;
            d2Var.a(this.c);
            a(true);
        }
        return this.h;
    }

    @Override // supwisdom.c2
    public boolean b(w1 w1Var, y1 y1Var) {
        return false;
    }

    public c2.a c() {
        return this.f8996e;
    }

    @Override // supwisdom.c2
    public int getId() {
        return this.i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // supwisdom.c2
    public void a(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.h;
        if (viewGroup == null) {
            return;
        }
        w1 w1Var = this.c;
        int i = 0;
        if (w1Var != null) {
            w1Var.b();
            ArrayList<y1> arrayListN = this.c.n();
            int size = arrayListN.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                y1 y1Var = arrayListN.get(i3);
                if (a(i2, y1Var)) {
                    View childAt = viewGroup.getChildAt(i2);
                    y1 itemData = childAt instanceof d2.a ? ((d2.a) childAt).getItemData() : null;
                    View viewA = a(y1Var, childAt, viewGroup);
                    if (y1Var != itemData) {
                        viewA.setPressed(false);
                        viewA.jumpDrawablesToCurrentState();
                    }
                    if (viewA != childAt) {
                        a(viewA, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!a(viewGroup, i)) {
                i++;
            }
        }
    }

    public void a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.h).addView(view, i);
    }

    public boolean a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // supwisdom.c2
    public void a(c2.a aVar) {
        this.f8996e = aVar;
    }

    public d2.a a(ViewGroup viewGroup) {
        return (d2.a) this.d.inflate(this.g, viewGroup, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View a(y1 y1Var, View view, ViewGroup viewGroup) {
        d2.a aVarA;
        if (view instanceof d2.a) {
            aVarA = (d2.a) view;
        } else {
            aVarA = a(viewGroup);
        }
        a(y1Var, aVarA);
        return (View) aVarA;
    }

    @Override // supwisdom.c2
    public void a(w1 w1Var, boolean z) {
        c2.a aVar = this.f8996e;
        if (aVar != null) {
            aVar.a(w1Var, z);
        }
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
    @Override // supwisdom.c2
    public boolean a(h2 h2Var) {
        c2.a aVar = this.f8996e;
        w1 w1Var = h2Var;
        if (aVar == null) {
            return false;
        }
        if (h2Var == null) {
            w1Var = this.c;
        }
        return aVar.a(w1Var);
    }

    public void a(int i) {
        this.i = i;
    }
}
