package org.bouncycastle.asn1.crmf;

import com.tencent.connect.common.Constants;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

/* JADX INFO: loaded from: classes3.dex */
public interface CRMFObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_alg;
    public static final ASN1ObjectIdentifier id_alg_dh_pop;
    public static final ASN1ObjectIdentifier id_ct_encKeyWithID;
    public static final ASN1ObjectIdentifier id_dh_sig_hmac_sha1;
    public static final ASN1ObjectIdentifier id_pkip;
    public static final ASN1ObjectIdentifier id_pkix;
    public static final ASN1ObjectIdentifier id_regCtrl;
    public static final ASN1ObjectIdentifier id_regCtrl_authenticator;
    public static final ASN1ObjectIdentifier id_regCtrl_pkiArchiveOptions;
    public static final ASN1ObjectIdentifier id_regCtrl_pkiPublicationInfo;
    public static final ASN1ObjectIdentifier id_regCtrl_regToken;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
        id_pkix = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = aSN1ObjectIdentifier.branch("5");
        id_pkip = aSN1ObjectIdentifierBranch;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = aSN1ObjectIdentifierBranch.branch("1");
        id_regCtrl = aSN1ObjectIdentifierBranch2;
        id_regCtrl_regToken = aSN1ObjectIdentifierBranch2.branch("1");
        id_regCtrl_authenticator = id_regCtrl.branch("2");
        id_regCtrl_pkiPublicationInfo = id_regCtrl.branch("3");
        id_regCtrl_pkiArchiveOptions = id_regCtrl.branch("4");
        id_ct_encKeyWithID = PKCSObjectIdentifiers.id_ct.branch("21");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch3 = id_pkix.branch(Constants.VIA_SHARE_TYPE_INFO);
        id_alg = aSN1ObjectIdentifierBranch3;
        id_dh_sig_hmac_sha1 = aSN1ObjectIdentifierBranch3.branch("3");
        id_alg_dh_pop = id_alg.branch("4");
    }
}
