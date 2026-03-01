package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class qx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f8966a = new int[4];
    public final StringBuilder b = new StringBuilder();

    public int a(ew ewVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f8966a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iC = ewVar.c();
        int iC2 = iArr[1];
        int i = 0;
        for (int i2 = 0; i2 < 2 && iC2 < iC; i2++) {
            int iA = tx.a(ewVar, iArr2, iC2, tx.g);
            sb.append((char) ((iA % 10) + 48));
            for (int i3 : iArr2) {
                iC2 += i3;
            }
            if (iA >= 10) {
                i |= 1 << (1 - i2);
            }
            if (i2 != 1) {
                iC2 = ewVar.c(ewVar.b(iC2));
            }
        }
        if (sb.length() != 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (Integer.parseInt(sb.toString()) % 4 == i) {
            return iC2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public xv a(int i, ew ewVar, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.b;
        sb.setLength(0);
        int iA = a(ewVar, iArr, sb);
        String string = sb.toString();
        Map<ResultMetadataType, Object> mapA = a(string);
        float f = i;
        xv xvVar = new xv(string, null, new yv[]{new yv((iArr[0] + iArr[1]) / 2.0f, f), new yv(iA, f)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (mapA != null) {
            xvVar.a(mapA);
        }
        return xvVar;
    }

    public static Map<ResultMetadataType, Object> a(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}
