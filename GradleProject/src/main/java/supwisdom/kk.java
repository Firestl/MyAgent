package supwisdom;

import java.util.ArrayList;
import java.util.List;
import supwisdom.tk;
import supwisdom.uk;

/* JADX INFO: compiled from: RadarChartData.java */
/* JADX INFO: loaded from: classes.dex */
public class kk<K extends tk, T extends uk> extends jk {
    public List<Double> j() {
        if (f().size() > 0 && ((tk) f().get(0)).f().size() > 0) {
            return ((tk) f().get(0)).f();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < f().size(); i++) {
            arrayList.add(Double.valueOf(c()));
        }
        return arrayList;
    }
}
