package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;

/* JADX INFO: compiled from: AesCmacKey.java */
/* JADX INFO: loaded from: classes.dex */
public final class jo0 extends GeneratedMessageLite<jo0, b> implements mo0 {
    public static final jo0 DEFAULT_INSTANCE;
    public static final int KEY_VALUE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 3;
    public static volatile dr0<jo0> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    public ByteString keyValue_ = ByteString.EMPTY;
    public no0 params_;
    public int version_;

    /* JADX INFO: compiled from: AesCmacKey.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8081a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f8081a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8081a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8081a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8081a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8081a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8081a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8081a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: AesCmacKey.java */
    public static final class b extends GeneratedMessageLite.a<jo0, b> implements mo0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a(int i) {
            h();
            ((jo0) this.b).b(i);
            return this;
        }

        public b() {
            super(jo0.DEFAULT_INSTANCE);
        }

        public b a(ByteString byteString) {
            h();
            ((jo0) this.b).a(byteString);
            return this;
        }

        public b a(no0 no0Var) {
            h();
            ((jo0) this.b).a(no0Var);
            return this;
        }
    }

    static {
        jo0 jo0Var = new jo0();
        DEFAULT_INSTANCE = jo0Var;
        GeneratedMessageLite.a((Class<jo0>) jo0.class, jo0Var);
    }

    public static b r() {
        return DEFAULT_INSTANCE.k();
    }

    public final void b(int i) {
        this.version_ = i;
    }

    public ByteString n() {
        return this.keyValue_;
    }

    public no0 o() {
        no0 no0Var = this.params_;
        return no0Var == null ? no0.p() : no0Var;
    }

    public int p() {
        return this.version_;
    }

    public final void a(ByteString byteString) {
        byteString.getClass();
        this.keyValue_ = byteString;
    }

    public final void a(no0 no0Var) {
        no0Var.getClass();
        this.params_ = no0Var;
    }

    public static jo0 a(ByteString byteString, xp0 xp0Var) throws InvalidProtocolBufferException {
        return (jo0) GeneratedMessageLite.a(DEFAULT_INSTANCE, byteString, xp0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f8081a[methodToInvoke.ordinal()]) {
            case 1:
                return new jo0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"version_", "keyValue_", "params_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<jo0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (jo0.class) {
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
