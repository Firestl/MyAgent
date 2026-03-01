package supwisdom;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.dt.AChartsLib.charts.Chart;
import com.alibaba.dt.dchartlib.R;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: DefaultPolarMarkerView.java */
/* JADX INFO: loaded from: classes.dex */
public class sn extends vn {
    public List<TextView> d;

    public sn(Chart chart) {
        super(chart);
        this.d = new ArrayList();
    }

    @Override // supwisdom.vn
    public void a(float[] fArr) {
        LinearLayout linearLayout = (LinearLayout) this.f9527a.findViewById(R.id.marker_view_container);
        List<pk> listG = this.c.getChartData().g();
        List listF = this.c.getChartData().f();
        this.d.clear();
        linearLayout.removeAllViews();
        int i = (int) fArr[0];
        String str = (listG.size() != 1 || (this.c.getChartData() instanceof kk) || i >= listF.size()) ? "" : (String) ((yk) listF.get(i)).b();
        for (pk pkVar : listG) {
            TextView textView = new TextView(this.b);
            textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            textView.setTextSize(10.0f);
            linearLayout.addView(textView);
            if (i > pkVar.i().size()) {
                this.d.clear();
                return;
            }
            if (fArr[0] >= pkVar.i().size()) {
                textView.setText(pkVar.h() + ": -");
                this.d.add(textView);
            } else {
                if ("".equals(str)) {
                    textView.setText(pkVar.h() + Constants.COLON_SEPARATOR + ((al) pkVar.i().get(i)).b());
                } else {
                    textView.setText(str + Constants.COLON_SEPARATOR + ((al) pkVar.i().get(i)).b());
                }
                this.d.add(textView);
            }
        }
    }

    @Override // supwisdom.vn
    public void b() {
        this.f9527a = (ViewGroup) LayoutInflater.from(this.b).inflate(R.layout.default_marker_view_layout, (ViewGroup) null);
        this.b.getResources().getColor(R.color.marker_text_white);
    }
}
