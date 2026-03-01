package com.g.gysdk.a;

import com.igexin.assist.sdk.AssistPushConsts;

/* JADX INFO: loaded from: classes.dex */
public enum as {
    UNKNOWN("", "未知", 0),
    CM("CM", "中国移动", 1),
    CU("CU", "中国联通", 2),
    CT(AssistPushConsts.MSG_KEY_CONTENT, "中国电信", 3);


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1981e;
    public final String f;
    public final int g;

    as(String str, String str2, int i) {
        this.f1981e = str;
        this.f = str2;
        this.g = i;
    }

    public static as a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? UNKNOWN : CT : CU : CM;
    }
}
