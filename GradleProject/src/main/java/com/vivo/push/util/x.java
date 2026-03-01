package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SpCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5645a = "SpCache";
    public static String b = "com.vivo.push.cache";
    public SharedPreferences c;

    @Override // com.vivo.push.util.d
    public final boolean a(Context context) {
        if (this.c != null) {
            return true;
        }
        this.c = context.getSharedPreferences(b, 0);
        return true;
    }

    @Override // com.vivo.push.util.d
    public final void b(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.c.edit();
        if (editorEdit == null) {
            o.b(f5645a, "putString error by ".concat(String.valueOf(str)));
            return;
        }
        editorEdit.putString(str, str2);
        b.a(editorEdit);
        o.d(f5645a, "putString by ".concat(String.valueOf(str)));
    }

    @Override // com.vivo.push.util.d
    public final String a(String str, String str2) {
        String string = this.c.getString(str, str2);
        o.d(f5645a, "getString " + str + " is " + string);
        return string;
    }

    public final void a() {
        SharedPreferences.Editor editorEdit = this.c.edit();
        if (editorEdit != null) {
            editorEdit.clear();
            b.a(editorEdit);
        }
        o.d(f5645a, "system cache is cleared");
    }
}
