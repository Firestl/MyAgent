package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;

/* JADX INFO: compiled from: AesCmacKeyFormat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ko0 extends GeneratedMessageLite<ko0, b> implements lo0 {
    public static final ko0 DEFAULT_INSTANCE;
    public static final int KEY_SIZE_FIELD_NUMBER = 1;
    public static final int PARAMS_FIELD_NUMBER = 2;
    public static volatile dr0<ko0> PARSER;
    public int keySize_;
    public no0 params_;

    /* JADX INFO: compiled from: AesCmacKeyFormat.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8189a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f8189a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8189a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8189a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8189a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8189a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8189a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8189a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: AesCmacKeyFormat.java */
    public static final class b extends GeneratedMessageLite.a<ko0, b> implements lo0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b() {
            super(ko0.DEFAULT_INSTANCE);
        }
    }

    static {
        ko0 ko0Var = new ko0();
        DEFAULT_INSTANCE = ko0Var;
        GeneratedMessageLite.a((Class<ko0>) ko0.class, ko0Var);
    }

    public static ko0 a(ByteString byteString, xp0 xp0Var) throws InvalidProtocolBufferException {
        return (ko0) GeneratedMessageLite.a(DEFAULT_INSTANCE, byteString, xp0Var);
    }

    public int n() {
        return this.keySize_;
    }

    public no0 o() {
        no0 no0Var = this.params_;
        return no0Var == null ? no0.p() : no0Var;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f8189a[methodToInvoke.ordinal()]) {
            case 1:
                return new ko0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"keySize_", "params_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<ko0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (ko0.class) {
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
