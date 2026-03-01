package org.bouncycastle.pqc.crypto.sphincs;

import com.uc.crashsdk.export.LogType;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* JADX INFO: loaded from: classes3.dex */
public class SPHINCS256KeyGenerationParameters extends KeyGenerationParameters {
    public final Digest treeDigest;

    public SPHINCS256KeyGenerationParameters(SecureRandom secureRandom, Digest digest) {
        super(secureRandom, LogType.UNEXP_EXIT);
        this.treeDigest = digest;
    }

    public Digest getTreeDigest() {
        return this.treeDigest;
    }
}
