package supwisdom;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextUtils;
import android.view.MotionEvent;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import org.json.JSONObject;
import supwisdom.cv;

/* JADX INFO: loaded from: classes.dex */
public class zu extends cv {
    public RectF A;
    public int B;
    public int C;
    public int D;
    public int E;
    public boolean F;
    public AdaFrameView t;
    public ViewOptions u;
    public io.dcloud.common.core.ui.g v;
    public boolean w;
    public boolean x;
    public float y;
    public float z;

    public class a implements cv.d {
        public a() {
        }

        @Override // supwisdom.cv.d
        public void a(String str, String str2) {
            zu.this.t.dispatchFrameViewEvents(AbsoluteConst.EVENTS_SLIDE_BOUNCE, StringUtil.format("{status:'%s',offset:'%s'}", str, str2));
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f10032a;
        public final /* synthetic */ int b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f10033e;
        public final /* synthetic */ int f;
        public final /* synthetic */ int g;
        public final /* synthetic */ int h;
        public final /* synthetic */ int i;
        public final /* synthetic */ int j;
        public final /* synthetic */ int k;

        public b(int i, int i2, IWebview iWebview, String str, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.f10032a = i;
            this.b = i2;
            this.c = iWebview;
            this.d = str;
            this.f10033e = i3;
            this.f = i4;
            this.g = i5;
            this.h = i6;
            this.i = i7;
            this.j = i8;
            this.k = i9;
        }

        @Override // java.lang.Runnable
        public void run() {
            zu.this.invalidate();
            int i = this.f10032a;
            int i2 = this.b;
            if (i == i2) {
                zu.this.a(this.c, this.d);
            } else {
                zu.this.a(this.c, this.d, this.f10033e, this.f, this.g, this.h, this.i, this.j, i2, this.k, i + 1);
            }
        }
    }

    public zu(Context context, AdaFrameView adaFrameView, IApp iApp) {
        super(context);
        this.t = null;
        this.u = null;
        this.w = false;
        this.x = true;
        this.v = new io.dcloud.common.core.ui.g(adaFrameView, context);
        this.t = adaFrameView;
        this.u = adaFrameView.obtainFrameOptions();
        setOnStateChangeListener(new a());
    }

