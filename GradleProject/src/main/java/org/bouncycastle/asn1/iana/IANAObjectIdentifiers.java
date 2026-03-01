package org.bouncycastle.asn1.iana;

import com.tencent.connect.common.Constants;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes3.dex */
public interface IANAObjectIdentifiers {
    public static final ASN1ObjectIdentifier SNMPv2;
    public static final ASN1ObjectIdentifier _private;
    public static final ASN1ObjectIdentifier directory;
    public static final ASN1ObjectIdentifier experimental;
    public static final ASN1ObjectIdentifier hmacMD5;
    public static final ASN1ObjectIdentifier hmacRIPEMD160;
    public static final ASN1ObjectIdentifier hmacSHA1;
    public static final ASN1ObjectIdentifier hmacTIGER;
    public static final ASN1ObjectIdentifier internet;
    public static final ASN1ObjectIdentifier ipsec;
    public static final ASN1ObjectIdentifier isakmpOakley;
    public static final ASN1ObjectIdentifier mail;
    public static final ASN1ObjectIdentifier mgmt;
    public static final ASN1ObjectIdentifier pkix;
    public static final ASN1ObjectIdentifier security;
    public static final ASN1ObjectIdentifier security_mechanisms;
    public static final ASN1ObjectIdentifier security_nametypes;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1");
        internet = aSN1ObjectIdentifier;
        directory = aSN1ObjectIdentifier.branch("1");
        mgmt = internet.branch("2");
        experimental = internet.branch("3");
        _private = internet.branch("4");
        security = internet.branch("5");
        SNMPv2 = internet.branch(Constants.VIA_SHARE_TYPE_INFO);
        mail = internet.branch("7");
        security_mechanisms = security.branch("5");
        security_nametypes = security.branch(Constants.VIA_SHARE_TYPE_INFO);
        pkix = security_mechanisms.branch(Constants.VIA_SHARE_TYPE_INFO);
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = security_mechanisms.branch(Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
        ipsec = aSN1ObjectIdentifierBranch;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = aSN1ObjectIdentifierBranch.branch("1");
        isakmpOakley = aSN1ObjectIdentifierBranch2;
        hmacMD5 = aSN1ObjectIdentifierBranch2.branch("1");
        hmacSHA1 = isakmpOakley.branch("2");
        hmacTIGER = isakmpOakley.branch("3");
        hmacRIPEMD160 = isakmpOakley.branch("4");
    }
}
