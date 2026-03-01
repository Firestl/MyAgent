package synjones.commerce.utils;

/* JADX INFO: loaded from: classes3.dex */
public class JniQrCodeSign {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f10049a = 0;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final JniQrCodeSign f10050a = new JniQrCodeSign();
    }

    static {
        System.loadLibrary("JniQrCodeSign");
    }

    public native String OqrGetQrcode(int i, int i2, String str, String str2);

    public native String OqrSetPackageName(String str, String str2, String str3);

    public native String QqrInitParam(String str, String str2, int i, String str3);
}
