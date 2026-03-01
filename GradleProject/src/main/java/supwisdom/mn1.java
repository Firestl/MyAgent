package supwisdom;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

/* JADX INFO: compiled from: BarcodeUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class mn1 {
    public static Bitmap a(String str, int i) {
        Log.i("生成码数据", "generateCodeByStr: " + str);
        try {
            return a(str, 300, 300, i);
        } catch (WriterException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap a(String str, int i, int i2, int i3) throws WriterException {
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hashtable.put(EncodeHintType.MARGIN, Integer.valueOf(i3));
        BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 300, 300, hashtable);
        int width = bitMatrixEncode.getWidth();
        int height = bitMatrixEncode.getHeight();
        int[] iArr = new int[width * height];
        for (int i4 = 0; i4 < height; i4++) {
            for (int i5 = 0; i5 < width; i5++) {
                if (bitMatrixEncode.get(i5, i4)) {
                    iArr[(i4 * width) + i5] = -16777216;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }
}
