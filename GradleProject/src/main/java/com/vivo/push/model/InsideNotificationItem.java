package com.vivo.push.model;

/* JADX INFO: loaded from: classes2.dex */
public class InsideNotificationItem extends UPSNotificationMessage {
    public int mAppType;
    public int mInnerPriority;
    public boolean mIsShowBigPicOnMobileNet;
    public int mMessageType;
    public String mReactPackage;
    public String mSuitReactVersion;

    public int getAppType() {
        return this.mAppType;
    }

    public int getInnerPriority() {
        return this.mInnerPriority;
    }

    public int getMessageType() {
        return this.mMessageType;
    }

    public String getReactPackage() {
        return this.mReactPackage;
    }

    public String getSuitReactVersion() {
        return this.mSuitReactVersion;
    }

    public boolean isShowBigPicOnMobileNet() {
        return this.mIsShowBigPicOnMobileNet;
    }

    public void setAppType(int i) {
        this.mAppType = i;
    }

    public void setInnerPriority(int i) {
        this.mInnerPriority = i;
    }

    public void setIsShowBigPicOnMobileNet(boolean z) {
        this.mIsShowBigPicOnMobileNet = z;
    }

    public void setMessageType(int i) {
        this.mMessageType = i;
    }

    public void setReactPackage(String str) {
        this.mReactPackage = str;
    }

    public void setSuitReactVersion(String str) {
        this.mSuitReactVersion = str;
    }
}
