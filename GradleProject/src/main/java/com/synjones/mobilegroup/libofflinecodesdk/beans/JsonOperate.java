package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
public class JsonOperate {
    public static <T> T fromJson(String str, Type type) {
        return (T) new Gson().fromJson(str, type);
    }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }
}
