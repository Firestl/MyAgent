package supwisdom;

import java.util.Date;

/* JADX INFO: compiled from: Cookie.java */
/* JADX INFO: loaded from: classes2.dex */
public interface jo1 {
    String getComment();

    String getDomain();

    Date getExpiryDate();

    String getName();

    String getPath();

    String getValue();

    int getVersion();

    boolean isExpired(Date date);

    boolean isSecure();
}
