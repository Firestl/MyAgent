package supwisdom;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class xp1 {
    public static Application b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SharedPreferences f9798a;

    public xp1() {
        if (a() == null) {
            this.f9798a = PreferenceManager.getDefaultSharedPreferences(b);
        } else {
            this.f9798a = b.getSharedPreferences(a(), 0);
        }
    }

    public abstract String a();

    public void a(String str, boolean z) {
        SharedPreferences.Editor editorEdit = this.f9798a.edit();
        editorEdit.putBoolean(str, z);
        synchronized (zp1.class) {
            if (Build.VERSION.SDK_INT <= 17) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        }
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.f9798a.edit();
        editorEdit.putString(str, str2);
        synchronized (zp1.class) {
            if (Build.VERSION.SDK_INT <= 17) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        }
    }
}
