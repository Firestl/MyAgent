package com.alibaba.fastjson.parser;

/* JADX INFO: loaded from: classes.dex */
public enum Feature {
    AutoCloseSource,
    AllowComment,
    AllowUnQuotedFieldNames,
    AllowSingleQuotes,
    InternFieldNames,
    AllowISO8601DateFormat,
    AllowArbitraryCommas,
    UseBigDecimal,
    IgnoreNotMatch,
    SortFeidFastMatch,
    DisableASM,
    DisableCircularReferenceDetect,
    InitStringFieldAsEmpty,
    SupportArrayToBean;

    public final int mask = 1 << ordinal();

    Feature() {
    }

    public static int config(int i, Feature feature, boolean z) {
        return z ? i | feature.getMask() : i & (~feature.getMask());
    }

    public static boolean isEnabled(int i, Feature feature) {
        return (i & feature.getMask()) != 0;
    }

    public static int of(Feature[] featureArr) {
        if (featureArr == null) {
            return 0;
        }
        int mask = 0;
        for (Feature feature : featureArr) {
            mask |= feature.getMask();
        }
        return mask;
    }

    public final int getMask() {
        return this.mask;
    }
}
