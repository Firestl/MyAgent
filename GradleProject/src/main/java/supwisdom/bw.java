package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class bw implements wv {
    @Override // supwisdom.wv
    public xv a(sv svVar, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        yv[] yvVarArrB;
        yv[] yvVarArrB2;
        FormatException formatException;
        zv zvVar;
        dw dwVar = new dw(svVar.a());
        hw hwVarA = null;
        try {
            aw awVarA = dwVar.a(false);
            yvVarArrB = awVarA.b();
            try {
                yvVarArrB2 = yvVarArrB;
                formatException = null;
                hwVarA = new cw().a(awVarA);
                e = null;
            } catch (FormatException e2) {
                e = e2;
                yvVarArrB2 = yvVarArrB;
                formatException = e;
                e = null;
            } catch (NotFoundException e3) {
                e = e3;
                yvVarArrB2 = yvVarArrB;
                formatException = null;
            }
        } catch (FormatException e4) {
            e = e4;
            yvVarArrB = null;
        } catch (NotFoundException e5) {
            e = e5;
            yvVarArrB = null;
        }
        if (hwVarA == null) {
            try {
                aw awVarA2 = dwVar.a(true);
                yvVarArrB2 = awVarA2.b();
                hwVarA = new cw().a(awVarA2);
            } catch (FormatException | NotFoundException e6) {
                if (e != null) {
                    throw e;
                }
                if (formatException != null) {
                    throw formatException;
                }
                throw e6;
            }
        }
        if (map != null && (zvVar = (zv) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
            for (yv yvVar : yvVarArrB2) {
                zvVar.foundPossibleResultPoint(yvVar);
            }
        }
        xv xvVar = new xv(hwVarA.g(), hwVarA.d(), yvVarArrB2, BarcodeFormat.AZTEC);
        List<byte[]> listA = hwVarA.a();
        if (listA != null) {
            xvVar.a(ResultMetadataType.BYTE_SEGMENTS, listA);
        }
        String strB = hwVarA.b();
        if (strB != null) {
            xvVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, strB);
        }
        return xvVar;
    }

    @Override // supwisdom.wv
    public void reset() {
    }
}
