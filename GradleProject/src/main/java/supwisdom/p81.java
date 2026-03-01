package supwisdom;

import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class p81 {
    public static boolean a() {
        try {
            Class.forName("com.sangfor.classloaderhook.HookedApplication");
            return true;
        } catch (ClassNotFoundException unused) {
            SFLogN.c("SFSdkUtils", "not inject");
            return false;
        }
    }
}
