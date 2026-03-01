package com.lzy.okgo.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.SystemClock;
import com.lzy.okgo.request.base.Request;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import supwisdom.ew0;
import supwisdom.fv0;

/* JADX INFO: loaded from: classes2.dex */
public class Progress implements Serializable {
    public static final String CURRENT_SIZE = "currentSize";
    public static final String DATE = "date";
    public static final int ERROR = 4;
    public static final String EXTRA1 = "extra1";
    public static final String EXTRA2 = "extra2";
    public static final String EXTRA3 = "extra3";
    public static final String FILE_NAME = "fileName";
    public static final String FILE_PATH = "filePath";
    public static final int FINISH = 5;
    public static final String FOLDER = "folder";
    public static final String FRACTION = "fraction";
    public static final int LOADING = 2;
    public static final int NONE = 0;
    public static final int PAUSE = 3;
    public static final String PRIORITY = "priority";
    public static final String REQUEST = "request";
    public static final String STATUS = "status";
    public static final String TAG = "tag";
    public static final String TOTAL_SIZE = "totalSize";
    public static final String URL = "url";
    public static final int WAITING = 1;
    public static final long serialVersionUID = 6353658567594109891L;
    public long currentSize;
    public Throwable exception;
    public Serializable extra1;
    public Serializable extra2;
    public Serializable extra3;
    public String fileName;
    public String filePath;
    public String folder;
    public float fraction;
    public Request<?, ? extends Request> request;
    public transient long speed;
    public int status;
    public String tag;
    public transient long tempSize;
    public String url;
    public transient long lastRefreshTime = SystemClock.elapsedRealtime();
    public long totalSize = -1;
    public int priority = 0;
    public long date = System.currentTimeMillis();
    public transient List<Long> speedBuffer = new ArrayList();

    public interface a {
        void a(Progress progress);
    }

    private long bufferSpeed(long j) {
        this.speedBuffer.add(Long.valueOf(j));
        if (this.speedBuffer.size() > 10) {
            this.speedBuffer.remove(0);
        }
        long jLongValue = 0;
        while (this.speedBuffer.iterator().hasNext()) {
            jLongValue = (long) (jLongValue + r0.next().longValue());
        }
        return jLongValue / ((long) this.speedBuffer.size());
    }

    public static ContentValues buildContentValues(Progress progress) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", progress.tag);
        contentValues.put("url", progress.url);
        contentValues.put(FOLDER, progress.folder);
        contentValues.put(FILE_PATH, progress.filePath);
        contentValues.put(FILE_NAME, progress.fileName);
        contentValues.put(FRACTION, Float.valueOf(progress.fraction));
        contentValues.put("totalSize", Long.valueOf(progress.totalSize));
        contentValues.put(CURRENT_SIZE, Long.valueOf(progress.currentSize));
        contentValues.put("status", Integer.valueOf(progress.status));
        contentValues.put("priority", Integer.valueOf(progress.priority));
        contentValues.put("date", Long.valueOf(progress.date));
        contentValues.put("request", ew0.a(progress.request));
        contentValues.put(EXTRA1, ew0.a(progress.extra1));
        contentValues.put(EXTRA2, ew0.a(progress.extra2));
        contentValues.put(EXTRA3, ew0.a(progress.extra3));
        return contentValues;
    }

    public static ContentValues buildUpdateContentValues(Progress progress) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FRACTION, Float.valueOf(progress.fraction));
        contentValues.put("totalSize", Long.valueOf(progress.totalSize));
        contentValues.put(CURRENT_SIZE, Long.valueOf(progress.currentSize));
        contentValues.put("status", Integer.valueOf(progress.status));
        contentValues.put("priority", Integer.valueOf(progress.priority));
        contentValues.put("date", Long.valueOf(progress.date));
        return contentValues;
    }

    public static Progress changeProgress(Progress progress, long j, a aVar) {
        return changeProgress(progress, j, progress.totalSize, aVar);
    }

    public static Progress parseCursorToBean(Cursor cursor) {
        Progress progress = new Progress();
        progress.tag = cursor.getString(cursor.getColumnIndex("tag"));
        progress.url = cursor.getString(cursor.getColumnIndex("url"));
        progress.folder = cursor.getString(cursor.getColumnIndex(FOLDER));
        progress.filePath = cursor.getString(cursor.getColumnIndex(FILE_PATH));
        progress.fileName = cursor.getString(cursor.getColumnIndex(FILE_NAME));
        progress.fraction = cursor.getFloat(cursor.getColumnIndex(FRACTION));
        progress.totalSize = cursor.getLong(cursor.getColumnIndex("totalSize"));
        progress.currentSize = cursor.getLong(cursor.getColumnIndex(CURRENT_SIZE));
        progress.status = cursor.getInt(cursor.getColumnIndex("status"));
        progress.priority = cursor.getInt(cursor.getColumnIndex("priority"));
        progress.date = cursor.getLong(cursor.getColumnIndex("date"));
        progress.request = (Request) ew0.a(cursor.getBlob(cursor.getColumnIndex("request")));
        progress.extra1 = (Serializable) ew0.a(cursor.getBlob(cursor.getColumnIndex(EXTRA1)));
        progress.extra2 = (Serializable) ew0.a(cursor.getBlob(cursor.getColumnIndex(EXTRA2)));
        progress.extra3 = (Serializable) ew0.a(cursor.getBlob(cursor.getColumnIndex(EXTRA3)));
        return progress;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Progress.class != obj.getClass()) {
            return false;
        }
        String str = this.tag;
        String str2 = ((Progress) obj).tag;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public void from(Progress progress) {
        this.totalSize = progress.totalSize;
        this.currentSize = progress.currentSize;
        this.fraction = progress.fraction;
        this.speed = progress.speed;
        this.lastRefreshTime = progress.lastRefreshTime;
        this.tempSize = progress.tempSize;
    }

    public int hashCode() {
        String str = this.tag;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Progress{fraction=" + this.fraction + ", totalSize=" + this.totalSize + ", currentSize=" + this.currentSize + ", speed=" + this.speed + ", status=" + this.status + ", priority=" + this.priority + ", folder=" + this.folder + ", filePath=" + this.filePath + ", fileName=" + this.fileName + ", tag=" + this.tag + ", url=" + this.url + Operators.BLOCK_END;
    }

    public static Progress changeProgress(Progress progress, long j, long j2, a aVar) {
        progress.totalSize = j2;
        progress.currentSize += j;
        progress.tempSize += j;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if ((jElapsedRealtime - progress.lastRefreshTime >= fv0.i) || progress.currentSize == j2) {
            long j3 = jElapsedRealtime - progress.lastRefreshTime;
            if (j3 == 0) {
                j3 = 1;
            }
            progress.fraction = (progress.currentSize * 1.0f) / j2;
            progress.speed = progress.bufferSpeed((progress.tempSize * 1000) / j3);
            progress.lastRefreshTime = jElapsedRealtime;
            progress.tempSize = 0L;
            if (aVar != null) {
                aVar.a(progress);
            }
        }
        return progress;
    }
}
