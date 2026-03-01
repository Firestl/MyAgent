package com.igexin.assist.control.xiaomi;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
public class XmSystemUtils {
    public static final String KEY_VERSION_CODE = "ro.miui.ui.version.code";
    public static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    public static final String XIAOMI = "Xiaomi".toLowerCase();
    public static Boolean isXiaoMi;

    public static String getProp(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ".concat(String.valueOf(str))).getInputStream()), 1024);
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            String line = bufferedReader.readLine();
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return line;
        } catch (Exception unused2) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isBrandXiaoMi() {
        try {
            if (isXiaoMi != null) {
                return isXiaoMi.booleanValue();
            }
            Boolean boolValueOf = Boolean.valueOf(isMIUI());
            isXiaoMi = boolValueOf;
            return boolValueOf.booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isMIUI() {
        return (TextUtils.isEmpty(getProp("ro.miui.ui.version.name")) && TextUtils.isEmpty(getProp("ro.miui.ui.version.code"))) ? false : true;
    }
}
