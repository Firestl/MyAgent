package com.baidu.idl.face.platform.common;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.utils.SoundPlayer;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class SoundPoolHelper {
    public static final String TAG = "SoundPoolHelper";
    public Context mContext;
    public FaceStatusEnum mPlaySoundStatusEnum;
    public volatile long mPlayDuration = 0;
    public volatile long mPlayTime = 0;
    public volatile boolean mIsPlaying = false;
    public volatile boolean mIsEnableSound = true;
    public HashMap<Integer, Long> mPlayDurationMap = new HashMap<>();

    public SoundPoolHelper(Context context) {
        this.mContext = context;
    }

    private long getSoundDuration(int i) {
        long jLongValue;
        if (this.mPlayDurationMap.containsKey(Integer.valueOf(i))) {
            return this.mPlayDurationMap.get(Integer.valueOf(i)).longValue();
        }
        System.currentTimeMillis();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(this.mContext, Uri.parse("android.resource://" + this.mContext.getPackageName() + "/" + i));
            jLongValue = Long.valueOf(mediaMetadataRetriever.extractMetadata(9)).longValue() + 0;
        } catch (IllegalArgumentException e2) {
            e = e2;
            jLongValue = 600;
        } catch (IllegalStateException e3) {
            e = e3;
            jLongValue = 600;
        } catch (Exception e4) {
            e = e4;
            jLongValue = 600;
        }
        try {
            this.mPlayDurationMap.put(Integer.valueOf(i), Long.valueOf(jLongValue));
            return jLongValue;
        } catch (IllegalArgumentException e5) {
            e = e5;
            e.printStackTrace();
            return jLongValue;
        } catch (IllegalStateException e6) {
            e = e6;
            e.printStackTrace();
            return jLongValue;
        } catch (Exception e7) {
            e = e7;
            e.printStackTrace();
            return jLongValue;
        }
    }

    public boolean getEnableSound() {
        return this.mIsEnableSound;
    }

    public long getPlayDuration() {
        return this.mPlayDuration;
    }

    public boolean playSound(FaceStatusEnum faceStatusEnum) {
        this.mIsPlaying = System.currentTimeMillis() - SoundPlayer.playTime < this.mPlayDuration;
        if (this.mIsPlaying || (this.mPlaySoundStatusEnum == faceStatusEnum && System.currentTimeMillis() - this.mPlayTime < FaceEnvironment.TIME_TIPS_REPEAT)) {
            return false;
        }
        this.mIsPlaying = true;
        this.mPlaySoundStatusEnum = faceStatusEnum;
        this.mPlayDuration = 0L;
        this.mPlayTime = System.currentTimeMillis();
        int soundId = FaceEnvironment.getSoundId(faceStatusEnum);
        if (soundId > 0) {
            this.mPlayDuration = getSoundDuration(soundId);
            SoundPlayer.playTime = System.currentTimeMillis();
            if (this.mIsEnableSound) {
                SoundPlayer.play(this.mContext, soundId);
            }
        }
        return this.mIsPlaying;
    }

    public void release() {
        SoundPlayer.release();
        this.mContext = null;
    }

    public void setEnableSound(boolean z) {
        this.mIsEnableSound = z;
    }
}
