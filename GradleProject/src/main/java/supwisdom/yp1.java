package supwisdom;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ConfigSDKBean;

/* JADX INFO: loaded from: classes2.dex */
public class yp1 extends xp1 {
    public static yp1 c;

    public static yp1 e() {
        if (c == null) {
            synchronized (dq1.class) {
                if (c == null) {
                    c = new yp1();
                }
            }
        }
        return c;
    }

    @Override // supwisdom.xp1
    public String a() {
        return "app_config_sp_name";
    }

    public boolean b() {
        return this.f9798a.getBoolean("saved_initstate", false);
    }

    public ConfigSDKBean c() {
        try {
            return (ConfigSDKBean) new Gson().fromJson(this.f9798a.getString("saved_mConfigSDKBean", null), ConfigSDKBean.class);
        } catch (JsonSyntaxException unused) {
            return null;
        }
    }

    public String d() {
        return this.f9798a.getString("saved_appid", "");
    }
}
