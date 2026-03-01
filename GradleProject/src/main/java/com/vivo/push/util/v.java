package com.vivo.push.util;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

/* JADX INFO: compiled from: SettingsCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ContentResolver f5644a;

    @Override // com.vivo.push.util.d
    public final boolean a(Context context) {
        if (!j.b()) {
            return false;
        }
        this.f5644a = context.getContentResolver();
        return true;
    }

    @Override // com.vivo.push.util.d
    public final void b(String str, String str2) {
        try {
            Settings.System.putString(this.f5644a, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            o.b("SettingsCache", "putString error by ".concat(String.valueOf(str)));
        }
    }

    @Override // com.vivo.push.util.d
    public final String a(String str, String str2) {
        try {
            return Settings.System.getString(this.f5644a, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            o.b("SettingsCache", "getString error by ".concat(String.valueOf(str)));
            return str2;
        }
    }
}
