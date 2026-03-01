package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: UnknownFieldSetLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class sr0 {
    public static final sr0 f = new sr0(0, new int[0], new Object[0], false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9207a;
    public int[] b;
    public Object[] c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9208e;

    public sr0() {
        this(0, new int[8], new Object[8], true);
    }

    public static sr0 a(sr0 sr0Var, sr0 sr0Var2) {
        int i = sr0Var.f9207a + sr0Var2.f9207a;
        int[] iArrCopyOf = Arrays.copyOf(sr0Var.b, i);
        System.arraycopy(sr0Var2.b, 0, iArrCopyOf, sr0Var.f9207a, sr0Var2.f9207a);
        Object[] objArrCopyOf = Arrays.copyOf(sr0Var.c, i);
        System.arraycopy(sr0Var2.c, 0, objArrCopyOf, sr0Var.f9207a, sr0Var2.f9207a);
        return new sr0(i, iArrCopyOf, objArrCopyOf, true);
    }

    public static sr0 f() {
        return f;
    }

    public static sr0 g() {
        return new sr0();
    }

    public void b(Writer writer) throws IOException {
        if (this.f9207a == 0) {
            return;
        }
        if (writer.a() == Writer.FieldOrder.ASCENDING) {
            for (int i = 0; i < this.f9207a; i++) {
                a(this.b[i], this.c[i], writer);
            }
            return;
        }
        for (int i2 = this.f9207a - 1; i2 >= 0; i2--) {
            a(this.b[i2], this.c[i2], writer);
        }
    }

    public int c() {
        int iJ;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f9207a; i3++) {
            int i4 = this.b[i3];
            int iA = WireFormat.a(i4);
            int iB = WireFormat.b(i4);
            if (iB == 0) {
                iJ = CodedOutputStream.j(iA, ((Long) this.c[i3]).longValue());
            } else if (iB == 1) {
                iJ = CodedOutputStream.f(iA, ((Long) this.c[i3]).longValue());
            } else if (iB == 2) {
                iJ = CodedOutputStream.c(iA, (ByteString) this.c[i3]);
            } else if (iB == 3) {
                iJ = (CodedOutputStream.n(iA) * 2) + ((sr0) this.c[i3]).c();
            } else {
                if (iB != 5) {
                    throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
                }
                iJ = CodedOutputStream.i(iA, ((Integer) this.c[i3]).intValue());
            }
            i2 += iJ;
        }
        this.d = i2;
        return i2;
    }

    public int d() {
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int iD = 0;
        for (int i2 = 0; i2 < this.f9207a; i2++) {
            iD += CodedOutputStream.d(WireFormat.a(this.b[i2]), (ByteString) this.c[i2]);
        }
        this.d = iD;
        return iD;
    }

    public void e() {
        this.f9208e = false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof sr0)) {
            return false;
        }
        sr0 sr0Var = (sr0) obj;
        int i = this.f9207a;
        return i == sr0Var.f9207a && a(this.b, sr0Var.b, i) && a(this.c, sr0Var.c, this.f9207a);
    }

    public int hashCode() {
        int i = this.f9207a;
        return ((((527 + i) * 31) + a(this.b, i)) * 31) + a(this.c, this.f9207a);
    }

    public sr0(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f9207a = i;
        this.b = iArr;
        this.c = objArr;
        this.f9208e = z;
    }

    public void a() {
        if (!this.f9208e) {
            throw new UnsupportedOperationException();
        }
    }

    public final void b() {
        int i = this.f9207a;
        if (i == this.b.length) {
            int i2 = this.f9207a + (i < 4 ? 8 : i >> 1);
            this.b = Arrays.copyOf(this.b, i2);
            this.c = Arrays.copyOf(this.c, i2);
        }
    }

    public void a(Writer writer) throws IOException {
        if (writer.a() == Writer.FieldOrder.DESCENDING) {
            for (int i = this.f9207a - 1; i >= 0; i--) {
                writer.a(WireFormat.a(this.b[i]), this.c[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.f9207a; i2++) {
            writer.a(WireFormat.a(this.b[i2]), this.c[i2]);
        }
    }

    public static void a(int i, Object obj, Writer writer) throws IOException {
        int iA = WireFormat.a(i);
        int iB = WireFormat.b(i);
        if (iB == 0) {
            writer.c(iA, ((Long) obj).longValue());
            return;
        }
        if (iB == 1) {
            writer.a(iA, ((Long) obj).longValue());
            return;
        }
        if (iB == 2) {
            writer.a(iA, (ByteString) obj);
            return;
        }
        if (iB != 3) {
            if (iB == 5) {
                writer.b(iA, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(InvalidProtocolBufferException.invalidWireType());
        }
        if (writer.a() == Writer.FieldOrder.ASCENDING) {
            writer.a(iA);
            ((sr0) obj).b(writer);
            writer.b(iA);
        } else {
            writer.b(iA);
            ((sr0) obj).b(writer);
            writer.a(iA);
        }
    }

    public static boolean a(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Object[] objArr, Object[] objArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!objArr[i2].equals(objArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    public static int a(int[] iArr, int i) {
        int i2 = 17;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + iArr[i3];
        }
        return i2;
    }

    public static int a(Object[] objArr, int i) {
        int iHashCode = 17;
        for (int i2 = 0; i2 < i; i2++) {
            iHashCode = (iHashCode * 31) + objArr[i2].hashCode();
        }
        return iHashCode;
    }

    public final void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f9207a; i2++) {
            wq0.a(sb, i, String.valueOf(WireFormat.a(this.b[i2])), this.c[i2]);
        }
    }

    public void a(int i, Object obj) {
        a();
        b();
        int[] iArr = this.b;
        int i2 = this.f9207a;
        iArr[i2] = i;
        this.c[i2] = obj;
        this.f9207a = i2 + 1;
    }
}
