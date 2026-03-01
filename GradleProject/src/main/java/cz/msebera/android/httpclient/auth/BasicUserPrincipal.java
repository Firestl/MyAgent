package cz.msebera.android.httpclient.auth;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.security.Principal;
import supwisdom.ap1;
import supwisdom.yo1;

/* JADX INFO: loaded from: classes2.dex */
public final class BasicUserPrincipal implements Principal, Serializable {
    public static final long serialVersionUID = -2266305184969850467L;
    public final String username;

    public BasicUserPrincipal(String str) {
        yo1.a(str, "User name");
        this.username = str;
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BasicUserPrincipal) && ap1.a(this.username, ((BasicUserPrincipal) obj).username);
    }

    @Override // java.security.Principal
    public String getName() {
        return this.username;
    }

    @Override // java.security.Principal
    public int hashCode() {
        return ap1.a(17, this.username);
    }

    @Override // java.security.Principal
    public String toString() {
        return "[principal: " + this.username + Operators.ARRAY_END_STR;
    }
}
