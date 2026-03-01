package supwisdom;

import android.content.res.Configuration;
import android.os.Build;

/* JADX INFO: compiled from: ConfigurationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class m9 {
    public static n9 a(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? n9.a(configuration.getLocales()) : n9.a(configuration.locale);
    }
}
