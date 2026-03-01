package supwisdom;

import android.graphics.Matrix;
import android.graphics.Path;
import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.List;

/* JADX INFO: compiled from: TransformUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class zn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Matrix f10015a = new Matrix();
    public Chart b;

    public zn(Chart chart) {
        this.b = null;
        this.b = chart;
    }

    public void a() {
        ek chartData = this.b.getChartData();
        if (chartData == null) {
            return;
        }
        float fD = (float) chartData.d();
        float fA = (float) chartData.a();
        float fE = (float) chartData.e();
        float fC = (float) chartData.c();
        float fC2 = this.b.getViewportHandler().c();
        float fB = this.b.getViewportHandler().b();
        bo viewportHandler = this.b.getViewportHandler();
        float contentWidth = ((this.b.getContentWidth() - viewportHandler.d()) - viewportHandler.c()) / (fA - fD);
        float contentHeight = ((this.b.getContentHeight() - viewportHandler.b()) - viewportHandler.e()) / (fC - fE);
        this.f10015a.postTranslate(-fD, -fE);
        this.f10015a.postScale(contentWidth, -contentHeight);
        this.f10015a.postTranslate(0.0f, this.b.getContentHeight());
        b(-1);
        this.f10015a.postTranslate(fC2, -fB);
    }

    public void b(Path path) {
        a();
        path.transform(this.f10015a);
        this.f10015a.reset();
    }

    public void c(float[] fArr) {
        a();
        this.f10015a.mapPoints(fArr);
        this.f10015a.reset();
    }

    public void d(float[] fArr) {
        a();
        this.f10015a.mapPoints(fArr);
        this.f10015a.reset();
    }

    public void b(float[] fArr) {
        bo viewportHandler = this.b.getViewportHandler();
        float contentWidth = (this.b.getContentWidth() - viewportHandler.d()) - viewportHandler.c();
        float contentHeight = (this.b.getContentHeight() - viewportHandler.b()) - viewportHandler.e();
        float contentWidth2 = (this.b.getContentWidth() - (contentWidth / 2.0f)) - viewportHandler.d();
        float contentHeight2 = (this.b.getContentHeight() - (contentHeight / 2.0f)) - viewportHandler.b();
        Matrix matrix = new Matrix();
        matrix.preRotate(90.0f, contentWidth2, contentHeight2);
        matrix.postScale(contentWidth / contentHeight, contentHeight / contentWidth, contentWidth2, contentHeight2);
        matrix.mapPoints(fArr);
    }

    public void b(int i) {
        Matrix scaleAndTransMatrix = this.b.getScaleAndTransMatrix();
        float[] fArr = new float[9];
        scaleAndTransMatrix.getValues(fArr);
        if (i == 1 || i == 2) {
            this.f10015a.postScale(fArr[0], 1.0f);
            this.f10015a.postTranslate(fArr[2], 0.0f);
        } else if (i != 3 && i != 4) {
            this.f10015a.postConcat(scaleAndTransMatrix);
        } else {
            this.f10015a.postScale(1.0f, fArr[4]);
            this.f10015a.postTranslate(0.0f, fArr[5]);
        }
    }

    public void a(int i) {
        ek chartData = this.b.getChartData();
        if (chartData == null) {
            return;
        }
        float fD = (float) chartData.d();
        float fA = (float) chartData.a();
        float fE = (float) chartData.e();
        float fC = (float) chartData.c();
        float fC2 = this.b.getViewportHandler().c();
        float fB = this.b.getViewportHandler().b();
        bo viewportHandler = this.b.getViewportHandler();
        float contentWidth = ((this.b.getContentWidth() - viewportHandler.d()) - viewportHandler.c()) / (fA - fD);
        float contentHeight = ((this.b.getContentHeight() - viewportHandler.b()) - viewportHandler.e()) / (fC - fE);
        this.f10015a.postTranslate(-fD, -fE);
        this.f10015a.postScale(contentWidth, -contentHeight);
        this.f10015a.postTranslate(0.0f, this.b.getContentHeight());
        b(i);
        this.f10015a.postTranslate(fC2, -fB);
    }

    public void a(float f, float f2, float f3, float f4, int i) {
        float fC = this.b.getViewportHandler().c();
        float fB = this.b.getViewportHandler().b();
        bo viewportHandler = this.b.getViewportHandler();
        float contentWidth = ((this.b.getContentWidth() - viewportHandler.d()) - viewportHandler.c()) / (f2 - f);
        float contentHeight = ((this.b.getContentHeight() - viewportHandler.b()) - viewportHandler.e()) / (f4 - f3);
        this.f10015a.postTranslate(-f, -f3);
        this.f10015a.postScale(contentWidth, -contentHeight);
        this.f10015a.postTranslate(0.0f, this.b.getContentHeight());
        b(i);
        this.f10015a.postTranslate(fC, -fB);
    }

    public void a(Path path, int i) {
        a(i);
        path.transform(this.f10015a);
        this.f10015a.reset();
    }

    public void a(Path path, float f, float f2, float f3, float f4) {
        a(f, f2, f3, f4, -1);
        path.transform(this.f10015a);
        this.f10015a.reset();
    }

    public void a(float[] fArr, float f, float f2, float f3, float f4) {
        a(f, f2, f3, f4, -1);
        this.f10015a.mapPoints(fArr);
        this.f10015a.reset();
    }

    public void a(float[] fArr, float f, float f2, float f3, float f4, int i) {
        a(f, f2, f3, f4, i);
        this.f10015a.mapPoints(fArr);
        this.f10015a.reset();
    }

    public void a(float[] fArr) {
        a();
        Matrix matrix = new Matrix();
        this.f10015a.invert(matrix);
        matrix.mapPoints(fArr);
        this.f10015a.reset();
    }

    public void a(Path path) {
        bo viewportHandler = this.b.getViewportHandler();
        float contentWidth = (this.b.getContentWidth() - viewportHandler.d()) - viewportHandler.c();
        float contentHeight = (this.b.getContentHeight() - viewportHandler.b()) - viewportHandler.e();
        float contentWidth2 = (this.b.getContentWidth() - (contentWidth / 2.0f)) - viewportHandler.d();
        float contentHeight2 = (this.b.getContentHeight() - (contentHeight / 2.0f)) - viewportHandler.b();
        Matrix matrix = new Matrix();
        matrix.preRotate(90.0f, contentWidth2, contentHeight2);
        matrix.postScale(contentWidth / contentHeight, contentHeight / contentWidth, contentWidth2, contentHeight2);
        path.transform(matrix);
    }

    public float[] a(List<Double> list, List<yk<Double>> list2) {
        if (list == null || list.size() == 0 || list2 == null || list2.size() == 0) {
            return null;
        }
        float[] fArr = new float[list.size() * 2];
        int i = 0;
        for (int i2 = 0; i2 < list.size() && i2 < list2.size(); i2++) {
            fArr[i] = list.get(i2).floatValue();
            int i3 = i + 1;
            if (list2 == null) {
                fArr[i3] = -1.0f;
            } else {
                fArr[i3] = list2.get(i2).b().floatValue();
            }
            i = i3 + 1;
        }
        return fArr;
    }
}
