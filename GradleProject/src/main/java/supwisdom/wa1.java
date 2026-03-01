package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class wa1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<String> f9607a;
    public Set<String> b = new HashSet();
    public Intent c;

    public wa1(Context context, int i, Set<String> set) {
        context.getPackageManager();
        context.getPackageName();
        this.f9607a = new HashSet();
        HashSet hashSet = new HashSet(8);
        this.b.add("com.sangfor.vpn.client.awork.std");
        this.b.add("com.sangfor.vpn.client.awork");
        this.f9607a.addAll(this.b);
        this.f9607a.addAll(set);
        hashSet.add("com.sangfor.vpn.client.awork.std");
        hashSet.add("com.sangfor.vpn.client.awork");
        hashSet.add("com.sangfor.vpn.client.phone");
        hashSet.add("com.sangfor.vpn.client.tablet");
        hashSet.add("com.android.systemui");
        hashSet.add("com.miui.securitycenter");
        try {
            this.c = VpnService.prepare(context);
        } catch (Exception unused) {
        }
    }
}
