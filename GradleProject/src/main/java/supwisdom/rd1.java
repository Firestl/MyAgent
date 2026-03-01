package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: GPUImageBrightnessFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class rd1 extends ud1 {
    public int w;
    public float x;

    public rd1() {
        this(0.0f);
    }

    public void a(float f) {
        this.x = f;
        a(this.w, f);
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.w = GLES20.glGetUniformLocation(f(), "brightness");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.x);
    }

    public rd1(float f) {
        super(MagicFilterType.BRIGHTNESS, R.raw.brightness);
        this.x = f;
    }
}
