package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.FieldType;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.ProtoSyntax;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.pqc.crypto.qtesla.QTesla1p;
import sun.misc.Unsafe;
import supwisdom.gq0;
import supwisdom.mp0;
import supwisdom.oq0;

/* JADX INFO: compiled from: MessageSchema.java */
/* JADX INFO: loaded from: classes.dex */
public final class xq0<T> implements lr0<T> {
    public static final int[] r = new int[0];
    public static final Unsafe s = vr0.c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f9801a;
    public final Object[] b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final uq0 f9802e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final int[] j;
    public final int k;
    public final int l;
    public final zq0 m;
    public final lq0 n;
    public final rr0<?, ?> o;
    public final yp0<?> p;
    public final pq0 q;

    /* JADX INFO: compiled from: MessageSchema.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9803a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9803a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9803a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9803a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9803a[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9803a[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9803a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9803a[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9803a[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9803a[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9803a[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9803a[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9803a[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9803a[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9803a[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9803a[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f9803a[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f9803a[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public xq0(int[] iArr, Object[] objArr, int i, int i2, uq0 uq0Var, boolean z, boolean z2, int[] iArr2, int i3, int i4, zq0 zq0Var, lq0 lq0Var, rr0<?, ?> rr0Var, yp0<?> yp0Var, pq0 pq0Var) {
        this.f9801a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.g = uq0Var instanceof GeneratedMessageLite;
        this.h = z;
        this.f = yp0Var != null && yp0Var.a(uq0Var);
        this.i = z2;
        this.j = iArr2;
        this.k = i3;
        this.l = i4;
        this.m = zq0Var;
        this.n = lq0Var;
        this.o = rr0Var;
        this.p = yp0Var;
        this.f9802e = uq0Var;
        this.q = pq0Var;
    }

    public static <T> xq0<T> a(Class<T> cls, sq0 sq0Var, zq0 zq0Var, lq0 lq0Var, rr0<?, ?> rr0Var, yp0<?> yp0Var, pq0 pq0Var) {
        return sq0Var instanceof ir0 ? a((ir0) sq0Var, zq0Var, lq0Var, rr0Var, yp0Var, pq0Var) : a((pr0) sq0Var, zq0Var, lq0Var, rr0Var, yp0Var, pq0Var);
    }

    public static sr0 g(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        sr0 sr0Var = generatedMessageLite.unknownFields;
        if (sr0Var != sr0.f()) {
            return sr0Var;
        }
        sr0 sr0VarG = sr0.g();
        generatedMessageLite.unknownFields = sr0VarG;
        return sr0VarG;
    }

    public static <T> double h(T t, long j) {
        return ((Double) vr0.n(t, j)).doubleValue();
    }

    public static boolean h(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    public static <T> float i(T t, long j) {
        return ((Float) vr0.n(t, j)).floatValue();
    }

    public static boolean i(int i) {
        return (i & 268435456) != 0;
    }

    public static <T> int j(T t, long j) {
        return ((Integer) vr0.n(t, j)).intValue();
    }

    public static long j(int i) {
        return i & QTesla1p.maskb1;
    }

    public static int k(int i) {
        return (i & 267386880) >>> 20;
    }

    public static <T> long k(T t, long j) {
        return ((Long) vr0.n(t, j)).longValue();
    }

    @Override // supwisdom.lr0
    public boolean b(T t, T t2) {
        int length = this.f9801a.length;
        for (int i = 0; i < length; i += 3) {
            if (!b(t, t2, i)) {
                return false;
            }
        }
        if (!this.o.b(t).equals(this.o.b(t2))) {
            return false;
        }
        if (this.f) {
            return this.p.a(t).equals(this.p.a(t2));
        }
        return true;
    }

    @Override // supwisdom.lr0
    public int c(T t) {
        return this.h ? f(t) : e(t);
    }

    @Override // supwisdom.lr0
    public int d(T t) {
        int i;
        int iA;
        int length = this.f9801a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iG = g(i3);
            int iD = d(i3);
            long j = j(iG);
            int iHashCode = 37;
            switch (k(iG)) {
                case 0:
                    i = i2 * 53;
                    iA = gq0.a(Double.doubleToLongBits(vr0.j(t, j)));
                    i2 = i + iA;
                    break;
                case 1:
                    i = i2 * 53;
                    iA = Float.floatToIntBits(vr0.k(t, j));
                    i2 = i + iA;
                    break;
                case 2:
                    i = i2 * 53;
                    iA = gq0.a(vr0.m(t, j));
                    i2 = i + iA;
                    break;
                case 3:
                    i = i2 * 53;
                    iA = gq0.a(vr0.m(t, j));
                    i2 = i + iA;
                    break;
                case 4:
                    i = i2 * 53;
                    iA = vr0.l(t, j);
                    i2 = i + iA;
                    break;
                case 5:
                    i = i2 * 53;
                    iA = gq0.a(vr0.m(t, j));
                    i2 = i + iA;
                    break;
                case 6:
                    i = i2 * 53;
                    iA = vr0.l(t, j);
                    i2 = i + iA;
                    break;
                case 7:
                    i = i2 * 53;
                    iA = gq0.a(vr0.e(t, j));
                    i2 = i + iA;
                    break;
                case 8:
                    i = i2 * 53;
                    iA = ((String) vr0.n(t, j)).hashCode();
                    i2 = i + iA;
                    break;
                case 9:
                    Object objN = vr0.n(t, j);
                    if (objN != null) {
                        iHashCode = objN.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iA = vr0.n(t, j).hashCode();
                    i2 = i + iA;
                    break;
                case 11:
                    i = i2 * 53;
                    iA = vr0.l(t, j);
                    i2 = i + iA;
                    break;
                case 12:
                    i = i2 * 53;
                    iA = vr0.l(t, j);
                    i2 = i + iA;
                    break;
                case 13:
                    i = i2 * 53;
                    iA = vr0.l(t, j);
                    i2 = i + iA;
                    break;
                case 14:
                    i = i2 * 53;
                    iA = gq0.a(vr0.m(t, j));
                    i2 = i + iA;
                    break;
                case 15:
                    i = i2 * 53;
                    iA = vr0.l(t, j);
                    i2 = i + iA;
                    break;
                case 16:
                    i = i2 * 53;
                    iA = gq0.a(vr0.m(t, j));
                    i2 = i + iA;
                    break;
                case 17:
                    Object objN2 = vr0.n(t, j);
                    if (objN2 != null) {
                        iHashCode = objN2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iA = vr0.n(t, j).hashCode();
                    i2 = i + iA;
                    break;
                case 50:
                    i = i2 * 53;
                    iA = vr0.n(t, j).hashCode();
                    i2 = i + iA;
                    break;
                case 51:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = gq0.a(Double.doubleToLongBits(h(t, j)));
                        i2 = i + iA;
                    }
                    break;
                case 52:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = Float.floatToIntBits(i(t, j));
                        i2 = i + iA;
                    }
                    break;
                case 53:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = gq0.a(k(t, j));
                        i2 = i + iA;
                    }
                    break;
                case 54:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = gq0.a(k(t, j));
                        i2 = i + iA;
                    }
                    break;
                case 55:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = j(t, j);
                        i2 = i + iA;
                    }
                    break;
                case 56:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = gq0.a(k(t, j));
                        i2 = i + iA;
                    }
                    break;
                case 57:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = j(t, j);
                        i2 = i + iA;
                    }
                    break;
                case 58:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = gq0.a(g(t, j));
                        i2 = i + iA;
                    }
                    break;
                case 59:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = ((String) vr0.n(t, j)).hashCode();
                        i2 = i + iA;
                    }
                    break;
                case 60:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = vr0.n(t, j).hashCode();
                        i2 = i + iA;
                    }
                    break;
                case 61:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = vr0.n(t, j).hashCode();
                        i2 = i + iA;
                    }
                    break;
                case 62:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = j(t, j);
                        i2 = i + iA;
                    }
                    break;
                case 63:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = j(t, j);
                        i2 = i + iA;
                    }
                    break;
                case 64:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = j(t, j);
                        i2 = i + iA;
                    }
                    break;
                case 65:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = gq0.a(k(t, j));
                        i2 = i + iA;
                    }
                    break;
                case 66:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = j(t, j);
                        i2 = i + iA;
                    }
                    break;
                case 67:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = gq0.a(k(t, j));
                        i2 = i + iA;
                    }
                    break;
                case 68:
                    if (c(t, iD, i3)) {
                        i = i2 * 53;
                        iA = vr0.n(t, j).hashCode();
                        i2 = i + iA;
                    }
                    break;
            }
        }
        int iHashCode2 = (i2 * 53) + this.o.b(t).hashCode();
        return this.f ? (iHashCode2 * 53) + this.p.a(t).hashCode() : iHashCode2;
    }

    public final void e(T t, T t2, int i) {
        int iG = g(i);
        int iD = d(i);
        long j = j(iG);
        if (c(t2, iD, i)) {
            Object objN = vr0.n(t, j);
            Object objN2 = vr0.n(t2, j);
            if (objN != null && objN2 != null) {
                vr0.a(t, j, gq0.a(objN, objN2));
                d(t, iD, i);
            } else if (objN2 != null) {
                vr0.a(t, j, objN2);
                d(t, iD, i);
            }
        }
    }

    public final void f(T t, T t2, int i) {
        int iG = g(i);
        long j = j(iG);
        int iD = d(i);
        switch (k(iG)) {
            case 0:
                if (a((Object) t2, i)) {
                    vr0.a(t, j, vr0.j(t2, j));
                    b((Object) t, i);
                }
                break;
            case 1:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.k(t2, j));
                    b((Object) t, i);
                }
                break;
            case 2:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.m(t2, j));
                    b((Object) t, i);
                }
                break;
            case 3:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.m(t2, j));
                    b((Object) t, i);
                }
                break;
            case 4:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.l(t2, j));
                    b((Object) t, i);
                }
                break;
            case 5:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.m(t2, j));
                    b((Object) t, i);
                }
                break;
            case 6:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.l(t2, j));
                    b((Object) t, i);
                }
                break;
            case 7:
                if (a((Object) t2, i)) {
                    vr0.c(t, j, vr0.e(t2, j));
                    b((Object) t, i);
                }
                break;
            case 8:
                if (a((Object) t2, i)) {
                    vr0.a(t, j, vr0.n(t2, j));
                    b((Object) t, i);
                }
                break;
            case 9:
                d(t, t2, i);
                break;
            case 10:
                if (a((Object) t2, i)) {
                    vr0.a(t, j, vr0.n(t2, j));
                    b((Object) t, i);
                }
                break;
            case 11:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.l(t2, j));
                    b((Object) t, i);
                }
                break;
            case 12:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.l(t2, j));
                    b((Object) t, i);
                }
                break;
            case 13:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.l(t2, j));
                    b((Object) t, i);
                }
                break;
            case 14:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.m(t2, j));
                    b((Object) t, i);
                }
                break;
            case 15:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.l(t2, j));
                    b((Object) t, i);
                }
                break;
            case 16:
                if (a((Object) t2, i)) {
                    vr0.a((Object) t, j, vr0.m(t2, j));
                    b((Object) t, i);
                }
                break;
            case 17:
                d(t, t2, i);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.n.a(t, t2, j);
                break;
            case 50:
                nr0.a(this.q, t, t2, j);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (c(t2, iD, i)) {
                    vr0.a(t, j, vr0.n(t2, j));
                    d(t, iD, i);
                }
                break;
            case 60:
                e(t, t2, i);
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (c(t2, iD, i)) {
                    vr0.a(t, j, vr0.n(t2, j));
                    d(t, iD, i);
                }
                break;
            case 68:
                e(t, t2, i);
                break;
        }
    }

    @Override // supwisdom.lr0
    public T newInstance() {
        return (T) this.m.a(this.f9802e);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(T r13, com.google.crypto.tink.shaded.protobuf.Writer r14) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.c(java.lang.Object, com.google.crypto.tink.shaded.protobuf.Writer):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x039f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> supwisdom.xq0<T> a(supwisdom.ir0 r36, supwisdom.zq0 r37, supwisdom.lq0 r38, supwisdom.rr0<?, ?> r39, supwisdom.yp0<?> r40, supwisdom.pq0 r41) {
        /*
            Method dump skipped, instruction units count: 1038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.a(supwisdom.ir0, supwisdom.zq0, supwisdom.lq0, supwisdom.rr0, supwisdom.yp0, supwisdom.pq0):supwisdom.xq0");
    }

    public final int g(int i) {
        return this.f9801a[i + 1];
    }

    public static <T> boolean g(T t, long j) {
        return ((Boolean) vr0.n(t, j)).booleanValue();
    }

    public final boolean b(T t, T t2, int i) {
        int iG = g(i);
        long j = j(iG);
        switch (k(iG)) {
            case 0:
                return a(t, t2, i) && Double.doubleToLongBits(vr0.j(t, j)) == Double.doubleToLongBits(vr0.j(t2, j));
            case 1:
                return a(t, t2, i) && Float.floatToIntBits(vr0.k(t, j)) == Float.floatToIntBits(vr0.k(t2, j));
            case 2:
                return a(t, t2, i) && vr0.m(t, j) == vr0.m(t2, j);
            case 3:
                return a(t, t2, i) && vr0.m(t, j) == vr0.m(t2, j);
            case 4:
                return a(t, t2, i) && vr0.l(t, j) == vr0.l(t2, j);
            case 5:
                return a(t, t2, i) && vr0.m(t, j) == vr0.m(t2, j);
            case 6:
                return a(t, t2, i) && vr0.l(t, j) == vr0.l(t2, j);
            case 7:
                return a(t, t2, i) && vr0.e(t, j) == vr0.e(t2, j);
            case 8:
                return a(t, t2, i) && nr0.a(vr0.n(t, j), vr0.n(t2, j));
            case 9:
                return a(t, t2, i) && nr0.a(vr0.n(t, j), vr0.n(t2, j));
            case 10:
                return a(t, t2, i) && nr0.a(vr0.n(t, j), vr0.n(t2, j));
            case 11:
                return a(t, t2, i) && vr0.l(t, j) == vr0.l(t2, j);
            case 12:
                return a(t, t2, i) && vr0.l(t, j) == vr0.l(t2, j);
            case 13:
                return a(t, t2, i) && vr0.l(t, j) == vr0.l(t2, j);
            case 14:
                return a(t, t2, i) && vr0.m(t, j) == vr0.m(t2, j);
            case 15:
                return a(t, t2, i) && vr0.l(t, j) == vr0.l(t2, j);
            case 16:
                return a(t, t2, i) && vr0.m(t, j) == vr0.m(t2, j);
            case 17:
                return a(t, t2, i) && nr0.a(vr0.n(t, j), vr0.n(t2, j));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return nr0.a(vr0.n(t, j), vr0.n(t2, j));
            case 50:
                return nr0.a(vr0.n(t, j), vr0.n(t2, j));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return c(t, t2, i) && nr0.a(vr0.n(t, j), vr0.n(t2, j));
            default:
                return true;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int e(T t) {
        int i;
        int i2;
        int iB;
        int iB2;
        int iK;
        int iD;
        int iN;
        int iO;
        Unsafe unsafe = s;
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.f9801a.length) {
            int iG = g(i4);
            int iD2 = d(i4);
            int iK2 = k(iG);
            if (iK2 <= 17) {
                i = this.f9801a[i4 + 2];
                int i7 = 1048575 & i;
                int i8 = 1 << (i >>> 20);
                if (i7 != i3) {
                    i6 = unsafe.getInt(t, i7);
                    i3 = i7;
                }
                i2 = i8;
            } else {
                i = (!this.i || iK2 < FieldType.DOUBLE_LIST_PACKED.id() || iK2 > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.f9801a[i4 + 2] & QTesla1p.maskb1;
                i2 = 0;
            }
            long j = j(iG);
            int i9 = i3;
            switch (iK2) {
                case 0:
                    if ((i6 & i2) != 0) {
                        iB = CodedOutputStream.b(iD2, 0.0d);
                        i5 += iB;
                    }
                    break;
                case 1:
                    if ((i6 & i2) != 0) {
                        iB = CodedOutputStream.b(iD2, 0.0f);
                        i5 += iB;
                    }
                    break;
                case 2:
                    if ((i6 & i2) != 0) {
                        iB = CodedOutputStream.g(iD2, unsafe.getLong(t, j));
                        i5 += iB;
                    }
                    break;
                case 3:
                    if ((i6 & i2) != 0) {
                        iB = CodedOutputStream.j(iD2, unsafe.getLong(t, j));
                        i5 += iB;
                    }
                    break;
                case 4:
                    if ((i6 & i2) != 0) {
                        iB = CodedOutputStream.j(iD2, unsafe.getInt(t, j));
                        i5 += iB;
                    }
                    break;
                case 5:
                    if ((i6 & i2) != 0) {
                        iB = CodedOutputStream.f(iD2, 0L);
                        i5 += iB;
                    }
                    break;
                case 6:
                    if ((i6 & i2) != 0) {
                        iB = CodedOutputStream.i(iD2, 0);
                        i5 += iB;
                    }
                    break;
                case 7:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.b(iD2, true);
                        i5 += iB2;
                    }
                    break;
                case 8:
                    if ((i6 & i2) != 0) {
                        Object object = unsafe.getObject(t, j);
                        if (object instanceof ByteString) {
                            iB2 = CodedOutputStream.c(iD2, (ByteString) object);
                        } else {
                            iB2 = CodedOutputStream.b(iD2, (String) object);
                        }
                        i5 += iB2;
                    }
                    break;
                case 9:
                    if ((i6 & i2) != 0) {
                        iB2 = nr0.a(iD2, unsafe.getObject(t, j), c(i4));
                        i5 += iB2;
                    }
                    break;
                case 10:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.c(iD2, (ByteString) unsafe.getObject(t, j));
                        i5 += iB2;
                    }
                    break;
                case 11:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.m(iD2, unsafe.getInt(t, j));
                        i5 += iB2;
                    }
                    break;
                case 12:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.h(iD2, unsafe.getInt(t, j));
                        i5 += iB2;
                    }
                    break;
                case 13:
                    if ((i6 & i2) != 0) {
                        iK = CodedOutputStream.k(iD2, 0);
                        i5 += iK;
                    }
                    break;
                case 14:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.h(iD2, 0L);
                        i5 += iB2;
                    }
                    break;
                case 15:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.l(iD2, unsafe.getInt(t, j));
                        i5 += iB2;
                    }
                    break;
                case 16:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.i(iD2, unsafe.getLong(t, j));
                        i5 += iB2;
                    }
                    break;
                case 17:
                    if ((i6 & i2) != 0) {
                        iB2 = CodedOutputStream.c(iD2, (uq0) unsafe.getObject(t, j), c(i4));
                        i5 += iB2;
                    }
                    break;
                case 18:
                    iB2 = nr0.d(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 19:
                    iB2 = nr0.c(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 20:
                    iB2 = nr0.f(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 21:
                    iB2 = nr0.j(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 22:
                    iB2 = nr0.e(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 23:
                    iB2 = nr0.d(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 24:
                    iB2 = nr0.c(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 25:
                    iB2 = nr0.a(iD2, (List<?>) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 26:
                    iB2 = nr0.b(iD2, (List) unsafe.getObject(t, j));
                    i5 += iB2;
                    break;
                case 27:
                    iB2 = nr0.b(iD2, (List<?>) unsafe.getObject(t, j), c(i4));
                    i5 += iB2;
                    break;
                case 28:
                    iB2 = nr0.a(iD2, (List<ByteString>) unsafe.getObject(t, j));
                    i5 += iB2;
                    break;
                case 29:
                    iB2 = nr0.i(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 30:
                    iB2 = nr0.b(iD2, (List<Integer>) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 31:
                    iB2 = nr0.c(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 32:
                    iB2 = nr0.d(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 33:
                    iB2 = nr0.g(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 34:
                    iB2 = nr0.h(iD2, (List) unsafe.getObject(t, j), false);
                    i5 += iB2;
                    break;
                case 35:
                    iD = nr0.d((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 36:
                    iD = nr0.c((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 37:
                    iD = nr0.f((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 38:
                    iD = nr0.j((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 39:
                    iD = nr0.e((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 40:
                    iD = nr0.d((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 41:
                    iD = nr0.c((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 42:
                    iD = nr0.a((List<?>) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 43:
                    iD = nr0.i((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 44:
                    iD = nr0.b((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 45:
                    iD = nr0.c((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 46:
                    iD = nr0.d((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 47:
                    iD = nr0.g((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 48:
                    iD = nr0.h((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iK = iN + iO + iD;
                        i5 += iK;
                    }
                    break;
                case 49:
                    iB2 = nr0.a(iD2, (List<uq0>) unsafe.getObject(t, j), c(i4));
                    i5 += iB2;
                    break;
                case 50:
                    iB2 = this.q.a(iD2, unsafe.getObject(t, j), b(i4));
                    i5 += iB2;
                    break;
                case 51:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.b(iD2, 0.0d);
                        i5 += iB2;
                    }
                    break;
                case 52:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.b(iD2, 0.0f);
                        i5 += iB2;
                    }
                    break;
                case 53:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.g(iD2, k(t, j));
                        i5 += iB2;
                    }
                    break;
                case 54:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.j(iD2, k(t, j));
                        i5 += iB2;
                    }
                    break;
                case 55:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.j(iD2, j(t, j));
                        i5 += iB2;
                    }
                    break;
                case 56:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.f(iD2, 0L);
                        i5 += iB2;
                    }
                    break;
                case 57:
                    if (c(t, iD2, i4)) {
                        iK = CodedOutputStream.i(iD2, 0);
                        i5 += iK;
                    }
                    break;
                case 58:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.b(iD2, true);
                        i5 += iB2;
                    }
                    break;
                case 59:
                    if (c(t, iD2, i4)) {
                        Object object2 = unsafe.getObject(t, j);
                        if (object2 instanceof ByteString) {
                            iB2 = CodedOutputStream.c(iD2, (ByteString) object2);
                        } else {
                            iB2 = CodedOutputStream.b(iD2, (String) object2);
                        }
                        i5 += iB2;
                    }
                    break;
                case 60:
                    if (c(t, iD2, i4)) {
                        iB2 = nr0.a(iD2, unsafe.getObject(t, j), c(i4));
                        i5 += iB2;
                    }
                    break;
                case 61:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.c(iD2, (ByteString) unsafe.getObject(t, j));
                        i5 += iB2;
                    }
                    break;
                case 62:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.m(iD2, j(t, j));
                        i5 += iB2;
                    }
                    break;
                case 63:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.h(iD2, j(t, j));
                        i5 += iB2;
                    }
                    break;
                case 64:
                    if (c(t, iD2, i4)) {
                        iK = CodedOutputStream.k(iD2, 0);
                        i5 += iK;
                    }
                    break;
                case 65:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.h(iD2, 0L);
                        i5 += iB2;
                    }
                    break;
                case 66:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.l(iD2, j(t, j));
                        i5 += iB2;
                    }
                    break;
                case 67:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.i(iD2, k(t, j));
                        i5 += iB2;
                    }
                    break;
                case 68:
                    if (c(t, iD2, i4)) {
                        iB2 = CodedOutputStream.c(iD2, (uq0) unsafe.getObject(t, j), c(i4));
                        i5 += iB2;
                    }
                    break;
            }
            i4 += 3;
            i3 = i9;
        }
        int iA = i5 + a(this.o, (Object) t);
        return this.f ? iA + this.p.a(t).c() : iA;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(T r18, com.google.crypto.tink.shaded.protobuf.Writer r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.b(java.lang.Object, com.google.crypto.tink.shaded.protobuf.Writer):void");
    }

    public final int f(T t) {
        int iB;
        int iD;
        int iN;
        int iO;
        Unsafe unsafe = s;
        int i = 0;
        for (int i2 = 0; i2 < this.f9801a.length; i2 += 3) {
            int iG = g(i2);
            int iK = k(iG);
            int iD2 = d(i2);
            long j = j(iG);
            int i3 = (iK < FieldType.DOUBLE_LIST_PACKED.id() || iK > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.f9801a[i2 + 2] & QTesla1p.maskb1;
            switch (iK) {
                case 0:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.b(iD2, 0.0d);
                        i += iB;
                    }
                    break;
                case 1:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.b(iD2, 0.0f);
                        i += iB;
                    }
                    break;
                case 2:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.g(iD2, vr0.m(t, j));
                        i += iB;
                    }
                    break;
                case 3:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.j(iD2, vr0.m(t, j));
                        i += iB;
                    }
                    break;
                case 4:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.j(iD2, vr0.l(t, j));
                        i += iB;
                    }
                    break;
                case 5:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.f(iD2, 0L);
                        i += iB;
                    }
                    break;
                case 6:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.i(iD2, 0);
                        i += iB;
                    }
                    break;
                case 7:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.b(iD2, true);
                        i += iB;
                    }
                    break;
                case 8:
                    if (a((Object) t, i2)) {
                        Object objN = vr0.n(t, j);
                        if (objN instanceof ByteString) {
                            iB = CodedOutputStream.c(iD2, (ByteString) objN);
                        } else {
                            iB = CodedOutputStream.b(iD2, (String) objN);
                        }
                        i += iB;
                    }
                    break;
                case 9:
                    if (a((Object) t, i2)) {
                        iB = nr0.a(iD2, vr0.n(t, j), c(i2));
                        i += iB;
                    }
                    break;
                case 10:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.c(iD2, (ByteString) vr0.n(t, j));
                        i += iB;
                    }
                    break;
                case 11:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.m(iD2, vr0.l(t, j));
                        i += iB;
                    }
                    break;
                case 12:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.h(iD2, vr0.l(t, j));
                        i += iB;
                    }
                    break;
                case 13:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.k(iD2, 0);
                        i += iB;
                    }
                    break;
                case 14:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.h(iD2, 0L);
                        i += iB;
                    }
                    break;
                case 15:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.l(iD2, vr0.l(t, j));
                        i += iB;
                    }
                    break;
                case 16:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.i(iD2, vr0.m(t, j));
                        i += iB;
                    }
                    break;
                case 17:
                    if (a((Object) t, i2)) {
                        iB = CodedOutputStream.c(iD2, (uq0) vr0.n(t, j), c(i2));
                        i += iB;
                    }
                    break;
                case 18:
                    iB = nr0.d(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 19:
                    iB = nr0.c(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 20:
                    iB = nr0.f(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 21:
                    iB = nr0.j(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 22:
                    iB = nr0.e(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 23:
                    iB = nr0.d(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 24:
                    iB = nr0.c(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 25:
                    iB = nr0.a(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 26:
                    iB = nr0.b(iD2, e(t, j));
                    i += iB;
                    break;
                case 27:
                    iB = nr0.b(iD2, e(t, j), c(i2));
                    i += iB;
                    break;
                case 28:
                    iB = nr0.a(iD2, (List<ByteString>) e(t, j));
                    i += iB;
                    break;
                case 29:
                    iB = nr0.i(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 30:
                    iB = nr0.b(iD2, (List<Integer>) e(t, j), false);
                    i += iB;
                    break;
                case 31:
                    iB = nr0.c(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 32:
                    iB = nr0.d(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 33:
                    iB = nr0.g(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 34:
                    iB = nr0.h(iD2, e(t, j), false);
                    i += iB;
                    break;
                case 35:
                    iD = nr0.d((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 36:
                    iD = nr0.c((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 37:
                    iD = nr0.f((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 38:
                    iD = nr0.j((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 39:
                    iD = nr0.e((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 40:
                    iD = nr0.d((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 41:
                    iD = nr0.c((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 42:
                    iD = nr0.a((List<?>) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 43:
                    iD = nr0.i((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 44:
                    iD = nr0.b((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 45:
                    iD = nr0.c((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 46:
                    iD = nr0.d((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 47:
                    iD = nr0.g((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 48:
                    iD = nr0.h((List) unsafe.getObject(t, j));
                    if (iD > 0) {
                        if (this.i) {
                            unsafe.putInt(t, i3, iD);
                        }
                        iN = CodedOutputStream.n(iD2);
                        iO = CodedOutputStream.o(iD);
                        iB = iN + iO + iD;
                        i += iB;
                    }
                    break;
                case 49:
                    iB = nr0.a(iD2, (List<uq0>) e(t, j), c(i2));
                    i += iB;
                    break;
                case 50:
                    iB = this.q.a(iD2, vr0.n(t, j), b(i2));
                    i += iB;
                    break;
                case 51:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.b(iD2, 0.0d);
                        i += iB;
                    }
                    break;
                case 52:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.b(iD2, 0.0f);
                        i += iB;
                    }
                    break;
                case 53:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.g(iD2, k(t, j));
                        i += iB;
                    }
                    break;
                case 54:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.j(iD2, k(t, j));
                        i += iB;
                    }
                    break;
                case 55:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.j(iD2, j(t, j));
                        i += iB;
                    }
                    break;
                case 56:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.f(iD2, 0L);
                        i += iB;
                    }
                    break;
                case 57:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.i(iD2, 0);
                        i += iB;
                    }
                    break;
                case 58:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.b(iD2, true);
                        i += iB;
                    }
                    break;
                case 59:
                    if (c(t, iD2, i2)) {
                        Object objN2 = vr0.n(t, j);
                        if (objN2 instanceof ByteString) {
                            iB = CodedOutputStream.c(iD2, (ByteString) objN2);
                        } else {
                            iB = CodedOutputStream.b(iD2, (String) objN2);
                        }
                        i += iB;
                    }
                    break;
                case 60:
                    if (c(t, iD2, i2)) {
                        iB = nr0.a(iD2, vr0.n(t, j), c(i2));
                        i += iB;
                    }
                    break;
                case 61:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.c(iD2, (ByteString) vr0.n(t, j));
                        i += iB;
                    }
                    break;
                case 62:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.m(iD2, j(t, j));
                        i += iB;
                    }
                    break;
                case 63:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.h(iD2, j(t, j));
                        i += iB;
                    }
                    break;
                case 64:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.k(iD2, 0);
                        i += iB;
                    }
                    break;
                case 65:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.h(iD2, 0L);
                        i += iB;
                    }
                    break;
                case 66:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.l(iD2, j(t, j));
                        i += iB;
                    }
                    break;
                case 67:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.i(iD2, k(t, j));
                        i += iB;
                    }
                    break;
                case 68:
                    if (c(t, iD2, i2)) {
                        iB = CodedOutputStream.c(iD2, (uq0) vr0.n(t, j), c(i2));
                        i += iB;
                    }
                    break;
            }
        }
        return i + a(this.o, (Object) t);
    }

    public final void d(T t, T t2, int i) {
        long j = j(g(i));
        if (a((Object) t2, i)) {
            Object objN = vr0.n(t, j);
            Object objN2 = vr0.n(t2, j);
            if (objN != null && objN2 != null) {
                vr0.a(t, j, gq0.a(objN, objN2));
                b((Object) t, i);
            } else if (objN2 != null) {
                vr0.a(t, j, objN2);
                b((Object) t, i);
            }
        }
    }

    public static Field a(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(T r11, com.google.crypto.tink.shaded.protobuf.Writer r12) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1586
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.d(java.lang.Object, com.google.crypto.tink.shaded.protobuf.Writer):void");
    }

    public static <T> xq0<T> a(pr0 pr0Var, zq0 zq0Var, lq0 lq0Var, rr0<?, ?> rr0Var, yp0<?> yp0Var, pq0 pq0Var) {
        boolean z = pr0Var.c() == ProtoSyntax.PROTO3;
        bq0[] bq0VarArrE = pr0Var.e();
        if (bq0VarArrE.length == 0) {
            int length = bq0VarArrE.length;
            int[] iArr = new int[length * 3];
            Object[] objArr = new Object[length * 2];
            if (bq0VarArrE.length <= 0) {
                int[] iArrD = pr0Var.d();
                if (iArrD == null) {
                    iArrD = r;
                }
                if (bq0VarArrE.length <= 0) {
                    int[] iArr2 = 0 == 0 ? r : null;
                    int[] iArr3 = 0 == 0 ? r : null;
                    int[] iArr4 = new int[iArrD.length + iArr2.length + iArr3.length];
                    System.arraycopy(iArrD, 0, iArr4, 0, iArrD.length);
                    System.arraycopy(iArr2, 0, iArr4, iArrD.length, iArr2.length);
                    System.arraycopy(iArr3, 0, iArr4, iArrD.length + iArr2.length, iArr3.length);
                    return new xq0<>(iArr, objArr, 0, 0, pr0Var.b(), z, true, iArr4, iArrD.length, iArrD.length + iArr2.length, zq0Var, lq0Var, rr0Var, yp0Var, pq0Var);
                }
                bq0VarArrE[0].a();
                throw null;
            }
            bq0VarArrE[0].b();
            throw null;
        }
        bq0VarArrE[0].a();
        throw null;
    }

    @Override // supwisdom.lr0
    public void a(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.f9801a.length; i += 3) {
                f(t, t2, i);
            }
            nr0.a(this.o, t, t2);
            if (this.f) {
                nr0.a(this.p, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    public final <UT, UB> int a(rr0<UT, UB> rr0Var, T t) {
        return rr0Var.c(rr0Var.b(t));
    }

    @Override // supwisdom.lr0
    public void a(T t, Writer writer) throws IOException {
        if (writer.a() == Writer.FieldOrder.DESCENDING) {
            d(t, writer);
        } else if (this.h) {
            c(t, writer);
        } else {
            b((Object) t, writer);
        }
    }

    public final <K, V> void a(Writer writer, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            writer.a(i, this.q.b(b(i2)), this.q.f(obj));
        }
    }

    public final <UT, UB> void a(rr0<UT, UB> rr0Var, T t, Writer writer) throws IOException {
        rr0Var.b(rr0Var.b(t), writer);
    }

    @Override // supwisdom.lr0
    public void a(T t, jr0 jr0Var, xp0 xp0Var) throws IOException {
        if (xp0Var != null) {
            a(this.o, this.p, t, jr0Var, xp0Var);
            return;
        }
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0075, code lost:
    
        r0 = r16.k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0079, code lost:
    
        if (r0 >= r16.l) goto L357;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007b, code lost:
    
        r13 = a(r19, r16.j[r0], r13, r17);
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
    
        if (r13 == null) goto L361;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
    
        r17.b(r19, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008b, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <UT, UB, ET extends supwisdom.cq0.b<ET>> void a(supwisdom.rr0<UT, UB> r17, supwisdom.yp0<ET> r18, T r19, supwisdom.jr0 r20, supwisdom.xp0 r21) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.a(supwisdom.rr0, supwisdom.yp0, java.lang.Object, supwisdom.jr0, supwisdom.xp0):void");
    }

    public final lr0 c(int i) {
        int i2 = (i / 3) * 2;
        lr0 lr0Var = (lr0) this.b[i2];
        if (lr0Var != null) {
            return lr0Var;
        }
        lr0<T> lr0VarA = fr0.a().a((Class) this.b[i2 + 1]);
        this.b[i2] = lr0VarA;
        return lr0VarA;
    }

    public static <T> float c(T t, long j) {
        return vr0.k(t, j);
    }

    public final boolean c(T t, int i, int i2) {
        return vr0.l(t, (long) (f(i2) & QTesla1p.maskb1)) == i;
    }

    public final boolean c(T t, T t2, int i) {
        long jF = f(i) & QTesla1p.maskb1;
        return vr0.l(t, jF) == vr0.l(t2, jF);
    }

    public final Object b(int i) {
        return this.b[(i / 3) * 2];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0229, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x022c, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01dc, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x020a, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x005b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int b(T r28, byte[] r29, int r30, int r31, supwisdom.mp0.b r32) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.b(java.lang.Object, byte[], int, int, supwisdom.mp0$b):int");
    }

    public static List<?> e(Object obj, long j) {
        return (List) vr0.n(obj, j);
    }

    public final int e(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return b(i, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0078  */
    @Override // supwisdom.lr0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(T r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = -1
            r2 = 0
            r3 = 0
        L4:
            int r4 = r12.k
            r5 = 1
            if (r2 >= r4) goto L94
            int[] r4 = r12.j
            r4 = r4[r2]
            int r6 = r12.d(r4)
            int r7 = r12.g(r4)
            boolean r8 = r12.h
            if (r8 != 0) goto L31
            int[] r8 = r12.f9801a
            int r9 = r4 + 2
            r8 = r8[r9]
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r9 & r8
            int r8 = r8 >>> 20
            int r5 = r5 << r8
            if (r9 == r1) goto L32
            sun.misc.Unsafe r1 = supwisdom.xq0.s
            long r10 = (long) r9
            int r3 = r1.getInt(r13, r10)
            r1 = r9
            goto L32
        L31:
            r5 = 0
        L32:
            boolean r8 = i(r7)
            if (r8 == 0) goto L3f
            boolean r8 = r12.a(r13, r4, r3, r5)
            if (r8 != 0) goto L3f
            return r0
        L3f:
            int r8 = k(r7)
            r9 = 9
            if (r8 == r9) goto L7f
            r9 = 17
            if (r8 == r9) goto L7f
            r5 = 27
            if (r8 == r5) goto L78
            r5 = 60
            if (r8 == r5) goto L67
            r5 = 68
            if (r8 == r5) goto L67
            r5 = 49
            if (r8 == r5) goto L78
            r5 = 50
            if (r8 == r5) goto L60
            goto L90
        L60:
            boolean r4 = r12.b(r13, r7, r4)
            if (r4 != 0) goto L90
            return r0
        L67:
            boolean r5 = r12.c(r13, r6, r4)
            if (r5 == 0) goto L90
            supwisdom.lr0 r4 = r12.c(r4)
            boolean r4 = a(r13, r7, r4)
            if (r4 != 0) goto L90
            return r0
        L78:
            boolean r4 = r12.a(r13, r7, r4)
            if (r4 != 0) goto L90
            return r0
        L7f:
            boolean r5 = r12.a(r13, r4, r3, r5)
            if (r5 == 0) goto L90
            supwisdom.lr0 r4 = r12.c(r4)
            boolean r4 = a(r13, r7, r4)
            if (r4 != 0) goto L90
            return r0
        L90:
            int r2 = r2 + 1
            goto L4
        L94:
            boolean r1 = r12.f
            if (r1 == 0) goto La5
            supwisdom.yp0<?> r1 = r12.p
            supwisdom.cq0 r13 = r1.a(r13)
            boolean r13 = r13.f()
            if (r13 != 0) goto La5
            return r0
        La5:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.b(java.lang.Object):boolean");
    }

    public final int d(int i) {
        return this.f9801a[i];
    }

    public final int f(int i) {
        return this.f9801a[i + 2];
    }

    public static <T> int d(T t, long j) {
        return vr0.l(t, j);
    }

    public static <T> long f(T t, long j) {
        return vr0.m(t, j);
    }

    public final void d(T t, int i, int i2) {
        vr0.a((Object) t, f(i2) & QTesla1p.maskb1, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [supwisdom.lr0] */
    public final boolean b(T t, int i, int i2) {
        Map<?, ?> mapF = this.q.f(vr0.n(t, j(i)));
        if (mapF.isEmpty()) {
            return true;
        }
        if (this.q.b(b(i2)).c.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? A = 0;
        for (Object obj : mapF.values()) {
            A = A;
            if (A == 0) {
                A = fr0.a().a((Class) obj.getClass());
            }
            if (!A.b(obj)) {
                return false;
            }
        }
        return true;
    }

    public final void b(Object obj, int i, jr0 jr0Var) throws IOException {
        if (h(i)) {
            jr0Var.m(this.n.b(obj, j(i)));
        } else {
            jr0Var.l(this.n.b(obj, j(i)));
        }
    }

    public static <T> double b(T t, long j) {
        return vr0.j(t, j);
    }

    public final void b(T t, int i) {
        if (this.h) {
            return;
        }
        int iF = f(i);
        long j = iF & QTesla1p.maskb1;
        vr0.a((Object) t, j, vr0.l(t, j) | (1 << (iF >>> 20)));
    }

    public final int b(int i, int i2) {
        int length = (this.f9801a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int iD = d(i4);
            if (i == iD) {
                return i4;
            }
            if (i < iD) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public final int a(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class<?> cls, mp0.b bVar) throws IOException {
        switch (a.f9803a[fieldType.ordinal()]) {
            case 1:
                int iE = mp0.e(bArr, i, bVar);
                bVar.c = Boolean.valueOf(bVar.b != 0);
                return iE;
            case 2:
                return mp0.a(bArr, i, bVar);
            case 3:
                bVar.c = Double.valueOf(mp0.a(bArr, i));
                return i + 8;
            case 4:
            case 5:
                bVar.c = Integer.valueOf(mp0.b(bArr, i));
                return i + 4;
            case 6:
            case 7:
                bVar.c = Long.valueOf(mp0.c(bArr, i));
                return i + 8;
            case 8:
                bVar.c = Float.valueOf(mp0.d(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int iD = mp0.d(bArr, i, bVar);
                bVar.c = Integer.valueOf(bVar.f8414a);
                return iD;
            case 12:
            case 13:
                int iE2 = mp0.e(bArr, i, bVar);
                bVar.c = Long.valueOf(bVar.b);
                return iE2;
            case 14:
                return mp0.a(fr0.a().a((Class) cls), bArr, i, i2, bVar);
            case 15:
                int iD2 = mp0.d(bArr, i, bVar);
                bVar.c = Integer.valueOf(rp0.e(bVar.f8414a));
                return iD2;
            case 16:
                int iE3 = mp0.e(bArr, i, bVar);
                bVar.c = Long.valueOf(rp0.a(bVar.b));
                return iE3;
            case 17:
                return mp0.c(bArr, i, bVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <K, V> int a(byte[] bArr, int i, int i2, oq0.a<K, V> aVar, Map<K, V> map, mp0.b bVar) throws IOException {
        int iA;
        int iD = mp0.d(bArr, i, bVar);
        int i3 = bVar.f8414a;
        if (i3 >= 0 && i3 <= i2 - iD) {
            int i4 = iD + i3;
            Object obj = aVar.b;
            Object obj2 = aVar.d;
            while (iD < i4) {
                int i5 = iD + 1;
                int i6 = bArr[iD];
                if (i6 < 0) {
                    iA = mp0.a(i6, bArr, i5, bVar);
                    i6 = bVar.f8414a;
                } else {
                    iA = i5;
                }
                int i7 = i6 >>> 3;
                int i8 = i6 & 7;
                if (i7 != 1) {
                    if (i7 == 2 && i8 == aVar.c.getWireType()) {
                        iD = a(bArr, iA, i2, aVar.c, aVar.d.getClass(), bVar);
                        obj2 = bVar.c;
                    } else {
                        iD = mp0.a(i6, bArr, iA, i2, bVar);
                    }
                } else if (i8 == aVar.f8699a.getWireType()) {
                    iD = a(bArr, iA, i2, aVar.f8699a, (Class<?>) null, bVar);
                    obj = bVar.c;
                } else {
                    iD = mp0.a(i6, bArr, iA, i2, bVar);
                }
            }
            if (iD == i4) {
                map.put(obj, obj2);
                return i4;
            }
            throw InvalidProtocolBufferException.parseFailure();
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int a(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, mp0.b bVar) throws IOException {
        int iK;
        gq0.i iVarA2 = (gq0.i) s.getObject(t, j2);
        if (!iVarA2.f()) {
            int size = iVarA2.size();
            iVarA2 = iVarA2.a2(size == 0 ? 10 : size * 2);
            s.putObject(t, j2, iVarA2);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    return mp0.b(bArr, i, iVarA2, bVar);
                }
                return i5 == 1 ? mp0.c(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 19:
            case 36:
                if (i5 == 2) {
                    return mp0.e(bArr, i, iVarA2, bVar);
                }
                return i5 == 5 ? mp0.f(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    return mp0.i(bArr, i, iVarA2, bVar);
                }
                return i5 == 0 ? mp0.l(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return mp0.h(bArr, i, iVarA2, bVar);
                }
                return i5 == 0 ? mp0.k(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    return mp0.d(bArr, i, iVarA2, bVar);
                }
                return i5 == 1 ? mp0.e(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    return mp0.c(bArr, i, iVarA2, bVar);
                }
                return i5 == 5 ? mp0.d(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 25:
            case 42:
                if (i5 == 2) {
                    return mp0.a(bArr, i, (gq0.i<?>) iVarA2, bVar);
                }
                return i5 == 0 ? mp0.a(i3, bArr, i, i2, (gq0.i<?>) iVarA2, bVar) : i;
            case 26:
                if (i5 != 2) {
                    return i;
                }
                if ((j & 536870912) == 0) {
                    return mp0.i(i3, bArr, i, i2, iVarA2, bVar);
                }
                return mp0.j(i3, bArr, i, i2, iVarA2, bVar);
            case 27:
                return i5 == 2 ? mp0.b(c(i6), i3, bArr, i, i2, iVarA2, bVar) : i;
            case 28:
                return i5 == 2 ? mp0.b(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 30:
            case 44:
                if (i5 == 2) {
                    iK = mp0.h(bArr, i, iVarA2, bVar);
                } else {
                    if (i5 != 0) {
                        return i;
                    }
                    iK = mp0.k(i3, bArr, i, i2, iVarA2, bVar);
                }
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t;
                sr0 sr0Var = generatedMessageLite.unknownFields;
                if (sr0Var == sr0.f()) {
                    sr0Var = null;
                }
                sr0 sr0Var2 = (sr0) nr0.a(i4, (List<Integer>) iVarA2, a(i6), sr0Var, (rr0<UT, sr0>) this.o);
                if (sr0Var2 != null) {
                    generatedMessageLite.unknownFields = sr0Var2;
                }
                return iK;
            case 33:
            case 47:
                if (i5 == 2) {
                    return mp0.f(bArr, i, iVarA2, bVar);
                }
                return i5 == 0 ? mp0.g(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 34:
            case 48:
                if (i5 == 2) {
                    return mp0.g(bArr, i, iVarA2, bVar);
                }
                return i5 == 0 ? mp0.h(i3, bArr, i, i2, iVarA2, bVar) : i;
            case 49:
                return i5 == 3 ? mp0.a(c(i6), i3, bArr, i, i2, iVarA2, bVar) : i;
            default:
                return i;
        }
    }

    public final <K, V> int a(T t, byte[] bArr, int i, int i2, int i3, long j, mp0.b bVar) throws IOException {
        Unsafe unsafe = s;
        Object objB = b(i3);
        Object object = unsafe.getObject(t, j);
        if (this.q.e(object)) {
            Object objD = this.q.d(objB);
            this.q.a(objD, object);
            unsafe.putObject(t, j, objD);
            object = objD;
        }
        return a(bArr, i, i2, this.q.b(objB), this.q.c(object), bVar);
    }

    public final int a(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, mp0.b bVar) throws IOException {
        Unsafe unsafe = s;
        long j2 = this.f9801a[i8 + 2] & QTesla1p.maskb1;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Double.valueOf(mp0.a(bArr, i)));
                int i9 = i + 8;
                unsafe.putInt(t, j2, i4);
                return i9;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Float.valueOf(mp0.d(bArr, i)));
                int i10 = i + 4;
                unsafe.putInt(t, j2, i4);
                return i10;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                int iE = mp0.e(bArr, i, bVar);
                unsafe.putObject(t, j, Long.valueOf(bVar.b));
                unsafe.putInt(t, j2, i4);
                return iE;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                int iD = mp0.d(bArr, i, bVar);
                unsafe.putObject(t, j, Integer.valueOf(bVar.f8414a));
                unsafe.putInt(t, j2, i4);
                return iD;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Long.valueOf(mp0.c(bArr, i)));
                int i11 = i + 8;
                unsafe.putInt(t, j2, i4);
                return i11;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Integer.valueOf(mp0.b(bArr, i)));
                int i12 = i + 4;
                unsafe.putInt(t, j2, i4);
                return i12;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                int iE2 = mp0.e(bArr, i, bVar);
                unsafe.putObject(t, j, Boolean.valueOf(bVar.b != 0));
                unsafe.putInt(t, j2, i4);
                return iE2;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iD2 = mp0.d(bArr, i, bVar);
                int i13 = bVar.f8414a;
                if (i13 == 0) {
                    unsafe.putObject(t, j, "");
                } else {
                    if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !wr0.d(bArr, iD2, iD2 + i13)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe.putObject(t, j, new String(bArr, iD2, i13, gq0.f7764a));
                    iD2 += i13;
                }
                unsafe.putInt(t, j2, i4);
                return iD2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iA = mp0.a(c(i8), bArr, i, i2, bVar);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, bVar.c);
                } else {
                    unsafe.putObject(t, j, gq0.a(object, bVar.c));
                }
                unsafe.putInt(t, j2, i4);
                return iA;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int iA2 = mp0.a(bArr, i, bVar);
                unsafe.putObject(t, j, bVar.c);
                unsafe.putInt(t, j2, i4);
                return iA2;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iD3 = mp0.d(bArr, i, bVar);
                int i14 = bVar.f8414a;
                gq0.e eVarA = a(i8);
                if (eVarA != null && !eVarA.a(i14)) {
                    g(t).a(i3, Long.valueOf(i14));
                } else {
                    unsafe.putObject(t, j, Integer.valueOf(i14));
                    unsafe.putInt(t, j2, i4);
                }
                return iD3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                int iD4 = mp0.d(bArr, i, bVar);
                unsafe.putObject(t, j, Integer.valueOf(rp0.e(bVar.f8414a)));
                unsafe.putInt(t, j2, i4);
                return iD4;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                int iE3 = mp0.e(bArr, i, bVar);
                unsafe.putObject(t, j, Long.valueOf(rp0.a(bVar.b)));
                unsafe.putInt(t, j2, i4);
                return iE3;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                int iA3 = mp0.a(c(i8), bArr, i, i2, (i3 & (-8)) | 4, bVar);
                Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object2 == null) {
                    unsafe.putObject(t, j, bVar.c);
                } else {
                    unsafe.putObject(t, j, gq0.a(object2, bVar.c));
                }
                unsafe.putInt(t, j2, i4);
                return iA3;
            default:
                return i;
        }
    }

    public final gq0.e a(int i) {
        return (gq0.e) this.b[((i / 3) * 2) + 1];
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x0345, code lost:
    
        if (r0 != r11) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0347, code lost:
    
        r15 = r29;
        r14 = r30;
        r12 = r31;
        r13 = r33;
        r11 = r34;
        r9 = r35;
        r1 = r17;
        r3 = r18;
        r7 = r19;
        r2 = r20;
        r6 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x038e, code lost:
    
        if (r0 != r15) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x03b1, code lost:
    
        if (r0 != r15) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x03b4, code lost:
    
        r2 = r0;
        r8 = r18;
        r0 = r34;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x008a. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int a(T r30, byte[] r31, int r32, int r33, int r34, supwisdom.mp0.b r35) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1154
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xq0.a(java.lang.Object, byte[], int, int, int, supwisdom.mp0$b):int");
    }

    @Override // supwisdom.lr0
    public void a(T t, byte[] bArr, int i, int i2, mp0.b bVar) throws IOException {
        if (this.h) {
            b(t, bArr, i, i2, bVar);
        } else {
            a(t, bArr, i, i2, 0, bVar);
        }
    }

    @Override // supwisdom.lr0
    public void a(T t) {
        int i;
        int i2 = this.k;
        while (true) {
            i = this.l;
            if (i2 >= i) {
                break;
            }
            long j = j(g(this.j[i2]));
            Object objN = vr0.n(t, j);
            if (objN != null) {
                vr0.a(t, j, this.q.a(objN));
            }
            i2++;
        }
        int length = this.j.length;
        while (i < length) {
            this.n.a(t, this.j[i]);
            i++;
        }
        this.o.e(t);
        if (this.f) {
            this.p.c(t);
        }
    }

    public final <K, V> void a(Object obj, int i, Object obj2, xp0 xp0Var, jr0 jr0Var) throws IOException {
        long j = j(g(i));
        Object objN = vr0.n(obj, j);
        if (objN == null) {
            objN = this.q.d(obj2);
            vr0.a(obj, j, objN);
        } else if (this.q.e(objN)) {
            Object objD = this.q.d(obj2);
            this.q.a(objD, objN);
            vr0.a(obj, j, objD);
            objN = objD;
        }
        jr0Var.a(this.q.c(objN), this.q.b(obj2), xp0Var);
    }

    public final <UT, UB> UB a(Object obj, int i, UB ub, rr0<UT, UB> rr0Var) {
        gq0.e eVarA;
        int iD = d(i);
        Object objN = vr0.n(obj, j(g(i)));
        return (objN == null || (eVarA = a(i)) == null) ? ub : (UB) a(i, iD, this.q.c(objN), eVarA, ub, rr0Var);
    }

    public final <K, V, UT, UB> UB a(int i, int i2, Map<K, V> map, gq0.e eVar, UB ub, rr0<UT, UB> rr0Var) {
        oq0.a<?, ?> aVarB = this.q.b(b(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!eVar.a(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = rr0Var.a();
                }
                ByteString.h hVarNewCodedBuilder = ByteString.newCodedBuilder(oq0.a(aVarB, next.getKey(), next.getValue()));
                try {
                    oq0.a(hVarNewCodedBuilder.b(), aVarB, next.getKey(), next.getValue());
                    rr0Var.a(ub, i2, hVarNewCodedBuilder.a());
                    it.remove();
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(Object obj, int i, lr0 lr0Var) {
        return lr0Var.b(vr0.n(obj, j(i)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <N> boolean a(Object obj, int i, int i2) {
        List list = (List) vr0.n(obj, j(i));
        if (list.isEmpty()) {
            return true;
        }
        lr0 lr0VarC = c(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!lr0VarC.b(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public final void a(int i, Object obj, Writer writer) throws IOException {
        if (obj instanceof String) {
            writer.a(i, (String) obj);
        } else {
            writer.a(i, (ByteString) obj);
        }
    }

    public final void a(Object obj, int i, jr0 jr0Var) throws IOException {
        if (h(i)) {
            vr0.a(obj, j(i), jr0Var.q());
        } else if (this.g) {
            vr0.a(obj, j(i), jr0Var.j());
        } else {
            vr0.a(obj, j(i), jr0Var.l());
        }
    }

    public final <E> void a(Object obj, int i, jr0 jr0Var, lr0<E> lr0Var, xp0 xp0Var) throws IOException {
        jr0Var.a(this.n.b(obj, j(i)), lr0Var, xp0Var);
    }

    public final <E> void a(Object obj, long j, jr0 jr0Var, lr0<E> lr0Var, xp0 xp0Var) throws IOException {
        jr0Var.b(this.n.b(obj, j), lr0Var, xp0Var);
    }

    public static <T> boolean a(T t, long j) {
        return vr0.e(t, j);
    }

    public final boolean a(T t, T t2, int i) {
        return a((Object) t, i) == a((Object) t2, i);
    }

    public final boolean a(T t, int i, int i2, int i3) {
        if (this.h) {
            return a((Object) t, i);
        }
        return (i2 & i3) != 0;
    }

    public final boolean a(T t, int i) {
        if (this.h) {
            int iG = g(i);
            long j = j(iG);
            switch (k(iG)) {
                case 0:
                    return vr0.j(t, j) != 0.0d;
                case 1:
                    return vr0.k(t, j) != 0.0f;
                case 2:
                    return vr0.m(t, j) != 0;
                case 3:
                    return vr0.m(t, j) != 0;
                case 4:
                    return vr0.l(t, j) != 0;
                case 5:
                    return vr0.m(t, j) != 0;
                case 6:
                    return vr0.l(t, j) != 0;
                case 7:
                    return vr0.e(t, j);
                case 8:
                    Object objN = vr0.n(t, j);
                    if (objN instanceof String) {
                        return !((String) objN).isEmpty();
                    }
                    if (objN instanceof ByteString) {
                        return !ByteString.EMPTY.equals(objN);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return vr0.n(t, j) != null;
                case 10:
                    return !ByteString.EMPTY.equals(vr0.n(t, j));
                case 11:
                    return vr0.l(t, j) != 0;
                case 12:
                    return vr0.l(t, j) != 0;
                case 13:
                    return vr0.l(t, j) != 0;
                case 14:
                    return vr0.m(t, j) != 0;
                case 15:
                    return vr0.l(t, j) != 0;
                case 16:
                    return vr0.m(t, j) != 0;
                case 17:
                    return vr0.n(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iF = f(i);
        return (vr0.l(t, (long) (iF & QTesla1p.maskb1)) & (1 << (iF >>> 20))) != 0;
    }

    public final int a(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return b(i, i2);
    }
}
