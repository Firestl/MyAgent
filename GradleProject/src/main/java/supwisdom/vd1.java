package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: GPUImageHueFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class vd1 extends ud1 {
    public float w;
    public int x;

    public vd1() {
        this(0.0f);
    }

    public void a(float f) {
        this.w = f;
        a(this.x, ((f % 360.0f) * 3.1415927f) / 180.0f);
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.x = GLES20.glGetUniformLocation(f(), "hueAdjust");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.w);
    }

    public vd1(float f) {
        super(MagicFilterType.HUE, R.raw.hue);
        this.w = f;
    }
}
