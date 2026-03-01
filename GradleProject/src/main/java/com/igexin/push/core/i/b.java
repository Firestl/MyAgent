package com.igexin.push.core.i;

import android.content.Intent;
import com.igexin.push.core.e;
import com.igexin.sdk.PushActivity;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public static b b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<Long, a> f3483a = new HashMap();

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    private void b(a aVar) {
        if (aVar != null) {
            if (aVar != null) {
                this.f3483a.put(aVar.a(), aVar);
            }
            Intent intent = new Intent(e.l, (Class<?>) PushActivity.class);
            intent.putExtra("activityid", aVar.a());
            intent.setFlags(268435456);
            e.l.startActivity(intent);
        }
    }

    private void c(a aVar) {
        if (aVar != null) {
            a(aVar);
        }
    }

    private void d(a aVar) {
        if (aVar != null) {
            this.f3483a.put(aVar.a(), aVar);
        }
    }

    public final a a(Long l) {
        return this.f3483a.get(l);
    }

    public final void a(a aVar) {
        if (aVar != null) {
            this.f3483a.remove(aVar.a());
        }
    }
}
