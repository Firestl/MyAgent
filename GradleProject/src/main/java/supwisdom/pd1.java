package supwisdom;

import android.content.Context;
import android.opengl.GLES20;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MagicBaseGroupFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class pd1 extends ud1 {
    public static int[] A;
    public static int[] z;
    public int w = -1;
    public int x = -1;
    public List<ud1> y;

    public pd1(List<ud1> list) {
        this.y = list;
    }

    @Override // supwisdom.ud1
    public void a(Context context) {
        Iterator<ud1> it = this.y.iterator();
        while (it.hasNext()) {
            it.next().a(context);
        }
    }

    @Override // supwisdom.ud1
    public void c(int i, int i2) {
        super.c(i, i2);
        int size = this.y.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.y.get(i3).c(i, i2);
        }
        int[] iArr = z;
        if (iArr != null && (this.w != i || this.x != i2 || iArr.length != size - 1)) {
            o();
            this.w = i;
            this.x = i2;
        }
        if (z == null) {
            int i4 = 1;
            int i5 = size - 1;
            z = new int[i5];
            A = new int[i5];
            int i6 = 0;
            while (i6 < i5) {
                GLES20.glGenFramebuffers(i4, z, i6);
                GLES20.glGenTextures(i4, A, i6);
                GLES20.glBindTexture(3553, A[i6]);
                GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
                GLES20.glTexParameterf(3553, 10240, 9729.0f);
                GLES20.glTexParameterf(3553, 10241, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
                GLES20.glBindFramebuffer(36160, z[i6]);
                GLES20.glFramebufferTexture2D(36160, 36064, 3553, A[i6], 0);
                GLES20.glBindTexture(3553, 0);
                GLES20.glBindFramebuffer(36160, 0);
                i6++;
                i4 = 1;
            }
        }
    }

    @Override // supwisdom.ud1
    public void i() {
        Iterator<ud1> it = this.y.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
        o();
    }

    public final void o() {
        int[] iArr = A;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            A = null;
        }
        int[] iArr2 = z;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            z = null;
        }
    }

    @Override // supwisdom.ud1
    public int a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (z == null || A == null) {
            return -1;
        }
        int size = this.y.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                return 1;
            }
            ud1 ud1Var = this.y.get(i2);
            if (i2 < size + (-1)) {
                GLES20.glViewport(0, 0, this.k, this.l);
                GLES20.glBindFramebuffer(36160, z[i2]);
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                ud1Var.a(i, this.o, this.p);
                GLES20.glBindFramebuffer(36160, 0);
                i = A[i2];
            } else {
                GLES20.glViewport(0, 0, this.m, this.n);
                ud1Var.a(i, floatBuffer, floatBuffer2);
            }
            i2++;
        }
    }

    @Override // supwisdom.ud1
    public int a(int i) {
        if (z == null || A == null) {
            return -1;
        }
        int size = this.y.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                return 1;
            }
            ud1 ud1Var = this.y.get(i2);
            if (i2 < size + (-1)) {
                GLES20.glBindFramebuffer(36160, z[i2]);
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                ud1Var.a(i, this.o, this.p);
                GLES20.glBindFramebuffer(36160, 0);
                i = A[i2];
            } else {
                ud1Var.a(i, this.o, this.p);
            }
            i2++;
        }
    }
}
