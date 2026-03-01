package org.bouncycastle.asn1;

import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class DERUTCTime extends ASN1UTCTime {
    public DERUTCTime(String str) {
        super(str);
    }

    public DERUTCTime(Date date) {
        super(date);
    }

    public DERUTCTime(byte[] bArr) {
        super(bArr);
    }
}
