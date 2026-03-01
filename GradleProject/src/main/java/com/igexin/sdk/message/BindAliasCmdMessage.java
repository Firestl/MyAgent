package com.igexin.sdk.message;

/* JADX INFO: loaded from: classes2.dex */
public class BindAliasCmdMessage extends GTCmdMessage {
    public String code;
    public String sn;

    public BindAliasCmdMessage() {
    }

    public BindAliasCmdMessage(String str, String str2, int i) {
        super(i);
        this.sn = str;
        this.code = str2;
    }

    public String getCode() {
        return this.code;
    }

    public String getSn() {
        return this.sn;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setSn(String str) {
        this.sn = str;
    }
}
