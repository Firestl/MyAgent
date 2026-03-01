package supwisdom;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class kp extends ip {
    @Override // supwisdom.ip
    public JSONObject a() throws JSONException {
        return ip.a("sdkConfig", "obtain");
    }

    @Override // supwisdom.ip
    public String b() {
        return "5.0.0";
    }
}
