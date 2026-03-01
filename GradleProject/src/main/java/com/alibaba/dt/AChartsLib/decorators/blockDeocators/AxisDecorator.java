package com.alibaba.dt.AChartsLib.decorators.blockDeocators;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import supwisdom.bo;
import supwisdom.ck;
import supwisdom.cl;
import supwisdom.dl;
import supwisdom.ek;
import supwisdom.lk;
import supwisdom.mk;
import supwisdom.ok;
import supwisdom.pk;
import supwisdom.tn;
import supwisdom.vl;
import supwisdom.wl;
import supwisdom.wn;
import supwisdom.xl;
import supwisdom.yk;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class AxisDecorator extends BlockDecorator {
    public float m;
    public float n;
    public Paint o;
    public Paint p;
    public float q;
    public ck.a r;
    public Float[] s;
    public List<wl> t;
    public List<xl> u;

    public enum XAxisLocation {
        BOTTOM("BOTTOM", 0),
        TOP("TOP", 1),
        BOTTOM_BELOW("BOTTOM_BELOW", 2);

        public int _id;
        public String _name;

        XAxisLocation(String str, int i) {
            this._name = str;
            this._id = i;
        }

        public int getId() {
            return this._id;
        }

        public String getName() {
            return this._name;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this._name + "," + this._id;
        }
    }

    public enum YAxisLocation {
        LEFT("LEFT", 0),
        RIGHT("RIGHT", 1),
        RIGHT_EXTRA("RIGHT_EXTRA", 2);

        public int _id;
        public String _name;

        YAxisLocation(String str, int i) {
            this._name = str;
            this._id = i;
        }

        public int getId() {
            return this._id;
        }

        public String getName() {
            return this._name;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this._name + "," + this._id;
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1543a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[YAxisLocation.values().length];
            b = iArr;
            try {
                iArr[YAxisLocation.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[YAxisLocation.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[YAxisLocation.RIGHT_EXTRA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[XAxisLocation.values().length];
            f1543a = iArr2;
            try {
                iArr2[XAxisLocation.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1543a[XAxisLocation.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1543a[XAxisLocation.BOTTOM_BELOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public AxisDecorator(Chart chart) {
        super(chart);
        this.m = 10.0f;
        this.n = 10.0f;
        this.r = new ck.a();
        this.u = new ArrayList();
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (this.f9893a.getChartData() != null && canvas != null) {
            canvas.save();
            if (k()) {
                l();
            }
            b(canvas);
            canvas.restore();
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.vl b(supwisdom.ok r3) {
        /*
            r2 = this;
            com.alibaba.dt.AChartsLib.charts.Chart r0 = r2.f9893a
            supwisdom.nl r0 = r0.getChartConfig()
            java.util.List<supwisdom.wl> r0 = r0.f8538a
            r2.t = r0
            int[] r0 = com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.a.f1543a
            com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator$XAxisLocation r3 = r3.a()
            int r3 = r3.ordinal()
            r3 = r0[r3]
            r0 = 1
            if (r3 == r0) goto L43
            r0 = 2
            if (r3 == r0) goto L31
            r1 = 3
            if (r3 == r1) goto L20
            goto L54
        L20:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= r0) goto L54
            java.util.List<supwisdom.wl> r3 = r2.t
            java.lang.Object r3 = r3.get(r0)
            supwisdom.wl r3 = (supwisdom.wl) r3
            goto L55
        L31:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= 0) goto L54
            java.util.List<supwisdom.wl> r3 = r2.t
            r0 = 0
            java.lang.Object r3 = r3.get(r0)
            supwisdom.wl r3 = (supwisdom.wl) r3
            goto L55
        L43:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= r0) goto L54
            java.util.List<supwisdom.wl> r3 = r2.t
            java.lang.Object r3 = r3.get(r0)
            supwisdom.wl r3 = (supwisdom.wl) r3
            goto L55
        L54:
            r3 = 0
        L55:
            if (r3 == 0) goto L86
            java.lang.String r0 = r3.f
            if (r0 != 0) goto L67
            android.graphics.Paint r0 = r2.p
            java.lang.String r1 = r3.c
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setColor(r1)
            goto L70
        L67:
            android.graphics.Paint r1 = r2.p
            int r0 = android.graphics.Color.parseColor(r0)
            r1.setColor(r0)
        L70:
            java.lang.Float r0 = r3.g
            if (r0 != 0) goto L7d
            android.graphics.Paint r0 = r2.p
            int r1 = r3.b
            float r1 = (float) r1
            r0.setTextSize(r1)
            goto L86
        L7d:
            android.graphics.Paint r1 = r2.p
            float r0 = r0.floatValue()
            r1.setTextSize(r0)
        L86:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.b(supwisdom.ok):supwisdom.vl");
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(2.0f);
        Paint paint = new Paint();
        this.o = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.o.setTextSize(27.0f);
        this.o.setColor(Color.parseColor(AMapUtil.HtmlGray));
        this.o.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.p = paint2;
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        this.p.setTextSize(27.0f);
        this.p.setAntiAlias(true);
        this.p.setColor(Color.parseColor(AMapUtil.HtmlGray));
        float f = tn.a(this.f9893a.getContext()).density;
        this.q = f;
        this.m = f * this.m;
    }

    public void l() {
        Iterator it;
        Float fValueOf;
        Float f;
        dl dlVarM = this.f9893a.getChartStrategy().m();
        ek chartData = this.f9893a.getChartData();
        bo viewportHandler = this.f9893a.getViewportHandler();
        viewportHandler.b(viewportHandler.c() - this.i);
        viewportHandler.d(viewportHandler.e() - this.k);
        viewportHandler.c(viewportHandler.d() - this.j);
        viewportHandler.a(viewportHandler.b() - this.l);
        viewportHandler.a();
        Float fValueOf2 = Float.valueOf(0.0f);
        this.i = 0.0f;
        this.k = 0.0f;
        this.j = 0.0f;
        this.l = 0.0f;
        float f2 = this.q * 2.0f;
        List listF = chartData.f();
        List listG = chartData.g();
        boolean z = ((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.VERTICAL;
        Iterator it2 = listF.iterator();
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            Float fValueOf3 = null;
            if (!it2.hasNext()) {
                break;
            }
            ok okVar = (ok) it2.next();
            b(okVar);
            wl wlVarA = a(okVar);
            if (!wlVarA.f8298a) {
                Paint.FontMetrics fontMetrics = this.p.getFontMetrics();
                float f3 = fontMetrics.bottom - fontMetrics.top;
                List listD = okVar.d();
                if (wlVarA.i) {
                    Iterator it3 = listD.iterator();
                    while (it3.hasNext()) {
                        Float f4 = fValueOf2;
                        float fMeasureText = this.p.measureText(a(okVar).a().a((String) ((yk) it3.next()).b()));
                        if (fValueOf3 == null) {
                            fValueOf3 = Float.valueOf(fMeasureText);
                        } else if (fValueOf3.floatValue() < fMeasureText) {
                            fValueOf3 = Float.valueOf(fMeasureText);
                        }
                        fValueOf2 = f4;
                    }
                    f = fValueOf2;
                } else {
                    f = fValueOf2;
                    fValueOf3 = f;
                    f3 = 0.0f;
                }
                Float f5 = wlVarA.h;
                float fFloatValue = f5 != null ? f5.floatValue() * this.q : 0.0f;
                int i = a.f1543a[okVar.a().ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3 && !z4) {
                            if (z) {
                                this.l = this.l + (f3 * 1.0f) + fFloatValue;
                            } else {
                                this.i = this.i + fValueOf3.floatValue() + this.m + fFloatValue;
                            }
                            z4 = true;
                        }
                    } else if (!z3) {
                        if (z) {
                            this.l = (f3 + fFloatValue) * 1.0f;
                        } else {
                            this.i = this.i + fValueOf3.floatValue() + this.m + fFloatValue;
                        }
                        z3 = true;
                    }
                } else if (!z2) {
                    if (z) {
                        this.k = (f3 * 1.0f) + fFloatValue;
                    } else {
                        this.j = (fValueOf3.floatValue() * 1.0f) + fFloatValue;
                    }
                    z2 = true;
                }
                fValueOf2 = f;
            }
        }
        Float f6 = fValueOf2;
        Iterator it4 = listG.iterator();
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (it4.hasNext()) {
            pk pkVar = (pk) it4.next();
            xl xlVarA = a(pkVar);
            if (!xlVarA.f8298a) {
                this.s = wn.a(this.f9893a, pkVar.b());
                Paint.FontMetrics fontMetrics2 = this.o.getFontMetrics();
                float f7 = fontMetrics2.bottom - fontMetrics2.top;
                float[] fArrA = wn.a(this.s[0].floatValue(), this.s[1].floatValue(), (int) (this.f9893a.getCurrentScaleY() * 5.0f));
                Float f8 = xlVarA.h;
                float fFloatValue2 = f8 != null ? f8.floatValue() * this.q : 0.0f;
                Float f9 = xlVarA.g;
                if (f9 == null) {
                    this.o.setTextSize(xlVarA.b);
                } else {
                    this.o.setTextSize(f9.floatValue());
                }
                if (xlVarA.i) {
                    int length = fArrA.length;
                    fValueOf = null;
                    int i2 = 0;
                    while (i2 < length) {
                        Iterator it5 = it4;
                        int i3 = length;
                        float fMeasureText2 = this.o.measureText(a(pkVar).a().a(Float.valueOf(fArrA[i2])));
                        if (fValueOf == null) {
                            fValueOf = Float.valueOf(fMeasureText2);
                        } else if (fValueOf.floatValue() < fMeasureText2) {
                            fValueOf = Float.valueOf(fMeasureText2);
                        }
                        i2++;
                        length = i3;
                        it4 = it5;
                    }
                    it = it4;
                } else {
                    it = it4;
                    fValueOf = f6;
                    f7 = 0.0f;
                }
                int i4 = a.b[pkVar.b().ordinal()];
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 == 3 && !z6) {
                            if (z) {
                                this.j = this.j + fValueOf.floatValue() + this.m + fFloatValue2;
                            } else {
                                this.k = this.k + f7 + this.m + fFloatValue2;
                            }
                            z6 = true;
                        }
                    } else if (!z5) {
                        if (z) {
                            this.j = this.j + fValueOf.floatValue() + this.m + fFloatValue2;
                        } else {
                            this.k = this.k + f7 + this.m + fFloatValue2;
                        }
                        z5 = true;
                    }
                } else if (!z7) {
                    if (z) {
                        this.i = fValueOf.floatValue() + this.m + fFloatValue2;
                    } else {
                        this.l = f7 + this.m + fFloatValue2;
                    }
                    z7 = true;
                }
                it4 = it;
            }
        }
        if (this.i == 0.0f) {
            this.i = f2;
        }
        if (this.k == 0.0f) {
            this.k = f2;
        }
        if (this.j == 0.0f) {
            this.j = f2;
        }
        if (this.l == 0.0f) {
            this.l = f2;
        }
        b(this.i, this.k, this.j, this.l);
        dlVarM.b(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.wl a(supwisdom.ok r3) {
        /*
            r2 = this;
            com.alibaba.dt.AChartsLib.charts.Chart r0 = r2.f9893a
            supwisdom.nl r0 = r0.getChartConfig()
            java.util.List<supwisdom.wl> r0 = r0.f8538a
            r2.t = r0
            int[] r0 = com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.a.f1543a
            com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator$XAxisLocation r3 = r3.a()
            int r3 = r3.ordinal()
            r3 = r0[r3]
            r0 = 1
            if (r3 == r0) goto L43
            r0 = 2
            if (r3 == r0) goto L31
            r1 = 3
            if (r3 == r1) goto L20
            goto L54
        L20:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= r0) goto L54
            java.util.List<supwisdom.wl> r3 = r2.t
            java.lang.Object r3 = r3.get(r0)
            supwisdom.wl r3 = (supwisdom.wl) r3
            goto L55
        L31:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= 0) goto L54
            java.util.List<supwisdom.wl> r3 = r2.t
            r0 = 0
            java.lang.Object r3 = r3.get(r0)
            supwisdom.wl r3 = (supwisdom.wl) r3
            goto L55
        L43:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= r0) goto L54
            java.util.List<supwisdom.wl> r3 = r2.t
            java.lang.Object r3 = r3.get(r0)
            supwisdom.wl r3 = (supwisdom.wl) r3
            goto L55
        L54:
            r3 = 0
        L55:
            if (r3 == 0) goto L58
            return r3
        L58:
            supwisdom.wl r3 = new supwisdom.wl
            r3.<init>()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.a(supwisdom.ok):supwisdom.wl");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.vl b(supwisdom.pk r3) {
        /*
            r2 = this;
            com.alibaba.dt.AChartsLib.charts.Chart r0 = r2.f9893a
            supwisdom.nl r0 = r0.getChartConfig()
            java.util.List<supwisdom.xl> r0 = r0.b
            r2.u = r0
            int[] r0 = com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.a.b
            com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator$YAxisLocation r3 = r3.b()
            int r3 = r3.ordinal()
            r3 = r0[r3]
            r0 = 1
            if (r3 == r0) goto L42
            r1 = 2
            if (r3 == r1) goto L31
            r0 = 3
            if (r3 == r0) goto L20
            goto L54
        L20:
            java.util.List<supwisdom.xl> r3 = r2.u
            int r3 = r3.size()
            if (r3 <= r1) goto L54
            java.util.List<supwisdom.xl> r3 = r2.u
            java.lang.Object r3 = r3.get(r1)
            supwisdom.xl r3 = (supwisdom.xl) r3
            goto L55
        L31:
            java.util.List<supwisdom.xl> r3 = r2.u
            int r3 = r3.size()
            if (r3 <= r0) goto L54
            java.util.List<supwisdom.xl> r3 = r2.u
            java.lang.Object r3 = r3.get(r0)
            supwisdom.xl r3 = (supwisdom.xl) r3
            goto L55
        L42:
            java.util.List<supwisdom.xl> r3 = r2.u
            int r3 = r3.size()
            if (r3 <= 0) goto L54
            java.util.List<supwisdom.xl> r3 = r2.u
            r0 = 0
            java.lang.Object r3 = r3.get(r0)
            supwisdom.xl r3 = (supwisdom.xl) r3
            goto L55
        L54:
            r3 = 0
        L55:
            if (r3 == 0) goto L86
            java.lang.String r0 = r3.f
            if (r0 != 0) goto L67
            android.graphics.Paint r0 = r2.o
            java.lang.String r1 = r3.c
            int r1 = android.graphics.Color.parseColor(r1)
            r0.setColor(r1)
            goto L70
        L67:
            android.graphics.Paint r1 = r2.o
            int r0 = android.graphics.Color.parseColor(r0)
            r1.setColor(r0)
        L70:
            java.lang.Float r0 = r3.g
            if (r0 != 0) goto L7d
            android.graphics.Paint r0 = r2.o
            int r1 = r3.b
            float r1 = (float) r1
            r0.setTextSize(r1)
            goto L86
        L7d:
            android.graphics.Paint r1 = r2.o
            float r0 = r0.floatValue()
            r1.setTextSize(r0)
        L86:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.b(supwisdom.pk):supwisdom.vl");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.xl a(supwisdom.pk r3) {
        /*
            r2 = this;
            com.alibaba.dt.AChartsLib.charts.Chart r0 = r2.f9893a
            supwisdom.nl r0 = r0.getChartConfig()
            java.util.List<supwisdom.xl> r0 = r0.b
            r2.u = r0
            int[] r0 = com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.a.b
            com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator$YAxisLocation r3 = r3.b()
            int r3 = r3.ordinal()
            r3 = r0[r3]
            r0 = 1
            if (r3 == r0) goto L42
            r1 = 2
            if (r3 == r1) goto L31
            r0 = 3
            if (r3 == r0) goto L20
            goto L54
        L20:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= r1) goto L54
            java.util.List<supwisdom.xl> r3 = r2.u
            java.lang.Object r3 = r3.get(r1)
            supwisdom.xl r3 = (supwisdom.xl) r3
            goto L55
        L31:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= r0) goto L54
            java.util.List<supwisdom.xl> r3 = r2.u
            java.lang.Object r3 = r3.get(r0)
            supwisdom.xl r3 = (supwisdom.xl) r3
            goto L55
        L42:
            java.util.List<supwisdom.wl> r3 = r2.t
            int r3 = r3.size()
            if (r3 <= 0) goto L54
            java.util.List<supwisdom.xl> r3 = r2.u
            r0 = 0
            java.lang.Object r3 = r3.get(r0)
            supwisdom.xl r3 = (supwisdom.xl) r3
            goto L55
        L54:
            r3 = 0
        L55:
            if (r3 == 0) goto L58
            return r3
        L58:
            supwisdom.xl r3 = new supwisdom.xl
            r3.<init>()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.a(supwisdom.pk):supwisdom.xl");
    }

    public final Path a(ok okVar, pk pkVar) {
        ek chartData = this.f9893a.getChartData();
        Path path = new Path();
        int i = a.f1543a[okVar.a().ordinal()];
        if (i == 1) {
            path.moveTo((float) chartData.d(), (float) chartData.c());
            path.lineTo((float) chartData.a(), (float) chartData.c());
        } else if (i == 2 || i == 3) {
            path.moveTo((float) chartData.d(), (float) chartData.e());
            path.lineTo((float) chartData.a(), (float) chartData.e());
        }
        return path;
    }

    public final void b(Canvas canvas) {
        Float f;
        String str;
        Float f2;
        String str2;
        ek chartData = this.f9893a.getChartData();
        List<ok> listF = chartData.f();
        List<pk> listG = chartData.g();
        HashSet hashSet = new HashSet();
        for (ok okVar : listF) {
            for (pk pkVar : listG) {
                if (pkVar.a() == okVar.a()) {
                    if (!hashSet.contains(pkVar.a().toString() + pkVar.b())) {
                        hashSet.add(pkVar.a().toString() + pkVar.b());
                        vl vlVarB = b(okVar);
                        vl vlVarB2 = b(pkVar);
                        if (!vlVarB.f8298a) {
                            Path pathA = a(okVar, pkVar);
                            this.f9893a.getTransformUtil().a(pathA, -1);
                            if (vlVarB != null && (str2 = vlVarB.d) != null) {
                                this.d.setColor(Color.parseColor(str2));
                            } else {
                                this.d.setColor(Color.parseColor("#e3e3e3"));
                            }
                            if (vlVarB != null && (f2 = vlVarB.f9518e) != null) {
                                this.d.setStrokeWidth(f2.floatValue());
                            } else {
                                this.d.setStrokeWidth(2.0f);
                            }
                            canvas.drawPath(pathA, this.d);
                        }
                        if (!vlVarB2.f8298a) {
                            Path pathB = b(okVar, pkVar);
                            this.f9893a.getTransformUtil().a(pathB, -1);
                            if (vlVarB2 != null && (str = vlVarB2.d) != null) {
                                this.d.setColor(Color.parseColor(str));
                            } else {
                                this.d.setColor(Color.parseColor("#e3e3e3"));
                            }
                            if (vlVarB2 != null && (f = vlVarB2.f9518e) != null) {
                                this.d.setStrokeWidth(f.floatValue());
                            } else {
                                this.d.setStrokeWidth(2.0f);
                            }
                            canvas.drawPath(pathB, this.d);
                        }
                        a(okVar, pkVar, canvas);
                    }
                }
            }
        }
    }

    public void a(ok okVar, pk pkVar, Canvas canvas) {
        float[] fArrA;
        if (this.s == null) {
            this.s = new Float[2];
        }
        List listD = okVar.d();
        Float[] fArrA2 = wn.a(this.f9893a, pkVar.b());
        this.s = fArrA2;
        float[] fArrA3 = wn.a(fArrA2[0].floatValue(), this.s[1].floatValue(), (int) (this.f9893a.getCurrentScaleY() * 5.0f));
        float[] fArr = new float[listD.size()];
        if (this.f9893a.getChartData() instanceof mk) {
            fArrA = wn.a((float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), okVar.d().size() - 1);
        } else {
            for (int iB = (int) okVar.b(); iB < listD.size(); iB++) {
                fArr[iB] = ((yk) listD.get(iB)).a();
            }
            fArrA = fArr;
        }
        vl vlVarB = b(okVar);
        if (!b(pkVar).f8298a) {
            a(fArrA, fArrA3, pkVar, canvas);
        }
        if (vlVarB.f8298a) {
            return;
        }
        a(fArrA, fArrA3, okVar, pkVar, canvas);
    }

    public void a(float[] fArr, float[] fArr2, pk pkVar, Canvas canvas) {
        float[] fArrB;
        float f;
        float f2;
        float f3 = fArr[0];
        int i = 1;
        float f4 = fArr[fArr.length - 1];
        int i2 = 3;
        if (this.r.b() == null) {
            int length = fArr2.length * 2;
            float[] fArr3 = new float[length];
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                int i5 = a.b[pkVar.b().ordinal()];
                if (i5 != 1) {
                    if (i5 == 2 || i5 == 3) {
                        if (j()) {
                            if (this.f9893a.getChartData() instanceof lk) {
                                fArr3[i3] = f3 - ((float) ((lk) this.f9893a.getChartData()).j());
                            } else {
                                fArr3[i3] = f3;
                            }
                        } else if (this.f9893a.getChartData() instanceof lk) {
                            fArr3[i3] = ((float) ((lk) this.f9893a.getChartData()).j()) + f4;
                        } else {
                            fArr3[i3] = f4;
                        }
                    }
                } else if (j()) {
                    if (this.f9893a.getChartData() instanceof lk) {
                        fArr3[i3] = ((float) ((lk) this.f9893a.getChartData()).j()) + f4;
                    } else {
                        fArr3[i3] = f4;
                    }
                } else if (this.f9893a.getChartData() instanceof lk) {
                    fArr3[i3] = f3 - ((float) ((lk) this.f9893a.getChartData()).j());
                } else {
                    fArr3[i3] = f3;
                }
                int i6 = i3 + 1;
                fArr3[i6] = fArr2[i4];
                i4++;
                i3 = i6 + 1;
            }
            this.f9893a.getTransformUtil().a(fArr3, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), this.s[0].floatValue(), this.s[1].floatValue(), 3);
            fArrB = fArr3;
        } else {
            fArrB = this.r.b();
        }
        Paint.FontMetrics fontMetrics = this.o.getFontMetrics();
        float f5 = (fontMetrics.top - fontMetrics.bottom) / 3.0f;
        if (((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL) {
            this.f9893a.getTransformUtil().b(fArrB);
        }
        float[] fArrA = wn.a(this.s[0].floatValue(), this.s[1].floatValue(), (int) (this.f9893a.getCurrentScaleY() * 5.0f));
        xl xlVarA = a(pkVar);
        Float f6 = xlVarA.h;
        float fFloatValue = f6 != null ? f6.floatValue() * this.q : 0.0f;
        int i7 = 0;
        float f7 = 0.0f;
        float f8 = 0.0f;
        boolean z = false;
        float f9 = 0.0f;
        while (i7 < fArr2.length) {
            String strA = xlVarA.a().a(Float.valueOf(fArrA[i7]));
            float fMeasureText = this.o.measureText(strA);
            int i8 = a.b[pkVar.b().ordinal()];
            if (i8 != i) {
                if (i8 != 2) {
                    if (i8 == i2) {
                        if (j()) {
                            this.o.setTextAlign(Paint.Align.LEFT);
                            int i9 = i7 * 2;
                            f7 = fArrB[i9];
                            f9 = fArrB[i9 + i] + (2.5f * f5);
                        } else {
                            this.o.setTextAlign(Paint.Align.LEFT);
                            int i10 = i7 * 2;
                            f7 = fArrB[i10] + (this.j / 2.0f) + this.m + fFloatValue;
                            f2 = fArrB[i10 + i];
                            f = f2 - f5;
                        }
                    }
                    f = f9;
                } else if (j()) {
                    this.o.setTextAlign(Paint.Align.CENTER);
                    int i11 = i7 * 2;
                    f7 = fArrB[i11];
                    f = (fArrB[i11 + i] + (f5 / 2.0f)) - fFloatValue;
                } else {
                    this.o.setTextAlign(Paint.Align.LEFT);
                    int i12 = i7 * 2;
                    f7 = fArrB[i12] + this.m + fFloatValue;
                    f2 = fArrB[i12 + i];
                    f = f2 - f5;
                }
            } else if (j()) {
                this.o.setTextAlign(Paint.Align.CENTER);
                int i13 = i7 * 2;
                f7 = fArrB[i13];
                f = (fArrB[i13 + i] - (f5 * 3.0f)) + fFloatValue;
            } else {
                this.o.setTextAlign(Paint.Align.RIGHT);
                int i14 = i7 * 2;
                f7 = fArrB[i14] - fFloatValue;
                f = fArrB[i14 + i] - f5;
            }
            if (i7 > 0 && j()) {
                if (((f7 - (fMeasureText / 2.0f)) - f8) - this.n < 0.0f) {
                    z = true;
                }
            }
            if (z) {
                z = false;
            } else {
                if (j()) {
                    f8 = f7 + (fMeasureText / 2.0f);
                }
                if (xlVarA.i) {
                    canvas.drawText(strA, f7, f, this.o);
                }
            }
            i7++;
            f9 = f;
            i = 1;
            i2 = 3;
        }
    }

    public final Path b(ok okVar, pk pkVar) {
        ek chartData = this.f9893a.getChartData();
        Path path = new Path();
        int i = a.b[pkVar.b().ordinal()];
        if (i == 1) {
            path.moveTo((float) chartData.d(), (float) chartData.e());
            path.lineTo((float) chartData.d(), (float) chartData.c());
        } else if (i == 2 || i == 3) {
            path.moveTo((float) chartData.a(), (float) chartData.e());
            path.lineTo((float) chartData.a(), (float) chartData.c());
        }
        return path;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(float[] r22, float[] r23, supwisdom.ok r24, supwisdom.pk r25, android.graphics.Canvas r26) {
        /*
            Method dump skipped, instruction units count: 568
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator.a(float[], float[], supwisdom.ok, supwisdom.pk, android.graphics.Canvas):void");
    }
}
