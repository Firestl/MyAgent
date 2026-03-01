package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;

/* JADX INFO: compiled from: HmacKeyFormat.java */
/* JADX INFO: loaded from: classes.dex */
public final class so0 extends GeneratedMessageLite<so0, b> implements to0 {
    public static final so0 DEFAULT_INSTANCE;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    public static volatile dr0<so0> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    public int keySize_;
    public vo0 params_;
    public int version_;

    /* JADX INFO: compiled from: HmacKeyFormat.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9196a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9196a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9196a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9196a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9196a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9196a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9196a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9196a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: HmacKeyFormat.java */
    public static final class b extends GeneratedMessageLite.a<so0, b> implements to0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a(vo0 vo0Var) {
            h();
            ((so0) this.b).a(vo0Var);
            return this;
        }

        public b() {
            super(so0.DEFAULT_INSTANCE);
        }

        public b a(int i) {
            h();
            ((so0) this.b).b(i);
            return this;
        }
    }

    static {
        so0 so0Var = new so0();
        DEFAULT_INSTANCE = so0Var;
        GeneratedMessageLite.a((Class<so0>) so0.class, so0Var);
    }

    public static b q() {
        return DEFAULT_INSTANCE.k();
    }

    public final void b(int i) {
        this.keySize_ = i;
    }

    public int n() {
        return this.keySize_;
    }

    public vo0 o() {
        vo0 vo0Var = this.params_;
        return vo0Var == null ? vo0.q() : vo0Var;
    }

    public final void a(vo0 vo0Var) {
        vo0Var.getClass();
        this.params_ = vo0Var;
    }

    public static so0 a(ByteString byteString, xp0 xp0Var) throws InvalidProtocolBufferException {
        return (so0) GeneratedMessageLite.a(DEFAULT_INSTANCE, byteString, xp0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f9196a[methodToInvoke.ordinal()]) {
            case 1:
                return new so0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"params_", "keySize_", "version_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<so0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (so0.class) {
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
