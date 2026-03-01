package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: compiled from: SharedPrefKeysetReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class co0 implements sn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SharedPreferences f7235a;
    public final String b;

    public co0(Context context, String str, String str2) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.b = str;
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.f7235a = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        } else {
            this.f7235a = applicationContext.getSharedPreferences(str2, 0);
        }
    }

    @Override // supwisdom.sn0
    public po0 a() throws IOException {
        return po0.a(b(), xp0.a());
    }

    public final byte[] b() throws IOException {
        try {
            String string = this.f7235a.getString(this.b, null);
            if (string != null) {
                return cs0.a(string);
            }
            throw new FileNotFoundException(String.format("can't read keyset; the pref value %s does not exist", this.b));
        } catch (ClassCastException | IllegalArgumentException unused) {
            throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", this.b));
        }
    }

    @Override // supwisdom.sn0
    public cp0 read() throws IOException {
        return cp0.a(b(), xp0.a());
    }
}
