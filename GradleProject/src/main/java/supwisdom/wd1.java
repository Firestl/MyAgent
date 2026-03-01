package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: GPUImageSaturationFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class wd1 extends ud1 {
    public int w;
    public float x;

    public wd1() {
        this(1.0f);
    }

    public void a(float f) {
        this.x = f;
        a(this.w, f);
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.w = GLES20.glGetUniformLocation(f(), "saturation");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.x);
    }

    public wd1(float f) {
        super(MagicFilterType.SATURATION, R.raw.saturation);
        this.x = f;
    }
}
