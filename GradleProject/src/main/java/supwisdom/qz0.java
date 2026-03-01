package supwisdom;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.PdrUtil;
import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class qz0 {
    public static short a(int i, int i2) {
        if ((i & 255) != i) {
            throw new IllegalArgumentException("low out of range 0..255");
        }
        if ((i2 & 255) == i2) {
            return (short) (i | (i2 << 8));
        }
        throw new IllegalArgumentException("high out of range 0..255");
    }

    public static boolean a(int i) {
        return ((byte) i) == i;
    }

    public static boolean b(int i) {
        return i >= -8 && i <= 7;
    }

    public static String c(m41 m41Var) {
        int size = m41Var.size();
        StringBuilder sb = new StringBuilder(30);
        sb.append(Operators.BLOCK_START_STR);
        if (size != 0) {
            if (size != 1) {
                l41 l41VarD = m41Var.d(size - 1);
                if (l41VarD.c() == 2) {
                    l41VarD = l41VarD.a(1);
                }
                sb.append(m41Var.d(0).i());
                sb.append(PdrUtil.FILE_PATH_ENTRY_BACK);
                sb.append(l41VarD.i());
            } else {
                sb.append(m41Var.d(0).i());
            }
        }
        sb.append(Operators.BLOCK_END_STR);
        return sb.toString();
    }

    public static boolean c(int i) {
        return ((short) i) == i;
    }

    public static String d(kz0 kz0Var) {
        int iP = ((b01) kz0Var).p();
        return iP == ((short) iP) ? m61.a(iP) : m61.b(iP);
    }

    public static boolean d(int i) {
        return i == (i & 255);
    }

    public static String e(kz0 kz0Var) {
        int iO = ((b01) kz0Var).o();
        return iO == ((char) iO) ? m61.d(iO) : m61.g(iO);
    }

    public static boolean e(int i) {
        return i == (i & 15);
    }

    public static boolean f(int i) {
        return i == (65535 & i);
    }

    public abstract int a();

    public abstract String a(kz0 kz0Var, boolean z);

    public abstract void a(h61 h61Var, kz0 kz0Var);

    public boolean a(b01 b01Var) {
        return false;
    }

    public abstract String b(kz0 kz0Var);

    public final String b(kz0 kz0Var, boolean z) {
        String strC = kz0Var.h().c();
        String strB = b(kz0Var);
        String strA = a(kz0Var, z);
        StringBuilder sb = new StringBuilder(100);
        sb.append(strC);
        if (strB.length() != 0) {
            sb.append(' ');
            sb.append(strB);
        }
        if (strA.length() != 0) {
            sb.append(" // ");
            sb.append(strA);
        }
        return sb.toString();
    }

    public abstract boolean c(kz0 kz0Var);

    public BitSet a(kz0 kz0Var) {
        return new BitSet();
    }

    public static boolean a(m41 m41Var) {
        int size = m41Var.size();
        if (size < 2) {
            return true;
        }
        int iF = m41Var.d(0).f();
        for (int i = 0; i < size; i++) {
            l41 l41VarD = m41Var.d(i);
            if (l41VarD.f() != iF) {
                return false;
            }
            iF += l41VarD.c();
        }
        return true;
    }

    public static String a(n51 n51Var, int i) {
        long jE;
        StringBuilder sb = new StringBuilder(20);
        sb.append("#");
        if (n51Var instanceof m51) {
            jE = ((m51) n51Var).f();
        } else {
            jE = n51Var.e();
        }
        if (i == 4) {
            sb.append(m61.h((int) jE));
        } else if (i == 8) {
            sb.append(m61.c((int) jE));
        } else if (i == 16) {
            sb.append(m61.d((int) jE));
        } else if (i == 32) {
            sb.append(m61.g((int) jE));
        } else if (i == 64) {
            sb.append(m61.a(jE));
        } else {
            throw new RuntimeException("shouldn't happen");
        }
        return sb.toString();
    }

    public static int b(int i, int i2) {
        if ((i & 15) != i) {
            throw new IllegalArgumentException("low out of range 0..15");
        }
        if ((i2 & 15) == i2) {
            return i | (i2 << 4);
        }
        throw new IllegalArgumentException("high out of range 0..15");
    }

    public static String b(m41 m41Var) {
        int size = m41Var.size();
        StringBuilder sb = new StringBuilder((size * 5) + 2);
        sb.append(Operators.BLOCK_START);
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(m41Var.d(i).i());
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public static String a(n51 n51Var) {
        StringBuilder sb = new StringBuilder(100);
        sb.append('#');
        if (n51Var instanceof k51) {
            sb.append(com.igexin.push.core.b.m);
        } else {
            sb.append(n51Var.c());
            sb.append(' ');
            sb.append(n51Var.toHuman());
        }
        return sb.toString();
    }

    public static short a(kz0 kz0Var, int i) {
        if ((i & 255) == i) {
            int iE = kz0Var.h().e();
            if ((iE & 255) == iE) {
                return (short) (iE | (i << 8));
            }
            throw new IllegalArgumentException("opcode out of range 0..255");
        }
        throw new IllegalArgumentException("arg out of range 0..255");
    }

    public static void a(h61 h61Var, short s) {
        h61Var.writeShort(s);
    }

    public static void a(h61 h61Var, short s, short s2) {
        h61Var.writeShort(s);
        h61Var.writeShort(s2);
    }

    public static short a(int i, int i2, int i3, int i4) {
        if ((i & 15) != i) {
            throw new IllegalArgumentException("n0 out of range 0..15");
        }
        if ((i2 & 15) != i2) {
            throw new IllegalArgumentException("n1 out of range 0..15");
        }
        if ((i3 & 15) != i3) {
            throw new IllegalArgumentException("n2 out of range 0..15");
        }
        if ((i4 & 15) == i4) {
            return (short) (i | (i2 << 4) | (i3 << 8) | (i4 << 12));
        }
        throw new IllegalArgumentException("n3 out of range 0..15");
    }

    public static void a(h61 h61Var, short s, short s2, short s3) {
        h61Var.writeShort(s);
        h61Var.writeShort(s2);
        h61Var.writeShort(s3);
    }

    public static void a(h61 h61Var, short s, short s2, short s3, short s4) {
        h61Var.writeShort(s);
        h61Var.writeShort(s2);
        h61Var.writeShort(s3);
        h61Var.writeShort(s4);
    }

    public static void a(h61 h61Var, short s, short s2, short s3, short s4, short s5) {
        h61Var.writeShort(s);
        h61Var.writeShort(s2);
        h61Var.writeShort(s3);
        h61Var.writeShort(s4);
        h61Var.writeShort(s5);
    }

    public static void a(h61 h61Var, short s, int i) {
        a(h61Var, s, (short) i, (short) (i >> 16));
    }

    public static void a(h61 h61Var, short s, long j) {
        a(h61Var, s, (short) j, (short) (j >> 16), (short) (j >> 32), (short) (j >> 48));
    }
}
