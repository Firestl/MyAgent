package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;

/* JADX INFO: compiled from: EncryptedKeyset.java */
/* JADX INFO: loaded from: classes.dex */
public final class po0 extends GeneratedMessageLite<po0, b> implements qo0 {
    public static final po0 DEFAULT_INSTANCE;
    public static final int ENCRYPTED_KEYSET_FIELD_NUMBER = 2;
    public static final int KEYSET_INFO_FIELD_NUMBER = 3;
    public static volatile dr0<po0> PARSER;
    public ByteString encryptedKeyset_ = ByteString.EMPTY;
    public dp0 keysetInfo_;

    /* JADX INFO: compiled from: EncryptedKeyset.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8813a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f8813a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8813a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8813a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8813a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8813a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8813a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8813a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: EncryptedKeyset.java */
    public static final class b extends GeneratedMessageLite.a<po0, b> implements qo0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a(ByteString byteString) {
            h();
            ((po0) this.b).a(byteString);
            return this;
        }

        public b() {
            super(po0.DEFAULT_INSTANCE);
        }

        public b a(dp0 dp0Var) {
            h();
            ((po0) this.b).a(dp0Var);
            return this;
        }
    }

    static {
        po0 po0Var = new po0();
        DEFAULT_INSTANCE = po0Var;
        GeneratedMessageLite.a((Class<po0>) po0.class, po0Var);
    }

    public static b p() {
        return DEFAULT_INSTANCE.k();
    }

    public ByteString n() {
        return this.encryptedKeyset_;
    }

    public final void a(ByteString byteString) {
        byteString.getClass();
        this.encryptedKeyset_ = byteString;
    }

    public final void a(dp0 dp0Var) {
        dp0Var.getClass();
        this.keysetInfo_ = dp0Var;
    }

    public static po0 a(byte[] bArr, xp0 xp0Var) throws InvalidProtocolBufferException {
        return (po0) GeneratedMessageLite.a(DEFAULT_INSTANCE, bArr, xp0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f8813a[methodToInvoke.ordinal()]) {
            case 1:
                return new po0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\t", new Object[]{"encryptedKeyset_", "keysetInfo_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<po0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (po0.class) {
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
