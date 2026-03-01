package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ProtoSyntax;

/* JADX INFO: compiled from: RawMessageInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class ir0 implements sq0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final uq0 f7976a;
    public final String b;
    public final Object[] c;
    public final int d;

    public ir0(uq0 uq0Var, String str, Object[] objArr) {
        this.f7976a = uq0Var;
        this.b = str;
        this.c = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.d = cCharAt;
            return;
        }
        int i = cCharAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 < 55296) {
                this.d = i | (cCharAt2 << i2);
                return;
            } else {
                i |= (cCharAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    @Override // supwisdom.sq0
    public boolean a() {
        return (this.d & 2) == 2;
    }

    @Override // supwisdom.sq0
    public uq0 b() {
        return this.f7976a;
    }

    @Override // supwisdom.sq0
    public ProtoSyntax c() {
        return (this.d & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }

    public Object[] d() {
        return this.c;
    }

    public String e() {
        return this.b;
    }
}
