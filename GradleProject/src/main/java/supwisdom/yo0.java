package supwisdom;

import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: KeyTemplate.java */
/* JADX INFO: loaded from: classes.dex */
public final class yo0 extends GeneratedMessageLite<yo0, b> implements zo0 {
    public static final yo0 DEFAULT_INSTANCE;
    public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 3;
    public static volatile dr0<yo0> PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    public int outputPrefixType_;
    public String typeUrl_ = "";
    public ByteString value_ = ByteString.EMPTY;

    /* JADX INFO: compiled from: KeyTemplate.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9903a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9903a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9903a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9903a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9903a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9903a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9903a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9903a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: KeyTemplate.java */
    public static final class b extends GeneratedMessageLite.a<yo0, b> implements zo0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a(String str) {
            h();
            ((yo0) this.b).b(str);
            return this;
        }

        public b() {
            super(yo0.DEFAULT_INSTANCE);
        }

        public b a(ByteString byteString) {
            h();
            ((yo0) this.b).a(byteString);
            return this;
        }

        public b a(OutputPrefixType outputPrefixType) {
            h();
            ((yo0) this.b).a(outputPrefixType);
            return this;
        }
    }

    static {
        yo0 yo0Var = new yo0();
        DEFAULT_INSTANCE = yo0Var;
        GeneratedMessageLite.a((Class<yo0>) yo0.class, yo0Var);
    }

    public static b r() {
        return DEFAULT_INSTANCE.k();
    }

    public final void b(String str) {
        str.getClass();
        this.typeUrl_ = str;
    }

    public OutputPrefixType n() {
        OutputPrefixType outputPrefixTypeForNumber = OutputPrefixType.forNumber(this.outputPrefixType_);
        return outputPrefixTypeForNumber == null ? OutputPrefixType.UNRECOGNIZED : outputPrefixTypeForNumber;
    }

    public String o() {
        return this.typeUrl_;
    }

    public ByteString p() {
        return this.value_;
    }

    public final void a(ByteString byteString) {
        byteString.getClass();
        this.value_ = byteString;
    }

    public final void a(OutputPrefixType outputPrefixType) {
        this.outputPrefixType_ = outputPrefixType.getNumber();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f9903a[methodToInvoke.ordinal()]) {
            case 1:
                return new yo0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"typeUrl_", "value_", "outputPrefixType_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<yo0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (yo0.class) {
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
