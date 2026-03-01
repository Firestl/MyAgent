package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: MagicFreudFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class lc1 extends ud1 {
    public int A;
    public int w;
    public int x;
    public int[] y;
    public int[] z;

    /* JADX INFO: compiled from: MagicFreudFilter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            lc1.this.y[0] = yd1.b(lc1.this.d(), "filter/freud_rand.png");
        }
    }

    public lc1() {
        super(MagicFilterType.FREUD, R.raw.freud);
        this.y = new int[]{-1};
        this.z = new int[]{-1};
    }

    @Override // supwisdom.ud1
    public void c(int i, int i2) {
        super.c(i, i2);
        GLES20.glUniform1f(this.x, i);
        GLES20.glUniform1f(this.w, i2);
    }

    @Override // supwisdom.ud1
    public void i() {
        super.i();
        int i = 0;
        GLES20.glDeleteTextures(1, this.y, 0);
        while (true) {
            int[] iArr = this.y;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = -1;
            i++;
        }
    }

    @Override // supwisdom.ud1
    public void j() {
        int i = 0;
        while (true) {
            int[] iArr = this.y;
            if (i >= iArr.length || iArr[i] == -1) {
                return;
            }
            GLES20.glActiveTexture(i + 3 + 33984);
            GLES20.glBindTexture(3553, 0);
            GLES20.glActiveTexture(33984);
            i++;
        }
    }

    @Override // supwisdom.ud1
    public void k() {
        int i = 0;
        while (true) {
            int[] iArr = this.y;
            if (i >= iArr.length || iArr[i] == -1) {
                return;
            }
            int i2 = i + 3;
            GLES20.glActiveTexture(33984 + i2);
            GLES20.glBindTexture(3553, this.y[i]);
            GLES20.glUniform1i(this.z[i], i2);
            i++;
        }
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.z[0] = GLES20.glGetUniformLocation(f(), "inputImageTexture2");
        this.x = GLES20.glGetUniformLocation(f(), "inputImageTextureWidth");
        this.w = GLES20.glGetUniformLocation(f(), "inputImageTextureHeight");
        this.A = GLES20.glGetUniformLocation(f(), "strength");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.A, 1.0f);
        a(new a());
    }
}
