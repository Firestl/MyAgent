package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.cookie.CookieIdentityComparator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import supwisdom.jo1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicCookieStore implements Serializable {
    public static final long serialVersionUID = -7581093305228232025L;
    public final TreeSet<jo1> cookies = new TreeSet<>(new CookieIdentityComparator());

    public synchronized void addCookie(jo1 jo1Var) {
        if (jo1Var != null) {
            this.cookies.remove(jo1Var);
            if (!jo1Var.isExpired(new Date())) {
                this.cookies.add(jo1Var);
            }
        }
    }

    public synchronized void addCookies(jo1[] jo1VarArr) {
        if (jo1VarArr != null) {
            for (jo1 jo1Var : jo1VarArr) {
                addCookie(jo1Var);
            }
        }
    }

    public synchronized void clear() {
        this.cookies.clear();
    }

    public synchronized boolean clearExpired(Date date) {
        boolean z = false;
        if (date == null) {
            return false;
        }
        Iterator<jo1> it = this.cookies.iterator();
        while (it.hasNext()) {
            if (it.next().isExpired(date)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public synchronized List<jo1> getCookies() {
        return new ArrayList(this.cookies);
    }

    public synchronized String toString() {
        return this.cookies.toString();
    }
}
