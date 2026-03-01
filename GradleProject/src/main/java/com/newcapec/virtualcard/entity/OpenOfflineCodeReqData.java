package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class OpenOfflineCodeReqData extends JsonData {
    public String devcode;
    public String idserial;
    public String schoolcode;
    public String txpasswd;

    public String getDevcode() {
        return this.devcode;
    }

    public String getIdserial() {
        return this.idserial;
    }

    public String getSchoolcode() {
        return this.schoolcode;
    }

    public String getTxpasswd() {
        return this.txpasswd;
    }

    public void setDevcode(String str) {
        this.devcode = str;
    }

    public void setIdserial(String str) {
        this.idserial = str;
    }

    public void setSchoolcode(String str) {
        this.schoolcode = str;
    }

    public void setTxpasswd(String str) {
        this.txpasswd = str;
    }
}
