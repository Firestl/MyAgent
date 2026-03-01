package com.lzy.okgo.exception;

/* JADX INFO: loaded from: classes2.dex */
public class StorageException extends Exception {
    public static final long serialVersionUID = 178946465;

    public StorageException() {
    }

    public static StorageException NOT_AVAILABLE() {
        return new StorageException("SDCard isn't available, please check SD card and permission: WRITE_EXTERNAL_STORAGE, and you must pay attention to Android6.0 RunTime Permissions!");
    }

    public StorageException(String str) {
        super(str);
    }

    public StorageException(String str, Throwable th) {
        super(str, th);
    }

    public StorageException(Throwable th) {
        super(th);
    }
}
