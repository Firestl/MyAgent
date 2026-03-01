package com.sangfor.sdk.auth.Sangfor_c.Sangfor_c;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public enum Sangfor_c {
    UNKNOWN(-1),
    TERMINAL_BIND(0),
    TERMINAL_APPLY(1),
    APPROVALING(2),
    APPROVAL_REJECT(3),
    BIND_SUCCESS(4),
    BIND_FAILED(5),
    TERMINAL_REMOVE(6),
    TERMINAL_EXTEND_AUTH(7);

    public final int Sangfor_k;

    Sangfor_c(int i) {
        this.Sangfor_k = i;
    }

    @Override // java.lang.Enum
    public String toString() {
        switch (this.Sangfor_k) {
            case 0:
                return "TERMINAL_BIND";
            case 1:
                return "TERMINAL_APPLY";
            case 2:
                return "APPROVALING";
            case 3:
                return "APPROVAL_REJECT";
            case 4:
                return "BIND_SUCCESS";
            case 5:
                return "BIND_FAILED";
            case 6:
                return "TERMINAL_REMOVE";
            case 7:
                return "TERMINAL_EXTEND_AUTH";
            default:
                return "UNKNOWN:" + this.Sangfor_k;
        }
    }
}
