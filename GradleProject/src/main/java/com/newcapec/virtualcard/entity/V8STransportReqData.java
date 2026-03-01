package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class V8STransportReqData extends JsonData {
    public String command = "TSM01012";
    public String customerCode;
    public String data;
    public String service;
    public String sessionId;

    public String getCommand() {
        return this.command;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public String getData() {
        return this.data;
    }

    public String getService() {
        return this.service;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public void setCustomerCode(String str) {
        this.customerCode = str;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setService(String str) {
        this.service = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }
}
