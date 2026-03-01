package com.zx.sdk.api;

import android.text.TextUtils;
import com.zx.a.I8b7.y1;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ZXID implements Serializable {
    public String aids;
    public long expiredTime;
    public String openid = "";
    public String tags;
    public String value;
    public String version;

    public JSONObject getAids() {
        JSONObject jSONObject = new JSONObject();
        try {
            return !TextUtils.isEmpty(this.aids) ? new JSONObject(this.aids) : jSONObject;
        } catch (Exception e2) {
            y1.a(e2);
            return jSONObject;
        }
    }

    public long getExpiredTime() {
        return this.expiredTime;
    }

    public String getOpenid() {
        return this.openid;
    }

    public String getTags() {
        return this.tags;
    }

    public String getValue() {
        return this.value;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() / 1000 >= this.expiredTime;
    }

    public void setAids(String str) {
        this.aids = str;
    }

    public void setExpiredTime(long j) {
        this.expiredTime = j;
    }

    public void setOpenid(String str) {
        this.openid = str;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return getValue();
    }
}
