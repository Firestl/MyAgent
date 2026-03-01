package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;

/* JADX INFO: compiled from: MessageLite.java */
/* JADX INFO: loaded from: classes.dex */
public interface uq0 extends vq0 {

    /* JADX INFO: compiled from: MessageLite.java */
    public interface a extends vq0, Cloneable {
        uq0 S();

        a a(uq0 uq0Var);

        a b(byte[] bArr) throws InvalidProtocolBufferException;

        uq0 build();
    }

    void a(CodedOutputStream codedOutputStream) throws IOException;

    a b();

    ByteString c();

    int d();

    a e();

    dr0<? extends uq0> f();

    byte[] toByteArray();
}
