package supwisdom;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class dy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<cy> f7394a;
    public final int b;
    public final boolean c;

    public dy(List<cy> list, int i, boolean z) {
        this.f7394a = new ArrayList(list);
        this.b = i;
        this.c = z;
    }

    public List<cy> a() {
        return this.f7394a;
    }

    public int b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof dy)) {
            return false;
        }
        dy dyVar = (dy) obj;
        return this.f7394a.equals(dyVar.a()) && this.c == dyVar.c;
    }

    public int hashCode() {
        return this.f7394a.hashCode() ^ Boolean.valueOf(this.c).hashCode();
    }

    public String toString() {
        return "{ " + this.f7394a + " }";
    }

    public boolean a(List<cy> list) {
        return this.f7394a.equals(list);
    }
}
