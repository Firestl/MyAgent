package master.flame.danmaku.danmaku.loader.android;

import master.flame.danmaku.danmaku.loader.ILoader;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuLoaderFactory {
    public static String TAG_ACFUN = "acfun";
    public static String TAG_BILI = "bili";

    public static ILoader create(String str) {
        if (TAG_BILI.equalsIgnoreCase(str)) {
            return BiliDanmakuLoader.instance();
        }
        if (TAG_ACFUN.equalsIgnoreCase(str)) {
            return AcFunDanmakuLoader.instance();
        }
        return null;
    }
}
