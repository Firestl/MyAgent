package org.bouncycastle.asn1.isismtt;

import com.tencent.connect.common.Constants;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes3.dex */
public interface ISISMTTObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_isismtt;
    public static final ASN1ObjectIdentifier id_isismtt_at;
    public static final ASN1ObjectIdentifier id_isismtt_at_PKReference;
    public static final ASN1ObjectIdentifier id_isismtt_at_additionalInformation;
    public static final ASN1ObjectIdentifier id_isismtt_at_admission;
    public static final ASN1ObjectIdentifier id_isismtt_at_certHash;
    public static final ASN1ObjectIdentifier id_isismtt_at_certInDirSince;
    public static final ASN1ObjectIdentifier id_isismtt_at_dateOfCertGen;
    public static final ASN1ObjectIdentifier id_isismtt_at_declarationOfMajority;
    public static final ASN1ObjectIdentifier id_isismtt_at_iCCSN;
    public static final ASN1ObjectIdentifier id_isismtt_at_liabilityLimitationFlag;
    public static final ASN1ObjectIdentifier id_isismtt_at_monetaryLimit;
    public static final ASN1ObjectIdentifier id_isismtt_at_nameAtBirth;
    public static final ASN1ObjectIdentifier id_isismtt_at_namingAuthorities;
    public static final ASN1ObjectIdentifier id_isismtt_at_procuration;
    public static final ASN1ObjectIdentifier id_isismtt_at_requestedCertificate;
    public static final ASN1ObjectIdentifier id_isismtt_at_restriction;
    public static final ASN1ObjectIdentifier id_isismtt_at_retrieveIfAllowed;
    public static final ASN1ObjectIdentifier id_isismtt_cp;
    public static final ASN1ObjectIdentifier id_isismtt_cp_accredited;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.36.8");
        id_isismtt = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = aSN1ObjectIdentifier.branch("1");
        id_isismtt_cp = aSN1ObjectIdentifierBranch;
        id_isismtt_cp_accredited = aSN1ObjectIdentifierBranch.branch("1");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = id_isismtt.branch("3");
        id_isismtt_at = aSN1ObjectIdentifierBranch2;
        id_isismtt_at_dateOfCertGen = aSN1ObjectIdentifierBranch2.branch("1");
        id_isismtt_at_procuration = id_isismtt_at.branch("2");
        id_isismtt_at_admission = id_isismtt_at.branch("3");
        id_isismtt_at_monetaryLimit = id_isismtt_at.branch("4");
        id_isismtt_at_declarationOfMajority = id_isismtt_at.branch("5");
        id_isismtt_at_iCCSN = id_isismtt_at.branch(Constants.VIA_SHARE_TYPE_INFO);
        id_isismtt_at_PKReference = id_isismtt_at.branch("7");
        id_isismtt_at_restriction = id_isismtt_at.branch(Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
        id_isismtt_at_retrieveIfAllowed = id_isismtt_at.branch(Constants.VIA_SHARE_TYPE_MINI_PROGRAM);
        id_isismtt_at_requestedCertificate = id_isismtt_at.branch(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        id_isismtt_at_namingAuthorities = id_isismtt_at.branch(Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
        id_isismtt_at_certInDirSince = id_isismtt_at.branch(Constants.VIA_REPORT_TYPE_SET_AVATAR);
        id_isismtt_at_certHash = id_isismtt_at.branch("13");
        id_isismtt_at_nameAtBirth = id_isismtt_at.branch(Constants.VIA_REPORT_TYPE_MAKE_FRIEND);
        id_isismtt_at_additionalInformation = id_isismtt_at.branch(Constants.VIA_REPORT_TYPE_WPA_STATE);
        id_isismtt_at_liabilityLimitationFlag = new ASN1ObjectIdentifier("0.2.262.1.10.12.0");
    }
}
