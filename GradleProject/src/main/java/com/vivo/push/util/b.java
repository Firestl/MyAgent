package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BaseSharePreference.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f5630a;
    public String b;
    public volatile SharedPreferences c;
    public HashMap<String, String> d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashMap<String, Long> f5631e = new HashMap<>();
    public HashMap<String, Integer> f = new HashMap<>();
    public HashMap<String, Boolean> g = new HashMap<>();

    public final void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("sharedFileName can't be null");
        }
        this.b = str;
        this.c = context.getSharedPreferences(str, 0);
        this.f5630a = context;
        HashMap map = new HashMap();
        map.put("com.vivo.push.secure_sub_iv", "34,32,33,37,33,34,32,33,33,33,34,41,35,35,32,32");
        map.put("com.vivo.push.secure_sub_key", "33,34,35,36,37,38,39,40,41,32,38,37,33,35,34,33");
        map.put("com.vivo.push.secure_cache_iv", "34,32,33,37,33,34,32,33,33,33,34,41,35,32,32,32");
        map.put("com.vivo.push.secure_cache_key", "33,34,35,36,37,38,39,40,41,32,38,37,36,35,34,33");
        a(map);
    }

    public final String b(String str, String str2) {
        String string = this.d.get(str);
        if (string != null) {
            return string;
        }
        b();
        if (this.c != null) {
            string = this.c.getString(str, str2);
            if (!TextUtils.isEmpty(string) && !string.equals(str2)) {
                this.d.put(str, string);
            }
        }
        return string;
    }

    public final long b(String str, long j) {
        Long lValueOf = this.f5631e.get(str);
        if (lValueOf != null) {
            return lValueOf.longValue();
        }
        b();
        if (this.c != null) {
            lValueOf = Long.valueOf(this.c.getLong(str, j));
            if (!lValueOf.equals(Long.valueOf(j))) {
                this.f5631e.put(str, lValueOf);
            }
        }
        return lValueOf.longValue();
    }

    public final void a(String str, String str2) {
        this.d.put(str, str2);
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putString(str, str2);
            a(editorEdit);
        }
    }

    public final void b(String str) {
        this.f5631e.remove(str);
        this.f.remove(str);
        this.g.remove(str);
        this.d.remove(str);
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            if (this.c.contains(str)) {
                editorEdit.remove(str);
                a(editorEdit);
            }
        }
    }

    private void a(Map<String, String> map) {
        if (map.size() > 0) {
            b();
            if (this.c != null) {
                SharedPreferences.Editor editorEdit = this.c.edit();
                for (String str : map.keySet()) {
                    String str2 = map.get(str);
                    this.d.put(str, str2);
                    editorEdit.putString(str, str2);
                }
                a(editorEdit);
            }
        }
    }

    private void b() {
        if (this.c == null) {
            Context context = this.f5630a;
            if (context != null) {
                this.c = context.getSharedPreferences(this.b, 0);
                return;
            }
            throw new RuntimeException("SharedPreferences is not init", new Throwable());
        }
    }

    public final void a(String str, int i) {
        this.f.put(str, Integer.valueOf(i));
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putInt(str, i);
            a(editorEdit);
        }
    }

    public final void a(String str, long j) {
        this.f5631e.put(str, Long.valueOf(j));
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putLong(str, j);
            a(editorEdit);
        }
    }

    public final int a(String str) {
        Integer numValueOf = this.f.get(str);
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        b();
        if (this.c != null) {
            numValueOf = Integer.valueOf(this.c.getInt(str, 0));
            if (!numValueOf.equals(0)) {
                this.f.put(str, numValueOf);
            }
        }
        return numValueOf.intValue();
    }

    public static void a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public final void a() {
        this.f5631e.clear();
        this.f.clear();
        this.g.clear();
        this.d.clear();
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.clear();
            a(editorEdit);
        }
    }
}
