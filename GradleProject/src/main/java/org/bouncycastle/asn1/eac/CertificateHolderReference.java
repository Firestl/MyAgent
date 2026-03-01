package org.bouncycastle.asn1.eac;

import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes3.dex */
public class CertificateHolderReference {
    public static final String ReferenceEncoding = "ISO-8859-1";
    public String countryCode;
    public String holderMnemonic;
    public String sequenceNumber;

    public CertificateHolderReference(String str, String str2, String str3) {
        this.countryCode = str;
        this.holderMnemonic = str2;
        this.sequenceNumber = str3;
    }

    public CertificateHolderReference(byte[] bArr) {
        try {
            String str = new String(bArr, "ISO-8859-1");
            this.countryCode = str.substring(0, 2);
            this.holderMnemonic = str.substring(2, str.length() - 5);
            this.sequenceNumber = str.substring(str.length() - 5);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalStateException(e2.toString());
        }
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public byte[] getEncoded() {
        try {
            return (this.countryCode + this.holderMnemonic + this.sequenceNumber).getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalStateException(e2.toString());
        }
    }

    public String getHolderMnemonic() {
        return this.holderMnemonic;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }
}
