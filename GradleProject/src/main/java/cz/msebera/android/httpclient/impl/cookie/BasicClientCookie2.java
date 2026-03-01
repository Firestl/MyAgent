package cz.msebera.android.httpclient.impl.cookie;

import java.util.Date;
import supwisdom.ko1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicClientCookie2 extends BasicClientCookie implements ko1 {
    public static final long serialVersionUID = -7744598295706617057L;
    public String commentURL;
    public boolean discard;
    public int[] ports;

    public BasicClientCookie2(String str, String str2) {
        super(str, str2);
    }

    @Override // cz.msebera.android.httpclient.impl.cookie.BasicClientCookie
    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 basicClientCookie2 = (BasicClientCookie2) super.clone();
        int[] iArr = this.ports;
        if (iArr != null) {
            basicClientCookie2.ports = (int[]) iArr.clone();
        }
        return basicClientCookie2;
    }

    @Override // cz.msebera.android.httpclient.impl.cookie.BasicClientCookie
    public String getCommentURL() {
        return this.commentURL;
    }

    @Override // cz.msebera.android.httpclient.impl.cookie.BasicClientCookie
    public int[] getPorts() {
        return this.ports;
    }

    @Override // cz.msebera.android.httpclient.impl.cookie.BasicClientCookie, supwisdom.jo1
    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    @Override // cz.msebera.android.httpclient.impl.cookie.BasicClientCookie
    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    public void setCommentURL(String str) {
        this.commentURL = str;
    }

    public void setDiscard(boolean z) {
        this.discard = z;
    }

    public void setPorts(int[] iArr) {
        this.ports = iArr;
    }
}
