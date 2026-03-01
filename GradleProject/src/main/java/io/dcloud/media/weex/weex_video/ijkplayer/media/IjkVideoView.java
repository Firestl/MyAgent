package io.dcloud.media.weex.weex_video.ijkplayer.media;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import com.lzy.okgo.model.HttpHeaders;
import dc.squareup.HttpConstants;
import dc.squareup.okhttp3.internal.http.BridgeInterceptor;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.feature.weex_media.option.EnumPlayStrategy;
import io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView;
import io.dcloud.media.weex.weex_video.ijkplayer.utils.VideoCacheUtil;
import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* JADX INFO: loaded from: classes3.dex */
public class IjkVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    public static final int RENDER_NONE = 0;
    public static final int RENDER_SURFACE_VIEW = 1;
    public static final int RENDER_TEXTURE_VIEW = 2;
    public static final int[] s_allAspectRatio = {0, 1, 2, 4, 5};
    public String TAG;
    public IMediaPlayer.OnBufferingUpdateListener bufferingUpdateListener;
    public ImageView coverImage;
    public String customUA;
    public EnumPlayStrategy enumPlayStrategy;
    public AssetsDataSourceProvider fd;
    public String headerInfo;
    public boolean isHttpCacheOpen;
    public boolean isMediaReady;
    public boolean isSetSeekTo;
    public boolean isStoped;
    public Context mAppContext;
    public IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    public boolean mCanPause;
    public boolean mCanSeekBack;
    public boolean mCanSeekForward;
    public IMediaPlayer.OnCompletionListener mCompletionListener;
    public int mCurrentAspectRatio;
    public int mCurrentBufferPercentage;
    public int mCurrentState;
    public boolean mEnableBackgroundPlay;
    public IMediaPlayer.OnErrorListener mErrorListener;
    public Map<String, String> mHeaders;
    public IMediaPlayer.OnInfoListener mInfoListener;
    public boolean mIsMediaCodecHandleResolutionChange;
    public boolean mIsNormalScreen;
    public boolean mIsUsingMediaCodec;
    public boolean mIsUsingMediaCodecAutoRotate;
    public boolean mIsUsingMediaDataSource;
    public boolean mIsUsingOpenSLES;
    public IMediaController mMediaController;
    public IMediaPlayer mMediaPlayer;
    public IMediaPlayer.OnCompletionListener mOnCompletionListener;
    public IMediaPlayer.OnErrorListener mOnErrorListener;
    public IMediaPlayer.OnInfoListener mOnInfoListener;
    public IMediaPlayer.OnPreparedListener mOnPreparedListener;
    public Matrix mOriginalMatrix;
    public String mPixelFormat;
    public long mPrepareEndTime;
    public long mPrepareStartTime;
    public IMediaPlayer.OnPreparedListener mPreparedListener;
    public IRenderView mRenderView;
    public IRenderView.IRenderCallback mSHCallback;
    public Matrix mSaveMatrix;
    public int mScreenOrHeight;
    public int mScreenOrWidth;
    public IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    public long mSeekEndTime;
    public long mSeekStartTime;
    public int mSeekWhenPrepared;
    public IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    public int mSurfaceHeight;
    public IRenderView.ISurfaceHolder mSurfaceHolder;
    public int mSurfaceWidth;
    public int mTargetState;
    public Uri mUri;
    public int mVideoHeight;
    public int mVideoRotationDegree;
    public int mVideoSarDen;
    public int mVideoSarNum;
    public float mVideoScale;
    public int mVideoTargetRotationDegree;
    public int mVideoWidth;
    public float volume;

    public IjkVideoView(Context context) {
        super(context);
        this.TAG = "TTAG";
        this.mCurrentState = 330;
        this.mTargetState = 330;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = true;
        this.mIsUsingMediaCodecAutoRotate = false;
        this.mIsMediaCodecHandleResolutionChange = false;
        this.mIsUsingOpenSLES = false;
        this.mPixelFormat = "";
        this.isHttpCacheOpen = false;
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.isStoped = false;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.enumPlayStrategy = EnumPlayStrategy.DEFAULT;
        this.customUA = "";
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = 333;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 334) {
                            IjkVideoView.this.start();
                            if (IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show();
                                return;
                            }
                            return;
                        }
                        if (IjkVideoView.this.isPlaying()) {
                            return;
                        }
                        if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
                IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, 337, -1);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = 336;
                IjkVideoView.this.mTargetState = 336;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
                }
                if (i == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    return true;
                }
                if (i == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i2);
                    return true;
                }
                if (i == 10002) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                    return true;
                }
                switch (i) {
                    case 700:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                        break;
                    case 702:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                        break;
                    case 703:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                        break;
                    default:
                        switch (i) {
                            case 800:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                break;
                            case 801:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                break;
                            case 802:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                break;
                        }
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("TTAG", "Error: " + i + "," + i2);
                IjkVideoView.this.mCurrentState = 331;
                IjkVideoView.this.mTargetState = 331;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener == null || IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i, i2)) {
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                IjkVideoView.this.mCurrentBufferPercentage = i;
                if (IjkVideoView.this.bufferingUpdateListener != null) {
                    IjkVideoView.this.bufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.9
            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i2;
                IjkVideoView.this.mSurfaceHeight = i3;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i2 || IjkVideoView.this.mVideoHeight != i3)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.isMediaReady = true;
        this.volume = 0.0f;
        this.isSetSeekTo = false;
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _notifyMediaStatus() {
        IMediaPlayer.OnInfoListener onInfoListener = this.mOnInfoListener;
        if (onInfoListener != null) {
            onInfoListener.onInfo(this.mMediaPlayer, this.mCurrentState, -1);
        }
    }

    private Bitmap adjustPhotoRotation(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i, bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f);
        try {
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    private void attachMediaController() {
        IMediaController iMediaController;
        if (this.mMediaPlayer == null || (iMediaController = this.mMediaController) == null) {
            return;
        }
        iMediaController.setMediaPlayer(this);
        this.mMediaController.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
        this.mMediaController.setEnabled(isInPlaybackState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindSurfaceHolder(IMediaPlayer iMediaPlayer, IRenderView.ISurfaceHolder iSurfaceHolder) {
        if (iMediaPlayer == null) {
            return;
        }
        if (iSurfaceHolder == null) {
            iMediaPlayer.setDisplay(null);
        } else {
            iSurfaceHolder.bindToMediaPlayer(iMediaPlayer);
        }
    }

    private String buildTimeMilli(long j) {
        long j2 = j / 1000;
        long j3 = j2 / 3600;
        long j4 = (j2 % 3600) / 60;
        long j5 = j2 % 60;
        return j <= 0 ? "--:--" : j3 >= 100 ? String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5)) : j3 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5)) : String.format(Locale.US, "%02d:%02d", Long.valueOf(j4), Long.valueOf(j5));
    }

    private void initBackground() {
    }

    private void initRenders() {
        setRender(2);
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
        initBackground();
        initRenders();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 330;
        this.mTargetState = 330;
        _notifyMediaStatus();
        ImageView imageView = new ImageView(getContext());
        this.coverImage = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.coverImage.setBackgroundColor(-16777216);
        this.coverImage.setVisibility(8);
        addView(this.coverImage, new ViewGroup.LayoutParams(-1, -1));
    }

    private boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || (i = this.mCurrentState) == 331 || i == 330 || i == 332) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    public void openVideo() {
        if ((this.mUri == null && this.fd == null) || this.mSurfaceHolder == null) {
            if (this.mUri == null && this.fd == null) {
                this.mCurrentState = 331;
                this.mTargetState = 331;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
                return;
            }
            return;
        }
        release(false);
        try {
            try {
                try {
                    this.mMediaPlayer = createPlayer(2);
                    if (!this.isMediaReady) {
                        setvolume(this.volume);
                        this.isMediaReady = true;
                    }
                    getContext();
                    this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                    this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                    this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                    this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                    this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                    this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                    this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
                    this.mCurrentBufferPercentage = 0;
                    if (this.mUri != null) {
                        String scheme = this.mUri.getScheme();
                        if (Build.VERSION.SDK_INT >= 23 && this.mIsUsingMediaDataSource && (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase("file"))) {
                            this.mMediaPlayer.setDataSource(new FileMediaDataSource(new File(this.mUri.toString())));
                        } else if (Build.VERSION.SDK_INT >= 14) {
                            this.mMediaPlayer.setDataSource(this.mAppContext, this.mUri, this.mHeaders);
                        } else {
                            this.mMediaPlayer.setDataSource(this.mUri.toString());
                        }
                    } else if (this.fd != null) {
                        this.mMediaPlayer.setDataSource(this.fd);
                    }
                    bindSurfaceHolder(this.mMediaPlayer, this.mSurfaceHolder);
                    this.mMediaPlayer.setAudioStreamType(3);
                    this.mMediaPlayer.setScreenOnWhilePlaying(true);
                    this.mPrepareStartTime = System.currentTimeMillis();
                    this.mMediaPlayer.prepareAsync();
                    this.mCurrentState = 332;
                    attachMediaController();
                } catch (IllegalArgumentException unused) {
                    this.mCurrentState = 331;
                    this.mTargetState = 331;
                    this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
                }
            } catch (IOException unused2) {
                this.mCurrentState = 331;
                this.mTargetState = 331;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        } finally {
            _notifyMediaStatus();
        }
    }

    private void showCoverImage() {
        if (this.mRenderView != null) {
            if (this.mCurrentAspectRatio == 1) {
                this.coverImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                this.coverImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            Bitmap videoScreenshot = this.mRenderView.getVideoScreenshot();
            int i = this.mVideoRotationDegree;
            if (i > 0) {
                videoScreenshot = adjustPhotoRotation(videoScreenshot, i);
            }
            if (videoScreenshot != null) {
                this.coverImage.setImageBitmap(videoScreenshot);
            } else {
                this.coverImage.setBackgroundColor(-16777216);
            }
            this.coverImage.bringToFront();
            this.coverImage.setVisibility(0);
        }
    }

    private void toggleMediaControlsVisibility() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    public boolean adjustVideoView(float f) {
        float f2 = this.mVideoScale * f;
        this.mVideoScale = f2;
        int i = (this.mVideoTargetRotationDegree + 360) % 360;
        if (f2 == 1.0f && i == 0) {
            return false;
        }
        if (i > 315 || i <= 45) {
            this.mVideoRotationDegree = 0;
        } else if (i > 45 && i <= 135) {
            this.mVideoRotationDegree = 90;
        } else if (i > 135 && i <= 225) {
            this.mVideoRotationDegree = 180;
        } else if (i <= 225 || i > 315) {
            this.mVideoRotationDegree = 0;
        } else {
            this.mVideoRotationDegree = 270;
        }
        int i2 = this.mVideoRotationDegree;
        final int i3 = i2 - this.mVideoTargetRotationDegree;
        this.mVideoTargetRotationDegree = i2;
        final Matrix videoTransform = getVideoTransform();
        if (this.mScreenOrWidth == 0 || this.mScreenOrHeight == 0) {
            this.mScreenOrWidth = this.mRenderView.getView().getWidth();
            this.mScreenOrHeight = this.mRenderView.getView().getHeight();
        }
        if (this.mIsNormalScreen) {
            float[] fArr = new float[2];
            videoTransform.mapPoints(fArr);
            float f3 = this.mScreenOrWidth;
            float f4 = this.mVideoScale;
            final float f5 = ((f3 * (1.0f - f4)) / 2.0f) - fArr[0];
            final float f6 = ((this.mScreenOrHeight * (1.0f - f4)) / 2.0f) - fArr[1];
            if (this.mSaveMatrix == null) {
                this.mSaveMatrix = new Matrix();
            }
            ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(300L);
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    IjkVideoView.this.mSaveMatrix.set(videoTransform);
                    IjkVideoView.this.mSaveMatrix.postTranslate(f5 * fFloatValue, f6 * fFloatValue);
                    IjkVideoView.this.mRenderView.setTransform(IjkVideoView.this.mSaveMatrix);
                    IjkVideoView.this.mRenderView.setVideoRotation((int) (IjkVideoView.this.mVideoRotationDegree - (i3 * (1.0f - fFloatValue))));
                }
            });
            duration.start();
        } else {
            float f7 = this.mVideoScale;
            videoTransform.preScale(f7, f7);
            float f8 = this.mScreenOrWidth;
            float f9 = this.mVideoScale;
            videoTransform.postTranslate((f8 * (1.0f - f9)) / 2.0f, (this.mScreenOrHeight * (1.0f - f9)) / 2.0f);
            this.mRenderView.setTransform(videoTransform);
            this.mIsNormalScreen = true;
        }
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.mCanPause;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    public IMediaPlayer createPlayer(int i) {
        if (i == 1) {
            return new AndroidMediaPlayer();
        }
        IjkMediaPlayer ijkMediaPlayer = null;
        if (this.mUri != null || this.fd != null) {
            ijkMediaPlayer = new IjkMediaPlayer();
            if (TextUtils.isEmpty(this.customUA)) {
                ijkMediaPlayer.setOption(1, "user_agent", HttpConstants.getDefaultUA());
            } else {
                ijkMediaPlayer.setOption(1, "user_agent", this.customUA);
            }
            if (this.mIsUsingMediaCodec) {
                ijkMediaPlayer.setOption(4, "mediacodec", 1L);
                if (this.mIsUsingMediaCodecAutoRotate) {
                    ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 1L);
                } else {
                    ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 0L);
                }
                if (this.mIsMediaCodecHandleResolutionChange) {
                    ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1L);
                } else {
                    ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 0L);
                }
            } else {
                ijkMediaPlayer.setOption(4, "mediacodec", 0L);
            }
            if (this.mIsUsingOpenSLES) {
                ijkMediaPlayer.setOption(4, "opensles", 1L);
            } else {
                ijkMediaPlayer.setOption(4, "opensles", 0L);
            }
            if (TextUtils.isEmpty(this.mPixelFormat)) {
                ijkMediaPlayer.setOption(4, "overlay-format", 842225234L);
            } else {
                ijkMediaPlayer.setOption(4, "overlay-format", this.mPixelFormat);
            }
            EnumPlayStrategy enumPlayStrategy = this.enumPlayStrategy;
            if (enumPlayStrategy == EnumPlayStrategy.PLAY_SMOOTH) {
                ijkMediaPlayer.setOption(4, "infbuf", 1L);
                ijkMediaPlayer.setOption(4, "max-fps", 25L);
                ijkMediaPlayer.setOption(4, "opensles", 1L);
                ijkMediaPlayer.setOption(4, "mediacodec-mpeg4", 1L);
                ijkMediaPlayer.setOption(4, "mediacodec-hevc", 1L);
            } else if (enumPlayStrategy != EnumPlayStrategy.START_QUICK && enumPlayStrategy == EnumPlayStrategy.M3U8_SMOOTH) {
                ijkMediaPlayer.setOption(4, "infbuf", 1L);
                ijkMediaPlayer.setOption(4, "max-fps", 25L);
            }
            ijkMediaPlayer.setOption(4, "start-on-prepared", 0L);
            ijkMediaPlayer.setOption(1, "http-detect-range-support", 0L);
            ijkMediaPlayer.setOption(2, "skip_loop_filter", 0L);
            ijkMediaPlayer.setOption(4, "soundtouch", 1L);
            ijkMediaPlayer.setOption(1, "dns_cache_clear", 1L);
            Uri uri = this.mUri;
            if (uri == null || !(uri.toString().startsWith("rtmp://") || this.mUri.toString().startsWith("rtsp://"))) {
                Uri uri2 = this.mUri;
                if (uri2 != null && (uri2.toString().startsWith(DeviceInfo.HTTP_PROTOCOL) || this.mUri.toString().startsWith(DeviceInfo.HTTPS_PROTOCOL))) {
                    if (this.mUri.toString().endsWith(".m3u8") || this.mUri.toString().endsWith(".M3U8")) {
                        this.isHttpCacheOpen = false;
                    } else {
                        ijkMediaPlayer.setOption(4, "enable-accurate-seek", 1L);
                    }
                    if (this.isHttpCacheOpen) {
                        VideoCacheUtil.cleanCacheIfNesscessary(this.mAppContext);
                        Uri uriCacheHttpUriWrap = VideoCacheUtil.cacheHttpUriWrap(this.mUri);
                        this.mUri = uriCacheHttpUriWrap;
                        String cacheFilePath = VideoCacheUtil.getCacheFilePath(this.mAppContext, uriCacheHttpUriWrap);
                        String cacheMapFilePath = VideoCacheUtil.getCacheMapFilePath(this.mAppContext, this.mUri);
                        if (!TextUtils.isEmpty(cacheFilePath) && !TextUtils.isEmpty(cacheMapFilePath)) {
                            ijkMediaPlayer.setOption(1, "cache_file_path", cacheFilePath);
                            ijkMediaPlayer.setOption(1, "cache_map_path", cacheMapFilePath);
                            ijkMediaPlayer.setOption(1, "auto_save_map", 1L);
                            ijkMediaPlayer.setOption(1, "parse_cache_map", 1L);
                        }
                    }
                }
            } else {
                if (this.mUri.toString().startsWith("rtsp://")) {
                    ijkMediaPlayer.setOption(1, "rtsp_transport", "tcp");
                }
                ijkMediaPlayer.setOption(1, "flush_packets", 1L);
                ijkMediaPlayer.setOption(4, "packet-buffering", 0L);
                ijkMediaPlayer.setOption(4, "framedrop", 1L);
            }
        }
        return this.mIsUsingMediaCodec ? new TextureMediaPlayer(ijkMediaPlayer) : ijkMediaPlayer;
    }

    public void deselectTrack(int i) {
        MediaPlayerCompat.deselectTrack(this.mMediaPlayer, i);
    }

    public void destroy() {
        release(true);
    }

    public void enterBackground() {
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) ((this.mCurrentState != 336 || this.isSetSeekTo) ? this.mMediaPlayer.getCurrentPosition() : this.mMediaPlayer.getDuration());
        }
        return 0;
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    public int getInterruptPosition() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            return (int) iMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public IMediaPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    public Bitmap getScreenshot() {
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            return iRenderView.getVideoScreenshot();
        }
        return null;
    }

    public int getSelectedTrack(int i) {
        return MediaPlayerCompat.getSelectedTrack(this.mMediaPlayer, i);
    }

    public ITrackInfo[] getTrackInfo() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return null;
        }
        return iMediaPlayer.getTrackInfo();
    }

    public Uri getUri() {
        return this.mUri;
    }

    public int getVideoHeight() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return 0;
        }
        return iMediaPlayer.getVideoHeight();
    }

    public Matrix getVideoTransform() {
        if (this.mOriginalMatrix == null) {
            this.mOriginalMatrix = this.mRenderView.getTransform();
        }
        return this.mRenderView.getTransform();
    }

    public int getVideoWidth() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return 0;
        }
        return iMediaPlayer.getVideoWidth();
    }

    public boolean isBackgroundPlayEnabled() {
        return this.mEnableBackgroundPlay;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (isInPlaybackState() && z && this.mMediaController != null) {
            if (i == 79 || i == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                } else {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            }
            if (i == 126) {
                if (!this.mMediaPlayer.isPlaying()) {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            }
            if (i == 86 || i == 127) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                }
                return true;
            }
            toggleMediaControlsVisibility();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 335;
            _notifyMediaStatus();
        }
        this.mTargetState = 335;
        showCoverImage();
    }

    public void release(boolean z) {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 330;
            _notifyMediaStatus();
            if (z) {
                this.mTargetState = 330;
            }
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    public void releaseWithoutStop() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.setDisplay(null);
        }
    }

    public void reload() {
        this.mCurrentState = 334;
        this.mTargetState = 334;
    }

    public void resetVideoView(boolean z) {
        this.mIsNormalScreen = z;
        this.mVideoRotationDegree = 0;
        if (z) {
            this.mVideoTargetRotationDegree = 0;
            this.mVideoScale = 1.0f;
        }
        this.mRenderView.setTransform(this.mOriginalMatrix);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
    }

    public void resume() {
        openVideo();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (!isInPlaybackState()) {
            this.mSeekWhenPrepared = i;
            this.isSetSeekTo = false;
            return;
        }
        this.mSeekStartTime = System.currentTimeMillis();
        this.mMediaPlayer.seekTo(i);
        this.mSeekWhenPrepared = 0;
        if (isPlaying()) {
            this.isSetSeekTo = false;
        } else {
            this.isSetSeekTo = true;
        }
    }

    public void selectTrack(int i) {
        MediaPlayerCompat.selectTrack(this.mMediaPlayer, i);
    }

    public void setAspectRatio(int i) {
        this.mCurrentAspectRatio = i;
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            iRenderView.setAspectRatio(i);
        }
    }

    public void setFlowStrategy(EnumPlayStrategy enumPlayStrategy) {
        this.enumPlayStrategy = enumPlayStrategy;
    }

    public void setHeaderInfo(String str) {
        this.headerInfo = str;
    }

    public void setIsHttpCacheOpen(boolean z) {
        this.isHttpCacheOpen = z;
    }

    public void setMediaController(IMediaController iMediaController) {
        IMediaController iMediaController2 = this.mMediaController;
        if (iMediaController2 != null) {
            iMediaController2.hide();
        }
        this.mMediaController = iMediaController;
        attachMediaController();
    }

    public void setOnBufferingUpdateListener(IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.bufferingUpdateListener = onBufferingUpdateListener;
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setRender(int i) {
        if (i == 0) {
            setRenderView(null);
            return;
        }
        if (i == 1) {
            setRenderView(new SurfaceRenderView(getContext()));
            return;
        }
        if (i != 2) {
            Log.e(this.TAG, String.format(Locale.US, "invalid render %d\n", Integer.valueOf(i)));
            return;
        }
        TextureRenderView textureRenderView = new TextureRenderView(getContext());
        if (this.mMediaPlayer != null) {
            textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
            textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
            textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
            textureRenderView.setAspectRatio(this.mCurrentAspectRatio);
        }
        setRenderView(textureRenderView);
    }

    public void setRenderView(IRenderView iRenderView) {
        int i;
        int i2;
        if (this.mRenderView != null) {
            IMediaPlayer iMediaPlayer = this.mMediaPlayer;
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallback(this.mSHCallback);
            this.mRenderView = null;
            removeView(view);
        }
        if (iRenderView == null) {
            return;
        }
        this.mRenderView = iRenderView;
        iRenderView.setAspectRatio(this.mCurrentAspectRatio);
        int i3 = this.mVideoWidth;
        if (i3 > 0 && (i2 = this.mVideoHeight) > 0) {
            iRenderView.setVideoSize(i3, i2);
        }
        int i4 = this.mVideoSarNum;
        if (i4 > 0 && (i = this.mVideoSarDen) > 0) {
            iRenderView.setVideoSampleAspectRatio(i4, i);
        }
        View view2 = this.mRenderView.getView();
        view2.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 17));
        addView(view2);
        this.mRenderView.addRenderCallback(this.mSHCallback);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
    }

    public void setSpeed(float f) {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer instanceof IjkMediaPlayer) {
            ((IjkMediaPlayer) iMediaPlayer).setSpeed(f);
        } else if (iMediaPlayer instanceof TextureMediaPlayer) {
            IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) ((TextureMediaPlayer) iMediaPlayer).getInternalMediaPlayer();
            if (ijkMediaPlayer instanceof IjkMediaPlayer) {
                ijkMediaPlayer.setSpeed(f);
            }
        }
    }

    public void setVideoFileDescriptor(AssetsDataSourceProvider assetsDataSourceProvider) {
        this.fd = assetsDataSourceProvider;
        this.mSeekWhenPrepared = 0;
        this.isSetSeekTo = false;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void setVideoRotation(int i) {
        int i2 = this.mVideoRotationDegree + i;
        this.mVideoTargetRotationDegree = i2;
        this.mRenderView.setVideoRotation(i2);
    }

    public void setVideoTransform(Matrix matrix) {
        this.mRenderView.setTransform(matrix);
    }

    public void setVideoURI(Uri uri) {
        this.customUA = "";
        new HashMap();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        try {
            Map<String, List<String>> map3 = CookieHandler.getDefault().get(URI.create(uri.toString()), map);
            if (map3 != null) {
                for (Map.Entry<String, List<String>> entry : map3.entrySet()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    if (key.equalsIgnoreCase("Cookie") || key.equalsIgnoreCase(HttpHeaders.HEAD_KEY_COOKIE2)) {
                        map2.put(key, BridgeInterceptor.buildCookieHeader(value));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            map2 = new HashMap();
        }
        try {
            JSONObject jSONObject = new JSONObject(this.headerInfo);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String strOptString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(next)) {
                    if ("User-Agent".equalsIgnoreCase(next)) {
                        this.customUA = strOptString;
                    } else {
                        map2.put(next, strOptString);
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        setVideoURI(uri, map2);
    }

    public void setmIsUsingMediaCodec(boolean z) {
        this.mIsUsingMediaCodec = z;
    }

    public void setvolume(float f) {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.setVolume(f, f);
        } else {
            this.isMediaReady = false;
            this.volume = f;
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        Log.e("TTAG", "start " + isInPlaybackState());
        ((AudioManager) this.mAppContext.getSystemService("audio")).requestAudioFocus(null, 3, 4);
        if (this.isStoped && this.mMediaPlayer == null && this.mCurrentState == 330) {
            this.isStoped = false;
            setRender(2);
            openVideo();
            requestLayout();
            invalidate();
        }
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 334;
            _notifyMediaStatus();
            this.isSetSeekTo = false;
        }
        this.mTargetState = 334;
        if (this.mCurrentState == 331) {
            this.mTargetState = 331;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        }
        this.coverImage.setVisibility(8);
    }

    public void stopBackgroundPlay() {
    }

    public void stopPlayback() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 330;
            this.mTargetState = 330;
            _notifyMediaStatus();
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
            this.isStoped = true;
            showCoverImage();
        }
    }

    public void suspend() {
        release(false);
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mHeaders = map;
        this.mSeekWhenPrepared = 0;
        this.isSetSeekTo = false;
        openVideo();
        requestLayout();
        invalidate();
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "TTAG";
        this.mCurrentState = 330;
        this.mTargetState = 330;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = true;
        this.mIsUsingMediaCodecAutoRotate = false;
        this.mIsMediaCodecHandleResolutionChange = false;
        this.mIsUsingOpenSLES = false;
        this.mPixelFormat = "";
        this.isHttpCacheOpen = false;
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.isStoped = false;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.enumPlayStrategy = EnumPlayStrategy.DEFAULT;
        this.customUA = "";
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = 333;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 334) {
                            IjkVideoView.this.start();
                            if (IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show();
                                return;
                            }
                            return;
                        }
                        if (IjkVideoView.this.isPlaying()) {
                            return;
                        }
                        if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
                IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, 337, -1);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = 336;
                IjkVideoView.this.mTargetState = 336;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
                }
                if (i == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    return true;
                }
                if (i == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i2);
                    return true;
                }
                if (i == 10002) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                    return true;
                }
                switch (i) {
                    case 700:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                        break;
                    case 702:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                        break;
                    case 703:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                        break;
                    default:
                        switch (i) {
                            case 800:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                break;
                            case 801:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                break;
                            case 802:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                break;
                        }
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("TTAG", "Error: " + i + "," + i2);
                IjkVideoView.this.mCurrentState = 331;
                IjkVideoView.this.mTargetState = 331;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener == null || IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i, i2)) {
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                IjkVideoView.this.mCurrentBufferPercentage = i;
                if (IjkVideoView.this.bufferingUpdateListener != null) {
                    IjkVideoView.this.bufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.9
            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i2;
                IjkVideoView.this.mSurfaceHeight = i3;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i2 || IjkVideoView.this.mVideoHeight != i3)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.isMediaReady = true;
        this.volume = 0.0f;
        this.isSetSeekTo = false;
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "TTAG";
        this.mCurrentState = 330;
        this.mTargetState = 330;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = true;
        this.mIsUsingMediaCodecAutoRotate = false;
        this.mIsMediaCodecHandleResolutionChange = false;
        this.mIsUsingOpenSLES = false;
        this.mPixelFormat = "";
        this.isHttpCacheOpen = false;
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.isStoped = false;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.enumPlayStrategy = EnumPlayStrategy.DEFAULT;
        this.customUA = "";
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i22, int i3, int i4) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = 333;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i2 = IjkVideoView.this.mSeekWhenPrepared;
                if (i2 != 0) {
                    IjkVideoView.this.seekTo(i2);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 334) {
                            IjkVideoView.this.start();
                            if (IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show();
                                return;
                            }
                            return;
                        }
                        if (IjkVideoView.this.isPlaying()) {
                            return;
                        }
                        if ((i2 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
                IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, 337, -1);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = 336;
                IjkVideoView.this.mTargetState = 336;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i22) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i2, i22);
                }
                if (i2 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    return true;
                }
                if (i2 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i2 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i2 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i22;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i22);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i22);
                    return true;
                }
                if (i2 == 10002) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                    return true;
                }
                switch (i2) {
                    case 700:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                        break;
                    case 702:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                        break;
                    case 703:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i22);
                        break;
                    default:
                        switch (i2) {
                            case 800:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                break;
                            case 801:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                break;
                            case 802:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                break;
                        }
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i22) {
                Log.d("TTAG", "Error: " + i2 + "," + i22);
                IjkVideoView.this.mCurrentState = 331;
                IjkVideoView.this.mTargetState = 331;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener == null || IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i2, i22)) {
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2) {
                IjkVideoView.this.mCurrentBufferPercentage = i2;
                if (IjkVideoView.this.bufferingUpdateListener != null) {
                    IjkVideoView.this.bufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i2);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.9
            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i22, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i22;
                IjkVideoView.this.mSurfaceHeight = i3;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i22 || IjkVideoView.this.mVideoHeight != i3)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i22) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.isMediaReady = true;
        this.volume = 0.0f;
        this.isSetSeekTo = false;
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    @TargetApi(21)
    public IjkVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.TAG = "TTAG";
        this.mCurrentState = 330;
        this.mTargetState = 330;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = true;
        this.mIsUsingMediaCodecAutoRotate = false;
        this.mIsMediaCodecHandleResolutionChange = false;
        this.mIsUsingOpenSLES = false;
        this.mPixelFormat = "";
        this.isHttpCacheOpen = false;
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.isStoped = false;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.enumPlayStrategy = EnumPlayStrategy.DEFAULT;
        this.customUA = "";
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i22, int i222, int i3, int i4) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = 333;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i22 = IjkVideoView.this.mSeekWhenPrepared;
                if (i22 != 0) {
                    IjkVideoView.this.seekTo(i22);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 334) {
                            IjkVideoView.this.start();
                            if (IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show();
                                return;
                            }
                            return;
                        }
                        if (IjkVideoView.this.isPlaying()) {
                            return;
                        }
                        if ((i22 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
                IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, 337, -1);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = 336;
                IjkVideoView.this.mTargetState = 336;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i22, int i222) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i22, i222);
                }
                if (i22 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    return true;
                }
                if (i22 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i22 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i22 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i222;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i222);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i222);
                    return true;
                }
                if (i22 == 10002) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                    return true;
                }
                switch (i22) {
                    case 700:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        break;
                    case 701:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                        break;
                    case 702:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                        break;
                    case 703:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i222);
                        break;
                    default:
                        switch (i22) {
                            case 800:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                break;
                            case 801:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                break;
                            case 802:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                break;
                        }
                        break;
                }
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i22, int i222) {
                Log.d("TTAG", "Error: " + i22 + "," + i222);
                IjkVideoView.this.mCurrentState = 331;
                IjkVideoView.this.mTargetState = 331;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener == null || IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i22, i222)) {
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i22) {
                IjkVideoView.this.mCurrentBufferPercentage = i22;
                if (IjkVideoView.this.bufferingUpdateListener != null) {
                    IjkVideoView.this.bufferingUpdateListener.onBufferingUpdate(iMediaPlayer, i22);
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: io.dcloud.media.weex.weex_video.ijkplayer.media.IjkVideoView.9
            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i222, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i222;
                IjkVideoView.this.mSurfaceHeight = i3;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i222 || IjkVideoView.this.mVideoHeight != i3)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i222) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.isMediaReady = true;
        this.volume = 0.0f;
        this.isSetSeekTo = false;
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }
}
