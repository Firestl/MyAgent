package androidtranscoder.format;

import android.media.MediaFormat;

/* JADX INFO: loaded from: classes.dex */
public interface MediaFormatStrategy {
    MediaFormat createAudioOutputFormat(MediaFormat mediaFormat);

    MediaFormat createVideoOutputFormat(MediaFormat mediaFormat);
}
