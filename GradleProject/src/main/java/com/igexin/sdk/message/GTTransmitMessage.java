package com.igexin.sdk.message;

/* JADX INFO: loaded from: classes2.dex */
public class GTTransmitMessage extends GTPushMessage {
    public String messageId;
    public byte[] payload;
    public String payloadId;
    public String taskId;

    public GTTransmitMessage() {
    }

    public GTTransmitMessage(String str, String str2, String str3, byte[] bArr) {
        this.taskId = str;
        this.messageId = str2;
        this.payloadId = str3;
        this.payload = bArr;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public String getPayloadId() {
        return this.payloadId;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public void setPayload(byte[] bArr) {
        this.payload = bArr;
    }

    public void setPayloadId(String str) {
        this.payloadId = str;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }
}
