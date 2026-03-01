package supwisdom;

import android.util.Pair;
import com.google.android.exoplayer2.d.a.d;
import java.util.Collections;
import java.util.List;
import net.ossrs.yasea.SrsFlvMuxer;

/* JADX INFO: compiled from: AudioTagPayloadReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class g20 extends com.google.android.exoplayer2.d.a.d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f7681e = {SrsFlvMuxer.SrsCodecAudioSampleRate.R5512, SrsFlvMuxer.SrsCodecAudioSampleRate.R11025, SrsFlvMuxer.SrsCodecAudioSampleRate.R22050, 44100};
    public boolean b;
    public boolean c;
    public int d;

    public g20(f50 f50Var) {
        super(f50Var);
    }

    @Override // com.google.android.exoplayer2.d.a.d
    public boolean a(o80 o80Var) throws d.a {
        if (this.b) {
            o80Var.d(1);
        } else {
            int iG = o80Var.g();
            int i = (iG >> 4) & 15;
            this.d = i;
            if (i == 2) {
                this.f2270a.a(com.google.android.exoplayer2.j.a(null, "audio/mpeg", null, -1, -1, 1, f7681e[(iG >> 2) & 3], null, null, 0, null));
                this.c = true;
            } else if (i == 7 || i == 8) {
                this.f2270a.a(com.google.android.exoplayer2.j.a((String) null, this.d == 7 ? "audio/g711-alaw" : "audio/g711-mlaw", (String) null, -1, -1, 1, 8000, (iG & 1) == 1 ? 2 : 3, (List<byte[]>) null, (com.google.android.exoplayer2.c.a) null, 0, (String) null));
                this.c = true;
            } else if (i != 10) {
                throw new d.a("Audio format not supported: " + this.d);
            }
            this.b = true;
        }
        return true;
    }

    @Override // com.google.android.exoplayer2.d.a.d
    public void a(o80 o80Var, long j) {
        if (this.d == 2) {
            int iB = o80Var.b();
            this.f2270a.a(o80Var, iB);
            this.f2270a.a(j, 1, iB, 0, null);
            return;
        }
        int iG = o80Var.g();
        if (iG == 0 && !this.c) {
            int iB2 = o80Var.b();
            byte[] bArr = new byte[iB2];
            o80Var.a(bArr, 0, iB2);
            Pair<Integer, Integer> pairA = f80.a(bArr);
            this.f2270a.a(com.google.android.exoplayer2.j.a(null, "audio/mp4a-latm", null, -1, -1, ((Integer) pairA.second).intValue(), ((Integer) pairA.first).intValue(), Collections.singletonList(bArr), null, 0, null));
            this.c = true;
            return;
        }
        if (this.d != 10 || iG == 1) {
            int iB3 = o80Var.b();
            this.f2270a.a(o80Var, iB3);
            this.f2270a.a(j, 1, iB3, 0, null);
        }
    }
}
