package com.sangfor.dex;

import supwisdom.qy0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum MethodHandle$MethodHandleType {
    METHOD_HANDLE_TYPE_STATIC_PUT(0),
    METHOD_HANDLE_TYPE_STATIC_GET(1),
    METHOD_HANDLE_TYPE_INSTANCE_PUT(2),
    METHOD_HANDLE_TYPE_INSTANCE_GET(3),
    METHOD_HANDLE_TYPE_INVOKE_STATIC(4),
    METHOD_HANDLE_TYPE_INVOKE_INSTANCE(5),
    METHOD_HANDLE_TYPE_INVOKE_DIRECT(6),
    METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR(7),
    METHOD_HANDLE_TYPE_INVOKE_INTERFACE(8);

    public final int value;

    MethodHandle$MethodHandleType(int i) {
        this.value = i;
    }

    public static MethodHandle$MethodHandleType fromValue(int i) {
        for (MethodHandle$MethodHandleType methodHandle$MethodHandleType : values()) {
            if (methodHandle$MethodHandleType.value == i) {
                return methodHandle$MethodHandleType;
            }
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }

    public boolean isField() {
        int i = qy0.f8972a[ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4;
    }
}
