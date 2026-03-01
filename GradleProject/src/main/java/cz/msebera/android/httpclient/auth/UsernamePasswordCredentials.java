package cz.msebera.android.httpclient.auth;

import java.io.Serializable;
import java.security.Principal;
import supwisdom.ap1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class UsernamePasswordCredentials implements Serializable {
    public static final long serialVersionUID = 243343858802739403L;
    public final String password;
    public final BasicUserPrincipal principal;

    public UsernamePasswordCredentials(String str) {
        yo1.a(str, "Username:password string");
        int iIndexOf = str.indexOf(58);
        if (iIndexOf >= 0) {
            this.principal = new BasicUserPrincipal(str.substring(0, iIndexOf));
            this.password = str.substring(iIndexOf + 1);
        } else {
            this.principal = new BasicUserPrincipal(str);
            this.password = null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UsernamePasswordCredentials) && ap1.a(this.principal, ((UsernamePasswordCredentials) obj).principal);
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.principal.getName();
    }

    public Principal getUserPrincipal() {
        return this.principal;
    }

    public int hashCode() {
        return this.principal.hashCode();
    }

    public String toString() {
        return this.principal.toString();
    }

    public UsernamePasswordCredentials(String str, String str2) {
        yo1.a(str, "Username");
        this.principal = new BasicUserPrincipal(str);
        this.password = str2;
    }
}
