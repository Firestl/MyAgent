package io.dcloud.feature.uniapp.utils;

import com.umeng.commonsdk.internal.utils.f;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;

/* JADX INFO: loaded from: classes3.dex */
public enum UniLogLevel implements AbsLogLevel {
    OFF("off", 8, 7),
    WTF("wtf", 7, 7),
    TLOG("tlog", 6, 6),
    ERROR("error", 5, 6),
    WARN("warn", 4, 5),
    INFO(f.f5404a, 3, 4),
    DEBUG("debug", 2, 3),
    VERBOSE("verbose", 1, 2),
    ALL(ProviderConfigurationPermission.ALL_STR, 0, 2);

    public String name;
    public int priority;
    public int value;

    UniLogLevel(String str, int i, int i2) {
        this.name = str;
        this.value = i;
        this.priority = i2;
    }

    @Override // io.dcloud.feature.uniapp.utils.AbsLogLevel
    public int compare(AbsLogLevel absLogLevel) {
        return this.value - absLogLevel.getValue();
    }

    @Override // io.dcloud.feature.uniapp.utils.AbsLogLevel
    public String getName() {
        return this.name;
    }

    @Override // io.dcloud.feature.uniapp.utils.AbsLogLevel
    public int getPriority() {
        return this.priority;
    }

    @Override // io.dcloud.feature.uniapp.utils.AbsLogLevel
    public int getValue() {
        return this.value;
    }
}
