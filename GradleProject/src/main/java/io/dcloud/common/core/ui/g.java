package io.dcloud.common.core.ui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.ui.view.gesture.WXGesture;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.adapter.util.DragBean;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.nineoldandroids.animation.Animator;
import io.dcloud.nineoldandroids.animation.ValueAnimator;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONObject;
import supwisdom.zu;

/* JADX INFO: loaded from: classes2.dex */
public class g {
    public static boolean e0 = false;
    public String A;
    public IFrameView G;
    public String H;
    public String I;
    public int J;
    public boolean K;
    public float L;
    public int M;
    public int R;
    public DisplayMetrics S;
    public float U;
    public float V;
    public float W;
    public float X;
    public float Y;
    public float Z;
    public boolean b0;
    public Pattern d0;
    public VelocityTracker f;
    public int h;
    public float i;
    public float j;
    public int k;
    public io.dcloud.common.core.ui.b p;
    public io.dcloud.common.core.ui.b q;
    public View s;
    public String v;
    public String w;
    public String y;
    public String z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f6394a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6395e = false;
    public boolean g = true;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    public View r = null;
    public boolean t = false;
    public int u = Integer.MAX_VALUE;
    public int x = Integer.MAX_VALUE;
    public boolean B = false;
    public boolean C = true;
    public boolean D = true;
    public boolean E = true;
    public int F = -1;
    public boolean N = false;
    public boolean O = false;
    public boolean P = false;
    public int Q = Integer.MAX_VALUE;
    public float T = 20.0f;
    public boolean a0 = true;
    public String c0 = "^([1-9]|[1-9]\\d|100)$";

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int i;
            if (Build.VERSION.SDK_INT >= 16) {
                g.this.s.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            g gVar = g.this;
            boolean z = true;
            if (gVar.d(gVar.I)) {
                i = g.this.o;
            } else {
                g gVar2 = g.this;
                int iB = gVar2.b(gVar2.s) / 2;
                Rect rect = new Rect();
                g.this.s.getGlobalVisibleRect(rect);
                if (rect.right - rect.left >= iB) {
                    i = g.this.o;
                } else {
                    i = g.this.n;
                    z = false;
                }
            }
            g gVar3 = g.this;
            int iA = gVar3.a(gVar3.s);
            g gVar4 = g.this;
            ValueAnimator valueAnimatorA = gVar4.a(gVar4.s, iA, i, z);
            if (valueAnimatorA != null) {
                valueAnimatorA.start();
            }
        }
    }

    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00e6  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x016c  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x01a2  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01b1  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x01c1  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x01da  */
        /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onGlobalLayout() {
            /*
                Method dump skipped, instruction units count: 478
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.b.onGlobalLayout():void");
        }
    }

    public class c implements ViewTreeObserver.OnGlobalLayoutListener {
        public c() {
        }

        /* JADX WARN: Removed duplicated region for block: B:58:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x021d  */
        /* JADX WARN: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onGlobalLayout() {
            /*
                Method dump skipped, instruction units count: 686
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.c.onGlobalLayout():void");
        }
    }

    public class d implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f6399a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ View c;
        public final /* synthetic */ int d;

        public d(boolean z, boolean z2, View view, int i) {
            this.f6399a = z;
            this.b = z2;
            this.c = view;
            this.d = i;
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        /* JADX WARN: Removed duplicated region for block: B:57:0x0137  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x014c  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x014f  */
        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onAnimationEnd(io.dcloud.nineoldandroids.animation.Animator r12) {
            /*
                Method dump skipped, instruction units count: 540
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.d.onAnimationEnd(io.dcloud.nineoldandroids.animation.Animator):void");
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            g.g(g.this);
        }
    }

    public class e implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6401a;

        public e(View view) {
            this.f6401a = view;
        }

        @Override // io.dcloud.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ViewGroup.LayoutParams layoutParams = this.f6401a.getLayoutParams();
            View view = this.f6401a;
            if (view instanceof INativeView) {
                g.this.b(view, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                this.f6401a.requestLayout();
                this.f6401a.invalidate();
            } else {
                if (layoutParams instanceof FrameLayout.LayoutParams) {
                    ViewHelper.setX(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                    return;
                }
                if (layoutParams instanceof AbsoluteLayout.LayoutParams) {
                    try {
                        ViewHelper.setX(this.f6401a, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    } catch (Exception unused) {
                        ViewHelper.setX(this.f6401a, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                    this.f6401a.requestLayout();
                }
            }
        }
    }

    public class f implements ViewTreeObserver.OnGlobalLayoutListener {
        public f() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int iB;
            int iB2;
            int iB3;
            if (Build.VERSION.SDK_INT >= 16) {
                g.this.r.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            g gVar = g.this;
            boolean z = true;
            if (gVar.d(gVar.I)) {
                if ("right".equals(g.this.I)) {
                    iB2 = g.this.M;
                } else if ("left".equals(g.this.I)) {
                    g gVar2 = g.this;
                    iB3 = gVar2.b(gVar2.s);
                    iB2 = -iB3;
                } else {
                    iB2 = 0;
                }
                iB = 0;
            } else {
                View view = (View) g.this.r.getParent();
                int[] iArr = new int[2];
                int[] iArr2 = new int[2];
                int[] iArr3 = new int[2];
                view.getLocationOnScreen(iArr);
                g.this.r.getLocationOnScreen(iArr2);
                g.this.s.getLocationOnScreen(iArr3);
                int width = iArr[0] + view.getWidth();
                int i = iArr2[0];
                g.this.r.getWidth();
                int i2 = iArr3[0];
                g gVar3 = g.this;
                int iB4 = i2 + gVar3.b(gVar3.s);
                if (iArr3[0] <= iArr[0] && iArr[0] < iB4) {
                    int i3 = iB4 - iArr[0];
                    if (i3 > 0) {
                        g gVar4 = g.this;
                        if (i3 < gVar4.b(gVar4.s)) {
                            g gVar5 = g.this;
                            if (i3 <= gVar5.b(gVar5.s) / 2) {
                                g gVar6 = g.this;
                                iB3 = gVar6.b(gVar6.s);
                                iB2 = -iB3;
                                iB = 0;
                            } else {
                                g gVar7 = g.this;
                                iB = gVar7.b(gVar7.s);
                                iB2 = 0;
                            }
                        }
                    }
                    g gVar8 = g.this;
                    if (i3 == gVar8.b(gVar8.s)) {
                        g.this.a(false, true);
                        g gVar9 = g.this;
                        View view2 = gVar9.r;
                        g gVar10 = g.this;
                        gVar9.a(view2, gVar10.b(gVar10.s));
                        g gVar11 = g.this;
                        gVar11.a(gVar11.s, 0);
                        boolean unused = g.e0 = false;
                        return;
                    }
                    g.this.a(true, true);
                    g gVar12 = g.this;
                    gVar12.a(gVar12.r, 0);
                    g gVar13 = g.this;
                    View view3 = gVar13.s;
                    g gVar14 = g.this;
                    gVar13.a(view3, -gVar14.b(gVar14.s));
                    boolean unused2 = g.e0 = false;
                    return;
                }
                if (iArr3[0] >= width || width > iB4) {
                    g.this.a(true, true);
                    g gVar15 = g.this;
                    gVar15.a(gVar15.r, 0);
                    g gVar16 = g.this;
                    gVar16.a(gVar16.s, g.this.M);
                    boolean unused3 = g.e0 = false;
                    return;
                }
                int i4 = width - iArr3[0];
                if (i4 > 0) {
                    g gVar17 = g.this;
                    if (i4 < gVar17.b(gVar17.s)) {
                        g gVar18 = g.this;
                        if (i4 <= gVar18.b(gVar18.s) / 2) {
                            iB2 = g.this.M;
                            iB = 0;
                        } else {
                            g gVar19 = g.this;
                            int i5 = -gVar19.b(gVar19.s);
                            int i6 = g.this.M;
                            g gVar20 = g.this;
                            iB = i5;
                            iB2 = i6 - gVar20.b(gVar20.s);
                        }
                    }
                }
                g gVar21 = g.this;
                if (i4 != gVar21.b(gVar21.s)) {
                    g.this.a(true, true);
                    g gVar22 = g.this;
                    gVar22.a(gVar22.r, 0);
                    g gVar23 = g.this;
                    gVar23.a(gVar23.s, g.this.M);
                    boolean unused4 = g.e0 = false;
                    return;
                }
                g.this.a(false, true);
                g gVar24 = g.this;
                View view4 = gVar24.r;
                g gVar25 = g.this;
                gVar24.a(view4, -gVar25.b(gVar25.s));
                g gVar26 = g.this;
                View view5 = gVar26.s;
                int i7 = g.this.M;
                g gVar27 = g.this;
                gVar26.a(view5, i7 - gVar27.b(gVar27.s));
                boolean unused5 = g.e0 = false;
                return;
                z = false;
            }
            g gVar28 = g.this;
            int iA = gVar28.a(gVar28.r);
            if (iA != 0) {
                g gVar29 = g.this;
                ValueAnimator valueAnimatorA = gVar29.a(gVar29.r, iA, iB, z, true);
                if (valueAnimatorA != null) {
                    valueAnimatorA.start();
                }
            }
            g gVar30 = g.this;
            int iA2 = gVar30.a(gVar30.s);
            g gVar31 = g.this;
            ValueAnimator valueAnimatorA2 = gVar31.a(gVar31.s, iA2, iB2, z, true);
            if (valueAnimatorA2 != null) {
                valueAnimatorA2.start();
            }
        }
    }

    public g(IFrameView iFrameView, Context context) {
        this.R = 0;
        e0 = false;
        this.R = 0;
        if (iFrameView instanceof io.dcloud.common.core.ui.b) {
            this.p = (io.dcloud.common.core.ui.b) iFrameView;
            if (this.S == null) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                this.S = displayMetrics;
                this.M = displayMetrics.widthPixels;
            }
        }
        this.h = ViewConfiguration.get(context).getScaledTouchSlop();
        this.d0 = Pattern.compile(this.c0);
    }

    public static /* synthetic */ int g(g gVar) {
        int i = gVar.R;
        gVar.R = i + 1;
        return i;
    }

    public static /* synthetic */ int h(g gVar) {
        int i = gVar.R;
        gVar.R = i - 1;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        if (r5 != 3) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0048 A[PHI: r2
  0x0048: PHI (r2v16 int) = (r2v15 int), (r2v19 int) binds: [B:12:0x003e, B:15:0x0046] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072 A[PHI: r2
  0x0072: PHI (r2v9 int) = (r2v8 int), (r2v12 int) binds: [B:26:0x0068, B:29:0x0070] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082 A[PHI: r0 r2
  0x0082: PHI (r0v8 float) = (r0v7 float), (r0v7 float), (r0v13 float), (r0v13 float) binds: [B:32:0x0078, B:35:0x0080, B:18:0x004e, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]
  0x0082: PHI (r2v13 int) = (r2v10 int), (r2v11 int), (r2v17 int), (r2v18 int) binds: [B:32:0x0078, B:35:0x0080, B:18:0x004e, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean d(android.view.MotionEvent r5) {
        /*
            r4 = this;
            float r0 = r5.getRawX()
            int r5 = r5.getAction()
            r1 = 1
            if (r5 == r1) goto L90
            r2 = 2
            if (r5 == r2) goto L13
            r0 = 3
            if (r5 == r0) goto L90
            goto L8f
        L13:
            float r5 = r4.i
            float r5 = r0 - r5
            int r5 = (int) r5
            float r5 = (float) r5
            r4.i = r0
            float r5 = r4.a(r5)
            android.view.View r0 = r4.r
            int r0 = r4.a(r0)
            float r0 = (float) r0
            float r0 = r0 + r5
            android.view.View r2 = r4.s
            int r2 = r4.a(r2)
            float r2 = (float) r2
            float r5 = r5 + r2
            java.lang.String r2 = r4.I
            java.lang.String r3 = "right"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L59
            int r2 = r4.m
            float r3 = (float) r2
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 < 0) goto L41
            goto L48
        L41:
            int r2 = r4.l
            float r3 = (float) r2
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 > 0) goto L49
        L48:
            float r0 = (float) r2
        L49:
            int r2 = r4.o
            float r3 = (float) r2
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 < 0) goto L51
            goto L82
        L51:
            int r2 = r4.n
            float r3 = (float) r2
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 > 0) goto L83
            goto L82
        L59:
            java.lang.String r2 = r4.I
            java.lang.String r3 = "left"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L83
            int r2 = r4.l
            float r3 = (float) r2
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 < 0) goto L6b
            goto L72
        L6b:
            int r2 = r4.m
            float r3 = (float) r2
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 > 0) goto L73
        L72:
            float r0 = (float) r2
        L73:
            int r2 = r4.n
            float r3 = (float) r2
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 < 0) goto L7b
            goto L82
        L7b:
            int r2 = r4.o
            float r3 = (float) r2
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 > 0) goto L83
        L82:
            float r5 = (float) r2
        L83:
            android.view.View r2 = r4.r
            int r0 = (int) r0
            r4.b(r2, r0)
            android.view.View r0 = r4.s
            int r5 = (int) r5
            r4.b(r0, r5)
        L8f:
            return r1
        L90:
            r5 = 0
            r4.g = r5
            boolean r0 = r4.E
            if (r0 == 0) goto L9a
            r4.a()
        L9a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.d(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        if (r4 != 3) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0053 A[PHI: r4
  0x0053: PHI (r4v11 int) = (r4v9 int), (r4v10 int), (r4v13 int), (r4v14 int) binds: [B:20:0x0049, B:23:0x0051, B:12:0x002f, B:15:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean f(android.view.MotionEvent r4) {
        /*
            r3 = this;
            float r0 = r4.getRawX()
            int r4 = r4.getAction()
            r1 = 1
            if (r4 == r1) goto L5b
            r2 = 2
            if (r4 == r2) goto L12
            r0 = 3
            if (r4 == r0) goto L5b
            goto L5a
        L12:
            float r4 = r3.i
            float r4 = r0 - r4
            r3.i = r0
            android.view.View r0 = r3.s
            int r0 = r3.a(r0)
            float r0 = (float) r0
            float r0 = r0 + r4
            java.lang.String r4 = r3.I
            java.lang.String r2 = "right"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L3a
            int r4 = r3.o
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L32
            goto L53
        L32:
            int r4 = r3.n
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L54
            goto L53
        L3a:
            java.lang.String r4 = r3.I
            java.lang.String r2 = "left"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L54
            int r4 = r3.n
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L4c
            goto L53
        L4c:
            int r4 = r3.o
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L54
        L53:
            float r0 = (float) r4
        L54:
            android.view.View r4 = r3.s
            int r0 = (int) r0
            r3.b(r4, r0)
        L5a:
            return r1
        L5b:
            r4 = 0
            r3.g = r4
            boolean r0 = r3.E
            if (r0 == 0) goto L65
            r3.c()
        L65:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.f(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        if (r4 != 3) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0057 A[PHI: r4
  0x0057: PHI (r4v12 int) = (r4v10 int), (r4v11 int), (r4v14 int), (r4v15 int) binds: [B:20:0x004d, B:23:0x0055, B:12:0x0033, B:15:0x003b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean g(android.view.MotionEvent r4) {
        /*
            r3 = this;
            float r0 = r4.getRawX()
            int r4 = r4.getAction()
            r1 = 1
            if (r4 == r1) goto L5f
            r2 = 2
            if (r4 == r2) goto L12
            r0 = 3
            if (r4 == r0) goto L5f
            goto L5e
        L12:
            float r4 = r3.i
            float r4 = r0 - r4
            r3.i = r0
            float r4 = r3.a(r4)
            android.view.View r0 = r3.r
            int r0 = r3.a(r0)
            float r0 = (float) r0
            float r0 = r0 + r4
            java.lang.String r4 = r3.I
            java.lang.String r2 = "right"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L3e
            int r4 = r3.m
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L36
            goto L57
        L36:
            int r4 = r3.l
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L58
            goto L57
        L3e:
            java.lang.String r4 = r3.I
            java.lang.String r2 = "left"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L58
            int r4 = r3.l
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L50
            goto L57
        L50:
            int r4 = r3.m
            float r2 = (float) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L58
        L57:
            float r0 = (float) r4
        L58:
            android.view.View r4 = r3.r
            int r0 = (int) r0
            r3.b(r4, r0)
        L5e:
            return r1
        L5f:
            r4 = 0
            r3.g = r4
            boolean r0 = r3.E
            if (r0 == 0) goto L69
            r3.d()
        L69:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.g(android.view.MotionEvent):boolean");
    }

    private boolean h() {
        this.l = a(this.r);
        if ("right".equals(this.I)) {
            int i = this.u;
            if (Integer.MAX_VALUE != i) {
                this.m = i;
                return this.l != i;
            }
            this.m = this.M;
            View view = this.s;
            if (view == null) {
                if (view != null || this.l >= 0) {
                    return true;
                }
                this.m = 0;
                return true;
            }
            if ("bounce".equalsIgnoreCase(this.A)) {
                this.m = this.l + (b(this.r) / 2);
                return true;
            }
            if (b(this.s) >= this.M) {
                return true;
            }
            int iA = a(this.r);
            int iA2 = a(this.s);
            int iA3 = a(this.s) + b(this.s);
            if (iA2 == 0 && iA3 == iA) {
                return false;
            }
            boolean z = this.D;
            if (!z && iA2 == 0) {
                this.m = b(this.s);
                return true;
            }
            if (z && iA3 == 0) {
                this.m = b(this.s);
                return true;
            }
            if (this.M != iA3) {
                return true;
            }
            this.m = 0;
            return true;
        }
        if (!"left".equals(this.I)) {
            return true;
        }
        int i2 = this.u;
        if (Integer.MAX_VALUE != i2) {
            this.m = i2;
            return this.l != i2;
        }
        this.m = -b(this.r);
        View view2 = this.s;
        if (view2 == null) {
            if (view2 != null || this.l <= 0) {
                return true;
            }
            this.m = 0;
            return true;
        }
        if ("bounce".equalsIgnoreCase(this.A)) {
            this.m = this.l - (b(this.r) / 2);
            return true;
        }
        if (b(this.s) >= this.M) {
            return true;
        }
        int iA4 = a(this.r) + b(this.r);
        int iA5 = a(this.s);
        int iA6 = a(this.s) + b(this.s);
        int i3 = this.M;
        if (i3 == iA6 && iA5 == iA4) {
            return false;
        }
        boolean z2 = this.D;
        if (!z2 && i3 == iA6) {
            this.m = -b(this.s);
            return true;
        }
        if (z2 && i3 == iA5) {
            this.m = -b(this.s);
            return true;
        }
        if (iA5 != 0) {
            return true;
        }
        this.m = 0;
        return true;
    }

    private void i() {
        float f2 = this.l;
        this.U = f2;
        float f3 = this.m;
        this.V = f3;
        this.X = f2;
        this.W = f2;
        float fAbs = Math.abs(f3 - f2);
        this.Y = fAbs;
        this.Z = (fAbs * this.T) / 100.0f;
        this.a0 = true;
        this.b0 = false;
    }

    private boolean j() {
        return "right".equals(this.I) ? a(this.s) != 0 : ("left".equals(this.I) && a(this.s) + b(this.s) == this.M) ? false : true;
    }

    private void k() {
        float fA = a(this.r);
        this.X = fA;
        if (this.a0) {
            float f2 = this.U;
            if (fA == f2) {
                this.b0 = true;
                this.a0 = false;
                this.W = f2;
                a(WXGesture.MOVE, false, "0");
                return;
            }
        }
        if (Math.abs(fA - this.W) >= this.Z) {
            this.a0 = true;
            this.b0 = true;
            a(WXGesture.MOVE, false, "" + ((int) ((Math.abs(this.X - this.U) / this.Y) * 100.0f)));
            this.W = this.X;
            return;
        }
        if (this.b0) {
            float f3 = this.X;
            float f4 = this.V;
            if (f3 == f4) {
                this.b0 = false;
                this.a0 = true;
                this.W = f4;
                a(WXGesture.MOVE, false, "100");
            }
        }
    }

    public HashMap<String, DragBean> e() {
        io.dcloud.common.core.ui.b bVar = this.p;
        if (bVar == null || bVar.obtainFrameOptions() == null) {
            return null;
        }
        return this.p.obtainFrameOptions().dragData;
    }

    public void c(boolean z) {
        this.f6394a = z;
    }

    public boolean c(MotionEvent motionEvent) {
        View view;
        View view2;
        if (!this.g) {
            return false;
        }
        if (motionEvent.getPointerCount() > 1) {
            return true;
        }
        if (BaseInfo.sDoingAnimation) {
            if (this.P) {
                return false;
            }
            this.P = true;
            motionEvent.setAction(3);
        }
        if (this.f6394a && !this.g) {
            return true;
        }
        if (this.f == null) {
            this.f = VelocityTracker.obtain();
        }
        if (this.F == -1 || e0) {
            return false;
        }
        if (2 == motionEvent.getAction()) {
            k();
            if ("left".equals(this.I)) {
                if (motionEvent.getRawX() < this.i) {
                    this.f.addMovement(motionEvent);
                }
            } else if ("right".equals(this.I) && motionEvent.getRawX() > this.i) {
                this.f.addMovement(motionEvent);
            }
        }
        if (1 == motionEvent.getAction() || 3 == motionEvent.getAction()) {
            this.f.addMovement(motionEvent);
        }
        if (this.O) {
            return e(motionEvent);
        }
        boolean z = this.B;
        if (z && this.C && this.D) {
            if ((!a(this.p) && !a(this.q)) || ((view2 = this.s) != null && (view2 instanceof INativeView))) {
                return d(motionEvent);
            }
        } else if (z && !this.C && this.D) {
            if ((!a(this.p) && !a(this.q)) || ((view = this.s) != null && (view instanceof INativeView))) {
                return f(motionEvent);
            }
        } else if (z && !this.D && this.C) {
            if (!a(this.p) && !a(this.q)) {
                return g(motionEvent);
            }
        } else if (!z && this.C && !a(this.p)) {
            return g(motionEvent);
        }
        return true;
    }

    private boolean e(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        this.r = this.p.obtainMainView();
        this.u = Integer.MAX_VALUE;
        this.v = null;
        this.w = null;
        HashMap<String, DragBean> mapE = e();
        if (mapE == null || !mapE.containsKey(str)) {
            return false;
        }
        DragBean dragBean = mapE.get(str);
        if (dragBean != null) {
            this.H = dragBean.dragCbId;
            this.G = dragBean.dragCallBackWebView;
            JSONObject jSONObject3 = dragBean.dragCurrentViewOp;
            try {
                JSONUtil.getString(jSONObject3, "direction");
                if (jSONObject3.has("moveMode")) {
                    String string = JSONUtil.getString(jSONObject3, "moveMode");
                    this.A = string;
                    this.C = "followFinger".equalsIgnoreCase(string) || "follow".equalsIgnoreCase(this.A) || "bounce".equalsIgnoreCase(this.A);
                }
                this.T = 20.0f;
                if (jSONObject3.has("callbackStep")) {
                    try {
                        if (this.d0.matcher(jSONObject3.getString("callbackStep")).find()) {
                            this.T = Integer.valueOf(r2).intValue();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (jSONObject3.has("over") && (jSONObject2 = JSONUtil.getJSONObject(jSONObject3, "over")) != null) {
                    if (jSONObject2.has("left")) {
                        this.u = PdrUtil.parseInt(JSONUtil.getString(jSONObject2, "left"), this.M, Integer.MAX_VALUE);
                    }
                    if (jSONObject2.has("action")) {
                        this.v = JSONUtil.getString(jSONObject2, "action");
                    }
                }
                if (jSONObject3.has(WXPickersModule.CANCEL) && (jSONObject = JSONUtil.getJSONObject(jSONObject3, WXPickersModule.CANCEL)) != null) {
                    if (jSONObject.has("left")) {
                        PdrUtil.parseInt(JSONUtil.getString(jSONObject, "left"), this.M, Integer.MAX_VALUE);
                    }
                    if (jSONObject.has("action")) {
                        this.w = JSONUtil.getString(jSONObject, "action");
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void b(MotionEvent motionEvent) {
        DisplayMetrics displayMetrics = this.S;
        if (displayMetrics != null) {
            this.M = displayMetrics.widthPixels;
        }
        this.L = motionEvent.getRawX();
        this.i = motionEvent.getRawX();
        this.j = motionEvent.getRawY();
        this.g = false;
        this.B = false;
        this.t = false;
        this.P = false;
        this.F = -1;
        this.p.obtainWebView().loadUrl("javascript:window.__needNotifyNative__=true;");
        this.p.obtainWebView().setWebviewProperty("needTouchEvent", AbsoluteConst.FALSE);
        this.K = false;
        this.J = this.p.obtainWebView().obtainWindowView().getScrollY();
        if (this.f == null) {
            this.f = VelocityTracker.obtain();
        }
        this.f.addMovement(motionEvent);
    }

    public boolean a(MotionEvent motionEvent) {
        View view;
        View view2;
        if (this.f6394a) {
            return true;
        }
        if (e() != null && !BaseInfo.sDoingAnimation && !e0) {
            int actionMasked = motionEvent.getActionMasked();
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            if (actionMasked == 0 && motionEvent.getEdgeFlags() != 0) {
                return false;
            }
            if (actionMasked == 0) {
                b(motionEvent);
            } else if (actionMasked == 1) {
                this.t = false;
                this.F = -1;
            } else if (actionMasked == 2) {
                if (this.J != this.p.obtainWebView().obtainWindowView().getScrollY()) {
                    this.K = true;
                }
                if (this.K) {
                    return false;
                }
                float f2 = rawX - this.i;
                if (Math.abs(f2) >= Math.abs(rawY - this.j)) {
                    if (((float) (this.h * 3)) <= Math.abs(motionEvent.getRawX() - this.L) && AbsoluteConst.FALSE.equals(this.p.obtainWebView().getWebviewProperty("needTouchEvent"))) {
                        String str = "right";
                        if (f2 >= 0.0f) {
                            if (!this.t && e("right")) {
                                this.I = "right";
                                this.g = true;
                                this.t = true;
                            }
                        } else {
                            if (!this.t && e("left")) {
                                this.I = "left";
                                this.g = true;
                                this.t = true;
                            }
                            str = "left";
                        }
                        if (-1 == this.F) {
                            this.i = rawX;
                            View viewA = a(str);
                            if (viewA != null) {
                                HashMap<String, DragBean> mapE = e();
                                String strC = c(str);
                                if (mapE != null && mapE.containsKey(strC)) {
                                    a(mapE.get(strC).dragBindViewOp);
                                }
                                this.I = str;
                                this.g = true;
                                this.s = viewA;
                                this.O = true;
                                this.F = 1;
                            } else {
                                b(this.I);
                            }
                        }
                    }
                }
            }
            if (this.g) {
                if (this.O) {
                    this.g = j();
                } else {
                    boolean z = this.B;
                    if (z && this.C && this.D) {
                        if ((!a(this.p) && !a(this.q)) || ((view2 = this.s) != null && (view2 instanceof INativeView))) {
                            this.g = g();
                        }
                    } else if (z && !this.C && this.D) {
                        if ((!a(this.p) && !a(this.q)) || ((view = this.s) != null && (view instanceof INativeView))) {
                            this.g = f();
                        }
                    } else if (!z && this.C) {
                        if (!a(this.p)) {
                            h();
                        }
                    } else if (z && !this.D && this.C) {
                        if (!a(this.p) && !a(this.q)) {
                            this.g = h();
                        }
                    } else {
                        this.g = false;
                    }
                }
            }
            if (this.g) {
                if (!a(this.p)) {
                    this.b = this.p.obtainWebView().obtainWindowView().isVerticalScrollBarEnabled();
                    this.c = this.p.obtainWebView().obtainWindowView().isHorizontalScrollBarEnabled();
                    this.p.obtainWebView().obtainWindowView().setVerticalScrollBarEnabled(false);
                    this.p.obtainWebView().obtainWindowView().setHorizontalScrollBarEnabled(false);
                }
                View view3 = this.s;
                if (view3 != null && (view3 instanceof zu)) {
                    ((zu) view3).getDrag().c(true);
                    if (!a(this.q)) {
                        this.d = this.q.obtainWebView().obtainWindowView().isVerticalScrollBarEnabled();
                        this.f6395e = this.q.obtainWebView().obtainWindowView().isHorizontalScrollBarEnabled();
                        this.q.obtainWebView().obtainWindowView().setVerticalScrollBarEnabled(false);
                        this.q.obtainWebView().obtainWindowView().setHorizontalScrollBarEnabled(false);
                    }
                }
                a("start", false, "0");
                i();
            }
            return this.g;
        }
        this.g = false;
        return false;
    }

    private boolean f() {
        this.n = a(this.s);
        if ("right".equals(this.I)) {
            int i = this.x;
            if (Integer.MAX_VALUE != i) {
                this.o = i;
                return this.n != i;
            }
            this.o = b(this.s);
            int i2 = this.n;
            if (i2 == 0 || i2 == this.M) {
                return false;
            }
            if ("bounce".equalsIgnoreCase(this.A)) {
                this.o = this.n + (b(this.r) / 2);
                return true;
            }
            if (this.n >= 0) {
                return true;
            }
            this.o = 0;
            return true;
        }
        if (!"left".equals(this.I)) {
            return true;
        }
        int i3 = this.x;
        if (Integer.MAX_VALUE != i3) {
            this.o = i3;
            return this.n != i3;
        }
        int iB = b(this.s);
        this.o = -iB;
        if ("bounce".equalsIgnoreCase(this.A)) {
            this.o = this.n - (b(this.r) / 2);
            return true;
        }
        int i4 = this.M;
        if (iB < i4) {
            int iA = a(this.s) + b(this.s);
            if (iA == this.M || iA == 0) {
                return false;
            }
            Rect rect = new Rect();
            this.s.getGlobalVisibleRect(rect);
            int i5 = this.M;
            if (i5 == rect.left) {
                this.o = i5 - iB;
                return true;
            }
            if (rect.right != 0) {
                return true;
            }
            this.o = 0;
            return true;
        }
        if (iB != i4 || this.n <= 0) {
            return true;
        }
        this.o = 0;
        return true;
    }

    private boolean g() {
        return h() && f();
    }

    private int b(String str) {
        View view;
        DragBean dragBean;
        int i = this.F;
        if (i != -1) {
            return i;
        }
        this.q = null;
        this.s = null;
        HashMap<String, DragBean> mapE = e();
        if (mapE != null && mapE.containsKey(str) && (dragBean = mapE.get(str)) != null) {
            a(dragBean.dragBindViewOp);
            IFrameView iFrameView = dragBean.dragBindWebView;
            if (iFrameView != null && (iFrameView instanceof io.dcloud.common.core.ui.b)) {
                this.B = true;
                io.dcloud.common.core.ui.b bVar = (io.dcloud.common.core.ui.b) iFrameView;
                this.q = bVar;
                this.s = bVar.obtainMainView();
            } else {
                View view2 = dragBean.nativeView;
                this.s = view2;
                if (view2 != null) {
                    this.B = true;
                }
            }
        }
        if (a(this.q) && ((view = this.s) == null || !(view instanceof INativeView))) {
            this.F = 0;
        } else {
            if (this.s.getVisibility() != 0) {
                this.q = null;
                this.s = null;
                return 0;
            }
            if (this.s.getParent() == null && (this.r.getParent() instanceof FrameLayout) && !(this.s instanceof INativeView)) {
                this.q.pushToViewStack();
            }
            if (!a(this.r, this.s) && !(this.s instanceof INativeView)) {
                this.q = null;
                this.s = null;
                return 0;
            }
            if (!(this.s instanceof INativeView) && this.D && this.r.getParent() != this.s.getParent()) {
                this.F = 0;
                return 0;
            }
            View view3 = this.s;
            if (view3 instanceof INativeView) {
                view3.bringToFront();
            }
            int iA = a(this.s);
            int iA2 = a(this.r);
            if (iA2 == 0) {
                int width = this.r.getWidth();
                int i2 = this.M;
                if (width == i2 && (iA >= i2 || iA <= (-b(this.s)))) {
                    this.Q = iA;
                    boolean z = this.C;
                    if (z && this.D) {
                        if ("right".equals(str)) {
                            this.k = iA2 - b(this.s);
                        } else if ("left".equals(str)) {
                            this.k = iA2 + this.r.getWidth();
                        }
                        b(this.s, this.k);
                    } else if (!z && this.D) {
                        if ("right".equals(str)) {
                            this.k = -b(this.s);
                        } else if ("left".equals(str)) {
                            this.k = this.r.getWidth();
                        }
                        b(this.s, this.k);
                    }
                }
            }
            this.F = 1;
        }
        return this.F;
    }

    private void d() {
        this.N = true;
        e0 = true;
        this.r.requestLayout();
        this.r.getViewTreeObserver().addOnGlobalLayoutListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str) {
        VelocityTracker velocityTracker = this.f;
        if (velocityTracker == null) {
            return false;
        }
        velocityTracker.computeCurrentVelocity(1000, 1000.0f);
        float xVelocity = velocityTracker.getXVelocity();
        this.f.clear();
        this.f.recycle();
        this.f = null;
        return Math.abs(xVelocity) >= 200.0f;
    }

    private void c() {
        this.N = true;
        e0 = true;
        View view = this.s;
        if (view != null) {
            view.requestLayout();
            this.s.getViewTreeObserver().addOnGlobalLayoutListener(new a());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
    
        if (r0 != 3) goto L84;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b1 A[PHI: r10 r12
  0x00b1: PHI (r10v12 int) = (r10v11 int), (r10v19 int) binds: [B:46:0x00af, B:38:0x008c] A[DONT_GENERATE, DONT_INLINE]
  0x00b1: PHI (r12v1 int) = (r12v0 int), (r12v3 int) binds: [B:46:0x00af, B:38:0x008c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0133  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean e(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.e(android.view.MotionEvent):boolean");
    }

    private String c(String str) {
        if ("left".equals(str)) {
            return "right";
        }
        if ("right".equals(str)) {
            return "left";
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.N) {
            this.N = false;
            a(WXGesture.END, z, z ? "100" : "0");
        }
    }

    private void b() {
        this.N = true;
        e0 = true;
        if (this.s != null) {
            this.r.requestLayout();
            this.r.getViewTreeObserver().addOnGlobalLayoutListener(new f());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public int b(View view) {
        if (view == 0) {
            return 0;
        }
        if (view instanceof INativeView) {
            return ((INativeView) view).getStyleWidth();
        }
        return view.getWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void b(View view, int i) {
        if (view != 0) {
            if (view instanceof INativeView) {
                ((INativeView) view).setStyleLeft(i);
                return;
            }
            if (view.getLayoutParams() instanceof AbsoluteLayout.LayoutParams) {
                AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) view.getLayoutParams();
                layoutParams.height = view.getHeight();
                layoutParams.width = view.getWidth();
                ViewHelper.setX(view, i);
                view.requestLayout();
                return;
            }
            if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                ViewHelper.setX(view, i);
            }
        }
    }

    private void a(JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        if (jSONObject != null) {
            this.x = Integer.MAX_VALUE;
            this.y = null;
            this.z = null;
            this.D = "follow".equalsIgnoreCase(JSONUtil.getString(jSONObject, "moveMode"));
            if (jSONObject.has("over") && (jSONObject3 = JSONUtil.getJSONObject(jSONObject, "over")) != null) {
                if (jSONObject3.has("left")) {
                    this.x = PdrUtil.parseInt(JSONUtil.getString(jSONObject3, "left"), this.M, Integer.MAX_VALUE);
                }
                if (jSONObject3.has("action")) {
                    this.y = JSONUtil.getString(jSONObject3, "action");
                }
            }
            if (!jSONObject.has(WXPickersModule.CANCEL) || (jSONObject2 = JSONUtil.getJSONObject(jSONObject, WXPickersModule.CANCEL)) == null) {
                return;
            }
            if (jSONObject2.has("left")) {
                PdrUtil.parseInt(JSONUtil.getString(jSONObject2, "left"), this.M, Integer.MAX_VALUE);
            }
            if (jSONObject2.has("action")) {
                this.z = JSONUtil.getString(jSONObject2, "action");
            }
        }
    }

    private boolean a(View view, View view2) {
        if (view != null && view2 != null) {
            ViewParent parent = view2.getParent();
            for (ViewParent parent2 = view.getParent(); parent2 != null && parent2 != view2; parent2 = parent2.getParent()) {
                while (parent != null) {
                    if (parent == view) {
                        return false;
                    }
                    if (parent2 == parent) {
                        return true;
                    }
                    parent = parent.getParent();
                }
                parent = view2.getParent();
            }
            return false;
        }
        return false;
    }

    private void a() {
        this.N = true;
        e0 = true;
        this.r.requestLayout();
        this.r.getViewTreeObserver().addOnGlobalLayoutListener(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2) {
        if (this.N) {
            this.N = false;
            a(WXGesture.END, z, z2, z ? "100" : "0");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, int i) {
        io.dcloud.common.core.ui.b bVar;
        io.dcloud.common.core.ui.b bVar2;
        if (view != null) {
            if (view == this.r && (bVar2 = this.p) != null) {
                bVar2.obtainFrameOptions().left = i;
                this.p.obtainFrameOptions().checkValueIsPercentage("left", i, this.M, true, true);
            } else {
                if (view != this.s || (bVar = this.q) == null) {
                    return;
                }
                bVar.obtainFrameOptions().left = i;
                this.q.obtainFrameOptions().checkValueIsPercentage("left", i, this.M, true, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ValueAnimator a(View view, int i, int i2, boolean z) {
        return a(view, i, i2, z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ValueAnimator a(View view, int i, int i2, boolean z, boolean z2) {
        ValueAnimator valueAnimatorOfFloat = null;
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if ((view instanceof INativeView) || (layoutParams instanceof AbsoluteLayout.LayoutParams)) {
            valueAnimatorOfFloat = ValueAnimator.ofInt(i, i2);
        } else if (layoutParams instanceof FrameLayout.LayoutParams) {
            valueAnimatorOfFloat = ValueAnimator.ofFloat(i, i2);
        }
        valueAnimatorOfFloat.setDuration(Math.min(Math.max(new BigDecimal(450).multiply(new BigDecimal(Math.abs(i2 - i)).divide(new BigDecimal(this.M), 4, 4)).longValue(), 200L), 250L));
        valueAnimatorOfFloat.addListener(new d(z, z2, view, i2));
        valueAnimatorOfFloat.addUpdateListener(new e(view));
        return valueAnimatorOfFloat;
    }

    private void a(String str, boolean z, String str2) {
        a(str, z, false, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r12, boolean r13, boolean r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.g.a(java.lang.String, boolean, boolean, java.lang.String):void");
    }

    public View a(String str) {
        DragBean dragBean;
        IFrameView iFrameView;
        View viewObtainMainView;
        String strC = c(str);
        HashMap<String, DragBean> map = this.p.obtainFrameOptions().dragData;
        if (map == null || !map.containsKey(strC) || (dragBean = map.get(strC)) == null || (iFrameView = dragBean.dragBindWebView) == null || !"follow".equalsIgnoreCase(JSONUtil.getString(dragBean.dragBindViewOp, "moveMode")) || (viewObtainMainView = iFrameView.obtainMainView()) == null || viewObtainMainView.getVisibility() != 0 || viewObtainMainView.getWidth() >= this.M) {
            return null;
        }
        int iA = a(viewObtainMainView);
        int width = viewObtainMainView.getWidth() + iA;
        if ((iA < 0 || iA >= this.M) && (width <= 0 || width > this.M)) {
            return null;
        }
        return viewObtainMainView;
    }

    private float a(float f2) {
        if (!"bounce".equalsIgnoreCase(this.A) || 0.0f == f2) {
            return f2;
        }
        boolean z = f2 < 0.0f;
        float fFloatValue = new BigDecimal(f2).multiply(new BigDecimal(Math.abs(this.m - a(this.r))).divide(new BigDecimal(this.m - this.l), 4, 4)).floatValue();
        if (z) {
            fFloatValue = -fFloatValue;
        }
        return z ? Math.min(fFloatValue, -2.0f) : Math.max(fFloatValue, 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public int a(View view) {
        if (view == 0) {
            return 0;
        }
        if (view instanceof INativeView) {
            return ((INativeView) view).getStyleLeft();
        }
        if (view.getLayoutParams() instanceof AbsoluteLayout.LayoutParams) {
            return (int) ViewHelper.getX(view);
        }
        if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            return (int) ViewHelper.getX(view);
        }
        return 0;
    }

    private boolean a(io.dcloud.common.core.ui.b bVar) {
        if (bVar == null) {
            return true;
        }
        if (bVar != null && bVar.obtainWebView() == null) {
            return true;
        }
        if (bVar == null || bVar.obtainMainView() != null) {
            return bVar != null && bVar.obtainWebView() == null && bVar.obtainMainView() == null;
        }
        return true;
    }
}
