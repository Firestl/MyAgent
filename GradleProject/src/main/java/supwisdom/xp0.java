package supwisdom;

import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ExtensionRegistryLite.java */
/* JADX INFO: loaded from: classes.dex */
public class xp0 {
    public static boolean b = true;
    public static volatile xp0 c;
    public static final xp0 d = new xp0(true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<a, GeneratedMessageLite.f<?, ?>> f9796a;

    /* JADX INFO: compiled from: ExtensionRegistryLite.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f9797a;
        public final int b;

        public a(Object obj, int i) {
            this.f9797a = obj;
            this.b = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f9797a == aVar.f9797a && this.b == aVar.b;
        }

        public int hashCode() {
            return (System.identityHashCode(this.f9797a) * 65535) + this.b;
        }
    }

    public xp0() {
        this.f9796a = new HashMap();
    }

    public static xp0 a() {
        xp0 xp0VarA = c;
        if (xp0VarA == null) {
            synchronized (xp0.class) {
                xp0VarA = c;
                if (xp0VarA == null) {
                    xp0VarA = b ? wp0.a() : d;
                    c = xp0VarA;
                }
            }
        }
        return xp0VarA;
    }

    public xp0(boolean z) {
        this.f9796a = Collections.emptyMap();
    }

    public <ContainingType extends uq0> GeneratedMessageLite.f<ContainingType, ?> a(ContainingType containingtype, int i) {
        return (GeneratedMessageLite.f) this.f9796a.get(new a(containingtype, i));
    }
}
