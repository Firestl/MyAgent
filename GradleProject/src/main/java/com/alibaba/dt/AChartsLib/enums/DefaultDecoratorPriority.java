package com.alibaba.dt.AChartsLib.enums;

/* JADX INFO: loaded from: classes.dex */
public enum DefaultDecoratorPriority {
    ORIGIN_DECORATOR(0, "ORIGIN"),
    GRID_DECORATOR(1, "GRID"),
    AXIS_DECORATOR(2, "AXIS"),
    DATA_DECORATOR(3, "DATA"),
    NODEVALUE_DECORATOR(4, "NODEVALUE"),
    NODECIRCLE_DECORATOR(5, "NODECIRCLE"),
    HIGHLIGHT_DECORATOR(6, "HIGHLIGHT"),
    MARKERVIEW_DECORATOR(7, "MARKERVIEW"),
    DESCRIPTION_DECORATOR(8, "DECSCRIPTION");

    public String mDecoratorTag;
    public int mPriority;

    DefaultDecoratorPriority(int i, String str) {
        this.mPriority = i;
        this.mDecoratorTag = str;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public String getTag() {
        return this.mDecoratorTag;
    }
}
