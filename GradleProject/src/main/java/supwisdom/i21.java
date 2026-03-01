package supwisdom;

import com.sangfor.dex.DexIndexOverflowException;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class i21 extends b31 {
    public i21(String str, t11 t11Var) {
        super(str, t11Var, 4);
    }

    @Override // supwisdom.b31
    public void j() {
        if (d().size() > 65536) {
            throw new DexIndexOverflowException(k());
        }
        Iterator<? extends f21> it = d().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((h21) it.next()).a(i);
            i++;
        }
    }

    public final String k() {
        TreeMap treeMap = new TreeMap();
        Iterator<? extends f21> it = d().iterator();
        while (it.hasNext()) {
            String strF = ((h21) it.next()).g().f();
            AtomicInteger atomicInteger = (AtomicInteger) treeMap.get(strF);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                treeMap.put(strF, atomicInteger);
            }
            atomicInteger.incrementAndGet();
        }
        Formatter formatter = new Formatter();
        try {
            formatter.format("Too many %1$s references to fit in one dex file: %2$d; max is %3$d.%nYou may try using multi-dex. If multi-dex is enabled then the list of classes for the main dex list is too large.%nReferences by package:", this instanceof n21 ? "method" : "field", Integer.valueOf(d().size()), 65536);
            for (Map.Entry entry : treeMap.entrySet()) {
                formatter.format("%n%6d %s", Integer.valueOf(((AtomicInteger) entry.getValue()).get()), entry.getKey());
            }
            return formatter.toString();
        } finally {
            formatter.close();
        }
    }
}
