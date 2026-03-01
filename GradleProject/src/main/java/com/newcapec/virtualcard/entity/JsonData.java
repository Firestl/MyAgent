package com.newcapec.virtualcard.entity;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonData implements Serializable {
    public String toString() {
        return JSON.toJSONString(this);
    }
}
