package io.dcloud.media.weex.weex_video.ijkplayer.media;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import com.taobao.weex.utils.WXDataStructureUtil;
import com.taobao.weex.utils.WXViewUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.ui.navigator.QueryNotchTool;
import io.dcloud.feature.weex_media.R;
import io.dcloud.feature.weex_media.VideoPlayerView;
import io.dcloud.feature.weex_media.option.EnumPlayStrategy;
import io.dcloud.media.weex.weex_video.ijkplayer.OnPlayerChangedListener;
import io.dcloud.media.weex.weex_video.ijkplayer.VideoR;
import io.dcloud.media.weex.weex_video.ijkplayer.danmaku.BaseDanmakuConverter;
import io.dcloud.media.weex.weex_video.ijkplayer.danmaku.BiliDanmukuParser;
import io.dcloud.media.weex.weex_video.ijkplayer.danmaku.OnDanmakuListener;
import io.dcloud.media.weex.weex_video.ijkplayer.danmaku.StandardDanmaKuParser;
import io.dcloud.media.weex.weex_video.ijkplayer.utils.MotionEventUtils;
import io.dcloud.media.weex.weex_video.ijkplayer.utils.NetWorkUtils;
import io.dcloud.media.weex.weex_video.ijkplayer.utils.StringUtils;
import io.dcloud.media.weex.weex_video.ijkplayer.widgets.MarqueeTextView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashMap;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.json.JSONObject;
import supwisdom.du;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class IjkPlayerView extends FrameLayout implements View.OnClickListener {
    public static final int DANMAKU_TAG_ACFUN = 702;
    public static final int DANMAKU_TAG_BILI = 701;
    public static final int DANMAKU_TAG_CUSTOM = 703;
    public static final int DEFAULT_HIDE_TIMEOUT = 5000;
    public static final int INTERRUPT_WHEN_PAUSE = 503;
    public static final int INTERRUPT_WHEN_PLAY = 502;
    public static final int INTERVAL_TIME = 1000;
    public static final int INVALID_VALUE = -1;
    public static final int MAX_VIDEO_SEEK = 1000;
    public static final int MSG_ENABLE_ORIENTATION = 10087;
    public static final int MSG_TRY_RELOAD = 10088;
    public static final int MSG_UPDATE_SEEK = 10086;
    public static final int MSG_UPDATE_TIME = 10099;
    public static final int NORMAL_STATUS = 501;
    public IMediaPlayer.OnBufferingUpdateListener bufferingUpdateListener;
    public WXVContainer component;
    public boolean controlShowEnable;
    public int defaultDisplayCutoutMode;
    public float defaultScreenBrightness;
    public int defaultSystemUI;
    public int duration;
    public String fullCallFormat;
    public boolean isCenterPlayBtnVisibility;
    public boolean isFullScreenPageGesture;
    public boolean isLoadingVisibility;
    public boolean isMutePlayer;
    public boolean isPageGesture;
    public boolean isPlayBtnCenter;
    public boolean isPlayBtnVisibility;
    public boolean isProgressGesture;
    public boolean isRtmpUri;
    public boolean isShowProgress;
    public boolean isShowScreenLockButton;
    public int mAspectOptionsHeight;
    public Activity mAttachActivity;
    public AudioManager mAudioManager;
    public int mBasicOptionsWidth;
    public IMediaPlayer.OnCompletionListener mCompletionListener;
    public float mCurBrightness;
    public int mCurPosition;
    public int mCurVolume;
    public DanmakuContext mDanmakuContext;
    public BaseDanmakuConverter mDanmakuConverter;
    public OnDanmakuListener mDanmakuListener;
    public ILoader mDanmakuLoader;
    public BaseDanmakuParser mDanmakuParser;
    public int mDanmakuTag;
    public long mDanmakuTargetPosition;
    public int mDanmakuTextColor;
    public float mDanmakuTextSize;
    public int mDanmakuType;
    public IDanmakuView mDanmakuView;
    public String mDanmuList;
    public long mExitTime;
    public FrameLayout mFlTouchLayout;
    public FrameLayout mFlVideoBox;
    public LinearLayout mFullscreenTopBar;
    public GestureDetector mGestureDetector;

    @SuppressLint({"HandlerLeak"})
    public Handler mHandler;
    public Runnable mHideBarRunnable;
    public Runnable mHideTouchViewRunnable;
    public ImageView mIVMute;
    public IMediaPlayer.OnInfoListener mInfoListener;
    public int mInitHeight;
    public int mInterruptPosition;
    public boolean mIsAlwaysFullScreen;
    public boolean mIsBufferingStart;
    public boolean mIsDoubleTapEnable;
    public boolean mIsEnableDanmaku;
    public boolean mIsForbidOrientation;
    public boolean mIsForbidTouch;
    public boolean mIsFullscreen;
    public boolean mIsNeedRecoverScreen;
    public boolean mIsNetConnected;
    public boolean mIsNeverPlay;
    public boolean mIsPlayComplete;
    public boolean mIsReady;
    public boolean mIsRenderingStart;
    public boolean mIsScreenLocked;
    public boolean mIsSeeking;
    public boolean mIsShowBar;
    public ImageView mIvBack;
    public TextView mIvDanmakuControl;
    public ImageView mIvFullscreen;
    public ImageView mIvPlay;
    public ImageView mIvPlayCenter;
    public ImageView mIvPlayCircle;
    public ImageView mIvScreenLock;
    public LinearLayout mLlBottomBar;
    public ProgressBar mLoadingView;
    public int mMaxVolume;
    public int mMoreOptionsWidth;
    public NetBroadcastReceiver mNetReceiver;
    public OnPlayerChangedListener mOnPlayerChangedListener;
    public int mOrientation;
    public OrientationEventListener mOrientationListener;
    public IMediaPlayer.OnInfoListener mOutsideInfoListener;
    public GestureDetector.OnGestureListener mPlayerGestureListener;
    public SeekBar mPlayerSeek;
    public ImageView mPlayerThumb;
    public View.OnTouchListener mPlayerTouchListener;
    public ViewGroup.LayoutParams mRawParams;
    public ViewGroup mRootLayout;
    public Matrix mSaveMatrix;
    public ScreenBroadcastReceiver mScreenReceiver;
    public int mScreenUiVisibility;
    public final SeekBar.OnSeekBarChangeListener mSeekListener;
    public long mTargetPosition;
    public TextView mTvBrightness;
    public TextView mTvCurTime;
    public TextView mTvEndTime;
    public TextView mTvFastForward;
    public TextView mTvRecoverScreen;
    public MarqueeTextView mTvTitle;
    public TextView mTvVolume;
    public Matrix mVideoMatrix;
    public SparseArray<String> mVideoSource;
    public int mVideoStatus;
    public IjkVideoView mVideoView;
    public int mWidthPixels;
    public IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener;
    public int orientation;
    public int originOrientation;
    public VideoPlayerView parentView;
    public String[] rates;
    public Runnable screenLockHideRunnable;
    public String timeUpdateF;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DanmakuTag {
    }

    public class NetBroadcastReceiver extends BroadcastReceiver {
        public NetBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                ijkPlayerView.mIsNetConnected = NetWorkUtils.isNetworkAvailable(ijkPlayerView.mAttachActivity);
            }
        }
    }

    public class ScreenBroadcastReceiver extends BroadcastReceiver {
        public ScreenBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                IjkPlayerView.this.mIsScreenLocked = true;
            }
        }
    }

    public IjkPlayerView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _endGesture() {
        long j = this.mTargetPosition;
        if (j >= 0 && j != this.mVideoView.getCurrentPosition() && getDuration() > 0) {
            seekTo((int) this.mTargetPosition);
            this.mPlayerSeek.setProgress((int) ((this.mTargetPosition * 1000) / ((long) getDuration())));
            this.mTargetPosition = -1L;
        }
        _hideTouchView();
        _refreshHideRunnable();
        this.mCurVolume = -1;
        this.mCurBrightness = -1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _handleOrientation(int i) {
        if (this.mIsNeverPlay) {
            return;
        }
        if (this.mIsFullscreen && !this.mIsAlwaysFullScreen) {
            if ((i < 0 || i > 30) && i < 330) {
                return;
            }
            this.mAttachActivity.setRequestedOrientation(1);
            return;
        }
        if (i >= 60 && i <= 120) {
            this.mAttachActivity.setRequestedOrientation(8);
        } else {
            if (i < 240 || i > 300) {
                return;
            }
            this.mAttachActivity.setRequestedOrientation(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideAllView(boolean z) {
        this.mFlTouchLayout.setVisibility(8);
        this.mFullscreenTopBar.setVisibility(8);
        this.mTvTitle.stopMotion();
        this.mIvPlayCenter.setVisibility(8);
        if (this.mLlBottomBar.getVisibility() == 0) {
            this.mOnPlayerChangedListener.onChanged("controlstoggle", "{'show':false}");
        }
        this.mLlBottomBar.setVisibility(8);
        if (!z) {
            this.mIsShowBar = false;
        }
        if (this.mIsNeedRecoverScreen) {
            this.mTvRecoverScreen.setVisibility(8);
        }
        if (this.mIvScreenLock.getVisibility() == 0) {
            this.mIvScreenLock.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideTouchView() {
        if (this.mFlTouchLayout.getVisibility() == 0) {
            this.mTvFastForward.setVisibility(8);
            this.mTvVolume.setVisibility(8);
            this.mTvBrightness.setVisibility(8);
            this.mFlTouchLayout.setVisibility(8);
        }
    }

    private void _initDanmaku() {
        this.mDanmakuView = (IDanmakuView) findViewById(VideoR.VIDEO_IJK_ID_SV_DANMAKU);
        this.mIvDanmakuControl.setOnClickListener(this);
        this.mMoreOptionsWidth = getResources().getDimensionPixelOffset(VideoR.VIDEO_IJK_DIMEN_DANMAKU_INPUT_BTN_SIZE) * 12;
    }

    private void _initMediaPlayer() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        AudioManager audioManager = (AudioManager) this.mAttachActivity.getSystemService("audio");
        this.mAudioManager = audioManager;
        this.mMaxVolume = audioManager.getStreamMaxVolume(3);
        this.defaultScreenBrightness = this.mAttachActivity.getWindow().getAttributes().screenBrightness;
        this.mPlayerSeek.setMax(1000);
        this.mPlayerSeek.setOnSeekBarChangeListener(this.mSeekListener);
        this.mVideoView.setOnInfoListener(this.mInfoListener);
        this.mVideoView.setOnBufferingUpdateListener(this.onBufferingUpdateListener);
        this.mGestureDetector = new GestureDetector(this.mAttachActivity, this.mPlayerGestureListener);
        this.mFlVideoBox.setClickable(true);
        this.mFlVideoBox.setOnTouchListener(this.mPlayerTouchListener);
        OrientationEventListener orientationEventListener = new OrientationEventListener(this.mAttachActivity) { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.3
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                IjkPlayerView.this._handleOrientation(i);
            }
        };
        this.mOrientationListener = orientationEventListener;
        if (this.mIsForbidOrientation) {
            orientationEventListener.disable();
        }
    }

    private void _initReceiver() {
        this.mScreenReceiver = new ScreenBroadcastReceiver();
        this.mNetReceiver = new NetBroadcastReceiver();
        this.mAttachActivity.registerReceiver(this.mScreenReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
        this.mAttachActivity.registerReceiver(this.mNetReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    private void _initView(Context context) {
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("Context must be Activity");
        }
        this.mAttachActivity = (Activity) context;
        View.inflate(context, VideoR.VIDEO_IJK_LAYOUT_PLAYER_VIEW, this);
        this.mVideoView = (IjkVideoView) findViewById(VideoR.VIDEO_IJK_ID_VIDEO_VIEW);
        this.mPlayerThumb = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_THUMB);
        this.mLoadingView = (ProgressBar) findViewById(VideoR.VIDEO_IJK_ID_PD_LOADING);
        this.mTvVolume = (TextView) findViewById(VideoR.VIDEO_IJK_ID_TV_VOLUME);
        this.mTvBrightness = (TextView) findViewById(VideoR.VIDEO_IJK_ID_TV_BRIGHTNESS);
        this.mTvFastForward = (TextView) findViewById(VideoR.VIDEO_IJK_ID_TV_FAST_FORWARD);
        this.mFlTouchLayout = (FrameLayout) findViewById(VideoR.VIDEO_IJK_ID_FL_TOUCH_LAYOUT);
        this.mIvBack = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_BACK);
        this.mTvTitle = (MarqueeTextView) findViewById(VideoR.VIDEO_IJK_ID_TV_TITLE);
        this.mFullscreenTopBar = (LinearLayout) findViewById(VideoR.VIDEO_IJK_ID_FULLSCREEN_TOP_BAR);
        this.mIvPlay = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_PLAY);
        this.mTvCurTime = (TextView) findViewById(VideoR.VIDEO_IJK_ID_TV_CUR_TIME);
        this.mPlayerSeek = (SeekBar) findViewById(VideoR.VIDEO_IJK_ID_PLAYER_SEEK);
        this.mTvEndTime = (TextView) findViewById(VideoR.VIDEO_IJK_ID_TV_END_TIME);
        this.mIvFullscreen = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_FULLSCREEN);
        this.mLlBottomBar = (LinearLayout) findViewById(VideoR.VIDEO_IJK_ID_LL_BOTTOM_BAR);
        this.mFlVideoBox = (FrameLayout) findViewById(VideoR.VIDEO_IJK_ID_FL_VIDEO_BOX);
        this.mIvPlayCircle = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_PLAY_CIRCLE);
        this.mTvRecoverScreen = (TextView) findViewById(VideoR.VIDEO_IJK_ID_TV_RECOVER_SCREEN);
        this.mIvDanmakuControl = (TextView) findViewById(VideoR.VIDEO_IJK_ID_IV_DANMAKU_CONTROL);
        this.mIVMute = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_MUTE);
        this.mIvPlayCenter = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_PLAY_CENTER);
        this.mIvScreenLock = (ImageView) findViewById(VideoR.VIDEO_IJK_ID_IV_SCREEN_LOCK);
        this.mAspectOptionsHeight = getResources().getDimensionPixelSize(VideoR.VIDEO_IJK_DIMEN_ASPECT_BNT_SIZE) * 4;
        _initReceiver();
        this.mIvPlay.setOnClickListener(this);
        this.mIvBack.setOnClickListener(this);
        this.mIvFullscreen.setOnClickListener(this);
        this.mIvPlayCircle.setOnClickListener(this);
        this.mTvRecoverScreen.setOnClickListener(this);
        this.mIVMute.setOnClickListener(this);
        this.mIvPlayCenter.setOnClickListener(this);
        setOnClickListener(this);
        this.mIvScreenLock.setOnClickListener(this);
        if (this.mIsForbidTouch) {
            this.mIvScreenLock.setImageResource(R.drawable.video_screen_lock);
            this.mIvScreenLock.setTag(1);
        } else {
            this.mIvScreenLock.setImageResource(R.drawable.video_screen_unlock);
            this.mIvScreenLock.setTag(0);
        }
    }

    private void _loadDanmaku() {
        if (this.mIsEnableDanmaku) {
            this.mDanmakuContext = DanmakuContext.create();
            if (this.mDanmakuParser == null) {
                this.mDanmakuParser = new BaseDanmakuParser() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.13
                    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
                    public Danmakus parse() {
                        return new Danmakus();
                    }
                };
            }
            try {
                setDanmakuSource(new ByteArrayInputStream(this.mDanmuList.getBytes("utf-8")));
                this.mDanmakuView.setCallback(new DrawHandler.Callback() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.14
                    @Override // master.flame.danmaku.controller.DrawHandler.Callback
                    public void danmakuShown(BaseDanmaku baseDanmaku) {
                    }

                    @Override // master.flame.danmaku.controller.DrawHandler.Callback
                    public void drawingFinished() {
                    }

                    @Override // master.flame.danmaku.controller.DrawHandler.Callback
                    public void prepared() {
                        if (!IjkPlayerView.this.mVideoView.isPlaying() || IjkPlayerView.this.mIsBufferingStart) {
                            return;
                        }
                        IjkPlayerView.this.mDanmakuView.start();
                    }

                    @Override // master.flame.danmaku.controller.DrawHandler.Callback
                    public void updateTimer(DanmakuTimer danmakuTimer) {
                    }
                });
                this.mDanmakuView.enableDanmakuDrawingCache(true);
                this.mDanmakuView.prepare(this.mDanmakuParser, this.mDanmakuContext);
            } catch (UnsupportedEncodingException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onBrightnessSlide(float f) {
        if (this.mIsFullscreen || this.isPageGesture) {
            if (!this.mIsFullscreen || this.isFullScreenPageGesture) {
                if (this.mCurBrightness < 0.0f) {
                    float f2 = this.mAttachActivity.getWindow().getAttributes().screenBrightness;
                    this.mCurBrightness = f2;
                    if (f2 < 0.0f) {
                        this.mCurBrightness = 0.5f;
                    } else if (f2 < 0.01f) {
                        this.mCurBrightness = 0.01f;
                    }
                }
                WindowManager.LayoutParams attributes = this.mAttachActivity.getWindow().getAttributes();
                float f3 = this.mCurBrightness + f;
                attributes.screenBrightness = f3;
                if (f3 > 1.0f) {
                    attributes.screenBrightness = 1.0f;
                } else if (f3 < 0.01f) {
                    attributes.screenBrightness = 0.01f;
                }
                _setBrightnessInfo(attributes.screenBrightness);
                this.mAttachActivity.getWindow().setAttributes(attributes);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onProgressSlide(float f) {
        String str;
        if (!this.isRtmpUri && this.isProgressGesture) {
            int currentPosition = this.mVideoView.getCurrentPosition();
            long duration = getDuration();
            long j = currentPosition;
            long jMin = ((long) (Math.min(100000L, duration / 2) * f)) + j;
            this.mTargetPosition = jMin;
            if (jMin > duration) {
                this.mTargetPosition = duration;
            } else if (jMin <= 0) {
                this.mTargetPosition = 0L;
            }
            long j2 = this.mTargetPosition;
            long j3 = (j2 - j) / 1000;
            if (j2 > j) {
                str = StringUtils.generateTime(this.mTargetPosition) + "/" + StringUtils.generateTime(duration);
            } else {
                str = StringUtils.generateTime(this.mTargetPosition) + "/" + StringUtils.generateTime(duration);
            }
            _setFastForward(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onVolumeSlide(float f) {
        if (this.isMutePlayer) {
            return;
        }
        if (this.mIsFullscreen || this.isPageGesture) {
            if (!this.mIsFullscreen || this.isFullScreenPageGesture) {
                if (this.mCurVolume == -1) {
                    int streamVolume = this.mAudioManager.getStreamVolume(3);
                    this.mCurVolume = streamVolume;
                    if (streamVolume < 0) {
                        this.mCurVolume = 0;
                    }
                }
                int i = this.mMaxVolume;
                int i2 = ((int) (f * i)) + this.mCurVolume;
                if (i2 <= i) {
                    i = i2 < 0 ? 0 : i2;
                }
                this.mAudioManager.setStreamVolume(3, i, 0);
                _setVolumeInfo(i);
            }
        }
    }

    private void _pauseDanmaku() {
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView == null || !iDanmakuView.isPrepared()) {
            return;
        }
        this.mDanmakuView.pause();
    }

    private void _recoverScreen() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _refreshHideRunnable() {
        this.mHandler.removeCallbacks(this.mHideBarRunnable);
        this.mHandler.postDelayed(this.mHideBarRunnable, 5000L);
    }

    private void _refreshOrientationEnable() {
        if (this.mIsForbidOrientation) {
            return;
        }
        this.mOrientationListener.disable();
        this.mHandler.removeMessages(10087);
        this.mHandler.sendEmptyMessageDelayed(10087, 3000L);
    }

    private void _resumeDanmaku() {
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView != null && iDanmakuView.isPrepared() && this.mDanmakuView.isPaused()) {
            long j = this.mDanmakuTargetPosition;
            if (j == -1) {
                this.mDanmakuView.resume();
            } else {
                this.mDanmakuView.seekTo(Long.valueOf(j));
                this.mDanmakuTargetPosition = -1L;
            }
        }
    }

    private void _setBrightnessInfo(float f) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvBrightness.getVisibility() == 8) {
            this.mTvBrightness.setVisibility(0);
        }
        this.mTvBrightness.setText(Math.ceil(f * 100.0f) + "%");
    }

    private void _setControlBarVisible(boolean z) {
        if (!this.controlShowEnable) {
            this.mLlBottomBar.setVisibility(8);
            this.mFullscreenTopBar.setVisibility(8);
            this.mTvTitle.stopMotion();
            if (this.mIsNeedRecoverScreen) {
                this.mTvRecoverScreen.setVisibility(8);
                return;
            }
            return;
        }
        if (this.mIsNeverPlay) {
            if (this.mIvPlayCircle.getVisibility() != 0) {
                this.mLlBottomBar.setVisibility(z ? 0 : 8);
                return;
            }
            return;
        }
        if (this.mIsForbidTouch) {
            return;
        }
        this.mLlBottomBar.setVisibility(z ? 0 : 8);
        if (this.isPlayBtnCenter && this.isPlayBtnVisibility) {
            this.mIvPlayCenter.setVisibility(z ? 0 : 8);
        }
        if (!this.mIsFullscreen) {
            this.mFullscreenTopBar.setVisibility(8);
            this.mTvTitle.stopMotion();
            if (this.mIsNeedRecoverScreen) {
                this.mTvRecoverScreen.setVisibility(8);
                return;
            }
            return;
        }
        this.mFullscreenTopBar.setVisibility(z ? 0 : 8);
        if (z) {
            this.mTvTitle.startMotion();
        } else {
            this.mTvTitle.stopMotion();
        }
        if (this.mIsNeedRecoverScreen) {
            this.mTvRecoverScreen.setVisibility(z ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _setFastForward(String str) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvFastForward.getVisibility() == 8) {
            this.mTvFastForward.setVisibility(0);
        }
        this.mTvFastForward.setText(str);
    }

    private void _setFullScreen(boolean z) {
        this.mIsFullscreen = z;
        this.mIvFullscreen.setSelected(z);
        if (this.mOnPlayerChangedListener != null) {
            final String str = z ? StringUtil.format(this.fullCallFormat, true, Constants.Value.HORIZONTAL) : StringUtil.format(this.fullCallFormat, false, "vertical");
            this.mHandler.postDelayed(new Runnable() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.6
                @Override // java.lang.Runnable
                public void run() {
                    IjkPlayerView.this.mOnPlayerChangedListener.onChanged("fullscreenchange", str);
                }
            }, 50L);
        }
        this.mHandler.post(this.mHideBarRunnable);
        if (this.mIsNeedRecoverScreen) {
            if (z) {
                this.mVideoView.adjustVideoView(1.0f);
                this.mTvRecoverScreen.setVisibility(this.mIsShowBar ? 0 : 8);
            } else {
                this.mVideoView.resetVideoView(false);
                this.mTvRecoverScreen.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int _setProgress() {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView == null || this.mIsSeeking) {
            return 0;
        }
        int iMax = Math.max(ijkVideoView.getCurrentPosition(), this.mInterruptPosition);
        int duration = getDuration();
        if (duration > 0) {
            this.mPlayerSeek.setProgress((int) ((((long) iMax) * 1000) / ((long) duration)));
        }
        this.mPlayerSeek.setSecondaryProgress(this.mVideoView.getBufferPercentage() * 10);
        this.mTvCurTime.setText(StringUtils.generateTime(iMax));
        this.mTvEndTime.setText(StringUtils.generateTime(duration));
        return iMax;
    }

    private void _setVolume(boolean z) {
        if (this.isMutePlayer) {
            return;
        }
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        int i = z ? streamVolume + (this.mMaxVolume / 15) : streamVolume - (this.mMaxVolume / 15);
        int i2 = this.mMaxVolume;
        if (i > i2) {
            i = i2;
        } else if (i < 0) {
            i = 0;
        }
        this.mAudioManager.setStreamVolume(3, i, 0);
        _setVolumeInfo(i);
        this.mHandler.removeCallbacks(this.mHideTouchViewRunnable);
        this.mHandler.postDelayed(this.mHideTouchViewRunnable, 1000L);
    }

    private void _setVolumeInfo(int i) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvVolume.getVisibility() == 8) {
            this.mTvVolume.setVisibility(0);
        }
        this.mTvVolume.setText(((i * 100) / this.mMaxVolume) + "%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _showControlBar(int i) {
        if (!this.mIsShowBar) {
            _setProgress();
            this.mIsShowBar = true;
        }
        _setControlBarVisible(true);
        this.mHandler.sendEmptyMessage(10086);
        this.mHandler.removeCallbacks(this.mHideBarRunnable);
        if (i != 0) {
            this.mHandler.postDelayed(this.mHideBarRunnable, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _switchStatus(int i) {
        Log.i("IjkPlayerView", "status " + i);
        if (i == 3) {
            this.mIsRenderingStart = true;
        } else {
            if (i == 331) {
                this.mInterruptPosition = Math.max(this.mVideoView.getInterruptPosition(), this.mInterruptPosition);
                pause();
                if (this.mVideoView.getDuration() != -1 || this.mIsReady) {
                    setLoadingVisibility(0);
                    this.mHandler.sendEmptyMessage(10088);
                    return;
                } else {
                    setLoadingVisibility(8);
                    this.mPlayerThumb.setVisibility(8);
                    this.mIvPlayCircle.setVisibility(8);
                    return;
                }
            }
            if (i == 336) {
                pause();
                if (this.mVideoView.getDuration() == -1 || this.mVideoView.getInterruptPosition() + 1000 < this.mVideoView.getDuration()) {
                    this.mInterruptPosition = Math.max(this.mVideoView.getInterruptPosition(), this.mInterruptPosition);
                    OnPlayerChangedListener onPlayerChangedListener = this.mOnPlayerChangedListener;
                    if (onPlayerChangedListener != null) {
                        onPlayerChangedListener.onChanged("error", DOMException.MSG_NETWORK_ERROR);
                        return;
                    }
                    return;
                }
                this.mIsPlayComplete = true;
                IMediaPlayer.OnCompletionListener onCompletionListener = this.mCompletionListener;
                if (onCompletionListener != null) {
                    onCompletionListener.onCompletion(this.mVideoView.getMediaPlayer());
                    return;
                }
                return;
            }
            if (i == 333) {
                this.mIsReady = true;
                return;
            }
            if (i == 334) {
                this.mHandler.removeMessages(10088);
                if (!this.mIsRenderingStart || this.mIsBufferingStart || this.mVideoView.getCurrentPosition() <= 0) {
                    return;
                }
                _resumeDanmaku();
                return;
            }
            if (i == 701) {
                this.mIsBufferingStart = true;
                _pauseDanmaku();
                if (!this.mIsNeverPlay) {
                    setLoadingVisibility(0);
                }
                this.mHandler.removeMessages(10088);
                return;
            }
            if (i != 702) {
                return;
            }
        }
        this.mIsBufferingStart = false;
        setLoadingVisibility(8);
        this.mPlayerThumb.setVisibility(8);
        if (this.mLlBottomBar.getVisibility() == 0 && !this.mIsShowBar) {
            this.mIsShowBar = true;
        }
        this.mHandler.removeMessages(10086);
        this.mHandler.sendEmptyMessage(10086);
        if (this.mVideoView.isPlaying() && this.mIsNetConnected) {
            this.mInterruptPosition = 0;
            _resumeDanmaku();
            if (this.mIvPlay.isSelected()) {
                return;
            }
            this.mVideoView.start();
            this.mIvPlay.setSelected(true);
            this.mIvPlayCenter.setSelected(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleControlBar() {
        this.mIsShowBar = !this.mIsShowBar;
        this.mOnPlayerChangedListener.onChanged("controlstoggle", "{'show':" + this.mIsShowBar + Operators.BLOCK_END_STR);
        _setControlBarVisible(this.mIsShowBar);
        this.mHandler.removeCallbacks(this.mHideBarRunnable);
        if (this.mIsShowBar) {
            this.mHandler.postDelayed(this.mHideBarRunnable, 5000L);
            this.mHandler.sendEmptyMessage(10086);
        }
    }

    private void _toggleDanmakuShow() {
        if (this.mIvDanmakuControl.isSelected()) {
            showOrHideDanmaku(true);
        } else {
            showOrHideDanmaku(false);
        }
    }

    private void _toggleFullScreen() {
        IjkVideoView ijkVideoView;
        if (isFullscreen()) {
            exitFullScreen();
            return;
        }
        VideoPlayerView videoPlayerView = this.parentView;
        if (videoPlayerView != null) {
            int direction = videoPlayerView.getDirection();
            if (direction == Integer.MIN_VALUE && (ijkVideoView = this.mVideoView) != null) {
                direction = ijkVideoView.getVideoHeight() > this.mVideoView.getVideoWidth() ? 0 : -90;
            }
            this.mOrientation = direction;
        }
        fullScreen(this.mOrientation);
    }

    private void _toggleMoreColorOptions() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _togglePlayStatus() {
        if (this.mVideoView.isPlaying()) {
            pause();
        } else {
            start();
        }
    }

    private void _togglePlayerLock() {
        boolean z = !this.mIsForbidTouch;
        this.mIsForbidTouch = z;
        if (z) {
            this.mOrientationListener.disable();
            _hideAllView(true);
            return;
        }
        if (!this.mIsForbidOrientation) {
            this.mOrientationListener.enable();
        }
        this.mFullscreenTopBar.setVisibility(0);
        if (this.controlShowEnable) {
            this.mLlBottomBar.setVisibility(0);
        }
        if (this.mIsNeedRecoverScreen) {
            this.mTvRecoverScreen.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void progressCallBack() {
        if (this.mVideoView == null || this.mIsSeeking || this.mOnPlayerChangedListener == null) {
            return;
        }
        this.mOnPlayerChangedListener.onChanged("timeupdate", StringUtil.format(this.timeUpdateF, Float.valueOf(Math.max(r0.getCurrentPosition(), this.mInterruptPosition) / 1000.0f), Float.valueOf(getDuration() / 1000.0f)));
    }

    private void setNavigationBar(boolean z) {
        Window window = this.mAttachActivity.getWindow();
        View decorView = window.getDecorView();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            this.defaultSystemUI = decorView.getSystemUiVisibility();
            this.mAttachActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
            if (QueryNotchTool.hasNotchInScreen(this.mAttachActivity) && Build.VERSION.SDK_INT >= 28) {
                this.defaultDisplayCutoutMode = attributes.layoutInDisplayCutoutMode;
                attributes.layoutInDisplayCutoutMode = 1;
            }
        } else {
            decorView.setSystemUiVisibility(this.defaultSystemUI);
            if (QueryNotchTool.hasNotchInScreen(this.mAttachActivity) && Build.VERSION.SDK_INT >= 28) {
                window.getAttributes().layoutInDisplayCutoutMode = this.defaultDisplayCutoutMode;
            }
        }
        window.setAttributes(attributes);
    }

    private void setSeekBarColor() {
        LayerDrawable layerDrawable = (LayerDrawable) this.mPlayerSeek.getProgressDrawable();
        layerDrawable.findDrawableByLayerId(android.R.id.background).setColorFilter(Color.parseColor("#ff00ff"), PorterDuff.Mode.SRC_ATOP);
        layerDrawable.findDrawableByLayerId(android.R.id.secondaryProgress).setColorFilter(Color.parseColor("#ffff00"), PorterDuff.Mode.SRC_ATOP);
        layerDrawable.findDrawableByLayerId(android.R.id.progress).setColorFilter(Color.parseColor("#00ffff"), PorterDuff.Mode.SRC_ATOP);
        this.mPlayerSeek.getThumb().setColorFilter(Color.parseColor("#0000ff"), PorterDuff.Mode.SRC_ATOP);
    }

    public IjkPlayerView alwaysFullScreen() {
        this.mIsAlwaysFullScreen = true;
        fullScreen(this.mOrientation);
        this.mIvFullscreen.setVisibility(8);
        return this;
    }

    public void clearDanma() {
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView == null || !iDanmakuView.isPrepared()) {
            return;
        }
        this.mDanmakuView.clearDanmakusOnScreen();
    }

    public void editVideo() {
        if (this.mVideoView.isPlaying()) {
            pause();
            this.mVideoStatus = 502;
        } else {
            this.mVideoStatus = 503;
        }
        _hideAllView(false);
    }

    public IjkPlayerView enableDanmaku() {
        this.mIsEnableDanmaku = true;
        _initDanmaku();
        return this;
    }

    public void enableDanmuBtn(boolean z) {
        if (!this.mIsEnableDanmaku) {
            this.mIvDanmakuControl.setVisibility(8);
        } else if (z) {
            this.mIvDanmakuControl.setVisibility(0);
        } else {
            this.mIvDanmakuControl.setVisibility(8);
        }
    }

    public IjkPlayerView enableOrientation() {
        this.mIsForbidOrientation = false;
        this.mOrientationListener.enable();
        return this;
    }

    public void exitFullScreen() {
        _refreshOrientationEnable();
        if (this.mIsFullscreen) {
            int requestedOrientation = this.mAttachActivity.getRequestedOrientation();
            int i = this.originOrientation;
            if (requestedOrientation != i) {
                this.mAttachActivity.setRequestedOrientation(i);
            }
            setNavigationBar(false);
            setLayoutParams(this.mRawParams);
            _setFullScreen(false);
            if (getParent() != this.mRootLayout) {
                ((ViewGroup) getParent()).removeView(this);
                this.mRootLayout.addView(this, new FrameLayout.LayoutParams(-1, -1));
            }
            if (Build.VERSION.SDK_INT < 28 || !QueryNotchTool.hasNotchInScreen(this.mAttachActivity)) {
                return;
            }
            this.mLlBottomBar.setPadding(0, 0, 0, 0);
            this.mFullscreenTopBar.setPadding(0, 0, 0, 0);
        }
    }

    public void fullScreen(int i) {
        ViewGroup.LayoutParams layoutParams;
        _refreshOrientationEnable();
        if (this.mIsFullscreen) {
            return;
        }
        this.originOrientation = this.mAttachActivity.getRequestedOrientation();
        this.orientation = i;
        if (i == 0) {
            if (this.mAttachActivity.getRequestedOrientation() != 1) {
                this.mAttachActivity.setRequestedOrientation(1);
            }
        } else if (i == 90) {
            if (this.mAttachActivity.getRequestedOrientation() != 8) {
                this.mAttachActivity.setRequestedOrientation(8);
            }
        } else if (i == -90 && this.mAttachActivity.getRequestedOrientation() != 0) {
            this.mAttachActivity.setRequestedOrientation(0);
        }
        setNavigationBar(true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mAttachActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        this.mRawParams = layoutParams2;
        if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
            layoutParams = new RelativeLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else if (layoutParams2 instanceof LinearLayout.LayoutParams) {
            layoutParams = new LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else {
            if (!(layoutParams2 instanceof FrameLayout.LayoutParams)) {
                new AlertDialog.Builder(getContext()).setMessage("nonsupport parent layout, please do it by yourself").setPositiveButton(WXModalUIModule.OK, new DialogInterface.OnClickListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                    }
                }).setCancelable(false).show();
                return;
            }
            layoutParams = new FrameLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        setLayoutParams(layoutParams);
        _setFullScreen(true);
        ViewGroup viewGroup = (ViewGroup) this.mAttachActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        if ((viewGroup instanceof FrameLayout) && getParent() != viewGroup) {
            ((ViewGroup) getParent()).removeView(this);
            viewGroup.addView(this, new FrameLayout.LayoutParams(-1, -1));
        }
        if (Build.VERSION.SDK_INT < 28 || !QueryNotchTool.hasNotchInScreen(this.mAttachActivity)) {
            return;
        }
        int statusHeight = DeviceInfo.getStatusHeight(getContext()) - PdrUtil.pxFromDp(10.0f, getContext().getResources().getDisplayMetrics());
        if (i == 0) {
            this.mFullscreenTopBar.setPadding(0, statusHeight, 0, 0);
        } else {
            this.mLlBottomBar.setPadding(statusHeight, 0, statusHeight, 0);
            this.mFullscreenTopBar.setPadding(statusHeight, 0, statusHeight, 0);
        }
    }

    public int getCurPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    public int getDuration() {
        if (this.duration <= -1) {
            this.duration = this.mVideoView.getDuration();
        }
        return this.duration;
    }

    public boolean handleVolumeKey(int i) {
        if (i == 24) {
            _setVolume(true);
            return true;
        }
        if (i != 25) {
            return false;
        }
        _setVolume(false);
        return true;
    }

    public void hiddenLoaded(boolean z) {
    }

    public IjkPlayerView init() {
        _initMediaPlayer();
        return this;
    }

    public boolean isFullscreen() {
        return this.mIsFullscreen;
    }

    public void isMuteBtnShow(boolean z) {
        if (z) {
            this.mIVMute.setVisibility(0);
        } else {
            this.mIVMute.setVisibility(8);
        }
    }

    public boolean isPlaying() {
        return this.mVideoView.isPlaying();
    }

    public void isUseMediaCodec(boolean z) {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView != null) {
            ijkVideoView.setmIsUsingMediaCodec(z);
        }
    }

    public boolean onBackPressed() {
        if (recoverFromEditVideo() || this.mIsAlwaysFullScreen) {
            return true;
        }
        if (!this.mIsFullscreen) {
            return false;
        }
        exitFullScreen();
        if (this.mIsForbidTouch) {
            this.mIsForbidTouch = false;
            _setControlBarVisible(this.mIsShowBar);
        }
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WXVContainer wXVContainer;
        _refreshHideRunnable();
        int id = view.getId();
        if (id == VideoR.VIDEO_IJK_ID_IV_BACK) {
            onBackPressed();
            return;
        }
        if (id == VideoR.VIDEO_IJK_ID_IV_PLAY || id == VideoR.VIDEO_IJK_ID_IV_PLAY_CIRCLE || id == VideoR.VIDEO_IJK_ID_IV_PLAY_CENTER) {
            _togglePlayStatus();
            return;
        }
        if (id == VideoR.VIDEO_IJK_ID_IV_FULLSCREEN) {
            _toggleFullScreen();
            return;
        }
        if (id == VideoR.VIDEO_IJK_ID_IV_DANMAKU_CONTROL) {
            _toggleDanmakuShow();
            return;
        }
        if (id == VideoR.VIDEO_IJK_ID_TV_RECOVER_SCREEN) {
            this.mVideoView.resetVideoView(true);
            this.mIsNeedRecoverScreen = false;
            this.mTvRecoverScreen.setVisibility(8);
            return;
        }
        if (id == VideoR.VIDEO_IJK_ID_IV_MUTE) {
            boolean z = !this.isMutePlayer;
            this.isMutePlayer = z;
            setMutePlayer(z);
            this.mIVMute.setSelected(this.isMutePlayer);
            return;
        }
        if (id == VideoR.VIDEO_IJK_ID_IV_SCREEN_LOCK) {
            if (this.isShowScreenLockButton) {
                if (((Integer) this.mIvScreenLock.getTag()).intValue() == 1) {
                    this.mIvScreenLock.setTag(0);
                    this.mIvScreenLock.setImageResource(R.drawable.video_screen_unlock);
                    this.mIsForbidTouch = true;
                } else {
                    this.mIvScreenLock.setTag(1);
                    this.mIvScreenLock.setImageResource(R.drawable.video_screen_lock);
                    this.mIsForbidTouch = false;
                }
                _togglePlayerLock();
                this.mIvScreenLock.setVisibility(0);
                getHandler().removeCallbacks(this.screenLockHideRunnable);
                getHandler().postDelayed(this.screenLockHideRunnable, 5000L);
                return;
            }
            return;
        }
        if (view == this && (wXVContainer = this.component) != null && wXVContainer.getEvents().contains(Constants.Event.CLICK)) {
            HashMap mapNewHashMapWithExpectedSize = WXDataStructureUtil.newHashMapWithExpectedSize(1);
            HashMap mapNewHashMapWithExpectedSize2 = WXDataStructureUtil.newHashMapWithExpectedSize(4);
            view.getLocationOnScreen(new int[2]);
            mapNewHashMapWithExpectedSize2.put(Constants.Name.X, Float.valueOf(WXViewUtils.getWebPxByWidth(r5[0], this.component.getInstance().getInstanceViewPortWidthWithFloat())));
            mapNewHashMapWithExpectedSize2.put(Constants.Name.Y, Float.valueOf(WXViewUtils.getWebPxByWidth(r5[1], this.component.getInstance().getInstanceViewPortWidthWithFloat())));
            mapNewHashMapWithExpectedSize2.put("width", Float.valueOf(WXViewUtils.getWebPxByWidth(this.component.getLayoutWidth(), this.component.getInstance().getInstanceViewPortWidthWithFloat())));
            mapNewHashMapWithExpectedSize2.put("height", Float.valueOf(WXViewUtils.getWebPxByWidth(this.component.getLayoutHeight(), this.component.getInstance().getInstanceViewPortWidthWithFloat())));
            mapNewHashMapWithExpectedSize.put("position", mapNewHashMapWithExpectedSize2);
            this.component.fireEvent(Constants.Event.CLICK, mapNewHashMapWithExpectedSize);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mOnPlayerChangedListener.onChanged("onConfigurationChanged", null);
    }

    public int onDestroy() {
        int currentPosition = this.mVideoView.getCurrentPosition();
        this.mVideoView.destroy();
        IjkMediaPlayer.native_profileEnd();
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView != null) {
            iDanmakuView.release();
            this.mDanmakuView = null;
        }
        MarqueeTextView marqueeTextView = this.mTvTitle;
        if (marqueeTextView != null) {
            marqueeTextView.stopMotion();
        }
        this.mHandler.removeMessages(10088);
        this.mHandler.removeMessages(10086);
        this.mHandler.removeMessages(10099);
        this.mAttachActivity.unregisterReceiver(this.mScreenReceiver);
        this.mAttachActivity.unregisterReceiver(this.mNetReceiver);
        this.mAttachActivity.getWindow().clearFlags(128);
        setMutePlayer(false);
        this.mAudioManager.abandonAudioFocus(null);
        WindowManager.LayoutParams attributes = this.mAttachActivity.getWindow().getAttributes();
        attributes.screenBrightness = this.defaultScreenBrightness;
        this.mAttachActivity.getWindow().setAttributes(attributes);
        return currentPosition;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isFullscreen() || !this.mIsForbidTouch || this.mIvPlayCircle.getVisibility() == 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mInitHeight == 0) {
            this.mInitHeight = getHeight();
            this.mWidthPixels = getResources().getDisplayMetrics().widthPixels;
        }
    }

    public void onPause() {
        this.mCurPosition = this.mVideoView.getCurrentPosition();
        this.mVideoView.pause();
        this.mIvPlay.setSelected(false);
        this.mIvPlayCenter.setSelected(false);
        this.mOrientationListener.disable();
        _pauseDanmaku();
    }

    public void onResume() {
        if (this.mIsScreenLocked) {
            this.mIsScreenLocked = false;
        }
        this.mVideoView.setRender(2);
        this.mVideoView.resume();
        if (!this.mIsForbidTouch && !this.mIsForbidOrientation) {
            this.mOrientationListener.enable();
        }
        int i = this.mCurPosition;
        if (i != -1) {
            seekTo(i);
            this.mCurPosition = -1;
        }
    }

    public void pause() {
        this.mCurPosition = this.mVideoView.getCurrentPosition();
        this.mIvPlay.setSelected(false);
        this.mIvPlayCenter.setSelected(false);
        if (this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
        }
        this.mHandler.removeMessages(10099);
        _pauseDanmaku();
        this.mAttachActivity.getWindow().clearFlags(128);
    }

    public void playbackRate(String str) {
        if (this.mVideoView != null) {
            if (Arrays.binarySearch(this.rates, str) >= 0) {
                this.mVideoView.setSpeed(Float.parseFloat(str));
            } else {
                this.mVideoView.setSpeed(1.0f);
            }
        }
    }

    public boolean recoverFromEditVideo() {
        if (this.mVideoStatus == 501) {
            return false;
        }
        if (this.mIsFullscreen) {
            _recoverScreen();
        }
        if (this.mVideoStatus == 502) {
            start();
        }
        this.mVideoStatus = 501;
        return true;
    }

    public void reload() {
        setLoadingVisibility(0);
        if (!this.mIsReady) {
            this.mVideoView.release(false);
            this.mVideoView.setRender(2);
            start();
        } else {
            if (!NetWorkUtils.isNetworkAvailable(this.mAttachActivity)) {
                OnPlayerChangedListener onPlayerChangedListener = this.mOnPlayerChangedListener;
                if (onPlayerChangedListener != null) {
                    onPlayerChangedListener.onChanged("error", DOMException.MSG_NETWORK_ERROR);
                    setLoadingVisibility(8);
                    return;
                }
                return;
            }
            this.mVideoView.reload();
            this.mVideoView.start();
            int i = this.mInterruptPosition;
            if (i > 0) {
                seekTo(i);
                this.mInterruptPosition = 0;
            }
        }
        this.mHandler.removeMessages(10086);
        this.mHandler.sendEmptyMessage(10086);
        this.mHandler.removeMessages(10099);
        this.mHandler.sendEmptyMessage(10099);
    }

    public void reset() {
        IDanmakuView iDanmakuView;
        if (this.mIsEnableDanmaku && (iDanmakuView = this.mDanmakuView) != null) {
            iDanmakuView.release();
            this.mDanmakuView = null;
            this.mIsEnableDanmaku = false;
        }
        this.mIsNeverPlay = true;
        this.mCurPosition = 0;
        stop();
        this.mVideoView.setRender(2);
    }

    public void seekTo(int i) {
        if (this.isRtmpUri) {
            return;
        }
        this.mVideoView.seekTo(i);
        this.mDanmakuTargetPosition = i;
    }

    public void sendDanmaku(JSONObject jSONObject, boolean z) {
        BaseDanmaku baseDanmakuCreateDanmaku;
        if (!this.mIsEnableDanmaku || TextUtils.isEmpty(jSONObject.optString("text")) || !this.mDanmakuView.isPrepared() || (baseDanmakuCreateDanmaku = this.mDanmakuContext.mDanmakuFactory.createDanmaku(this.mDanmakuType)) == null || this.mDanmakuView == null) {
            return;
        }
        if (this.mDanmakuTextSize == -1.0f) {
            this.mDanmakuTextSize = (this.mDanmakuParser.getDisplayer().getDensity() - 0.6f) * 25.0f;
        }
        baseDanmakuCreateDanmaku.text = jSONObject.optString("text", "....");
        baseDanmakuCreateDanmaku.padding = 5;
        baseDanmakuCreateDanmaku.isLive = z;
        baseDanmakuCreateDanmaku.priority = (byte) 0;
        baseDanmakuCreateDanmaku.textSize = this.mDanmakuTextSize;
        baseDanmakuCreateDanmaku.textColor = Color.parseColor(jSONObject.optString("color", "#ffffff"));
        baseDanmakuCreateDanmaku.setTime(this.mDanmakuView.getCurrentTime() + 500);
        this.mDanmakuView.addDanmaku(baseDanmakuCreateDanmaku);
        OnDanmakuListener onDanmakuListener = this.mDanmakuListener;
        if (onDanmakuListener != null) {
            BaseDanmakuConverter baseDanmakuConverter = this.mDanmakuConverter;
            if (baseDanmakuConverter != null) {
                onDanmakuListener.onDataObtain(baseDanmakuConverter.convertDanmaku(baseDanmakuCreateDanmaku));
            } else {
                onDanmakuListener.onDataObtain(baseDanmakuCreateDanmaku);
            }
        }
    }

    public void setCenterPlayBtnVisibility(boolean z) {
        if (!this.mIsNeverPlay || isPlaying() || this.mIvPlayCircle == null) {
            return;
        }
        this.isCenterPlayBtnVisibility = z;
        this.mIvPlayCircle.setVisibility(z ? 0 : 8);
    }

    public void setComponent(WXVContainer wXVContainer) {
        this.component = wXVContainer;
    }

    public void setControls(boolean z) {
        this.controlShowEnable = z;
        this.mIsShowBar = z;
        this.mOnPlayerChangedListener.onChanged("controlstoggle", "{'show':" + this.mIsShowBar + Operators.BLOCK_END_STR);
        _setControlBarVisible(this.mIsShowBar);
        if (this.mIsShowBar) {
            this.mHandler.sendEmptyMessage(10086);
        }
    }

    public IjkPlayerView setDanmakuCustomParser(BaseDanmakuParser baseDanmakuParser, ILoader iLoader, BaseDanmakuConverter baseDanmakuConverter) {
        this.mDanmakuParser = baseDanmakuParser;
        this.mDanmakuLoader = iLoader;
        this.mDanmakuConverter = baseDanmakuConverter;
        return this;
    }

    public IjkPlayerView setDanmakuListener(OnDanmakuListener onDanmakuListener) {
        this.mDanmakuListener = onDanmakuListener;
        return this;
    }

    public IjkPlayerView setDanmakuSource(InputStream inputStream) {
        if (inputStream == null) {
            return this;
        }
        if (!this.mIsEnableDanmaku) {
            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
        }
        if (this.mDanmakuLoader == null) {
            this.mDanmakuLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_ACFUN);
        }
        try {
            this.mDanmakuLoader.load(inputStream);
        } catch (IllegalDataException e2) {
            e2.printStackTrace();
        }
        IDataSource<?> dataSource = this.mDanmakuLoader.getDataSource();
        StandardDanmaKuParser standardDanmaKuParser = new StandardDanmaKuParser();
        this.mDanmakuParser = standardDanmaKuParser;
        standardDanmaKuParser.load(dataSource);
        return this;
    }

    public void setDirection(int i) {
        this.mOrientation = i;
    }

    public void setDuration(int i) {
        if (!this.mIsNeverPlay || isPlaying()) {
            return;
        }
        if (i > 0) {
            this.duration = i;
        } else {
            this.duration = -1;
        }
    }

    public IjkPlayerView setFlowStrategy(EnumPlayStrategy enumPlayStrategy) {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView != null) {
            ijkVideoView.setFlowStrategy(enumPlayStrategy);
        }
        return this;
    }

    public void setFullScreenPageGesture(boolean z) {
        this.isFullScreenPageGesture = z;
    }

    public void setFullscreenBntVisibility(boolean z) {
        if (this.mIvFullscreen != null) {
            this.mIvFullscreen.setVisibility(z ? 0 : 4);
        }
    }

    public IjkPlayerView setHeader(String str) {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView != null) {
            ijkVideoView.setHeaderInfo(str);
        }
        return this;
    }

    public IjkPlayerView setHttpCacheEnable(boolean z) {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView != null) {
            ijkVideoView.setIsHttpCacheOpen(z);
        }
        return this;
    }

    public void setIsEnableProgressGesture(boolean z) {
        this.isProgressGesture = z;
    }

    public void setIsShowScreenLockButton(boolean z) {
        this.isShowScreenLockButton = z;
    }

    public void setLoadingVisibility(boolean z) {
        this.isLoadingVisibility = z;
    }

    public void setMutePlayer(boolean z) {
        this.isMutePlayer = z;
        this.mIVMute.setSelected(z);
        this.mVideoView.setvolume(z ? 0.0f : 1.0f);
    }

    public void setOnBufferingUpdateListener(IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.bufferingUpdateListener = onBufferingUpdateListener;
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.mCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.mVideoView.setOnErrorListener(onErrorListener);
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.mOutsideInfoListener = onInfoListener;
    }

    public void setOnPlayerChangedListener(OnPlayerChangedListener onPlayerChangedListener) {
        this.mOnPlayerChangedListener = onPlayerChangedListener;
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.mVideoView.setOnPreparedListener(onPreparedListener);
    }

    public void setPageGesture(boolean z) {
        this.isPageGesture = z;
    }

    public void setPlayBntVisibility(boolean z) {
        if (this.mIvPlay != null) {
            this.isPlayBtnVisibility = z;
            int i = z ? 0 : 4;
            if (this.isPlayBtnCenter) {
                this.mIvPlayCenter.setVisibility(i);
            } else {
                this.mIvPlay.setVisibility(i);
            }
        }
    }

    public void setPlayBtnPosition(String str) {
        boolean zEquals = str.equals("center");
        this.isPlayBtnCenter = zEquals;
        if (zEquals) {
            this.mIvPlay.setVisibility(8);
        } else if (this.isPlayBtnVisibility) {
            this.mIvPlayCenter.setVisibility(8);
            this.mIvPlay.setVisibility(0);
        }
    }

    public IjkPlayerView setPlayerRootView(ViewGroup viewGroup) {
        this.mRootLayout = viewGroup;
        return this;
    }

    public void setProgressVisibility(boolean z) {
        if (this.mPlayerSeek == null || this.isRtmpUri) {
            return;
        }
        this.isShowProgress = z;
        this.mPlayerSeek.setVisibility(z ? 0 : 4);
    }

    public void setScaleType(String str) {
        if (this.mVideoView != null) {
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != 3143043) {
                if (iHashCode != 94852023) {
                    if (iHashCode == 951526612 && str.equals("contain")) {
                        b = 0;
                    }
                } else if (str.equals(IApp.ConfigProperty.CONFIG_COVER)) {
                    b = 2;
                }
            } else if (str.equals("fill")) {
                b = 1;
            }
            if (b == 0) {
                this.mVideoView.setAspectRatio(0);
                this.mPlayerThumb.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else if (b == 1) {
                this.mVideoView.setAspectRatio(3);
                this.mPlayerThumb.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                if (b != 2) {
                    return;
                }
                this.mVideoView.setAspectRatio(1);
                this.mPlayerThumb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
    }

    public IjkPlayerView setTitle(String str) {
        this.mTvTitle.setText(str);
        return this;
    }

    public IjkPlayerView setVideoFileDescriptor(AssetsDataSourceProvider assetsDataSourceProvider) {
        int i = this.mCurPosition;
        if (i != -1) {
            seekTo(i);
            this.mCurPosition = -1;
        } else {
            seekTo(0);
        }
        this.mVideoView.setVideoFileDescriptor(assetsDataSourceProvider);
        return this;
    }

    public IjkPlayerView setVideoPath(String str) {
        return setVideoPath(Uri.parse(str));
    }

    public void setVideoVisibility() {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView != null) {
            ijkVideoView.setVisibility(0);
        }
    }

    public void setmDanmuList(String str) {
        this.mDanmuList = str;
    }

    public void setmIsDoubleTapEnable(boolean z) {
        this.mIsDoubleTapEnable = z;
    }

    public IjkPlayerView showOrHideDanmaku(boolean z) {
        if (z) {
            this.mIvDanmakuControl.setSelected(false);
            this.mDanmakuView.show();
        } else {
            this.mIvDanmakuControl.setSelected(true);
            this.mDanmakuView.hide();
        }
        return this;
    }

    public void showScreenLockView() {
        if (!this.isShowScreenLockButton) {
            this.mIvScreenLock.setVisibility(8);
            return;
        }
        this.mIvScreenLock.setVisibility(0);
        getHandler().removeCallbacks(this.screenLockHideRunnable);
        getHandler().postDelayed(this.screenLockHideRunnable, 5000L);
    }

    public void start() {
        if (this.mIsPlayComplete) {
            IDanmakuView iDanmakuView = this.mDanmakuView;
            if (iDanmakuView != null && iDanmakuView.isPrepared()) {
                this.mDanmakuView.seekTo(0L);
                this.mDanmakuView.pause();
            }
            this.mIsPlayComplete = false;
        }
        if (!this.mVideoView.isPlaying()) {
            this.mIvPlay.setSelected(true);
            this.mIvPlayCenter.setSelected(true);
            this.mVideoView.start();
            this.mHandler.sendEmptyMessage(10086);
            this.mHandler.sendEmptyMessage(10099);
        }
        this.mIvPlayCircle.setVisibility(8);
        if (this.mIsNeverPlay) {
            this.mIsNeverPlay = false;
            if (this.mVideoView.getCurrentState() != 331) {
                setLoadingVisibility(0);
            }
            this.mIsShowBar = false;
            _loadDanmaku();
        }
        this.mAttachActivity.getWindow().addFlags(128);
    }

    public void stop() {
        pause();
        this.mVideoView.stopPlayback();
    }

    public IjkPlayerView switchVideoFileDescriptor(AssetsDataSourceProvider assetsDataSourceProvider) {
        reset();
        _setControlBarVisible(true);
        this.duration = -1;
        return setVideoFileDescriptor(assetsDataSourceProvider);
    }

    public IjkPlayerView switchVideoPath(String str) {
        return switchVideoPath(Uri.parse(str));
    }

    public IjkPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 10086) {
                    int i_setProgress = IjkPlayerView.this._setProgress();
                    if (!IjkPlayerView.this.mIsSeeking && IjkPlayerView.this.mIsShowBar && IjkPlayerView.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(10086), 1000 - (i_setProgress % 1000));
                        return;
                    }
                    return;
                }
                if (i == 10087) {
                    if (IjkPlayerView.this.mOrientationListener != null) {
                        IjkPlayerView.this.mOrientationListener.enable();
                    }
                } else if (i == 10088) {
                    if (IjkPlayerView.this.mIsNetConnected) {
                        IjkPlayerView.this.reload();
                    }
                    sendMessageDelayed(obtainMessage(10088), 3000L);
                } else if (i == 10099) {
                    IjkPlayerView.this.progressCallBack();
                    sendMessageDelayed(obtainMessage(10099), 250L);
                }
            }
        };
        this.mIsForbidTouch = false;
        this.mIsDoubleTapEnable = false;
        this.mIsShowBar = true;
        this.mIsPlayComplete = false;
        this.mTargetPosition = -1L;
        this.mCurPosition = -1;
        this.mCurVolume = -1;
        this.mCurBrightness = -1.0f;
        this.mIsNeverPlay = true;
        this.mIsForbidOrientation = true;
        this.mIsAlwaysFullScreen = false;
        this.mExitTime = 0L;
        this.mVideoMatrix = new Matrix();
        this.mSaveMatrix = new Matrix();
        this.mIsNeedRecoverScreen = false;
        this.mIsReady = false;
        this.isRtmpUri = false;
        this.isPageGesture = false;
        this.isFullScreenPageGesture = true;
        this.isShowScreenLockButton = false;
        this.isProgressGesture = true;
        this.screenLockHideRunnable = new Runnable() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.2
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView.this.mIvScreenLock.setVisibility(8);
            }
        };
        this.isPlayBtnCenter = false;
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.4
            public long curPosition;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                String str;
                if (z) {
                    long duration = IjkPlayerView.this.getDuration();
                    IjkPlayerView.this.mTargetPosition = (((long) i) * duration) / 1000;
                    int i2 = (int) ((IjkPlayerView.this.mTargetPosition - this.curPosition) / 1000);
                    if (IjkPlayerView.this.mTargetPosition > this.curPosition) {
                        str = StringUtils.generateTime(IjkPlayerView.this.mTargetPosition) + "/" + StringUtils.generateTime(duration) + "\n" + Operators.PLUS + i2 + DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_video_second_unit);
                    } else {
                        str = StringUtils.generateTime(IjkPlayerView.this.mTargetPosition) + "/" + StringUtils.generateTime(duration) + "\n" + i2 + DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_video_second_unit);
                    }
                    IjkPlayerView.this._setFastForward(str);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                IjkPlayerView.this.mIsSeeking = true;
                IjkPlayerView.this._showControlBar(3600000);
                IjkPlayerView.this.mHandler.removeMessages(10086);
                this.curPosition = IjkPlayerView.this.mVideoView.getCurrentPosition();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                IjkPlayerView.this._hideTouchView();
                IjkPlayerView.this.mIsSeeking = false;
                IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                ijkPlayerView.seekTo((int) ijkPlayerView.mTargetPosition);
                IjkPlayerView.this.mTargetPosition = -1L;
                IjkPlayerView.this._setProgress();
                IjkPlayerView.this._showControlBar(5000);
            }
        };
        this.mHideBarRunnable = new Runnable() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.5
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView.this._hideAllView(false);
            }
        };
        this.controlShowEnable = false;
        this.isShowProgress = true;
        this.isPlayBtnVisibility = true;
        this.isCenterPlayBtnVisibility = true;
        this.fullCallFormat = "{fullScreen:%b, direction:'%s'}";
        this.mOrientation = -90;
        this.orientation = 90;
        this.defaultSystemUI = 0;
        this.defaultDisplayCutoutMode = 0;
        this.rates = new String[]{"0.5", "0.8", "1.0", "1.25", "1.5", UMCrashManager.CM_VERSION};
        this.mPlayerGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.8
            public boolean isDownTouch;
            public boolean isLandscape;
            public boolean isRecoverFromDanmaku;
            public boolean isVolume;

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!IjkPlayerView.this.mIsNeverPlay && !this.isRecoverFromDanmaku && !IjkPlayerView.this.mIsForbidTouch && IjkPlayerView.this.mIsDoubleTapEnable) {
                    IjkPlayerView.this._refreshHideRunnable();
                    IjkPlayerView.this._togglePlayStatus();
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                this.isDownTouch = true;
                this.isRecoverFromDanmaku = IjkPlayerView.this.recoverFromEditVideo();
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (!IjkPlayerView.this.mIsForbidTouch && !IjkPlayerView.this.mIsNeverPlay) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY() - motionEvent2.getY();
                    float x2 = x - motionEvent2.getX();
                    if (this.isDownTouch) {
                        this.isLandscape = Math.abs(f) >= Math.abs(f2);
                        this.isVolume = x > ((float) IjkPlayerView.this.getResources().getDisplayMetrics().widthPixels) * 0.5f;
                        this.isDownTouch = false;
                    }
                    if (this.isLandscape) {
                        IjkPlayerView.this._onProgressSlide((-x2) / r0.mVideoView.getWidth());
                    } else {
                        float height = y / IjkPlayerView.this.mVideoView.getHeight();
                        if (this.isVolume) {
                            IjkPlayerView.this._onVolumeSlide(height);
                        } else {
                            IjkPlayerView.this._onBrightnessSlide(height);
                        }
                    }
                }
                return super.onScroll(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (IjkPlayerView.this.component != null && IjkPlayerView.this.component.getInstance() != null && IjkPlayerView.this.component.getEvents().contains(Constants.Event.CLICK)) {
                    HashMap mapNewHashMapWithExpectedSize = WXDataStructureUtil.newHashMapWithExpectedSize(1);
                    HashMap mapNewHashMapWithExpectedSize2 = WXDataStructureUtil.newHashMapWithExpectedSize(4);
                    mapNewHashMapWithExpectedSize2.put(Constants.Name.X, Float.valueOf(WXViewUtils.getWebPxByWidth(0.0f, IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth())));
                    mapNewHashMapWithExpectedSize2.put(Constants.Name.Y, Float.valueOf(WXViewUtils.getWebPxByWidth(0.0f, IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth())));
                    mapNewHashMapWithExpectedSize2.put("width", Float.valueOf(WXViewUtils.getWebPxByWidth(IjkPlayerView.this.component.getLayoutWidth(), IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth())));
                    mapNewHashMapWithExpectedSize2.put("height", Float.valueOf(WXViewUtils.getWebPxByWidth(IjkPlayerView.this.component.getLayoutHeight(), IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth())));
                    mapNewHashMapWithExpectedSize.put("position", mapNewHashMapWithExpectedSize2);
                    IjkPlayerView.this.component.fireEvent(Constants.Event.CLICK, mapNewHashMapWithExpectedSize);
                }
                if (IjkPlayerView.this.isFullscreen() && IjkPlayerView.this.component != null && IjkPlayerView.this.component.getInstance() != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(WXGestureType.GestureInfo.SCREEN_X, WXViewUtils.getWebPxByWidth(motionEvent.getX(), IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth()));
                        jSONObject.put(WXGestureType.GestureInfo.SCREEN_Y, WXViewUtils.getWebPxByWidth(motionEvent.getY(), IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth()));
                        jSONObject.put("screenWidth", WXViewUtils.getWebPxByWidth(IjkPlayerView.this.getWidth(), IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth()));
                        jSONObject.put("screenHeight", WXViewUtils.getWebPxByWidth(IjkPlayerView.this.getHeight(), IjkPlayerView.this.component.getInstance().getInstanceViewPortWidth()));
                    } catch (Exception unused) {
                    }
                    IjkPlayerView.this.mOnPlayerChangedListener.onChanged("fullscreenclick", jSONObject.toString());
                }
                if (this.isRecoverFromDanmaku) {
                    return true;
                }
                IjkPlayerView.this._toggleControlBar();
                if (IjkPlayerView.this.mIsFullscreen) {
                    IjkPlayerView.this.showScreenLockView();
                }
                return true;
            }
        };
        this.mHideTouchViewRunnable = new Runnable() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.9
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView.this._hideTouchView();
            }
        };
        this.mPlayerTouchListener = new View.OnTouchListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.10
            public static final int INVALID_POINTER = 2;
            public static final int NORMAL = 1;
            public static final int ZOOM_AND_ROTATE = 3;
            public float oldDist;
            public float scale;
            public int mode = 1;
            public PointF midPoint = new PointF(0.0f, 0.0f);
            public float degree = 0.0f;
            public int fingerFlag = -1;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int iB = du.b(motionEvent);
                if (iB == 0) {
                    this.mode = 1;
                    IjkPlayerView.this.mHandler.removeCallbacks(IjkPlayerView.this.mHideBarRunnable);
                } else if (iB != 2) {
                    if (iB != 5) {
                        if (iB == 6) {
                            if (this.mode == 3) {
                                IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                                ijkPlayerView.mIsNeedRecoverScreen = ijkPlayerView.mVideoView.adjustVideoView(this.scale);
                                if (IjkPlayerView.this.mIsNeedRecoverScreen && IjkPlayerView.this.mIsShowBar) {
                                    IjkPlayerView.this.mTvRecoverScreen.setVisibility(0);
                                }
                            }
                            this.mode = 2;
                        }
                    } else if (motionEvent.getPointerCount() == 3 && IjkPlayerView.this.mIsFullscreen) {
                        IjkPlayerView.this._hideTouchView();
                        this.mode = 3;
                        MotionEventUtils.midPoint(this.midPoint, motionEvent);
                        int iCalcFingerFlag = MotionEventUtils.calcFingerFlag(motionEvent);
                        this.fingerFlag = iCalcFingerFlag;
                        this.degree = MotionEventUtils.rotation(motionEvent, iCalcFingerFlag);
                        this.oldDist = MotionEventUtils.calcSpacing(motionEvent, this.fingerFlag);
                        IjkPlayerView ijkPlayerView2 = IjkPlayerView.this;
                        ijkPlayerView2.mSaveMatrix = ijkPlayerView2.mVideoView.getVideoTransform();
                    } else {
                        this.mode = 2;
                    }
                } else if (this.mode == 3) {
                    IjkPlayerView.this.mVideoView.setVideoRotation((int) (MotionEventUtils.rotation(motionEvent, this.fingerFlag) - this.degree));
                    IjkPlayerView.this.mVideoMatrix.set(IjkPlayerView.this.mSaveMatrix);
                    this.scale = MotionEventUtils.calcSpacing(motionEvent, this.fingerFlag) / this.oldDist;
                    Matrix matrix = IjkPlayerView.this.mVideoMatrix;
                    float f = this.scale;
                    PointF pointF = this.midPoint;
                    matrix.postScale(f, f, pointF.x, pointF.y);
                    IjkPlayerView.this.mVideoView.setVideoTransform(IjkPlayerView.this.mVideoMatrix);
                }
                if (this.mode == 1 && !IjkPlayerView.this.mGestureDetector.onTouchEvent(motionEvent) && du.b(motionEvent) == 1) {
                    IjkPlayerView.this._endGesture();
                }
                return true;
            }
        };
        this.timeUpdateF = "{currentTime:%f,duration:%f}";
        this.duration = -1;
        this.isLoadingVisibility = true;
        this.isMutePlayer = false;
        this.mIsRenderingStart = false;
        this.mIsBufferingStart = false;
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.11
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                IjkPlayerView.this._switchStatus(i);
                if (IjkPlayerView.this.mOutsideInfoListener == null) {
                    return true;
                }
                IjkPlayerView.this.mOutsideInfoListener.onInfo(iMediaPlayer, i, i2);
                return true;
            }
        };
        this.onBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView.12
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                if (IjkPlayerView.this.bufferingUpdateListener != null) {
                    IjkPlayerView.this.bufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i);
                }
            }
        };
        this.mVideoSource = new SparseArray<>();
        this.mVideoStatus = 501;
        this.mDanmakuTag = 701;
        this.mIsEnableDanmaku = false;
        this.mDanmakuTextColor = -1;
        this.mDanmakuTextSize = -1.0f;
        this.mDanmakuType = 1;
        this.mBasicOptionsWidth = -1;
        this.mMoreOptionsWidth = -1;
        this.mDanmakuTargetPosition = -1L;
        this.mDanmuList = "";
        this.mIsScreenLocked = false;
        _initView(context);
    }

    private void setLoadingVisibility(int i) {
        ProgressBar progressBar = this.mLoadingView;
        if (!this.isLoadingVisibility) {
            i = 8;
        }
        progressBar.setVisibility(i);
    }

    public IjkPlayerView setVideoPath(Uri uri) {
        if (uri.toString().startsWith("rtmp:")) {
            this.isRtmpUri = true;
            this.mPlayerSeek.setEnabled(false);
            this.mPlayerSeek.setVisibility(4);
            this.mTvEndTime.setVisibility(4);
            this.mTvCurTime.setVisibility(4);
        } else {
            this.isRtmpUri = false;
            this.mPlayerSeek.setEnabled(true);
            this.mPlayerSeek.setVisibility(this.isShowProgress ? 0 : 4);
            this.mTvEndTime.setVisibility(0);
            this.mTvCurTime.setVisibility(0);
        }
        int i = this.mCurPosition;
        if (i != -1) {
            seekTo(i);
            this.mCurPosition = -1;
        } else {
            seekTo(0);
        }
        this.mVideoView.setVideoURI(uri);
        return this;
    }

    public IjkPlayerView switchVideoPath(Uri uri) {
        reset();
        _setControlBarVisible(true);
        this.duration = -1;
        return setVideoPath(uri);
    }

    public void enableDanmaku(boolean z) {
        this.mIsEnableDanmaku = z;
        if (z) {
            _initDanmaku();
        } else {
            this.mIvDanmakuControl.setVisibility(8);
        }
    }

    public IjkPlayerView setDanmakuSource(String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        if (this.mIsEnableDanmaku) {
            if (this.mDanmakuLoader == null) {
                this.mDanmakuLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
            }
            try {
                this.mDanmakuLoader.load(str);
            } catch (IllegalDataException e2) {
                e2.printStackTrace();
            }
            IDataSource<?> dataSource = this.mDanmakuLoader.getDataSource();
            BiliDanmukuParser biliDanmukuParser = new BiliDanmukuParser();
            this.mDanmakuParser = biliDanmukuParser;
            biliDanmukuParser.load(dataSource);
            return this;
        }
        throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
    }

    public IjkPlayerView(Context context, AttributeSet attributeSet, VideoPlayerView videoPlayerView) {
        this(context, attributeSet);
        this.parentView = videoPlayerView;
    }
}
