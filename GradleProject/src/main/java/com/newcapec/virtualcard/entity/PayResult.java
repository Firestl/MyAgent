package com.newcapec.virtualcard.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PayResult implements Serializable {
    public List<PayInfoItem> itemList = new ArrayList();
    public String payLocation;
    public String payMoney;

    public static class PayInfoItem implements Serializable {
        public String name;
        public String value;

        public PayInfoItem(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }

    public List<PayInfoItem> getItemList() {
        return this.itemList;
    }

    public String getPayLocation() {
        return this.payLocation;
    }

    public String getPayMoney() {
        return this.payMoney;
    }

    public void setItemList(List<PayInfoItem> list) {
        this.itemList = list;
    }

    public void setPayLocation(String str) {
        this.payLocation = str;
    }

    public void setPayMoney(String str) {
        this.payMoney = str;
    }
}
