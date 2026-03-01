package io.dcloud.common.util;

import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class AppStatus {
    public static final int ACTIVE = 2;
    public static final int STOPPED = 0;
    public static HashMap<String, Integer> sMaps = new HashMap<>();

    public static int getAppStatus(String str) {
        if (sMaps.containsKey(str)) {
            return sMaps.get(str).intValue();
        }
        return 2;
    }

    public static void setAppStatus(String str, int i) {
        sMaps.put(str, Integer.valueOf(i));
    }
}
