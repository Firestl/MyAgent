package io.dcloud.feature.audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import com.taobao.weex.common.Constants;
import com.umeng.analytics.AnalyticsConfig;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AudioPlayer extends AbsAudio implements ISysEventListener, IEventCallback {
    public IApp _app;
    public AudioManager mAudioMgr;
    public String mFunId;
    public IWebview mWebview;
    public JSONObject params;
    public int bufferPercent = 0;
    public int startTime = Integer.MIN_VALUE;
    public String mSrcPath = "";
    public float volume = 1.0f;
    public boolean autoplay = false;
    public boolean isCanMix = false;
    public boolean needPause = false;
    public AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: io.dcloud.feature.audio.AudioPlayer.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if ((i == -1 || i == -2 || i == -3) && !AudioPlayer.this.needPause) {
                AudioPlayer.this.pause();
            }
        }
    };
    public boolean isPrepared = false;
    public boolean isPlay = false;
    public boolean isCanplay = false;
    public boolean isStoped = false;
    public MediaPlayer mMediaPlayer = new MediaPlayer();
    public Map<String, String> events = new HashMap();

    public AudioPlayer(JSONObject jSONObject, IWebview iWebview) {
        this.params = jSONObject;
        this.mWebview = iWebview;
        addListener();
        this._app = iWebview.obtainFrameView().obtainApp();
        iWebview.obtainFrameView().addFrameViewListener(this);
        this._app.registerSysEventListener(this, ISysEventListener.SysEventType.onStop);
        setStyle(this.params);
    }

    private void addListener() {
        this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: io.dcloud.feature.audio.AudioPlayer.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                AudioPlayer.this.execEvents("canplay", "");
            }
        });
        this.mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() { // from class: io.dcloud.feature.audio.AudioPlayer.3
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                AudioPlayer.this.execEvents("seeked", "");
            }
        });
        this.mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() { // from class: io.dcloud.feature.audio.AudioPlayer.4
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                AudioPlayer.this.bufferPercent = i;
            }
        });
        this.mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: io.dcloud.feature.audio.AudioPlayer.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                if (i != 701) {
                    return false;
                }
                AudioPlayer.this.execEvents(IApp.ConfigProperty.CONFIG_WAITING, "");
                return false;
            }
        });
        this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: io.dcloud.feature.audio.AudioPlayer.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                int i3;
                String string;
                if (i == 1) {
                    i3 = -99;
                    string = DOMException.MSG_UNKNOWN_ERROR;
                } else if (i != 100) {
                    i3 = 0;
                    string = null;
                } else {
                    i3 = 1303;
                    string = AudioPlayer.this.mWebview.getContext().getString(R.string.dcloud_audio_abnormal_rebuild);
                }
                if (i2 == -1010) {
                    i3 = -3;
                    string = DOMException.MSG_NOT_SUPPORT;
                } else if (i2 == -1007) {
                    i3 = -1301;
                    string = DOMException.MSG_AUDIO_ERROR_MALFORMED;
                } else if (i2 == -1004) {
                    i3 = -5;
                    string = DOMException.MSG_IO_ERROR;
                } else if (i2 == -110) {
                    i3 = -1302;
                    string = DOMException.MSG_AUDIO_ERROR_TIMED_OUT;
                }
                if (i3 != 0) {
                    AudioPlayer.this.failCallback(i3, string);
                    AudioPlayer.this.execEvents("error", DOMException.toJSON(i3, string));
                }
                return true;
            }
        });
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: io.dcloud.feature.audio.AudioPlayer.7
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                AudioPlayer.this.execEvents("ended", "");
            }
        });
    }

    public static AudioPlayer createAudioPlayer(JSONObject jSONObject, IWebview iWebview) {
        return new AudioPlayer(jSONObject, iWebview);
    }

    private void requestAudioFocus() {
        if (this.mAudioMgr == null) {
            this.mAudioMgr = (AudioManager) this.mWebview.getActivity().getSystemService("audio");
        }
        AudioManager audioManager = this.mAudioMgr;
        if (audioManager != null) {
            audioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006d A[Catch: Exception -> 0x00cc, TRY_LEAVE, TryCatch #0 {Exception -> 0x00cc, blocks: (B:3:0x0004, B:5:0x000d, B:33:0x00c4, B:6:0x001e, B:8:0x0024, B:10:0x0044, B:12:0x004a, B:14:0x0055, B:16:0x005d, B:19:0x006d, B:22:0x0075, B:26:0x0084, B:28:0x008a, B:23:0x007a, B:25:0x0080, B:32:0x00ba, B:30:0x00b1), top: B:38:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setSrc(java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.audio.AudioPlayer.setSrc(java.lang.String):void");
    }

    private void startPlay() {
        requestAudioFocus();
        this.isPrepared = true;
        this.mMediaPlayer.start();
        execEvents(Constants.Value.PLAY, "");
        this.isPlay = false;
    }

    private void successCallback() {
        Deprecated_JSUtil.excCallbackSuccess(this.mWebview, this.mFunId, "");
    }

    public void addEventListener(String str, String str2) {
        this.events.put(str, str2);
    }

    public void destory() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mWebview.obtainFrameView().removeFrameViewListener(this);
            this.mWebview.obtainFrameView().obtainApp().unregisterSysEventListener(this, ISysEventListener.SysEventType.onStop);
            this.mMediaPlayer = null;
            AudioManager audioManager = this.mAudioMgr;
            if (audioManager != null) {
                audioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
            }
            this.mAudioMgr = null;
        }
    }

    public void execEvents(String str, String str2) {
        String str3 = this.events.get(str);
        if (!PdrUtil.isEmpty(str3)) {
            Deprecated_JSUtil.execCallback(this.mWebview, str3, str2, JSUtil.OK, !PdrUtil.isEmpty(str2), true);
        }
        str.hashCode();
        if (str.equals("ended")) {
            pause();
            successCallback();
            return;
        }
        if (str.equals("canplay")) {
            this.isCanplay = true;
            if (this.autoplay) {
                play();
            }
            if (this.isPlay) {
                startPlay();
            }
            int i = this.startTime;
            if (i != Integer.MIN_VALUE) {
                this.mMediaPlayer.seekTo(i);
            }
        }
    }

    public void failCallback(int i, String str) {
        Deprecated_JSUtil.excCallbackError(this.mWebview, this.mFunId, DOMException.toJSON(i, str), true);
    }

    public String getBuffer() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        return Deprecated_JSUtil.wrapJsVar(PdrUtil.int2DecimalStr(mediaPlayer != null ? (this.bufferPercent * mediaPlayer.getDuration()) / 100 : -1, 1000), false);
    }

    public String getDuration() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return Deprecated_JSUtil.wrapJsVar("undefined", false);
        }
        int duration = mediaPlayer.getDuration();
        return duration < 0 ? Deprecated_JSUtil.wrapJsVar("undefined", false) : Deprecated_JSUtil.wrapJsVar(PdrUtil.int2DecimalStr(duration, 1000), false);
    }

    public String getPosition() {
        return Deprecated_JSUtil.wrapJsVar(PdrUtil.int2DecimalStr(this.mMediaPlayer.getCurrentPosition(), 1000), false);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public String getStyles(String str) {
        Object objValueOf;
        if (PdrUtil.isEmpty(str)) {
            return JSUtil.wrapJsVar(this.params);
        }
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2129294769:
                if (str.equals(AnalyticsConfig.RTD_START_TIME)) {
                    b = 0;
                }
                break;
            case -810883302:
                if (str.equals("volume")) {
                    b = 1;
                }
                break;
            case 114148:
                if (str.equals("src")) {
                    b = 2;
                }
                break;
            case 3327652:
                if (str.equals("loop")) {
                    b = 3;
                }
                break;
            case 1439562083:
                if (str.equals(Constants.Name.AUTOPLAY)) {
                    b = 4;
                }
                break;
        }
        if (b == 0) {
            int i = this.startTime;
            objValueOf = Integer.valueOf(i < 0 ? this.params.has(AnalyticsConfig.RTD_START_TIME) ? this.params.optInt(AnalyticsConfig.RTD_START_TIME) : 0 : i / 1000);
        } else if (b == 1) {
            objValueOf = Float.valueOf(this.volume);
        } else if (b == 2) {
            objValueOf = this.mSrcPath;
        } else if (b == 3) {
            objValueOf = Boolean.valueOf(this.mMediaPlayer.isLooping());
        } else {
            if (b != 4) {
                return this.params.has(str) ? JSUtil.wrapJsVar(this.params.optString(str)) : Deprecated_JSUtil.wrapJsVar("undefined", false);
            }
            objValueOf = Boolean.valueOf(this.params.optBoolean(Constants.Name.AUTOPLAY, false));
        }
        return objValueOf != null ? JSUtil.wrapJsVar(objValueOf.toString()) : Deprecated_JSUtil.wrapJsVar("undefined", false);
    }

    public String getVolume() {
        return JSUtil.wrapJsVar(this.volume);
    }

    public boolean isCanMix() {
        return this.isCanMix;
    }

    public String isPause() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        return JSUtil.wrapJsVar(mediaPlayer != null ? true ^ mediaPlayer.isPlaying() : true);
    }

    @Override // io.dcloud.common.DHInterface.IEventCallback
    public Object onCallBack(String str, Object obj) {
        if ((!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str, "close")) || !(obj instanceof IWebview)) {
            return null;
        }
        destory();
        return null;
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (sysEventType != ISysEventListener.SysEventType.onStop) {
            return false;
        }
        destory();
        return false;
    }

    public void pause() {
        this.autoplay = false;
        try {
            this.mMediaPlayer.pause();
        } catch (Exception unused) {
        }
        execEvents("pause", "");
    }

    public void play() {
        if (this.isStoped && !this.mMediaPlayer.isPlaying()) {
            try {
                this.mMediaPlayer.prepareAsync();
                this.isStoped = false;
            } catch (Exception unused) {
                this.mSrcPath = "";
                setStyle(this.params);
                this.isStoped = false;
            }
        }
        try {
            this.isPrepared = false;
            this.isPlay = true;
            if (this.isCanplay) {
                startPlay();
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            destory();
            failCallback(-1, e2.toString());
            execEvents("error", DOMException.toJSON(-1, e2.getMessage()));
        } catch (NumberFormatException unused2) {
        }
    }

    public void removeEventListener(String str) {
        this.events.remove(str);
    }

    public void resume() {
        requestAudioFocus();
        this.mMediaPlayer.start();
    }

    public void seekTo(int i) {
        this.mMediaPlayer.seekTo(i);
        execEvents("seeking", "");
    }

    public void setCanMix(boolean z) {
        this.needPause = z;
    }

    public void setParams(JSONObject jSONObject) {
        this.params = jSONObject;
    }

    public void setSessionCategory(String str) {
        MediaPlayer mediaPlayer;
        if (PdrUtil.isEmpty(str) || (mediaPlayer = this.mMediaPlayer) == null || mediaPlayer.isPlaying()) {
            return;
        }
        this.isCanMix = str.equals("ambient");
    }

    public void setStyle(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("src");
        if (!PdrUtil.isEmpty(strOptString) && (PdrUtil.isEmpty(this.mSrcPath) || !strOptString.equals(this.mSrcPath))) {
            this.mMediaPlayer.reset();
            setSrc(strOptString);
        }
        JSONUtil.combinJSONObject(this.params, jSONObject);
        this.mSrcPath = jSONObject.optString("src");
        this.mMediaPlayer.setLooping(this.params.optBoolean("loop"));
        try {
            float f = Float.parseFloat(this.params.optString("volume", "1"));
            this.volume = f;
            if (f < 0.0f) {
                this.volume = 0.0f;
            } else if (f > 1.0f) {
                this.volume = 1.0f;
            }
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            float f2 = this.volume;
            mediaPlayer.setVolume(f2, f2);
            if (this.params.has(AnalyticsConfig.RTD_START_TIME)) {
                this.startTime = this.params.optInt(AnalyticsConfig.RTD_START_TIME) * 1000;
            }
            this.autoplay = this.params.optBoolean(Constants.Name.AUTOPLAY, false);
        } catch (Exception unused) {
        }
    }

    public void stop() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.isStoped = true;
            this.isCanplay = false;
            execEvents(Constants.Value.STOP, "");
        }
    }
}
