package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: AdaptationSet.java */
/* JADX INFO: loaded from: classes.dex */
public class ba0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7043a;
    public final int b;
    public final List<ga0> c;
    public final List<ha0> d;

    public ba0(int i, int i2, List<ga0> list, List<ha0> list2) {
        this.f7043a = i;
        this.b = i2;
        this.c = Collections.unmodifiableList(list);
        this.d = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
    }
}
