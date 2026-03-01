package io.dcloud.common.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.umeng.analytics.pro.bq;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.g.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import supwisdom.y7;

/* JADX INFO: loaded from: classes2.dex */
public class TelephonyUtil {
    public static String AId = "";
    public static String[] MultiIMEITemp = null;
    public static final String OLD_UUID_FILE_NAME = ".imei.txt";
    public static final String TAG = "TelephonyUtil";
    public static final String UUID_FILE_NAME = ".DC4278477faeb9.txt";
    public static boolean isGetAId = false;
    public static boolean isGetIMSI = false;
    public static boolean isGetMultiIMEI = false;
    public static String mImei = "";
    public static String muuid = null;
    public static String sIMSI = null;
    public static String sImei = "";
    public static String sImeiAndBakInfo;
    public static String sOriginalImeiAndBakInfo;

    public static String createRandomBSFile(Context context, File file, File file2, String str) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        String strReplace = UUID.randomUUID().toString().replaceAll("-", "").replace("\n", "");
        byte[] bytes = strReplace.getBytes();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (!FileUtil.needMediaStoreOpenFile(context)) {
                DHFile.copyFile(file.getPath(), file2.getPath());
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return strReplace;
    }

    public static String getAId(Context context) {
        if (!isGetAId) {
            AId = Settings.Secure.getString(context.getContentResolver(), a.a("aWZsemdhbFdhbA=="));
            isGetAId = true;
        }
        return AId;
    }

