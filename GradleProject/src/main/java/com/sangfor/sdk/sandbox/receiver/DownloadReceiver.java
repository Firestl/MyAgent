package com.sangfor.sdk.sandbox.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import com.sangfor.sdk.sandbox.business.file.jni.CryptoFilesManager;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import supwisdom.t91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class DownloadReceiver extends BroadcastReceiver {
    public final void a(List<String> list) {
        CryptoFilesManager.a();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            try {
                URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                t91.d("CryptoFilesManager", "Url decord pathname " + str + " failed!");
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.DOWNLOAD_COMPLETE")) {
            t91.a("CryptoFilesManager", "Receiver download complete success message.");
            ArrayList arrayList = new ArrayList();
            Cursor cursorQuery = ((DownloadManager) context.getSystemService(AbsoluteConst.SPNAME_DOWNLOAD)).query(new DownloadManager.Query());
            while (cursorQuery.moveToNext()) {
                for (int i = 0; i < cursorQuery.getColumnCount(); i++) {
                    String columnName = cursorQuery.getColumnName(i);
                    if (!TextUtils.isEmpty(columnName) && columnName.equals("local_uri")) {
                        String string = cursorQuery.getString(i);
                        if (!TextUtils.isEmpty(string) && string.startsWith("file://")) {
                            arrayList.add(string.substring(7));
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                a(arrayList);
            }
        }
    }
}
