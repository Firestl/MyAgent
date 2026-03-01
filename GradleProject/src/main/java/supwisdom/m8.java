package supwisdom;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.security.GeneralSecurityException;
import supwisdom.zn0;

/* JADX INFO: compiled from: ShortcutUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class m8 {
    public static qn0 a(Context context) {
        try {
            go0.b();
            zn0.b bVar = new zn0.b();
            bVar.a(context, "core-google-shortcuts.TINK_KEYSET", "core-google-shortcuts.PREF_FILE_NAME");
            bVar.a(fo0.h());
            bVar.a(String.format("android-keystore://%s", "core-google-shortcuts.MASTER_KEY"));
            return bVar.a().a();
        } catch (IOException | GeneralSecurityException e2) {
            Log.e("ShortcutUtils", "could not get or create keyset handle.", e2);
            return null;
        }
    }
}
