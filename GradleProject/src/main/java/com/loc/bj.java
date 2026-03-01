package com.loc;

import com.taobao.weex.el.parse.Operators;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* JADX INFO: compiled from: StatisticsPubDataStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bj extends bk {
    public bj() {
    }

    public bj(bk bkVar) {
        super(bkVar);
    }

    @Override // com.loc.bk
    public final byte[] a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
        stringBuffer.append(Operators.SPACE_STR);
        stringBuffer.append(UUID.randomUUID().toString());
        stringBuffer.append(Operators.SPACE_STR);
        if (stringBuffer.length() != 53) {
            return new byte[0];
        }
        byte[] bArrA = u.a(stringBuffer.toString());
        byte[] bArr2 = new byte[bArrA.length + bArr.length];
        System.arraycopy(bArrA, 0, bArr2, 0, bArrA.length);
        System.arraycopy(bArr, 0, bArr2, bArrA.length, bArr.length);
        return bArr2;
    }
}
