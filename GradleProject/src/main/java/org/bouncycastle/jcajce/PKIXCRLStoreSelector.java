package org.bouncycastle.jcajce;

import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;

/* JADX INFO: loaded from: classes3.dex */
public class PKIXCRLStoreSelector<T extends CRL> implements Selector<T> {
    public final CRLSelector baseSelector;
    public final boolean completeCRLEnabled;
    public final boolean deltaCRLIndicator;
    public final byte[] issuingDistributionPoint;
    public final boolean issuingDistributionPointEnabled;
    public final BigInteger maxBaseCRLNumber;

    public static class Builder {
        public final CRLSelector baseSelector;
        public boolean deltaCRLIndicator = false;
        public boolean completeCRLEnabled = false;
        public BigInteger maxBaseCRLNumber = null;
        public byte[] issuingDistributionPoint = null;
        public boolean issuingDistributionPointEnabled = false;

        public Builder(CRLSelector cRLSelector) {
            this.baseSelector = (CRLSelector) cRLSelector.clone();
        }

        public PKIXCRLStoreSelector<? extends CRL> build() {
            return new PKIXCRLStoreSelector<>(this);
        }

        public Builder setCompleteCRLEnabled(boolean z) {
            this.completeCRLEnabled = z;
            return this;
        }

        public Builder setDeltaCRLIndicatorEnabled(boolean z) {
            this.deltaCRLIndicator = z;
            return this;
        }

        public void setIssuingDistributionPoint(byte[] bArr) {
            this.issuingDistributionPoint = Arrays.clone(bArr);
        }

        public void setIssuingDistributionPointEnabled(boolean z) {
            this.issuingDistributionPointEnabled = z;
        }

        public void setMaxBaseCRLNumber(BigInteger bigInteger) {
            this.maxBaseCRLNumber = bigInteger;
        }
    }

    public static class SelectorClone extends X509CRLSelector {
        public final PKIXCRLStoreSelector selector;

        public SelectorClone(PKIXCRLStoreSelector pKIXCRLStoreSelector) {
            this.selector = pKIXCRLStoreSelector;
            if (pKIXCRLStoreSelector.baseSelector instanceof X509CRLSelector) {
                X509CRLSelector x509CRLSelector = (X509CRLSelector) pKIXCRLStoreSelector.baseSelector;
                setCertificateChecking(x509CRLSelector.getCertificateChecking());
                setDateAndTime(x509CRLSelector.getDateAndTime());
                setIssuers(x509CRLSelector.getIssuers());
                setMinCRLNumber(x509CRLSelector.getMinCRL());
                setMaxCRLNumber(x509CRLSelector.getMaxCRL());
            }
        }

        @Override // java.security.cert.X509CRLSelector, java.security.cert.CRLSelector
        public boolean match(CRL crl) {
            PKIXCRLStoreSelector pKIXCRLStoreSelector = this.selector;
            return pKIXCRLStoreSelector == null ? crl != null : pKIXCRLStoreSelector.match(crl);
        }
    }

    public PKIXCRLStoreSelector(Builder builder) {
        this.baseSelector = builder.baseSelector;
        this.deltaCRLIndicator = builder.deltaCRLIndicator;
        this.completeCRLEnabled = builder.completeCRLEnabled;
        this.maxBaseCRLNumber = builder.maxBaseCRLNumber;
        this.issuingDistributionPoint = builder.issuingDistributionPoint;
        this.issuingDistributionPointEnabled = builder.issuingDistributionPointEnabled;
    }

    public static Collection<? extends CRL> getCRLs(PKIXCRLStoreSelector pKIXCRLStoreSelector, CertStore certStore) throws CertStoreException {
        return certStore.getCRLs(new SelectorClone(pKIXCRLStoreSelector));
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return this;
    }

    public X509Certificate getCertificateChecking() {
        CRLSelector cRLSelector = this.baseSelector;
        if (cRLSelector instanceof X509CRLSelector) {
            return ((X509CRLSelector) cRLSelector).getCertificateChecking();
        }
        return null;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.issuingDistributionPoint);
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.maxBaseCRLNumber;
    }

    public boolean isCompleteCRLEnabled() {
        return this.completeCRLEnabled;
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.deltaCRLIndicator;
    }

    public boolean isIssuingDistributionPointEnabled() {
        return this.issuingDistributionPointEnabled;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
    
        if (org.bouncycastle.util.Arrays.areEqual(r0, r1) == false) goto L32;
     */
    @Override // org.bouncycastle.util.Selector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean match(java.security.cert.CRL r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof java.security.cert.X509CRL
            if (r0 != 0) goto Lb
        L4:
            java.security.cert.CRLSelector r0 = r4.baseSelector
            boolean r5 = r0.match(r5)
            return r5
        Lb:
            r0 = r5
            java.security.cert.X509CRL r0 = (java.security.cert.X509CRL) r0
            r1 = 0
            r2 = 0
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.x509.Extension.deltaCRLIndicator     // Catch: java.lang.Exception -> L69
            java.lang.String r3 = r3.getId()     // Catch: java.lang.Exception -> L69
            byte[] r3 = r0.getExtensionValue(r3)     // Catch: java.lang.Exception -> L69
            if (r3 == 0) goto L28
            org.bouncycastle.asn1.ASN1OctetString r1 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r3)     // Catch: java.lang.Exception -> L69
            byte[] r1 = r1.getOctets()     // Catch: java.lang.Exception -> L69
            org.bouncycastle.asn1.ASN1Integer r1 = org.bouncycastle.asn1.ASN1Integer.getInstance(r1)     // Catch: java.lang.Exception -> L69
        L28:
            boolean r3 = r4.isDeltaCRLIndicatorEnabled()
            if (r3 == 0) goto L31
            if (r1 != 0) goto L31
            return r2
        L31:
            boolean r3 = r4.isCompleteCRLEnabled()
            if (r3 == 0) goto L3a
            if (r1 == 0) goto L3a
            return r2
        L3a:
            if (r1 == 0) goto L4e
            java.math.BigInteger r3 = r4.maxBaseCRLNumber
            if (r3 == 0) goto L4e
            java.math.BigInteger r1 = r1.getPositiveValue()
            java.math.BigInteger r3 = r4.maxBaseCRLNumber
            int r1 = r1.compareTo(r3)
            r3 = 1
            if (r1 != r3) goto L4e
            return r2
        L4e:
            boolean r1 = r4.issuingDistributionPointEnabled
            if (r1 == 0) goto L4
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = org.bouncycastle.asn1.x509.Extension.issuingDistributionPoint
            java.lang.String r1 = r1.getId()
            byte[] r0 = r0.getExtensionValue(r1)
            byte[] r1 = r4.issuingDistributionPoint
            if (r1 != 0) goto L63
            if (r0 == 0) goto L4
            return r2
        L63:
            boolean r0 = org.bouncycastle.util.Arrays.areEqual(r0, r1)
            if (r0 != 0) goto L4
        L69:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.PKIXCRLStoreSelector.match(java.security.cert.CRL):boolean");
    }
}
