package cz.msebera.android.httpclient.cookie;

import java.io.Serializable;
import java.util.Comparator;
import supwisdom.jo1;

/* JADX INFO: loaded from: classes2.dex */
public class CookieIdentityComparator implements Serializable, Comparator<jo1> {
    public static final long serialVersionUID = 4466565437490631532L;

    @Override // java.util.Comparator
    public int compare(jo1 jo1Var, jo1 jo1Var2) {
        int iCompareTo = jo1Var.getName().compareTo(jo1Var2.getName());
        if (iCompareTo == 0) {
            String domain = jo1Var.getDomain();
            String str = "";
            if (domain == null) {
                domain = "";
            } else if (domain.indexOf(46) == -1) {
                domain = domain + ".local";
            }
            String domain2 = jo1Var2.getDomain();
            if (domain2 != null) {
                if (domain2.indexOf(46) == -1) {
                    str = domain2 + ".local";
                } else {
                    str = domain2;
                }
            }
            iCompareTo = domain.compareToIgnoreCase(str);
        }
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        String path = jo1Var.getPath();
        if (path == null) {
            path = "/";
        }
        String path2 = jo1Var2.getPath();
        return path.compareTo(path2 != null ? path2 : "/");
    }
}