    public void d() {
        this.F = false;
        this.A = null;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        canvas.save();
        RectF rectF = this.A;
        if (rectF != null) {
            canvas.clipRect(rectF, Region.Op.DIFFERENCE);
        }
        ViewOptions viewOptions = this.u;
        if (viewOptions != null && !viewOptions.isTabHasBg() && !this.u.hasBackground() && !this.u.isTransparent() && !this.u.hasMask() && this.u.mUniNViewJson != null) {
            canvas.drawColor(-1);
        }
        this.t.paint(canvas);
        try {
            super.dispatchDraw(canvas);
            canvas.restore();
            ViewOptions viewOptions2 = this.u;
            if (viewOptions2 != null && viewOptions2.hasMask()) {
                canvas.drawColor(this.u.maskColor);
            }
            this.t.onDrawAfter(canvas);
        } catch (Exception unused) {
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            if (!this.t.interceptTouchEvent) {
                return false;
            }
            ViewOptions viewOptions = this.u;
            if (viewOptions == null || !viewOptions.hasMask()) {
                ViewOptions viewOptions2 = this.u;
                if (viewOptions2 == null || !viewOptions2.hasBackground()) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                super.dispatchTouchEvent(motionEvent);
                return true;
            }
            a(motionEvent);
            if (motionEvent.getAction() == 0) {
                this.w = false;
            }
            if (!this.w) {
                this.w = this.v.a(motionEvent);
            }
            if (this.w) {
                onTouchEvent(motionEvent);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public io.dcloud.common.core.ui.g getDrag() {
        return this.v;
    }

    public AdaFrameView getFrameView() {
        return this.t;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.t.onConfigurationChanged();
        if (this.F) {
            this.F = false;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.A != null) {
            canvas.save();
            ViewOptions viewOptions = this.u;
            int i = viewOptions.left;
            int i2 = viewOptions.top;
            canvas.clipRect(i, i2, viewOptions.width + i, viewOptions.height + i2);
            canvas.restore();
        }
        super.onDraw(canvas);
    }

    @Override // supwisdom.cv, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.v.a(motionEvent)) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // supwisdom.cv, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zC = this.v.c(motionEvent);
        if (this.u.isTransparent()) {
            return super.onTouchEvent(motionEvent);
        }
        AdaFrameView adaFrameView = this.t;
        if ((adaFrameView == null || !adaFrameView.isTouchEvent) && !zC) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    public String toString() {
        AdaFrameView adaFrameView = this.t;
        return adaFrameView != null ? adaFrameView.toString() : super.toString();
    }

    public void a(IWebview iWebview, String str, String str2) {
        if (this.u != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("type");
                int iOptInt = jSONObject.optInt("duration", 200);
                int iOptInt2 = jSONObject.optInt("frames", 12);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("region");
                if (jSONObjectOptJSONObject != null) {
                    String strOptString2 = jSONObjectOptJSONObject.optString("left");
                    ViewOptions viewOptions = this.u;
                    this.B = PdrUtil.convertToScreenInt(strOptString2, viewOptions.width, 0, viewOptions.mWebviewScale);
                    String strOptString3 = jSONObjectOptJSONObject.optString("right");
                    ViewOptions viewOptions2 = this.u;
                    this.C = PdrUtil.convertToScreenInt(strOptString3, viewOptions2.width, 0, viewOptions2.mWebviewScale);
                    String strOptString4 = jSONObjectOptJSONObject.optString("top");
                    ViewOptions viewOptions3 = this.u;
                    this.D = PdrUtil.convertToScreenInt(strOptString4, viewOptions3.height, 0, viewOptions3.mWebviewScale);
                    String strOptString5 = jSONObjectOptJSONObject.optString("bottom");
                    ViewOptions viewOptions4 = this.u;
                    this.E = PdrUtil.convertToScreenInt(strOptString5, viewOptions4.height, 0, viewOptions4.mWebviewScale);
                }
                int i = iOptInt / iOptInt2;
                ViewOptions viewOptions5 = this.u;
                int i2 = viewOptions5.height - ((this.D + viewOptions5.top) + this.E);
                int i3 = i2 / iOptInt2;
                int i4 = i2 - (i3 * iOptInt2);
                if (TextUtils.isEmpty(strOptString) || !strOptString.equals("shrink")) {
                    return;
                }
                this.F = true;
                int i5 = this.B;
                int i6 = this.C;
                int i7 = this.D;
                ViewOptions viewOptions6 = this.u;
                a(iWebview, str2, i5, i6, i7 + viewOptions6.top, viewOptions6.height - this.E, i, i3, iOptInt2, i4, 1);
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                a(iWebview, str2);
                return;
            }
        }
        a(iWebview, str2);
    }

    public final void a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.x = true;
            this.y = motionEvent.getX();
            this.z = motionEvent.getY();
            return;
        }
        if (motionEvent.getAction() == 1) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.x) {
                float f = 10;
                if (Math.abs(this.y - x) > f || Math.abs(this.z - y) > f) {
                    return;
                }
                this.t.dispatchFrameViewEvents(AbsoluteConst.EVENTS_MASK_CLICK, null);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 2) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            if (this.x) {
                float f2 = 10;
                if (Math.abs(this.y - x2) <= f2 || Math.abs(this.z - y2) <= f2) {
                    return;
                }
                this.x = false;
            }
        }
    }

    public final void a(IWebview iWebview, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Deprecated_JSUtil.execCallback(iWebview, str, null, JSUtil.OK, false, false);
    }

    public final void a(IWebview iWebview, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (!this.F) {
            a(iWebview, str);
            return;
        }
        if (this.A == null) {
            this.A = new RectF();
        }
        RectF rectF = this.A;
        rectF.left = i;
        rectF.right = this.u.width - i2;
        rectF.top = i3;
        if (i9 == i7) {
            rectF.bottom = (i6 * i9) + i3 + i8;
        } else {
            rectF.bottom = (i6 * i9) + i3;
        }
        postDelayed(new b(i9, i7, iWebview, str, i, i2, i3, i4, i5, i6, i8), i5);
    }
}
