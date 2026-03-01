package androidtranscoder.engine;

import android.media.MediaFormat;

/* JADX INFO: loaded from: classes.dex */
public interface TrackTranscoder {
    MediaFormat getDeterminedFormat();

    long getWrittenPresentationTimeUs();

    boolean isFinished();

    void release();

    void setup();

    boolean stepPipeline();
}
