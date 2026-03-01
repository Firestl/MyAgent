package org.bouncycastle.pqc.crypto.lms;

/* JADX INFO: loaded from: classes3.dex */
public class LMSParameters {
    public final LMOtsParameters lmOTSParam;
    public final LMSigParameters lmSigParam;

    public LMSParameters(LMSigParameters lMSigParameters, LMOtsParameters lMOtsParameters) {
        this.lmSigParam = lMSigParameters;
        this.lmOTSParam = lMOtsParameters;
    }

    public LMOtsParameters getLMOTSParam() {
        return this.lmOTSParam;
    }

    public LMSigParameters getLMSigParam() {
        return this.lmSigParam;
    }
}
