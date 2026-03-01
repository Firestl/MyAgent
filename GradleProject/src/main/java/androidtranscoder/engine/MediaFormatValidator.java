package androidtranscoder.engine;

import android.media.MediaFormat;

/* JADX INFO: loaded from: classes.dex */
public class MediaFormatValidator {
    public static void validateAudioOutputFormat(MediaFormat mediaFormat) {
        String string = mediaFormat.getString("mime");
        if ("audio/mp4a-latm".equals(string)) {
            return;
        }
        throw new InvalidOutputFormatException("Audio codecs other than AAC is not supported, actual mime type: " + string);
    }

    public static void validateVideoOutputFormat(MediaFormat mediaFormat) {
        String string = mediaFormat.getString("mime");
        if ("video/avc".equals(string)) {
            return;
        }
        throw new InvalidOutputFormatException("Video codecs other than AVC is not supported, actual mime type: " + string);
    }
}
