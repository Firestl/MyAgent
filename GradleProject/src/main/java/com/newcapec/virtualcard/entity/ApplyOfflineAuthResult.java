package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class ApplyOfflineAuthResult extends JsonData {
    public String authordate;
    public String authorinfo;
    public String idserial;

    public String getAuthordate() {
        return this.authordate;
    }

    public String getAuthorinfo() {
        return this.authorinfo;
    }

    public String getIdserial() {
        return this.idserial;
    }

    public void setAuthordate(String str) {
        this.authordate = str;
    }

    public void setAuthorinfo(String str) {
        this.authorinfo = str;
    }

    public void setIdserial(String str) {
        this.idserial = str;
    }
}
