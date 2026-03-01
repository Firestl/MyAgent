package io.dcloud.media.weex.weex_video.ijkplayer.utils;

import io.dcloud.common.util.StringUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public final class StringUtils {
    public StringUtils() {
        throw new AssertionError();
    }

    public static String generateTime(long j) {
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = i / 60;
        Object[] objArr = new Object[2];
        if (i3 > 99) {
            objArr[0] = Integer.valueOf(i3);
            objArr[1] = Integer.valueOf(i2);
            return StringUtil.format("%d:%02d", objArr);
        }
        objArr[0] = Integer.valueOf(i3);
        objArr[1] = Integer.valueOf(i2);
        return StringUtil.format("%02d:%02d", objArr);
    }

    public static String getCurFormatTime() {
        return new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
    }

    public static String getFormatSize(int i) {
        long j = i;
        if (j >= 0 && j < 1024) {
            return j + "Kb/s";
        }
        if (j >= 1024 && j < 1048576) {
            return Long.toString(j / 1024) + "KB/s";
        }
        if (j < 1048576 || j >= 1073741824) {
            return "";
        }
        return Long.toString(j / 1048576) + "MB/s";
    }
}
