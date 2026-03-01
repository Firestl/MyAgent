package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;

/* JADX INFO: compiled from: Video.java */
/* JADX INFO: loaded from: classes.dex */
public class i10 extends c10 {
    public i10(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    public String toString() {
        return "RTMP Video";
    }

    public i10() {
        super(new RtmpHeader(RtmpHeader.ChunkType.TYPE_0_FULL, 6, RtmpHeader.MessageType.VIDEO));
    }
}
