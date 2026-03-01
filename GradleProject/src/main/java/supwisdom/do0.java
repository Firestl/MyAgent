package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;

/* JADX INFO: compiled from: SharedPrefKeysetWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class do0 implements tn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SharedPreferences.Editor f7367a;
    public final String b;

    public do0(Context context, String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.b = str;
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.f7367a = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
        } else {
            this.f7367a = applicationContext.getSharedPreferences(str2, 0).edit();
        }
    }

    @Override // supwisdom.tn0
    public void a(cp0 cp0Var) throws IOException {
        if (!this.f7367a.putString(this.b, cs0.a(cp0Var.toByteArray())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    @Override // supwisdom.tn0
    public void a(po0 po0Var) throws IOException {
        if (!this.f7367a.putString(this.b, cs0.a(po0Var.toByteArray())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
