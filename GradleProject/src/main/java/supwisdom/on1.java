package supwisdom;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.supwisdom.superapp.util.BarcodeUtils2;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: GenerateQrCodeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class on1 {
    public static void a(String str, ImageView imageView) {
        Bitmap bitmapA = null;
        if (!TextUtils.isEmpty(str)) {
            if (str.length() == 20) {
                bitmapA = mn1.a(str, 0);
            } else if (str.length() > 20) {
                if ("SP".equals(str.substring(20, 22))) {
                    bitmapA = mn1.a(str, 0);
                } else {
                    byte[] bArrA = a(str);
                    if (bArrA != null) {
                        bitmapA = BarcodeUtils2.a(bArrA, 0);
                    }
                }
            }
        }
        imageView.setImageBitmap(bitmapA);
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("SO")) {
            return null;
        }
        String[] strArrSplit = str.split("SO");
        return a(strArrSplit[1], strArrSplit[0], "");
    }

    public static byte[] a(String str, String str2, String str3) {
        byte[] bytes = str2.getBytes();
        byte[] bArrA = nn1.a(str);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("onCode", bytes);
        linkedHashMap.put("soByte", new byte[]{83, 79});
        linkedHashMap.put("soBytes", bArrA);
        byte[] bArr = new byte[bytes.length + 2 + bArrA.length];
        int length = 0;
        for (byte[] bArr2 : linkedHashMap.values()) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return bArr;
    }
}
