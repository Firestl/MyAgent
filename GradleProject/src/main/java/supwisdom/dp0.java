package supwisdom;

import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import supwisdom.gq0;

/* JADX INFO: compiled from: KeysetInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class dp0 extends GeneratedMessageLite<dp0, b> implements ep0 {
    public static final dp0 DEFAULT_INSTANCE;
    public static final int KEY_INFO_FIELD_NUMBER = 2;
    public static volatile dr0<dp0> PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    public gq0.i<c> keyInfo_ = GeneratedMessageLite.m();
    public int primaryKeyId_;

    /* JADX INFO: compiled from: KeysetInfo.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7371a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f7371a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7371a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7371a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7371a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7371a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7371a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7371a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: KeysetInfo.java */
    public static final class b extends GeneratedMessageLite.a<dp0, b> implements ep0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a(int i) {
            h();
            ((dp0) this.b).c(i);
            return this;
        }

        public b() {
            super(dp0.DEFAULT_INSTANCE);
        }

        public b a(c cVar) {
            h();
            ((dp0) this.b).a(cVar);
            return this;
        }
    }

    /* JADX INFO: compiled from: KeysetInfo.java */
    public static final class c extends GeneratedMessageLite<c, a> implements d {
        public static final c DEFAULT_INSTANCE;
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        public static volatile dr0<c> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        public static final int TYPE_URL_FIELD_NUMBER = 1;
        public int keyId_;
        public int outputPrefixType_;
        public int status_;
        public String typeUrl_ = "";

        /* JADX INFO: compiled from: KeysetInfo.java */
        public static final class a extends GeneratedMessageLite.a<c, a> implements d {
            public /* synthetic */ a(a aVar) {
                this();
            }

            public a a(String str) {
                h();
                ((c) this.b).b(str);
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

        public static a p() {
            return DEFAULT_INSTANCE.k();
        }

        public final void b(String str) {
            str.getClass();
            this.typeUrl_ = str;
        }

        public int n() {
            return this.keyId_;
        }

        public final void b(int i) {
            this.keyId_ = i;
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
            switch (a.f7371a[methodToInvoke.ordinal()]) {
                case 1:
                    return new c();
                case 2:
                    return new a(aVar);
                case 3:
                    return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"typeUrl_", "status_", "keyId_", "outputPrefixType_"});
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

    /* JADX INFO: compiled from: KeysetInfo.java */
    public interface d extends vq0 {
    }

    static {
        dp0 dp0Var = new dp0();
        DEFAULT_INSTANCE = dp0Var;
        GeneratedMessageLite.a((Class<dp0>) dp0.class, dp0Var);
    }

    public static b p() {
        return DEFAULT_INSTANCE.k();
    }

    public c b(int i) {
        return this.keyInfo_.get(i);
    }

    public final void c(int i) {
        this.primaryKeyId_ = i;
    }

    public final void n() {
        if (this.keyInfo_.f()) {
            return;
        }
        this.keyInfo_ = GeneratedMessageLite.a(this.keyInfo_);
    }

    public final void a(c cVar) {
        cVar.getClass();
        n();
        this.keyInfo_.add(cVar);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f7371a[methodToInvoke.ordinal()]) {
            case 1:
                return new dp0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"primaryKeyId_", "keyInfo_", c.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<dp0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (dp0.class) {
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
