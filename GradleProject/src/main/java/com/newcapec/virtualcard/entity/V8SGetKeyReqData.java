package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class V8SGetKeyReqData extends JsonData {
    public String randomno;
    public String schoolcode;

    public String getRandomno() {
        return this.randomno;
    }

    public String getSchoolcode() {
        return this.schoolcode;
    }

    public void setRandomNo(String str) {
        this.randomno = str;
    }

    public void setSchoolcode(String str) {
        this.schoolcode = str;
    }
}
