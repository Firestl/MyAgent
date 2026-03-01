package org.bouncycastle.asn1.eac;

import com.tencent.connect.common.Constants;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes3.dex */
public interface EACObjectIdentifiers {
    public static final ASN1ObjectIdentifier bsi_de;
    public static final ASN1ObjectIdentifier id_CA;
    public static final ASN1ObjectIdentifier id_CA_DH;
    public static final ASN1ObjectIdentifier id_CA_DH_3DES_CBC_CBC;
    public static final ASN1ObjectIdentifier id_CA_ECDH;
    public static final ASN1ObjectIdentifier id_CA_ECDH_3DES_CBC_CBC;
    public static final ASN1ObjectIdentifier id_EAC_ePassport;
    public static final ASN1ObjectIdentifier id_PK;
    public static final ASN1ObjectIdentifier id_PK_DH;
    public static final ASN1ObjectIdentifier id_PK_ECDH;
    public static final ASN1ObjectIdentifier id_TA;
    public static final ASN1ObjectIdentifier id_TA_ECDSA;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_1;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_224;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_256;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_384;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_512;
    public static final ASN1ObjectIdentifier id_TA_RSA;
    public static final ASN1ObjectIdentifier id_TA_RSA_PSS_SHA_1;
    public static final ASN1ObjectIdentifier id_TA_RSA_PSS_SHA_256;
    public static final ASN1ObjectIdentifier id_TA_RSA_PSS_SHA_512;
    public static final ASN1ObjectIdentifier id_TA_RSA_v1_5_SHA_1;
    public static final ASN1ObjectIdentifier id_TA_RSA_v1_5_SHA_256;
    public static final ASN1ObjectIdentifier id_TA_RSA_v1_5_SHA_512;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("0.4.0.127.0.7");
        bsi_de = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = aSN1ObjectIdentifier.branch("2.2.1");
        id_PK = aSN1ObjectIdentifierBranch;
        id_PK_DH = aSN1ObjectIdentifierBranch.branch("1");
        id_PK_ECDH = id_PK.branch("2");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = bsi_de.branch("2.2.3");
        id_CA = aSN1ObjectIdentifierBranch2;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch3 = aSN1ObjectIdentifierBranch2.branch("1");
        id_CA_DH = aSN1ObjectIdentifierBranch3;
        id_CA_DH_3DES_CBC_CBC = aSN1ObjectIdentifierBranch3.branch("1");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch4 = id_CA.branch("2");
        id_CA_ECDH = aSN1ObjectIdentifierBranch4;
        id_CA_ECDH_3DES_CBC_CBC = aSN1ObjectIdentifierBranch4.branch("1");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch5 = bsi_de.branch("2.2.2");
        id_TA = aSN1ObjectIdentifierBranch5;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch6 = aSN1ObjectIdentifierBranch5.branch("1");
        id_TA_RSA = aSN1ObjectIdentifierBranch6;
        id_TA_RSA_v1_5_SHA_1 = aSN1ObjectIdentifierBranch6.branch("1");
        id_TA_RSA_v1_5_SHA_256 = id_TA_RSA.branch("2");
        id_TA_RSA_PSS_SHA_1 = id_TA_RSA.branch("3");
        id_TA_RSA_PSS_SHA_256 = id_TA_RSA.branch("4");
        id_TA_RSA_v1_5_SHA_512 = id_TA_RSA.branch("5");
        id_TA_RSA_PSS_SHA_512 = id_TA_RSA.branch(Constants.VIA_SHARE_TYPE_INFO);
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch7 = id_TA.branch("2");
        id_TA_ECDSA = aSN1ObjectIdentifierBranch7;
        id_TA_ECDSA_SHA_1 = aSN1ObjectIdentifierBranch7.branch("1");
        id_TA_ECDSA_SHA_224 = id_TA_ECDSA.branch("2");
        id_TA_ECDSA_SHA_256 = id_TA_ECDSA.branch("3");
        id_TA_ECDSA_SHA_384 = id_TA_ECDSA.branch("4");
        id_TA_ECDSA_SHA_512 = id_TA_ECDSA.branch("5");
        id_EAC_ePassport = bsi_de.branch("3.1.2.1");
    }
}
