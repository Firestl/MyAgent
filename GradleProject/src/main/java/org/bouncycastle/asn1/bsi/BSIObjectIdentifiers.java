package org.bouncycastle.asn1.bsi;

import com.tencent.connect.common.Constants;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes3.dex */
public interface BSIObjectIdentifiers {
    public static final ASN1ObjectIdentifier algorithm;
    public static final ASN1ObjectIdentifier bsi_de;
    public static final ASN1ObjectIdentifier ecdsa_plain_RIPEMD160;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA1;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA224;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA256;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA384;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA512;
    public static final ASN1ObjectIdentifier ecdsa_plain_signatures;
    public static final ASN1ObjectIdentifier ecka_eg;
    public static final ASN1ObjectIdentifier ecka_eg_SessionKDF;
    public static final ASN1ObjectIdentifier ecka_eg_SessionKDF_3DES;
    public static final ASN1ObjectIdentifier ecka_eg_SessionKDF_AES128;
    public static final ASN1ObjectIdentifier ecka_eg_SessionKDF_AES192;
    public static final ASN1ObjectIdentifier ecka_eg_SessionKDF_AES256;
    public static final ASN1ObjectIdentifier ecka_eg_X963kdf;
    public static final ASN1ObjectIdentifier ecka_eg_X963kdf_RIPEMD160;
    public static final ASN1ObjectIdentifier ecka_eg_X963kdf_SHA1;
    public static final ASN1ObjectIdentifier ecka_eg_X963kdf_SHA224;
    public static final ASN1ObjectIdentifier ecka_eg_X963kdf_SHA256;
    public static final ASN1ObjectIdentifier ecka_eg_X963kdf_SHA384;
    public static final ASN1ObjectIdentifier ecka_eg_X963kdf_SHA512;
    public static final ASN1ObjectIdentifier id_ecc;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("0.4.0.127.0.7");
        bsi_de = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = aSN1ObjectIdentifier.branch("1.1");
        id_ecc = aSN1ObjectIdentifierBranch;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = aSN1ObjectIdentifierBranch.branch("4.1");
        ecdsa_plain_signatures = aSN1ObjectIdentifierBranch2;
        ecdsa_plain_SHA1 = aSN1ObjectIdentifierBranch2.branch("1");
        ecdsa_plain_SHA224 = ecdsa_plain_signatures.branch("2");
        ecdsa_plain_SHA256 = ecdsa_plain_signatures.branch("3");
        ecdsa_plain_SHA384 = ecdsa_plain_signatures.branch("4");
        ecdsa_plain_SHA512 = ecdsa_plain_signatures.branch("5");
        ecdsa_plain_RIPEMD160 = ecdsa_plain_signatures.branch(Constants.VIA_SHARE_TYPE_INFO);
        algorithm = bsi_de.branch("1");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch3 = id_ecc.branch("5.1");
        ecka_eg = aSN1ObjectIdentifierBranch3;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch4 = aSN1ObjectIdentifierBranch3.branch("1");
        ecka_eg_X963kdf = aSN1ObjectIdentifierBranch4;
        ecka_eg_X963kdf_SHA1 = aSN1ObjectIdentifierBranch4.branch("1");
        ecka_eg_X963kdf_SHA224 = ecka_eg_X963kdf.branch("2");
        ecka_eg_X963kdf_SHA256 = ecka_eg_X963kdf.branch("3");
        ecka_eg_X963kdf_SHA384 = ecka_eg_X963kdf.branch("4");
        ecka_eg_X963kdf_SHA512 = ecka_eg_X963kdf.branch("5");
        ecka_eg_X963kdf_RIPEMD160 = ecka_eg_X963kdf.branch(Constants.VIA_SHARE_TYPE_INFO);
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch5 = ecka_eg.branch("2");
        ecka_eg_SessionKDF = aSN1ObjectIdentifierBranch5;
        ecka_eg_SessionKDF_3DES = aSN1ObjectIdentifierBranch5.branch("1");
        ecka_eg_SessionKDF_AES128 = ecka_eg_SessionKDF.branch("2");
        ecka_eg_SessionKDF_AES192 = ecka_eg_SessionKDF.branch("3");
        ecka_eg_SessionKDF_AES256 = ecka_eg_SessionKDF.branch("4");
    }
}
