package io.dcloud.js.map.amap.overlay;

import android.graphics.Bitmap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AMapServicesUtil {
    public static int BUFFER_SIZE = 2048;

    public static ArrayList<LatLng> convertArrList(List<LatLonPoint> list) {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        Iterator<LatLonPoint> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToLatLng(it.next()));
        }
        return arrayList;
    }

    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    public static LatLonPoint convertToLatLonPoint(LatLng latLng) {
        return new LatLonPoint(latLng.latitude, latLng.longitude);
    }

    public static byte[] inputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[BUFFER_SIZE];
        while (true) {
            int i = inputStream.read(bArr, 0, BUFFER_SIZE);
            if (i == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f), (int) (bitmap.getHeight() * f), true);
    }
}
