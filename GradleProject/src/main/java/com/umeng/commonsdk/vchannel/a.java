package com.umeng.commonsdk.vchannel;

import cn.com.chinatelecom.account.api.a.d;

/* JADX INFO: compiled from: Constant.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5487a = "https://pslog.umeng.com";
    public static String b = "https://pslog.umeng.com/";
    public static String c = "explog";
    public static final String d = "analytics";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f5488e = "ekv";
    public static final String f = "id";
    public static final String g = "ts";
    public static final String h = "ds";
    public static final String i = "pn";
    public static String j = "";

    static {
        String str = "SUB" + System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(String.format("%0" + (32 - str.length()) + d.f1473a, 0));
        j = sb.toString();
    }
}
