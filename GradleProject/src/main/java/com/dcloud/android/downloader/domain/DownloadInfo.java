package com.dcloud.android.downloader.domain;

import android.content.Context;
import android.text.TextUtils;
import com.dcloud.android.downloader.exception.DownloadException;
import java.io.Serializable;
import java.util.List;
import supwisdom.tt;

/* JADX INFO: loaded from: classes.dex */
public class DownloadInfo implements Serializable {
    public static final int STATUS_COMPLETED = 5;
    public static final int STATUS_DOWNLOADING = 2;
    public static final int STATUS_ERROR = 6;
    public static final int STATUS_NONE = 0;
    public static final int STATUS_PAUSED = 4;
    public static final int STATUS_PREPARE_DOWNLOAD = 1;
    public static final int STATUS_REMOVED = 7;
    public static final int STATUS_WAIT = 3;
    public Context context;
    public long createAt;
    public transient tt downloadListener;
    public List<DownloadThreadInfo> downloadThreadInfos;
    public DownloadException exception;
    public int id;
    public String location;
    public String path;
    public long progress;
    public long size;
    public int status;
    public int supportRanges;
    public Object tag;
    public String uri;

    public DownloadInfo(Context context) {
        this.context = context;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && DownloadInfo.class == obj.getClass() && this.id == ((DownloadInfo) obj).id;
    }

    public Context getContext() {
        return this.context;
    }

    public long getCreateAt() {
        return this.createAt;
    }

    public tt getDownloadListener() {
        return this.downloadListener;
    }

    public List<DownloadThreadInfo> getDownloadThreadInfos() {
        return this.downloadThreadInfos;
    }

    public String getDownloadUrl() {
        return TextUtils.isEmpty(this.location) ? getUri() : this.location;
    }

    public DownloadException getException() {
        return this.exception;
    }

    public int getId() {
        return this.id;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPath() {
        return this.path;
    }

    public long getProgress() {
        return this.progress;
    }

    public long getSize() {
        return this.size;
    }

    public int getStatus() {
        return this.status;
    }

    public int getSupportRanges() {
        return this.supportRanges;
    }

    public Object getTag() {
        return this.tag;
    }

    public String getUri() {
        return this.uri;
    }

    public int hashCode() {
        return this.id;
    }

    public boolean isPause() {
        int i = this.status;
        return i == 4 || i == 6 || i == 7;
    }

    public boolean isSupportRanges() {
        return this.supportRanges == 0;
    }

    public void setCreateAt(long j) {
        this.createAt = j;
    }

    public void setDownloadListener(tt ttVar) {
        this.downloadListener = ttVar;
    }

    public void setDownloadThreadInfos(List<DownloadThreadInfo> list) {
        this.downloadThreadInfos = list;
    }

    public void setException(DownloadException downloadException) {
        this.exception = downloadException;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setProgress(long j) {
        this.progress = j;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setSupportRanges(int i) {
        this.supportRanges = i;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public void setSupportRanges(boolean z) {
        this.supportRanges = !z ? 1 : 0;
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1778a;
        public long b = -1;
        public String c;
        public String d;

        public DownloadInfo a(Context context) {
            DownloadInfo downloadInfo = new DownloadInfo(context);
            if (TextUtils.isEmpty(this.c)) {
                throw new DownloadException(0, "uri cannot be null.");
            }
            downloadInfo.setUri(this.c);
            if (TextUtils.isEmpty(this.d)) {
                throw new DownloadException(1, "path cannot be null.");
            }
            downloadInfo.setPath(this.d);
            if (this.b == -1) {
                a(System.currentTimeMillis());
            }
            downloadInfo.setId(this.c.hashCode());
            if (TextUtils.isEmpty(this.f1778a)) {
                downloadInfo.setId(this.c.hashCode());
            }
            return downloadInfo;
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public a a(long j) {
            this.b = j;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }
    }
}