    public static String getAPSubId(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                Object objInvokeMethod = ReflectUtils.invokeMethod(context.getSystemService("phone"), a.a("b218W31qe2t6YWptekFs"), new Class[0], new Object[0]);
                if (objInvokeMethod != null) {
                    return (String) objInvokeMethod;
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String getIMEI(Context context) {
        return getIMEI(context, true);
    }

    public static String getIMEIS(Context context) {
        try {
            String[] multiIMEI = getMultiIMEI(context);
            if (multiIMEI != null) {
                return TextUtils.join(",", multiIMEI);
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String getIMSI(Context context) {
        if (context == null) {
            return "";
        }
        if (y7.a(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        if (isGetIMSI) {
            return sIMSI;
        }
        String str = sIMSI;
        if (str != null) {
            return str;
        }
        int subId = getSubId(0, context);
        int subId2 = getSubId(1, context);
        if (subId == -1 && subId2 == -1) {
            sIMSI = getAPSubId(context);
        } else {
            String str2 = (String) getPhoneInfo(subId, context);
            String str3 = (String) getPhoneInfo(subId2, context);
            if (!PdrUtil.isEmpty(str2)) {
                sIMSI = str2;
                if (!PdrUtil.isEmpty(str3) && !str2.equals(str3)) {
                    sIMSI += "," + str3;
                }
            } else if (PdrUtil.isEmpty(str3)) {
                sIMSI = getAPSubId(context);
            } else {
                sIMSI = str3;
            }
        }
        isGetIMSI = true;
        return sIMSI;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00d3 A[PHI: r4
  0x00d3: PHI (r4v6 java.lang.String) = (r4v4 java.lang.String), (r4v7 java.lang.String), (r4v7 java.lang.String) binds: [B:39:0x00be, B:41:0x00c8, B:43:0x00ce] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] getMultiIMEI(android.content.Context r11) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.TelephonyUtil.getMultiIMEI(android.content.Context):java.lang.String[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003d, code lost:
    
        r5 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object getPhoneInfo(int r5, android.content.Context r6) {
        /*
            java.lang.String r0 = "phone"
            java.lang.Object r6 = r6.getSystemService(r0)     // Catch: java.lang.Exception -> L3c
            java.lang.String r0 = "b218W31qe2t6YWptekFs"
            java.lang.String r0 = io.dcloud.g.a.a(r0)     // Catch: java.lang.Exception -> L3c
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L3c
            r2 = 21
            r3 = 0
            r4 = 1
            if (r1 <= r2) goto L27
            java.lang.Class[] r1 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L3c
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L3c
            r1[r3] = r2     // Catch: java.lang.Exception -> L3c
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch: java.lang.Exception -> L3c
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L3c
            r2[r3] = r5     // Catch: java.lang.Exception -> L3c
            java.lang.Object r5 = io.dcloud.common.util.ReflectUtils.invokeMethod(r6, r0, r1, r2)     // Catch: java.lang.Exception -> L3c
            goto L3d
        L27:
            if (r1 != r2) goto L3c
            java.lang.Class[] r1 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L3c
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch: java.lang.Exception -> L3c
            r1[r3] = r2     // Catch: java.lang.Exception -> L3c
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch: java.lang.Exception -> L3c
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L3c
            r2[r3] = r5     // Catch: java.lang.Exception -> L3c
            java.lang.Object r5 = io.dcloud.common.util.ReflectUtils.invokeMethod(r6, r0, r1, r2)     // Catch: java.lang.Exception -> L3c
            goto L3d
        L3c:
            r5 = 0
        L3d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.TelephonyUtil.getPhoneInfo(int, android.content.Context):java.lang.Object");
    }

    public static String getSBBS(Context context, boolean z, boolean z2) {
        return getSBBS(context, z, z2, true);
    }

    public static int getSubId(int i, Context context) {
        Uri uri = Uri.parse("content://telephony/siminfo");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(uri, new String[]{bq.d, "sim_id"}, "sim_id = ?", new String[]{String.valueOf(i)}, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex(bq.d));
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return i2;
            }
            if (cursorQuery == null) {
                return -1;
            }
        } catch (Exception unused) {
            if (cursorQuery == null) {
                return -1;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
        cursorQuery.close();
        return -1;
    }

    public static String getUUID(Context context) {
        if (context == null) {
            return "";
        }
        if (!TextUtils.isEmpty(muuid)) {
            return muuid;
        }
        try {
            String string = Settings.System.getString(context.getContentResolver(), a.a("aWZsemdhbFdhbA=="));
            return string != null ? string : "";
        } finally {
            muuid = "";
        }
    }

    public static String getWifiData(Context context) {
        Object objInvokeMethod;
        Object systemService = context.getSystemService(a.a("f2FuYQ=="));
        if (systemService == null || (objInvokeMethod = ReflectUtils.invokeMethod(systemService, a.a("b218S2dmZm1rfGFnZkFmbmc"), new Class[0], new Object[0])) == null || objInvokeMethod == null) {
            return null;
        }
        Object objInvokeMethod2 = ReflectUtils.invokeMethod(objInvokeMethod, a.a("b218RWlrSWxsem17ew"), new Class[0], new Object[0]);
        String str = objInvokeMethod2 != null ? (String) objInvokeMethod2 : null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replace(Constants.COLON_SEPARATOR, "");
    }

    public static boolean isUnValid(String str) {
        return TextUtils.isEmpty(str) || str.contains("Unknown") || str.contains("00000000");
    }

    public static String savePublicFile(File file, File file2, String str, String str2, Context context) throws IOException {
        String string;
        if (!file.exists() || file.length() <= 0) {
            return createRandomBSFile(context, file, file2, UUID_FILE_NAME);
        }
        try {
            string = IOUtil.toString(new FileInputStream(file));
            if (file2 == null) {
                return string;
            }
            try {
                if (FileUtil.needMediaStoreOpenFile(context)) {
                    return string;
                }
                if (!file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                    file2.createNewFile();
                }
                DHFile.copyFile(str, str2);
                return string;
            } catch (Exception unused) {
                return string == null ? createRandomBSFile(context, file, file2, UUID_FILE_NAME) : string;
            }
        } catch (Exception unused2) {
            string = null;
        }
    }

    public static String updateIMEI(Context context) {
        if (!PdrUtil.isEmpty(mImei)) {
            return mImei;
        }
        String[] multiIMEI = getMultiIMEI(context);
        if (multiIMEI == null) {
            mImei = "";
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : multiIMEI) {
            sb.append(str);
            sb.append(",");
        }
        if (sb.lastIndexOf(",") >= sb.length() - 1) {
            String string = sb.deleteCharAt(sb.length() - 1).toString();
            mImei = string;
            return string;
        }
        String string2 = sb.toString();
        mImei = string2;
        return string2;
    }

    public static String getIMEI(Context context, boolean z) {
        return getIMEI(context, z, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0237 A[Catch: Exception -> 0x026f, all -> 0x029f, TRY_LEAVE, TryCatch #0 {Exception -> 0x026f, blocks: (B:133:0x022e, B:136:0x0237), top: B:197:0x022e }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02b0 A[PHI: r9
  0x02b0: PHI (r9v13 java.lang.String) = (r9v14 java.lang.String), (r9v19 java.lang.String) binds: [B:174:0x02ad, B:165:0x029c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getSBBS(android.content.Context r19, boolean r20, boolean r21, boolean r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 781
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.TelephonyUtil.getSBBS(android.content.Context, boolean, boolean, boolean):java.lang.String");
    }

    public static String getIMEI(Context context, boolean z, boolean z2) {
        return getSBBS(context, z, z2, true);
    }
}
