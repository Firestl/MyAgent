package androidx.core.os;

import supwisdom.ia;

/* JADX INFO: loaded from: classes.dex */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this(null);
    }

    public OperationCanceledException(String str) {
        super(ia.a((Object) str, "The operation has been canceled."));
    }
}
