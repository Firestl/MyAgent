package cz.msebera.android.httpclient.auth;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import supwisdom.ap1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class NTCredentials implements Serializable {
    public static final long serialVersionUID = -7385699315228907265L;
    public final String password;
    public final NTUserPrincipal principal;
    public final String workstation;

    public NTCredentials(String str) {
        yo1.a(str, "Username:password string");
        int iIndexOf = str.indexOf(58);
        if (iIndexOf >= 0) {
            String strSubstring = str.substring(0, iIndexOf);
            this.password = str.substring(iIndexOf + 1);
            str = strSubstring;
        } else {
            this.password = null;
        }
        int iIndexOf2 = str.indexOf(47);
        if (iIndexOf2 >= 0) {
            this.principal = new NTUserPrincipal(str.substring(0, iIndexOf2).toUpperCase(Locale.ENGLISH), str.substring(iIndexOf2 + 1));
        } else {
            this.principal = new NTUserPrincipal(null, str.substring(iIndexOf2 + 1));
        }
        this.workstation = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NTCredentials)) {
            return false;
        }
        NTCredentials nTCredentials = (NTCredentials) obj;
        return ap1.a(this.principal, nTCredentials.principal) && ap1.a(this.workstation, nTCredentials.workstation);
    }

    public String getDomain() {
        return this.principal.getDomain();
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.principal.getUsername();
    }

    public Principal getUserPrincipal() {
        return this.principal;
    }

    public String getWorkstation() {
        return this.workstation;
    }

    public int hashCode() {
        return ap1.a(ap1.a(17, this.principal), this.workstation);
    }

    public String toString() {
        return "[principal: " + this.principal + "][workstation: " + this.workstation + Operators.ARRAY_END_STR;
    }

    public NTCredentials(String str, String str2, String str3, String str4) {
        yo1.a(str, "User name");
        this.principal = new NTUserPrincipal(str4, str);
        this.password = str2;
        if (str3 != null) {
            this.workstation = str3.toUpperCase(Locale.ENGLISH);
        } else {
            this.workstation = null;
        }
    }
}
