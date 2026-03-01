package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpException;
import supwisdom.bo1;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class TunnelRefusedException extends HttpException {
    public static final long serialVersionUID = -8646722842745617323L;
    public final bo1 response;

    public TunnelRefusedException(String str, bo1 bo1Var) {
        super(str);
        this.response = bo1Var;
    }

    public bo1 getResponse() {
        return this.response;
    }
}
