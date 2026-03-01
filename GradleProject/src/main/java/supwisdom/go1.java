package supwisdom;

import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: compiled from: DateUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class go1 {
    public static final Date b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f7752a = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
    public static final TimeZone c = TimeZone.getTimeZone("GMT");

    /* JADX INFO: compiled from: DateUtils.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> f7753a = new C0214a();

        /* JADX INFO: renamed from: supwisdom.go1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DateUtils.java */
        public static class C0214a extends ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> {
            @Override // java.lang.ThreadLocal
            public SoftReference<Map<String, SimpleDateFormat>> initialValue() {
                return new SoftReference<>(new HashMap());
            }
        }

        public static SimpleDateFormat a(String str) {
            Map<String, SimpleDateFormat> map = f7753a.get().get();
            if (map == null) {
                map = new HashMap<>();
                f7753a.set(new SoftReference<>(map));
            }
            SimpleDateFormat simpleDateFormat = map.get(str);
            if (simpleDateFormat != null) {
                return simpleDateFormat;
            }
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str, Locale.US);
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
            map.put(str, simpleDateFormat2);
            return simpleDateFormat2;
        }
    }

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(c);
        calendar.set(2000, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        b = calendar.getTime();
    }

    public static Date a(String str) {
        return a(str, null, null);
    }

    public static Date a(String str, String[] strArr, Date date) {
        yo1.a(str, "Date value");
        if (strArr == null) {
            strArr = f7752a;
        }
        if (date == null) {
            date = b;
        }
        if (str.length() > 1 && str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        for (String str2 : strArr) {
            SimpleDateFormat simpleDateFormatA = a.a(str2);
            simpleDateFormatA.set2DigitYearStart(date);
            ParsePosition parsePosition = new ParsePosition(0);
            Date date2 = simpleDateFormatA.parse(str, parsePosition);
            if (parsePosition.getIndex() != 0) {
                return date2;
            }
        }
        return null;
    }
}
