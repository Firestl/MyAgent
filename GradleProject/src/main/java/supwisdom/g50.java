package supwisdom;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import supwisdom.c70;
import supwisdom.q50;

/* JADX INFO: compiled from: DefaultRenderersFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class g50 implements wb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7686a;
    public final c20<e20> b;
    public final int c;
    public final long d;

    public g50(Context context) {
        this(context, null);
    }

    public void a(Context context, Handler handler, int i, ArrayList<h90> arrayList) {
    }

    public com.google.android.exoplayer2.a.c[] a() {
        return new com.google.android.exoplayer2.a.c[0];
    }

    @Override // supwisdom.wb0
    public h90[] a(Handler handler, d90 d90Var, m10 m10Var, c70.a aVar, q50.a aVar2) {
        ArrayList<h90> arrayList = new ArrayList<>();
        a(this.f7686a, this.b, this.d, handler, d90Var, this.c, arrayList);
        a(this.f7686a, this.b, a(), handler, m10Var, this.c, arrayList);
        a(this.f7686a, aVar, handler.getLooper(), this.c, arrayList);
        a(this.f7686a, aVar2, handler.getLooper(), this.c, arrayList);
        a(this.f7686a, handler, this.c, arrayList);
        return (h90[]) arrayList.toArray(new h90[arrayList.size()]);
    }

    public g50(Context context, c20<e20> c20Var) {
        this(context, c20Var, 0);
    }

    public g50(Context context, c20<e20> c20Var, int i) {
        this(context, c20Var, i, 5000L);
    }

    public g50(Context context, c20<e20> c20Var, int i, long j) {
        this.f7686a = context;
        this.b = c20Var;
        this.c = i;
        this.d = j;
    }

    public void a(Context context, c20<e20> c20Var, long j, Handler handler, d90 d90Var, int i, ArrayList<h90> arrayList) {
        arrayList.add(new b90(context, i50.f7915a, j, c20Var, false, handler, d90Var, 50));
        if (i == 0) {
            return;
        }
        int size = arrayList.size();
        if (i == 2) {
            size--;
        }
        try {
            arrayList.add(size, (h90) Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer").getConstructor(Boolean.TYPE, Long.TYPE, Handler.class, d90.class, Integer.TYPE).newInstance(true, Long.valueOf(j), handler, d90Var, 50));
            Log.i("DefaultRenderersFactory", "Loaded LibvpxVideoRenderer.");
        } catch (ClassNotFoundException unused) {
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public void a(Context context, c20<e20> c20Var, com.google.android.exoplayer2.a.c[] cVarArr, Handler handler, m10 m10Var, int i, ArrayList<h90> arrayList) {
        int i2;
        int i3;
        arrayList.add(new p10(i50.f7915a, c20Var, true, handler, m10Var, l10.a(context), cVarArr));
        if (i == 0) {
            return;
        }
        int size = arrayList.size();
        if (i == 2) {
            size--;
        }
        try {
            try {
                i2 = size + 1;
                try {
                    arrayList.add(size, (h90) Class.forName("com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer").getConstructor(Handler.class, m10.class, com.google.android.exoplayer2.a.c[].class).newInstance(handler, m10Var, cVarArr));
                    Log.i("DefaultRenderersFactory", "Loaded LibopusAudioRenderer.");
                } catch (ClassNotFoundException unused) {
                    size = i2;
                    i2 = size;
                }
            } catch (ClassNotFoundException unused2) {
            }
            try {
                try {
                    i3 = i2 + 1;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            } catch (ClassNotFoundException unused3) {
            }
            try {
                arrayList.add(i2, (h90) Class.forName("com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer").getConstructor(Handler.class, m10.class, com.google.android.exoplayer2.a.c[].class).newInstance(handler, m10Var, cVarArr));
                Log.i("DefaultRenderersFactory", "Loaded LibflacAudioRenderer.");
            } catch (ClassNotFoundException unused4) {
                i2 = i3;
                i3 = i2;
            }
            try {
                arrayList.add(i3, (h90) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(Handler.class, m10.class, com.google.android.exoplayer2.a.c[].class).newInstance(handler, m10Var, cVarArr));
                Log.i("DefaultRenderersFactory", "Loaded FfmpegAudioRenderer.");
            } catch (ClassNotFoundException unused5) {
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
        } catch (Exception e4) {
            throw new RuntimeException(e4);
        }
    }

    public void a(Context context, c70.a aVar, Looper looper, int i, ArrayList<h90> arrayList) {
        arrayList.add(new c70(aVar, looper));
    }

    public void a(Context context, q50.a aVar, Looper looper, int i, ArrayList<h90> arrayList) {
        arrayList.add(new q50(aVar, looper));
    }
}
