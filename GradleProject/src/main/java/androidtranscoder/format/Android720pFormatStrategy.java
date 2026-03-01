package androidtranscoder.format;

import android.media.MediaFormat;

/* JADX INFO: loaded from: classes.dex */
public class Android720pFormatStrategy implements MediaFormatStrategy {
    public static final int AUDIO_BITRATE_AS_IS = -1;
    public static final int AUDIO_CHANNELS_AS_IS = -1;
    public static final int DEFAULT_VIDEO_BITRATE = 8000000;
    public static final int LONGER_LENGTH = 1280;
    public static final int SHORTER_LENGTH = 720;
    public static final String TAG = "720pFormatStrategy";
    public int compressLevel;
    public final int mAudioBitrate = 128000;
    public final int mAudioChannels = 1;
    public int mVideoBitrate;
    public double resolution;

    public Android720pFormatStrategy(int i, double d) {
        this.compressLevel = 1;
        this.resolution = 1.0d;
        this.compressLevel = i;
        this.resolution = d;
    }

    @Override // androidtranscoder.format.MediaFormatStrategy
    public MediaFormat createAudioOutputFormat(MediaFormat mediaFormat) {
        if (this.mAudioBitrate == -1 || this.mAudioChannels == -1) {
            return null;
        }
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", mediaFormat.getInteger("sample-rate"), this.mAudioChannels);
        mediaFormatCreateAudioFormat.setInteger("aac-profile", 2);
        mediaFormatCreateAudioFormat.setInteger("bitrate", this.mAudioBitrate);
        return mediaFormatCreateAudioFormat;
    }

    @Override // androidtranscoder.format.MediaFormatStrategy
    public MediaFormat createVideoOutputFormat(MediaFormat mediaFormat) {
        int i;
        int i2;
        double d;
        double d2;
        int integer = mediaFormat.getInteger("width");
        int integer2 = mediaFormat.getInteger("height");
        double d3 = this.resolution;
        if (d3 == 1.0d) {
            int i3 = this.compressLevel;
            if (i3 == 1) {
                d = integer;
                d2 = 0.8d;
            } else if (i3 == 2) {
                d = integer;
                d2 = 0.5d;
            } else {
                d = integer;
                d2 = 0.3d;
            }
            i = (int) (d * d2);
            i2 = (int) (((double) integer2) * d2);
        } else {
            i = (int) (((double) integer) * d3);
            i2 = (int) (((double) integer2) * d3);
        }
        if (i % 2 > 0) {
            i++;
        }
        if (i2 % 2 > 0) {
            i2++;
        }
        this.mVideoBitrate = i * i2;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        mediaFormatCreateVideoFormat.setInteger("bitrate", this.mVideoBitrate);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", 25);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 3);
        mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
        return mediaFormatCreateVideoFormat;
    }
}
