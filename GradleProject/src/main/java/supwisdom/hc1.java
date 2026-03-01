package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: MagicCrayonFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class hc1 extends ud1 {
    public int w;
    public int x;

    public hc1() {
        super(MagicFilterType.CRAYON, R.raw.crayon);
    }

    @Override // supwisdom.ud1
    public void c(int i, int i2) {
        a(this.w, new float[]{1.0f / i, 1.0f / i2});
    }

    @Override // supwisdom.ud1
    public void i() {
        super.i();
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.w = GLES20.glGetUniformLocation(f(), "singleStepOffset");
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(f(), "strength");
        this.x = iGlGetUniformLocation;
        a(iGlGetUniformLocation, 2.0f);
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.x, 0.5f);
    }
}
