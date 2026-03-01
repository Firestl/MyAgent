package cz.msebera.android.httpclient.params;

import supwisdom.wo1;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class SyncBasicHttpParams extends BasicHttpParams {
    public static final long serialVersionUID = 5387834869062660642L;

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams
    public synchronized void clear() {
        super.clear();
    }

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams
    public synchronized Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams, supwisdom.wo1
    public synchronized Object getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams
    public synchronized boolean isParameterSet(String str) {
        return super.isParameterSet(str);
    }

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams
    public synchronized boolean isParameterSetLocally(String str) {
        return super.isParameterSetLocally(str);
    }

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams
    public synchronized boolean removeParameter(String str) {
        return super.removeParameter(str);
    }

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams, supwisdom.wo1
    public synchronized wo1 setParameter(String str, Object obj) {
        return super.setParameter(str, obj);
    }

    @Override // cz.msebera.android.httpclient.params.BasicHttpParams
    public synchronized void setParameters(String[] strArr, Object obj) {
        super.setParameters(strArr, obj);
    }
}
