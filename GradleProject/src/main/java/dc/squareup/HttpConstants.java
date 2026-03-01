package dc.squareup;

/* JADX INFO: loaded from: classes2.dex */
public class HttpConstants {
    public static String defaultUAConstants = "UNI-APP/1.0";

    public static String getDefaultUA() {
        return defaultUAConstants;
    }

    public static void setUA(String str) {
        defaultUAConstants = str;
    }
}
