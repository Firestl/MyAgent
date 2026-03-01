package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;

/* JADX INFO: compiled from: HmacKey.java */
/* JADX INFO: loaded from: classes.dex */
public final class ro0 extends GeneratedMessageLite<ro0, b> implements uo0 {
    public static final ro0 DEFAULT_INSTANCE;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    public static volatile dr0<ro0> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    public ByteString keyValue_ = ByteString.EMPTY;
    public vo0 params_;
    public int version_;

    /* JADX INFO: compiled from: HmacKey.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9078a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9078a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9078a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9078a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9078a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9078a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9078a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9078a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: HmacKey.java */
    public static final class b extends GeneratedMessageLite.a<ro0, b> implements uo0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a(int i) {
            h();
            ((ro0) this.b).b(i);
            return this;
        }

        public b() {
            super(ro0.DEFAULT_INSTANCE);
        }

        public b a(vo0 vo0Var) {
            h();
            ((ro0) this.b).a(vo0Var);
            return this;
        }

        public b a(ByteString byteString) {
            h();
            ((ro0) this.b).a(byteString);
            return this;
        }
    }

    static {
        ro0 ro0Var = new ro0();
        DEFAULT_INSTANCE = ro0Var;
        GeneratedMessageLite.a((Class<ro0>) ro0.class, ro0Var);
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

    public vo0 o() {
        vo0 vo0Var = this.params_;
        return vo0Var == null ? vo0.q() : vo0Var;
    }

    public int p() {
        return this.version_;
    }

    public final void a(vo0 vo0Var) {
        vo0Var.getClass();
        this.params_ = vo0Var;
    }

    public final void a(ByteString byteString) {
        byteString.getClass();
        this.keyValue_ = byteString;
    }

    public static ro0 a(ByteString byteString, xp0 xp0Var) throws InvalidProtocolBufferException {
        return (ro0) GeneratedMessageLite.a(DEFAULT_INSTANCE, byteString, xp0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f9078a[methodToInvoke.ordinal()]) {
            case 1:
                return new ro0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"version_", "params_", "keyValue_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<ro0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (ro0.class) {
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
