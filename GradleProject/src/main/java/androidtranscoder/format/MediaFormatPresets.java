package androidtranscoder.format;

import android.media.MediaFormat;
import io.dcloud.common.util.StringUtil;

/* JADX INFO: loaded from: classes.dex */
public class MediaFormatPresets {
    public static final int LONGER_LENGTH_960x540 = 960;

    @Deprecated
    public static MediaFormat getExportPreset960x540() {
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", 960, 540);
        mediaFormatCreateVideoFormat.setInteger("bitrate", 5500000);
        mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", 30);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
        return mediaFormatCreateVideoFormat;
    }

    public static MediaFormat getExportPreset960x540(int i, int i2) {
        int iMax = Math.max(i, i2);
        int iMin = Math.min(i, i2);
        int i3 = 960;
        if (iMax <= 960) {
            return null;
        }
        int i4 = iMin * 960;
        if (i4 % iMax == 0) {
            int i5 = i4 / iMax;
            if (i < i2) {
                i3 = i5;
                i5 = 960;
            }
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", i3, i5);
            mediaFormatCreateVideoFormat.setInteger("bitrate", 5500000);
            mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
            mediaFormatCreateVideoFormat.setInteger("frame-rate", 30);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
            return mediaFormatCreateVideoFormat;
        }
        throw new OutputFormatUnavailableException(StringUtil.format("Could not fit to integer, original: (%d, %d), scaled: (%d, %f)", Integer.valueOf(iMax), Integer.valueOf(iMin), 960, Double.valueOf((((double) iMin) * 960.0d) / ((double) iMax))));
    }
}
