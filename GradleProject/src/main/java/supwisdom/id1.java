package supwisdom;

import android.opengl.GLES20;
import com.baidu.speech.core.BDSHttpRequestMaker;
import com.facebook.imageutils.JfifUtil;
import com.google.zxing.oned.Code39Reader;
import com.seu.magicfilter.utils.MagicFilterType;
import com.tencent.ijk.media.player.IjkMediaPlayer;
import io.dcloud.feature.audio.recorder.RecorderTask;
import io.dcloud.feature.livepusher.R;
import java.nio.ByteBuffer;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;
import tv.cjump.jni.DeviceUtils;

/* JADX INFO: compiled from: MagicTenderFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class id1 extends ud1 {
    public int[] w;
    public int x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: MagicTenderFilter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glGenTextures(1, id1.this.w, 0);
            GLES20.glBindTexture(3553, id1.this.w[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            byte[] bArr = new byte[1024];
            int[] iArr = {10, 12, 14, 15, 17, 19, 21, 22, 24, 26, 28, 29, 31, 33, 35, 38, 40, 41, 43, 45, 47, 48, 50, 52, 53, 55, 57, 58, 60, 61, 63, 65, 66, 68, 69, 71, 72, 74, 75, 77, 79, 80, 81, 83, 84, 86, 87, 89, 92, 93, 94, 96, 97, 99, 100, 101, 103, 104, 105, 107, 108, 109, 110, 112, 113, 114, 116, 117, 118, 119, 120, 122, 123, 124, 125, 126, 127, 129, 130, com.igexin.push.core.a.c.h.b, 132, 133, 134, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 138, 139, 140, 141, 142, 143, 144, com.igexin.push.config.c.G, 146, 147, Code39Reader.ASTERISK_ENCODING, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 158, 159, 160, 161, 162, 163, 164, 165, 166, 166, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 171, 171, 172, 173, 174, 175, 175, 176, 177, 178, 179, 179, 180, 181, 182, 182, DeviceUtils.EM_AARCH64, 184, 184, 185, 186, 187, 187, 188, 189, 189, 190, 191, 191, 192, 193, 193, 194, 195, 195, 196, 196, 197, 198, 198, 199, 200, 200, 201, 201, 202, 202, 203, 204, 204, 205, 205, 206, 206, 207, 207, JfifUtil.MARKER_RST0, 209, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 213, 214, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, JfifUtil.MARKER_SOI, JfifUtil.MARKER_SOI, 217, 217, 218, 218, 219, 219, 219, RecorderTask.FRAME_COUNT, RecorderTask.FRAME_COUNT, 221, 221, 222, 222, 223, 223, 224, 224, 224, 225, 225, 226, 226, 227, 227, 227, 228, 228, 229, 229, 230, 230, 230, 231, 231, 232, 232, 232, 233, 233, 234, 234, 234, 234, 235, 235, 236, 236, 236, 237, 237, 238, 238, 238, 239, 239, 240, 240, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA};
            int[] iArr2 = {10, 12, 14, 15, 17, 19, 19, 21, 22, 24, 26, 28, 29, 31, 33, 35, 36, 36, 38, 40, 41, 43, 45, 47, 48, 50, 52, 52, 53, 55, 57, 58, 60, 61, 63, 65, 66, 68, 69, 69, 71, 72, 74, 75, 77, 79, 80, 81, 83, 84, 86, 86, 87, 89, 90, 92, 93, 94, 96, 97, 99, 100, 101, 103, 103, 104, 105, 107, 108, 109, 110, 112, 113, 114, 116, 117, 118, 119, 120, 122, 122, 123, 124, 125, 126, 127, 129, 130, com.igexin.push.core.a.c.h.b, 132, 133, 134, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 138, 139, 140, 141, 142, 143, 144, 144, com.igexin.push.config.c.G, 146, 147, Code39Reader.ASTERISK_ENCODING, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 158, 159, 160, 161, 162, 163, 164, 165, 166, 166, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 171, 171, 172, 173, 174, 175, 175, 176, 177, 178, 179, 179, 180, 181, 182, 182, DeviceUtils.EM_AARCH64, 184, 184, 185, 186, 187, 187, 188, 189, 190, 191, 191, 192, 193, 193, 194, 195, 195, 196, 196, 197, 198, 198, 199, 200, 200, 201, 201, 202, 202, 204, 204, 205, 205, 206, 206, 207, 207, JfifUtil.MARKER_RST0, 209, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 213, 214, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, JfifUtil.MARKER_SOI, 217, 217, 218, 218, 219, 219, RecorderTask.FRAME_COUNT, RecorderTask.FRAME_COUNT, 221, 221, 222, 222, 223, 223, 224, 224, 224, 225, 226, 226, 227, 227, 227, 228, 228, 229, 229, 230, 230, 231, 231, 232, 232, 232, 233, 233, 234, 234, 234, 235, 236, 236, 236, 237, 237, 238, 238, 238, 239, 239, 240, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA};
            int[] iArr3 = {10, 12, 12, 14, 15, 15, 17, 17, 19, 21, 21, 22, 24, 24, 26, 28, 28, 29, 31, 31, 33, 33, 35, 36, 36, 38, 40, 40, 41, 43, 43, 45, 47, 47, 48, 50, 52, 52, 53, 55, 55, 57, 58, 58, 60, 61, 63, 63, 65, 66, 68, 68, 69, 71, 71, 72, 74, 75, 77, 77, 79, 80, 81, 81, 83, 84, 86, 87, 87, 89, 90, 92, 93, 94, 94, 96, 97, 99, 100, 101, 103, 103, 104, 105, 107, 108, 109, 110, 112, 113, 113, 114, 116, 117, 118, 119, 120, 122, 123, 124, 125, 126, 127, 129, 130, 130, com.igexin.push.core.a.c.h.b, 132, 133, 134, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 138, 139, 140, 141, 142, 143, 144, com.igexin.push.config.c.G, 146, 147, Code39Reader.ASTERISK_ENCODING, 149, 150, 151, 152, 154, 155, 156, 157, 158, 158, 159, 160, 161, 162, 163, 164, 165, 166, 166, 167, 169, 170, 171, 171, 172, 173, 174, 175, 175, 176, 178, 179, 179, 180, 181, 182, 182, DeviceUtils.EM_AARCH64, 184, 185, 186, 187, 187, 188, 189, 190, 191, 191, 192, 193, 193, 195, 195, 196, 196, 197, 198, 199, 200, 200, 201, 201, 202, 203, 204, 204, 205, 206, 206, 207, 207, 209, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, 217, 217, 218, 218, 219, 219, RecorderTask.FRAME_COUNT, RecorderTask.FRAME_COUNT, 221, 222, 222, 223, 223, 224, 224, 225, 225, 226, 227, 227, 227, 228, 229, 229, 230, 230, 231, 231, 232, 232, 233, 233, 234, 234, 235, 235, 236, 236, 237, 238, 238, 238, 239, 240, 240, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA};
            int[] iArr4 = {0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 13, 13, 13, 14, 14, 14, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 18, 19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 23, 23, 23, 24, 24, 24, 25, 25, 25, 25, 26, 26, 27, 27, 28, 28, 28, 28, 29, 29, 30, 29, 31, 31, 31, 31, 32, 32, 33, 33, 34, 34, 34, 34, 35, 35, 36, 36, 37, 37, 37, 38, 38, 39, 39, 39, 40, 40, 40, 41, 42, 42, 43, 43, 44, 44, 45, 45, 45, 46, 47, 47, 48, 48, 49, 50, 51, 51, 52, 52, 53, 53, 54, 55, 55, 56, 57, 57, 58, 59, 60, 60, 61, 62, 63, 63, 64, 65, 66, 67, 68, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 100, 101, 103, 104, 105, 107, 108, 110, 111, 113, 115, 116, 118, 119, 120, 122, 123, 125, 127, 128, 130, 132, 134, 135, 137, 139, 141, 143, 144, 146, Code39Reader.ASTERISK_ENCODING, 150, 152, 154, 156, 158, 160, 163, 165, 167, 169, 171, 173, 175, 178, 180, 182, 185, 187, 189, 192, 194, 197, 199, 201, 204, 206, 209, 211, 214, JfifUtil.MARKER_SOI, 219, 221, 224, 226, 229, 232, 234, 236, 239, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, 245, 247, 250, 252, 255};
            for (int i = 0; i < 256; i++) {
                int i2 = i * 4;
                bArr[i2] = (byte) iArr[i];
                bArr[i2 + 1] = (byte) iArr2[i];
                bArr[i2 + 2] = (byte) iArr3[i];
                bArr[i2 + 3] = (byte) iArr4[i];
            }
            GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
            id1 id1Var = id1.this;
            id1Var.y = yd1.b(id1Var.d(), "filter/bluevintage_mask1.jpg");
        }
    }

    public id1() {
        super(MagicFilterType.TENDER, R.raw.tender);
        this.w = new int[]{-1};
        this.y = -1;
    }

    @Override // supwisdom.ud1
    public void i() {
        super.i();
        GLES20.glDeleteTextures(2, new int[]{this.w[0], this.y}, 0);
        this.w[0] = -1;
        this.y = -1;
    }

    @Override // supwisdom.ud1
    public void j() {
        if (this.w[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, 0);
            GLES20.glActiveTexture(33984);
        }
        if (this.y != -1) {
            GLES20.glActiveTexture(33988);
            GLES20.glBindTexture(3553, 0);
            GLES20.glActiveTexture(33984);
        }
    }

    @Override // supwisdom.ud1
    public void k() {
        if (this.w[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.w[0]);
            GLES20.glUniform1i(this.x, 3);
        }
        if (this.y != -1) {
            GLES20.glActiveTexture(33988);
            GLES20.glBindTexture(3553, this.y);
            GLES20.glUniform1i(this.z, 4);
        }
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.x = GLES20.glGetUniformLocation(f(), "curve");
        this.z = GLES20.glGetUniformLocation(f(), "grey1Frame");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(new a());
    }
}
