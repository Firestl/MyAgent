package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Period.java */
/* JADX INFO: loaded from: classes.dex */
public class ea0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7459a;
    public final long b;
    public final List<ba0> c;

    public ea0(String str, long j, List<ba0> list) {
        this.f7459a = str;
        this.b = j;
        this.c = Collections.unmodifiableList(list);
    }

    public int a(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.c.get(i2).b == i) {
                return i2;
            }
        }
        return -1;
    }
}
