package cz.msebera.android.httpclient.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import supwisdom.ap1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public class NTUserPrincipal implements Principal, Serializable {
    public static final long serialVersionUID = -6870169797924406894L;
    public final String domain;
    public final String ntname;
    public final String username;

    public NTUserPrincipal(String str, String str2) {
        yo1.a(str2, "User name");
        this.username = str2;
        if (str != null) {
            this.domain = str.toUpperCase(Locale.ENGLISH);
        } else {
            this.domain = null;
        }
        String str3 = this.domain;
        if (str3 == null || str3.length() <= 0) {
            this.ntname = this.username;
            return;
        }
        this.ntname = this.domain + '\\' + this.username;
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NTUserPrincipal)) {
            return false;
        }
        NTUserPrincipal nTUserPrincipal = (NTUserPrincipal) obj;
        return ap1.a(this.username, nTUserPrincipal.username) && ap1.a(this.domain, nTUserPrincipal.domain);
    }

    public String getDomain() {
        return this.domain;
    }

    @Override // java.security.Principal
    public String getName() {
        return this.ntname;
    }

    public String getUsername() {
        return this.username;
    }

    @Override // java.security.Principal
    public int hashCode() {
        return ap1.a(ap1.a(17, this.username), this.domain);
    }

    @Override // java.security.Principal
    public String toString() {
        return this.ntname;
    }
}
