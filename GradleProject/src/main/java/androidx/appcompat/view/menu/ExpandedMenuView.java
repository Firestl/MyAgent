package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import supwisdom.d2;
import supwisdom.p3;
import supwisdom.w1;
import supwisdom.y1;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements w1.b, d2, AdapterView.OnItemClickListener {
    public static final int[] c = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w1 f1117a;
    public int b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // supwisdom.d2
    public void a(w1 w1Var) {
        this.f1117a = w1Var;
    }

    public int getWindowAnimations() {
        return this.b;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((y1) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        p3 p3VarA = p3.a(context, attributeSet, c, i, 0);
        if (p3VarA.g(0)) {
            setBackgroundDrawable(p3VarA.b(0));
        }
        if (p3VarA.g(1)) {
            setDivider(p3VarA.b(1));
        }
        p3VarA.b();
    }

    @Override // supwisdom.w1.b
    public boolean a(y1 y1Var) {
        return this.f1117a.a(y1Var, 0);
    }
}
