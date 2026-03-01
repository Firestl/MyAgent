package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: NumberUtil.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class xn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<Class<?>, BigDecimal> f9789a;
    public static final List<Class<?>> b;

    static {
        new BigDecimal(BigInteger.ZERO);
        HashMap map = new HashMap();
        f9789a = map;
        map.put(Byte.class, BigDecimal.valueOf(127L));
        f9789a.put(Short.class, BigDecimal.valueOf(32767L));
        f9789a.put(Integer.class, BigDecimal.valueOf(2147483647L));
        f9789a.put(Long.class, BigDecimal.valueOf(Long.MAX_VALUE));
        f9789a.put(BigInteger.class, BigDecimal.valueOf(-1L));
        ArrayList arrayList = new ArrayList();
        b = arrayList;
        arrayList.add(Byte.class);
        b.add(Short.class);
        b.add(Integer.class);
        b.add(Long.class);
        b.add(Float.class);
        b.add(Double.class);
        Pattern.compile("^[-\\+]?[.\\d]*$");
        Pattern.compile("^[-\\+]?[\\d]*$");
        Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)[%]$");
    }

    public static String a(Number number, int i, boolean z, boolean z2) {
        return a(number, i, z, z2, false);
    }

    public static String b(Number number, int i, boolean z, RoundingMode roundingMode) {
        if (roundingMode == null) {
            throw new IllegalArgumentException("roundingMode can not be null.");
        }
        if (number == null || number.equals("") || number.equals(com.igexin.push.core.b.m)) {
            return "-";
        }
        if (number.longValue() >= 100000000) {
            return a(new BigDecimal(number.doubleValue() / 1.0E9d), 1, z, false, true, roundingMode) + "G";
        }
        if (number.longValue() >= 100000) {
            return a(new BigDecimal(number.doubleValue() / 1000000.0d), 1, z, false, true, roundingMode) + "M";
        }
        if (number.longValue() < 100) {
            return a(new BigDecimal(number.doubleValue()), i, z, false, false, roundingMode);
        }
        return a(new BigDecimal(number.doubleValue() / 1000.0d), 1, z, false, true, roundingMode) + "k";
    }

    public static String a(Number number, int i, boolean z) {
        return a() ? a(number, i, z, RoundingMode.HALF_UP) : b(number, i, z, RoundingMode.HALF_UP);
    }

    public static String a(Number number, int i, boolean z, RoundingMode roundingMode) {
        if (roundingMode != null) {
            if (number == null || number.equals("") || number.equals(com.igexin.push.core.b.m)) {
                return "-";
            }
            if (number.longValue() >= 100000000) {
                return a(new BigDecimal(number.doubleValue() / 1.0E8d), 2, z, false, true, roundingMode) + "亿";
            }
            if (10000 <= number.longValue()) {
                return a(new BigDecimal(number.doubleValue() / 10000.0d), 2, z, false, true, roundingMode) + "万";
            }
            return a(new BigDecimal(number.doubleValue()), i, z, false, false, roundingMode);
        }
        throw new IllegalArgumentException("roundingMode can not be null.");
    }

    public static String a(Number number, int i, boolean z, boolean z2, boolean z3) {
        return a(number != null ? new BigDecimal(number.doubleValue()) : null, i, z, z2, z3, RoundingMode.HALF_UP);
    }

    public static String a(BigDecimal bigDecimal, int i, boolean z, boolean z2, boolean z3, RoundingMode roundingMode) {
        if (bigDecimal == null) {
            return "-";
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("###,##0");
        } else {
            sb.append("##0");
        }
        if (i >= 1) {
            sb.append(Operators.DOT_STR);
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(z3 ? "0" : "#");
            }
        }
        if (z2) {
            sb.append("%");
        }
        DecimalFormat decimalFormat = new DecimalFormat(sb.toString());
        decimalFormat.setRoundingMode(roundingMode);
        return decimalFormat.format(bigDecimal);
    }

    public static String a(String str, int i, boolean z, boolean z2, boolean z3) {
        if (str == null || str.equals("") || str.equals(com.igexin.push.core.b.m)) {
            return "-";
        }
        BigDecimal bigDecimal = new BigDecimal(str);
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("###,##0");
        } else {
            sb.append("##0");
        }
        if (i >= 1) {
            sb.append(Operators.DOT_STR);
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(z2 ? "0" : "#");
            }
        }
        if (z3) {
            sb.append("%");
        }
        return new DecimalFormat(sb.toString()).format(bigDecimal.doubleValue());
    }

    public static String a(String str) {
        return (str == null || str.equals("") || str.equals(com.igexin.push.core.b.m)) ? "-" : a(str, 2, true, false, false);
    }

    public static boolean a() {
        return Locale.getDefault().getLanguage().equals("zh");
    }
}
