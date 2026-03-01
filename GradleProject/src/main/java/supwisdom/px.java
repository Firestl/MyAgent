package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class px extends tx {
    public final tx h = new ix();

    @Override // supwisdom.tx
    public int a(ew ewVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        return this.h.a(ewVar, iArr, sb);
    }

    @Override // supwisdom.tx
    public xv a(int i, ew ewVar, int[] iArr, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        return a(this.h.a(i, ewVar, iArr, map));
    }

    @Override // supwisdom.tx
    public BarcodeFormat a() {
        return BarcodeFormat.UPC_A;
    }

    public static xv a(xv xvVar) throws FormatException {
        String strE = xvVar.e();
        if (strE.charAt(0) == '0') {
            return new xv(strE.substring(1), null, xvVar.d(), BarcodeFormat.UPC_A);
        }
        throw FormatException.getFormatInstance();
    }

    @Override // supwisdom.ox, supwisdom.wv
    public xv a(sv svVar, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        return a(this.h.a(svVar, map));
    }

    @Override // supwisdom.tx, supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        return a(this.h.a(i, ewVar, map));
    }
}
