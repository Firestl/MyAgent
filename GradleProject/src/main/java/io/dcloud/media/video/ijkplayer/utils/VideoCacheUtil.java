package io.dcloud.media.video.ijkplayer.utils;

import android.content.Context;
import android.net.Uri;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class VideoCacheUtil {
    public static final int CACHE_FOLDER_MAX_LIMIT = 104857600;
    public static final String VIDEO_CACHE_FOLDER_NAME = "videoPlayer_CacheFiles";

    public static Uri cacheHttpUriWrap(Uri uri) {
        return Uri.parse("ijkio:cache:ffio:" + uri.toString());
    }

    public static void cleanCacheIfNesscessary(Context context) {
        boolean z;
        File cacheRootFile = getCacheRootFile(context);
        if (cacheRootFile.canRead()) {
            File[] fileArrListFiles = cacheRootFile.listFiles();
            long length = 0;
            int length2 = fileArrListFiles.length;
            int i = 0;
            while (true) {
                if (i >= length2) {
                    z = false;
                    break;
                }
                File file = fileArrListFiles[i];
                if (file.isFile()) {
                    length += file.length();
                }
                if (length >= 104857600) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                for (File file2 : fileArrListFiles) {
                    file2.delete();
                }
            }
        }
    }

    public static String getCacheFilePath(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        File cacheRootFile = getCacheRootFile(context);
        return cacheRootFile.getPath() + File.separator + (String.valueOf(uri.hashCode()) + ".videoCache");
    }

    public static String getCacheMapFilePath(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        File cacheRootFile = getCacheRootFile(context);
        return cacheRootFile.getPath() + File.separator + (String.valueOf(uri.hashCode()) + ".videoCacheMap");
    }

    public static File getCacheRootFile(Context context) {
        File file = new File(context.getCacheDir().toString() + File.separator + "videoPlayer_CacheFiles");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
}
