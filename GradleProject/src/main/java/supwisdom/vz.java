package supwisdom;

import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.common.reedsolomon.ReedSolomonException;
import com.dcloud.zxing2.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class vz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final sw f9567a = new sw(qw.l);

    public final void a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f9567a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    public hw a(fw fwVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        ChecksumException e2;
        rz rzVar = new rz(fwVar);
        FormatException formatException = null;
        try {
            return a(rzVar, map);
        } catch (ChecksumException e3) {
            e2 = e3;
            try {
                rzVar.e();
                rzVar.a(true);
                rzVar.d();
                rzVar.c();
                rzVar.a();
                hw hwVarA = a(rzVar, map);
                hwVarA.a(new xz(true));
                return hwVarA;
            } catch (ChecksumException | FormatException e4) {
                if (formatException != null) {
                    throw formatException;
                }
                if (e2 != null) {
                    throw e2;
                }
                throw e4;
            }
        } catch (FormatException e5) {
            e2 = null;
            formatException = e5;
            rzVar.e();
            rzVar.a(true);
            rzVar.d();
            rzVar.c();
            rzVar.a();
            hw hwVarA2 = a(rzVar, map);
            hwVarA2.a(new xz(true));
            return hwVarA2;
        }
    }

    public final hw a(rz rzVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        yz yzVarD = rzVar.d();
        ErrorCorrectionLevel errorCorrectionLevelB = rzVar.c().b();
        sz[] szVarArrA = sz.a(rzVar.b(), yzVarD, errorCorrectionLevelB);
        int iB = 0;
        for (sz szVar : szVarArrA) {
            iB += szVar.b();
        }
        byte[] bArr = new byte[iB];
        int i = 0;
        for (sz szVar2 : szVarArrA) {
            byte[] bArrA = szVar2.a();
            int iB2 = szVar2.b();
            a(bArrA, iB2);
            int i2 = 0;
            while (i2 < iB2) {
                bArr[i] = bArrA[i2];
                i2++;
                i++;
            }
        }
        return uz.a(bArr, yzVarD, errorCorrectionLevelB, map);
    }
}
