package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class yt0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile cu0 f9916a;
    public static final yt0 b = new yt0();

    public final void a(Context context) {
        if (f9916a == null) {
            f9916a = new cu0(context, "push");
        }
    }

    public synchronized String b(Context context) {
        String str;
        a(context);
        str = "";
        SharedPreferences sharedPreferences = f9916a.f7256a;
        boolean z = true;
        if (sharedPreferences != null && sharedPreferences.contains("key_push_token")) {
            SharedPreferences sharedPreferences2 = f9916a.f7256a;
            if (sharedPreferences2 == null || !sharedPreferences2.contains("key_aes_gcm")) {
                z = false;
            }
            if (z) {
                SharedPreferences sharedPreferences3 = f9916a.f7256a;
                String string = sharedPreferences3 != null ? sharedPreferences3.getString("key_push_token", "") : "";
                SharedPreferences sharedPreferences4 = f9916a.f7256a;
                byte[] bArrDecode = Base64.decode(sharedPreferences4 != null ? sharedPreferences4.getString("key_aes_gcm", "") : "", 0);
                String str2 = "";
                if (!TextUtils.isEmpty(string) && bArrDecode != null && bArrDecode.length >= 16) {
                    try {
                        SecretKeySpec secretKeySpec = new SecretKeySpec(bArrDecode, "AES");
                        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                        String strSubstring = string.substring(0, 24);
                        String strSubstring2 = string.substring(24);
                        if (!TextUtils.isEmpty(strSubstring) && !TextUtils.isEmpty(strSubstring2)) {
                            cipher.init(2, secretKeySpec, new GCMParameterSpec(128, tt0.a(strSubstring)));
                            str2 = new String(cipher.doFinal(tt0.a(strSubstring2)), StandardCharsets.UTF_8);
                        }
                    } catch (Exception e2) {
                        String str3 = "GCM decrypt data exception: " + e2.getMessage();
                    }
                }
                if (TextUtils.isEmpty(str2)) {
                    f9916a.a("key_aes_gcm");
                    f9916a.a("key_push_token");
                } else {
                    str = str2;
                }
            } else {
                f9916a.a("key_push_token");
            }
        }
        return str;
    }

    public synchronized void a(Context context, String str) {
        byte[] bArr;
        byte[] bArr2;
        a(context);
        if (TextUtils.isEmpty(str)) {
            f9916a.a("key_push_token");
        } else {
            String strA = tt0.a(context, context.getPackageName());
            byte[] bArrA = tt0.a("EA23F5B8C7577CDC744ABD1C6D7E143D5123F8F282BF4E7853C1EC86BD2EDD22");
            byte[] bArrA2 = tt0.a(strA);
            try {
                bArr = new byte[32];
                new SecureRandom().nextBytes(bArr);
            } catch (Exception unused) {
                bArr = new byte[0];
            }
            tt0.a(bArrA, -4);
            byte[] bArrA3 = tt0.a(bArrA, bArrA2);
            tt0.a(bArrA3, 6);
            String strEncodeToString = Base64.encodeToString(tt0.a(bArrA3, bArr), 0);
            boolean zA = f9916a.a("key_aes_gcm", strEncodeToString);
            byte[] bArrDecode = Base64.decode(strEncodeToString, 0);
            String str2 = "";
            if (!TextUtils.isEmpty(str) && bArrDecode != null && bArrDecode.length >= 16) {
                try {
                    try {
                        bArr2 = new byte[12];
                        new SecureRandom().nextBytes(bArr2);
                    } catch (Exception unused2) {
                        bArr2 = new byte[0];
                    }
                    byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArrDecode, "AES");
                    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                    cipher.init(1, secretKeySpec, new GCMParameterSpec(128, bArr2));
                    byte[] bArrDoFinal = cipher.doFinal(bytes);
                    if (bArrDoFinal != null && bArrDoFinal.length != 0) {
                        str2 = tt0.a(bArr2) + tt0.a(bArrDoFinal);
                    }
                } catch (GeneralSecurityException e2) {
                    String str3 = "GCM encrypt data error" + e2.getMessage();
                }
            }
            if (zA && !TextUtils.isEmpty(str2)) {
                f9916a.a("key_push_token", str2);
            }
        }
    }
}
