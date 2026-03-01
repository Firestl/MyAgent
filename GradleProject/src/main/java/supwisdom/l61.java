package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class l61 extends r61 implements t61 {
    public Object[] b;

    public l61(int i) {
        super(i != 0);
        try {
            this.b = new Object[i];
        } catch (NegativeArraySizeException unused) {
            throw new IllegalArgumentException("size < 0");
        }
    }

    public final Object a(int i) {
        try {
            Object obj = this.b[i];
            if (obj != null) {
                return obj;
            }
            throw new NullPointerException("unset: " + i);
        } catch (ArrayIndexOutOfBoundsException unused) {
            c(i);
            throw null;
        }
    }

    public final Object b(int i) {
        return this.b[i];
    }

    public final Object c(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("n < 0");
        }
        throw new IndexOutOfBoundsException("n >= size()");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.b, ((l61) obj).b);
    }

    public int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final int size() {
        return this.b.length;
    }

    public String toHuman() {
        String name = getClass().getName();
        return a(name.substring(name.lastIndexOf(46) + 1) + Operators.BLOCK_START, ", ", Operators.BLOCK_END_STR, true);
    }

    public String toString() {
        String name = getClass().getName();
        return a(name.substring(name.lastIndexOf(46) + 1) + Operators.BLOCK_START, ", ", Operators.BLOCK_END_STR, false);
    }

    public String b(String str, String str2, String str3) {
        return a(str, str2, str3, false);
    }

    public final void a(int i, Object obj) {
        f();
        try {
            this.b[i] = obj;
        } catch (ArrayIndexOutOfBoundsException unused) {
            c(i);
            throw null;
        }
    }

    public final String a(String str, String str2, String str3, boolean z) {
        int length = this.b.length;
        StringBuilder sb = new StringBuilder((length * 10) + 10);
        if (str != null) {
            sb.append(str);
        }
        for (int i = 0; i < length; i++) {
            if (i != 0 && str2 != null) {
                sb.append(str2);
            }
            if (z) {
                sb.append(((t61) this.b[i]).toHuman());
            } else {
                sb.append(this.b[i]);
            }
        }
        if (str3 != null) {
            sb.append(str3);
        }
        return sb.toString();
    }

    public String a(String str, String str2, String str3) {
        return a(str, str2, str3, true);
    }
}
