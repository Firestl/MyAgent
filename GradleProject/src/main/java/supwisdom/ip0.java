package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.UninitializedMessageException;
import java.io.IOException;
import supwisdom.ip0;
import supwisdom.ip0.a;
import supwisdom.uq0;

/* JADX INFO: compiled from: AbstractMessageLite.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ip0<MessageType extends ip0<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> implements uq0 {
    public int memoizedHashCode = 0;

    /* JADX INFO: compiled from: AbstractMessageLite.java */
    public static abstract class a<MessageType extends ip0<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> implements uq0.a {
        public abstract BuilderType a(MessageType messagetype);

        public abstract BuilderType a(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

        @Override // supwisdom.uq0.a
        public /* bridge */ /* synthetic */ uq0.a a(uq0 uq0Var) {
            a(uq0Var);
            return this;
        }

        @Override // supwisdom.uq0.a
        public /* bridge */ /* synthetic */ uq0.a b(byte[] bArr) throws InvalidProtocolBufferException {
            b(bArr);
            return this;
        }

        @Override // supwisdom.uq0.a
        public BuilderType a(uq0 uq0Var) {
            if (!a().getClass().isInstance(uq0Var)) {
                throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
            }
            a((ip0) uq0Var);
            return this;
        }

        @Override // supwisdom.uq0.a
        public BuilderType b(byte[] bArr) throws InvalidProtocolBufferException {
            a(bArr, 0, bArr.length);
            return this;
        }

        public static UninitializedMessageException b(uq0 uq0Var) {
            return new UninitializedMessageException(uq0Var);
        }
    }

    public void a(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // supwisdom.uq0
    public ByteString c() {
        try {
            ByteString.h hVarNewCodedBuilder = ByteString.newCodedBuilder(d());
            a(hVarNewCodedBuilder.b());
            return hVarNewCodedBuilder.a();
        } catch (IOException e2) {
            throw new RuntimeException(a("ByteString"), e2);
        }
    }

    public int h() {
        throw new UnsupportedOperationException();
    }

    public UninitializedMessageException i() {
        return new UninitializedMessageException(this);
    }

    @Override // supwisdom.uq0
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[d()];
            CodedOutputStream codedOutputStreamC = CodedOutputStream.c(bArr);
            a(codedOutputStreamC);
            codedOutputStreamC.a();
            return bArr;
        } catch (IOException e2) {
            throw new RuntimeException(a("byte array"), e2);
        }
    }

    public int a(lr0 lr0Var) {
        int iH = h();
        if (iH != -1) {
            return iH;
        }
        int iC = lr0Var.c(this);
        a(iC);
        return iC;
    }

    public final String a(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }
}
