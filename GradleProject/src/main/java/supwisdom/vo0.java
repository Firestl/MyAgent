package supwisdom;

import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: HmacParams.java */
/* JADX INFO: loaded from: classes.dex */
public final class vo0 extends GeneratedMessageLite<vo0, b> implements wo0 {
    public static final vo0 DEFAULT_INSTANCE;
    public static final int HASH_FIELD_NUMBER = 1;
    public static volatile dr0<vo0> PARSER = null;
    public static final int TAG_SIZE_FIELD_NUMBER = 2;
    public int hash_;
    public int tagSize_;

    /* JADX INFO: compiled from: HmacParams.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9536a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9536a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9536a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9536a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9536a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9536a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9536a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9536a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: HmacParams.java */
    public static final class b extends GeneratedMessageLite.a<vo0, b> implements wo0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a(HashType hashType) {
            h();
            ((vo0) this.b).a(hashType);
            return this;
        }

        public b() {
            super(vo0.DEFAULT_INSTANCE);
        }

        public b a(int i) {
            h();
            ((vo0) this.b).b(i);
            return this;
        }
    }

    static {
        vo0 vo0Var = new vo0();
        DEFAULT_INSTANCE = vo0Var;
        GeneratedMessageLite.a((Class<vo0>) vo0.class, vo0Var);
    }

    public static vo0 q() {
        return DEFAULT_INSTANCE;
    }

    public static b r() {
        return DEFAULT_INSTANCE.k();
    }

    public final void b(int i) {
        this.tagSize_ = i;
    }

    public HashType n() {
        HashType hashTypeForNumber = HashType.forNumber(this.hash_);
        return hashTypeForNumber == null ? HashType.UNRECOGNIZED : hashTypeForNumber;
    }

    public int o() {
        return this.tagSize_;
    }

    public final void a(HashType hashType) {
        this.hash_ = hashType.getNumber();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f9536a[methodToInvoke.ordinal()]) {
            case 1:
                return new vo0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"hash_", "tagSize_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<vo0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (vo0.class) {
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
