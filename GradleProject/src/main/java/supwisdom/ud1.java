package supwisdom;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;

/* JADX INFO: compiled from: GPUImageFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class ud1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9397a;
    public Context b;
    public final LinkedList<Runnable> c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9398e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public FloatBuffer o;
    public FloatBuffer p;
    public int[] q;
    public int[] r;
    public float[] s;
    public int[] t;
    public int[] u;
    public IntBuffer v;

    /* JADX INFO: compiled from: GPUImageFilter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f9399a;
        public final /* synthetic */ int b;

        public a(ud1 ud1Var, int i, int i2) {
            this.f9399a = i;
            this.b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform1i(this.f9399a, this.b);
        }
    }

    /* JADX INFO: compiled from: GPUImageFilter.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f9400a;
        public final /* synthetic */ float b;

        public b(ud1 ud1Var, int i, float f) {
            this.f9400a = i;
            this.b = f;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform1f(this.f9400a, this.b);
        }
    }

    /* JADX INFO: compiled from: GPUImageFilter.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f9401a;
        public final /* synthetic */ float[] b;

        public c(ud1 ud1Var, int i, float[] fArr) {
            this.f9401a = i;
            this.b = fArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform2fv(this.f9401a, 1, FloatBuffer.wrap(this.b));
        }
    }

    public ud1() {
        this(MagicFilterType.NONE);
    }

    public void a(Context context) {
        this.b = context;
        l();
        m();
    }

    public void b(int i) {
        this.f9398e = i;
    }

    public void c(int i, int i2) {
        this.k = i;
        this.l = i2;
        a(i, i2);
    }

    public Context d() {
        return this.b;
    }

    public IntBuffer e() {
        return this.v;
    }

    public int f() {
        return this.f;
    }

    public final void g() {
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.o = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f}).position(0);
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.p = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f}).position(0);
        int[] iArr = new int[1];
        this.q = iArr;
        this.r = new int[1];
        GLES20.glGenBuffers(1, iArr, 0);
        GLES20.glBindBuffer(34962, this.q[0]);
        GLES20.glBufferData(34962, this.o.capacity() * 4, this.o, 35044);
        GLES20.glGenBuffers(1, this.r, 0);
        GLES20.glBindBuffer(34962, this.r[0]);
        GLES20.glBufferData(34962, this.p.capacity() * 4, this.p, 35044);
    }

    public final void h() {
        int iA = yd1.a(yd1.a(d(), this.d), yd1.a(d(), this.f9398e));
        this.f = iA;
        this.g = GLES20.glGetAttribLocation(iA, "position");
        Log.d("dqqdo", "mGLPositionIndex   ===   " + this.g);
        this.i = GLES20.glGetAttribLocation(this.f, "inputTextureCoordinate");
        this.j = GLES20.glGetUniformLocation(this.f, "textureTransform");
        this.h = GLES20.glGetUniformLocation(this.f, "inputImageTexture");
    }

    public void i() {
    }

    public void j() {
    }

    public void k() {
    }

    public void l() {
        g();
        h();
    }

    public void m() {
        this.f9397a = true;
    }

    public final void n() {
        while (!this.c.isEmpty()) {
            this.c.removeFirst().run();
        }
    }

    public ud1(MagicFilterType magicFilterType) {
        this(magicFilterType, R.raw.vertex, R.raw.fragment);
    }

    public final void b() {
        this.f9397a = false;
        c();
        a();
        GLES20.glDeleteProgram(this.f);
        i();
    }

    public void d(int i, int i2) {
        a(new a(this, i, i2));
    }

    public ud1(MagicFilterType magicFilterType, int i) {
        this(magicFilterType, R.raw.vertex, i);
    }

    public ud1(MagicFilterType magicFilterType, int i, int i2) {
        MagicFilterType magicFilterType2 = MagicFilterType.NONE;
        this.c = new LinkedList<>();
        this.d = i;
        this.f9398e = i2;
    }

    public final void a() {
        int[] iArr = this.q;
        if (iArr != null) {
            GLES20.glDeleteBuffers(1, iArr, 0);
            this.q = null;
        }
        int[] iArr2 = this.r;
        if (iArr2 != null) {
            GLES20.glDeleteBuffers(1, iArr2, 0);
            this.r = null;
        }
    }

    public final void c() {
        int[] iArr = this.u;
        if (iArr != null) {
            GLES20.glDeleteTextures(1, iArr, 0);
            this.u = null;
        }
        int[] iArr2 = this.t;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(1, iArr2, 0);
            this.t = null;
        }
    }

    public void b(int i, int i2) {
        this.m = i;
        this.n = i2;
    }

    public final void a(int i, int i2) {
        if (this.t != null && (this.k != i || this.l != i2)) {
            c();
        }
        this.t = new int[1];
        this.u = new int[1];
        this.v = IntBuffer.allocate(i * i2);
        GLES20.glGenFramebuffers(1, this.t, 0);
        GLES20.glGenTextures(1, this.u, 0);
        GLES20.glBindTexture(3553, this.u[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.t[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.u[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    public int a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (!this.f9397a) {
            return -1;
        }
        GLES20.glUseProgram(this.f);
        n();
        GLES20.glEnableVertexAttribArray(this.g);
        GLES20.glVertexAttribPointer(this.g, 2, 5126, false, 8, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.i);
        GLES20.glVertexAttribPointer(this.i, 2, 5126, false, 8, (Buffer) floatBuffer2);
        if (i != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
            GLES20.glUniform1i(this.h, 0);
        }
        k();
        GLES20.glDrawArrays(5, 0, 4);
        j();
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisableVertexAttribArray(this.g);
        GLES20.glDisableVertexAttribArray(this.i);
        return 1;
    }

    public int a(int i) {
        if (!this.f9397a || this.t == null) {
            return -1;
        }
        GLES20.glUseProgram(this.f);
        n();
        GLES20.glBindBuffer(34962, this.q[0]);
        GLES20.glEnableVertexAttribArray(this.g);
        GLES20.glVertexAttribPointer(this.g, 2, 5126, false, 8, 0);
        GLES20.glBindBuffer(34962, this.r[0]);
        GLES20.glEnableVertexAttribArray(this.i);
        GLES20.glVertexAttribPointer(this.i, 2, 5126, false, 8, 0);
        GLES20.glUniformMatrix4fv(this.j, 1, false, this.s, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        GLES20.glUniform1i(this.h, 0);
        k();
        GLES20.glViewport(0, 0, this.k, this.l);
        GLES20.glBindFramebuffer(36160, this.t[0]);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glReadPixels(0, 0, this.k, this.l, 6408, 5121, this.v);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(0, 0, this.m, this.n);
        GLES20.glDrawArrays(5, 0, 4);
        j();
        GLES20.glBindTexture(36197, 0);
        GLES20.glDisableVertexAttribArray(this.g);
        GLES20.glDisableVertexAttribArray(this.i);
        GLES20.glBindBuffer(34962, 0);
        return this.u[0];
    }

    public void a(float[] fArr) {
        this.s = fArr;
    }

    public void a(int i, float f) {
        a(new b(this, i, f));
    }

    public void a(int i, float[] fArr) {
        a(new c(this, i, fArr));
    }

    public void a(Runnable runnable) {
        synchronized (this.c) {
            this.c.addLast(runnable);
        }
    }
}
