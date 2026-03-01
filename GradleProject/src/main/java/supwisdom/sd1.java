package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: GPUImageContrastFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class sd1 extends ud1 {
    public int w;
    public float x;

    public sd1() {
        this(1.0f);
    }

    public void a(float f) {
        this.x = f;
        a(this.w, f);
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.w = GLES20.glGetUniformLocation(f(), "contrast");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(this.x);
    }

    public sd1(float f) {
        super(MagicFilterType.CONTRAST, R.raw.constrast);
        this.x = f;
    }
}
