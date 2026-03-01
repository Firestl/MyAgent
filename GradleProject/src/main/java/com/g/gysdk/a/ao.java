package com.g.gysdk.a;

import android.content.Context;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ao {
    public static int a(String str, Context context) {
        return context.getResources().getIdentifier(str, "drawable", context.getApplicationInfo().packageName);
    }

    public static String a(InputStream inputStream) {
        try {
            byte[] bArr = new byte[4];
            inputStream.read(bArr, 0, 4);
            String strA = a(bArr);
            if (inputStream == null) {
                return strA;
            }
            try {
                inputStream.close();
                return strA;
            } catch (IOException unused) {
                return strA;
            }
        } catch (Exception unused2) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String upperCase = Integer.toHexString(b & 255).toUpperCase();
            if (upperCase.length() < 2) {
                sb.append(0);
            }
            sb.append(upperCase);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static boolean a(String str) {
        return c(str).equals("gif");
    }

    public static boolean b(String str) {
        String strC = c(str);
        return strC.equals(BitmapUtils.IMAGE_KEY_SUFFIX) || strC.equals("jpeg") || strC.equals("png") || strC.equals("bmp");
    }

    public static String c(String str) {
        return ("FFD8FF".equals(str) || str.startsWith("FFD8FF")) ? BitmapUtils.IMAGE_KEY_SUFFIX : str.startsWith("FFD8") ? "jpeg" : ("89504E47".equals(str) || str.startsWith("89504E")) ? "png" : ("47494638".equals(str) || str.startsWith("474946")) ? "gif" : ("424D".equals(str) || str.startsWith("424D")) ? "bmp" : "unknown";
    }
}
