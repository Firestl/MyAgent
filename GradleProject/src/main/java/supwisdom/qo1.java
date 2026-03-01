package supwisdom;

import com.google.zxing.client.android.encode.MECARDContactEncoder;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicHeaderValueParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class qo1 implements to1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qo1 f8943a;
    public static final char[] b;

    static {
        new qo1();
        f8943a = new qo1();
        b = new char[]{MECARDContactEncoder.TERMINATOR, ','};
    }

    public static yn1[] a(String str, to1 to1Var) throws ParseException {
        yo1.a(str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        uo1 uo1Var = new uo1(0, str.length());
        if (to1Var == null) {
            to1Var = f8943a;
        }
        return to1Var.a(charArrayBuffer, uo1Var);
    }

    public yn1 b(CharArrayBuffer charArrayBuffer, uo1 uo1Var) {
        yo1.a(charArrayBuffer, "Char array buffer");
        yo1.a(uo1Var, "Parser cursor");
        co1 co1VarC = c(charArrayBuffer, uo1Var);
        return a(co1VarC.getName(), co1VarC.getValue(), (uo1Var.a() || charArrayBuffer.charAt(uo1Var.b() + (-1)) == ',') ? null : d(charArrayBuffer, uo1Var));
    }

    public co1 c(CharArrayBuffer charArrayBuffer, uo1 uo1Var) {
        return a(charArrayBuffer, uo1Var, b);
    }

    public co1[] d(CharArrayBuffer charArrayBuffer, uo1 uo1Var) {
        yo1.a(charArrayBuffer, "Char array buffer");
        yo1.a(uo1Var, "Parser cursor");
        int iB = uo1Var.b();
        int iC = uo1Var.c();
        while (iB < iC && xo1.a(charArrayBuffer.charAt(iB))) {
            iB++;
        }
        uo1Var.a(iB);
        if (uo1Var.a()) {
            return new co1[0];
        }
        ArrayList arrayList = new ArrayList();
        while (!uo1Var.a()) {
            arrayList.add(c(charArrayBuffer, uo1Var));
            if (charArrayBuffer.charAt(uo1Var.b() - 1) == ',') {
                break;
            }
        }
        return (co1[]) arrayList.toArray(new co1[arrayList.size()]);
    }

    @Override // supwisdom.to1
    public yn1[] a(CharArrayBuffer charArrayBuffer, uo1 uo1Var) {
        yo1.a(charArrayBuffer, "Char array buffer");
        yo1.a(uo1Var, "Parser cursor");
        ArrayList arrayList = new ArrayList();
        while (!uo1Var.a()) {
            yn1 yn1VarB = b(charArrayBuffer, uo1Var);
            if (yn1VarB.getName().length() != 0 || yn1VarB.getValue() != null) {
                arrayList.add(yn1VarB);
            }
        }
        return (yn1[]) arrayList.toArray(new yn1[arrayList.size()]);
    }

    public yn1 a(String str, String str2, co1[] co1VarArr) {
        return new oo1(str, str2, co1VarArr);
    }

    public static boolean a(char c, char[] cArr) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        r5 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public supwisdom.co1 a(cz.msebera.android.httpclient.util.CharArrayBuffer r13, supwisdom.uo1 r14, char[] r15) {
        /*
            r12 = this;
            java.lang.String r0 = "Char array buffer"
            supwisdom.yo1.a(r13, r0)
            java.lang.String r0 = "Parser cursor"
            supwisdom.yo1.a(r14, r0)
            int r0 = r14.b()
            int r1 = r14.b()
            int r2 = r14.c()
        L16:
            r3 = 0
            r4 = 1
            if (r0 >= r2) goto L2e
            char r5 = r13.charAt(r0)
            r6 = 61
            if (r5 != r6) goto L23
            goto L2e
        L23:
            boolean r5 = a(r5, r15)
            if (r5 == 0) goto L2b
            r5 = 1
            goto L2f
        L2b:
            int r0 = r0 + 1
            goto L16
        L2e:
            r5 = 0
        L2f:
            if (r0 != r2) goto L37
            java.lang.String r1 = r13.substringTrimmed(r1, r2)
            r5 = 1
            goto L3d
        L37:
            java.lang.String r1 = r13.substringTrimmed(r1, r0)
            int r0 = r0 + 1
        L3d:
            if (r5 == 0) goto L48
            r14.a(r0)
            r13 = 0
            supwisdom.co1 r13 = r12.a(r1, r13)
            return r13
        L48:
            r6 = r0
            r7 = 0
            r8 = 0
        L4b:
            r9 = 34
            if (r6 >= r2) goto L72
            char r10 = r13.charAt(r6)
            if (r10 != r9) goto L59
            if (r7 != 0) goto L59
            r8 = r8 ^ 1
        L59:
            if (r8 != 0) goto L64
            if (r7 != 0) goto L64
            boolean r11 = a(r10, r15)
            if (r11 == 0) goto L64
            goto L73
        L64:
            if (r7 == 0) goto L68
        L66:
            r7 = 0
            goto L6f
        L68:
            if (r8 == 0) goto L66
            r7 = 92
            if (r10 != r7) goto L66
            r7 = 1
        L6f:
            int r6 = r6 + 1
            goto L4b
        L72:
            r4 = r5
        L73:
            if (r0 >= r6) goto L82
            char r15 = r13.charAt(r0)
            boolean r15 = supwisdom.xo1.a(r15)
            if (r15 == 0) goto L82
            int r0 = r0 + 1
            goto L73
        L82:
            r15 = r6
        L83:
            if (r15 <= r0) goto L94
            int r2 = r15 + (-1)
            char r2 = r13.charAt(r2)
            boolean r2 = supwisdom.xo1.a(r2)
            if (r2 == 0) goto L94
            int r15 = r15 + (-1)
            goto L83
        L94:
            int r2 = r15 - r0
            r3 = 2
            if (r2 < r3) goto Lab
            char r2 = r13.charAt(r0)
            if (r2 != r9) goto Lab
            int r2 = r15 + (-1)
            char r2 = r13.charAt(r2)
            if (r2 != r9) goto Lab
            int r0 = r0 + 1
            int r15 = r15 + (-1)
        Lab:
            java.lang.String r13 = r13.substring(r0, r15)
            if (r4 == 0) goto Lb3
            int r6 = r6 + 1
        Lb3:
            r14.a(r6)
            supwisdom.co1 r13 = r12.a(r1, r13)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.qo1.a(cz.msebera.android.httpclient.util.CharArrayBuffer, supwisdom.uo1, char[]):supwisdom.co1");
    }

    public co1 a(String str, String str2) {
        return new BasicNameValuePair(str, str2);
    }
}
