package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ReaderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class nx extends ox {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final tx[] f8585a;

    public nx(Map<DecodeHintType, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new ix());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new px());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new jx());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new ux());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new ix());
            arrayList.add(new jx());
            arrayList.add(new ux());
        }
        this.f8585a = (tx[]) arrayList.toArray(new tx[arrayList.size()]);
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        int[] iArrA = tx.a(ewVar);
        for (tx txVar : this.f8585a) {
            try {
                xv xvVarA = txVar.a(i, ewVar, iArrA, map);
                boolean z = xvVarA.a() == BarcodeFormat.EAN_13 && xvVarA.e().charAt(0) == '0';
                Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                boolean z2 = collection == null || collection.contains(BarcodeFormat.UPC_A);
                if (!z || !z2) {
                    return xvVarA;
                }
                xv xvVar = new xv(xvVarA.e().substring(1), xvVarA.b(), xvVarA.d(), BarcodeFormat.UPC_A);
                xvVar.a(xvVarA.c());
                return xvVar;
            } catch (ReaderException unused) {
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.ox, supwisdom.wv
    public void reset() {
        for (tx txVar : this.f8585a) {
            txVar.reset();
        }
    }
}
