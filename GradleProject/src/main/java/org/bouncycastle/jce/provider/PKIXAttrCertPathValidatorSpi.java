package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;

/* JADX INFO: loaded from: classes3.dex */
public class PKIXAttrCertPathValidatorSpi extends CertPathValidatorSpi {
    public final JcaJceHelper helper = new BCJcaJceHelper();

    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParametersBuild;
        boolean z = certPathParameters instanceof ExtendedPKIXParameters;
        if (!z && !(certPathParameters instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + ExtendedPKIXParameters.class.getName() + " instance.");
        }
        Set hashSet = new HashSet();
        Set hashSet2 = new HashSet();
        Set hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (z) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
                hashSet = extendedPKIXParameters.getAttrCertCheckers();
                hashSet2 = extendedPKIXParameters.getProhibitedACAttributes();
                hashSet3 = extendedPKIXParameters.getNecessaryACAttributes();
            }
            pKIXExtendedParametersBuild = builder.build();
        } else {
            pKIXExtendedParametersBuild = (PKIXExtendedParameters) certPathParameters;
        }
        PKIXExtendedParameters pKIXExtendedParameters = pKIXExtendedParametersBuild;
        Selector targetConstraints = pKIXExtendedParameters.getTargetConstraints();
        if (!(targetConstraints instanceof X509AttributeCertStoreSelector)) {
            throw new InvalidAlgorithmParameterException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + PKIXAttrCertPathValidatorSpi.class.getName() + " class.");
        }
        X509AttributeCertificate attributeCert = ((X509AttributeCertStoreSelector) targetConstraints).getAttributeCert();
        CertPath certPathProcessAttrCert1 = RFC3281CertPathUtilities.processAttrCert1(attributeCert, pKIXExtendedParameters);
        CertPathValidatorResult certPathValidatorResultProcessAttrCert2 = RFC3281CertPathUtilities.processAttrCert2(certPath, pKIXExtendedParameters);
        X509Certificate x509Certificate = (X509Certificate) certPath.getCertificates().get(0);
        RFC3281CertPathUtilities.processAttrCert3(x509Certificate, pKIXExtendedParameters);
        RFC3281CertPathUtilities.processAttrCert4(x509Certificate, hashSet4);
        RFC3281CertPathUtilities.processAttrCert5(attributeCert, pKIXExtendedParameters);
        RFC3281CertPathUtilities.processAttrCert7(attributeCert, certPath, certPathProcessAttrCert1, pKIXExtendedParameters, hashSet);
        RFC3281CertPathUtilities.additionalChecks(attributeCert, hashSet2, hashSet3);
        try {
            RFC3281CertPathUtilities.checkCRLs(attributeCert, pKIXExtendedParameters, x509Certificate, CertPathValidatorUtilities.getValidCertDateFromValidityModel(pKIXExtendedParameters, null, -1), certPath.getCertificates(), this.helper);
            return certPathValidatorResultProcessAttrCert2;
        } catch (AnnotatedException e2) {
            throw new ExtCertPathValidatorException("Could not get validity date from attribute certificate.", e2);
        }
    }
}
