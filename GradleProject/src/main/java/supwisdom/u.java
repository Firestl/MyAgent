package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;
import com.newcapec.virtualcard.R;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SharedPreferences f9343a;

    public u(Context context, String str) {
        this.f9343a = context.getSharedPreferences(str, 0);
    }

    public String a(String str, String str2) {
        String string = this.f9343a.getString(str, str2);
        try {
            return hw0.a(string, b0.a(R.string.sm4_key));
        } catch (Exception e2) {
            e2.printStackTrace();
            return string;
        }
    }

    public boolean a(String str, boolean z) {
        return this.f9343a.getBoolean(str, z);
    }

    public void b(String str, String str2) {
        try {
            str2 = hw0.b(str2, b0.a(R.string.sm4_key));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f9343a.edit().putString(str, str2).commit();
    }
}
