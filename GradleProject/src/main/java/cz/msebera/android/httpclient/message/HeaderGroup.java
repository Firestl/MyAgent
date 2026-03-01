package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import supwisdom.so1;
import supwisdom.xn1;
import supwisdom.zn1;

/* JADX INFO: loaded from: classes2.dex */
public class HeaderGroup implements Cloneable, Serializable {
    public static final long serialVersionUID = 2608834160639271617L;
    public final List<xn1> headers = new ArrayList(16);

    public void addHeader(xn1 xn1Var) {
        if (xn1Var == null) {
            return;
        }
        this.headers.add(xn1Var);
    }

    public void clear() {
        this.headers.clear();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean containsHeader(String str) {
        for (int i = 0; i < this.headers.size(); i++) {
            if (this.headers.get(i).getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public HeaderGroup copy() {
        HeaderGroup headerGroup = new HeaderGroup();
        headerGroup.headers.addAll(this.headers);
        return headerGroup;
    }

    public xn1[] getAllHeaders() {
        List<xn1> list = this.headers;
        return (xn1[]) list.toArray(new xn1[list.size()]);
    }

    public xn1 getCondensedHeader(String str) {
        xn1[] headers = getHeaders(str);
        if (headers.length == 0) {
            return null;
        }
        if (headers.length == 1) {
            return headers[0];
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(128);
        charArrayBuffer.append(headers[0].getValue());
        for (int i = 1; i < headers.length; i++) {
            charArrayBuffer.append(", ");
            charArrayBuffer.append(headers[i].getValue());
        }
        return new BasicHeader(str.toLowerCase(Locale.ENGLISH), charArrayBuffer.toString());
    }

    public xn1 getFirstHeader(String str) {
        for (int i = 0; i < this.headers.size(); i++) {
            xn1 xn1Var = this.headers.get(i);
            if (xn1Var.getName().equalsIgnoreCase(str)) {
                return xn1Var;
            }
        }
        return null;
    }

    public xn1[] getHeaders(String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.headers.size(); i++) {
            xn1 xn1Var = this.headers.get(i);
            if (xn1Var.getName().equalsIgnoreCase(str)) {
                arrayList.add(xn1Var);
            }
        }
        return (xn1[]) arrayList.toArray(new xn1[arrayList.size()]);
    }

    public xn1 getLastHeader(String str) {
        for (int size = this.headers.size() - 1; size >= 0; size--) {
            xn1 xn1Var = this.headers.get(size);
            if (xn1Var.getName().equalsIgnoreCase(str)) {
                return xn1Var;
            }
        }
        return null;
    }

    public zn1 iterator() {
        return new so1(this.headers, null);
    }

    public void removeHeader(xn1 xn1Var) {
        if (xn1Var == null) {
            return;
        }
        this.headers.remove(xn1Var);
    }

    public void setHeaders(xn1[] xn1VarArr) {
        clear();
        if (xn1VarArr == null) {
            return;
        }
        Collections.addAll(this.headers, xn1VarArr);
    }

    public String toString() {
        return this.headers.toString();
    }

    public void updateHeader(xn1 xn1Var) {
        if (xn1Var == null) {
            return;
        }
        for (int i = 0; i < this.headers.size(); i++) {
            if (this.headers.get(i).getName().equalsIgnoreCase(xn1Var.getName())) {
                this.headers.set(i, xn1Var);
                return;
            }
        }
        this.headers.add(xn1Var);
    }

    public zn1 iterator(String str) {
        return new so1(this.headers, str);
    }
}
