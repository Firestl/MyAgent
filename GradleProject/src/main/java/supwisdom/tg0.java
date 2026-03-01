package supwisdom;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import io.dcloud.feature.weex_amap.adapter.Constant;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class tg0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Uri f9284a;
    public static final Uri b;

    static {
        Uri uri = Uri.parse("https://plus.google.com/");
        f9284a = uri;
        b = uri.buildUpon().appendPath(Constant.Name.CIRCLES).appendPath("find").build();
    }

    public static Intent a(String str) {
        Uri uriFromParts = Uri.fromParts("package", str, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(uriFromParts);
        return intent;
    }

    public static Intent a(String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri.Builder builderAppendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", str);
        if (!TextUtils.isEmpty(str2)) {
            builderAppendQueryParameter.appendQueryParameter("pcampaignid", str2);
        }
        intent.setData(builderAppendQueryParameter.build());
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }

    public static Intent a() {
        Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }
}
