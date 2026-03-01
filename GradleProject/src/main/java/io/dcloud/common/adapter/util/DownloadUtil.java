package io.dcloud.common.adapter.util;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.ILoadCallBack;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.LoadAppUtils;
import io.dcloud.common.util.PdrUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadUtil {
    public static DownloadUtil mInstance;
    public BroadcastReceiver download_receiver = new BroadcastReceiver() { // from class: io.dcloud.common.adapter.util.DownloadUtil.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.v(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "" + intent.getLongExtra("extra_download_id", 0L));
            try {
                DownloadUtil.this.queryDownloadStatus(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };
    public Context mContext;
    public DownloadManager mDownloader;
    public HashMap<Long, MyRequest> rs;

    public class MyRequest {
        public ILoadCallBack callback;
        public long id;
        public DownloadManager.Request request;

        public MyRequest(String str, String str2, ILoadCallBack iLoadCallBack) {
            this.request = null;
            this.callback = null;
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            this.request = request;
            request.setMimeType(str2);
            this.callback = iLoadCallBack;
        }
    }

    public DownloadUtil(Context context) {
        this.rs = null;
        this.mDownloader = (DownloadManager) context.getSystemService(AbsoluteConst.SPNAME_DOWNLOAD);
        this.rs = new HashMap<>(2);
        this.mContext = context;
        try {
            context.getApplicationContext().registerReceiver(this.download_receiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        } catch (Exception unused) {
        }
    }

    public static Intent getAPKInstallIntent(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String mimeType = PdrUtil.getMimeType(str);
        if (str.startsWith("file://")) {
            str = str.substring(7);
        }
        if (str.startsWith("content://")) {
            str = PlatformUtil.getFilePathFromContentUri(Uri.parse(str), context.getContentResolver());
            mimeType = PdrUtil.getMimeType(str);
        }
        return LoadAppUtils.getDataAndTypeIntent(context, str, mimeType);
    }

    public static DownloadUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DownloadUtil(context);
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryDownloadStatus(Context context) {
        Set<Long> setKeySet = this.rs.keySet();
        int size = setKeySet.size();
        Long[] lArr = new Long[size];
        setKeySet.toArray(lArr);
        for (int i = size - 1; i >= 0; i--) {
            long jLongValue = lArr[i].longValue();
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(jLongValue);
            Cursor cursorQuery = this.mDownloader.query(query);
            if (cursorQuery.moveToFirst()) {
                int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                if (i2 == 1) {
                    Log.v(Constants.Value.DIRECTION_DOWN, "STATUS_PENDING");
                    Log.v(Constants.Value.DIRECTION_DOWN, "STATUS_RUNNING");
                } else if (i2 == 2) {
                    Log.v(Constants.Value.DIRECTION_DOWN, "STATUS_RUNNING");
                } else if (i2 == 4) {
                    Log.v(Constants.Value.DIRECTION_DOWN, "STATUS_PAUSED");
                    Log.v(Constants.Value.DIRECTION_DOWN, "STATUS_PENDING");
                    Log.v(Constants.Value.DIRECTION_DOWN, "STATUS_RUNNING");
                } else if (i2 == 8) {
                    Log.e(Constants.Value.DIRECTION_DOWN, "下载完成");
                    String string = null;
                    try {
                        Uri uriForDownloadedFile = this.mDownloader.getUriForDownloadedFile(jLongValue);
                        string = uriForDownloadedFile.toString().startsWith("content://") ? uriForDownloadedFile.toString() : uriForDownloadedFile.getPath();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    ILoadCallBack iLoadCallBack = this.rs.remove(Long.valueOf(jLongValue)).callback;
                    if (iLoadCallBack != null) {
                        iLoadCallBack.onCallBack(0, context, string);
                    }
                } else if (i2 == 16) {
                    Log.v(Constants.Value.DIRECTION_DOWN, "STATUS_FAILED");
                    this.mDownloader.remove(jLongValue);
                    this.rs.remove(Long.valueOf(jLongValue));
                }
            }
        }
    }

    private void runCallBack(final Activity activity, final ILoadCallBack iLoadCallBack, final int i, final Object obj) {
        activity.runOnUiThread(new Runnable() { // from class: io.dcloud.common.adapter.util.DownloadUtil.2
            @Override // java.lang.Runnable
            public void run() {
                iLoadCallBack.onCallBack(i, activity, obj);
            }
        });
    }

    public void addDownlaodCallBack(long j, String str, String str2, ILoadCallBack iLoadCallBack) {
        if (this.rs.size() <= 0 || !this.rs.containsKey(Long.valueOf(j))) {
            this.rs.put(Long.valueOf(j), new MyRequest(str, str2, iLoadCallBack));
        }
    }

    public void checkDownloadStatus(Activity activity, long j, ILoadCallBack iLoadCallBack) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(j);
        Cursor cursorQuery = this.mDownloader.query(query);
        if (cursorQuery.moveToFirst()) {
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
            String string = null;
            if (i != 1 && i != 2) {
                if (i == 4) {
                    this.mDownloader.remove(j);
                } else {
                    if (i == 8) {
                        try {
                            Uri uriForDownloadedFile = this.mDownloader.getUriForDownloadedFile(j);
                            string = uriForDownloadedFile.toString().startsWith("content://") ? uriForDownloadedFile.toString() : uriForDownloadedFile.getPath();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        runCallBack(activity, iLoadCallBack, i, getAPKInstallIntent(activity, string));
                        return;
                    }
                    if (i == 16) {
                        this.mDownloader.remove(j);
                    }
                }
            }
            if (iLoadCallBack != null) {
                runCallBack(activity, iLoadCallBack, i, null);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
    }

    public long startRequest(Context context, String str, String str2, String str3, String str4, ILoadCallBack iLoadCallBack) {
        try {
            MyRequest myRequest = new MyRequest(str, str2, iLoadCallBack);
            try {
                if (!DHFile.isExist(str3)) {
                    new File(str3).mkdirs();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (str3.endsWith("/")) {
                str3 = str3.substring(0, str3.length() - 1);
            }
            if (str3.startsWith(DeviceInfo.sDeviceRootDir)) {
                str3 = str3.substring(DeviceInfo.sDeviceRootDir.length() + 1);
            }
            myRequest.request.setTitle(str4);
            myRequest.request.setDestinationInExternalPublicDir(str3, str4);
            long jEnqueue = this.mDownloader.enqueue(myRequest.request);
            myRequest.id = jEnqueue;
            this.rs.put(Long.valueOf(jEnqueue), myRequest);
            return jEnqueue;
        } catch (Exception e3) {
            e3.printStackTrace();
            iLoadCallBack.onCallBack(-1, null, null);
            return -1L;
        }
    }

    public void stop() {
        Context context = this.mContext;
        if (context != null) {
            try {
                context.getApplicationContext().unregisterReceiver(this.download_receiver);
                mInstance = null;
                this.mContext = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
