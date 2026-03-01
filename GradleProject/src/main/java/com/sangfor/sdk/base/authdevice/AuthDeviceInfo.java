package com.sangfor.sdk.base.authdevice;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class AuthDeviceInfo implements Serializable {
    public int bindNumLimit;
    public int bindStatus;
    public int curBindNum;
    public int firstApply;
    public LastApplyInfo lastApplyInfo;
    public String time;
    public String tips;
    public List<TrustedDeviceList> trustedDeviceList;

    public int getBindNumLimit() {
        return this.bindNumLimit;
    }

    public int getBindStatus() {
        return this.bindStatus;
    }

    public int getCurBindNum() {
        return this.curBindNum;
    }

    public int getFirstApply() {
        return this.firstApply;
    }

    public LastApplyInfo getLastApplyInfo() {
        return this.lastApplyInfo;
    }

    public String getTime() {
        return this.time;
    }

    public String getTips() {
        return this.tips;
    }

    public List<TrustedDeviceList> getTrustedDeviceList() {
        return this.trustedDeviceList;
    }

    public void setBindNumLimit(int i) {
        this.bindNumLimit = i;
    }

    public void setBindStatus(int i) {
        this.bindStatus = i;
    }

    public void setCurBindNum(int i) {
        this.curBindNum = i;
    }

    public void setFirstApply(int i) {
        this.firstApply = i;
    }

    public void setLastApplyInfo(LastApplyInfo lastApplyInfo) {
        this.lastApplyInfo = lastApplyInfo;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setTips(String str) {
        this.tips = str;
    }

    public void setTrustedDeviceList(List<TrustedDeviceList> list) {
        this.trustedDeviceList = list;
    }

    public String toString() {
        return "AuthDeviceInfo{firstApply=" + this.firstApply + ", bindStatus=" + this.bindStatus + ", tips='" + this.tips + Operators.SINGLE_QUOTE + ", curBindNum=" + this.curBindNum + ", time='" + this.time + Operators.SINGLE_QUOTE + ", bindNumLimit=" + this.bindNumLimit + ", lastApplyInfo=" + this.lastApplyInfo + ", trustedDeviceList=" + this.trustedDeviceList + Operators.BLOCK_END;
    }
}
