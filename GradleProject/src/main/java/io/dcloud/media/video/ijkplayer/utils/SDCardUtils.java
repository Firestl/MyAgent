package io.dcloud.media.video.ijkplayer.utils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class SDCardUtils {
    public static long getFreeSpaceBytes(String str) {
        StatFs statFs = new StatFs(str);
        return Build.VERSION.SDK_INT >= 18 ? statFs.getAvailableBytes() : ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static File getRootDirectory() {
        if (isAvailable()) {
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    public static String getRootPath() {
        File rootDirectory = getRootDirectory();
        if (rootDirectory != null) {
            return rootDirectory.getPath();
        }
        return null;
    }

    public static String getSDPath() {
        return (Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : null).toString();
    }

    public static String getState() {
        return Environment.getExternalStorageState();
    }

    public static boolean isAvailable() {
        return getState().equals("mounted");
    }
}
