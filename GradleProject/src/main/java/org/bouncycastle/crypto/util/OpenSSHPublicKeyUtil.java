package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.custom.sec.SecP256R1Curve;

/* JADX INFO: loaded from: classes3.dex */
public class OpenSSHPublicKeyUtil {
    public static final String DSS = "ssh-dss";
    public static final String ECDSA = "ecdsa";
    public static final String ED_25519 = "ssh-ed25519";
    public static final String RSA = "ssh-rsa";

    public static byte[] encodePublicKey(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        if (asymmetricKeyParameter == null) {
            throw new IllegalArgumentException("cipherParameters was null.");
        }
        if (asymmetricKeyParameter instanceof RSAKeyParameters) {
            if (asymmetricKeyParameter.isPrivate()) {
                throw new IllegalArgumentException("RSAKeyParamaters was for encryption");
            }
            RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) asymmetricKeyParameter;
            SSHBuilder sSHBuilder = new SSHBuilder();
            sSHBuilder.writeString(RSA);
            sSHBuilder.writeBigNum(rSAKeyParameters.getExponent());
            sSHBuilder.writeBigNum(rSAKeyParameters.getModulus());
            return sSHBuilder.getBytes();
        }
        if (asymmetricKeyParameter instanceof ECPublicKeyParameters) {
            SSHBuilder sSHBuilder2 = new SSHBuilder();
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) asymmetricKeyParameter;
            if (!(eCPublicKeyParameters.getParameters().getCurve() instanceof SecP256R1Curve)) {
                throw new IllegalArgumentException("unable to derive ssh curve name for " + eCPublicKeyParameters.getParameters().getCurve().getClass().getName());
            }
            sSHBuilder2.writeString("ecdsa-sha2-nistp256");
            sSHBuilder2.writeString("nistp256");
            sSHBuilder2.writeBlock(eCPublicKeyParameters.getQ().getEncoded(false));
            return sSHBuilder2.getBytes();
        }
        if (asymmetricKeyParameter instanceof DSAPublicKeyParameters) {
            DSAPublicKeyParameters dSAPublicKeyParameters = (DSAPublicKeyParameters) asymmetricKeyParameter;
            DSAParameters parameters = dSAPublicKeyParameters.getParameters();
            SSHBuilder sSHBuilder3 = new SSHBuilder();
            sSHBuilder3.writeString(DSS);
            sSHBuilder3.writeBigNum(parameters.getP());
            sSHBuilder3.writeBigNum(parameters.getQ());
            sSHBuilder3.writeBigNum(parameters.getG());
            sSHBuilder3.writeBigNum(dSAPublicKeyParameters.getY());
            return sSHBuilder3.getBytes();
        }
        if (asymmetricKeyParameter instanceof Ed25519PublicKeyParameters) {
            SSHBuilder sSHBuilder4 = new SSHBuilder();
            sSHBuilder4.writeString(ED_25519);
            sSHBuilder4.writeBlock(((Ed25519PublicKeyParameters) asymmetricKeyParameter).getEncoded());
            return sSHBuilder4.getBytes();
        }
        throw new IllegalArgumentException("unable to convert " + asymmetricKeyParameter.getClass().getName() + " to private key");
    }

    public static AsymmetricKeyParameter parsePublicKey(SSHBuffer sSHBuffer) {
        AsymmetricKeyParameter ed25519PublicKeyParameters;
        String string = sSHBuffer.readString();
        if (RSA.equals(string)) {
            ed25519PublicKeyParameters = new RSAKeyParameters(false, sSHBuffer.readBigNumPositive(), sSHBuffer.readBigNumPositive());
        } else if (DSS.equals(string)) {
            ed25519PublicKeyParameters = new DSAPublicKeyParameters(sSHBuffer.readBigNumPositive(), new DSAParameters(sSHBuffer.readBigNumPositive(), sSHBuffer.readBigNumPositive(), sSHBuffer.readBigNumPositive()));
        } else if (string.startsWith(ECDSA)) {
            String string2 = sSHBuffer.readString();
            if (string2.startsWith("nist")) {
                String strSubstring = string2.substring(4);
                string2 = strSubstring.substring(0, 1) + "-" + strSubstring.substring(1);
            }
            X9ECParameters byName = ECNamedCurveTable.getByName(string2);
            if (byName == null) {
                throw new IllegalStateException("unable to find curve for " + string + " using curve name " + string2);
            }
            ECCurve curve = byName.getCurve();
            ed25519PublicKeyParameters = new ECPublicKeyParameters(curve.decodePoint(sSHBuffer.readBlock()), new ECDomainParameters(curve, byName.getG(), byName.getN(), byName.getH(), byName.getSeed()));
        } else if (ED_25519.equals(string)) {
            byte[] block = sSHBuffer.readBlock();
            if (block.length != 32) {
                throw new IllegalStateException("public key value of wrong length");
            }
            ed25519PublicKeyParameters = new Ed25519PublicKeyParameters(block, 0);
        } else {
            ed25519PublicKeyParameters = null;
        }
        if (ed25519PublicKeyParameters == null) {
            throw new IllegalArgumentException("unable to parse key");
        }
        if (sSHBuffer.hasRemaining()) {
            throw new IllegalArgumentException("decoded key has trailing data");
        }
        return ed25519PublicKeyParameters;
    }

    public static AsymmetricKeyParameter parsePublicKey(byte[] bArr) {
        return parsePublicKey(new SSHBuffer(bArr));
    }
}
