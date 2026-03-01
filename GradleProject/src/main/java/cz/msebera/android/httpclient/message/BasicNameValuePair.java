package cz.msebera.android.httpclient.message;

import com.huawei.hms.framework.common.ContainerUtils;
import java.io.Serializable;
import supwisdom.ap1;
import supwisdom.co1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicNameValuePair implements co1, Cloneable, Serializable {
    public static final long serialVersionUID = -6437800749411518984L;
    public final String name;
    public final String value;

    public BasicNameValuePair(String str, String str2) {
        yo1.a(str, "Name");
        this.name = str;
        this.value = str2;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof co1)) {
            return false;
        }
        BasicNameValuePair basicNameValuePair = (BasicNameValuePair) obj;
        return this.name.equals(basicNameValuePair.name) && ap1.a(this.value, basicNameValuePair.value);
    }

    @Override // supwisdom.co1
    public String getName() {
        return this.name;
    }

    @Override // supwisdom.co1
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return ap1.a(ap1.a(17, this.name), this.value);
    }

    public String toString() {
        if (this.value == null) {
            return this.name;
        }
        StringBuilder sb = new StringBuilder(this.name.length() + 1 + this.value.length());
        sb.append(this.name);
        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
        sb.append(this.value);
        return sb.toString();
    }
}
