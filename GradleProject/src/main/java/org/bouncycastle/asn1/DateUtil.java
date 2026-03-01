package org.bouncycastle.asn1;

import com.google.zxing.client.android.LocaleManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DateUtil {
    public static Long ZERO = longValueOf(0);
    public static final Map localeCache = new HashMap();
    public static Locale EN_Locale = forEN();

    public static Date epochAdjust(Date date) throws ParseException {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return date;
        }
        synchronized (localeCache) {
            Long lLongValueOf = (Long) localeCache.get(locale);
            if (lLongValueOf == null) {
                long time = new SimpleDateFormat("yyyyMMddHHmmssz").parse("19700101000000GMT+00:00").getTime();
                lLongValueOf = time == 0 ? ZERO : longValueOf(time);
                localeCache.put(locale, lLongValueOf);
            }
            if (lLongValueOf != ZERO) {
                return new Date(date.getTime() - lLongValueOf.longValue());
            }
            return date;
        }
    }

    public static Locale forEN() {
        if (LocaleManager.DEFAULT_LANGUAGE.equalsIgnoreCase(Locale.getDefault().getLanguage())) {
            return Locale.getDefault();
        }
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (int i = 0; i != availableLocales.length; i++) {
            if (LocaleManager.DEFAULT_LANGUAGE.equalsIgnoreCase(availableLocales[i].getLanguage())) {
                return availableLocales[i];
            }
        }
        return Locale.getDefault();
    }

    public static Long longValueOf(long j) {
        return Long.valueOf(j);
    }
}
