package supwisdom;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: Protobuf.java */
/* JADX INFO: loaded from: classes.dex */
public final class fr0 {
    public static final fr0 c = new fr0();
    public final ConcurrentMap<Class<?>, lr0<?>> b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final mr0 f7636a = new nq0();

    public static fr0 a() {
        return c;
    }

    public <T> void a(T t, jr0 jr0Var, xp0 xp0Var) throws IOException {
        a(t).a(t, jr0Var, xp0Var);
    }

    public <T> lr0<T> a(Class<T> cls) {
        gq0.a(cls, "messageType");
        lr0<T> lr0Var = (lr0) this.b.get(cls);
        if (lr0Var != null) {
            return lr0Var;
        }
        lr0<T> lr0VarA = this.f7636a.a(cls);
        lr0<T> lr0Var2 = (lr0<T>) a(cls, lr0VarA);
        return lr0Var2 != null ? lr0Var2 : lr0VarA;
    }

    public <T> lr0<T> a(T t) {
        return a((Class) t.getClass());
    }

    public lr0<?> a(Class<?> cls, lr0<?> lr0Var) {
        gq0.a(cls, "messageType");
        gq0.a(lr0Var, "schema");
        return this.b.putIfAbsent(cls, lr0Var);
    }
}
