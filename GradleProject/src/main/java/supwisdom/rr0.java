package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;

/* JADX INFO: compiled from: UnknownFieldSchema.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class rr0<T, B> {
    public abstract B a();

    public abstract B a(Object obj);

    public abstract T a(T t, T t2);

    public abstract void a(B b, int i, int i2);

    public abstract void a(B b, int i, long j);

    public abstract void a(B b, int i, ByteString byteString);

    public abstract void a(B b, int i, T t);

    public abstract void a(T t, Writer writer) throws IOException;

    public final void a(B b, jr0 jr0Var) throws IOException {
        while (jr0Var.k() != Integer.MAX_VALUE && b((Object) b, jr0Var)) {
        }
    }

    public abstract boolean a(jr0 jr0Var);

    public abstract T b(Object obj);

    public abstract void b(B b, int i, long j);

    public abstract void b(T t, Writer writer) throws IOException;

    public abstract void b(Object obj, B b);

    public final boolean b(B b, jr0 jr0Var) throws IOException {
        int tag = jr0Var.getTag();
        int iA = WireFormat.a(tag);
        int iB = WireFormat.b(tag);
        if (iB == 0) {
            b(b, iA, jr0Var.p());
            return true;
        }
        if (iB == 1) {
            a(b, iA, jr0Var.b());
            return true;
        }
        if (iB == 2) {
            a((Object) b, iA, jr0Var.l());
            return true;
        }
        if (iB != 3) {
            if (iB == 4) {
                return false;
            }
            if (iB != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            a((Object) b, iA, jr0Var.c());
            return true;
        }
        B bA = a();
        int iA2 = WireFormat.a(iA, 4);
        a((Object) bA, jr0Var);
        if (iA2 != jr0Var.getTag()) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        a(b, iA, f(bA));
        return true;
    }

    public abstract int c(T t);

    public abstract void c(Object obj, T t);

    public abstract int d(T t);

    public abstract void e(Object obj);

    public abstract T f(B b);
}
