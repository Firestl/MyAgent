package supwisdom;

import android.content.Context;
import com.alibaba.dt.dchartlib.R;

/* JADX INFO: compiled from: ChartPalette.java */
/* JADX INFO: loaded from: classes.dex */
public class pn {
    public static int c;
    public static int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f8807e;
    public static int f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f8808a;
    public Context b;

    public pn(Context context) {
        this.b = context;
        c = context.getResources().getColor(R.color.themeColorMain);
        d = this.b.getResources().getColor(R.color.themeColorSecondary);
        f8807e = this.b.getResources().getColor(R.color.themeColorThird);
        f = this.b.getResources().getColor(R.color.themeColorFourth);
        g = this.b.getResources().getColor(R.color.themeColorFifth);
        h = this.b.getResources().getColor(R.color.themeColorSixth);
        i = this.b.getResources().getColor(R.color.themeColorSeventh);
        int color = this.b.getResources().getColor(R.color.themeColorEighth);
        j = color;
        this.f8808a = new int[]{c, d, f8807e, f, g, h, i, color};
    }
}
