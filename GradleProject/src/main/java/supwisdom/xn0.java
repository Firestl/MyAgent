package supwisdom;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import supwisdom.cp0;
import supwisdom.vn0;

/* JADX INFO: compiled from: Registry.java */
/* JADX INFO: loaded from: classes.dex */
public final class xn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f9790a = Logger.getLogger(xn0.class.getName());
    public static final ConcurrentMap<String, d> b = new ConcurrentHashMap();
    public static final ConcurrentMap<String, c> c = new ConcurrentHashMap();
    public static final ConcurrentMap<String, Boolean> d = new ConcurrentHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ConcurrentMap<Class<?>, wn0<?, ?>> f9791e;

    /* JADX INFO: compiled from: Registry.java */
    public class b implements c {
        public b(pn0 pn0Var) {
        }
    }

    /* JADX INFO: compiled from: Registry.java */
    public interface c {
    }

    /* JADX INFO: compiled from: Registry.java */
    public interface d {
        nn0<?> a();

        <P> nn0<P> a(Class<P> cls) throws GeneralSecurityException;

        Class<?> b();

        Set<Class<?>> c();
    }

    static {
        new ConcurrentHashMap();
        f9791e = new ConcurrentHashMap();
    }

    public static <KeyProtoT extends uq0> d a(pn0<KeyProtoT> pn0Var) {
        return new a(pn0Var);
    }

    public static <KeyProtoT extends uq0> c b(pn0<KeyProtoT> pn0Var) {
        return new b(pn0Var);
    }

    /* JADX INFO: compiled from: Registry.java */
    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ pn0 f9792a;

        public a(pn0 pn0Var) {
            this.f9792a = pn0Var;
        }

        @Override // supwisdom.xn0.d
        public <Q> nn0<Q> a(Class<Q> cls) throws GeneralSecurityException {
            try {
                return new on0(this.f9792a, cls);
            } catch (IllegalArgumentException e2) {
                throw new GeneralSecurityException("Primitive type not supported", e2);
            }
        }

        @Override // supwisdom.xn0.d
        public Class<?> b() {
            return this.f9792a.getClass();
        }

        @Override // supwisdom.xn0.d
        public Set<Class<?>> c() {
            return this.f9792a.f();
        }

        @Override // supwisdom.xn0.d
        public nn0<?> a() {
            pn0 pn0Var = this.f9792a;
            return new on0(pn0Var, pn0Var.a());
        }
    }

    public static synchronized d a(String str) throws GeneralSecurityException {
        if (!b.containsKey(str)) {
            throw new GeneralSecurityException("No key manager found for key type " + str);
        }
        return b.get(str);
    }

    public static nn0<?> b(String str) throws GeneralSecurityException {
        return a(str).a();
    }

    public static <P> vn0<P> b(qn0 qn0Var, nn0<P> nn0Var, Class<P> cls) throws GeneralSecurityException {
        P pB;
        yn0.b(qn0Var.a());
        vn0<P> vn0VarA = vn0.a(cls);
        for (cp0.c cVar : qn0Var.a().p()) {
            if (cVar.q() == KeyStatusType.ENABLED) {
                if (nn0Var != null && nn0Var.a(cVar.n().o())) {
                    pB = nn0Var.b(cVar.n().p());
                } else {
                    pB = (P) a(cVar.n().o(), cVar.n().p(), cls);
                }
                vn0.b<P> bVarA = vn0VarA.a(pB, cVar);
                if (cVar.o() == qn0Var.a().q()) {
                    vn0VarA.a(bVarA);
                }
            }
        }
        return vn0VarA;
    }

    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static synchronized void a(String str, Class<?> cls, boolean z) throws GeneralSecurityException {
        if (b.containsKey(str)) {
            d dVar = b.get(str);
            if (dVar.b().equals(cls)) {
                if (z && !d.get(str).booleanValue()) {
                    throw new GeneralSecurityException("New keys are already disallowed for key type " + str);
                }
                return;
            }
            f9790a.warning("Attempted overwrite of a registered key manager for key type " + str);
            throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", str, dVar.b().getName(), cls.getName()));
        }
    }

    public static synchronized <KeyProtoT extends uq0> void a(pn0<KeyProtoT> pn0Var, boolean z) throws GeneralSecurityException {
        if (pn0Var != null) {
            String strC = pn0Var.c();
            a(strC, pn0Var.getClass(), z);
            if (!b.containsKey(strC)) {
                b.put(strC, a((pn0) pn0Var));
                c.put(strC, b(pn0Var));
            }
            d.put(strC, Boolean.valueOf(z));
        } else {
            throw new IllegalArgumentException("key manager must be non-null.");
        }
    }

    public static synchronized <B, P> void a(wn0<B, P> wn0Var) throws GeneralSecurityException {
        if (wn0Var != null) {
            Class<P> clsB = wn0Var.b();
            if (f9791e.containsKey(clsB)) {
                wn0<?, ?> wn0Var2 = f9791e.get(clsB);
                if (!wn0Var.getClass().equals(wn0Var2.getClass())) {
                    f9790a.warning("Attempted overwrite of a registered SetWrapper for type " + clsB);
                    throw new GeneralSecurityException(String.format("SetWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", clsB.getName(), wn0Var2.getClass().getName(), wn0Var.getClass().getName()));
                }
            }
            f9791e.put(clsB, wn0Var);
        } else {
            throw new IllegalArgumentException("wrapper must be non-null");
        }
    }

    public static String a(Set<Class<?>> set) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class<?> cls : set) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls.getCanonicalName());
            z = false;
        }
        return sb.toString();
    }

    public static <P> nn0<P> a(String str, Class<P> cls) throws GeneralSecurityException {
        d dVarA = a(str);
        if (cls == null) {
            return (nn0<P>) dVarA.a();
        }
        if (dVarA.c().contains(cls)) {
            return dVarA.a(cls);
        }
        throw new GeneralSecurityException("Primitive type " + cls.getName() + " not supported by key manager of type " + dVarA.b() + ", supported primitives: " + a(dVarA.c()));
    }

    public static synchronized KeyData a(yo0 yo0Var) throws GeneralSecurityException {
        nn0<?> nn0VarB;
        nn0VarB = b(yo0Var.o());
        if (d.get(yo0Var.o()).booleanValue()) {
        } else {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + yo0Var.o());
        }
        return nn0VarB.a(yo0Var.p());
    }

    public static <P> P a(String str, ByteString byteString, Class<P> cls) throws GeneralSecurityException {
        return (P) a(str, cls).b(byteString);
    }

    public static <P> vn0<P> a(qn0 qn0Var, Class<P> cls) throws GeneralSecurityException {
        return a(qn0Var, (nn0) null, cls);
    }

    public static <P> vn0<P> a(qn0 qn0Var, nn0<P> nn0Var, Class<P> cls) throws GeneralSecurityException {
        a(cls);
        return b(qn0Var, nn0Var, cls);
    }

    public static <B, P> P a(vn0<B> vn0Var, Class<P> cls) throws GeneralSecurityException {
        wn0<?, ?> wn0Var = f9791e.get(cls);
        if (wn0Var != null) {
            if (wn0Var.a().equals(vn0Var.a())) {
                return (P) wn0Var.a(vn0Var);
            }
            throw new GeneralSecurityException("Wrong input primitive class, expected " + wn0Var.a() + ", got " + vn0Var.a());
        }
        throw new GeneralSecurityException("No wrapper found for " + vn0Var.a().getName());
    }

    public static Class<?> a(Class<?> cls) {
        wn0<?, ?> wn0Var = f9791e.get(cls);
        if (wn0Var == null) {
            return null;
        }
        return wn0Var.a();
    }
}
