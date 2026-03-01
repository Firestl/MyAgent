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

/* JADX INFO: compiled from: MagicLatteFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class sc1 extends ud1 {
    public int[] w;
    public int x;

    /* JADX INFO: compiled from: MagicLatteFilter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glGenTextures(1, sc1.this.w, 0);
            GLES20.glBindTexture(3553, sc1.this.w[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            byte[] bArr = new byte[2048];
            int[] iArr = {5, 6, 8, 9, 11, 12, 14, 15, 16, 18, 19, 21, 22, 23, 25, 26, 28, 29, 30, 32, 33, 34, 36, 37, 39, 40, 41, 43, 44, 45, 46, 48, 49, 50, 52, 53, 54, 55, 56, 58, 59, 60, 61, 62, 64, 65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 108, 109, 110, 111, 112, 113, 114, 115, 115, 116, 117, 118, 119, 120, 120, 121, 122, 123, 124, 125, 125, 126, 127, 128, 129, 130, 130, com.igexin.push.core.a.c.h.b, 132, 133, 134, 134, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 137, 137, 138, 139, 140, 141, 141, 142, 143, 144, com.igexin.push.config.c.G, com.igexin.push.config.c.G, 146, 147, Code39Reader.ASTERISK_ENCODING, Code39Reader.ASTERISK_ENCODING, 149, 150, 151, 151, 152, 153, 154, 155, 155, 156, 157, 158, 158, 159, 160, 161, 162, 162, 163, 164, 165, 166, 166, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 170, 171, 172, 173, 174, 174, 175, 176, 177, 178, 178, 179, 180, 181, 182, DeviceUtils.EM_AARCH64, DeviceUtils.EM_AARCH64, 184, 185, 186, 187, 188, 189, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, JfifUtil.MARKER_RST0, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, 217, 218, 219, RecorderTask.FRAME_COUNT, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 237, 238, 239, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, 244, 245, 246, 247, 248, 249, 250, 251, 252, com.igexin.push.config.c.E, 254, 255};
            int[] iArr2 = {5, 6, 8, 11, 12, 14, 15, 18, 19, 21, 22, 25, 26, 28, 29, 32, 33, 34, 36, 39, 40, 41, 43, 44, 46, 48, 49, 50, 52, 54, 55, 56, 58, 59, 61, 62, 64, 65, 66, 67, 69, 71, 72, 73, 74, 75, 76, 78, 79, 80, 81, 82, 83, 85, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 108, 108, 109, 110, 111, 112, 113, 114, 115, 115, 116, 117, 118, 119, 120, 120, 121, 122, 123, 125, 125, 126, 127, 128, 129, 130, 130, com.igexin.push.core.a.c.h.b, 132, 133, 134, 134, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 137, 137, 138, 139, 140, 141, 141, 142, 143, 144, com.igexin.push.config.c.G, com.igexin.push.config.c.G, 146, 147, Code39Reader.ASTERISK_ENCODING, Code39Reader.ASTERISK_ENCODING, 149, 149, 150, 151, 151, 152, 153, 154, 155, 155, 156, 157, 158, 158, 159, 160, 161, 162, 162, 163, 164, 165, 165, 166, 166, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 170, 171, 172, 173, 174, 174, 175, 175, 176, 177, 178, 178, 179, 180, 181, 182, DeviceUtils.EM_AARCH64, DeviceUtils.EM_AARCH64, 184, 184, 185, 186, 187, 188, 189, 189, 190, 191, 192, 192, 193, 194, 195, 196, 197, 198, 198, 199, 199, 200, 201, 202, 203, 204, 205, 206, 206, 207, JfifUtil.MARKER_RST0, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, 217, 218, 219, 219, RecorderTask.FRAME_COUNT, 221, 222, 223, 224, 225, 226, 226, 227, 228, 229, 230, 231, 232, 232, 233, 234, 235, 237, 238, 239, 240, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, 244, 245, 246, 246, 247, 248, 249, 250, 251, 252, 252, com.igexin.push.config.c.E, 254, 255};
            int[] iArr3 = {5, 6, 8, 11, 12, 14, 15, 16, 18, 21, 22, 23, 25, 26, 28, 30, 32, 33, 34, 36, 37, 40, 41, 43, 44, 45, 46, 49, 50, 52, 53, 54, 55, 58, 59, 60, 61, 62, 64, 66, 67, 68, 69, 71, 72, 73, 75, 76, 77, 78, 79, 80, 81, 83, 85, 86, 87, 88, 89, 90, 92, 93, 94, 95, 95, 96, 97, 98, 99, 101, 102, 103, 104, 105, 106, 107, 108, 108, 109, 111, 112, 113, 114, 115, 115, 116, 117, 118, 119, 120, 120, 121, 123, 124, 125, 125, 126, 127, 128, 129, 130, 130, com.igexin.push.core.a.c.h.b, 132, 133, 134, 134, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 137, 137, 138, 139, 140, 141, 141, 142, 143, 144, com.igexin.push.config.c.G, com.igexin.push.config.c.G, 146, 147, Code39Reader.ASTERISK_ENCODING, Code39Reader.ASTERISK_ENCODING, 149, 150, 151, 151, 152, 153, 154, 155, 155, 156, 156, 157, 158, 158, 159, 160, 161, 162, 162, 163, 164, 165, 165, 166, 166, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 170, 170, 171, 172, 173, 174, 174, 175, 176, 176, 177, 178, 178, 179, 180, 180, 181, 182, DeviceUtils.EM_AARCH64, DeviceUtils.EM_AARCH64, 184, 184, 185, 186, 187, 188, 189, 189, 189, 190, 191, 192, 192, 193, 194, 195, 196, 196, 197, 198, 198, 199, 199, 200, 201, 202, 202, 203, 204, 205, 206, 206, 207, JfifUtil.MARKER_RST0, 209, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, 217, 218, 218, 219, RecorderTask.FRAME_COUNT, 221, 221, 222, 223, 224, 224, 225, 226, 227, 227, 228, 229, 230, 230, 231, 232, 233, 233, 234, 235, 237, 237, 238, 239, 240, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, 244};
            for (int i = 0; i < 256; i++) {
                int i2 = i * 4;
                bArr[i2] = (byte) iArr[i];
                bArr[i2 + 1] = (byte) iArr2[i];
                bArr[i2 + 2] = (byte) iArr3[i];
                bArr[i2 + 3] = -1;
            }
            int[] iArr4 = {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 24, 25, 26, 27, 28, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 54, 55, 56, 57, 58, 60, 61, 62, 63, 64, 66, 67, 68, 69, 71, 72, 73, 74, 75, 77, 78, 79, 80, 81, 83, 84, 85, 86, 87, 89, 90, 91, 92, 93, 95, 96, 97, 98, 99, 101, 102, 103, 104, 105, 107, 108, 109, 110, 111, 113, 114, 115, 116, 117, 119, 120, 121, 122, 123, 125, 126, 127, 128, 129, com.igexin.push.core.a.c.h.b, 132, 133, 134, 135, 137, 138, 139, 140, 141, 143, 144, com.igexin.push.config.c.G, 146, 147, 149, 150, 151, 152, 153, 155, 156, 157, 158, 159, 161, 162, 163, 164, 165, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 171, 173, 174, 175, 176, 177, 179, 180, 181, 182, 184, 185, 186, 187, 188, 190, 191, 192, 193, 194, 196, 197, 198, 199, 200, 202, 203, 204, 205, 206, JfifUtil.MARKER_RST0, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, 217, 218, RecorderTask.FRAME_COUNT, 221, 222, 223, 224, 226, 227, 228, 229, 230, 232, 233, 234, 235, 236, 238, 239, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240, 240};
            int[] iArr5 = {0, 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 41, 42, 43, 44, 46, 47, 48, 49, 51, 52, 53, 54, 56, 57, 58, 59, 61, 62, 63, 65, 66, 67, 69, 70, 71, 73, 74, 75, 77, 78, 79, 81, 82, 83, 85, 86, 87, 89, 90, 92, 93, 94, 96, 97, 99, 100, 101, 103, 104, 106, 107, 108, 110, 111, 113, 114, 116, 117, 119, 120, 121, 123, 124, 126, 127, 129, 130, 132, 133, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 138, 139, 140, 142, 143, com.igexin.push.config.c.G, 146, 147, 149, 150, 151, 153, 154, 155, 157, 158, 159, 160, 162, 163, 164, 165, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 171, 173, 174, 175, 176, 177, 178, 180, 181, 182, DeviceUtils.EM_AARCH64, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, JfifUtil.MARKER_RST0, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, 217, 218, 219, 219, RecorderTask.FRAME_COUNT, 221, 222, 222, 223, 224, 225, 225, 226, 227, 227, 228, 229, 229, 230, 231, 231, 232, 233, 233, 234, 234, 235, 236, 236, 237, 237, 238, 238, 239, 240, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, 244, 244, 244, 245, 245, 246, 246, 247, 247, 247, 248, 248, 249, 249, 249, 250, 250, 250, 251, 251, 251, 252, 252, 252, 252, com.igexin.push.config.c.E, com.igexin.push.config.c.E, com.igexin.push.config.c.E, com.igexin.push.config.c.E, 254, 254, 254, 254, 255, 255, 255};
            int[] iArr6 = {0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 13, 13, 13, 14, 14, 14, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 18, 19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 23, 23, 23, 24, 24, 24, 25, 25, 25, 25, 26, 26, 27, 27, 28, 28, 28, 28, 29, 29, 30, 29, 31, 31, 31, 31, 32, 32, 33, 33, 34, 34, 34, 34, 35, 35, 36, 36, 37, 37, 37, 38, 38, 39, 39, 39, 40, 40, 40, 41, 42, 42, 43, 43, 44, 44, 45, 45, 45, 46, 47, 47, 48, 48, 49, 50, 51, 51, 52, 52, 53, 53, 54, 55, 55, 56, 57, 57, 58, 59, 60, 60, 61, 62, 63, 63, 64, 65, 66, 67, 68, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 100, 101, 103, 104, 105, 107, 108, 110, 111, 113, 115, 116, 118, 119, 120, 122, 123, 125, 127, 128, 130, 132, 134, 135, 137, 139, 141, 143, 144, 146, Code39Reader.ASTERISK_ENCODING, 150, 152, 154, 156, 158, 160, 163, 165, 167, 169, 171, 173, 175, 178, 180, 182, 185, 187, 189, 192, 194, 197, 199, 201, 204, 206, 209, 211, 214, JfifUtil.MARKER_SOI, 219, 221, 224, 226, 229, 232, 234, 236, 239, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, 245, 247, 250, 252, 255};
            for (int i3 = 0; i3 < 256; i3++) {
                int i4 = (i3 * 4) + 1024;
                bArr[i4] = (byte) iArr5[i3];
                bArr[i4 + 1] = (byte) iArr4[i3];
                bArr[i4 + 2] = (byte) iArr6[i3];
                bArr[i4 + 3] = -1;
            }
            GLES20.glTexImage2D(3553, 0, 6408, 256, 2, 0, 6408, 5121, ByteBuffer.wrap(bArr));
        }
    }

    public sc1() {
        super(MagicFilterType.LATTE, R.raw.latte);
        this.w = new int[]{-1};
    }

    @Override // supwisdom.ud1
    public void i() {
        super.i();
        GLES20.glDeleteTextures(1, this.w, 0);
        this.w[0] = -1;
    }

    @Override // supwisdom.ud1
    public void j() {
        if (this.w[0] != -1) {
            GLES20.glActiveTexture(33987);
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
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.x = GLES20.glGetUniformLocation(f(), "curve");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(new a());
    }
}
