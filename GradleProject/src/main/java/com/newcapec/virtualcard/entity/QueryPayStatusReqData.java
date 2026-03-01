package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class QueryPayStatusReqData extends JsonData {
    public String devcode;
    public String idserial;
    public String paycode;
    public String schoolcode;

    public String getDevcode() {
        return this.devcode;
    }

    public String getIdserial() {
        return this.idserial;
    }

    public String getPaycode() {
        return this.paycode;
    }

    public String getSchoolcode() {
        return this.schoolcode;
    }

    public void setDevcode(String str) {
        this.devcode = str;
    }

    public void setIdserial(String str) {
        this.idserial = str;
    }

    public void setPaycode(String str) {
        this.paycode = str;
    }

    public void setSchoolcode(String str) {
        this.schoolcode = str;
    }
}
