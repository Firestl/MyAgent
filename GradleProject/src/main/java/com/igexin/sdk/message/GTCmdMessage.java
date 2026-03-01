package com.igexin.sdk.message;

/* JADX INFO: loaded from: classes2.dex */
public class GTCmdMessage extends BaseMessage {
    public int action;

    public GTCmdMessage() {
    }

    public GTCmdMessage(int i) {
        this.action = i;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int i) {
        this.action = i;
    }
}
