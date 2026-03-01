package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class OnLineCodeResult extends JsonData {
    public String balance;
    public String paycode;

    public String getBalance() {
        return this.balance;
    }

    public String getPaycode() {
        return this.paycode;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public void setPaycode(String str) {
        this.paycode = str;
    }
}
