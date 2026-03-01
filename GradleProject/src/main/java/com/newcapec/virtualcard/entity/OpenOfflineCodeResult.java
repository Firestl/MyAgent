package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class OpenOfflineCodeResult extends JsonData {
    public int balance;
    public String departname;
    public String effectdate;
    public String grade;
    public String idserial;
    public String lasttxdate;
    public String pname;
    public String username;

    public int getBalance() {
        return this.balance;
    }

    public String getDepartname() {
        return this.departname;
    }

    public String getEffectdate() {
        return this.effectdate;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getIdserial() {
        return this.idserial;
    }

    public String getLasttxdate() {
        return this.lasttxdate;
    }

    public String getPname() {
        return this.pname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setBalance(int i) {
        this.balance = i;
    }

    public void setDepartname(String str) {
        this.departname = str;
    }

    public void setEffectdate(String str) {
        this.effectdate = str;
    }

    public void setGrade(String str) {
        this.grade = str;
    }

    public void setIdserial(String str) {
        this.idserial = str;
    }

    public void setLasttxdate(String str) {
        this.lasttxdate = str;
    }

    public void setPname(String str) {
        this.pname = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
