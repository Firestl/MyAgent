package com.sangfor.sdk.base.authdevice;

import java.io.Serializable;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class TrustedDeviceList implements Serializable {
    public String bindTime;
    public String bindType;
    public String bindUser;
    public String createdAt;
    public String deviceName;
    public String deviceType;
    public String domain;
    public String id;
    public String mac;
    public String os;
    public String updatedAt;
    public String userDirectoryId;
    public String userDirectoryName;
    public String userId;

    public String getBindTime() {
        return this.bindTime;
    }

    public String getBindType() {
        return this.bindType;
    }

    public String getBindUser() {
        return this.bindUser;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getId() {
        return this.id;
    }

    public String getMac() {
        return this.mac;
    }

    public String getOs() {
        return this.os;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public String getUserDirectoryId() {
        return this.userDirectoryId;
    }

    public String getUserDirectoryName() {
        return this.userDirectoryName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setBindTime(String str) {
        this.bindTime = str;
    }

    public void setBindType(String str) {
        this.bindType = str;
    }

    public void setBindUser(String str) {
        this.bindUser = str;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public void setUserDirectoryId(String str) {
        this.userDirectoryId = str;
    }

    public void setUserDirectoryName(String str) {
        this.userDirectoryName = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
