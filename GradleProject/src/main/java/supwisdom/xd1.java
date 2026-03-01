package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: GPUImageSharpenFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class xd1 extends ud1 {
    public int w;
    public float x;
    public int y;
    public int z;

    public xd1() {
        this(0.0f);
    }

    public void a(float f) {
        this.x = f;
        a(this.w, f);
    }

    @Override // supwisdom.ud1
    public void c(int i, int i2) {
        super.c(i, i2);
        a(this.y, 1.0f / i);
        a(this.z, 1.0f / i2);
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.w = GLES20.glGetUniformLocation(f(), "sharpness");
        this.y = GLES20.glGetUniformLocation(f(), "imageWidthFactor");
        this.z = GLES20.glGetUniformLocation(f(), "imageHeightFactor");
        a(this.x);
    }

    public xd1(float f) {
        super(MagicFilterType.SHARPEN, R.raw.vertex_sharpen, R.raw.sharpen);
        this.x = f;
    }
}
