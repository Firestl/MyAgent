package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Map;
import supwisdom.cq0;
import supwisdom.cq0.b;

/* JADX INFO: compiled from: ExtensionSchema.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class yp0<T extends cq0.b<T>> {
    public abstract int a(Map.Entry<?, ?> entry);

    public abstract <UT, UB> UB a(jr0 jr0Var, Object obj, xp0 xp0Var, cq0<T> cq0Var, UB ub, rr0<UT, UB> rr0Var) throws IOException;

    public abstract Object a(xp0 xp0Var, uq0 uq0Var, int i);

    public abstract cq0<T> a(Object obj);

    public abstract void a(ByteString byteString, Object obj, xp0 xp0Var, cq0<T> cq0Var) throws IOException;

    public abstract void a(Writer writer, Map.Entry<?, ?> entry) throws IOException;

    public abstract void a(jr0 jr0Var, Object obj, xp0 xp0Var, cq0<T> cq0Var) throws IOException;

    public abstract boolean a(uq0 uq0Var);

    public abstract cq0<T> b(Object obj);

    public abstract void c(Object obj);
}
