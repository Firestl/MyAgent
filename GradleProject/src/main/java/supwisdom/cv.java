package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Scroller;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class cv extends AbsoluteLayout {
    public static String p = "left";
    public static String q = "right";
    public static String r = "beforeSlide";
    public static String s = "afterSlide";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f7259a;
    public boolean b;
    public int c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public VelocityTracker f7260e;
    public boolean f;
    public int g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;
    public Scroller m;
    public float n;
    public d o;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7261a;
        public final /* synthetic */ String b;

        public a(String str, String str2) {
            this.f7261a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            cv.this.o.a(this.f7261a, this.b);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            cv cvVar = cv.this;
            cvVar.a(cvVar.getScrollX());
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            cv cvVar = cv.this;
            cvVar.a(cvVar.getScrollX());
        }
    }

    public interface d {
        void a(String str, String str2);
    }

    public cv(Context context) {
        super(context);
        this.f7259a = true;
        this.b = false;
        this.d = -1.0f;
        this.f = false;
        this.g = 0;
        this.h = 0;
        this.i = -1;
        this.j = -1;
        this.k = false;
        this.l = false;
        this.n = 0.0f;
        this.m = new Scroller(getContext());
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void b() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setDrawingCacheEnabled(true);
        }
    }

    public void c() {
        int scrollX = getScrollX();
        if (scrollX == 0) {
            return;
        }
        a(-scrollX, 0);
        if (scrollX < 0) {
            a(p, r);
        } else {
            a(q, r);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.m.computeScrollOffset()) {
            scrollTo(this.m.getCurrX(), this.m.getCurrY());
            postInvalidate();
        } else {
            a();
        }
        super.computeScroll();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (!this.f7259a) {
            return false;
        }
        if (!this.l && !this.k) {
            return false;
        }
        if (action == 3 || action == 1) {
            this.b = false;
            a();
            return this.b;
        }
        if (action != 0 && this.b) {
            return true;
        }
        if (action == 0) {
            this.d = motionEvent.getX();
            this.n = motionEvent.getX();
            this.b = false;
            this.f = false;
        } else if (action == 2 && ((int) Math.abs(motionEvent.getX() - this.n)) > this.c) {
            b();
            this.b = true;
            this.f = true;
            requestDisallowInterceptTouchEvent(true);
        }
        return this.b;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0145  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.cv.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setHeight(int i) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = i;
            requestLayout();
        }
    }

    public void setInterceptTouchEventEnabled(boolean z) {
        this.b = z;
    }

    public void setOnStateChangeListener(d dVar) {
        this.o = dVar;
    }

    public void setWidth(int i) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = i;
            requestLayout();
        }
    }

    public void a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setDrawingCacheEnabled(false);
        }
    }

    public void a(JSONObject jSONObject, float f, int i) {
        JSONObject jSONObject2 = JSONUtil.getJSONObject(jSONObject, AbsoluteConst.BOUNCE_SLIDEO_OFFSET);
        if (jSONObject2 != null) {
            JSONObject jSONObject3 = JSONUtil.getJSONObject(jSONObject, "position");
            if (jSONObject3 != null) {
                String strOptString = jSONObject3.optString(p);
                String strOptString2 = jSONObject3.optString(q);
                if (!TextUtils.isEmpty(strOptString)) {
                    this.i = PdrUtil.convertToScreenInt(strOptString, i, i / 2, f);
                }
                if (!TextUtils.isEmpty(strOptString2)) {
                    this.j = PdrUtil.convertToScreenInt(strOptString2, i, i / 2, f);
                }
            }
            this.f7259a = jSONObject.optBoolean("preventTouchEvent", true);
            String string = JSONUtil.getString(jSONObject2, p);
            if (!TextUtils.isEmpty(string)) {
                this.l = this.i > 0;
                this.g = PdrUtil.convertToScreenInt(string, i, i / 2, f);
            }
            String string2 = JSONUtil.getString(jSONObject2, q);
            if (TextUtils.isEmpty(string2)) {
                return;
            }
            this.k = this.j > 0;
            this.h = PdrUtil.convertToScreenInt(string2, i, i / 2, f);
        }
    }

    public void a(String str, String str2, float f) {
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(str2, getWidth(), 0, f);
        int scrollX = getScrollX();
        if (str.equals(p)) {
            if (iConvertToScreenInt == 0) {
                if (scrollX != 0) {
                    a(-scrollX, 0);
                    a(p, r);
                    return;
                }
                return;
            }
            int i = this.i;
            if (iConvertToScreenInt > i) {
                iConvertToScreenInt = i;
            }
            a(-(iConvertToScreenInt - Math.abs(scrollX)), 0);
            postDelayed(new b(), (r4 * 2) + 200);
            return;
        }
        if (iConvertToScreenInt == 0) {
            if (scrollX != 0) {
                a(-scrollX, 0);
                a(q, r);
                return;
            }
            return;
        }
        int i2 = this.j;
        if (iConvertToScreenInt > i2) {
            iConvertToScreenInt = i2;
        }
        a(iConvertToScreenInt - Math.abs(scrollX), 0);
        postDelayed(new c(), (r4 * 2) + 200);
    }

    public final void a(String str, String str2) {
        if (this.o != null) {
            postDelayed(new a(str2, str), 150L);
        }
    }

    public final void a(int i, int i2) {
        b();
        this.m.startScroll(getScrollX(), 0, i, 0, Math.abs(i) * 2);
        invalidate();
    }

    public void a(int i) {
        if (i < 0) {
            int iAbs = Math.abs(i);
            int i2 = this.g;
            if (iAbs >= i2 / 2 && this.i >= i2) {
                a(-(i2 - Math.abs(i)), 0);
                a(p, s);
                return;
            }
        }
        if (i > 0) {
            int iAbs2 = Math.abs(i);
            int i3 = this.h;
            if (iAbs2 >= i3 / 2 && this.j >= i3) {
                a(i3 - Math.abs(i), 0);
                a(q, s);
                return;
            }
        }
        if (i > 0) {
            a(-i, 0);
            a(q, r);
        } else {
            a(-i, 0);
            a(p, r);
        }
    }
}
