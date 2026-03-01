package supwisdom;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.domain.DownloadThreadInfo;
import com.taobao.weex.ui.view.gesture.WXGesture;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class au implements cu {
    public static final String[] d = {com.umeng.analytics.pro.bq.d, "supportRanges", "createAt", "uri", "location", "path", AbsoluteConst.JSON_KEY_SIZE, AbsoluteConst.JSON_KEY_PROGRESS, "status"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f6980e = {com.umeng.analytics.pro.bq.d, "threadId", "downloadInfoId", "uri", "start", WXGesture.END, AbsoluteConst.JSON_KEY_PROGRESS};
    public static final String f = StringUtil.format("REPLACE INTO %s (_id,threadId,downloadInfoId,uri,start,end,progress) VALUES(?,?,?,?,?,?,?);", "download_thread_info");
    public static final String g = StringUtil.format("REPLACE INTO %s (_id,supportRanges,createAt,uri,location,path,size,progress,status) VALUES(?,?,?,?,?,?,?,?,?);", "download_info");
    public static final String h = StringUtil.format("UPDATE %s SET status=? WHERE status!=?;", "download_info");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6981a;
    public final SQLiteDatabase b;
    public final SQLiteDatabase c;

    public au(Context context, ut utVar) {
        this.f6981a = context;
        bu buVar = new bu(context, utVar);
        this.b = buVar.getWritableDatabase();
        this.c = buVar.getReadableDatabase();
    }

    @Override // supwisdom.cu
    public void a(DownloadInfo downloadInfo) {
        this.b.delete("download_info", "_id=?", new String[]{String.valueOf(downloadInfo.getId())});
        this.b.delete("download_thread_info", "downloadInfoId=?", new String[]{String.valueOf(downloadInfo.getId())});
    }

    @Override // supwisdom.cu
    public void b(DownloadInfo downloadInfo) {
        this.b.execSQL(g, new Object[]{Integer.valueOf(downloadInfo.getId()), Integer.valueOf(downloadInfo.getSupportRanges()), Long.valueOf(downloadInfo.getCreateAt()), downloadInfo.getUri(), downloadInfo.getLocation(), downloadInfo.getPath(), Long.valueOf(downloadInfo.getSize()), Long.valueOf(downloadInfo.getProgress()), Integer.valueOf(downloadInfo.getStatus())});
    }

    @Override // supwisdom.cu
    public void b() {
        this.b.execSQL(h, new Object[]{4, 5});
    }

    @Override // supwisdom.cu
    public List<DownloadInfo> a() {
        Cursor cursorQuery = this.c.query("download_info", d, "status!=?", new String[]{String.valueOf(5)}, null, null, "createAt desc");
        ArrayList arrayList = new ArrayList();
        while (cursorQuery.moveToNext()) {
            DownloadInfo downloadInfo = new DownloadInfo(this.f6981a);
            arrayList.add(downloadInfo);
            a(cursorQuery, downloadInfo);
            Cursor cursorQuery2 = this.c.query("download_thread_info", f6980e, "downloadInfoId=?", new String[]{String.valueOf(downloadInfo.getId())}, null, null, null);
            ArrayList arrayList2 = new ArrayList();
            while (cursorQuery2.moveToNext()) {
                DownloadThreadInfo downloadThreadInfo = new DownloadThreadInfo();
                arrayList2.add(downloadThreadInfo);
                a(cursorQuery2, downloadThreadInfo);
            }
            downloadInfo.setDownloadThreadInfos(arrayList2);
        }
        return arrayList;
    }

    @Override // supwisdom.cu
    public DownloadInfo a(int i) {
        Cursor cursorQuery = this.c.query("download_info", d, "_id=?", new String[]{String.valueOf(i)}, null, null, "createAt desc");
        if (!cursorQuery.moveToNext()) {
            return null;
        }
        DownloadInfo downloadInfo = new DownloadInfo(this.f6981a);
        a(cursorQuery, downloadInfo);
        return downloadInfo;
    }

    public final void a(Cursor cursor, DownloadInfo downloadInfo) {
        downloadInfo.setId(cursor.getInt(0));
        downloadInfo.setSupportRanges(cursor.getInt(1));
        downloadInfo.setCreateAt(cursor.getLong(2));
        downloadInfo.setUri(cursor.getString(3));
        downloadInfo.setLocation(cursor.getString(4));
        downloadInfo.setPath(cursor.getString(5));
        downloadInfo.setSize(cursor.getLong(6));
        downloadInfo.setProgress(cursor.getLong(7));
        downloadInfo.setStatus(cursor.getInt(8));
    }

    public final void a(Cursor cursor, DownloadThreadInfo downloadThreadInfo) {
        downloadThreadInfo.setId(cursor.getInt(0));
        downloadThreadInfo.setThreadId(cursor.getInt(1));
        downloadThreadInfo.setDownloadInfoId(cursor.getInt(2));
        downloadThreadInfo.setUri(cursor.getString(3));
        downloadThreadInfo.setStart(cursor.getLong(4));
        downloadThreadInfo.setEnd(cursor.getLong(5));
        downloadThreadInfo.setProgress(cursor.getLong(6));
    }

    @Override // supwisdom.cu
    public void a(DownloadThreadInfo downloadThreadInfo) {
        this.b.execSQL(f, new Object[]{Integer.valueOf(downloadThreadInfo.getId()), Integer.valueOf(downloadThreadInfo.getThreadId()), Integer.valueOf(downloadThreadInfo.getDownloadInfoId()), downloadThreadInfo.getUri(), Long.valueOf(downloadThreadInfo.getStart()), Long.valueOf(downloadThreadInfo.getEnd()), Long.valueOf(downloadThreadInfo.getProgress())});
    }
}
