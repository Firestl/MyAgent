package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zy implements wv {
    public static int a(yv[] yvVarArr) {
        return Math.max(Math.max(a(yvVarArr[0], yvVarArr[4]), (a(yvVarArr[6], yvVarArr[2]) * 17) / 18), Math.max(a(yvVarArr[1], yvVarArr[5]), (a(yvVarArr[7], yvVarArr[3]) * 17) / 18));
    }

    public static int b(yv[] yvVarArr) {
        return Math.min(Math.min(b(yvVarArr[0], yvVarArr[4]), (b(yvVarArr[6], yvVarArr[2]) * 17) / 18), Math.min(b(yvVarArr[1], yvVarArr[5]), (b(yvVarArr[7], yvVarArr[3]) * 17) / 18));
    }

    @Override // supwisdom.wv
    public void reset() {
    }

    public static int a(yv yvVar, yv yvVar2) {
        if (yvVar == null || yvVar2 == null) {
            return 0;
        }
        return (int) Math.abs(yvVar.a() - yvVar2.a());
    }

    public static int b(yv yvVar, yv yvVar2) {
        if (yvVar == null || yvVar2 == null) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(yvVar.a() - yvVar2.a());
    }

    @Override // supwisdom.wv
    public xv a(sv svVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        xv[] xvVarArrA = a(svVar, map, false);
        if (xvVarArrA != null && xvVarArrA.length != 0 && xvVarArrA[0] != null) {
            return xvVarArrA[0];
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static xv[] a(sv svVar, Map<DecodeHintType, ?> map, boolean z) throws ChecksumException, FormatException, NotFoundException {
        ArrayList arrayList = new ArrayList();
        pz pzVarA = oz.a(svVar, map, z);
        for (yv[] yvVarArr : pzVarA.b()) {
            hw hwVarA = kz.a(pzVarA.a(), yvVarArr[4], yvVarArr[5], yvVarArr[6], yvVarArr[7], b(yvVarArr), a(yvVarArr));
            xv xvVar = new xv(hwVarA.g(), hwVarA.d(), yvVarArr, BarcodeFormat.PDF_417);
            xvVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, hwVarA.b());
            az azVar = (az) hwVarA.c();
            if (azVar != null) {
                xvVar.a(ResultMetadataType.PDF417_EXTRA_METADATA, azVar);
            }
            arrayList.add(xvVar);
        }
        return (xv[]) arrayList.toArray(new xv[arrayList.size()]);
    }
}
