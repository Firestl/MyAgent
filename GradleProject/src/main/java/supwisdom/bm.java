package supwisdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator;
import com.alibaba.dt.dchartlib.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: BaseLegendDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class bm extends BlockDecorator {
    public LinearLayout m;
    public List<LinearLayout> n;
    public Context o;
    public int p;

    public bm(Chart chart) {
        super(chart);
        this.p = 0;
    }

    @Override // supwisdom.yl
    public void a(View view, MotionEvent motionEvent) {
        m();
    }

    public final void b(ek ekVar) {
        List listF = ekVar.f();
        int i = this.p % 4;
        List listG = ekVar.g();
        if (listG == null || listG.size() == 0) {
            return;
        }
        if (listG.size() <= 0 || ((sk) listG.get(0)).j) {
            int[] iArrL = ((sk) listG.get(0)).l();
            if (iArrL == null) {
                iArrL = this.f9893a.getChartPalette().f8808a;
            }
            for (int i2 = 0; i2 < listF.size(); i2++) {
                View viewInflate = LayoutInflater.from(this.o).inflate(R.layout.legend_item_layout, (ViewGroup) null);
                TextView textView = (TextView) viewInflate.findViewById(R.id.legend_item_name);
                View viewFindViewById = viewInflate.findViewById(R.id.legend_label_view);
                textView.setTextSize(12.0f);
                textView.setText((String) ((yk) listF.get(i2)).b());
                textView.setTextColor(iArrL[i2 % iArrL.length]);
                a(viewFindViewById, iArrL[i2 % iArrL.length]);
                viewInflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                this.n.get(i).measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                if (viewInflate.getMeasuredWidth() + this.n.get(i).getMeasuredWidth() > this.f9893a.getMeasuredWidth()) {
                    a(i);
                    i++;
                    if (i > 1) {
                        break;
                    }
                }
                viewInflate.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                this.n.get(i).addView(viewInflate);
                this.p++;
            }
            a(i);
        }
    }

    public final void c(ek ekVar) {
        int i = this.p > 4 ? 1 : 0;
        if (i > 1) {
            return;
        }
        List listG = ekVar.g();
        for (int i2 = 0; i2 < listG.size(); i2++) {
            if (((pk) listG.get(i2)).j) {
                View viewInflate = LayoutInflater.from(this.o).inflate(R.layout.legend_item_layout, (ViewGroup) null);
                TextView textView = (TextView) viewInflate.findViewById(R.id.legend_item_name);
                View viewFindViewById = viewInflate.findViewById(R.id.legend_label_view);
                textView.setTextSize(12.0f);
                textView.setText(((pk) listG.get(i2)).h());
                textView.setTextColor(((pk) listG.get(i2)).d());
                a(viewFindViewById, ((pk) listG.get(i2)).d());
                viewInflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                this.n.get(i).measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                if (viewInflate.getMeasuredWidth() + this.n.get(i).getMeasuredWidth() > this.f9893a.getMeasuredWidth()) {
                    a(i);
                    i++;
                    if (i > 1) {
                        break;
                    }
                }
                viewInflate.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                this.n.get(i).addView(viewInflate);
                this.p++;
            }
        }
        a(i);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator
    public void h() {
        super.h();
        this.f9894e = true;
        this.o = this.f9893a.getContext();
        this.n = new ArrayList();
        LinearLayout linearLayout = new LinearLayout(this.o);
        this.m = linearLayout;
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.m.setOrientation(1);
        for (int i = 0; i < 2; i++) {
            LinearLayout linearLayout2 = (LinearLayout) LayoutInflater.from(this.f9893a.getContext()).inflate(R.layout.legend_row_layout, (ViewGroup) this.m, false);
            linearLayout2.setVisibility(8);
            this.n.add(linearLayout2);
        }
    }

    public final void l() {
        ek chartData = this.f9893a.getChartData();
        n();
        if (chartData instanceof fk) {
            a(chartData);
            return;
        }
        if (chartData instanceof ik) {
            if (chartData.g().size() > 1) {
                c(chartData);
                return;
            } else {
                b(chartData);
                return;
            }
        }
        if (chartData instanceof jk) {
            b(chartData);
        } else if (chartData instanceof lk) {
            c(chartData);
        }
    }

    public void m() {
        if (this.f9893a.getChartConfig().k) {
            this.h = true;
            dl dlVarM = this.f9893a.getChartStrategy().m();
            l();
            this.m.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            LinearLayout linearLayout = this.m;
            linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(), this.m.getMeasuredHeight());
            b(0.0f, this.m.getMeasuredHeight(), 0.0f, 0.0f);
            dlVarM.a((BlockDecorator) this);
            this.h = false;
        }
    }

    public final void n() {
        this.p = 0;
        this.m.removeAllViews();
        for (int i = 0; i < this.n.size(); i++) {
            this.n.get(i).removeAllViews();
        }
        this.n.get(1).setVisibility(8);
    }

    public final void a(ek ekVar) {
        List<ek> listK = ((fk) ekVar).k();
        this.p = 0;
        Iterator<ek> it = listK.iterator();
        while (it.hasNext()) {
            c(it.next());
        }
    }

    public final void a(View view, int i) {
        Drawable background = view.getBackground();
        if (background != null) {
            background.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
        view.setBackgroundDrawable(background);
    }

    @Override // com.alibaba.dt.AChartsLib.decorators.blockDeocators.BlockDecorator, supwisdom.yl
    public yl a(Canvas canvas) {
        if (!this.f9893a.getChartConfig().k) {
            this.m.removeAllViews();
            this.m.setVisibility(8);
            return this;
        }
        m();
        this.m.draw(canvas);
        return this;
    }

    public final void a(int i) {
        if (i < this.n.size()) {
            ViewParent parent = this.n.get(i).getParent();
            LinearLayout linearLayout = this.m;
            if (parent != linearLayout) {
                linearLayout.addView(this.n.get(i));
                this.n.get(i).setVisibility(0);
            }
        }
    }
}
