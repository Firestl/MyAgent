package org.bouncycastle.asn1.x509;

import com.tencent.connect.common.Constants;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;

/* JADX INFO: loaded from: classes3.dex */
public class KeyPurposeId extends ASN1Object {
    public ASN1ObjectIdentifier id;
    public static final ASN1ObjectIdentifier id_kp = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3");
    public static final KeyPurposeId anyExtendedKeyUsage = new KeyPurposeId(Extension.extendedKeyUsage.branch("0"));
    public static final KeyPurposeId id_kp_serverAuth = new KeyPurposeId(id_kp.branch("1"));
    public static final KeyPurposeId id_kp_clientAuth = new KeyPurposeId(id_kp.branch("2"));
    public static final KeyPurposeId id_kp_codeSigning = new KeyPurposeId(id_kp.branch("3"));
    public static final KeyPurposeId id_kp_emailProtection = new KeyPurposeId(id_kp.branch("4"));
    public static final KeyPurposeId id_kp_ipsecEndSystem = new KeyPurposeId(id_kp.branch("5"));
    public static final KeyPurposeId id_kp_ipsecTunnel = new KeyPurposeId(id_kp.branch(Constants.VIA_SHARE_TYPE_INFO));
    public static final KeyPurposeId id_kp_ipsecUser = new KeyPurposeId(id_kp.branch("7"));
    public static final KeyPurposeId id_kp_timeStamping = new KeyPurposeId(id_kp.branch(Constants.VIA_SHARE_TYPE_PUBLISHVIDEO));
    public static final KeyPurposeId id_kp_OCSPSigning = new KeyPurposeId(id_kp.branch(Constants.VIA_SHARE_TYPE_MINI_PROGRAM));
    public static final KeyPurposeId id_kp_dvcs = new KeyPurposeId(id_kp.branch(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ));
    public static final KeyPurposeId id_kp_sbgpCertAAServerAuth = new KeyPurposeId(id_kp.branch(Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE));
    public static final KeyPurposeId id_kp_scvp_responder = new KeyPurposeId(id_kp.branch(Constants.VIA_REPORT_TYPE_SET_AVATAR));
    public static final KeyPurposeId id_kp_eapOverPPP = new KeyPurposeId(id_kp.branch("13"));
    public static final KeyPurposeId id_kp_eapOverLAN = new KeyPurposeId(id_kp.branch(Constants.VIA_REPORT_TYPE_MAKE_FRIEND));
    public static final KeyPurposeId id_kp_scvpServer = new KeyPurposeId(id_kp.branch(Constants.VIA_REPORT_TYPE_WPA_STATE));
    public static final KeyPurposeId id_kp_scvpClient = new KeyPurposeId(id_kp.branch(Constants.VIA_REPORT_TYPE_START_WAP));
    public static final KeyPurposeId id_kp_ipsecIKE = new KeyPurposeId(id_kp.branch(Constants.VIA_REPORT_TYPE_START_GROUP));
    public static final KeyPurposeId id_kp_capwapAC = new KeyPurposeId(id_kp.branch("18"));
    public static final KeyPurposeId id_kp_capwapWTP = new KeyPurposeId(id_kp.branch(Constants.VIA_ACT_TYPE_NINETEEN));
    public static final KeyPurposeId id_kp_smartcardlogon = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.20.2.2"));
    public static final KeyPurposeId id_kp_macAddress = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.1.1.1.22"));
    public static final KeyPurposeId id_kp_msSGC = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.10.3.3"));
    public static final KeyPurposeId id_kp_nsSGC = new KeyPurposeId(new ASN1ObjectIdentifier("2.16.840.1.113730.4.1"));

    public KeyPurposeId(String str) {
        this(new ASN1ObjectIdentifier(str));
    }

    public KeyPurposeId(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.id = aSN1ObjectIdentifier;
    }

    public static KeyPurposeId getInstance(Object obj) {
        if (obj instanceof KeyPurposeId) {
            return (KeyPurposeId) obj;
        }
        if (obj != null) {
            return new KeyPurposeId(ASN1ObjectIdentifier.getInstance(obj));
        }
        return null;
    }

    public String getId() {
        return this.id.getId();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.id;
    }

    public ASN1ObjectIdentifier toOID() {
        return this.id;
    }

    public String toString() {
        return this.id.toString();
    }
}
