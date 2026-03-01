package com.alibaba.dt.AChartsLib.decorators.blockDeocators;

import android.graphics.Canvas;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.enums.BlockPosition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import supwisdom.cl;
import supwisdom.pk;
import supwisdom.tn;
import supwisdom.yl;

/* JADX INFO: loaded from: classes.dex */
public class BlockDecorator extends yl {
    public List<BlockPosition> g;
    public boolean h;
    public float i;
    public float j;
    public float k;
    public float l;

    public enum PlaceholderAttr {
        BLOCK_HORIZONTAL,
        BLOCK_VERTICAL,
        FRAME
    }

    public BlockDecorator(Chart chart) {
        super(chart);
        PlaceholderAttr placeholderAttr = PlaceholderAttr.FRAME;
        this.g = new ArrayList();
        this.h = true;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        h();
    }

    public yl a(float f, float f2, float f3, float f4) {
        float f5 = tn.a(this.f9893a.getContext()).density;
        float f6 = f * f5;
        this.i = f6;
        this.k = f2 * f5;
        this.j = f3 * f5;
        this.l = f4 * f5;
        if (f6 > 1.0E-7f) {
            this.g.add(BlockPosition.LEFT);
        }
        if (this.j > 1.0E-7f) {
            this.g.add(BlockPosition.RIGHT);
        }
        if (this.k > 1.0E-7f) {
            this.g.add(BlockPosition.TOP);
        }
        if (this.l > 1.0E-7f) {
            this.g.add(BlockPosition.BOTTOM);
        }
        return this;
    }

    @Override // supwisdom.yl
    public yl a(Canvas canvas) {
        return null;
    }

    public void b(float f, float f2, float f3, float f4) {
        this.i = f;
        this.k = f2;
        this.j = f3;
        this.l = f4;
        if (f > 1.0E-7f) {
            this.g.add(BlockPosition.LEFT);
        }
        if (this.j > 1.0E-7f) {
            this.g.add(BlockPosition.RIGHT);
        }
        if (this.k > 1.0E-7f) {
            this.g.add(BlockPosition.TOP);
        }
        if (this.l > 1.0E-7f) {
            this.g.add(BlockPosition.BOTTOM);
        }
    }

    public float d() {
        return this.l;
    }

    public float e() {
        return this.i;
    }

    public float f() {
        return this.j;
    }

    public float g() {
        return this.k;
    }

    public void h() {
    }

    public boolean i() {
        return this.h;
    }

    public boolean j() {
        return ((cl) this.f9893a.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL;
    }

    public boolean k() {
        Iterator it = this.f9893a.getChartData().g().iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((pk) it.next()).k()) {
                z = true;
            }
        }
        return z;
    }
}
