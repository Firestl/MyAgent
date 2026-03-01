package com.newcapec.virtualcard.entity;

import com.alibaba.fastjson.JSON;

/* JADX INFO: loaded from: classes2.dex */
public class V8SGetKeyResult extends JsonData {
    public String schoolprvkey;
    public String schoolpubkey;

    public String getSchoolprvkey() {
        return this.schoolprvkey;
    }

    public String getSchoolpubkey() {
        return this.schoolpubkey;
    }

    public void setSchoolprvkey(String str) {
        this.schoolprvkey = str;
    }

    public void setSchoolpubkey(String str) {
        this.schoolpubkey = str;
    }

    @Override // com.newcapec.virtualcard.entity.JsonData
    public String toString() {
        return JSON.toJSONString(this);
    }
}
