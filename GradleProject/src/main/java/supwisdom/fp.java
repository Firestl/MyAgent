package supwisdom;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class fp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7624a;
    public final String b;

    public fp(String str, String str2) {
        this.f7624a = str;
        this.b = str2;
    }

    public String a() {
        return this.f7624a;
    }

    public String b() {
        return this.b;
    }

    public JSONObject c() {
        if (TextUtils.isEmpty(this.b)) {
            return null;
        }
        try {
            return new JSONObject(this.b);
        } catch (Exception e2) {
            vp.a(e2);
            return null;
        }
    }

    public String toString() {
        return String.format("<Letter envelop=%s body=%s>", this.f7624a, this.b);
    }
}
