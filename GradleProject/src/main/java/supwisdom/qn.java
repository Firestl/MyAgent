package supwisdom;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.dchartlib.R;
import java.util.List;

/* JADX INFO: compiled from: DefaultCrossMarkerView.java */
/* JADX INFO: loaded from: classes.dex */
public class qn extends rn {
    public qn(Chart chart) {
        super(chart);
    }

    public void a(float[] fArr, int i) {
        this.d.clear();
        if (this.c.getChartConfig().f.b) {
            LinearLayout linearLayout = (LinearLayout) this.f9527a.findViewById(R.id.marker_view_container);
            Drawable background = this.f9527a.getBackground();
            if (background != null) {
                background.setColorFilter(Color.parseColor(this.c.getChartConfig().f.d), PorterDuff.Mode.MULTIPLY);
                this.f9527a.setBackgroundDrawable(background);
            }
            this.d.clear();
            linearLayout.removeAllViews();
            List listG = this.c.getChartData().g();
            if (i < listG.size()) {
                String str = this.c.getChartConfig().f.f9421e;
                String strH = ((xk) listG.get(i)).h();
                TextView textView = new TextView(this.b);
                textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                textView.setTextSize(10.0f);
                textView.setTextColor(Color.parseColor(str));
                textView.setText(strH);
                linearLayout.addView(textView);
                this.d.add(textView);
            }
            TextView textView2 = new TextView(this.b);
            textView2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            textView2.setTextSize(10.0f);
            textView2.setText("x: " + fArr[0] + "\ny:" + fArr[1]);
            linearLayout.addView(textView2);
        }
    }
}
