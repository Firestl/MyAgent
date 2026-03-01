package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: WebvttParserUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class x60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f9713a = Pattern.compile("^NOTE(( |\t).*)?$");
    public static final Pattern b = Pattern.compile("^\ufeff?WEBVTT(( |\t).*)?$");

    public static void a(o80 o80Var) throws com.google.android.exoplayer2.g.f {
        String strY = o80Var.y();
        if (strY == null || !b.matcher(strY).matches()) {
            throw new com.google.android.exoplayer2.g.f("Expected WEBVTT. Got " + strY);
        }
    }

    public static float b(String str) throws NumberFormatException {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static long a(String str) throws NumberFormatException {
        String[] strArrSplit = str.split("\\.", 2);
        long j = 0;
        for (String str2 : strArrSplit[0].split(Constants.COLON_SEPARATOR)) {
            j = (j * 60) + Long.parseLong(str2);
        }
        return ((j * 1000) + Long.parseLong(strArrSplit[1])) * 1000;
    }

    public static Matcher b(o80 o80Var) {
        String strY;
        while (true) {
            String strY2 = o80Var.y();
            if (strY2 == null) {
                return null;
            }
            if (f9713a.matcher(strY2).matches()) {
                do {
                    strY = o80Var.y();
                    if (strY != null) {
                    }
                } while (!strY.isEmpty());
            } else {
                Matcher matcher = v60.b.matcher(strY2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }
}
