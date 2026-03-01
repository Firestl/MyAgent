package com.lzy.okgo.exception;

/* JADX INFO: loaded from: classes2.dex */
public class CacheException extends Exception {
    public static final long serialVersionUID = 845628123701073013L;

    public CacheException(String str) {
        super(str);
    }

    public static CacheException NON_AND_304(String str) {
        return new CacheException("the http response code is 304, but the cache with cacheKey = " + str + " is null or expired!");
    }

    public static CacheException NON_OR_EXPIRE(String str) {
        return new CacheException("cacheKey = " + str + " ,can't find cache by cacheKey, or cache has expired!");
    }
}
