package supwisdom;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.base.R;
import java.util.Locale;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class bg0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @GuardedBy("sCache")
    public static final p4<String, String> f7069a = new p4<>();

    @GuardedBy("sCache")
    public static Locale b;

    public static String a(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return a(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return a(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unexpected error code ");
                sb.append(i);
                Log.e("GoogleApiAvailability", sb.toString());
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return a(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return a(context, "common_google_play_services_restricted_profile_title");
        }
    }

    public static String b(Context context, int i) {
        String strA = i == 6 ? a(context, "common_google_play_services_resolution_required_title") : a(context, i);
        return strA == null ? context.getResources().getString(R.string.common_google_play_services_notification_ticker) : strA;
    }

    public static String c(Context context, int i) {
        Resources resources = context.getResources();
        String strB = b(context);
        if (i == 1) {
            return resources.getString(R.string.common_google_play_services_install_text, strB);
        }
        if (i == 2) {
            return qh0.c(context) ? resources.getString(R.string.common_google_play_services_wear_update_text) : resources.getString(R.string.common_google_play_services_update_text, strB);
        }
        if (i == 3) {
            return resources.getString(R.string.common_google_play_services_enable_text, strB);
        }
        if (i == 5) {
            return a(context, "common_google_play_services_invalid_account_text", strB);
        }
        if (i == 7) {
            return a(context, "common_google_play_services_network_error_text", strB);
        }
        if (i == 9) {
            return resources.getString(R.string.common_google_play_services_unsupported_text, strB);
        }
        if (i == 20) {
            return a(context, "common_google_play_services_restricted_profile_text", strB);
        }
        switch (i) {
            case 16:
                return a(context, "common_google_play_services_api_unavailable_text", strB);
            case 17:
                return a(context, "common_google_play_services_sign_in_failed_text", strB);
            case 18:
                return resources.getString(R.string.common_google_play_services_updating_text, strB);
            default:
                return resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue, strB);
        }
    }

    public static String d(Context context, int i) {
        return (i == 6 || i == 19) ? a(context, "common_google_play_services_resolution_required_text", b(context)) : c(context, i);
    }

    public static String e(Context context, int i) {
        Resources resources = context.getResources();
        return i != 1 ? i != 2 ? i != 3 ? resources.getString(android.R.string.ok) : resources.getString(R.string.common_google_play_services_enable_button) : resources.getString(R.string.common_google_play_services_update_button) : resources.getString(R.string.common_google_play_services_install_button);
    }

    public static String b(Context context) {
        String packageName = context.getPackageName();
        try {
            return wh0.b(context).a(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String a(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String strA = a(context, str);
        if (strA == null) {
            strA = resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, strA, str2);
    }

    public static String a(Context context, String str) {
        synchronized (f7069a) {
            Locale localeA = m9.a(context.getResources().getConfiguration()).a(0);
            if (!localeA.equals(b)) {
                f7069a.clear();
                b = localeA;
            }
            String str2 = f7069a.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources resourcesB = ic0.b(context);
            if (resourcesB == null) {
                return null;
            }
            int identifier = resourcesB.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String strValueOf = String.valueOf(str);
                Log.w("GoogleApiAvailability", strValueOf.length() != 0 ? "Missing resource: ".concat(strValueOf) : new String("Missing resource: "));
                return null;
            }
            String string = resourcesB.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                String strValueOf2 = String.valueOf(str);
                Log.w("GoogleApiAvailability", strValueOf2.length() != 0 ? "Got empty resource: ".concat(strValueOf2) : new String("Got empty resource: "));
                return null;
            }
            f7069a.put(str, string);
            return string;
        }
    }

    public static String a(Context context) {
        return context.getResources().getString(R.string.common_google_play_services_notification_channel_name);
    }
}
