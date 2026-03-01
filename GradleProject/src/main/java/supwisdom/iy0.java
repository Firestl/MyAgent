package supwisdom;

import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import io.dcloud.common.adapter.util.DeviceInfo;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: DnspodFree.java */
/* JADX INFO: loaded from: classes2.dex */
public final class iy0 implements fy0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7999a;
    public final int b;

    public iy0(String str) {
        this(str, 10);
    }

    @Override // supwisdom.fy0
    public Record[] a(ey0 ey0Var, NetworkInfo networkInfo) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(DeviceInfo.HTTP_PROTOCOL + this.f7999a + "/d?ttl=1&dn=" + ey0Var.f7544a).openConnection();
        httpURLConnection.setConnectTimeout(3000);
        httpURLConnection.setReadTimeout(this.b * 1000);
        Record[] recordArr = null;
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        int contentLength = httpURLConnection.getContentLength();
        if (contentLength > 0 && contentLength <= 1024) {
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[contentLength];
            int i = inputStream.read(bArr);
            inputStream.close();
            if (i <= 0) {
                return null;
            }
            String[] strArrSplit = new String(bArr, 0, i).split(",");
            if (strArrSplit.length != 2) {
                return null;
            }
            try {
                int i2 = Integer.parseInt(strArrSplit[1]);
                String[] strArrSplit2 = strArrSplit[0].split(";");
                if (strArrSplit2.length == 0) {
                    return null;
                }
                recordArr = new Record[strArrSplit2.length];
                long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                for (int i3 = 0; i3 < strArrSplit2.length; i3++) {
                    recordArr[i3] = new Record(strArrSplit2[i3], 1, i2, jCurrentTimeMillis, Record.Source.DnspodFree);
                }
            } catch (Exception unused) {
            }
        }
        return recordArr;
    }

    public iy0(String str, int i) {
        this.f7999a = str;
        this.b = i;
    }

    public iy0() {
        this("119.29.29.29");
    }
}
