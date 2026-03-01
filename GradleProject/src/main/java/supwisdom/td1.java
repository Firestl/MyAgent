package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: GPUImageExposureFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class td1 extends ud1 {
    public int w;
    public float x;

    public td1() {
        this(0.0f);
    }

    public void a(float f) {
        this.x = f;
        a(this.w, f);
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.w = GLES20.glGetUniformLocation(f(), "exposure");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.x);
    }

    public td1(float f) {
        super(MagicFilterType.EXPOSURE, R.raw.exposure);
        this.x = f;
    }
}
