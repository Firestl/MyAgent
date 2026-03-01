package io.dcloud.common.DHInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IFeature {
    public static final String F_ACCELEROMETER = "Accelerometer";
    public static final String F_AUDIO = "Audio";
    public static final String F_BARCODE = "Barcode";
    public static final String F_CAMERA = "Camera";
    public static final String F_CONSOLE = "Console";
    public static final String F_CONTACTS = "Contacts";
    public static final String F_DEVICE = "Device";
    public static final String F_DOWNLOADER = "Downloader";
    public static final String F_EVENTS = "Events";
    public static final String F_FILE = "File";
    public static final String F_GALLERY = "Gallery";
    public static final String F_GEOLOCATION = "Geolocation";
    public static final String F_INVOCATION = "Invocation";
    public static final String F_MAPS = "Maps";
    public static final String F_MESSAGING = "Messaging";
    public static final String F_NAVIGATOR = "Navigator";
    public static final String F_ORIENTATION = "Orientation";
    public static final String F_PAYMENT = "Payment";
    public static final String F_PROXIMITY = "Proximity";
    public static final String F_PUSH = "Push";
    public static final String F_RUNTIME = "Runtime";
    public static final String F_SHARE = "Share";
    public static final String F_SPEECH = "Speech";
    public static final String F_SPLASHSCREEN = "Splashscreen";
    public static final String F_STATISTIC = "Statistic";
    public static final String F_STORAGE = "Storage";
    public static final String F_UI = "UI";
    public static final String F_XMLHTTPREQUEST = "XMLHttpRequest";
    public static final String F_ZIP = "Zip";

    void dispose(String str);

    String execute(IWebview iWebview, String str, String[] strArr);

    void init(AbsMgr absMgr, String str);
}
