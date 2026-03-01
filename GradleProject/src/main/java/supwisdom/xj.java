package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.list.WXListComponent;
import com.taobao.weex.ui.view.WXHorizontalScrollView;
import com.taobao.weex.ui.view.WXScrollView;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.refresh.core.WXSwipeLayout;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import supwisdom.si;

/* JADX INFO: compiled from: BindingXScrollHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class xj extends bj {
    public static HashMap<String, b> u = new HashMap<>();
    public RecyclerView.s o;
    public WXSwipeLayout.OnRefreshOffsetChangedListener p;
    public WXScrollView.WXScrollViewListener q;
    public WXHorizontalScrollView.ScrollViewListener r;
    public AppBarLayout.c s;
    public String t;

    /* JADX INFO: compiled from: BindingXScrollHandler.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9769a;
        public int b;

        public b(int i, int i2) {
            this.f9769a = i;
            this.b = i2;
        }
    }

    /* JADX INFO: compiled from: BindingXScrollHandler.java */
    public class c implements AppBarLayout.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9770a;
        public int b;
        public int c;

        /* JADX INFO: compiled from: BindingXScrollHandler.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f9771a;
            public final /* synthetic */ int b;

            public a(int i, int i2) {
                this.f9771a = i;
                this.b = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                xj.super.a(0, cVar.f9770a, 0, this.f9771a, 0, this.b);
            }
        }

        public c() {
            this.f9770a = 0;
            this.b = 0;
            this.c = 0;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.b
        public void a(AppBarLayout appBarLayout, int i) {
            boolean z;
            int i2 = -i;
            int i3 = i2 - this.f9770a;
            this.f9770a = i2;
            if (i3 == 0) {
                return;
            }
            if (xj.this.a(i3, this.c)) {
                z = false;
            } else {
                this.b = this.f9770a;
                z = true;
            }
            int i4 = this.f9770a;
            int i5 = i4 - this.b;
            this.c = i3;
            if (z) {
                xj.super.a("turn", 0.0d, i4, 0.0d, i3, 0.0d, i5, new Object[0]);
            }
            WXBridgeManager.getInstance().post(new a(i3, i5), xj.this.f6928e);
        }
    }

    /* JADX INFO: compiled from: BindingXScrollHandler.java */
    public class d extends RecyclerView.s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9772a;
        public int b;
        public int c = 0;
        public int d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9773e = 0;
        public int f = 0;
        public boolean g;
        public WeakReference<WXListComponent> h;

        /* JADX INFO: compiled from: BindingXScrollHandler.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f9774a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;
            public final /* synthetic */ int d;

            public a(int i, int i2, int i3, int i4) {
                this.f9774a = i;
                this.b = i2;
                this.c = i3;
                this.d = i4;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = d.this;
                xj.super.a(dVar.f9772a, d.this.b, this.f9774a, this.b, this.c, this.d);
            }
        }

        public d(boolean z, WeakReference<WXListComponent> weakReference) {
            b bVar;
            this.f9772a = 0;
            this.b = 0;
            this.g = z;
            this.h = weakReference;
            if (TextUtils.isEmpty(xj.this.t) || xj.u == null || (bVar = (b) xj.u.get(xj.this.t)) == null) {
                return;
            }
            this.f9772a = bVar.f9769a;
            this.b = bVar.b;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.s
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            boolean z;
            WeakReference<WXListComponent> weakReference;
            if (!lb.L(recyclerView) || (weakReference = this.h) == null || weakReference.get() == null) {
                this.b += i2;
            } else {
                this.b = Math.abs(this.h.get().calcContentOffset(recyclerView));
            }
            this.f9772a += i;
            boolean z2 = true;
            if (xj.this.a(i, this.f9773e) || this.g) {
                z = false;
            } else {
                this.c = this.f9772a;
                z = true;
            }
            if (xj.this.a(i2, this.f) || !this.g) {
                z2 = z;
            } else {
                this.d = this.b;
            }
            int i3 = this.f9772a;
            int i4 = i3 - this.c;
            int i5 = this.b;
            int i6 = i5 - this.d;
            this.f9773e = i;
            this.f = i2;
            if (z2) {
                xj.this.a("turn", i3, i5, i, i2, i4, i6, new Object[0]);
            }
            WXBridgeManager.getInstance().post(new a(i, i2, i4, i6), xj.this.f6928e);
        }
    }

    /* JADX INFO: compiled from: BindingXScrollHandler.java */
    public class e implements WXScrollView.WXScrollViewListener, WXHorizontalScrollView.ScrollViewListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9776a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9777e;

        /* JADX INFO: compiled from: BindingXScrollHandler.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f9778a;
            public final /* synthetic */ int b;
            public final /* synthetic */ int c;
            public final /* synthetic */ int d;

            public a(int i, int i2, int i3, int i4) {
                this.f9778a = i;
                this.b = i2;
                this.c = i3;
                this.d = i4;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                xj.super.a(eVar.f9776a, e.this.b, this.f9778a, this.b, this.c, this.d);
            }
        }

        public e() {
            this.f9776a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.f9777e = 0;
        }

        @Override // com.taobao.weex.ui.view.WXScrollView.WXScrollViewListener
        public void onScroll(WXScrollView wXScrollView, int i, int i2) {
            a(i, i2);
        }

        @Override // com.taobao.weex.ui.view.WXHorizontalScrollView.ScrollViewListener
        public void onScrollChanged(WXHorizontalScrollView wXHorizontalScrollView, int i, int i2, int i3, int i4) {
            a(i, i2);
        }

        @Override // com.taobao.weex.ui.view.WXScrollView.WXScrollViewListener
        public void onScrollChanged(WXScrollView wXScrollView, int i, int i2, int i3, int i4) {
        }

        @Override // com.taobao.weex.ui.view.WXScrollView.WXScrollViewListener
        public void onScrollStopped(WXScrollView wXScrollView, int i, int i2) {
        }

        @Override // com.taobao.weex.ui.view.WXScrollView.WXScrollViewListener
        public void onScrollToBottom(WXScrollView wXScrollView, int i, int i2) {
        }

        public final void a(int i, int i2) {
            boolean z;
            int i3;
            int i4;
            int i5 = i - this.f9776a;
            int i6 = i2 - this.b;
            this.f9776a = i;
            this.b = i2;
            if (i5 == 0 && i6 == 0) {
                return;
            }
            if (xj.this.a(i6, this.f9777e)) {
                z = false;
            } else {
                this.d = this.b;
                z = true;
            }
            int i7 = this.f9776a;
            int i8 = i7 - this.c;
            int i9 = this.b;
            int i10 = i9 - this.d;
            this.f9777e = i6;
            if (z) {
                i4 = i6;
                i3 = i5;
                xj.super.a("turn", i7, i9, i5, i6, i8, i10, new Object[0]);
            } else {
                i3 = i5;
                i4 = i6;
            }
            WXBridgeManager.getInstance().post(new a(i3, i4, i8, i10), xj.this.f6928e);
        }
    }

    /* JADX INFO: compiled from: BindingXScrollHandler.java */
    public class f implements WXSwipeLayout.OnRefreshOffsetChangedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9780a;
        public int b;
        public int c;

        /* JADX INFO: compiled from: BindingXScrollHandler.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f9781a;
            public final /* synthetic */ int b;

            public a(int i, int i2) {
                this.f9781a = i;
                this.b = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                xj xjVar = xj.this;
                xj.super.a(xjVar.l, f.this.f9780a, 0, this.f9781a, 0, this.b);
            }
        }

        public f() {
            this.f9780a = 0;
            this.b = 0;
            this.c = 0;
        }

        @Override // com.taobao.weex.ui.view.refresh.core.WXSwipeLayout.OnRefreshOffsetChangedListener
        public void onOffsetChanged(int i) {
            boolean z;
            int i2 = -i;
            int i3 = i2 - this.f9780a;
            this.f9780a = i2;
            if (i3 == 0) {
                return;
            }
            if (xj.this.a(i3, this.c)) {
                z = false;
            } else {
                this.b = this.f9780a;
                z = true;
            }
            int i4 = this.f9780a - this.b;
            this.c = i3;
            if (z) {
                xj.super.a("turn", r5.l, this.f9780a, 0.0d, i3, 0.0d, i4, new Object[0]);
            }
            WXBridgeManager.getInstance().post(new a(i3, i4), xj.this.f6928e);
        }
    }

    public xj(Context context, yi yiVar, Object... objArr) {
        super(context, yiVar, objArr);
    }

    public final boolean a(int i, int i2) {
        return (i > 0 && i2 > 0) || (i < 0 && i2 < 0);
    }

    @Override // supwisdom.vi
    public void c(String str, String str2) {
    }

    @Override // supwisdom.vi
    public void onActivityPause() {
    }

    @Override // supwisdom.vi
    public void onActivityResume() {
    }

    @Override // supwisdom.bj, supwisdom.aj, supwisdom.vi
    public void onDestroy() {
        super.onDestroy();
        this.o = null;
        this.q = null;
        this.s = null;
        HashMap<String, b> map = u;
        if (map != null) {
            map.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // supwisdom.vi
    public boolean b(String str, String str2) {
        WXSwipeLayout swipeLayout;
        WXComponent wXComponentA = zj.a(TextUtils.isEmpty(this.f) ? this.f6928e : this.f, str);
        if (wXComponentA == null) {
            xi.b("[ExpressionScrollHandler]source component not found.");
            return false;
        }
        this.t = str;
        if (wXComponentA instanceof WXScroller) {
            WXScroller wXScroller = (WXScroller) wXComponentA;
            ViewGroup viewGroup = (ViewGroup) wXScroller.getHostView();
            if (viewGroup != null && (viewGroup instanceof BounceScrollerView) && (swipeLayout = ((BounceScrollerView) viewGroup).getSwipeLayout()) != null) {
                f fVar = new f();
                this.p = fVar;
                swipeLayout.addOnRefreshOffsetChangedListener(fVar);
            }
            ViewGroup innerView = wXScroller.getInnerView();
            if (innerView != null && (innerView instanceof WXScrollView)) {
                e eVar = new e();
                this.q = eVar;
                ((WXScrollView) innerView).addScrollViewListener(eVar);
                return true;
            }
            if (innerView != null && (innerView instanceof WXHorizontalScrollView)) {
                e eVar2 = new e();
                this.r = eVar2;
                ((WXHorizontalScrollView) innerView).addScrollViewListener(eVar2);
                return true;
            }
        } else if (wXComponentA instanceof WXListComponent) {
            WXListComponent wXListComponent = (WXListComponent) wXComponentA;
            BounceRecyclerView bounceRecyclerView = (BounceRecyclerView) wXListComponent.getHostView();
            if (bounceRecyclerView != null) {
                WXSwipeLayout swipeLayout2 = bounceRecyclerView.getSwipeLayout();
                if (swipeLayout2 != null) {
                    f fVar2 = new f();
                    this.p = fVar2;
                    swipeLayout2.addOnRefreshOffsetChangedListener(fVar2);
                }
                WXRecyclerView innerView2 = bounceRecyclerView.getInnerView();
                boolean z = wXListComponent.getOrientation() == 1;
                if (innerView2 != null) {
                    HashMap<String, b> map = u;
                    if (map != null && map.get(str) == null) {
                        u.put(str, new b(0, 0));
                    }
                    d dVar = new d(z, new WeakReference(wXListComponent));
                    this.o = dVar;
                    innerView2.addOnScrollListener(dVar);
                    return true;
                }
            }
        } else if (wXComponentA.getHostView() != null && (wXComponentA.getHostView() instanceof AppBarLayout)) {
            AppBarLayout appBarLayout = (AppBarLayout) wXComponentA.getHostView();
            c cVar = new c();
            this.s = cVar;
            appBarLayout.a((AppBarLayout.c) cVar);
            return true;
        }
        return false;
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void a(String str, Map<String, Object> map, jj jjVar, List<Map<String, Object>> list, si.d dVar) {
        super.a(str, map, jjVar, list, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // supwisdom.bj, supwisdom.vi
    public boolean a(String str, String str2) {
        BounceRecyclerView bounceRecyclerView;
        RecyclerView.s sVar;
        WXHorizontalScrollView.ScrollViewListener scrollViewListener;
        WXScrollView.WXScrollViewListener wXScrollViewListener;
        WXSwipeLayout swipeLayout;
        WXSwipeLayout.OnRefreshOffsetChangedListener onRefreshOffsetChangedListener;
        b bVar;
        super.a(str, str2);
        if (u != null && !TextUtils.isEmpty(this.t) && (bVar = u.get(this.t)) != null) {
            bVar.f9769a = this.l;
            bVar.b = this.m;
        }
        WXComponent wXComponentA = zj.a(TextUtils.isEmpty(this.f) ? this.f6928e : this.f, str);
        if (wXComponentA == null) {
            xi.b("[ExpressionScrollHandler]source component not found.");
            return false;
        }
        if (wXComponentA instanceof WXScroller) {
            WXScroller wXScroller = (WXScroller) wXComponentA;
            ViewGroup viewGroup = (ViewGroup) wXScroller.getHostView();
            if (viewGroup != null && (viewGroup instanceof BounceScrollerView) && (swipeLayout = ((BounceScrollerView) viewGroup).getSwipeLayout()) != null && (onRefreshOffsetChangedListener = this.p) != null) {
                swipeLayout.removeOnRefreshOffsetChangedListener(onRefreshOffsetChangedListener);
            }
            ViewGroup innerView = wXScroller.getInnerView();
            if (innerView != null && (innerView instanceof WXScrollView) && (wXScrollViewListener = this.q) != null) {
                ((WXScrollView) innerView).removeScrollViewListener(wXScrollViewListener);
                return true;
            }
            if (innerView != null && (innerView instanceof WXHorizontalScrollView) && (scrollViewListener = this.r) != null) {
                ((WXHorizontalScrollView) innerView).removeScrollViewListener(scrollViewListener);
                return true;
            }
        } else if ((wXComponentA instanceof WXListComponent) && (bounceRecyclerView = (BounceRecyclerView) ((WXListComponent) wXComponentA).getHostView()) != null) {
            if (bounceRecyclerView.getSwipeLayout() != null && this.p != null) {
                bounceRecyclerView.getSwipeLayout().removeOnRefreshOffsetChangedListener(this.p);
            }
            WXRecyclerView innerView2 = bounceRecyclerView.getInnerView();
            if (innerView2 != null && (sVar = this.o) != null) {
                innerView2.removeOnScrollListener(sVar);
                return true;
            }
        }
        return false;
    }
}
