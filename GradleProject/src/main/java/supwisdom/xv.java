package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class xv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9814a;
    public final byte[] b;
    public yv[] c;
    public final BarcodeFormat d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<ResultMetadataType, Object> f9815e;
    public String f;

    public xv(String str, byte[] bArr, yv[] yvVarArr, BarcodeFormat barcodeFormat) {
        this(str, bArr, yvVarArr, barcodeFormat, System.currentTimeMillis());
    }

    public void a(yv[] yvVarArr) {
        yv[] yvVarArr2 = this.c;
        if (yvVarArr2 == null) {
            this.c = yvVarArr;
            return;
        }
        if (yvVarArr == null || yvVarArr.length <= 0) {
            return;
        }
        yv[] yvVarArr3 = new yv[yvVarArr2.length + yvVarArr.length];
        System.arraycopy(yvVarArr2, 0, yvVarArr3, 0, yvVarArr2.length);
        System.arraycopy(yvVarArr, 0, yvVarArr3, yvVarArr2.length, yvVarArr.length);
        this.c = yvVarArr3;
    }

    public byte[] b() {
        return this.b;
    }

    public Map<ResultMetadataType, Object> c() {
        return this.f9815e;
    }

    public yv[] d() {
        return this.c;
    }

    public String e() {
        return this.f9814a;
    }

    public String toString() {
        return this.f9814a;
    }

    public xv(String str, byte[] bArr, yv[] yvVarArr, BarcodeFormat barcodeFormat, long j) {
        this.f9814a = str;
        this.b = bArr;
        this.c = yvVarArr;
        this.d = barcodeFormat;
        this.f9815e = null;
    }

    public BarcodeFormat a() {
        return this.d;
    }

    public void a(Map<ResultMetadataType, Object> map) {
        if (map != null) {
            Map<ResultMetadataType, Object> map2 = this.f9815e;
            if (map2 == null) {
                this.f9815e = map;
            } else {
                map2.putAll(map);
            }
        }
    }

    public void a(ResultMetadataType resultMetadataType, Object obj) {
        if (this.f9815e == null) {
            this.f9815e = new EnumMap(ResultMetadataType.class);
        }
        this.f9815e.put(resultMetadataType, obj);
    }
}
