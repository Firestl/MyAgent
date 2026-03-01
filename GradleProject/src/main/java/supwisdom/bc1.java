package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: MagicBeautyFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class bc1 extends ud1 {
    public int w;

    public bc1() {
        super(MagicFilterType.BEAUTY, R.raw.beauty);
    }

    @Override // supwisdom.ud1
    public void c(int i, int i2) {
        super.c(i, i2);
        a(this.w, new float[]{2.0f / i, 2.0f / i2});
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.w = GLES20.glGetUniformLocation(f(), "singleStepOffset");
    }
}
