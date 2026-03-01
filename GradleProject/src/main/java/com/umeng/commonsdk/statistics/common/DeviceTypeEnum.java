package com.umeng.commonsdk.statistics.common;

import com.umeng.commonsdk.statistics.idtracking.g;
import com.umeng.commonsdk.statistics.idtracking.h;
import com.umeng.commonsdk.statistics.idtracking.i;

/* JADX INFO: loaded from: classes2.dex */
public enum DeviceTypeEnum {
    IMEI(g.f5442a, g.f5442a),
    OAID(i.d, i.d),
    ANDROIDID(com.umeng.commonsdk.statistics.idtracking.b.f5435a, com.umeng.commonsdk.statistics.idtracking.b.f5435a),
    MAC(h.f5443a, h.f5443a),
    SERIALNO("serial_no", "serial_no"),
    IDFA(com.umeng.commonsdk.statistics.idtracking.d.f5437a, com.umeng.commonsdk.statistics.idtracking.d.f5437a),
    DEFAULT(com.igexin.push.core.b.m, com.igexin.push.core.b.m);

    public String description;
    public String deviceIdType;

    DeviceTypeEnum(String str, String str2) {
        this.deviceIdType = str;
        this.description = str2;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDeviceIdType() {
        return this.deviceIdType;
    }
}
