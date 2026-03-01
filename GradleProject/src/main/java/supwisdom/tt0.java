package supwisdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.hihonor.push.sdk.internal.HonorPushErrorEnum;
import com.hihonor.push.sdk.ipc.HonorApiAvailability$PackageStates;
import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public class tt0 {
    public static String a(byte[] bArr) {
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e2) {
            wt0.a("DeflateUtil", "close", e2);
        }
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        try {
            byte[] bytes = upperCase.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("0x");
                int i2 = i * 2;
                sb.append(new String(new byte[]{bytes[i2]}, StandardCharsets.UTF_8));
                bArr[i] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i2 + 1]}, StandardCharsets.UTF_8)).byteValue());
            }
        } catch (NumberFormatException e2) {
            String str2 = "hex string 2 byte array exception : " + e2.getMessage();
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (i < 0) {
                bArr[i2] = (byte) (bArr[i2] << (-i));
            } else {
                bArr[i2] = (byte) (bArr[i2] >> i);
            }
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        if (bArr != null) {
            int length = bArr.length;
            if (bArr2.length != length) {
                return null;
            }
            bArr3 = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
            }
        }
        return bArr3;
    }

    public static int b(Context context) {
        HonorApiAvailability$PackageStates honorApiAvailability$PackageStates;
        if (context == null) {
            throw new NullPointerException("context must not be null.");
        }
        ut0 ut0VarA = a(context);
        String strC = ut0VarA.c();
        String str = "service package name is " + strC;
        if (TextUtils.isEmpty(strC)) {
            honorApiAvailability$PackageStates = HonorApiAvailability$PackageStates.NOT_INSTALLED;
        } else {
            try {
                honorApiAvailability$PackageStates = context.getPackageManager().getApplicationInfo(strC, 0).enabled ? HonorApiAvailability$PackageStates.ENABLED : HonorApiAvailability$PackageStates.DISABLED;
            } catch (PackageManager.NameNotFoundException unused) {
                honorApiAvailability$PackageStates = HonorApiAvailability$PackageStates.NOT_INSTALLED;
            }
        }
        if (HonorApiAvailability$PackageStates.NOT_INSTALLED.equals(honorApiAvailability$PackageStates)) {
            Log.i("HonorApiAvailability", "push service is not installed");
            return 8002008;
        }
        if (HonorApiAvailability$PackageStates.DISABLED.equals(honorApiAvailability$PackageStates)) {
            Log.i("HonorApiAvailability", "push service is disabled");
            return 8002007;
        }
        if (!TextUtils.equals(strC, "android") || TextUtils.isEmpty(ut0VarA.e())) {
            return 8002006;
        }
        return HonorPushErrorEnum.SUCCESS.statusCode;
    }

    public static <TResult> pt0<TResult> a(Callable<TResult> callable) {
        ExecutorService executorService = iu0.c.b;
        hu0 hu0Var = new hu0();
        try {
            executorService.execute(new qu0(hu0Var, callable));
        } catch (Exception e2) {
            hu0Var.a(e2);
        }
        return hu0Var.f7869a;
    }

    public static void a(Handler handler) {
        if (Looper.myLooper() != handler.getLooper()) {
            throw new IllegalStateException("Must be called on the handler thread");
        }
    }

    public static ut0 a(Context context) {
        ut0 ut0Var = new ut0();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("android", "com.hihonor.android.pushagentproxy.HiPushService"));
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (listQueryIntentServices.size() > 0) {
            Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
            if (it.hasNext()) {
                ResolveInfo next = it.next();
                String str = next.serviceInfo.applicationInfo.packageName;
                String strA = a(context, str);
                ut0Var.a(str);
                ut0Var.b(next.serviceInfo.name);
                ut0Var.c(strA);
            }
        }
        return ut0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0050 -> B:18:0x0051). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r11, java.lang.String r12) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getCertFingerprint pkgName="
            r0.append(r1)
            r0.append(r12)
            java.lang.String r1 = "isOnlyOne="
            r0.append(r1)
            r1 = 1
            r0.append(r1)
            r0.toString()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.content.pm.PackageManager r11 = r11.getPackageManager()
            r2 = 0
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            r4 = 30
            if (r3 < r4) goto L45
            r3 = 134217728(0x8000000, float:3.85186E-34)
            android.content.pm.PackageInfo r11 = r11.getPackageInfo(r12, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            if (r11 == 0) goto L50
            android.content.pm.SigningInfo r11 = r11.signingInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            if (r11 == 0) goto L50
            boolean r12 = r11.hasMultipleSigners()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            if (r12 == 0) goto L40
            android.content.pm.Signature[] r11 = r11.getApkContentsSigners()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            goto L51
        L40:
            android.content.pm.Signature[] r11 = r11.getSigningCertificateHistory()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            goto L51
        L45:
            r3 = 64
            android.content.pm.PackageInfo r11 = r11.getPackageInfo(r12, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            if (r11 == 0) goto L50
            android.content.pm.Signature[] r11 = r11.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L50
            goto L51
        L50:
            r11 = r2
        L51:
            r12 = 0
            if (r11 == 0) goto La2
            int r3 = r11.length
            if (r3 <= 0) goto La2
            int r3 = r11.length
            r4 = 0
        L59:
            if (r4 >= r3) goto La2
            r5 = r11[r4]
            byte[] r5 = r5.toByteArray()
            java.lang.String r6 = "SHA256"
            java.security.MessageDigest r6 = java.security.MessageDigest.getInstance(r6)     // Catch: java.security.NoSuchAlgorithmException -> L98
            byte[] r5 = r6.digest(r5)     // Catch: java.security.NoSuchAlgorithmException -> L98
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.security.NoSuchAlgorithmException -> L98
            r6.<init>()     // Catch: java.security.NoSuchAlgorithmException -> L98
            int r7 = r5.length     // Catch: java.security.NoSuchAlgorithmException -> L98
            r8 = 0
        L72:
            if (r8 >= r7) goto L93
            r9 = r5[r8]     // Catch: java.security.NoSuchAlgorithmException -> L98
            r9 = r9 & 255(0xff, float:3.57E-43)
            java.lang.String r9 = java.lang.Integer.toHexString(r9)     // Catch: java.security.NoSuchAlgorithmException -> L98
            java.util.Locale r10 = java.util.Locale.ENGLISH     // Catch: java.security.NoSuchAlgorithmException -> L98
            java.lang.String r9 = r9.toUpperCase(r10)     // Catch: java.security.NoSuchAlgorithmException -> L98
            int r10 = r9.length()     // Catch: java.security.NoSuchAlgorithmException -> L98
            if (r10 != r1) goto L8d
            java.lang.String r10 = "0"
            r6.append(r10)     // Catch: java.security.NoSuchAlgorithmException -> L98
        L8d:
            r6.append(r9)     // Catch: java.security.NoSuchAlgorithmException -> L98
            int r8 = r8 + 1
            goto L72
        L93:
            java.lang.String r5 = r6.toString()     // Catch: java.security.NoSuchAlgorithmException -> L98
            goto L99
        L98:
            r5 = r2
        L99:
            if (r5 == 0) goto L9f
            r0.add(r5)
            goto La2
        L9f:
            int r4 = r4 + 1
            goto L59
        La2:
            boolean r11 = r0.isEmpty()
            if (r11 == 0) goto La9
            goto Lb0
        La9:
            java.lang.Object r11 = r0.get(r12)
            r2 = r11
            java.lang.String r2 = (java.lang.String) r2
        Lb0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.tt0.a(android.content.Context, java.lang.String):java.lang.String");
    }
}
