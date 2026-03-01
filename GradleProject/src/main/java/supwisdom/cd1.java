package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: MagicSketchFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class cd1 extends ud1 {
    public int w;
    public int x;

    public cd1() {
        super(MagicFilterType.SKETCH, R.raw.sketch);
    }

    @Override // supwisdom.ud1
    public void c(int i, int i2) {
        super.c(i, i2);
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
        this.x = GLES20.glGetUniformLocation(f(), "strength");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.x, 0.5f);
    }
}
