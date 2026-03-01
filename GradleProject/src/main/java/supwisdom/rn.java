package supwisdom;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.dt.AChartsLib.chartStrategys.BarChartStrategy;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.dchartlib.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DefaultMarkerView.java */
/* JADX INFO: loaded from: classes.dex */
public class rn extends vn {
    public List<TextView> d;

    public rn(Chart chart) {
        super(chart);
        this.d = new ArrayList();
    }

    @Override // supwisdom.vn
    public void a(float[] fArr) {
        int i;
        new ArrayList();
        new ArrayList();
        List listH = this.c.getChartData().h();
        List<pk> listG = this.c.getChartData().g();
        int i2 = 0;
        this.f9527a.setVisibility(0);
        LinearLayout linearLayout = (LinearLayout) this.f9527a.findViewById(R.id.marker_view_container);
        int i3 = 1;
        float f = ((cl) this.c.getChartStrategy()).p() == BarChartStrategy.BarChartDirection.HORIZONTAL ? fArr[1] : fArr[0];
        if (listG.size() >= this.d.size() - 1) {
            Iterator it = listG.iterator();
            i = 0;
            while (it.hasNext()) {
                if (f < ((pk) it.next()).i().size()) {
                    i++;
                }
            }
        } else {
            i = 0;
        }
        if (this.d.size() > 0 && i != this.d.size() - 1) {
            this.d.clear();
            linearLayout.removeAllViews();
        }
        List listF = this.c.getChartData().f();
        if (this.d.size() == 0) {
            String str = this.c.getChartConfig().f.f9421e;
            if (listF.size() > 0 && f < ((ok) listF.get(0)).d().size()) {
                TextView textView = new TextView(this.b);
                textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                textView.setTextSize(10.0f);
                textView.setTextColor(Color.parseColor(str));
                textView.setText((CharSequence) ((yk) ((ok) listF.get(0)).d().get((int) f)).b());
                linearLayout.addView(textView);
                this.d.add(textView);
            }
        } else {
            String str2 = this.c.getChartConfig().f.f9421e;
            if (listF.size() > 0 && f < ((ok) listF.get(0)).d().size()) {
                TextView textView2 = this.d.get(0);
                textView2.setTextSize(10.0f);
                textView2.setTextColor(Color.parseColor(str2));
                textView2.setText((CharSequence) ((yk) ((ok) listF.get(0)).d().get((int) f)).b());
            }
        }
        if (listF.size() > 0 && f < ((ok) listF.get(0)).d().size()) {
            if (this.c.getChartConfig().f.c) {
                this.d.get(0).setVisibility(0);
            } else {
                this.d.get(0).setVisibility(8);
            }
        }
        if (this.c.getChartConfig().f.b) {
            if (this.d.size() == 1) {
                for (pk pkVar : listG) {
                    i2 += i3;
                    if (f < pkVar.i().size()) {
                        TextView textView3 = new TextView(this.b);
                        textView3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                        textView3.setTextSize(10.0f);
                        linearLayout.addView(textView3);
                        int i4 = (int) f;
                        if (pkVar.i().get(i4).b() == null) {
                            textView3.setText(pkVar.h() + " : -");
                            textView3.setTextColor(pkVar.d());
                        } else {
                            double dDoubleValue = this.c.getChartData() instanceof nk ? pkVar.i().get(i4).b().doubleValue() : ((pk) listH.get(i2 - 1)).i().get(i4).b().doubleValue();
                            String strValueOf = String.valueOf(dDoubleValue);
                            if (this.c.getChartConfig().f.i != null) {
                                strValueOf = this.c.getChartConfig().f.i.a(Double.valueOf(dDoubleValue));
                            }
                            textView3.setText(pkVar.h() + " : " + strValueOf);
                            textView3.setTextColor(pkVar.d());
                        }
                        this.d.add(textView3);
                    }
                    i3 = 1;
                }
            } else {
                for (int i5 = 1; i5 < this.d.size(); i5++) {
                    int i6 = i5 - 1;
                    if (f < ((pk) this.c.getChartData().g().get(i6)).i().size()) {
                        Double dB = ((pk) listH.get(i6)).i().get((int) f).b();
                        String strValueOf2 = String.valueOf(dB);
                        if (this.c.getChartConfig().f.i != null) {
                            strValueOf2 = this.c.getChartConfig().f.i.a(dB);
                        }
                        this.d.get(i5).setText(((pk) listG.get(i6)).h() + " : " + strValueOf2);
                        this.d.get(i5).setTextColor(((pk) this.c.getChartData().g().get(i6)).d());
                    }
                }
            }
        }
        if (this.d.size() == 0 || !(this.c.getChartConfig().f.c || this.c.getChartConfig().f.b)) {
            this.f9527a.setVisibility(8);
        }
    }

    @Override // supwisdom.vn
    public void b() {
        this.f9527a = (ViewGroup) LayoutInflater.from(this.b).inflate(R.layout.default_marker_view_layout, (ViewGroup) null);
        this.b.getResources().getColor(R.color.marker_text_white);
    }
}
