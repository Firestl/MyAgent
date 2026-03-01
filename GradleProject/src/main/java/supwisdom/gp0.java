package supwisdom;

import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import supwisdom.gq0;

/* JADX INFO: compiled from: RegistryConfig.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class gp0 extends GeneratedMessageLite<gp0, b> implements hp0 {
    public static final int CONFIG_NAME_FIELD_NUMBER = 1;
    public static final gp0 DEFAULT_INSTANCE;
    public static final int ENTRY_FIELD_NUMBER = 2;
    public static volatile dr0<gp0> PARSER;
    public String configName_ = "";
    public gq0.i<ap0> entry_ = GeneratedMessageLite.m();

    /* JADX INFO: compiled from: RegistryConfig.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7755a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f7755a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7755a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7755a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7755a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7755a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7755a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7755a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: RegistryConfig.java */
    public static final class b extends GeneratedMessageLite.a<gp0, b> implements hp0 {
        public /* synthetic */ b(a aVar) {
            this();
        }

        public b() {
            super(gp0.DEFAULT_INSTANCE);
        }
    }

    static {
        gp0 gp0Var = new gp0();
        DEFAULT_INSTANCE = gp0Var;
        GeneratedMessageLite.a((Class<gp0>) gp0.class, gp0Var);
    }

    public static gp0 o() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        switch (a.f7755a[methodToInvoke.ordinal()]) {
            case 1:
                return new gp0();
            case 2:
                return new b(aVar);
            case 3:
                return GeneratedMessageLite.a(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"configName_", "entry_", ap0.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                dr0<gp0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (gp0.class) {
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
