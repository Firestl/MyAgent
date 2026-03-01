package com.lzy.okgo.cache;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import supwisdom.ew0;

/* JADX INFO: loaded from: classes2.dex */
public class CacheEntity<T> implements Serializable {
    public static final long CACHE_NEVER_EXPIRE = -1;
    public static final String DATA = "data";
    public static final String HEAD = "head";
    public static final String KEY = "key";
    public static final String LOCAL_EXPIRE = "localExpire";
    public static final long serialVersionUID = -4337711009801627866L;
    public T data;
    public boolean isExpire;
    public String key;
    public long localExpire;
    public HttpHeaders responseHeaders;

    public static <T> ContentValues getContentValues(CacheEntity<T> cacheEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", cacheEntity.getKey());
        contentValues.put(LOCAL_EXPIRE, Long.valueOf(cacheEntity.getLocalExpire()));
        contentValues.put(HEAD, ew0.a(cacheEntity.getResponseHeaders()));
        contentValues.put("data", ew0.a(cacheEntity.getData()));
        return contentValues;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> CacheEntity<T> parseCursorToBean(Cursor cursor) {
        CacheEntity<T> cacheEntity = (CacheEntity<T>) new CacheEntity();
        cacheEntity.setKey(cursor.getString(cursor.getColumnIndex("key")));
        cacheEntity.setLocalExpire(cursor.getLong(cursor.getColumnIndex(LOCAL_EXPIRE)));
        cacheEntity.setResponseHeaders((HttpHeaders) ew0.a(cursor.getBlob(cursor.getColumnIndex(HEAD))));
        cacheEntity.setData(ew0.a(cursor.getBlob(cursor.getColumnIndex("data"))));
        return cacheEntity;
    }

    public boolean checkExpire(CacheMode cacheMode, long j, long j2) {
        return cacheMode == CacheMode.DEFAULT ? getLocalExpire() < j2 : j != -1 && getLocalExpire() + j < j2;
    }

    public T getData() {
        return this.data;
    }

    public String getKey() {
        return this.key;
    }

    public long getLocalExpire() {
        return this.localExpire;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public boolean isExpire() {
        return this.isExpire;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setExpire(boolean z) {
        this.isExpire = z;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setLocalExpire(long j) {
        this.localExpire = j;
    }

    public void setResponseHeaders(HttpHeaders httpHeaders) {
        this.responseHeaders = httpHeaders;
    }

    public String toString() {
        return "CacheEntity{key='" + this.key + Operators.SINGLE_QUOTE + ", responseHeaders=" + this.responseHeaders + ", data=" + this.data + ", localExpire=" + this.localExpire + Operators.BLOCK_END;
    }
}
