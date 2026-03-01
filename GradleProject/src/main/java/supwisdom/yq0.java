package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import supwisdom.cq0;
import supwisdom.hq0;

/* JADX INFO: compiled from: MessageSetSchema.java */
/* JADX INFO: loaded from: classes.dex */
public final class yq0<T> implements lr0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final uq0 f9905a;
    public final rr0<?, ?> b;
    public final boolean c;
    public final yp0<?> d;

    public yq0(rr0<?, ?> rr0Var, yp0<?> yp0Var, uq0 uq0Var) {
        this.b = rr0Var;
        this.c = yp0Var.a(uq0Var);
        this.d = yp0Var;
        this.f9905a = uq0Var;
    }

    public static <T> yq0<T> a(rr0<?, ?> rr0Var, yp0<?> yp0Var, uq0 uq0Var) {
        return new yq0<>(rr0Var, yp0Var, uq0Var);
    }

    @Override // supwisdom.lr0
    public boolean b(T t, T t2) {
        if (!this.b.b(t).equals(this.b.b(t2))) {
            return false;
        }
        if (this.c) {
            return this.d.a(t).equals(this.d.a(t2));
        }
        return true;
    }

    @Override // supwisdom.lr0
    public int c(T t) {
        int iA = a(this.b, (Object) t) + 0;
        return this.c ? iA + this.d.a(t).b() : iA;
    }

    @Override // supwisdom.lr0
    public int d(T t) {
        int iHashCode = this.b.b(t).hashCode();
        return this.c ? (iHashCode * 53) + this.d.a(t).hashCode() : iHashCode;
    }

    @Override // supwisdom.lr0
    public T newInstance() {
        return (T) this.f9905a.e().S();
    }

    @Override // supwisdom.lr0
    public void a(T t, T t2) {
        nr0.a(this.b, t, t2);
        if (this.c) {
            nr0.a(this.d, t, t2);
        }
    }

    @Override // supwisdom.lr0
    public void a(T t, Writer writer) throws IOException {
        Iterator itG = this.d.a(t).g();
        while (itG.hasNext()) {
            Map.Entry entry = (Map.Entry) itG.next();
            cq0.b bVar = (cq0.b) entry.getKey();
            if (bVar.F() == WireFormat.JavaType.MESSAGE && !bVar.D() && !bVar.isPacked()) {
                if (entry instanceof hq0.b) {
                    writer.a(bVar.getNumber(), (Object) ((hq0.b) entry).a().b());
                } else {
                    writer.a(bVar.getNumber(), entry.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        a(this.b, t, writer);
    }

    @Override // supwisdom.lr0
    public final boolean b(T t) {
        return this.d.a(t).f();
    }

    public final <UT, UB> void a(rr0<UT, UB> rr0Var, T t, Writer writer) throws IOException {
        rr0Var.a(rr0Var.b(t), writer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cb A[EDGE_INSN: B:58:0x00cb->B:34:0x00cb BREAK  A[LOOP:1: B:18:0x006d->B:61:0x006d], SYNTHETIC] */
    @Override // supwisdom.lr0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(T r11, byte[] r12, int r13, int r14, supwisdom.mp0.b r15) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.yq0.a(java.lang.Object, byte[], int, int, supwisdom.mp0$b):void");
    }

    @Override // supwisdom.lr0
    public void a(T t, jr0 jr0Var, xp0 xp0Var) throws IOException {
        a(this.b, this.d, t, jr0Var, xp0Var);
    }

    public final <UT, UB, ET extends cq0.b<ET>> void a(rr0<UT, UB> rr0Var, yp0<ET> yp0Var, T t, jr0 jr0Var, xp0 xp0Var) throws IOException {
        UB ubA = rr0Var.a(t);
        cq0<ET> cq0VarB = yp0Var.b(t);
        while (jr0Var.k() != Integer.MAX_VALUE) {
            try {
                if (!a(jr0Var, xp0Var, yp0Var, cq0VarB, rr0Var, ubA)) {
                    return;
                }
            } finally {
                rr0Var.b(t, ubA);
            }
        }
    }

    @Override // supwisdom.lr0
    public void a(T t) {
        this.b.e(t);
        this.d.c(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <UT, UB, ET extends cq0.b<ET>> boolean a(jr0 jr0Var, xp0 xp0Var, yp0<ET> yp0Var, cq0<ET> cq0Var, rr0<UT, UB> rr0Var, UB ub) throws IOException {
        int tag = jr0Var.getTag();
        if (tag != WireFormat.f2545a) {
            if (WireFormat.b(tag) == 2) {
                Object objA = yp0Var.a(xp0Var, this.f9905a, WireFormat.a(tag));
                if (objA != null) {
                    yp0Var.a(jr0Var, objA, xp0Var, cq0Var);
                    return true;
                }
                return rr0Var.b(ub, jr0Var);
            }
            return jr0Var.n();
        }
        int iF = 0;
        Object objA2 = null;
        ByteString byteStringL = null;
        while (jr0Var.k() != Integer.MAX_VALUE) {
            int tag2 = jr0Var.getTag();
            if (tag2 == WireFormat.c) {
                iF = jr0Var.f();
                objA2 = yp0Var.a(xp0Var, this.f9905a, iF);
            } else if (tag2 == WireFormat.d) {
                if (objA2 != null) {
                    yp0Var.a(jr0Var, objA2, xp0Var, cq0Var);
                } else {
                    byteStringL = jr0Var.l();
                }
            } else if (!jr0Var.n()) {
                break;
            }
        }
        if (jr0Var.getTag() != WireFormat.b) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        if (byteStringL != null) {
            if (objA2 != null) {
                yp0Var.a(byteStringL, objA2, xp0Var, cq0Var);
            } else {
                rr0Var.a(ub, iF, byteStringL);
            }
        }
        return true;
    }

    public final <UT, UB> int a(rr0<UT, UB> rr0Var, T t) {
        return rr0Var.d(rr0Var.b(t));
    }
}
