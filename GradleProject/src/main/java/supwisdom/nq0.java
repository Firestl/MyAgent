package supwisdom;

import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.ProtoSyntax;

/* JADX INFO: compiled from: ManifestSchemaFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class nq0 implements mr0 {
    public static final tq0 b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final tq0 f8553a;

    /* JADX INFO: compiled from: ManifestSchemaFactory.java */
    public class a implements tq0 {
        @Override // supwisdom.tq0
        public sq0 a(Class<?> cls) {
            throw new IllegalStateException("This should never be called.");
        }

        @Override // supwisdom.tq0
        public boolean b(Class<?> cls) {
            return false;
        }
    }

    /* JADX INFO: compiled from: ManifestSchemaFactory.java */
    public static class b implements tq0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public tq0[] f8554a;

        public b(tq0... tq0VarArr) {
            this.f8554a = tq0VarArr;
        }

        @Override // supwisdom.tq0
        public sq0 a(Class<?> cls) {
            for (tq0 tq0Var : this.f8554a) {
                if (tq0Var.b(cls)) {
                    return tq0Var.a(cls);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
        }

        @Override // supwisdom.tq0
        public boolean b(Class<?> cls) {
            for (tq0 tq0Var : this.f8554a) {
                if (tq0Var.b(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    public nq0() {
        this(a());
    }

    public static tq0 b() {
        try {
            return (tq0) Class.forName("com.google.crypto.tink.shaded.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    @Override // supwisdom.mr0
    public <T> lr0<T> a(Class<T> cls) {
        nr0.a((Class<?>) cls);
        sq0 sq0VarA = this.f8553a.a(cls);
        return sq0VarA.a() ? GeneratedMessageLite.class.isAssignableFrom(cls) ? yq0.a(nr0.e(), aq0.b(), sq0VarA.b()) : yq0.a(nr0.c(), aq0.a(), sq0VarA.b()) : a(cls, sq0VarA);
    }

    public nq0(tq0 tq0Var) {
        gq0.a(tq0Var, "messageInfoFactory");
        this.f8553a = tq0Var;
    }

    public static <T> lr0<T> a(Class<T> cls, sq0 sq0Var) {
        if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
            if (a(sq0Var)) {
                return xq0.a(cls, sq0Var, br0.b(), lq0.b(), nr0.e(), aq0.b(), rq0.b());
            }
            return xq0.a(cls, sq0Var, br0.b(), lq0.b(), nr0.e(), (yp0<?>) null, rq0.b());
        }
        if (a(sq0Var)) {
            return xq0.a(cls, sq0Var, br0.a(), lq0.a(), nr0.c(), aq0.a(), rq0.a());
        }
        return xq0.a(cls, sq0Var, br0.a(), lq0.a(), nr0.d(), (yp0<?>) null, rq0.a());
    }

    public static boolean a(sq0 sq0Var) {
        return sq0Var.c() == ProtoSyntax.PROTO2;
    }

    public static tq0 a() {
        return new b(eq0.a(), b());
    }
}
