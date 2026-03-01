package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ReaderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class vv implements wv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<DecodeHintType, ?> f9551a;
    public wv[] b;

    public final xv a(sv svVar) throws NotFoundException {
        wv[] wvVarArr = this.b;
        if (wvVarArr != null) {
            for (wv wvVar : wvVarArr) {
                try {
                    return wvVar.a(svVar, this.f9551a);
                } catch (ReaderException unused) {
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public xv b(sv svVar) throws NotFoundException {
        if (this.b == null) {
            a((Map<DecodeHintType, ?>) null);
        }
        return a(svVar);
    }

    @Override // supwisdom.wv
    public void reset() {
        wv[] wvVarArr = this.b;
        if (wvVarArr != null) {
            for (wv wvVar : wvVarArr) {
                wvVar.reset();
            }
        }
    }

    public void a(Map<DecodeHintType, ?> map) {
        this.f9551a = map;
        boolean z = true;
        boolean z2 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (!collection.contains(BarcodeFormat.UPC_A) && !collection.contains(BarcodeFormat.UPC_E) && !collection.contains(BarcodeFormat.EAN_13) && !collection.contains(BarcodeFormat.EAN_8) && !collection.contains(BarcodeFormat.CODABAR) && !collection.contains(BarcodeFormat.CODE_39) && !collection.contains(BarcodeFormat.CODE_93) && !collection.contains(BarcodeFormat.CODE_128) && !collection.contains(BarcodeFormat.ITF) && !collection.contains(BarcodeFormat.RSS_14) && !collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                z = false;
            }
            if (z && !z2) {
                arrayList.add(new mx(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new qz());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new tw());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new bw());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new zy());
            }
            if (collection.contains(BarcodeFormat.MAXICODE)) {
                arrayList.add(new ax());
            }
            if (z && z2) {
                arrayList.add(new mx(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z2) {
                arrayList.add(new mx(map));
            }
            arrayList.add(new qz());
            arrayList.add(new tw());
            arrayList.add(new bw());
            arrayList.add(new zy());
            arrayList.add(new ax());
            if (z2) {
                arrayList.add(new mx(map));
            }
        }
        this.b = (wv[]) arrayList.toArray(new wv[arrayList.size()]);
    }

    @Override // supwisdom.wv
    public xv a(sv svVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        a(map);
        return a(svVar);
    }
}
