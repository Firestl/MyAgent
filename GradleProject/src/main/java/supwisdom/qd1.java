package supwisdom;

import android.opengl.GLES20;
import com.seu.magicfilter.utils.MagicFilterType;
import io.dcloud.feature.livepusher.R;

/* JADX INFO: compiled from: MagicLookupFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class qd1 extends ud1 {
    public String w;
    public int x;
    public int y;

    /* JADX INFO: compiled from: MagicLookupFilter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            qd1 qd1Var = qd1.this;
            qd1Var.y = yd1.b(qd1Var.d(), qd1.this.w);
        }
    }

    public qd1(String str) {
        super(MagicFilterType.LOCKUP, R.raw.lookup);
        this.y = -1;
        this.w = str;
    }

    @Override // supwisdom.ud1
    public void i() {
        super.i();
        GLES20.glDeleteTextures(1, new int[]{this.y}, 0);
        this.y = -1;
    }

    @Override // supwisdom.ud1
    public void j() {
        if (this.y != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, 0);
            GLES20.glActiveTexture(33984);
        }
    }

    @Override // supwisdom.ud1
    public void k() {
        if (this.y != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.y);
            GLES20.glUniform1i(this.x, 3);
        }
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.x = GLES20.glGetUniformLocation(f(), "inputImageTexture2");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(new a());
    }
}
