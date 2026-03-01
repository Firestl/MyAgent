package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: MagicEarlyBirdFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class ic1 extends ud1 {
    public int[] w;
    public int[] x;
    public int y;

    /* JADX INFO: compiled from: MagicEarlyBirdFilter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ic1.this.w[0] = yd1.b(ic1.this.d(), "filter/earlybirdcurves.png");
            ic1.this.w[1] = yd1.b(ic1.this.d(), "filter/earlybirdoverlaymap_new.png");
            ic1.this.w[2] = yd1.b(ic1.this.d(), "filter/vignettemap_new.png");
            ic1.this.w[3] = yd1.b(ic1.this.d(), "filter/earlybirdblowout.png");
            ic1.this.w[4] = yd1.b(ic1.this.d(), "filter/earlybirdmap.png");
        }
    }

    public ic1() {
        super(MagicFilterType.EARLYBIRD, R.raw.earlybird);
        this.w = new int[]{-1, -1, -1, -1, -1};
        this.x = new int[]{-1, -1, -1, -1, -1};
    }

    @Override // supwisdom.ud1
    public void i() {
        super.i();
        int[] iArr = this.w;
        int i = 0;
        GLES20.glDeleteTextures(iArr.length, iArr, 0);
        while (true) {
            int[] iArr2 = this.w;
            if (i >= iArr2.length) {
                return;
            }
            iArr2[i] = -1;
            i++;
        }
    }

    @Override // supwisdom.ud1
    public void j() {
        int i = 0;
        while (true) {
            int[] iArr = this.w;
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
            int[] iArr = this.w;
            if (i >= iArr.length || iArr[i] == -1) {
                return;
            }
            int i2 = i + 3;
            GLES20.glActiveTexture(33984 + i2);
            GLES20.glBindTexture(3553, this.w[i]);
            GLES20.glUniform1i(this.x[i], i2);
            i++;
        }
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        int i = 0;
        while (true) {
            int[] iArr = this.x;
            if (i >= iArr.length) {
                this.y = GLES20.glGetUniformLocation(f(), "strength");
                return;
            }
            iArr[i] = GLES20.glGetUniformLocation(f(), "inputImageTexture" + (i + 2));
            i++;
        }
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.y, 1.0f);
        a(new a());
    }
}
