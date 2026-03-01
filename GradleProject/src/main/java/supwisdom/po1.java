package supwisdom;

import com.taobao.weex.el.parse.Operators;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

/* JADX INFO: compiled from: BasicHeaderValueFormatter.java */
/* JADX INFO: loaded from: classes2.dex */
public class po1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final po1 f8814a;

    static {
        new po1();
        f8814a = new po1();
    }

    public CharArrayBuffer a(CharArrayBuffer charArrayBuffer, co1[] co1VarArr, boolean z) {
        yo1.a(co1VarArr, "Header parameter array");
        int iA = a(co1VarArr);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(iA);
        } else {
            charArrayBuffer.ensureCapacity(iA);
        }
        for (int i = 0; i < co1VarArr.length; i++) {
            if (i > 0) {
                charArrayBuffer.append("; ");
            }
            a(charArrayBuffer, co1VarArr[i], z);
        }
        return charArrayBuffer;
    }

    public boolean b(char c) {
        return "\"\\".indexOf(c) >= 0;
    }

    public int a(co1[] co1VarArr) {
        if (co1VarArr == null || co1VarArr.length < 1) {
            return 0;
        }
        int length = (co1VarArr.length - 1) * 2;
        for (co1 co1Var : co1VarArr) {
            length += a(co1Var);
        }
        return length;
    }

    public CharArrayBuffer a(CharArrayBuffer charArrayBuffer, co1 co1Var, boolean z) {
        yo1.a(co1Var, "Name / value pair");
        int iA = a(co1Var);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(iA);
        } else {
            charArrayBuffer.ensureCapacity(iA);
        }
        charArrayBuffer.append(co1Var.getName());
        String value = co1Var.getValue();
        if (value != null) {
            charArrayBuffer.append('=');
            a(charArrayBuffer, value, z);
        }
        return charArrayBuffer;
    }

    public int a(co1 co1Var) {
        if (co1Var == null) {
            return 0;
        }
        int length = co1Var.getName().length();
        String value = co1Var.getValue();
        return value != null ? length + value.length() + 3 : length;
    }

    public void a(CharArrayBuffer charArrayBuffer, String str, boolean z) {
        if (!z) {
            for (int i = 0; i < str.length() && !z; i++) {
                z = a(str.charAt(i));
            }
        }
        if (z) {
            charArrayBuffer.append(Operators.QUOTE);
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (b(cCharAt)) {
                charArrayBuffer.append('\\');
            }
            charArrayBuffer.append(cCharAt);
        }
        if (z) {
            charArrayBuffer.append(Operators.QUOTE);
        }
    }

    public boolean a(char c) {
        return " ;,:@()<>\\\"/[]?={}\t".indexOf(c) >= 0;
    }
}
