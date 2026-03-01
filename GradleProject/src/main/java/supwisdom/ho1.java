package supwisdom;

import com.google.zxing.client.android.encode.MECARDContactEncoder;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: compiled from: URLEncodedUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class ho1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f7860a = {'&', MECARDContactEncoder.TERMINATOR};
    public static final BitSet b;
    public static final BitSet c;
    public static final BitSet d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final BitSet f7861e;
    public static final BitSet f;
    public static final BitSet g;
    public static final BitSet h;

    static {
        String str = Operators.ARRAY_START_STR + new String(f7860a) + Operators.ARRAY_END_STR;
        b = new BitSet(256);
        c = new BitSet(256);
        d = new BitSet(256);
        f7861e = new BitSet(256);
        f = new BitSet(256);
        g = new BitSet(256);
        h = new BitSet(256);
        for (int i = 97; i <= 122; i++) {
            b.set(i);
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            b.set(i2);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            b.set(i3);
        }
        b.set(95);
        b.set(45);
        b.set(46);
        b.set(42);
        h.or(b);
        b.set(33);
        b.set(126);
        b.set(39);
        b.set(40);
        b.set(41);
        c.set(44);
        c.set(59);
        c.set(58);
        c.set(36);
        c.set(38);
        c.set(43);
        c.set(61);
        d.or(b);
        d.or(c);
        f7861e.or(b);
        f7861e.set(47);
        f7861e.set(59);
        f7861e.set(58);
        f7861e.set(64);
        f7861e.set(38);
        f7861e.set(61);
        f7861e.set(43);
        f7861e.set(36);
        f7861e.set(44);
        g.set(59);
        g.set(47);
        g.set(63);
        g.set(58);
        g.set(64);
        g.set(38);
        g.set(61);
        g.set(43);
        g.set(36);
        g.set(44);
        g.set(91);
        g.set(93);
        f.or(g);
        f.or(b);
    }

    public static String a(List<? extends co1> list, String str) {
        return a(list, '&', str);
    }

    public static String a(List<? extends co1> list, char c2, String str) {
        StringBuilder sb = new StringBuilder();
        for (co1 co1Var : list) {
            String strA = a(co1Var.getName(), str);
            String strA2 = a(co1Var.getValue(), str);
            if (sb.length() > 0) {
                sb.append(c2);
            }
            sb.append(strA);
            if (strA2 != null) {
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(strA2);
            }
        }
        return sb.toString();
    }

    public static String a(String str, Charset charset, BitSet bitSet, boolean z) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ByteBuffer byteBufferEncode = charset.encode(str);
        while (byteBufferEncode.hasRemaining()) {
            int i = byteBufferEncode.get() & 255;
            if (bitSet.get(i)) {
                sb.append((char) i);
            } else if (z && i == 32) {
                sb.append(FunctionParser.Lexer.PLUS);
            } else {
                sb.append("%");
                char upperCase = Character.toUpperCase(Character.forDigit((i >> 4) & 15, 16));
                char upperCase2 = Character.toUpperCase(Character.forDigit(i & 15, 16));
                sb.append(upperCase);
                sb.append(upperCase2);
            }
        }
        return sb.toString();
    }

    public static String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        return a(str, str2 != null ? Charset.forName(str2) : vn1.f9531a, h, true);
    }
}
