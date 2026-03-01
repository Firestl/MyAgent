package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;

/* JADX INFO: compiled from: LazyFieldLite.java */
/* JADX INFO: loaded from: classes.dex */
public class iq0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ByteString f7973a;
    public xp0 b;
    public volatile uq0 c;
    public volatile ByteString d;

    static {
        xp0.a();
    }

    public int a() {
        if (this.d != null) {
            return this.d.size();
        }
        ByteString byteString = this.f7973a;
        if (byteString != null) {
            return byteString.size();
        }
        if (this.c != null) {
            return this.c.d();
        }
        return 0;
    }

    public uq0 b(uq0 uq0Var) {
        a(uq0Var);
        return this.c;
    }

    public uq0 c(uq0 uq0Var) {
        uq0 uq0Var2 = this.c;
        this.f7973a = null;
        this.d = null;
        this.c = uq0Var;
        return uq0Var2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iq0)) {
            return false;
        }
        iq0 iq0Var = (iq0) obj;
        uq0 uq0Var = this.c;
        uq0 uq0Var2 = iq0Var.c;
        return (uq0Var == null && uq0Var2 == null) ? b().equals(iq0Var.b()) : (uq0Var == null || uq0Var2 == null) ? uq0Var != null ? uq0Var.equals(iq0Var.b(uq0Var.a())) : b(uq0Var2.a()).equals(uq0Var2) : uq0Var.equals(uq0Var2);
    }

    public int hashCode() {
        return 1;
    }

    public ByteString b() {
        if (this.d != null) {
            return this.d;
        }
        ByteString byteString = this.f7973a;
        if (byteString != null) {
            return byteString;
        }
        synchronized (this) {
            if (this.d != null) {
                return this.d;
            }
            if (this.c == null) {
                this.d = ByteString.EMPTY;
            } else {
                this.d = this.c.c();
            }
            return this.d;
        }
    }

    public void a(uq0 uq0Var) {
        if (this.c != null) {
            return;
        }
        synchronized (this) {
            if (this.c != null) {
                return;
            }
            try {
                if (this.f7973a != null) {
                    this.c = uq0Var.f().a(this.f7973a, this.b);
                    this.d = this.f7973a;
                } else {
                    this.c = uq0Var;
                    this.d = ByteString.EMPTY;
                }
            } catch (InvalidProtocolBufferException unused) {
                this.c = uq0Var;
                this.d = ByteString.EMPTY;
            }
        }
    }
}
