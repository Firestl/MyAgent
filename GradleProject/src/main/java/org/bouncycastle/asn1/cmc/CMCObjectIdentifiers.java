package org.bouncycastle.asn1.cmc;

import com.tencent.connect.common.Constants;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes3.dex */
public interface CMCObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_cct;
    public static final ASN1ObjectIdentifier id_cct_PKIData;
    public static final ASN1ObjectIdentifier id_cct_PKIResponse;
    public static final ASN1ObjectIdentifier id_cmc;
    public static final ASN1ObjectIdentifier id_cmc_addExtensions;
    public static final ASN1ObjectIdentifier id_cmc_authData;
    public static final ASN1ObjectIdentifier id_cmc_batchRequests;
    public static final ASN1ObjectIdentifier id_cmc_batchResponses;
    public static final ASN1ObjectIdentifier id_cmc_confirmCertAcceptance;
    public static final ASN1ObjectIdentifier id_cmc_controlProcessed;
    public static final ASN1ObjectIdentifier id_cmc_dataReturn;
    public static final ASN1ObjectIdentifier id_cmc_decryptedPOP;
    public static final ASN1ObjectIdentifier id_cmc_encryptedPOP;
    public static final ASN1ObjectIdentifier id_cmc_getCRL;
    public static final ASN1ObjectIdentifier id_cmc_getCert;
    public static final ASN1ObjectIdentifier id_cmc_identification;
    public static final ASN1ObjectIdentifier id_cmc_identityProof;
    public static final ASN1ObjectIdentifier id_cmc_identityProofV2;
    public static final ASN1ObjectIdentifier id_cmc_lraPOPWitness;
    public static final ASN1ObjectIdentifier id_cmc_modCertTemplate;
    public static final ASN1ObjectIdentifier id_cmc_popLinkRandom;
    public static final ASN1ObjectIdentifier id_cmc_popLinkWitness;
    public static final ASN1ObjectIdentifier id_cmc_popLinkWitnessV2;
    public static final ASN1ObjectIdentifier id_cmc_publishCert;
    public static final ASN1ObjectIdentifier id_cmc_queryPending;
    public static final ASN1ObjectIdentifier id_cmc_recipientNonce;
    public static final ASN1ObjectIdentifier id_cmc_regInfo;
    public static final ASN1ObjectIdentifier id_cmc_responseInfo;
    public static final ASN1ObjectIdentifier id_cmc_revokeRequest;
    public static final ASN1ObjectIdentifier id_cmc_senderNonce;
    public static final ASN1ObjectIdentifier id_cmc_statusInfo;
    public static final ASN1ObjectIdentifier id_cmc_statusInfoV2;
    public static final ASN1ObjectIdentifier id_cmc_transactionId;
    public static final ASN1ObjectIdentifier id_cmc_trustedAnchors;
    public static final ASN1ObjectIdentifier id_pkix;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
        id_pkix = aSN1ObjectIdentifier;
        id_cmc = aSN1ObjectIdentifier.branch("7");
        id_cct = id_pkix.branch(Constants.VIA_REPORT_TYPE_SET_AVATAR);
        id_cmc_identityProof = id_cmc.branch("3");
        id_cmc_dataReturn = id_cmc.branch("4");
        id_cmc_regInfo = id_cmc.branch("18");
        id_cmc_responseInfo = id_cmc.branch(Constants.VIA_ACT_TYPE_NINETEEN);
        id_cmc_queryPending = id_cmc.branch("21");
        id_cmc_popLinkRandom = id_cmc.branch(Constants.VIA_REPORT_TYPE_DATALINE);
        id_cmc_popLinkWitness = id_cmc.branch(Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR);
        id_cmc_identification = id_cmc.branch("2");
        id_cmc_transactionId = id_cmc.branch("5");
        id_cmc_senderNonce = id_cmc.branch(Constants.VIA_SHARE_TYPE_INFO);
        id_cmc_recipientNonce = id_cmc.branch("7");
        id_cct_PKIData = id_cct.branch("2");
        id_cct_PKIResponse = id_cct.branch("3");
        id_cmc_statusInfo = id_cmc.branch("1");
        id_cmc_addExtensions = id_cmc.branch(Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
        id_cmc_encryptedPOP = id_cmc.branch(Constants.VIA_SHARE_TYPE_MINI_PROGRAM);
        id_cmc_decryptedPOP = id_cmc.branch(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        id_cmc_lraPOPWitness = id_cmc.branch(Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
        id_cmc_getCert = id_cmc.branch(Constants.VIA_REPORT_TYPE_WPA_STATE);
        id_cmc_getCRL = id_cmc.branch(Constants.VIA_REPORT_TYPE_START_WAP);
        id_cmc_revokeRequest = id_cmc.branch(Constants.VIA_REPORT_TYPE_START_GROUP);
        id_cmc_confirmCertAcceptance = id_cmc.branch(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        id_cmc_statusInfoV2 = id_cmc.branch(Constants.VIA_REPORT_TYPE_CHAT_AUDIO);
        id_cmc_trustedAnchors = id_cmc.branch(Constants.VIA_REPORT_TYPE_CHAT_VIDEO);
        id_cmc_authData = id_cmc.branch("27");
        id_cmc_batchRequests = id_cmc.branch(Constants.VIA_ACT_TYPE_TWENTY_EIGHT);
        id_cmc_batchResponses = id_cmc.branch("29");
        id_cmc_publishCert = id_cmc.branch("30");
        id_cmc_modCertTemplate = id_cmc.branch("31");
        id_cmc_controlProcessed = id_cmc.branch("32");
        id_cmc_identityProofV2 = id_cmc.branch("34");
        id_cmc_popLinkWitnessV2 = id_cmc.branch("33");
    }
}
