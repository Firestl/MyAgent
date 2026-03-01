package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Properties;

/* JADX INFO: loaded from: classes3.dex */
public class TBSCertificate extends ASN1Object {
    public Time endDate;
    public Extensions extensions;
    public X500Name issuer;
    public DERBitString issuerUniqueId;
    public ASN1Sequence seq;
    public ASN1Integer serialNumber;
    public AlgorithmIdentifier signature;
    public Time startDate;
    public X500Name subject;
    public SubjectPublicKeyInfo subjectPublicKeyInfo;
    public DERBitString subjectUniqueId;
    public ASN1Integer version;

    /* JADX WARN: Removed duplicated region for block: B:23:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TBSCertificate(org.bouncycastle.asn1.ASN1Sequence r9) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.x509.TBSCertificate.<init>(org.bouncycastle.asn1.ASN1Sequence):void");
    }

    public static TBSCertificate getInstance(Object obj) {
        if (obj instanceof TBSCertificate) {
            return (TBSCertificate) obj;
        }
        if (obj != null) {
            return new TBSCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.endDate;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public DERBitString getIssuerUniqueId() {
        return this.issuerUniqueId;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public Time getStartDate() {
        return this.startDate;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPublicKeyInfo;
    }

    public DERBitString getSubjectUniqueId() {
        return this.subjectUniqueId;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public int getVersionNumber() {
        return this.version.intValueExact() + 1;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (Properties.getPropertyValue("org.bouncycastle.x509.allow_non-der_tbscert") != null && !Properties.isOverrideSet("org.bouncycastle.x509.allow_non-der_tbscert")) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (!this.version.hasValue(BigIntegers.ZERO)) {
                aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.version));
            }
            aSN1EncodableVector.add(this.serialNumber);
            aSN1EncodableVector.add(this.signature);
            aSN1EncodableVector.add(this.issuer);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
            aSN1EncodableVector2.add(this.startDate);
            aSN1EncodableVector2.add(this.endDate);
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            ASN1Encodable dERSequence = this.subject;
            if (dERSequence == null) {
                dERSequence = new DERSequence();
            }
            aSN1EncodableVector.add(dERSequence);
            aSN1EncodableVector.add(this.subjectPublicKeyInfo);
            DERBitString dERBitString = this.issuerUniqueId;
            if (dERBitString != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, dERBitString));
            }
            DERBitString dERBitString2 = this.subjectUniqueId;
            if (dERBitString2 != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 2, dERBitString2));
            }
            Extensions extensions = this.extensions;
            if (extensions != null) {
                aSN1EncodableVector.add(new DERTaggedObject(true, 3, extensions));
            }
            return new DERSequence(aSN1EncodableVector);
        }
        return this.seq;
    }
}
