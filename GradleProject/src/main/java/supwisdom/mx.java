package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ReaderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class mx extends ox {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ox[] f8448a;

    public mx(Map<DecodeHintType, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        boolean z = (map == null || map.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) == null) ? false : true;
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new nx(map));
            }
            if (collection.contains(BarcodeFormat.CODE_39)) {
                arrayList.add(new gx(z));
            }
            if (collection.contains(BarcodeFormat.CODE_93)) {
                arrayList.add(new hx());
            }
            if (collection.contains(BarcodeFormat.CODE_128)) {
                arrayList.add(new fx());
            }
            if (collection.contains(BarcodeFormat.ITF)) {
                arrayList.add(new lx());
            }
            if (collection.contains(BarcodeFormat.CODABAR)) {
                arrayList.add(new ex());
            }
            if (collection.contains(BarcodeFormat.RSS_14)) {
                arrayList.add(new zx());
            }
            if (collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                arrayList.add(new ey());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new nx(map));
            arrayList.add(new gx());
            arrayList.add(new ex());
            arrayList.add(new hx());
            arrayList.add(new fx());
            arrayList.add(new lx());
            arrayList.add(new zx());
            arrayList.add(new ey());
        }
        this.f8448a = (ox[]) arrayList.toArray(new ox[arrayList.size()]);
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        for (ox oxVar : this.f8448a) {
            try {
                return oxVar.a(i, ewVar, map);
            } catch (ReaderException unused) {
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.ox, supwisdom.wv
    public void reset() {
        for (ox oxVar : this.f8448a) {
            oxVar.reset();
        }
    }
}
