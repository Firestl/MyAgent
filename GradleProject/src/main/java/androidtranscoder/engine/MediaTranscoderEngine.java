package androidtranscoder.engine;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.os.Build;
import android.util.Log;
import androidtranscoder.engine.QueuedMuxer;
import androidtranscoder.format.MediaFormatStrategy;
import androidtranscoder.utils.ISO6709LocationParser;
import androidtranscoder.utils.MediaExtractorUtils;
import java.io.FileDescriptor;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class MediaTranscoderEngine {
    public static final long PROGRESS_INTERVAL_STEPS = 10;
    public static final double PROGRESS_UNKNOWN = -1.0d;
    public static final long SLEEP_TO_WAIT_TRACK_TRANSCODERS = 10;
    public static final String TAG = "MediaTranscoderEngine";
    public TrackTranscoder mAudioTrackTranscoder;
    public long mDurationUs;
    public MediaExtractor mExtractor;
    public FileDescriptor mInputFileDescriptor;
    public MediaMuxer mMuxer;
    public volatile double mProgress;
    public ProgressCallback mProgressCallback;
    public TrackTranscoder mVideoTrackTranscoder;

    public interface ProgressCallback {
        void onProgress(double d);
    }

    private void runPipelines() throws InterruptedException {
        if (this.mDurationUs <= 0) {
            this.mProgress = -1.0d;
            ProgressCallback progressCallback = this.mProgressCallback;
            if (progressCallback != null) {
                progressCallback.onProgress(-1.0d);
            }
        }
        long j = 0;
        while (true) {
            if (this.mVideoTrackTranscoder.isFinished() && this.mAudioTrackTranscoder.isFinished()) {
                return;
            }
            boolean z = this.mVideoTrackTranscoder.stepPipeline() || this.mAudioTrackTranscoder.stepPipeline();
            j++;
            if (this.mDurationUs > 0 && j % 10 == 0) {
                double dMin = ((this.mVideoTrackTranscoder.isFinished() ? 1.0d : Math.min(1.0d, this.mVideoTrackTranscoder.getWrittenPresentationTimeUs() / this.mDurationUs)) + (this.mAudioTrackTranscoder.isFinished() ? 1.0d : Math.min(1.0d, this.mAudioTrackTranscoder.getWrittenPresentationTimeUs() / this.mDurationUs))) / 2.0d;
                this.mProgress = dMin;
                ProgressCallback progressCallback2 = this.mProgressCallback;
                if (progressCallback2 != null) {
                    progressCallback2.onProgress(dMin);
                }
            }
            if (!z) {
                Thread.sleep(10L);
            }
        }
    }

    private void setupMetadata() throws IOException {
        String strExtractMetadata;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.mInputFileDescriptor);
        try {
            this.mMuxer.setOrientationHint(Integer.parseInt(mediaMetadataRetriever.extractMetadata(24)));
        } catch (NumberFormatException unused) {
        }
        if (Build.VERSION.SDK_INT >= 19 && (strExtractMetadata = mediaMetadataRetriever.extractMetadata(23)) != null) {
            float[] fArr = new ISO6709LocationParser().parse(strExtractMetadata);
            if (fArr != null) {
                this.mMuxer.setLocation(fArr[0], fArr[1]);
            } else {
                Log.d(TAG, "Failed to parse the location metadata: " + strExtractMetadata);
            }
        }
        try {
            this.mDurationUs = Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) * 1000;
        } catch (NumberFormatException unused2) {
            this.mDurationUs = -1L;
        }
        Log.d(TAG, "Duration (us): " + this.mDurationUs);
    }

    private void setupTrackTranscoders(MediaFormatStrategy mediaFormatStrategy) {
        MediaExtractorUtils.TrackResult firstVideoAndAudioTrack = MediaExtractorUtils.getFirstVideoAndAudioTrack(this.mExtractor);
        MediaFormat mediaFormatCreateVideoOutputFormat = mediaFormatStrategy.createVideoOutputFormat(firstVideoAndAudioTrack.mVideoTrackFormat);
        MediaFormat mediaFormatCreateAudioOutputFormat = mediaFormatStrategy.createAudioOutputFormat(firstVideoAndAudioTrack.mAudioTrackFormat);
        if (mediaFormatCreateVideoOutputFormat == null && mediaFormatCreateAudioOutputFormat == null) {
            throw new InvalidOutputFormatException("MediaFormatStrategy returned pass-through for both video and audio. No transcoding is necessary.");
        }
        QueuedMuxer queuedMuxer = new QueuedMuxer(this.mMuxer, new QueuedMuxer.Listener() { // from class: androidtranscoder.engine.MediaTranscoderEngine.1
            @Override // androidtranscoder.engine.QueuedMuxer.Listener
            public void onDetermineOutputFormat() {
                MediaFormatValidator.validateVideoOutputFormat(MediaTranscoderEngine.this.mVideoTrackTranscoder.getDeterminedFormat());
                MediaFormatValidator.validateAudioOutputFormat(MediaTranscoderEngine.this.mAudioTrackTranscoder.getDeterminedFormat());
            }
        });
        if (mediaFormatCreateVideoOutputFormat == null) {
            this.mVideoTrackTranscoder = new PassThroughTrackTranscoder(this.mExtractor, firstVideoAndAudioTrack.mVideoTrackIndex, queuedMuxer, QueuedMuxer.SampleType.VIDEO);
        } else {
            this.mVideoTrackTranscoder = new VideoTrackTranscoder(this.mExtractor, firstVideoAndAudioTrack.mVideoTrackIndex, mediaFormatCreateVideoOutputFormat, queuedMuxer);
        }
        this.mVideoTrackTranscoder.setup();
        PassThroughTrackTranscoder passThroughTrackTranscoder = new PassThroughTrackTranscoder(this.mExtractor, firstVideoAndAudioTrack.mAudioTrackIndex, queuedMuxer, QueuedMuxer.SampleType.AUDIO);
        this.mAudioTrackTranscoder = passThroughTrackTranscoder;
        passThroughTrackTranscoder.setup();
        this.mExtractor.selectTrack(firstVideoAndAudioTrack.mVideoTrackIndex);
        this.mExtractor.selectTrack(firstVideoAndAudioTrack.mAudioTrackIndex);
    }

    public double getProgress() {
        return this.mProgress;
    }

    public ProgressCallback getProgressCallback() {
        return this.mProgressCallback;
    }

    public void setDataSource(FileDescriptor fileDescriptor) {
        this.mInputFileDescriptor = fileDescriptor;
    }

    public void setProgressCallback(ProgressCallback progressCallback) {
        this.mProgressCallback = progressCallback;
    }

    public void transcodeVideo(String str, MediaFormatStrategy mediaFormatStrategy) throws InterruptedException, IOException {
        if (str == null) {
            throw new NullPointerException("Output path cannot be null.");
        }
        if (this.mInputFileDescriptor == null) {
            throw new IllegalStateException("Data source is not set.");
        }
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            this.mExtractor = mediaExtractor;
            mediaExtractor.setDataSource(this.mInputFileDescriptor);
            this.mMuxer = new MediaMuxer(str, 0);
            setupMetadata();
            setupTrackTranscoders(mediaFormatStrategy);
            runPipelines();
            this.mMuxer.stop();
            try {
                TrackTranscoder trackTranscoder = this.mVideoTrackTranscoder;
                if (trackTranscoder != null) {
                    trackTranscoder.release();
                    this.mVideoTrackTranscoder = null;
                }
                TrackTranscoder trackTranscoder2 = this.mAudioTrackTranscoder;
                if (trackTranscoder2 != null) {
                    trackTranscoder2.release();
                    this.mAudioTrackTranscoder = null;
                }
                MediaExtractor mediaExtractor2 = this.mExtractor;
                if (mediaExtractor2 != null) {
                    mediaExtractor2.release();
                    this.mExtractor = null;
                }
                try {
                    MediaMuxer mediaMuxer = this.mMuxer;
                    if (mediaMuxer != null) {
                        mediaMuxer.release();
                        this.mMuxer = null;
                    }
                } catch (RuntimeException e2) {
                    Log.e(TAG, "Failed to release muxer.", e2);
                }
            } catch (RuntimeException e3) {
                throw new Error("Could not shutdown extractor, codecs and muxer pipeline.", e3);
            }
        } catch (Throwable th) {
            try {
                TrackTranscoder trackTranscoder3 = this.mVideoTrackTranscoder;
                if (trackTranscoder3 != null) {
                    trackTranscoder3.release();
                    this.mVideoTrackTranscoder = null;
                }
                TrackTranscoder trackTranscoder4 = this.mAudioTrackTranscoder;
                if (trackTranscoder4 != null) {
                    trackTranscoder4.release();
                    this.mAudioTrackTranscoder = null;
                }
                MediaExtractor mediaExtractor3 = this.mExtractor;
                if (mediaExtractor3 != null) {
                    mediaExtractor3.release();
                    this.mExtractor = null;
                }
                try {
                    MediaMuxer mediaMuxer2 = this.mMuxer;
                    if (mediaMuxer2 != null) {
                        mediaMuxer2.release();
                        this.mMuxer = null;
                    }
                } catch (RuntimeException e4) {
                    Log.e(TAG, "Failed to release muxer.", e4);
                }
                throw th;
            } catch (RuntimeException e5) {
                throw new Error("Could not shutdown extractor, codecs and muxer pipeline.", e5);
            }
        }
    }
}
