package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.bz;

/* JADX INFO: loaded from: classes2.dex */
public enum Gender implements bz {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);

    public final int value;

    Gender(int i) {
        this.value = i;
    }

    public static Gender findByValue(int i) {
        if (i == 0) {
            return MALE;
        }
        if (i == 1) {
            return FEMALE;
        }
        if (i != 2) {
            return null;
        }
        return UNKNOWN;
    }

    @Override // com.umeng.analytics.pro.bz
    public int getValue() {
        return this.value;
    }
}
