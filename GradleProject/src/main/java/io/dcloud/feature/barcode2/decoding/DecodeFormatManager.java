package io.dcloud.feature.barcode2.decoding;

import android.content.Intent;
import android.net.Uri;
import com.dcloud.zxing2.BarcodeFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class DecodeFormatManager {
    public static final Pattern COMMA_PATTERN = Pattern.compile(",");
    public static final Vector<BarcodeFormat> DATA_MATRIX_FORMATS;
    public static final Vector<BarcodeFormat> ONE_D_FORMATS;
    public static final Vector<BarcodeFormat> PRODUCT_FORMATS;
    public static final Vector<BarcodeFormat> QR_CODE_FORMATS;

    static {
        Vector<BarcodeFormat> vector = new Vector<>(5);
        PRODUCT_FORMATS = vector;
        vector.add(BarcodeFormat.UPC_A);
        vector.add(BarcodeFormat.UPC_E);
        vector.add(BarcodeFormat.EAN_13);
        vector.add(BarcodeFormat.EAN_8);
        vector.add(BarcodeFormat.RSS_14);
        Vector<BarcodeFormat> vector2 = new Vector<>(vector.size() + 4);
        ONE_D_FORMATS = vector2;
        vector2.addAll(vector);
        vector2.add(BarcodeFormat.CODE_39);
        vector2.add(BarcodeFormat.CODE_93);
        vector2.add(BarcodeFormat.CODE_128);
        vector2.add(BarcodeFormat.ITF);
        Vector<BarcodeFormat> vector3 = new Vector<>(1);
        QR_CODE_FORMATS = vector3;
        vector3.add(BarcodeFormat.QR_CODE);
        Vector<BarcodeFormat> vector4 = new Vector<>(1);
        DATA_MATRIX_FORMATS = vector4;
        vector4.add(BarcodeFormat.DATA_MATRIX);
    }

    public static Vector<BarcodeFormat> parseDecodeFormats(Intent intent) {
        String stringExtra = intent.getStringExtra("SCAN_FORMATS");
        return parseDecodeFormats(stringExtra != null ? Arrays.asList(COMMA_PATTERN.split(stringExtra)) : null, intent.getStringExtra("SCAN_MODE"));
    }

    public static Vector<BarcodeFormat> parseDecodeFormats(Uri uri) {
        List<String> queryParameters = uri.getQueryParameters("SCAN_FORMATS");
        if (queryParameters != null && queryParameters.size() == 1 && queryParameters.get(0) != null) {
            queryParameters = Arrays.asList(COMMA_PATTERN.split(queryParameters.get(0)));
        }
        return parseDecodeFormats(queryParameters, uri.getQueryParameter("SCAN_MODE"));
    }

    public static Vector<BarcodeFormat> parseDecodeFormats(Iterable<String> iterable, String str) {
        if (iterable != null) {
            Vector<BarcodeFormat> vector = new Vector<>();
            try {
                Iterator<String> it = iterable.iterator();
                while (it.hasNext()) {
                    vector.add(BarcodeFormat.valueOf(it.next()));
                }
                return vector;
            } catch (IllegalArgumentException unused) {
            }
        }
        if (str == null) {
            return null;
        }
        if ("PRODUCT_MODE".equals(str)) {
            return PRODUCT_FORMATS;
        }
        if ("QR_CODE_MODE".equals(str)) {
            return QR_CODE_FORMATS;
        }
        if ("DATA_MATRIX_MODE".equals(str)) {
            return DATA_MATRIX_FORMATS;
        }
        if ("ONE_D_MODE".equals(str)) {
            return ONE_D_FORMATS;
        }
        return null;
    }
}
