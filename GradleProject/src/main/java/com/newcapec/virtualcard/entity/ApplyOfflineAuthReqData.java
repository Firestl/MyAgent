package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class ApplyOfflineAuthReqData extends JsonData {
    public String encryptdata;
    public String schoolcode;

    public static class ApplyOfflineAuthInnerReqData extends JsonData {
        public String devcode;
        public String idserial;
        public String userpubkey;

        public String getDevcode() {
            return this.devcode;
        }

        public String getIdserial() {
            return this.idserial;
        }

        public String getUserpubkey() {
            return this.userpubkey;
        }

        public void setDevcode(String str) {
            this.devcode = str;
        }

        public void setIdserial(String str) {
            this.idserial = str;
        }

        public void setUserpubkey(String str) {
            this.userpubkey = str;
        }
    }

    public String getEncryptdata() {
        return this.encryptdata;
    }

    public String getSchoolcode() {
        return this.schoolcode;
    }

    public void setEncryptdata(String str) {
        this.encryptdata = str;
    }

    public void setSchoolcode(String str) {
        this.schoolcode = str;
    }
}
