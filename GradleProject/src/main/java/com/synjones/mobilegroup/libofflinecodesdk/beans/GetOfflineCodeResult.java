package com.synjones.mobilegroup.libofflinecodesdk.beans;

/* JADX INFO: loaded from: classes2.dex */
public class GetOfflineCodeResult {
    public String barCode;
    public String code;
    public String msg;
    public String qrCode;
    public boolean success;

    public GetOfflineCodeResult(boolean z, String str, String str2, String str3, String str4) {
        this.success = z;
        this.code = str;
        this.msg = str2;
        this.barCode = str3;
        this.qrCode = str4;
    }
}
