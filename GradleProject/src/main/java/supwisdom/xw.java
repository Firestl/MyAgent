package supwisdom;

import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.common.reedsolomon.ReedSolomonException;

/* JADX INFO: loaded from: classes.dex */
public final class xw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final sw f9818a = new sw(qw.m);

    public final void a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f9818a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    public hw a(fw fwVar) throws ChecksumException, FormatException {
        uw uwVar = new uw(fwVar);
        vw[] vwVarArrA = vw.a(uwVar.b(), uwVar.a());
        int length = vwVarArrA.length;
        int iB = 0;
        for (vw vwVar : vwVarArrA) {
            iB += vwVar.b();
        }
        byte[] bArr = new byte[iB];
        for (int i = 0; i < length; i++) {
            vw vwVar2 = vwVarArrA[i];
            byte[] bArrA = vwVar2.a();
            int iB2 = vwVar2.b();
            a(bArrA, iB2);
            for (int i2 = 0; i2 < iB2; i2++) {
                bArr[(i2 * length) + i] = bArrA[i2];
            }
        }
        return ww.a(bArr);
    }
}
