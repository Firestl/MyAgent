package supwisdom;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import java.util.List;
import supwisdom.c70;
import supwisdom.j50;
import supwisdom.q50;

/* JADX INFO: compiled from: SimpleExoPlayer.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public class xb0 implements j50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h90[] f9734a;
    public final j50 b;
    public final b c = new b();
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9735e;
    public com.google.android.exoplayer2.j f;
    public com.google.android.exoplayer2.j g;
    public Surface h;
    public boolean i;
    public SurfaceHolder j;
    public TextureView k;
    public c70.a l;
    public q50.a m;
    public c n;
    public m10 o;
    public d90 p;
    public x10 q;
    public x10 r;
    public int s;

    /* JADX INFO: compiled from: SimpleExoPlayer.java */
    public final class b implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener, m10, q50.a, c70.a, d90 {
        public b() {
        }

        @Override // supwisdom.c70.a
        public void a(List<y50> list) {
            if (xb0.this.l != null) {
                xb0.this.l.a(list);
            }
        }

        @Override // supwisdom.m10
        public void onAudioDecoderInitialized(String str, long j, long j2) {
            if (xb0.this.o != null) {
                xb0.this.o.onAudioDecoderInitialized(str, j, j2);
            }
        }

        @Override // supwisdom.m10
        public void onAudioDisabled(x10 x10Var) {
            if (xb0.this.o != null) {
                xb0.this.o.onAudioDisabled(x10Var);
            }
            xb0.this.g = null;
            xb0.this.r = null;
            xb0.this.s = 0;
        }

        @Override // supwisdom.m10
        public void onAudioEnabled(x10 x10Var) {
            xb0.this.r = x10Var;
            if (xb0.this.o != null) {
                xb0.this.o.onAudioEnabled(x10Var);
            }
        }

        @Override // supwisdom.m10
        public void onAudioInputFormatChanged(com.google.android.exoplayer2.j jVar) {
            xb0.this.g = jVar;
            if (xb0.this.o != null) {
                xb0.this.o.onAudioInputFormatChanged(jVar);
            }
        }

        @Override // supwisdom.m10
        public void onAudioSessionId(int i) {
            xb0.this.s = i;
            if (xb0.this.o != null) {
                xb0.this.o.onAudioSessionId(i);
            }
        }

        @Override // supwisdom.m10
        public void onAudioTrackUnderrun(int i, long j, long j2) {
            if (xb0.this.o != null) {
                xb0.this.o.onAudioTrackUnderrun(i, j, j2);
            }
        }

        @Override // supwisdom.d90
        public void onDroppedFrames(int i, long j) {
            if (xb0.this.p != null) {
                xb0.this.p.onDroppedFrames(i, j);
            }
        }

        @Override // supwisdom.q50.a
        public void onMetadata(com.google.android.exoplayer2.f.a aVar) {
            if (xb0.this.m != null) {
                xb0.this.m.onMetadata(aVar);
            }
        }

        @Override // supwisdom.d90
        public void onRenderedFirstFrame(Surface surface) {
            if (xb0.this.n != null && xb0.this.h == surface) {
                xb0.this.n.onRenderedFirstFrame();
            }
            if (xb0.this.p != null) {
                xb0.this.p.onRenderedFirstFrame(surface);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            xb0.this.a(new Surface(surfaceTexture), true);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            xb0.this.a((Surface) null, true);
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @Override // supwisdom.d90
        public void onVideoDecoderInitialized(String str, long j, long j2) {
            if (xb0.this.p != null) {
                xb0.this.p.onVideoDecoderInitialized(str, j, j2);
            }
        }

        @Override // supwisdom.d90
        public void onVideoDisabled(x10 x10Var) {
            if (xb0.this.p != null) {
                xb0.this.p.onVideoDisabled(x10Var);
            }
            xb0.this.f = null;
            xb0.this.q = null;
        }

        @Override // supwisdom.d90
        public void onVideoEnabled(x10 x10Var) {
            xb0.this.q = x10Var;
            if (xb0.this.p != null) {
                xb0.this.p.onVideoEnabled(x10Var);
            }
        }

        @Override // supwisdom.d90
        public void onVideoInputFormatChanged(com.google.android.exoplayer2.j jVar) {
            xb0.this.f = jVar;
            if (xb0.this.p != null) {
                xb0.this.p.onVideoInputFormatChanged(jVar);
            }
        }

        @Override // supwisdom.d90
        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (xb0.this.n != null) {
                xb0.this.n.onVideoSizeChanged(i, i2, i3, f);
            }
            if (xb0.this.p != null) {
                xb0.this.p.onVideoSizeChanged(i, i2, i3, f);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            xb0.this.a(surfaceHolder.getSurface(), false);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            xb0.this.a((Surface) null, false);
        }
    }

    /* JADX INFO: compiled from: SimpleExoPlayer.java */
    public interface c {
        void onRenderedFirstFrame();

        void onVideoSizeChanged(int i, int i2, int i3, float f);
    }

    public xb0(wb0 wb0Var, m70 m70Var, f90 f90Var) {
        Handler handler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        b bVar = this.c;
        h90[] h90VarArrA = wb0Var.a(handler, bVar, bVar, bVar, bVar);
        this.f9734a = h90VarArrA;
        int i = 0;
        int i2 = 0;
        for (h90 h90Var : h90VarArrA) {
            int iA = h90Var.a();
            if (iA == 1) {
                i2++;
            } else if (iA == 2) {
                i++;
            }
        }
        this.d = i;
        this.f9735e = i2;
        this.b = new e70(this.f9734a, m70Var, f90Var);
    }

    @Override // supwisdom.j50
    public int g() {
        return this.b.g();
    }

    public com.google.android.exoplayer2.j h() {
        return this.f;
    }

    public x10 i() {
        return this.q;
    }

    public final void j() {
        TextureView textureView = this.k;
        if (textureView != null) {
            if (textureView.getSurfaceTextureListener() != this.c) {
                Log.w("SimpleExoPlayer", "SurfaceTextureListener already unset or replaced.");
            } else {
                this.k.setSurfaceTextureListener(null);
            }
            this.k = null;
        }
        SurfaceHolder surfaceHolder = this.j;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.c);
            this.j = null;
        }
    }

    @Override // supwisdom.j50
    public g90 c() {
        return this.b.c();
    }

    @Override // supwisdom.j50
    public void d() {
        this.b.d();
        j();
        Surface surface = this.h;
        if (surface != null) {
            if (this.i) {
                surface.release();
            }
            this.h = null;
        }
    }

    @Override // supwisdom.j50
    public long e() {
        return this.b.e();
    }

    @Override // supwisdom.j50
    public long f() {
        return this.b.f();
    }

    @Override // supwisdom.j50
    public void b(j50.a aVar) {
        this.b.b(aVar);
    }

    @Override // supwisdom.j50
    public boolean b() {
        return this.b.b();
    }

    public void a(Surface surface) {
        j();
        a(surface, false);
    }

    @Override // supwisdom.j50
    public void b(j50.c... cVarArr) {
        this.b.b(cVarArr);
    }

    public void a(float f) {
        j50.c[] cVarArr = new j50.c[this.f9735e];
        int i = 0;
        for (h90 h90Var : this.f9734a) {
            if (h90Var.a() == 1) {
                cVarArr[i] = new j50.c(h90Var, 2, Float.valueOf(f));
                i++;
            }
        }
        this.b.a(cVarArr);
    }

    public void a(c cVar) {
        this.n = cVar;
    }

    public void a(q50.a aVar) {
        this.m = aVar;
    }

    public void a(d90 d90Var) {
        this.p = d90Var;
    }

    public void a(m10 m10Var) {
        this.o = m10Var;
    }

    @Override // supwisdom.j50
    public void a(j50.a aVar) {
        this.b.a(aVar);
    }

    @Override // supwisdom.j50
    public int a() {
        return this.b.a();
    }

    @Override // supwisdom.j50
    public void a(ua0 ua0Var) {
        this.b.a(ua0Var);
    }

    @Override // supwisdom.j50
    public void a(ua0 ua0Var, boolean z, boolean z2) {
        this.b.a(ua0Var, z, z2);
    }

    @Override // supwisdom.j50
    public void a(boolean z) {
        this.b.a(z);
    }

    @Override // supwisdom.j50
    public void a(long j) {
        this.b.a(j);
    }

    @Override // supwisdom.j50
    public void a(g90 g90Var) {
        this.b.a(g90Var);
    }

    @Override // supwisdom.j50
    public void a(j50.c... cVarArr) {
        this.b.a(cVarArr);
    }

    public final void a(Surface surface, boolean z) {
        j50.c[] cVarArr = new j50.c[this.d];
        int i = 0;
        for (h90 h90Var : this.f9734a) {
            if (h90Var.a() == 2) {
                cVarArr[i] = new j50.c(h90Var, 1, surface);
                i++;
            }
        }
        Surface surface2 = this.h;
        if (surface2 != null && surface2 != surface) {
            if (this.i) {
                surface2.release();
            }
            this.b.b(cVarArr);
        } else {
            this.b.a(cVarArr);
        }
        this.h = surface;
        this.i = z;
    }
}
