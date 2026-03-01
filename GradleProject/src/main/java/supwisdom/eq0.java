package supwisdom;

import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: GeneratedMessageInfoFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class eq0 implements tq0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final eq0 f7516a = new eq0();

    public static eq0 a() {
        return f7516a;
    }

    @Override // supwisdom.tq0
    public boolean b(Class<?> cls) {
        return GeneratedMessageLite.class.isAssignableFrom(cls);
    }

    @Override // supwisdom.tq0
    public sq0 a(Class<?> cls) {
        if (!GeneratedMessageLite.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
        }
        try {
            return (sq0) GeneratedMessageLite.a(cls.asSubclass(GeneratedMessageLite.class)).j();
        } catch (Exception e2) {
            throw new RuntimeException("Unable to get message info for " + cls.getName(), e2);
        }
    }
}
