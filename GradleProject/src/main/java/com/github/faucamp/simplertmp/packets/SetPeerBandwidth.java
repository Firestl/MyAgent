package com.github.faucamp.simplertmp.packets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import supwisdom.f10;
import supwisdom.j00;

/* JADX INFO: loaded from: classes.dex */
public class SetPeerBandwidth extends f10 {
    public int b;
    public LimitType c;

    public enum LimitType {
        HARD(0),
        SOFT(1),
        DYNAMIC(2);

        public static final Map<Integer, LimitType> quickLookupMap = new HashMap();
        public int intValue;

        static {
            for (LimitType limitType : values()) {
                quickLookupMap.put(Integer.valueOf(limitType.getIntValue()), limitType);
            }
        }

        LimitType(int i) {
            this.intValue = i;
        }

        public int getIntValue() {
            return this.intValue;
        }

        public static LimitType valueOf(int i) {
            return quickLookupMap.get(Integer.valueOf(i));
        }
    }

    public SetPeerBandwidth(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    @Override // supwisdom.f10
    public void a(InputStream inputStream) throws IOException {
        this.b = j00.d(inputStream);
        this.c = LimitType.valueOf(inputStream.read());
    }

    @Override // supwisdom.f10
    public byte[] a() {
        return null;
    }

    @Override // supwisdom.f10
    public int c() {
        return 0;
    }

    public int d() {
        return this.b;
    }

    public String toString() {
        return "RTMP Set Peer Bandwidth";
    }

    @Override // supwisdom.f10
    public void a(OutputStream outputStream) throws IOException {
        j00.c(outputStream, this.b);
        outputStream.write(this.c.getIntValue());
    }
}
