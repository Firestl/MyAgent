package supwisdom;

import com.sangfor.sdk.SFSecuritySDK;
import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class c91 {
    public static boolean a() {
        SFLogN.c("SFLaunchEntry", "exec isSubApp... ");
        SFSecuritySDK sFSecuritySDKA = b71.b().a();
        if (sFSecuritySDKA == null) {
            SFLogN.b("SFLaunchEntry", "isSubApp failed", "SFSecuritySDK not inited");
            return false;
        }
        if ((sFSecuritySDKA.e() & 8) == 0) {
            return true;
        }
        SFLogN.c("SFLaunchEntry", "it's hostApp");
        return false;
    }
}
