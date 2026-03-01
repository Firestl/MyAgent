package supwisdom;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class ie {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap<String, ge> f7949a = new HashMap<>();

    public final void a(String str, ge geVar) {
        ge geVarPut = this.f7949a.put(str, geVar);
        if (geVarPut != null) {
            geVarPut.b();
        }
    }

    public final ge a(String str) {
        return this.f7949a.get(str);
    }

    public final void a() {
        Iterator<ge> it = this.f7949a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f7949a.clear();
    }
}
