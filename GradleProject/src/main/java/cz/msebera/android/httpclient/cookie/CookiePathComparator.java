package cz.msebera.android.httpclient.cookie;

import java.io.Serializable;
import java.util.Comparator;
import supwisdom.jo1;

/* JADX INFO: loaded from: classes2.dex */
public class CookiePathComparator implements Serializable, Comparator<jo1> {
    public static final long serialVersionUID = 7523645369616405818L;

    private String normalizePath(jo1 jo1Var) {
        String path = jo1Var.getPath();
        if (path == null) {
            path = "/";
        }
        if (path.endsWith("/")) {
            return path;
        }
        return path + '/';
    }

    @Override // java.util.Comparator
    public int compare(jo1 jo1Var, jo1 jo1Var2) {
        String strNormalizePath = normalizePath(jo1Var);
        String strNormalizePath2 = normalizePath(jo1Var2);
        if (strNormalizePath.equals(strNormalizePath2)) {
            return 0;
        }
        if (strNormalizePath.startsWith(strNormalizePath2)) {
            return -1;
        }
        return strNormalizePath2.startsWith(strNormalizePath) ? 1 : 0;
    }
}
