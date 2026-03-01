package org.bouncycastle.asn1.edec;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes3.dex */
public interface EdECObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_Ed25519;
    public static final ASN1ObjectIdentifier id_Ed448;
    public static final ASN1ObjectIdentifier id_X25519;
    public static final ASN1ObjectIdentifier id_X448;
    public static final ASN1ObjectIdentifier id_edwards_curve_algs;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.101");
        id_edwards_curve_algs = aSN1ObjectIdentifier;
        id_X25519 = aSN1ObjectIdentifier.branch("110").intern();
        id_X448 = id_edwards_curve_algs.branch("111").intern();
        id_Ed25519 = id_edwards_curve_algs.branch("112").intern();
        id_Ed448 = id_edwards_curve_algs.branch("113").intern();
    }
}
