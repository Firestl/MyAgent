package com.dcloud.android.downloader.exception;

/* JADX INFO: loaded from: classes.dex */
public class DownloadPauseException extends DownloadException {
    public DownloadPauseException(int i) {
        super(i);
    }

    public DownloadPauseException(int i, String str) {
        super(i, str);
    }

    public DownloadPauseException(int i, String str, Throwable th) {
        super(i, str, th);
    }

    public DownloadPauseException(int i, Throwable th) {
        super(i, th);
    }
}
