package com.sangfor.sdk.base.authdevice;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class LastApplyInfo implements Serializable {
    public int applied;
    public int applyStatus;
    public String deviceName;
    public String macAddress;
    public String reason;
    public String time;
    public String userName;

    public int getApplied() {
        return this.applied;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getReason() {
        return this.reason;
    }

    public int getStatus() {
        return this.applyStatus;
    }

    public String getTime() {
        return this.time;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setApplied(int i) {
        this.applied = i;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setStatus(int i) {
        this.applyStatus = i;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String toString() {
        return "LastApplyInfo{applied=" + this.applied + ", applyStatus=" + this.applyStatus + ", time='" + this.time + Operators.SINGLE_QUOTE + ", userName='" + this.userName + Operators.SINGLE_QUOTE + ", reason='" + this.reason + Operators.SINGLE_QUOTE + ", deviceName='" + this.deviceName + Operators.SINGLE_QUOTE + ", macAddress='" + this.macAddress + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
