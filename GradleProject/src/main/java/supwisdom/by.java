package supwisdom;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class by {
    public static ew a(List<cy> list) {
        int size = (list.size() * 2) - 1;
        if (list.get(list.size() - 1).c() == null) {
            size--;
        }
        ew ewVar = new ew(size * 12);
        int i = 0;
        int iB = list.get(0).c().b();
        for (int i2 = 11; i2 >= 0; i2--) {
            if (((1 << i2) & iB) != 0) {
                ewVar.d(i);
            }
            i++;
        }
        for (int i3 = 1; i3 < list.size(); i3++) {
            cy cyVar = list.get(i3);
            int iB2 = cyVar.b().b();
            for (int i4 = 11; i4 >= 0; i4--) {
                if (((1 << i4) & iB2) != 0) {
                    ewVar.d(i);
                }
                i++;
            }
            if (cyVar.c() != null) {
                int iB3 = cyVar.c().b();
                for (int i5 = 11; i5 >= 0; i5--) {
                    if (((1 << i5) & iB3) != 0) {
                        ewVar.d(i);
                    }
                    i++;
                }
            }
        }
        return ewVar;
    }
}
