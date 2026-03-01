package supwisdom;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h {
    public Bitmap a(String str, int i) {
        int i2 = (b0.f6999a.getResources().getDisplayMetrics().widthPixels * 3) / 4;
        return a(str, i2, i2, i, null);
    }

    public Bitmap a(String str, int i, int i2, int i3, Map<EncodeHintType, Object> map) throws WriterException {
        HashMap map2 = new HashMap();
        map2.put(EncodeHintType.MARGIN, 1);
        if (map != null) {
            map2.putAll(map);
        }
        BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, map2);
        int width = bitMatrixEncode.getWidth();
        int height = bitMatrixEncode.getHeight();
        int i4 = width * height;
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i5] = -1;
        }
        for (int i6 = 0; i6 < height; i6++) {
            for (int i7 = 0; i7 < width; i7++) {
                if (bitMatrixEncode.get(i7, i6)) {
                    iArr[(i6 * width) + i7] = i3;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }
}
