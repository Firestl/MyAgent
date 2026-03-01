package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;
import supwisdom.gq0;

/* JADX INFO: compiled from: ArrayDecoders.java */
/* JADX INFO: loaded from: classes.dex */
public final class mp0 {

    /* JADX INFO: compiled from: ArrayDecoders.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8413a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f8413a = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8413a[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8413a[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8413a[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8413a[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8413a[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8413a[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8413a[WireFormat.FieldType.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8413a[WireFormat.FieldType.FIXED32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8413a[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8413a[WireFormat.FieldType.BOOL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f8413a[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f8413a[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f8413a[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f8413a[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f8413a[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f8413a[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f8413a[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* JADX INFO: compiled from: ArrayDecoders.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8414a;
        public long b;
        public Object c;
        public final xp0 d;

        public b(xp0 xp0Var) {
            if (xp0Var == null) {
                throw null;
            }
            this.d = xp0Var;
        }
    }

    public static int a(int i, byte[] bArr, int i2, b bVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            bVar.f8414a = i3 | (b2 << 7);
            return i4;
        }
        int i5 = i3 | ((b2 & 127) << 7);
        int i6 = i4 + 1;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            bVar.f8414a = i5 | (b3 << com.umeng.analytics.pro.db.l);
            return i6;
        }
        int i7 = i5 | ((b3 & 127) << 14);
        int i8 = i6 + 1;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            bVar.f8414a = i7 | (b4 << 21);
            return i8;
        }
        int i9 = i7 | ((b4 & 127) << 21);
        int i10 = i8 + 1;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            bVar.f8414a = i9 | (b5 << 28);
            return i10;
        }
        int i11 = i9 | ((b5 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                bVar.f8414a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static long c(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public static int d(byte[] bArr, int i, b bVar) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            return a((int) b2, bArr, i2, bVar);
        }
        bVar.f8414a = b2;
        return i2;
    }

    public static int e(byte[] bArr, int i, b bVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j < 0) {
            return a(j, bArr, i2, bVar);
        }
        bVar.b = j;
        return i2;
    }

    public static int f(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        dq0 dq0Var = (dq0) iVar;
        dq0Var.a(d(bArr, i2));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int iD = d(bArr, i4, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            dq0Var.a(d(bArr, iD));
            i4 = iD + 4;
        }
        return i4;
    }

    public static int g(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        fq0 fq0Var = (fq0) iVar;
        int iD = d(bArr, i2, bVar);
        fq0Var.c(rp0.e(bVar.f8414a));
        while (iD < i3) {
            int iD2 = d(bArr, iD, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iD = d(bArr, iD2, bVar);
            fq0Var.c(rp0.e(bVar.f8414a));
        }
        return iD;
    }

    public static int h(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        mq0 mq0Var = (mq0) iVar;
        int iE = e(bArr, i2, bVar);
        mq0Var.a(rp0.a(bVar.b));
        while (iE < i3) {
            int iD = d(bArr, iE, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iE = e(bArr, iD, bVar);
            mq0Var.a(rp0.a(bVar.b));
        }
        return iE;
    }

    public static int i(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        mq0 mq0Var = (mq0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            iD = e(bArr, iD, bVar);
            mq0Var.a(bVar.b);
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int j(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) throws InvalidProtocolBufferException {
        int iD = d(bArr, i2, bVar);
        int i4 = bVar.f8414a;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i4 == 0) {
            iVar.add("");
        } else {
            int i5 = iD + i4;
            if (!wr0.d(bArr, iD, i5)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            iVar.add(new String(bArr, iD, i4, gq0.f7764a));
            iD = i5;
        }
        while (iD < i3) {
            int iD2 = d(bArr, iD, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iD = d(bArr, iD2, bVar);
            int i6 = bVar.f8414a;
            if (i6 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i6 == 0) {
                iVar.add("");
            } else {
                int i7 = iD + i6;
                if (!wr0.d(bArr, iD, i7)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
                iVar.add(new String(bArr, iD, i6, gq0.f7764a));
                iD = i7;
            }
        }
        return iD;
    }

    public static int k(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        fq0 fq0Var = (fq0) iVar;
        int iD = d(bArr, i2, bVar);
        fq0Var.c(bVar.f8414a);
        while (iD < i3) {
            int iD2 = d(bArr, iD, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iD = d(bArr, iD2, bVar);
            fq0Var.c(bVar.f8414a);
        }
        return iD;
    }

    public static int l(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        mq0 mq0Var = (mq0) iVar;
        int iE = e(bArr, i2, bVar);
        mq0Var.a(bVar.b);
        while (iE < i3) {
            int iD = d(bArr, iE, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iE = e(bArr, iD, bVar);
            mq0Var.a(bVar.b);
        }
        return iE;
    }

    public static int b(byte[] bArr, int i, b bVar) throws InvalidProtocolBufferException {
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a;
        if (i2 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i2 == 0) {
            bVar.c = "";
            return iD;
        }
        bVar.c = new String(bArr, iD, i2, gq0.f7764a);
        return iD + i2;
    }

    public static int c(byte[] bArr, int i, b bVar) throws InvalidProtocolBufferException {
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a;
        if (i2 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i2 == 0) {
            bVar.c = "";
            return iD;
        }
        bVar.c = wr0.b(bArr, iD, i2);
        return iD + i2;
    }

    public static float d(byte[] bArr, int i) {
        return Float.intBitsToFloat(b(bArr, i));
    }

    public static int e(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        mq0 mq0Var = (mq0) iVar;
        mq0Var.a(c(bArr, i2));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int iD = d(bArr, i4, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            mq0Var.a(c(bArr, iD));
            i4 = iD + 8;
        }
        return i4;
    }

    public static int d(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        fq0 fq0Var = (fq0) iVar;
        fq0Var.c(b(bArr, i2));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int iD = d(bArr, i4, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            fq0Var.c(b(bArr, iD));
            i4 = iD + 4;
        }
        return i4;
    }

    public static int f(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        fq0 fq0Var = (fq0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            iD = d(bArr, iD, bVar);
            fq0Var.c(rp0.e(bVar.f8414a));
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int b(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        up0 up0Var = (up0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            up0Var.a(a(bArr, iD));
            iD += 8;
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int c(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        up0 up0Var = (up0) iVar;
        up0Var.a(a(bArr, i2));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int iD = d(bArr, i4, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            up0Var.a(a(bArr, iD));
            i4 = iD + 8;
        }
        return i4;
    }

    public static int i(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) throws InvalidProtocolBufferException {
        int iD = d(bArr, i2, bVar);
        int i4 = bVar.f8414a;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i4 == 0) {
            iVar.add("");
        } else {
            iVar.add(new String(bArr, iD, i4, gq0.f7764a));
            iD += i4;
        }
        while (iD < i3) {
            int iD2 = d(bArr, iD, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iD = d(bArr, iD2, bVar);
            int i5 = bVar.f8414a;
            if (i5 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i5 == 0) {
                iVar.add("");
            } else {
                iVar.add(new String(bArr, iD, i5, gq0.f7764a));
                iD += i5;
            }
        }
        return iD;
    }

    public static int g(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        mq0 mq0Var = (mq0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            iD = e(bArr, iD, bVar);
            mq0Var.a(rp0.a(bVar.b));
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int h(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        fq0 fq0Var = (fq0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            iD = d(bArr, iD, bVar);
            fq0Var.c(bVar.f8414a);
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int e(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        dq0 dq0Var = (dq0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            dq0Var.a(d(bArr, iD));
            iD += 4;
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int d(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        mq0 mq0Var = (mq0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            mq0Var.a(c(bArr, iD));
            iD += 8;
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int a(long j, byte[] bArr, int i, b bVar) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        long j2 = (j & 127) | (((long) (b2 & 127)) << 7);
        int i3 = 7;
        while (b2 < 0) {
            int i4 = i2 + 1;
            byte b3 = bArr[i2];
            i3 += 7;
            j2 |= ((long) (b3 & 127)) << i3;
            i2 = i4;
            b2 = b3;
        }
        bVar.b = j2;
        return i2;
    }

    public static int b(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) throws InvalidProtocolBufferException {
        int iD = d(bArr, i2, bVar);
        int i4 = bVar.f8414a;
        if (i4 >= 0) {
            if (i4 > bArr.length - iD) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i4 == 0) {
                iVar.add(ByteString.EMPTY);
            } else {
                iVar.add(ByteString.copyFrom(bArr, iD, i4));
                iD += i4;
            }
            while (iD < i3) {
                int iD2 = d(bArr, iD, bVar);
                if (i != bVar.f8414a) {
                    break;
                }
                iD = d(bArr, iD2, bVar);
                int i5 = bVar.f8414a;
                if (i5 >= 0) {
                    if (i5 > bArr.length - iD) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    if (i5 == 0) {
                        iVar.add(ByteString.EMPTY);
                    } else {
                        iVar.add(ByteString.copyFrom(bArr, iD, i5));
                        iD += i5;
                    }
                } else {
                    throw InvalidProtocolBufferException.negativeSize();
                }
            }
            return iD;
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int c(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        fq0 fq0Var = (fq0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            fq0Var.c(b(bArr, iD));
            iD += 4;
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static double a(byte[] bArr, int i) {
        return Double.longBitsToDouble(c(bArr, i));
    }

    public static int a(byte[] bArr, int i, b bVar) throws InvalidProtocolBufferException {
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a;
        if (i2 >= 0) {
            if (i2 > bArr.length - iD) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i2 == 0) {
                bVar.c = ByteString.EMPTY;
                return iD;
            }
            bVar.c = ByteString.copyFrom(bArr, iD, i2);
            return iD + i2;
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    public static int a(lr0 lr0Var, byte[] bArr, int i, int i2, b bVar) throws IOException {
        int iA = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iA = a(i3, bArr, iA, bVar);
            i3 = bVar.f8414a;
        }
        int i4 = iA;
        if (i3 >= 0 && i3 <= i2 - i4) {
            Object objNewInstance = lr0Var.newInstance();
            int i5 = i3 + i4;
            lr0Var.a(objNewInstance, bArr, i4, i5, bVar);
            lr0Var.a(objNewInstance);
            bVar.c = objNewInstance;
            return i5;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int b(lr0<?> lr0Var, int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) throws IOException {
        int iA = a(lr0Var, bArr, i2, i3, bVar);
        iVar.add(bVar.c);
        while (iA < i3) {
            int iD = d(bArr, iA, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iA = a(lr0Var, bArr, iD, i3, bVar);
            iVar.add(bVar.c);
        }
        return iA;
    }

    public static int a(lr0 lr0Var, byte[] bArr, int i, int i2, int i3, b bVar) throws IOException {
        xq0 xq0Var = (xq0) lr0Var;
        Object objNewInstance = xq0Var.newInstance();
        int iA = xq0Var.a(objNewInstance, bArr, i, i2, i3, bVar);
        xq0Var.a(objNewInstance);
        bVar.c = objNewInstance;
        return iA;
    }

    public static int a(int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) {
        op0 op0Var = (op0) iVar;
        int iE = e(bArr, i2, bVar);
        op0Var.a(bVar.b != 0);
        while (iE < i3) {
            int iD = d(bArr, iE, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iE = e(bArr, iD, bVar);
            op0Var.a(bVar.b != 0);
        }
        return iE;
    }

    public static int a(byte[] bArr, int i, gq0.i<?> iVar, b bVar) throws IOException {
        op0 op0Var = (op0) iVar;
        int iD = d(bArr, i, bVar);
        int i2 = bVar.f8414a + iD;
        while (iD < i2) {
            iD = e(bArr, iD, bVar);
            op0Var.a(bVar.b != 0);
        }
        if (iD == i2) {
            return iD;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int a(lr0 lr0Var, int i, byte[] bArr, int i2, int i3, gq0.i<?> iVar, b bVar) throws IOException {
        int i4 = (i & (-8)) | 4;
        int iA = a(lr0Var, bArr, i2, i3, i4, bVar);
        iVar.add(bVar.c);
        while (iA < i3) {
            int iD = d(bArr, iA, bVar);
            if (i != bVar.f8414a) {
                break;
            }
            iA = a(lr0Var, bArr, iD, i3, i4, bVar);
            iVar.add(bVar.c);
        }
        return iA;
    }

    public static int a(int i, byte[] bArr, int i2, int i3, Object obj, uq0 uq0Var, rr0<sr0, sr0> rr0Var, b bVar) throws IOException {
        GeneratedMessageLite.f fVarA = bVar.d.a(uq0Var, i >>> 3);
        if (fVarA == null) {
            return a(i, bArr, i2, i3, xq0.g(obj), bVar);
        }
        GeneratedMessageLite.c cVar = (GeneratedMessageLite.c) obj;
        cVar.n();
        return a(i, bArr, i2, i3, (GeneratedMessageLite.c<?, ?>) cVar, (GeneratedMessageLite.f<?, ?>) fVarA, rr0Var, bVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int a(int i, byte[] bArr, int i2, int i3, GeneratedMessageLite.c<?, ?> cVar, GeneratedMessageLite.f<?, ?> fVar, rr0<sr0, sr0> rr0Var, b bVar) throws IOException {
        Object objA;
        cq0<GeneratedMessageLite.e> cq0Var = cVar.extensions;
        int i4 = i >>> 3;
        if (fVar.b.D() && fVar.b.isPacked()) {
            switch (a.f8413a[fVar.a().ordinal()]) {
                case 1:
                    up0 up0Var = new up0();
                    int iB = b(bArr, i2, up0Var, bVar);
                    cq0Var.b(fVar.b, up0Var);
                    return iB;
                case 2:
                    dq0 dq0Var = new dq0();
                    int iE = e(bArr, i2, dq0Var, bVar);
                    cq0Var.b(fVar.b, dq0Var);
                    return iE;
                case 3:
                case 4:
                    mq0 mq0Var = new mq0();
                    int i5 = i(bArr, i2, mq0Var, bVar);
                    cq0Var.b(fVar.b, mq0Var);
                    return i5;
                case 5:
                case 6:
                    fq0 fq0Var = new fq0();
                    int iH = h(bArr, i2, fq0Var, bVar);
                    cq0Var.b(fVar.b, fq0Var);
                    return iH;
                case 7:
                case 8:
                    mq0 mq0Var2 = new mq0();
                    int iD = d(bArr, i2, mq0Var2, bVar);
                    cq0Var.b(fVar.b, mq0Var2);
                    return iD;
                case 9:
                case 10:
                    fq0 fq0Var2 = new fq0();
                    int iC = c(bArr, i2, fq0Var2, bVar);
                    cq0Var.b(fVar.b, fq0Var2);
                    return iC;
                case 11:
                    op0 op0Var = new op0();
                    int iA = a(bArr, i2, op0Var, bVar);
                    cq0Var.b(fVar.b, op0Var);
                    return iA;
                case 12:
                    fq0 fq0Var3 = new fq0();
                    int iF = f(bArr, i2, fq0Var3, bVar);
                    cq0Var.b(fVar.b, fq0Var3);
                    return iF;
                case 13:
                    mq0 mq0Var3 = new mq0();
                    int iG = g(bArr, i2, mq0Var3, bVar);
                    cq0Var.b(fVar.b, mq0Var3);
                    return iG;
                case 14:
                    fq0 fq0Var4 = new fq0();
                    int iH2 = h(bArr, i2, fq0Var4, bVar);
                    sr0 sr0Var = cVar.unknownFields;
                    sr0 sr0Var2 = (sr0) nr0.a(i4, (List<Integer>) fq0Var4, fVar.b.a(), sr0Var != sr0.f() ? sr0Var : null, (rr0<UT, Object>) rr0Var);
                    if (sr0Var2 != null) {
                        cVar.unknownFields = sr0Var2;
                    }
                    cq0Var.b(fVar.b, fq0Var4);
                    return iH2;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + fVar.b.E());
            }
        }
        if (fVar.a() == WireFormat.FieldType.ENUM) {
            i2 = d(bArr, i2, bVar);
            if (fVar.b.a().a(bVar.f8414a) == null) {
                sr0 sr0VarG = cVar.unknownFields;
                if (sr0VarG == sr0.f()) {
                    sr0VarG = sr0.g();
                    cVar.unknownFields = sr0VarG;
                }
                nr0.a(i4, bVar.f8414a, sr0VarG, (rr0<UT, sr0>) rr0Var);
                return i2;
            }
            objValueOf = Integer.valueOf(bVar.f8414a);
        } else {
            switch (a.f8413a[fVar.a().ordinal()]) {
                case 1:
                    objValueOf = Double.valueOf(a(bArr, i2));
                    i2 += 8;
                    break;
                case 2:
                    objValueOf = Float.valueOf(d(bArr, i2));
                    i2 += 4;
                    break;
                case 3:
                case 4:
                    i2 = e(bArr, i2, bVar);
                    objValueOf = Long.valueOf(bVar.b);
                    break;
                case 5:
                case 6:
                    i2 = d(bArr, i2, bVar);
                    objValueOf = Integer.valueOf(bVar.f8414a);
                    break;
                case 7:
                case 8:
                    objValueOf = Long.valueOf(c(bArr, i2));
                    i2 += 8;
                    break;
                case 9:
                case 10:
                    objValueOf = Integer.valueOf(b(bArr, i2));
                    i2 += 4;
                    break;
                case 11:
                    i2 = e(bArr, i2, bVar);
                    objValueOf = Boolean.valueOf(bVar.b != 0);
                    break;
                case 12:
                    i2 = d(bArr, i2, bVar);
                    objValueOf = Integer.valueOf(rp0.e(bVar.f8414a));
                    break;
                case 13:
                    i2 = e(bArr, i2, bVar);
                    objValueOf = Long.valueOf(rp0.a(bVar.b));
                    break;
                case 14:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 15:
                    i2 = a(bArr, i2, bVar);
                    objValueOf = bVar.c;
                    break;
                case 16:
                    i2 = b(bArr, i2, bVar);
                    objValueOf = bVar.c;
                    break;
                case 17:
                    i2 = a(fr0.a().a((Class) fVar.b().getClass()), bArr, i2, i3, (i4 << 3) | 4, bVar);
                    objValueOf = bVar.c;
                    break;
                case 18:
                    i2 = a(fr0.a().a((Class) fVar.b().getClass()), bArr, i2, i3, bVar);
                    objValueOf = bVar.c;
                    break;
            }
        }
        if (fVar.d()) {
            cq0Var.a(fVar.b, objValueOf);
        } else {
            int i6 = a.f8413a[fVar.a().ordinal()];
            if ((i6 == 17 || i6 == 18) && (objA = cq0Var.a(fVar.b)) != null) {
                objValueOf = gq0.a(objA, objValueOf);
            }
            cq0Var.b(fVar.b, objValueOf);
        }
        return i2;
    }

    public static int a(int i, byte[] bArr, int i2, int i3, sr0 sr0Var, b bVar) throws InvalidProtocolBufferException {
        if (WireFormat.a(i) != 0) {
            int iB = WireFormat.b(i);
            if (iB == 0) {
                int iE = e(bArr, i2, bVar);
                sr0Var.a(i, Long.valueOf(bVar.b));
                return iE;
            }
            if (iB == 1) {
                sr0Var.a(i, Long.valueOf(c(bArr, i2)));
                return i2 + 8;
            }
            if (iB == 2) {
                int iD = d(bArr, i2, bVar);
                int i4 = bVar.f8414a;
                if (i4 >= 0) {
                    if (i4 > bArr.length - iD) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    if (i4 == 0) {
                        sr0Var.a(i, ByteString.EMPTY);
                    } else {
                        sr0Var.a(i, ByteString.copyFrom(bArr, iD, i4));
                    }
                    return iD + i4;
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (iB != 3) {
                if (iB == 5) {
                    sr0Var.a(i, Integer.valueOf(b(bArr, i2)));
                    return i2 + 4;
                }
                throw InvalidProtocolBufferException.invalidTag();
            }
            sr0 sr0VarG = sr0.g();
            int i5 = (i & (-8)) | 4;
            int i6 = 0;
            while (true) {
                if (i2 >= i3) {
                    break;
                }
                int iD2 = d(bArr, i2, bVar);
                int i7 = bVar.f8414a;
                if (i7 == i5) {
                    i6 = i7;
                    i2 = iD2;
                    break;
                }
                i6 = i7;
                i2 = a(i7, bArr, iD2, i3, sr0VarG, bVar);
            }
            if (i2 <= i3 && i6 == i5) {
                sr0Var.a(i, sr0VarG);
                return i2;
            }
            throw InvalidProtocolBufferException.parseFailure();
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public static int a(int i, byte[] bArr, int i2, int i3, b bVar) throws InvalidProtocolBufferException {
        if (WireFormat.a(i) != 0) {
            int iB = WireFormat.b(i);
            if (iB == 0) {
                return e(bArr, i2, bVar);
            }
            if (iB == 1) {
                return i2 + 8;
            }
            if (iB == 2) {
                return d(bArr, i2, bVar) + bVar.f8414a;
            }
            if (iB != 3) {
                if (iB == 5) {
                    return i2 + 4;
                }
                throw InvalidProtocolBufferException.invalidTag();
            }
            int i4 = (i & (-8)) | 4;
            int i5 = 0;
            while (i2 < i3) {
                i2 = d(bArr, i2, bVar);
                i5 = bVar.f8414a;
                if (i5 == i4) {
                    break;
                }
                i2 = a(i5, bArr, i2, i3, bVar);
            }
            if (i2 > i3 || i5 != i4) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            return i2;
        }
        throw InvalidProtocolBufferException.invalidTag();
    }
}
