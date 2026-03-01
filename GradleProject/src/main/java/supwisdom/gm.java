package supwisdom;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.math.ec.rfc7748.X25519Field;

/* JADX INFO: compiled from: LineDataDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class gm extends am {
    public hk n;
    public un o;
    public RectF p;
    public boolean q;

    public gm(Chart chart) {
        super(chart);
        this.q = true;
        new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        canvas.save();
        canvas.clipRect(this.f9893a.getViewportHandler().c(), this.f9893a.getViewportHandler().e(), this.f9893a.getContentWidth() - this.f9893a.getViewportHandler().d(), this.f9893a.getContentHeight() - this.f9893a.getViewportHandler().b());
        c(canvas);
        b(canvas);
        canvas.restore();
        return this;
    }

    public void c(Canvas canvas) {
        if (this.q) {
            this.n = (hk) this.f9893a.getChartData();
        }
        if (this.n == null) {
            return;
        }
        bo viewportHandler = this.f9893a.getViewportHandler();
        RectF rectF = new RectF(viewportHandler.c(), viewportHandler.e(), viewportHandler.d(), viewportHandler.b());
        this.p = rectF;
        rectF.right = this.f9893a.getContentWidth() - viewportHandler.d();
        this.p.bottom = this.f9893a.getContentHeight() - viewportHandler.b();
        this.p.left = viewportHandler.c();
        this.p.top = viewportHandler.e();
        d(canvas);
    }

    public final void d(Canvas canvas) {
        List<ok> listF = this.n.f();
        List<vk> listG = this.n.g();
        if (listF == null && listG == null) {
            return;
        }
        Path path = null;
        for (ok okVar : listF) {
            this.d.reset();
            Path path2 = path;
            for (vk vkVar : listG) {
                Float[] fArrA = wn.a(this.f9893a, vkVar.b());
                if (vkVar.a() == okVar.a() && !on.a(vkVar.g())) {
                    Path path3 = new Path();
                    ArrayList arrayList = new ArrayList();
                    if (vkVar.C) {
                        a(okVar, vkVar, path3, arrayList, fArrA);
                    } else {
                        a(okVar, vkVar, path3, fArrA);
                    }
                    if (j()) {
                        this.f9893a.getTransformUtil().a(path3);
                    }
                    vkVar.a(path3);
                    if (vkVar.B) {
                        this.d.setPathEffect(new DashPathEffect(new float[]{15.0f, 6.0f}, 0.0f));
                    } else {
                        this.d.setPathEffect(null);
                    }
                    if (vkVar.D) {
                        a(canvas, path3, path2, okVar, vkVar);
                        path2 = path3;
                    }
                    this.d.setStyle(Paint.Style.STROKE);
                    if (vkVar.c() == null || !vkVar.c().f8401a.equals("lineargradient")) {
                        this.d.setShader(null);
                        this.d.setColor(vkVar.d());
                    } else {
                        this.d.setShader(new LinearGradient(this.p.left + (vkVar.c().b[0] * this.p.width()), this.p.top + (vkVar.c().b[1] * this.p.height()), this.p.left + (vkVar.c().c[0] * this.p.width()), this.p.top + (vkVar.c().c[1] * this.p.height()), vkVar.c().d, vkVar.c().f8402e, Shader.TileMode.CLAMP));
                    }
                    this.d.setStrokeWidth(vkVar.n());
                    canvas.drawPath(path3, this.d);
                    this.d.reset();
                    vkVar.a(false);
                }
            }
            path = path2;
        }
    }

    @Override // supwisdom.am, com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
        this.o = new un(this.f9893a);
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(2.0f);
    }

    public void a(boolean z) {
        this.q = z;
    }

    public void a(hk hkVar) {
        this.n = hkVar;
    }

    public final void a(ok okVar, vk vkVar, Path path, List<float[]> list, Float[] fArr) {
        ArrayList<List> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        int i2 = 0;
        boolean z = false;
        for (Double d : vkVar.g()) {
            if (d != null) {
                if (arrayList3.size() == 0) {
                    if (z) {
                        float[] fArr2 = {Float.valueOf(i).floatValue(), d.floatValue()};
                        this.f9893a.getTransformUtil().a(fArr2, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArr[0].floatValue(), fArr[1].floatValue());
                        list.add(fArr2);
                    }
                    i2 = i;
                }
                arrayList3.add(d);
                z = false;
            } else {
                if (arrayList3.size() != 0) {
                    ArrayList arrayList4 = new ArrayList();
                    arrayList4.addAll(arrayList3);
                    arrayList.add(arrayList4);
                    ArrayList arrayList5 = new ArrayList();
                    arrayList5.addAll(okVar.c().subList(i2, i));
                    arrayList2.add(arrayList5);
                    arrayList3.clear();
                }
                z = true;
            }
            i++;
        }
        if (arrayList3.size() != 0) {
            ArrayList arrayList6 = new ArrayList();
            arrayList6.addAll(arrayList3);
            arrayList.add(arrayList6);
            ArrayList arrayList7 = new ArrayList();
            if (okVar.c().size() > i) {
                arrayList7.addAll(okVar.c().subList(i2, i));
            } else {
                arrayList7.addAll(okVar.c().subList(i2, okVar.c().size()));
            }
            arrayList2.add(arrayList7);
        }
        if (arrayList.size() == 0) {
            this.o.a(okVar, vkVar, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArr[0].floatValue(), fArr[1].floatValue());
            return;
        }
        int i3 = 0;
        for (List list2 : arrayList) {
            float[] fArr3 = {0.0f, 0.0f};
            this.f9893a.getTransformUtil().a(fArr3, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArr[0].floatValue(), fArr[1].floatValue());
            Path pathA = this.o.a(new ok((List) arrayList2.get(i3)), new pk(list2), (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArr[0].floatValue(), fArr[1].floatValue());
            if (arrayList.size() > list.size()) {
                if (i3 != 0 && list.size() > 0) {
                    pathA.offset(list.get(i3 - 1)[0] - fArr3[0], 0.0f);
                }
            } else if (list.size() > 0) {
                pathA.offset(list.get(i3)[0] - fArr3[0], 0.0f);
            }
            i3++;
            path.addPath(pathA);
        }
    }

    public final void a(ok okVar, vk vkVar, Path path, Float[] fArr) {
        Path path2 = new Path();
        List<yk<Double>> listI = vkVar.i();
        boolean z = false;
        for (int iB = (int) okVar.b(); iB < okVar.d().size(); iB++) {
            if (iB < listI.size()) {
                if (listI.get(iB).b() == null) {
                    path.addPath(path2);
                    path2.reset();
                    z = true;
                } else if (iB != ((int) okVar.b()) && !z) {
                    path2.lineTo(iB, listI.get(iB).b().floatValue());
                } else {
                    path2.moveTo(iB, listI.get(iB).b().floatValue());
                    z = false;
                }
            }
        }
        path.addPath(path2);
        this.f9893a.getTransformUtil().a(path, (float) this.f9893a.getChartData().d(), (float) this.f9893a.getChartData().a(), fArr[0].floatValue(), fArr[1].floatValue());
        vkVar.a(path);
    }

    @TargetApi(19)
    public final void a(Canvas canvas, Path path, Path path2, ok okVar, vk vkVar) {
        Path path3 = new Path();
        path3.addPath(path);
        List listD = okVar.d();
        float[] fArr = {((yk) listD.get(listD.size() - 1)).a(), 0.0f, ((yk) listD.get(0)).a(), 0.0f};
        this.f9893a.getTransformUtil().d(fArr);
        if (j()) {
            this.f9893a.getTransformUtil().b(fArr);
        }
        path3.lineTo(fArr[0], fArr[1]);
        path3.lineTo(fArr[2], fArr[3]);
        path3.close();
        vkVar.b(path3);
        this.d.setStyle(Paint.Style.FILL);
        if (vkVar.m() != null && vkVar.m().f8401a.equals("lineargradient")) {
            this.d.setShader(new LinearGradient(this.p.left + (vkVar.m().b[0] * this.p.width()), this.p.top + (vkVar.m().b[1] * this.p.height()), this.p.left + (vkVar.m().c[0] * this.p.width()), this.p.top + (vkVar.m().c[1] * this.p.height()), vkVar.m().d, vkVar.m().f8402e, Shader.TileMode.CLAMP));
        } else {
            this.d.setShader(null);
            this.d.setColor(vkVar.l());
        }
        if (!vkVar.D) {
            this.d.setColor(X25519Field.M24);
        }
        canvas.drawPath(path3, this.d);
        this.d.setAlpha(255);
    }
}
