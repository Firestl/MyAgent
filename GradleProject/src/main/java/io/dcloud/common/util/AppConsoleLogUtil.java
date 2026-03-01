package io.dcloud.common.util;

import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class AppConsoleLogUtil {
    public static int LOG_MAXLENGTH = 1000;
    public static String TAG = "console";

    public static void DCLog(String str, String str2) {
        if (BaseInfo.SyncDebug) {
            if (TextUtils.isEmpty(str)) {
                Log.i(TAG, str);
                return;
            }
            int length = str.length();
            String str3 = Operators.ARRAY_START_STR + str2 + Operators.ARRAY_END_STR;
            if (length <= LOG_MAXLENGTH) {
                Log.i(TAG, str3 + str);
                return;
            }
            int iHashCode = str.hashCode();
            String str4 = "[___Connect_" + iHashCode + "___]";
            String str5 = "[___Connect_" + iHashCode + "___END]";
            int i = 0;
            while (i < length) {
                int i2 = length - i;
                int i3 = LOG_MAXLENGTH;
                int i4 = i2 >= i3 ? i3 + i : length;
                String strSubstring = str.substring(i, i4);
                if (i4 == length) {
                    Log.i(TAG, str3 + str4 + strSubstring + str5);
                } else {
                    Log.i(TAG, str3 + str4 + strSubstring);
                }
                i = i4;
            }
        }
    }
}
