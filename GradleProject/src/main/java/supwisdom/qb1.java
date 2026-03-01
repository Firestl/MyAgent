package supwisdom;

import android.text.TextUtils;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class qb1 extends pb1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Set<String> f8908e;
    public Set<String> f;
    public String g;
    public boolean h;
    public String i;
    public List<String> j;

    public synchronized Set<String> c() {
        return this.f;
    }

    @Override // supwisdom.pb1
    public synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        Set<String> set = this.f8908e;
        if (set != null && !set.isEmpty()) {
            sb.append("{AllowShareFromPackages = " + this.f8908e.toString());
        }
        Set<String> set2 = this.f;
        if (set2 != null && !set2.isEmpty()) {
            sb.append(" AllowShareToPackages = " + this.f.toString());
        }
        if (!TextUtils.isEmpty(this.g)) {
            sb.append(" DisAllowActivityName = " + this.g);
        }
        sb.append(" ActivityRedirectEnabled = " + this.h);
        if (!TextUtils.isEmpty(this.i)) {
            sb.append(" RedirectActivityName = " + this.i);
        }
        List<String> list = this.j;
        if (list != null && !list.isEmpty()) {
            sb.append(" NoRedirectActivities = " + this.j.toString());
        }
        return sb.toString();
    }
}
