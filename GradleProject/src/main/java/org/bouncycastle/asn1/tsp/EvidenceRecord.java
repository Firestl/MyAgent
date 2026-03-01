package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* JADX INFO: loaded from: classes3.dex */
public class EvidenceRecord extends ASN1Object {
    public static final ASN1ObjectIdentifier OID = new ASN1ObjectIdentifier("1.3.6.1.5.5.11.0.2.1");
    public ArchiveTimeStampSequence archiveTimeStampSequence;
    public CryptoInfos cryptoInfos;
    public ASN1Sequence digestAlgorithms;
    public EncryptionInfo encryptionInfo;
    public ASN1Integer version;

    public EvidenceRecord(ASN1Sequence aSN1Sequence) {
        this.version = new ASN1Integer(1L);
        if (aSN1Sequence.size() < 3 && aSN1Sequence.size() > 5) {
            throw new IllegalArgumentException("wrong sequence size in constructor: " + aSN1Sequence.size());
        }
        ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Integer.intValueExact() != 1) {
            throw new IllegalArgumentException("incompatible version");
        }
        this.version = aSN1Integer;
        this.digestAlgorithms = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        for (int i = 2; i != aSN1Sequence.size() - 1; i++) {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i);
            if (!(objectAt instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("unknown object in getInstance: " + objectAt.getClass().getName());
            }
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objectAt;
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.cryptoInfos = CryptoInfos.getInstance(aSN1TaggedObject, false);
            } else {
                if (tagNo != 1) {
                    throw new IllegalArgumentException("unknown tag in getInstance: " + aSN1TaggedObject.getTagNo());
                }
                this.encryptionInfo = EncryptionInfo.getInstance(aSN1TaggedObject, false);
            }
        }
        this.archiveTimeStampSequence = ArchiveTimeStampSequence.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public EvidenceRecord(org.bouncycastle.asn1.tsp.EvidenceRecord r5, org.bouncycastle.asn1.tsp.ArchiveTimeStampSequence r6, org.bouncycastle.asn1.tsp.ArchiveTimeStamp r7) {
        /*
            r4 = this;
            r4.<init>()
            org.bouncycastle.asn1.ASN1Integer r0 = new org.bouncycastle.asn1.ASN1Integer
            r1 = 1
            r0.<init>(r1)
            r4.version = r0
            org.bouncycastle.asn1.ASN1Integer r0 = r5.version
            r4.version = r0
            if (r7 == 0) goto L45
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r7 = r7.getDigestAlgorithmIdentifier()
            org.bouncycastle.asn1.ASN1EncodableVector r0 = new org.bouncycastle.asn1.ASN1EncodableVector
            r0.<init>()
            org.bouncycastle.asn1.ASN1Sequence r1 = r5.digestAlgorithms
            java.util.Enumeration r1 = r1.getObjects()
            r2 = 0
        L22:
            boolean r3 = r1.hasMoreElements()
            if (r3 == 0) goto L3a
            java.lang.Object r3 = r1.nextElement()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = org.bouncycastle.asn1.x509.AlgorithmIdentifier.getInstance(r3)
            r0.add(r3)
            boolean r3 = r3.equals(r7)
            if (r3 == 0) goto L22
            r2 = 1
        L3a:
            if (r2 != 0) goto L45
            r0.add(r7)
            org.bouncycastle.asn1.DERSequence r7 = new org.bouncycastle.asn1.DERSequence
            r7.<init>(r0)
            goto L47
        L45:
            org.bouncycastle.asn1.ASN1Sequence r7 = r5.digestAlgorithms
        L47:
            r4.digestAlgorithms = r7
            org.bouncycastle.asn1.tsp.CryptoInfos r7 = r5.cryptoInfos
            r4.cryptoInfos = r7
            org.bouncycastle.asn1.tsp.EncryptionInfo r5 = r5.encryptionInfo
            r4.encryptionInfo = r5
            r4.archiveTimeStampSequence = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.tsp.EvidenceRecord.<init>(org.bouncycastle.asn1.tsp.EvidenceRecord, org.bouncycastle.asn1.tsp.ArchiveTimeStampSequence, org.bouncycastle.asn1.tsp.ArchiveTimeStamp):void");
    }

    public EvidenceRecord(AlgorithmIdentifier[] algorithmIdentifierArr, CryptoInfos cryptoInfos, EncryptionInfo encryptionInfo, ArchiveTimeStampSequence archiveTimeStampSequence) {
        this.version = new ASN1Integer(1L);
        this.digestAlgorithms = new DERSequence(algorithmIdentifierArr);
        this.cryptoInfos = cryptoInfos;
        this.encryptionInfo = encryptionInfo;
        this.archiveTimeStampSequence = archiveTimeStampSequence;
    }

    public static EvidenceRecord getInstance(Object obj) {
        if (obj instanceof EvidenceRecord) {
            return (EvidenceRecord) obj;
        }
        if (obj != null) {
            return new EvidenceRecord(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static EvidenceRecord getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public EvidenceRecord addArchiveTimeStamp(ArchiveTimeStamp archiveTimeStamp, boolean z) {
        if (z) {
            return new EvidenceRecord(this, this.archiveTimeStampSequence.append(new ArchiveTimeStampChain(archiveTimeStamp)), archiveTimeStamp);
        }
        ArchiveTimeStampChain[] archiveTimeStampChains = this.archiveTimeStampSequence.getArchiveTimeStampChains();
        archiveTimeStampChains[archiveTimeStampChains.length - 1] = archiveTimeStampChains[archiveTimeStampChains.length - 1].append(archiveTimeStamp);
        return new EvidenceRecord(this, new ArchiveTimeStampSequence(archiveTimeStampChains), null);
    }

    public ArchiveTimeStampSequence getArchiveTimeStampSequence() {
        return this.archiveTimeStampSequence;
    }

    public AlgorithmIdentifier[] getDigestAlgorithms() {
        int size = this.digestAlgorithms.size();
        AlgorithmIdentifier[] algorithmIdentifierArr = new AlgorithmIdentifier[size];
        for (int i = 0; i != size; i++) {
            algorithmIdentifierArr[i] = AlgorithmIdentifier.getInstance(this.digestAlgorithms.getObjectAt(i));
        }
        return algorithmIdentifierArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(5);
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.digestAlgorithms);
        CryptoInfos cryptoInfos = this.cryptoInfos;
        if (cryptoInfos != null) {
            aSN1EncodableVector.add(cryptoInfos);
        }
        EncryptionInfo encryptionInfo = this.encryptionInfo;
        if (encryptionInfo != null) {
            aSN1EncodableVector.add(encryptionInfo);
        }
        aSN1EncodableVector.add(this.archiveTimeStampSequence);
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "EvidenceRecord: Oid(" + OID + ")";
    }
}
