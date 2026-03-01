package io.dcloud.common.util.language;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.client.android.LocaleManager;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageUtil {
    public static final String LanguageBroadCastIntent = "language_uni_broad_cast_intent";
    public static final String LanguageConfigKey = "language_uni_current_key";
    public static final String LanguageConfigSPFile = "language_uni_sp_file";
    public static String deviceDefLocalLanguage = "";
    public static String sCurrentLocalLanguage = "";

    public static Locale getCurrentLocal(Context context) {
        if (context == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 24) {
            return context.getResources().getConfiguration().locale;
        }
        LocaleList locales = context.getResources().getConfiguration().getLocales();
        return (locales == null || locales.size() <= 0) ? context.getResources().getConfiguration().locale : locales.get(0);
    }

    public static String getCurrentLocaleLanguage(Context context) {
        if (!TextUtils.isEmpty(sCurrentLocalLanguage)) {
            return sCurrentLocalLanguage;
        }
        Locale currentLocal = getCurrentLocal(context);
        if (Build.VERSION.SDK_INT < 24 && currentLocal != null) {
            String language = currentLocal.getLanguage();
            language.hashCode();
            byte b = -1;
            int iHashCode = language.hashCode();
            if (iHashCode != 3241) {
                if (iHashCode != 3246) {
                    if (iHashCode != 3276) {
                        if (iHashCode == 3886 && language.equals("zh")) {
                            b = 3;
                        }
                    } else if (language.equals("fr")) {
                        b = 2;
                    }
                } else if (language.equals("es")) {
                    b = 1;
                }
            } else if (language.equals(LocaleManager.DEFAULT_LANGUAGE)) {
                b = 0;
            }
            if (b != 0) {
                if (b == 1) {
                    return "es";
                }
                if (b == 2) {
                    return "fr";
                }
                if (b == 3) {
                    if (currentLocal.toLanguageTag().equalsIgnoreCase("zh-CN")) {
                        return "zh-Hans";
                    }
                    if (currentLocal.toLanguageTag().equalsIgnoreCase("zh-HK")) {
                        return "zh-Hant-HK";
                    }
                    if (currentLocal.toLanguageTag().equalsIgnoreCase("zh-TW")) {
                        return "zh-Hant-TW";
                    }
                }
            }
            return LocaleManager.DEFAULT_LANGUAGE;
        }
        return currentLocal != null ? currentLocal.toLanguageTag() : "";
    }

    public static String getDeviceDefLocalLanguage() {
        return deviceDefLocalLanguage;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getString(com.alibaba.fastjson.JSONObject r6, java.lang.String r7) {
        /*
            if (r6 == 0) goto L85
            android.content.Context r0 = io.dcloud.common.adapter.util.DeviceInfo.sApplicationContext
            java.lang.String r0 = getCurrentLocaleLanguage(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L85
            java.lang.String r1 = getString(r0, r6)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L86
            java.lang.String r2 = "zh-CN"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 == 0) goto L29
            java.lang.String r0 = "zh-Hans"
            java.lang.String r1 = getString(r0, r6)
            goto L86
        L29:
            java.lang.String r2 = "zh-HK"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 == 0) goto L3a
            java.lang.String r0 = "zh-Hant-HK"
            java.lang.String r1 = getString(r0, r6)
            goto L86
        L3a:
            java.lang.String r2 = "zh-TW"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 == 0) goto L4b
            java.lang.String r0 = "zh-Hant-TW"
            java.lang.String r1 = getString(r0, r6)
            goto L86
        L4b:
            java.lang.String r2 = "-"
            java.lang.String[] r0 = r0.split(r2)
            int r2 = r0.length
            r3 = 2
            r4 = 0
            if (r2 == r3) goto L7e
            r5 = 3
            if (r2 == r5) goto L5a
            goto L86
        L5a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = r0[r4]
            r1.append(r2)
            r2 = r0[r3]
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = getString(r1, r6)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L86
            r0 = r0[r4]
            java.lang.String r1 = getString(r0, r6)
            goto L86
        L7e:
            r0 = r0[r4]
            java.lang.String r1 = getString(r0, r6)
            goto L86
        L85:
            r1 = r7
        L86:
            boolean r6 = io.dcloud.common.util.PdrUtil.isEmpty(r1)
            if (r6 == 0) goto L8d
            goto L8e
        L8d:
            r7 = r1
        L8e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.language.LanguageUtil.getString(com.alibaba.fastjson.JSONObject, java.lang.String):java.lang.String");
    }

    public static void initAppLanguageForAppBeforeO(Context context) {
        updateAppBootLanguage(context);
        updateSystemLanguage(context);
    }

    public static void updateAppBootLanguage(Context context) {
        String string;
        String string2 = context.getSharedPreferences(LanguageConfigSPFile, 0).getString(LanguageConfigKey, "");
        try {
            string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("DCLOUD_APP_DEFAULT_LANGUAGE");
        } catch (Exception e2) {
            e2.printStackTrace();
            string = "";
        }
        if (TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string)) {
            string2 = string;
        }
        sCurrentLocalLanguage = "auto".equalsIgnoreCase(string2) ? "" : string2;
        updateDeviceDefLocalLanguage(getCurrentLocal(context));
    }

    public static Context updateContextLanguageAfterO(Context context, boolean z) {
        if (z) {
            updateAppBootLanguage(context);
        }
        return TextUtils.isEmpty(sCurrentLocalLanguage) ? wrapContextConfigurationAfterO(context, deviceDefLocalLanguage) : wrapContextConfigurationAfterO(context, sCurrentLocalLanguage);
    }

    public static void updateDeviceDefLocalLanguage(Locale locale) {
        if (locale != null) {
            deviceDefLocalLanguage = locale.getLanguage() + "-" + locale.getCountry();
        }
    }

    public static void updateLanguage(Context context, String str) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(LanguageConfigSPFile, 0).edit();
        editorEdit.putString(LanguageConfigKey, str);
        editorEdit.commit();
        sCurrentLocalLanguage = str;
        updateSystemLanguage(context);
    }

    public static void updateSystemLanguage(Context context) {
        if (context == null) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            Resources resources = context.getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            String str = sCurrentLocalLanguage;
            if (TextUtils.isEmpty(str)) {
                str = deviceDefLocalLanguage;
            }
            LocaleList localeList = new LocaleList(Locale.forLanguageTag(str));
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            resources.updateConfiguration(configuration, displayMetrics);
            return;
        }
        if (i >= 21) {
            Resources resources2 = context.getResources();
            String str2 = sCurrentLocalLanguage;
            if (TextUtils.isEmpty(str2)) {
                str2 = deviceDefLocalLanguage;
            }
            Locale localeForLanguageTag = Locale.forLanguageTag(str2);
            Locale.setDefault(localeForLanguageTag);
            Configuration configuration2 = resources2.getConfiguration();
            configuration2.setLocale(localeForLanguageTag);
            resources2.updateConfiguration(configuration2, resources2.getDisplayMetrics());
        }
    }

    public static Context wrapContextConfigurationAfterO(Context context, String str) {
        if (Build.VERSION.SDK_INT < 24) {
            return context;
        }
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        LocaleList localeList = new LocaleList(Locale.forLanguageTag(str));
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        resources.updateConfiguration(configuration, displayMetrics);
        return context.createConfigurationContext(configuration);
    }

    public static String getString(String str, JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.containsKey(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }
}
