package io.dcloud.common.core.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.taobao.weex.ui.view.gesture.WXGesture;
import io.dcloud.PdrR;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.DHImageView;
import io.dcloud.common.adapter.ui.FrameSwitchView;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.core.ui.j;
import io.dcloud.common.util.BaseInfo;
import java.util.ArrayList;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import supwisdom.ku;

/* JADX INFO: loaded from: classes2.dex */
public class k extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6415a;
    public float b;
    public boolean c;
    public View d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f6416e;
    public float f;
    public b g;
    public Drawable h;
    public float i;
    public Rect j;
    public DHImageView k;
    public DHImageView l;
    public io.dcloud.common.core.ui.a m;
    public io.dcloud.common.core.ui.b n;
    public io.dcloud.common.core.ui.b o;
    public int p;
    public int q;
    public boolean r;

    public interface b {
        void a();

        void a(int i);

        void a(int i, float f);
    }

    public k(Context context, io.dcloud.common.core.ui.a aVar) {
        super(context);
        this.b = 0.3f;
        this.c = true;
        this.j = new Rect();
        this.m = null;
        this.p = 1;
        this.r = false;
        float f = getResources().getDisplayMetrics().density * 400.0f;
        this.m = aVar;
        j jVar = new j(this, new c(), aVar);
        this.f6416e = jVar;
        jVar.b(f);
        setEdgeTrackingEnabled(1);
        this.k = new DHImageView(context);
        this.l = new DHImageView(context);
        addView(this.k, new FrameLayout.LayoutParams(-1, -1));
        addView(this.l, new FrameLayout.LayoutParams(-1, -1));
        a(PdrR.DRAWEBL_SHADOW_LEFT, 1);
        this.l.setVisibility(8);
        this.k.setVisibility(8);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.i = 1.0f - this.f;
        if (this.f6416e.a(true)) {
            ku.c(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean z = view == this.d;
        boolean zDrawChild = super.drawChild(canvas, view, j);
        if (this.i > 0.0f && z && this.f6416e.c() != 0) {
            a(canvas, view);
        }
        return zDrawChild;
    }

    public DHImageView getLeftImageView() {
        return this.k;
    }

    public DHImageView getRightImageView() {
        return this.l;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.c) {
            return false;
        }
        try {
            return this.f6416e.c(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.c) {
            return false;
        }
        io.dcloud.common.core.ui.b bVar = this.n;
        if (bVar == null || bVar.obtainMainView() == null) {
            return true;
        }
        try {
            this.f6416e.a(motionEvent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        j jVar = this.f6416e;
        if (jVar == null) {
            super.requestLayout();
        } else if (jVar.c() == 0) {
            super.requestLayout();
        }
    }

    public void setEdgeSize(int i) {
        this.f6416e.e(i);
    }

    public void setEdgeTrackingEnabled(int i) {
        this.f6415a = i;
        this.f6416e.f(i);
    }

    public void setEnableGesture(boolean z) {
        this.c = z;
    }

    public void setScrollThresHold(float f) {
        if (f >= 1.0f || f <= 0.0f) {
            throw new IllegalArgumentException("Threshold value should be between 0 and 1.0");
        }
        this.b = f;
    }

    public void setSwipeListener(b bVar) {
        this.g = bVar;
    }

    public void c() {
        DHImageView dHImageView = this.k;
        if (dHImageView != null && dHImageView.getParent() == null) {
            addView(this.k, new FrameLayout.LayoutParams(-1, -1));
        }
        DHImageView dHImageView2 = this.l;
        if (dHImageView2 == null || dHImageView2.getParent() != null) {
            return;
        }
        addView(this.l, new FrameLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(11)
    public void b() {
        io.dcloud.common.core.ui.b bVar = this.o;
        if (bVar != null) {
            bVar.obtainMainView().setTranslationX(0.0f);
        }
        io.dcloud.common.core.ui.b bVar2 = this.n;
        if (bVar2 != null) {
            bVar2.obtainMainView().setLeft(0);
            this.n.obtainMainView().setTranslationX(0.0f);
        }
    }

    public void a(Drawable drawable, int i) {
        if ((i & 1) != 0) {
            this.h = drawable;
        }
        invalidate();
    }

    public void a(int i, int i2) {
        a(getResources().getDrawable(i), i2);
    }

    private void a(Canvas canvas, View view) {
        Rect rect = this.j;
        view.getHitRect(rect);
        if ((this.f6415a & 1) != 0) {
            Drawable drawable = this.h;
            drawable.setBounds((int) (((double) rect.left) - (((double) drawable.getIntrinsicWidth()) * 0.6d)), rect.top, rect.left, rect.bottom);
            this.h.setAlpha((int) (this.i * 190.0f));
            this.h.draw(canvas);
        }
    }

    public class c extends j.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6417a;

        public c() {
        }

        @Override // io.dcloud.common.core.ui.j.c
        public int a(View view) {
            return 1;
        }

        @Override // io.dcloud.common.core.ui.j.c
        public void a(View view, int i, int i2, int i3, int i4) {
            super.a(view, i, i2, i3, i4);
            if (k.this.p == 1) {
                return;
            }
            if ((k.this.q & 1) != 0) {
                k.this.f = Math.abs(i / (r1.d.getWidth() + k.this.h.getIntrinsicWidth()));
            }
            k.this.invalidate();
            if (k.this.f < k.this.b && !this.f6417a) {
                this.f6417a = true;
            }
            if (k.this.g != null && k.this.f6416e.c() == 1 && k.this.f >= k.this.b && this.f6417a) {
                this.f6417a = false;
                k.this.g.a();
            }
            k kVar = k.this;
            kVar.a(kVar.o);
        }

        @Override // io.dcloud.common.core.ui.j.c
        public int b(View view) {
            return 1;
        }

        @Override // io.dcloud.common.core.ui.j.c
        public boolean b(View view, int i) {
            k.this.d = view;
            if (k.this.f6416e.d(k.this.f6415a, i)) {
                if (k.this.f6416e.d(1, i)) {
                    k.this.q = 1;
                }
                if (k.this.g != null) {
                    k.this.g.a(k.this.q);
                }
                this.f6417a = true;
            }
            return true;
        }

        @Override // io.dcloud.common.core.ui.j.c
        public void c(int i) {
            super.c(i);
            if (k.this.g != null) {
                k.this.g.a(i, k.this.f);
            }
            if (i == 0) {
                k kVar = k.this;
                kVar.a(kVar.n, WXGesture.END, Boolean.valueOf(k.this.f >= 1.0f));
                k.this.requestLayout();
                k.this.b();
                if (k.this.f >= 1.0f) {
                    if (k.this.o != null) {
                        k.this.o.setSlipping(false);
                    }
                    if (k.this.n != null) {
                        k.this.n.setSlipping(false);
                    }
                    k.this.a();
                } else if (k.this.p == 1 && k.this.f == 0.0f && k.this.n != null && k.this.n.obtainWebView().canGoBack()) {
                    k.this.n.obtainWebView().goBackOrForward(-1);
                }
                k.this.n = null;
                k.this.o = null;
            }
        }

        @Override // io.dcloud.common.core.ui.j.c
        public void a(View view, float f, float f2) {
            if (k.this.p == 1) {
                return;
            }
            k.this.f6416e.e(((k.this.q & 1) == 0 || (f <= 0.0f && (f != 0.0f || k.this.f <= k.this.b))) ? 0 : view.getWidth() + k.this.h.getIntrinsicWidth() + 10, 0);
            k.this.invalidate();
        }

        @Override // io.dcloud.common.core.ui.j.c
        public int a(View view, int i, int i2) {
            if (k.this.p == 1) {
                return 0;
            }
            return Math.min(view.getWidth(), Math.max(i, 0));
        }

        @Override // io.dcloud.common.core.ui.j.c
        public boolean a(io.dcloud.common.core.ui.b bVar) {
            int i;
            int i2;
            if (bVar == null || 2 == bVar.getFrameType() || BaseInfo.sDoingAnimation || !k.this.r) {
                return false;
            }
            Rect rect = new Rect();
            bVar.obtainWebView().obtainWindowView().getGlobalVisibleRect(rect);
            ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
            k.this.n = bVar;
            int height = k.this.getHeight();
            if (bVar != null && bVar.obtainApp() != null && bVar.obtainApp().obtainStatusBarMgr() != null && bVar.obtainApp().obtainStatusBarMgr().isHandledWhiteScreen) {
                height--;
            }
            if (rect.left != 0 || (((i = viewOptionsObtainFrameOptions.width) != -1 && i < k.this.getWidth()) || (((i2 = viewOptionsObtainFrameOptions.height) != -1 && i2 < height) || bVar.obtainFrameOptions().popGesture.equals("none")))) {
                return false;
            }
            if ((bVar.obtainFrameOptions().historyBack.equals(ProviderConfigurationPermission.ALL_STR) || bVar.obtainFrameOptions().historyBack.equals("popGesture")) && bVar.obtainWebView() != null && bVar.obtainWebView().canGoBack()) {
                k.this.p = 1;
                return true;
            }
            k.this.c();
            ArrayList<io.dcloud.common.core.ui.b> arrayList = new ArrayList<>();
            k.this.m.a(bVar, arrayList);
            bVar.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 28, arrayList);
            k.this.o = null;
            if (arrayList.size() == 1) {
                k.this.o = arrayList.get(0);
            }
            if (arrayList.size() > 0) {
                if (k.this.o != null) {
                    k.this.o.setSlipping(true);
                    k.this.o.obtainMainView().setVisibility(0);
                    k.this.o.obtainMainView().bringToFront();
                }
                bVar.setSlipping(true);
                bVar.obtainMainView().setVisibility(0);
                bVar.obtainMainView().bringToFront();
                k.this.p = 2;
                FrameSwitchView frameSwitchView = FrameSwitchView.getInstance();
                if (frameSwitchView != null) {
                    frameSwitchView.endRefreshView();
                }
                k.this.a(bVar, "start", "undefined");
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(11)
    public void a(io.dcloud.common.core.ui.b bVar) {
        if (bVar != null) {
            View viewObtainMainView = bVar.obtainMainView();
            float width = (float) ((((double) this.f) - 0.95d) * 0.4210526315789474d * ((double) viewObtainMainView.getWidth()));
            if (width > 0.0f) {
                width = 0.0f;
            }
            viewObtainMainView.setTranslationX(width);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        io.dcloud.common.core.ui.b bVar = this.n;
        if (bVar != null) {
            String str = bVar.obtainFrameOptions().popGesture;
            if (str.equals("hide")) {
                this.n.dispatchFrameViewEvents(AbsoluteConst.EVENTS_WEBAPP_SLIDE_HIDE, null);
                io.dcloud.common.core.ui.b bVar2 = this.n;
                bVar2.mWindowMgr.c(bVar2);
            } else if (str.equals("close")) {
                this.n.dispatchFrameViewEvents(AbsoluteConst.EVENTS_WEBAPP_SLIDE_CLOSE, null);
                io.dcloud.common.core.ui.b bVar3 = this.n;
                bVar3.mWindowMgr.a(bVar3);
            }
        }
    }

    public void a(AdaFrameView adaFrameView, String str, Object obj) {
        adaFrameView.dispatchFrameViewEvents("popGesture", new Object[]{str, obj, this.n});
    }
}
