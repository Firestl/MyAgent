package com.github.faucamp.simplertmp.io;

import supwisdom.f10;

/* JADX INFO: loaded from: classes.dex */
public class WindowAckRequired extends Exception {
    public int bytesRead;
    public f10 rtmpPacket;

    public WindowAckRequired(int i, f10 f10Var) {
        this.rtmpPacket = f10Var;
        this.bytesRead = i;
    }

    public int getBytesRead() {
        return this.bytesRead;
    }

    public f10 getRtmpPacket() {
        return this.rtmpPacket;
    }
}
