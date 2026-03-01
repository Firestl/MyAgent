package com.g.gysdk.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.g.gysdk.a.ap;
import com.g.gysdk.cta.ELoginThemeConfig;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class a extends SurfaceView implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2074a;
    public SurfaceHolder b;
    public String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2075e;
    public MediaPlayer f;
    public boolean g;
    public String h;

    public a(Context context) {
        super(context);
        this.f2074a = a.class.getSimpleName();
        this.d = "unknown";
        this.f2075e = "video";
        this.g = false;
        this.h = "unknown";
    }

    private int a(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return i2;
        }
        if (mode == 0) {
            return size;
        }
        if (mode != 1073741824) {
            return 0;
        }
        return Math.max(i2, size);
    }

    private void a(SurfaceHolder surfaceHolder) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f = mediaPlayer;
        mediaPlayer.setAudioStreamType(3);
        this.f.setDisplay(surfaceHolder);
        this.f.setOnPreparedListener(this);
        this.f.setVolume(0.0f, 0.0f);
        if (this.h.equals("video")) {
            c();
        }
    }

    private int b(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            return i2;
        }
        if (mode == 0) {
            return size;
        }
        if (mode != 1073741824) {
            return 0;
        }
        return Math.max(i2, size);
    }

    private void b() {
        this.b = getHolder();
        setFocusable(true);
        this.b.addCallback(this);
        this.b.setKeepScreenOn(true);
        Log.i(this.f2074a, "initSurface");
    }

    private void c() {
        d();
    }

    private void d() {
        try {
            if (this.f == null) {
                Log.i(getClass().getSimpleName(), "播放器初始化失败");
                return;
            }
            if (this.f.isPlaying()) {
                this.f.pause();
                this.f.stop();
            }
            this.f.reset();
            this.f.setDataSource(this.c);
            this.f.setLooping(true);
            this.f.prepareAsync();
            if (Build.VERSION.SDK_INT >= 16) {
                this.f.setVideoScalingMode(1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a() {
        try {
            if (this.f != null) {
                this.f.stop();
                this.f.release();
                this.f = null;
            }
            this.b = null;
            Log.i(this.f2074a, "surfaceholder stopped");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(ELoginThemeConfig eLoginThemeConfig) {
        if (eLoginThemeConfig != null) {
            try {
                if (!TextUtils.isEmpty(eLoginThemeConfig.getBuilder().getAuthVideoBGPath())) {
                    if (TextUtils.isEmpty(eLoginThemeConfig.getBuilder().getAuthVideoBGPath())) {
                        Log.e(this.f2074a, "video path is null");
                    } else {
                        setVideoPath(eLoginThemeConfig.getBuilder().getAuthVideoBGPath());
                        b();
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        try {
            setMeasuredDimension(b(i, 200), a(i2, 200));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        try {
            if (this.f != null) {
                this.f.start();
            }
        } catch (Throwable th) {
            ap.a(th.toString());
        }
    }

    public void setVideoPath(String str) {
        this.h = "video";
        this.c = str;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(this.f2074a, "surfaceCreated");
        try {
            a(surfaceHolder);
        } catch (Throwable th) {
            ap.a(th.toString());
        }
        this.g = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(this.f2074a, "surfaceDestroyed");
        try {
            if (this.f != null) {
                this.f.pause();
            }
        } catch (Throwable th) {
            ap.a(th.toString());
        }
    }
}
