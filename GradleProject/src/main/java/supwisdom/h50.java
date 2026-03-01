package supwisdom;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.util.Log;
import android.util.Pair;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: MediaCodecInfo.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public final class h50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7802a;
    public final boolean b;
    public final boolean c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MediaCodecInfo.CodecCapabilities f7803e;

    public h50(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        e80.a(str);
        this.f7802a = str;
        this.d = str2;
        this.f7803e = codecCapabilities;
        this.b = codecCapabilities != null && a(codecCapabilities);
        this.c = codecCapabilities != null && c(codecCapabilities);
    }

    public static h50 a(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return new h50(str, str2, codecCapabilities);
    }

    public static h50 d(String str) {
        return new h50(str, null, null);
    }

    @TargetApi(21)
    public boolean b(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.f7803e;
        if (codecCapabilities == null) {
            b("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            b("channelCount.aCaps");
            return false;
        }
        if (audioCapabilities.getMaxInputChannelCount() >= i) {
            return true;
        }
        b("channelCount.support, " + i);
        return false;
    }

    public final void c(String str) {
        Log.d("MediaCodecInfo", "AssumedSupport [" + str + "] [" + this.f7802a + ", " + this.d + "] [" + x80.f9719e + Operators.ARRAY_END_STR);
    }

    public static boolean c(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return x80.f9718a >= 21 && d(codecCapabilities);
    }

    @TargetApi(21)
    public static boolean d(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    public MediaCodecInfo.CodecProfileLevel[] a() {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.f7803e;
        return (codecCapabilities == null || (codecProfileLevelArr = codecCapabilities.profileLevels) == null) ? new MediaCodecInfo.CodecProfileLevel[0] : codecProfileLevelArr;
    }

    public boolean a(String str) {
        String strF;
        if (str == null || this.d == null || (strF = l80.f(str)) == null) {
            return true;
        }
        if (!this.d.equals(strF)) {
            b("codec.mime " + str + ", " + strF);
            return false;
        }
        Pair<Integer, Integer> pairA = com.google.android.exoplayer2.e.d.a(str);
        if (pairA == null) {
            return true;
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : a()) {
            if (codecProfileLevel.profile == ((Integer) pairA.first).intValue() && codecProfileLevel.level >= ((Integer) pairA.second).intValue()) {
                return true;
            }
        }
        b("codec.profileLevel, " + str + ", " + strF);
        return false;
    }

    public final void b(String str) {
        Log.d("MediaCodecInfo", "NoSupport [" + str + "] [" + this.f7802a + ", " + this.d + "] [" + x80.f9719e + Operators.ARRAY_END_STR);
    }

    @TargetApi(19)
    public static boolean b(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    @TargetApi(21)
    public boolean a(int i, int i2, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.f7803e;
        if (codecCapabilities == null) {
            b("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            b("sizeAndRate.vCaps");
            return false;
        }
        if (a(videoCapabilities, i, i2, d)) {
            return true;
        }
        if (i < i2 && a(videoCapabilities, i2, i, d)) {
            c("sizeAndRate.rotated, " + i + Constants.Name.X + i2 + Constants.Name.X + d);
            return true;
        }
        b("sizeAndRate.support, " + i + Constants.Name.X + i2 + Constants.Name.X + d);
        return false;
    }

    @TargetApi(21)
    public Point a(int i, int i2) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.f7803e;
        if (codecCapabilities == null) {
            b("align.caps");
            return null;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            b("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(x80.a(i, widthAlignment) * widthAlignment, x80.a(i2, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public boolean a(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.f7803e;
        if (codecCapabilities == null) {
            b("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            b("sampleRate.aCaps");
            return false;
        }
        if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        }
        b("sampleRate.support, " + i);
        return false;
    }

    public static boolean a(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return x80.f9718a >= 19 && b(codecCapabilities);
    }

    @TargetApi(21)
    public static boolean a(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d != -1.0d && d > 0.0d) {
            return videoCapabilities.areSizeAndRateSupported(i, i2, d);
        }
        return videoCapabilities.isSizeSupported(i, i2);
    }
}
