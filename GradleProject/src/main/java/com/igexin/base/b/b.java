package com.igexin.base.b;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3146a;
    public SharedPreferences b;

    public b(Context context, String str) {
        this.f3146a = context;
        this.b = context.getSharedPreferences(str, 0);
    }

    @Override // com.igexin.base.b.a
    public final Object getParam(String str, Object obj) {
        SharedPreferences sharedPreferences = this.b;
        return sharedPreferences == null ? obj : obj instanceof String ? sharedPreferences.getString(str, (String) obj) : obj instanceof Integer ? Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue())) : obj instanceof Boolean ? Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue())) : obj instanceof Float ? Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue())) : obj instanceof Long ? Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue())) : obj;
    }

    @Override // com.igexin.base.b.a
    public final boolean remove(String str) {
        SharedPreferences.Editor editorEdit = this.b.edit();
        editorEdit.remove(str);
        editorEdit.commit();
        return false;
    }

    @Override // com.igexin.base.b.a
    public final boolean saveParam(String str, Object obj) {
        if (obj == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = this.b.edit();
        if (obj instanceof String) {
            editorEdit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        }
        return editorEdit.commit();
    }
}
