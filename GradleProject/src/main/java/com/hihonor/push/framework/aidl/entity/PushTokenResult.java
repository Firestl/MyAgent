package com.hihonor.push.framework.aidl.entity;

import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.annotation.Packed;

/* JADX INFO: loaded from: classes.dex */
public class PushTokenResult implements IMessageEntity {

    @Packed
    public String pushToken = "";

    public String getPushToken() {
        return this.pushToken;
    }

    public void setPushToken(String str) {
        this.pushToken = str;
    }
}
