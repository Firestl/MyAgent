package com.zx.a.I8b7;

import com.zx.module.base.Listener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class n0 implements Listener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Set<o0>> f6243a = new HashMap();

    public synchronized void a(String str, o0 o0Var) {
        if (!this.f6243a.containsKey(str)) {
            this.f6243a.put(str, new HashSet());
        }
        this.f6243a.get(str).add(o0Var);
    }

    @Override // com.zx.module.base.Listener
    public void onMessage(String str, String str2) {
        Set<o0> set = this.f6243a.get(str);
        if (set != null) {
            Iterator<o0> it = set.iterator();
            while (it.hasNext()) {
                it.next().a(str2);
            }
        }
    }
}
