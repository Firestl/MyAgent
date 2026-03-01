package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class QueryPayStatusResult extends JsonData {
    public String authcode;
    public String journo;
    public String status;
    public String txamt;

    public String getAuthcode() {
        return this.authcode;
    }

    public String getJourno() {
        return this.journo;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTxamt() {
        return this.txamt;
    }

    public void setAuthcode(String str) {
        this.authcode = str;
    }

    public void setJourno(String str) {
        this.journo = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTxamt(String str) {
        this.txamt = str;
    }
}
