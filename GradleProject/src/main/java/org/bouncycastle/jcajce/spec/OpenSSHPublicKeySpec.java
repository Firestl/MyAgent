package org.bouncycastle.jcajce.spec;

import java.security.spec.EncodedKeySpec;
import org.bouncycastle.crypto.util.OpenSSHPublicKeyUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: classes3.dex */
public class OpenSSHPublicKeySpec extends EncodedKeySpec {
    public static final String[] allowedTypes = {OpenSSHPublicKeyUtil.RSA, OpenSSHPublicKeyUtil.ED_25519, OpenSSHPublicKeyUtil.DSS};
    public final String type;

    public OpenSSHPublicKeySpec(byte[] bArr) {
        super(bArr);
        int i = 0;
        int i2 = (((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) + 4;
        if (i2 >= bArr.length) {
            throw new IllegalArgumentException("invalid public key blob: type field longer than blob");
        }
        String strFromByteArray = Strings.fromByteArray(Arrays.copyOfRange(bArr, 4, i2));
        this.type = strFromByteArray;
        if (strFromByteArray.startsWith(OpenSSHPublicKeyUtil.ECDSA)) {
            return;
        }
        while (true) {
            String[] strArr = allowedTypes;
            if (i >= strArr.length) {
                throw new IllegalArgumentException("unrecognised public key type " + this.type);
            }
            if (strArr[i].equals(this.type)) {
                return;
            } else {
                i++;
            }
        }
    }

    @Override // java.security.spec.EncodedKeySpec
    public String getFormat() {
        return "OpenSSH";
    }

    public String getType() {
        return this.type;
    }
}
