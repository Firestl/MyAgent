package dc.squareup.cookie;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CookieCenter {
    public static List<ICookieProvider> providers = new ArrayList();

    public interface ICookieProvider {
        boolean addCookie(String str, String str2);

        String getCookieByUrl(String str);

        boolean removeAllCookie();

        boolean removeSessionCookie();
    }

    public static String getCookies(String str) {
        for (ICookieProvider iCookieProvider : providers) {
            if (iCookieProvider != null) {
                String cookieByUrl = iCookieProvider.getCookieByUrl(str);
                if (!TextUtils.isEmpty(cookieByUrl)) {
                    return cookieByUrl;
                }
            }
        }
        return null;
    }

    public static void putCookies(String str, String str2) {
        for (ICookieProvider iCookieProvider : providers) {
            if (iCookieProvider != null) {
                iCookieProvider.addCookie(str, str2);
            }
        }
    }

    public static synchronized void registerProvider(ICookieProvider iCookieProvider) {
        providers.add(iCookieProvider);
    }

    public static String removeAllCookie() {
        for (ICookieProvider iCookieProvider : providers) {
            if (iCookieProvider != null) {
                iCookieProvider.removeAllCookie();
            }
        }
        return null;
    }

    public static String removeSessionCookie() {
        for (ICookieProvider iCookieProvider : providers) {
            if (iCookieProvider != null) {
                iCookieProvider.removeSessionCookie();
            }
        }
        return null;
    }

    public static synchronized void unRegisterProvider(ICookieProvider iCookieProvider) {
        providers.remove(iCookieProvider);
    }
}
