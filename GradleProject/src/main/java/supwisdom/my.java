package supwisdom;

import com.taobao.weex.utils.FunctionParser;

/* JADX INFO: loaded from: classes.dex */
public abstract class my extends oy {
    public my(ew ewVar) {
        super(ewVar);
    }

    public static void b(StringBuilder sb, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int iCharAt = sb.charAt(i3 + i) - '0';
            if ((i3 & 1) == 0) {
                iCharAt *= 3;
            }
            i2 += iCharAt;
        }
        int i4 = 10 - (i2 % 10);
        sb.append(i4 != 10 ? i4 : 0);
    }

    public final void a(StringBuilder sb, int i) {
        sb.append("(01)");
        int length = sb.length();
        sb.append(FunctionParser.Lexer.NINE);
        a(sb, i, length);
    }

    public final void a(StringBuilder sb, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            int iA = a().a((i3 * 10) + i, 10);
            if (iA / 100 == 0) {
                sb.append('0');
            }
            if (iA / 10 == 0) {
                sb.append('0');
            }
            sb.append(iA);
        }
        b(sb, i2);
    }
}
