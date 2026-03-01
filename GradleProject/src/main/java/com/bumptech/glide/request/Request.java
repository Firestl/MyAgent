package com.bumptech.glide.request;

/* JADX INFO: loaded from: classes.dex */
public interface Request {
    void begin();

    void clear();

    boolean isCleared();

    boolean isComplete();

    boolean isEquivalentTo(Request request);

    boolean isFailed();

    boolean isResourceSet();

    boolean isRunning();

    void recycle();
}
