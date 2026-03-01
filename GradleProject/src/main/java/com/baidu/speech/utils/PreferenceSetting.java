package com.baidu.speech.utils;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public final class PreferenceSetting {
    public static final String FILE_NAME = "bdvrsetting";
    public static final String FILE_NAME_MD5 = Util.toMd5(FILE_NAME.getBytes(), false);
    public static final String VTLN_KEY = "vtln";
    public static final int VTLN_LIMIT = 255;
    public static final String VTLN_SECRET_KEY = "BDVRVtln*!Secret";

    public static boolean getBoolean(Context context, String str, boolean z) {
        return context.getSharedPreferences(FILE_NAME_MD5, 0).getBoolean(str, z);
    }

    public static String getString(Context context, String str, String str2) {
        return context.getSharedPreferences(FILE_NAME_MD5, 0).getString(str, str2);
    }

    public static int getVtlnWithCheckSum(Context context) {
        String string = getString(context, VTLN_KEY, "");
        if (string.indexOf(Operators.OR) == -1) {
            return -1;
        }
        String[] strArrSplit = string.split("\\|\\|");
        if (strArrSplit.length < 2) {
            return -1;
        }
        String str = strArrSplit[1];
        String str2 = strArrSplit[0];
        if (Util.toMd5((str2 + VTLN_SECRET_KEY).getBytes(), false).equals(str)) {
            return Integer.parseInt(str2);
        }
        return -1;
    }

    public static void removeString(Context context, String str) {
        context.getSharedPreferences(FILE_NAME_MD5, 0).edit().remove(str).commit();
    }

    public static void setBoolean(Context context, String str, boolean z) {
        context.getSharedPreferences(FILE_NAME_MD5, 0).edit().putBoolean(str, z).commit();
    }

    public static void setString(Context context, String str, String str2) {
        context.getSharedPreferences(FILE_NAME_MD5, 0).edit().putString(str, str2).commit();
    }

    public static boolean setVtlnWithCheckSum(Context context, int i) {
        if (i < 0 || i > 255) {
            return false;
        }
        setString(context, VTLN_KEY, i + Operators.OR + Util.toMd5((i + VTLN_SECRET_KEY).getBytes(), false));
        return true;
    }
}
