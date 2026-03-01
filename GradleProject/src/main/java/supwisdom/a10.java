package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;

/* JADX INFO: compiled from: Audio.java */
/* JADX INFO: loaded from: classes.dex */
public class a10 extends c10 {
    public a10(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    public String toString() {
        return "RTMP Audio";
    }

    public a10() {
        super(new RtmpHeader(RtmpHeader.ChunkType.TYPE_0_FULL, 7, RtmpHeader.MessageType.AUDIO));
    }
}
