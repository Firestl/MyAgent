package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;
import supwisdom.qo1;
import supwisdom.ro1;
import supwisdom.to1;
import supwisdom.xn1;
import supwisdom.yn1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicHeader implements xn1, Cloneable, Serializable {
    public static final long serialVersionUID = -5427236326487562174L;
    public final String name;
    public final String value;

    public BasicHeader(String str, String str2) {
        yo1.a(str, "Name");
        this.name = str;
        this.value = str2;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // supwisdom.xn1
    public yn1[] getElements() throws ParseException {
        String str = this.value;
        return str != null ? qo1.a(str, (to1) null) : new yn1[0];
    }

    @Override // supwisdom.xn1
    public String getName() {
        return this.name;
    }

    @Override // supwisdom.xn1
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return ro1.f9079a.b((CharArrayBuffer) null, this).toString();
    }
}
