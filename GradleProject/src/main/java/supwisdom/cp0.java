package supwisdom;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.util.Collections;
import java.util.List;
import supwisdom.gq0;

/* JADX INFO: compiled from: Keyset.java */
/* JADX INFO: loaded from: classes.dex */
public final class cp0 extends GeneratedMessageLite<cp0, b> implements fp0 {
    public static final cp0 DEFAULT_INSTANCE;
    public static final int KEY_FIELD_NUMBER = 2;
    public static volatile dr0<cp0> PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    public gq0.i<c> key_ = GeneratedMessageLite.m();
    public int primaryKeyId_;

    /* JADX INFO: compiled from: Keyset.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7236a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f7236a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7236a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7236a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7236a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7236a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7236a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7236a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: Keyset.java */
    public static final class b extends GeneratedMessageLite.a<cp0, b> implements fp0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public c a(int i) {
            return ((cp0) this.b).b(i);
        }

        public b b(int i) {
            h();
            ((cp0) this.b).c(i);
            return this;
        }

        public int j() {
            return ((cp0) this.b).o();
        }

        public List<c> k() {
            return Collections.unmodifiableList(((cp0) this.b).p());
        }

        public b() {
            super(cp0.DEFAULT_INSTANCE);
        }

        public b a(c cVar) {
            h();
            ((cp0) this.b).a(cVar);
            return this;
        }
    }

    /* JADX INFO: compiled from: Keyset.java */
    public static final class c extends GeneratedMessageLite<c, a> implements d {
        public static final c DEFAULT_INSTANCE;
        public static final int KEY_DATA_FIELD_NUMBER = 1;
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        public static volatile dr0<c> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        public KeyData keyData_;
        public int keyId_;
        public int outputPrefixType_;
        public int status_;

        /* JADX INFO: compiled from: Keyset.java */
        public static final class a extends GeneratedMessageLite.a<c, a> implements d {
            public /* synthetic */ a(a aVar) {
                this();
            }

            public a a(KeyData keyData) {
                h();
                ((c) this.b).a(keyData);
                return this;
            }

            public a() {
                super(c.DEFAULT_INSTANCE);
            }

            public a a(KeyStatusType keyStatusType) {
                h();
                ((c) this.b).a(keyStatusType);
                return this;
            }

            public a a(int i) {
                h();
                ((c) this.b).b(i);
                return this;
            }

            public a a(OutputPrefixType outputPrefixType) {
                h();
                ((c) this.b).a(outputPrefixType);
                return this;
            }
        }

        static {
            c cVar = new c();
            DEFAULT_INSTANCE = cVar;
            GeneratedMessageLite.a((Class<c>) c.class, cVar);
        }

        public static a t() {
            return DEFAULT_INSTANCE.k();
        }

        public final void b(int i) {
            this.keyId_ = i;
        }

        public KeyData n() {
            KeyData keyData = this.keyData_;
            return keyData == null ? KeyData.r() : keyData;
        }

        public int o() {
            return this.keyId_;
        }

        public OutputPrefixType p() {
            OutputPrefixType outputPrefixTypeForNumber = OutputPrefixType.forNumber(this.outputPrefixType_);
            return outputPrefixTypeForNumber == null ? OutputPrefixType.UNRECOGNIZED : outputPrefixTypeForNumber;
        }

        public KeyStatusType q() {
            KeyStatusType keyStatusTypeForNumber = KeyStatusType.forNumber(this.status_);
            return keyStatusTypeForNumber == null ? KeyStatusType.UNRECOGNIZED : keyStatusTypeForNumber;
        }

        public boolean r() {
            return this.keyData_ != null;
        }

        public final void a(KeyData keyData) {
            keyData.getClass();
            this.keyData_ = keyData;
        }

        public final void a(KeyStatusType keyStatusType) {
            this.status_ = keyStatusType.getNumber();
        }

        public final void a(OutputPrefixType outputPrefixType) {
            this.outputPrefixType_ = outputPrefixType.getNumber();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            a aVar = null;
            switch (a.f7236a[methodToInvoke.ordinal()]) {
                case 1:
                    return new c();
                case 2:
                    return new a(aVar);
                case 3:
                    return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"keyData_", "status_", "keyId_", "outputPrefixType_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    dr0<c> bVar = PARSER;
                    if (bVar == null) {
                        synchronized (c.class) {
                            bVar = PARSER;
                            if (bVar == null) {
                                bVar = new GeneratedMessageLite.b<>(DEFAULT_INSTANCE);
                                PARSER = bVar;
                            }
                            break;
                        }
                    }
                    return bVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    /* JADX INFO: compiled from: Keyset.java */
    public interface d extends vq0 {
    }

    static {
        cp0 cp0Var = new cp0();
        DEFAULT_INSTANCE = cp0Var;
        GeneratedMessageLite.a((Class<cp0>) cp0.class, cp0Var);
    }

    public static b s() {
        return DEFAULT_INSTANCE.k();
    }

    public c b(int i) {
        return this.key_.get(i);
    }

    public final void c(int i) {
        this.primaryKeyId_ = i;
    }

    public final void n() {
        if (this.key_.f()) {
            return;
        }
        this.key_ = GeneratedMessageLite.a(this.key_);
    }

    public int o() {
        return this.key_.size();
    }

    public List<c> p() {
        return this.key_;
    }

    public int q() {
        return this.primaryKeyId_;
    }

    public final void a(c cVar) {
        cVar.getClass();
        n();
        this.key_.add(cVar);
    }

    public static cp0 a(byte[] bArr, xp0 xp0Var) throws InvalidProtocolBufferException {
        return (cp0) GeneratedMessageLite.a(DEFAULT_INSTANCE, bArr, xp0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f7236a[methodToInvoke.ordinal()]) {
            case 1:
                return new cp0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"primaryKeyId_", "key_", c.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<cp0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (cp0.class) {
                        bVar = PARSER;
                        if (bVar == null) {
                            bVar = new GeneratedMessageLite.b<>(DEFAULT_INSTANCE);
                            PARSER = bVar;
                        }
                        break;
                    }
                }
                return bVar;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
