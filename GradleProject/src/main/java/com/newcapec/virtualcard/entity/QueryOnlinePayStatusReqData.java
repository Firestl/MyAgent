package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class QueryOnlinePayStatusReqData extends JsonData {
    public String idserial;
    public String ip;
    public String paycode;

    public String getIdserial() {
        return this.idserial;
    }

    public String getIp() {
        return this.ip;
    }

    public String getPaycode() {
        return this.paycode;
    }

    public void setIdserial(String str) {
        this.idserial = str;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public void setPaycode(String str) {
        this.paycode = str;
    }
}
