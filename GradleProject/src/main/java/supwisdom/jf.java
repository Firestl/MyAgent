package supwisdom;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
public class jf {
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8056e;
    public boolean h;
    public boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8055a = true;
    public int f = 0;
    public int g = 0;

    public boolean a(RecyclerView.y yVar) {
        int i = this.c;
        return i >= 0 && i < yVar.a();
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.b + ", mCurrentPosition=" + this.c + ", mItemDirection=" + this.d + ", mLayoutDirection=" + this.f8056e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + Operators.BLOCK_END;
    }

    public View a(RecyclerView.u uVar) {
        View viewD = uVar.d(this.c);
        this.c += this.d;
        return viewD;
    }
}
