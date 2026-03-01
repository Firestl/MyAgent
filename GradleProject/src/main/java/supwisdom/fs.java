package supwisdom;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: JWTPayload.java */
/* JADX INFO: loaded from: classes.dex */
public class fs {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7638a;
    public final Date b;
    public final Date c;
    public final Date d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<String, cs> f7639e;

    public fs(String str, String str2, Date date, Date date2, Date date3, String str3, List<String> list, Map<String, cs> map) {
        this.f7638a = str2;
        this.b = date;
        this.c = date2;
        this.d = date3;
        this.f7639e = Collections.unmodifiableMap(map);
    }
}
